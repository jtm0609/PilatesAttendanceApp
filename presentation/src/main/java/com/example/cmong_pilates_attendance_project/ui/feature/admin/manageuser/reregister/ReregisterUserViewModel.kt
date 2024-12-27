package com.example.cmong_pilates_attendance_project.ui.feature.admin.manageuser.reregister

import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.base.BaseViewModel
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.cmong_pilates_attendance_project.utils.ResourceProvider
import com.example.cmong_pilates_attendance_project.utils.toTimestamp
import com.example.data.model.UserEntity
import com.example.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class ReregisterUserViewModel
@Inject constructor(
    private val repository: UserRepository,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<ReregisterUserContract.Event, ReregisterUserContract.State, ReregisterUserContract.Effect>() {

    override fun createInitialState(): ReregisterUserContract.State {
        return ReregisterUserContract.State()
    }

    override fun handleEvent(event: ReregisterUserContract.Event) {
        when (event) {
            is ReregisterUserContract.Event.ClickedSave -> {
                val user = createUser(event.no,event.name, event.phone, event.durationText, event.startDateText)
                reRegisterUser(user)
            }
            is ReregisterUserContract.Event.OnClickSetBeginDate -> {
                setState {
                    this.copy(
                        isShowStartDateView = true
                    )
                }
            }

            is ReregisterUserContract.Event.OnClickSetDuration -> {
                setState {
                    this.copy(
                        isShowDurationView = true
                    )
                }
            }

            is ReregisterUserContract.Event.OnCompleteSetDuration -> {
                setState {
                    this.copy(
                        durationText = event.duration,
                        isShowDurationView = false
                    )
                }
            }

            is ReregisterUserContract.Event.OnChangedDuration -> {
                setState {
                    this.copy(durationState = event.duration)
                }
            }

            is ReregisterUserContract.Event.OnCompleteStartDate -> {
                val message =
                    resourceProvider.getString(
                        R.string.text_start_date,
                        event.year,
                        event.month + 1,
                        event.day
                    )

                setState {
                    this.copy(
                        startYear = event.year,
                        startMonth = event.month,
                        startDay = event.day,
                        startDateText = message,
                        isShowStartDateView = false
                    )
                }
            }

            is ReregisterUserContract.Event.OnDismissDurationView -> {
                setState {
                    this.copy(isShowDurationView = false)
                }
            }
            is ReregisterUserContract.Event.OnDismissStartDate -> {
                setState {
                    this.copy(isShowStartDateView=false)
                }
            }
        }
    }

    private fun reRegisterUser(user: UserEntity) {
        compositeDisposable.add(
            repository.updateUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        val msg = resourceProvider.getString(R.string.text_success_re_register_user)
                        setEffect { ReregisterUserContract.Effect.ShowToast(msg) }
                        setEffect { ReregisterUserContract.Effect.CompleteRegister(user) }
                        LogUtil.d("Update Successfully, ${user}")

                    }, {
                        val msg = resourceProvider.getString(R.string.text_fail_re_register_user)
                        setEffect { ReregisterUserContract.Effect.ShowToast(msg) }
                        LogUtil.d("Error Inserting: ${it.message}")
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

        return endDate?.toEpochMilli()!!
    }

    private fun createUser(
        no: Long,
        name: String,
        phone: String,
        durationText: String,
        startDateText: String
    ): UserEntity {

        val startDateTime = startDateText.toTimestamp()
        val userEndDateTime =
            getEndDateTimeMilli(startDateTime, durationText)

        return UserEntity(
            no = no,
            name = name,
            phoneNumber = phone,
            duration = durationText,
            startDateTime = startDateTime,
            endDateTime = userEndDateTime
        )
    }
}