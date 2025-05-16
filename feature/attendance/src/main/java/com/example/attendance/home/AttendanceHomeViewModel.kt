package com.example.attendance.home

import com.example.domain.model.User
import com.example.domain.usecase.GetAdminUseCase
import com.example.domain.usecase.GetUserUseCase
import com.example.domain.usecase.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.common.ResourceProvider
import com.example.common.toDateString
import com.example.domain.dataresource.DataResource
import com.example.feature.attendance.R
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AttendanceHomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getAdminUseCase: GetAdminUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<AttendanceHomeContract.Event, AttendanceHomeContract.State, AttendanceHomeContract.Effect>() {

    init {
        getAdminData()
    }

    override fun createInitialState(): AttendanceHomeContract.State {
        return AttendanceHomeContract.State()
    }

    override fun handleEvent(event: AttendanceHomeContract.Event) {
        when (event) {
            is AttendanceHomeContract.Event.OnClickAdminMenu -> {
                setEffect {
                    AttendanceHomeContract.Effect.GoAdminPage
                }
            }

            is AttendanceHomeContract.Event.OnClickNumber -> {
                if(event.number == "←"){
                    setState {
                        this.copy(phoneNumber = phoneNumber.dropLast(1))
                    }
                }else {
                    if (uiState.value.phoneNumber.length < 8) {
                        setState {
                            this.copy(phoneNumber = phoneNumber + event.number)
                        }
                    }
                }
            }

            is AttendanceHomeContract.Event.OnClickOK -> {
                checkUser("010${event.phoneNumber}")
            }
        }
    }

    private fun checkUser(phoneNumber: String) {
        viewModelScope.launch {
            getUserUseCase(phoneNumber).collect {
                when (it) {
                    is DataResource.Success -> {
                        setState { this.copy(isLoading = false) }

                        val user = it.data

                        if (!checkRegisterDate(user)) {
                            val msg = resourceProvider.getString(R.string.text_noti_retry_phone_number)
                            setEffect { AttendanceHomeContract.Effect.ShowToast(msg) }
                            return@collect
                        }

                        if (checkAlreadyAttendance(user)) { //오늘 이미 출석을 한 회원이라면
                            val maxAttendanceCount = uiState.value.adminData?.maxAttendance ?: 0
                            if (checkExceedAttendanceCount(maxAttendanceCount, user)) {
                                val msg = resourceProvider.getString(R.string.text_noti_already_attendance_user)
                                setEffect { AttendanceHomeContract.Effect.ShowToast(msg) }
                            } else {
                                user.attendanceCountOfToday += 1
                                user.mileage += 1
                                updateUser(user)
                            }
                        } else { //오늘 출석을 하지 않은 회원
                            user.attendanceDate = Calendar.getInstance().timeInMillis
                            user.attendanceCountOfToday = 1
                            user.mileage += 1
                            updateUser(user)
                        }
                    }

                    is DataResource.Error -> {
                        val msg = resourceProvider.getString(R.string.text_noti_retry_phone_number)
                        setEffect { AttendanceHomeContract.Effect.ShowToast(msg) }
                        setState { this.copy(isLoading = false) }
                    }

                    is DataResource.Loading -> {
                        setState { this.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun getAdminData() {
        viewModelScope.launch {
            getAdminUseCase().collect {
                when (it) {
                    is DataResource.Loading -> {
                        setState { this.copy(isLoading = true) }
                    }

                    is DataResource.Success -> {
                        setState {
                            this.copy(
                                adminData = it.data,
                                isLoading = false
                            )
                        }
                    }

                    is DataResource.Error -> {
                        setState { this.copy(isLoading = false) }
                    }

                }
            }
        }
    }

    private fun updateUser(user: User) {
        viewModelScope.launch {
            updateUserUseCase(user).collect {
                when (it) {
                    is DataResource.Loading -> {
                        setState { this.copy(isLoading = true) }
                    }

                    is DataResource.Success -> {
                        setEffect {
                            AttendanceHomeContract.Effect.SuccessAttendance(user)
                        }
                        setState {
                            this.copy(
                                searchedUser = user,
                                isLoading = false
                            )
                        }
                    }

                    is DataResource.Error -> {
                        setState { this.copy(isLoading = false) }
                    }
                }
            }
        }
    }

    //등록된 기간 범위 안에 있는 회원인지 체크
    private fun checkRegisterDate(user: User): Boolean {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.timeInMillis

        return user.startDateTime <= currentTime && currentTime <= user.endDateTime
    }

    //이미 오늘 출석을 한 회원인지 체크
    private fun checkAlreadyAttendance(user: User): Boolean {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.timeInMillis
        val currentDate = currentTime.toDateString()
        val attendanceDate = user.attendanceDate.toDateString()

        return currentDate == attendanceDate
    }

    //하루 출석횟수 초과한 회원인지 체크
    private fun checkExceedAttendanceCount(maxCount: Int, user: User): Boolean {
        return user.attendanceCountOfToday >= maxCount
    }
}