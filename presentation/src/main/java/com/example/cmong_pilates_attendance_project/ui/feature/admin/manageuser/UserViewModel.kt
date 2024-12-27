package com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.data.model.UserEntity
import com.example.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class UserViewModel : ViewModel() {

    private var _searchedUser: UserEntity? by mutableStateOf(null)// 조회한 유저
    val searchedUser get() = _searchedUser

    fun setSearchedUser(user: UserEntity) {
        _searchedUser = user
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
}