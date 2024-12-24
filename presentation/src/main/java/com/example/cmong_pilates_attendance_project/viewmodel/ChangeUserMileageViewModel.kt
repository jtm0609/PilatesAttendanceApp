package com.example.cmong_pilates_attendance_project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.cmong_pilates_attendance_project.base.BaseViewModel
import com.example.cmong_pilates_attendance_project.state.PilatesState
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ChangeUserMileageViewModel@Inject constructor(
    private val repository: UserRepository
) : BaseViewModel() {
    private var _name : String = ""
    val name get() = _name
    private var _mileage by mutableStateOf(0)
    val mileage get() = _mileage

    private var _state : PilatesState? by mutableStateOf(null)
    val state get() =_state
    fun setMileage(mileage: Int){
        _mileage = mileage
    }

    fun setName(name: String){
        _name = name
    }
    fun changeUserMileage(phone: String, mileage:Int){
        compositeDisposable.add(
            repository.updateUserMileage(phone, mileage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        LogUtil.d("Success change mileage!")
                        _state = PilatesState.Success
                    },{
                        LogUtil.d("error ${it.message}}")
                        _state = PilatesState.Fail
                    }
                )
        )
    }
}