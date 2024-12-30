package com.example.presentation.ui.feature.admin.manageuser

import androidx.lifecycle.ViewModel
import com.example.domain.model.User

class UserViewModel : ViewModel() {

    private lateinit var _searchedUser: User // 조회한 유저
    val searchedUser get() = _searchedUser

    fun setSearchedUser(user: User) {
        _searchedUser = user
    }

    fun updateSearchedUserMileage(mileage:Int){
        _searchedUser.mileage = mileage
    }

    fun updateSearchedUserUsingDate(pStartDateTime: Long, pEndDateTime: Long, pDuration: String){
        _searchedUser.apply {
            startDateTime = pStartDateTime
            endDateTime = pEndDateTime
            duration = pDuration
        }
    }
}