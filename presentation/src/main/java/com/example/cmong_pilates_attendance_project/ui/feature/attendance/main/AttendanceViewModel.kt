package com.example.cmong_pilates_attendance_project.ui.feature.attendance.main

import android.util.Log
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseViewModel
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.cmong_pilates_attendance_project.utils.ResourceProvider
import com.example.cmong_pilates_attendance_project.utils.toDateString
import com.example.data.model.AdminEntity
import com.example.data.model.UserEntity
import com.example.data.repository.AdminRepository
import com.example.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel
@Inject constructor(
    private val userRepository: UserRepository,
    private val adminRepository: AdminRepository,
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

    fun checkUser(phoneNumber: String) {
        compositeDisposable.add(
            userRepository.getUser(phoneNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    //등록된 기간 범위안에 있지 않은 회원이라면
                    if (!checkRegisterDate(it)) {
                        LogUtil.d("user not register date")
                        val msg = resourceProvider.getString(R.string.text_noti_retry_phone_number)
                        setEffect { AttendanceMainContract.Effect.ShowToast(msg) }
                        return@subscribe
                    }

                    if (checkAlreadyAttendance(it)) { //오늘 이미 출석을 한 회원이라면
                        val maxAttendanceCount = uiState.value.adminData?.maxAttendance ?: 0
                        Log.d("taek", "maxAttendanceCount: ${maxAttendanceCount}")
                        if (checkExceedAttendanceCount(maxAttendanceCount, it)) {
                            LogUtil.d("user exceed AttendanceCount!")
                            val msg =
                                resourceProvider.getString(R.string.text_noti_already_attendance_user)
                            setEffect { AttendanceMainContract.Effect.ShowToast(msg) }
                        } else {
                            LogUtil.d("user search success! : $it")
                            it.attendanceCountOfToday += 1
                            it.mileage += 1
                            updateUser(it)
                        }
                    } else { //오늘 출석을 하지 않은 회원
                        LogUtil.d("user search success! : $it")
                        it.attendanceDate = Calendar.getInstance().timeInMillis
                        it.attendanceCountOfToday = 1
                        it.mileage += 1
                        updateUser(it)
                    }
                },
                    {
                        LogUtil.d("throwable! : ${it.message}")
                        val msg = resourceProvider.getString(R.string.text_noti_retry_phone_number)
                        setEffect { AttendanceMainContract.Effect.ShowToast(msg) }
                    })
        )
    }

    private fun getAdminData() {
        compositeDisposable.add(
            adminRepository.getAdminData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        setState {
                            this.copy(adminData = it)
                        }
                        LogUtil.d("Success Search Admin, $it")
                    }, {
                        addAdminData(
                            AdminEntity(
                                maxAttendance = 1
                            )
                        )
                        LogUtil.d("Error Search Admin, ${it.message}")
                    }
                )
        )
    }

    private fun addAdminData(adminEntity: AdminEntity) {
        compositeDisposable.add(
            adminRepository.addAdminData(adminEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        LogUtil.d("Success Add Admin")
                    }, {
                        LogUtil.d("Error Add Admin, ${it.message}")
                    }
                )
        )
    }

    private fun updateUser(user: UserEntity) {
        compositeDisposable.add(
            userRepository.updateUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        setEffect {
                            AttendanceMainContract.Effect.SuccessAttendance(user)
                        }
                        setState {
                            this.copy(
                                searchedUser = user
                            )
                        }
                        LogUtil.d("Update Successfully, ${user}")

                    }, {
                        LogUtil.d("Error Inserting: ${it.message}")
                    }
                )
        )
    }

    //등록된 기간 범위 안에 있는 회원인지 체크
    private fun checkRegisterDate(user: UserEntity): Boolean {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.timeInMillis
        //등록된 기간 범위안에 있는 회원이라면
        return user.startDateTime <= currentTime && currentTime <= user.endDateTime
    }

    //이미 오늘 출석을 한 회원인지 체크
    private fun checkAlreadyAttendance(user: UserEntity): Boolean {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.timeInMillis
        val currentDate = currentTime.toDateString()
        val attendanceDate = user.attendanceDate.toDateString()
        return currentDate == attendanceDate
    }

    //하루 출석횟수 초과한 회원인지 체크
    private fun checkExceedAttendanceCount(maxCount: Int, user: UserEntity): Boolean {
        return user.attendanceCountOfToday >= maxCount
    }
}