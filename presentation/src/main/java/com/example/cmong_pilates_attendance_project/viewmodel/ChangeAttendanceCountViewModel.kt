package com.example.cmong_pilates_attendance_project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.example.cmong_pilates_attendance_project.base.BaseViewModel
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.data.data.AdminEntity
import com.example.data.repository.AdminRepository
import com.example.data.repository.UserRepository
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

    private var _isChangeCount = MutableLiveData<Boolean>()
    val isChangeCount get() = _isChangeCount

    private var _isAddAdminData = MutableLiveData<Boolean>()
    val isAddAdminData get() = _isAddAdminData

    private var _adminData = MutableLiveData<AdminEntity>()
    val adminData get() = _adminData

    private var _isEmptyAdminData = MutableLiveData<Boolean>()
    val isEmptyAdminData get() = _isEmptyAdminData
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
                        _adminData.value = it
                        LogUtil.d("Success Search Admin, $it")
                    }, {
                        _isEmptyAdminData.value = true
                        LogUtil.d("Error Search Admin, ${it.message}")
                    }
                )
        )
    }

    fun addAdminData(adminEntity: AdminEntity) {
        compositeDisposable.add(
            repository.addAdminData(adminEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        LogUtil.d("Success Add Admin")
                        _isAddAdminData.value = true
                    }, {
                        LogUtil.d("Error Add Admin, ${it.message}")
                        _isAddAdminData.value = false
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
                    _isChangeCount.value = true
                }, {
                    LogUtil.d("Error Change Attendance Count, ${it.message}")
                    _isChangeCount.value = false
                })
        )
    }


}