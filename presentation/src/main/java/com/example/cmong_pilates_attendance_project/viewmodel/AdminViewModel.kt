package com.example.cmong_pilates_attendance_project.viewmodel

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
class AdminViewModel
@Inject constructor(
    private val repository: AdminRepository
): BaseViewModel() {

    private var _adminData = MutableLiveData<AdminEntity>()
    val adminData get() = _adminData

    private var _isEmptyAdminData = MutableLiveData<Boolean>()
    val isEmptyAdminData get() = _isEmptyAdminData

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
                        addAdminData(AdminEntity(
                            maxAttendance = 1
                        ))
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
                    }, {
                        LogUtil.d("Error Add Admin, ${it.message}")
                    }
                )
        )
    }
}