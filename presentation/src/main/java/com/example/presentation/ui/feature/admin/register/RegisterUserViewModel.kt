package com.example.presentation.ui.feature.admin.register

import com.example.presentation.utils.ResourceProvider
import com.example.presentation.utils.toTimestamp
import com.example.presentation.utils.isValidPhoneNumber
import com.example.domain.model.User
import com.example.domain.usecase.InsertUserUseCase
import com.example.presentation.R
import com.example.presentation.base.BaseViewModel
import com.example.presentation.utils.DateFormatter.formatDate
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.dataresource.DataResource
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class RegisterUserViewModel @Inject constructor(
    private val insertUserUseCase: InsertUserUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<RegisterUserContract.Event, RegisterUserContract.State, RegisterUserContract.Effect>() {

    override fun createInitialState(): RegisterUserContract.State {
        return RegisterUserContract.State()
    }

    override fun handleEvent(event: RegisterUserContract.Event) {
        when (event) {
            is RegisterUserContract.Event.OnClickSetBeginDate -> {
                setState {
                    this.copy(
                        isShowStartDateView = true
                    )
                }
            }

            is RegisterUserContract.Event.OnClickSetDuration -> {
                setState {
                    this.copy(
                        isShowDurationView = true
                    )
                }
            }

            is RegisterUserContract.Event.OnCompleteSetDuration -> {
                setState {
                    this.copy(
                        durationText = event.duration,
                        isShowDurationView = false
                    )
                }
            }

            is RegisterUserContract.Event.OnChangeDuration -> {
                setState {
                    this.copy(durationState = event.duration)
                }
            }

            is RegisterUserContract.Event.OnChangedName -> {
                if (event.name.length <= 11) {
                    setState {
                        this.copy(name = event.name)
                    }
                }
            }

            is RegisterUserContract.Event.OnChangePhoneNumber -> {
                with(event.phone) {
                    if (length <= 11 && !contains(",") && !contains("-")) {
                        setState {
                            this.copy(phone = event.phone)
                        }
                    }
                }
            }

            is RegisterUserContract.Event.ClickedSave -> {
                val msg: String
                if (isEmptyInputFields(
                        event.name,
                        event.phone,
                        event.durationText,
                        event.startDateText
                    )
                ) {
                    msg = resourceProvider.getString(R.string.msg_text_empty)
                    setEffect { RegisterUserContract.Effect.ShowToast(msg) }
                } else {
                    if (!event.phone.isValidPhoneNumber()) {
                        msg = resourceProvider.getString(R.string.msg_text_not_valid_phone_number)
                        setEffect { RegisterUserContract.Effect.ShowToast(msg) }
                    } else {
                        val user = createUser(
                            event.name,
                            event.phone,
                            event.durationText,
                            event.startDateText
                        )
                        addUser(user)
                    }
                }
            }

            is RegisterUserContract.Event.OnCompleteStartDate -> {
                val startDateString = formatDate(
                    event.year,
                    event.month + 1,
                    event.day
                )

                setState {
                    this.copy(
                        startYear = event.year,
                        startMonth = event.month,
                        startDay = event.day,
                        startDateText = startDateString,
                        isShowStartDateView = false
                    )
                }
            }

            is RegisterUserContract.Event.OnDismissDurationView -> {
                setState {
                    this.copy(isShowDurationView = false)
                }
            }

            is RegisterUserContract.Event.OnDismissStartDate -> {
                setState {
                    this.copy(isShowStartDateView = false)
                }
            }
        }
    }

    private fun addUser(user: User) {
        viewModelScope.launch {
            insertUserUseCase(user).collect {
                when (it) {
                    is DataResource.Loading -> {
                        setState { this.copy(isLoading = true) }
                    }

                    is DataResource.Error -> {
                        val msg = resourceProvider.getString(R.string.text_duplicate_add_user)
                        setEffect { RegisterUserContract.Effect.ShowToast(msg) }
                        setState { this.copy(isLoading = false) }
                    }

                    is DataResource.Success -> {
                        val msg = resourceProvider.getString(R.string.text_complete_add_user)
                        setEffect { RegisterUserContract.Effect.ShowToast(msg) }
                        setEffect { RegisterUserContract.Effect.GoBeforeFragment }
                        setState { this.copy(isLoading = false) }
                    }
                }
            }
        }
    }

    private fun isEmptyInputFields(
        name: String,
        phone: String,
        durationText: String,
        startDateText: String
    ): Boolean {
        return name.isBlank() || phone.isBlank() || durationText.isBlank() || startDateText.isBlank()
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

    private fun createUser(
        name: String,
        phone: String,
        durationText: String,
        startDateText: String
    ): User {
        val startDateTime = startDateText.toTimestamp()
        val userEndDateTime =
            getEndDateTimeMilli(startDateTime, durationText)

        return User(
            id = 0,
            name = name,
            phoneNumber = phone,
            duration = durationText,
            startDateTime = startDateTime,
            endDateTime = userEndDateTime,
            mileage = 0,
            attendanceCountOfToday = 0,
            attendanceDate = 0
        )
    }
}