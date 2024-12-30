package com.example.presentation.ui.feature.admin.manageuser.search

import com.example.presentation.utils.LogUtil
import com.example.presentation.utils.ResourceProvider
import com.example.domain.usecase.GetUserUseCase
import com.example.presentation.R
import com.example.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchUserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<SearchUserContract.Event, SearchUserContract.State, SearchUserContract.Effect>() {

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
        compositeDisposable.add(
            getUserUseCase(phoneNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { LogUtil.d("doOnSuccess") }
                .subscribe({ user ->
                    LogUtil.d("user search success! : $user")
                    setEffect { SearchUserContract.Effect.CompleteSearch(user) }
                },
                    { throwable ->
                        val msg = resourceProvider.getString(R.string.text_not_exist_user)
                        setEffect { SearchUserContract.Effect.ShowToast(msg) }
                        LogUtil.d("throwable! : ${throwable.message}")
                    })
        )
    }
}