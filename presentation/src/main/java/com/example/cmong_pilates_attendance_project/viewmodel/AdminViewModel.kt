package com.example.cmong_pilates_attendance_project.viewmodel

import com.example.cmong_pilates_attendance_project.base.BaseViewModel
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.data.data.UserEntity
import com.example.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AdminViewModel @Inject constructor(
    private val repository: UserRepository
) : BaseViewModel() {


/*    fun addUser(user: UserEntity) {
        compositeDisposable.add(
            repository.insertUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        LogUtil.d("Inserted Successfully, ${user}")
                    }
                )
        )
    }*/
}