package com.example.cmong_pilates_attendance_project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cmong_pilates_attendance_project.base.BaseViewModel
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.cmong_pilates_attendance_project.utils.Utils
import com.example.data.data.UserEntity
import com.example.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel
@Inject constructor(
    private val repository: UserRepository
) : BaseViewModel() {
    private var _phoneNumber by mutableStateOf("")
    val phoneNumber get() = _phoneNumber

    fun setPhoneNumber(number: String) {
        _phoneNumber = number
    }

    private var _searchedUser: MutableLiveData<UserEntity> = MutableLiveData<UserEntity>() // 조회한 유저
    val searchedUser: LiveData<UserEntity> get() = _searchedUser

    private var _isNoExistUser: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isNoExistUser: LiveData<Boolean> get() = _isNoExistUser

    private var _isSuccessAttendance = MutableLiveData<Boolean>()
    val isSuccessAttendance: LiveData<Boolean> get() = _isSuccessAttendance

    private var _isAlreadyAttendance = MutableLiveData<Boolean>()
    val isAlreadyAttendance: LiveData<Boolean> get() = _isAlreadyAttendance

    private var _maxAttendanceCount = 0
    val maxAttendanceCount get() = _maxAttendanceCount

    fun setMaxAttendanceCount(count: Int) {
        _maxAttendanceCount = count
    }

    private fun updateUser(user: UserEntity) {
        compositeDisposable.add(
            repository.updateUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _isSuccessAttendance.value = true
                        LogUtil.d("Update Successfully, ${user}")

                    }, {
                        LogUtil.d("Error Inserting: ${it.message}")
                        _isSuccessAttendance.value = false
                    }
                )
        )
    }

    fun checkUser(phoneNumber: String) {
        compositeDisposable.add(
            repository.getUser(phoneNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    //등록된 기간 범위안에 있지 않은 회원이라면
                    if (!checkRegisterDate(it)) {
                        LogUtil.d("user not register date")
                        _isNoExistUser.value = true
                        return@subscribe
                    }

                    if (checkAlreadyAttendance(it)) { //오늘 이미 출석을 한 회원이라면
                        LogUtil.d("user already Attendance!")
                        if (checkExceedAttendanceCount(maxAttendanceCount, it)) {
                            LogUtil.d("user exceed AttendanceCount!")
                            _isAlreadyAttendance.value = true
                        } else {
                            LogUtil.d("user search success! : $it")
                            it.attendanceCountOfToday += 1
                            it.mileage += 1
                            updateUser(it)
                            _searchedUser.value = it

                        }
                    } else { //오늘 출석을 하지 않은 회원
                        LogUtil.d("user search success! : $it")
                        it.attendanceDate = Calendar.getInstance().timeInMillis
                        it.attendanceCountOfToday = 1
                        it.mileage += 1
                        updateUser(it)
                        _searchedUser.value = it
                    }
                },
                    {
                        _isNoExistUser?.value = true
                        LogUtil.d("throwable! : ${it.message}")

                    })

        )
    }

    //등록된 기간 범위 안에 있는 회원인지 체크
    private fun checkRegisterDate(user: UserEntity): Boolean {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.timeInMillis
        LogUtil.d("startDateTime: ${user.startDateTime}")
        LogUtil.d("currentTime: $currentTime")
        LogUtil.d("endDateTime: ${user.endDateTime}")
        //등록된 기간 범위안에 있는 회원이라면
        return user.startDateTime <= currentTime && currentTime <= user.endDateTime
    }

    //이미 오늘 출석을 한 회원인지 체크
    private fun checkAlreadyAttendance(user: UserEntity): Boolean {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.timeInMillis

        val currentDate = Utils.convertTimeStampToDateString(currentTime)
        val attendanceDate = Utils.convertTimeStampToDateString(user.attendanceDate)
        LogUtil.d("currentDate: $currentDate")
        LogUtil.d("attendanceDate: $attendanceDate")
        return currentDate == attendanceDate
    }

    //하루 출석횟수 초과한 회원인지 체크
    private fun checkExceedAttendanceCount(maxCount: Int, user: UserEntity): Boolean {
        LogUtil.d("maxCount: ${maxCount}")
        return user.attendanceCountOfToday >= maxCount
    }

    fun onClearData() {
        LogUtil.d("onClearData")
        _isNoExistUser.value = false
        _isSuccessAttendance.value =false
        _isAlreadyAttendance.value =false
        _phoneNumber = ""
    }
}