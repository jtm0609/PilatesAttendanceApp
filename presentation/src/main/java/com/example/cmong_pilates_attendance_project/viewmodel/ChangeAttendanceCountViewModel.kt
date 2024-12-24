package com.example.cmong_pilates_attendance_project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.example.cmong_pilates_attendance_project.base.BaseViewModel
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.data.model.AdminEntity
import com.example.data.repository.AdminRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ChangeAttendanceCountViewModel
@Inject constructor
    (private val repository: AdminRepository) : BaseViewModel() {
    private var _attendanceCount by mutableStateOf(1)
    val attendanceCount get() = _attendanceCount

    private var _isChangeCount by mutableStateOf(false)
    val isChangeCount get() = _isChangeCount

    private var _isAddAdminData by mutableStateOf(false)
    val isAddAdminData get() = _isAddAdminData

    private var _adminData: AdminEntity? by mutableStateOf(null)
    val adminData get() = _adminData

    fun setAttendanceCount(count: Int) {
        _attendanceCount = count
    }

    fun getAdminData() {
        compositeDisposable.add(
            repository.getAdminData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _adminData = it
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
            repository.addAdminData(adminEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        LogUtil.d("Success Add Admin")
                        _isAddAdminData = true
                    }, {
                        LogUtil.d("Error Add Admin, ${it.message}")
                        _isAddAdminData = false
                    }
                )
        )
    }

    fun changeAttendanceCount(count: Int) {
        compositeDisposable.add(
            repository.updateAttendanceCount(count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    LogUtil.d("Success Change Attendance Count!")
                    _isChangeCount = true
                }, {
                    LogUtil.d("Error Change Attendance Count, ${it.message}")
                    _isChangeCount = false
                })
        )
    }


}