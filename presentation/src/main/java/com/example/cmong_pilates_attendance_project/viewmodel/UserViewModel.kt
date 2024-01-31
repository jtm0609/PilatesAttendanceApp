package com.example.cmong_pilates_attendance_project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cmong_pilates_attendance_project.base.BaseViewModel
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.data.data.UserEntity
import com.example.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : BaseViewModel() {
    private var _userPhoneNumber by mutableStateOf("") //검색할 회원의 번호
    val userPhoneNumber get() = _userPhoneNumber
    private var _searchedUser: MutableLiveData<UserEntity> = MutableLiveData<UserEntity>() // 조회한 유저
    val searchedUser: LiveData<UserEntity> get() = _searchedUser

    private var _isExistUser:MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isExistUser: LiveData<Boolean> get() = _isExistUser


    fun setUserPhoneNumber(phoneNumber: String) {
        _userPhoneNumber = phoneNumber
    }

    fun updateSearchedUserMileage(mileage:Int){
        _searchedUser.value?.mileage = mileage
    }

    fun updateSearchedUserUsingDate(pStartDateTime: Long, pEndDateTime: Long, pDuration: String){
        _searchedUser.value?.apply {
            startDateTime = pStartDateTime
            endDateTime = pEndDateTime
            duration = pDuration
        }
    }

    fun getUser(phoneNumber: String) {
        compositeDisposable.add(
            repository.getUser(phoneNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doOnSuccess { hideProgress() }
                .subscribe({ it ->
                    LogUtil.d("user search success! : $it")
                    _isExistUser?.value = true
                    _searchedUser?.value = it

                },
                    {
                        _isExistUser?.value = false
                        LogUtil.d("throwable! : ${it.message}")

                    })

        )
    }

    fun clearData(){
        _isExistUser= MutableLiveData<Boolean>()
        _userPhoneNumber =""
    }


}