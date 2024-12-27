package com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.search

import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseViewModel
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.cmong_pilates_attendance_project.utils.ResourceProvider
import com.example.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchUserViewModel @Inject constructor(
    private val repository: UserRepository,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<SearchUserContract.Event, SearchUserContract.State, SearchUserContract.Effect>() {

    override fun createInitialState(): SearchUserContract.State {
        return SearchUserContract.State()
    }

    override fun handleEvent(event: SearchUserContract.Event) {
        when (event) {
            is SearchUserContract.Event.OnClickNextButton -> {
                if(event.phoneNumber.isBlank()){
                    val msg = resourceProvider.getString(R.string.text_noti_input_phone_number)
                    setEffect { SearchUserContract.Effect.ShowToast(msg) }
                }else {
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
            repository.getUser(phoneNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { LogUtil.d("doOnSubscribe") }
                .doOnSuccess { LogUtil.d("doOnSuccess") }
                .subscribe({ user ->
                    LogUtil.d("user search success! : $user")
                    setEffect { SearchUserContract.Effect.CompleteSearch(user) }
                },
                    {
                        val msg = resourceProvider.getString(R.string.text_not_exist_user)
                        setEffect { SearchUserContract.Effect.ShowToast(msg) }
                        LogUtil.d("throwable! : ${it.message}")
                    })
        )
    }

    fun clearData() {
//        _isExistUser= MutableLiveData<Boolean>()
//        _userPhoneNumber =""
    }
}