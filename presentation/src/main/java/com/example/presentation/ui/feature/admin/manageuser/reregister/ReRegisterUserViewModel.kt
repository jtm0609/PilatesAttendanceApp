package com.example.presentation.ui.feature.admin.manageuser.reregister

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.example.presentation.utils.LogUtil
import com.example.presentation.utils.ResourceProvider
import com.example.presentation.utils.toTimestamp
import com.example.domain.model.User
import com.example.domain.usecase.UpdateUserUseCase
import com.example.presentation.R
import com.example.presentation.base.BaseViewModel
import com.example.presentation.navigation.AppDestination
import com.example.presentation.utils.DateFormatter
import com.example.presentation.utils.DateFormatter.formatDate
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class ReRegisterUserViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val updateUserUseCase: UpdateUserUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<ReRegisterUserContract.Event, ReRegisterUserContract.State, ReRegisterUserContract.Effect>() {

    private val _user: User =
        savedStateHandle.toRoute<AppDestination.ReRegister>(typeMap = AppDestination.ReRegister.typeMap).user

    override fun createInitialState(): ReRegisterUserContract.State {
        return ReRegisterUserContract.State()
    }

    init {
        setState {
            this.copy(
                user = _user
            )
        }
    }

    override fun handleEvent(event: ReRegisterUserContract.Event) {
        when (event) {
            is ReRegisterUserContract.Event.ClickedSave -> {
                val user = event.user
                user.endDateTime = getEndDateTimeMilli(user.startDateTime, user.duration)
                reRegisterUser(user)
            }

            is ReRegisterUserContract.Event.OnClickSetBeginDate -> {
                setState {
                    this.copy(
                        isShowStartDateView = true
                    )
                }
            }

            is ReRegisterUserContract.Event.OnClickSetDuration -> {
                setState {
                    this.copy(
                        isShowDurationView = true
                    )
                }
            }

            is ReRegisterUserContract.Event.OnCompleteSetDuration -> {
                setState {
                    this.copy(
                        user = user?.copy(duration = event.duration),
                        isShowDurationView = false
                    )
                }
            }

            is ReRegisterUserContract.Event.OnChangedDuration -> {
                setState {
                    this.copy(
                        user = user?.copy(duration = event.duration)
                    )
                }
            }

            is ReRegisterUserContract.Event.OnCompleteStartDate -> {
                val startDateTime = formatDate(event.year, event.month + 1, event.day).toTimestamp()
                setState {
                    this.copy(
                        user = user?.copy(
                            startDateTime = startDateTime
                        ),
                        isShowStartDateView = false
                    )
                }
            }

            is ReRegisterUserContract.Event.OnDismissDurationView -> {
                setState {
                    this.copy(isShowDurationView = false)
                }
            }

            is ReRegisterUserContract.Event.OnDismissStartDate -> {
                setState {
                    this.copy(isShowStartDateView = false)
                }
            }
        }
    }

    private fun reRegisterUser(user: User) {
        compositeDisposable.add(
            updateUserUseCase(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        val msg = resourceProvider.getString(R.string.text_success_re_register_user)
                        setEffect { ReRegisterUserContract.Effect.ShowToast(msg) }
                        setEffect { ReRegisterUserContract.Effect.CompleteRegister(user) }
                        LogUtil.d("Update Successfully, ${user}")

                    }, { throwable ->
                        val msg = resourceProvider.getString(R.string.text_fail_re_register_user)
                        setEffect { ReRegisterUserContract.Effect.ShowToast(msg) }
                        LogUtil.d("Error Inserting: ${throwable.message}")
                    }
                )
        )
    }

    private fun getEndDateTimeMilli(startDate: Long, duration: String): Long {
        val instant = Instant.ofEpochMilli(startDate)
        var endDate: Instant? = null
        when (duration) {
            "2주" -> {
                endDate = instant.plus(15, ChronoUnit.DAYS)
            }

            "4주" -> {
                endDate = instant.plus(29, ChronoUnit.DAYS)
            }

            "8주" -> {
                endDate = instant.plus(57, ChronoUnit.DAYS)
            }

            "12주" -> {
                endDate = instant.plus(85, ChronoUnit.DAYS)
            }
        }

        return endDate?.toEpochMilli() ?: 0
    }
}