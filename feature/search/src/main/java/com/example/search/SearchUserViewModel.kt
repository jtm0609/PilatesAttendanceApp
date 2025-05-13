package com.example.search

import com.example.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.common.ResourceProvider
import com.example.domain.dataresource.DataResource
import com.example.domain.model.User
import com.example.feature.search.R
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchUserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<SearchUserContract.Event, SearchUserContract.State, SearchUserContract.Effect>() {

    private lateinit var _searchedUser: User
    val searchedUser get() = _searchedUser

    override fun createInitialState(): SearchUserContract.State {
        return SearchUserContract.State()
    }

    override fun handleEvent(event: SearchUserContract.Event) {
        when (event) {
            is SearchUserContract.Event.OnClickNextButton -> {
                if (event.phoneNumber.isBlank()) {
                    val msg = resourceProvider.getString(R.string.text_noti_input_phone_number)
                    setEffect { SearchUserContract.Effect.ShowToast(msg) }
                } else {
                    getUser(event.phoneNumber)
                }
            }

            is SearchUserContract.Event.OnChangePhoneNumber -> {
                if (event.phoneNumber.length <= 11) {
                    setState {
                        this.copy(phone = event.phoneNumber)
                    }
                }
            }
        }
    }

    private fun getUser(phoneNumber: String) {
        viewModelScope.launch {
            getUserUseCase(phoneNumber).collect {
                when (it) {
                    is DataResource.Loading -> {
                        setState { this.copy(isLoading = true) }
                    }

                    is DataResource.Success -> {
                        setSearchedUser(it.data)
                        setEffect { SearchUserContract.Effect.CompleteSearch(it.data) }
                        setState { this.copy(isLoading = false) }
                    }

                    is DataResource.Error -> {
                        val msg = resourceProvider.getString(R.string.text_not_exist_user)
                        setEffect { SearchUserContract.Effect.ShowToast(msg) }
                        setState { this.copy(isLoading = false) }
                    }
                }
            }
        }
    }

    private fun setSearchedUser(user: User) {
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