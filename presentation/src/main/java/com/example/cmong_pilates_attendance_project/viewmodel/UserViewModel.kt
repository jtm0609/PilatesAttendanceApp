package com.example.cmong_pilates_attendance_project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.cmong_pilates_attendance_project.base.BaseViewModel
import com.example.cmong_pilates_attendance_project.state.PilatesState
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.data.model.UserEntity
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
    private var _searchedUser: UserEntity? by mutableStateOf(null)// 조회한 유저
    val searchedUser get() = _searchedUser

    private var _state: PilatesState? by mutableStateOf(null)
    val state get() = _state


    fun setUserPhoneNumber(phoneNumber: String) {
        _userPhoneNumber = phoneNumber
    }

    fun updateSearchedUserMileage(mileage:Int){
        _searchedUser?.mileage = mileage
    }

    fun updateSearchedUserUsingDate(pStartDateTime: Long, pEndDateTime: Long, pDuration: String){
        _searchedUser?.apply {
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
                    _state = PilatesState.Success
                    _searchedUser = it

                },
                    {
                        _state = PilatesState.Fail
                        LogUtil.d("throwable! : ${it.message}")

                    })

        )
    }

    fun clearData(){
//        _isExistUser= MutableLiveData<Boolean>()
//        _userPhoneNumber =""
    }


}