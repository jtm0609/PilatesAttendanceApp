package com.example.presentation.ui.feature.attendance.main

import com.example.presentation.utils.LogUtil
import com.example.presentation.utils.ResourceProvider
import com.example.presentation.utils.toDateString
import com.example.domain.model.User
import com.example.domain.usecase.GetAdminUseCase
import com.example.domain.usecase.GetUserUseCase
import com.example.domain.usecase.UpdateUserUseCase
import com.example.presentation.R
import com.example.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.dataresource.DataResource
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getAdminUseCase: GetAdminUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<AttendanceMainContract.Event, AttendanceMainContract.State, AttendanceMainContract.Effect>() {

    init {
        getAdminData()
    }

    override fun createInitialState(): AttendanceMainContract.State {
        return AttendanceMainContract.State()
    }

    override fun handleEvent(event: AttendanceMainContract.Event) {
        when (event) {
            is AttendanceMainContract.Event.OnClickAdminMenu -> {
                setEffect {
                    AttendanceMainContract.Effect.GoAdminPage
                }
            }

            is AttendanceMainContract.Event.OnClickDelete -> {
                setState {
                    this.copy(phoneNumber = phoneNumber.dropLast(1))
                }
            }

            is AttendanceMainContract.Event.OnClickNumber -> {
                if (uiState.value.phoneNumber.length < 8) {
                    setState {
                        this.copy(phoneNumber = phoneNumber + event.number)
                    }
                }
            }

            is AttendanceMainContract.Event.OnClickOK -> {
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
                        //등록된 기간 범위안에 있지 않은 회원이라면
                        if (!checkRegisterDate(user)) {
                            val msg =
                                resourceProvider.getString(R.string.text_noti_retry_phone_number)
                            setEffect { AttendanceMainContract.Effect.ShowToast(msg) }
                            return@collect
                        }

                        if (checkAlreadyAttendance(user)) { //오늘 이미 출석을 한 회원이라면
                            val maxAttendanceCount = uiState.value.adminData?.maxAttendance ?: 0
                            if (checkExceedAttendanceCount(maxAttendanceCount, user)) {
                                val msg =
                                    resourceProvider.getString(R.string.text_noti_already_attendance_user)
                                setEffect { AttendanceMainContract.Effect.ShowToast(msg) }
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
                        setEffect { AttendanceMainContract.Effect.ShowToast(msg) }
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
                            AttendanceMainContract.Effect.SuccessAttendance(user)
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