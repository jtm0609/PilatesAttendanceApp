package com.example.main

import androidx.lifecycle.ViewModel
import com.example.domain.model.User

class MainViewModel : ViewModel() {

    private lateinit var _searchedUser: User
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