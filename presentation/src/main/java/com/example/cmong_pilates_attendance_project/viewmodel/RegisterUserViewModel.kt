package com.example.cmong_pilates_attendance_project.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.cmong_pilates_attendance_project.base.BaseViewModel
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.data.data.UserEntity
import com.example.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RegisterUserViewModel@Inject constructor(
    private val repository: UserRepository
) : BaseViewModel() {

    private var _name by mutableStateOf("")
    val name get() =_name

    private var _phone by mutableStateOf("")
    val phone get() =_phone



    //이용 기간 설정 뷰 Visible 상태
    private var _durationVisibility by mutableStateOf(false)
    val durationVisibility get() = _durationVisibility

    private var _durationText by mutableStateOf("")
    val durationText get() = _durationText

    private var _startDateText by mutableStateOf("")
    val startDateText get() = _startDateText

    fun setVisibilityDuration(visible: Boolean){
        _durationVisibility = visible
    }

    fun setDurationText(duration: String){
        _durationText = duration
    }

    fun setStartDateTextText(date: String){
        _startDateText = date
    }

    fun setNameText(name: String){
        _name = name
    }

    fun setPhoneText(phone: String){
        _phone = phone
    }

    fun addUser(user: UserEntity) {
        compositeDisposable.add(
            repository.insertUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        LogUtil.d("Inserted Successfully, ${user}")

                    },{
                        error->
                        LogUtil.d("Error Inserting: ${error.message}")
                    }
                )
        )
    }


}