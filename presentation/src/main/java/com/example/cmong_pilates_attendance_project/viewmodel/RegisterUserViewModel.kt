package com.example.cmong_pilates_attendance_project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.cmong_pilates_attendance_project.base.BaseViewModel
import com.example.cmong_pilates_attendance_project.state.PilatesState
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.cmong_pilates_attendance_project.utils.dateStringToTimestamp
import com.example.data.model.UserEntity
import com.example.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.GregorianCalendar
import javax.inject.Inject

@HiltViewModel
class RegisterUserViewModel @Inject constructor(
    private val repository: UserRepository
) : BaseViewModel() {

    private var _name by mutableStateOf("")
    val name get() = _name

    private var _phone by mutableStateOf("")
    val phone get() = _phone

    val durationValues = listOf("2주", "4주", "8주", "12주")
    private var _durationState by mutableStateOf(durationValues[0])
    val durationState get() = _durationState

    //이용 기간 설정 뷰 Visible 상태
    private var _durationVisibility by mutableStateOf(false)
    val durationVisibility get() = _durationVisibility

    private var _durationText by mutableStateOf("")
    val durationText get() = _durationText

    private var _startDateText by mutableStateOf("")
    val startDateText get() = _startDateText

    val today = GregorianCalendar()
    private var _startYear: Int = today.get(Calendar.YEAR)
    val startYear get() = _startYear
    private var _startMonth: Int = today.get(Calendar.MONTH)
    val startMonth get() = _startMonth
    private var _startDay: Int = today.get(Calendar.DATE)
    val startDay get() = _startDay

    private var _state: PilatesState? by mutableStateOf(null)
    val state get() = _state

    private var _isClickStartDate by mutableStateOf(false)
    val isClickStartDate get() = _isClickStartDate
    fun setVisibilityDuration(visible: Boolean) {
        _durationVisibility = visible
    }

    fun setDurationText(duration: String) {
        _durationText = duration
    }

    fun setDurationState(duration: String) {
        _durationState = duration
    }

    fun setStartDateText(date: String) {
        _startDateText = date
    }

    fun setNameText(name: String) {
        _name = name
    }

    fun setPhoneText(phone: String) {
        _phone = phone
    }

    fun setStartDate(year: Int, month: Int, date: Int) {
        _startYear = year
        _startMonth = month
        _startDay = date
    }

    fun addUser(user: UserEntity) {
        compositeDisposable.add(repository.insertUser(user).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe { LogUtil.d("doOnSubscribe") }
            .doOnComplete { LogUtil.d("doOnComplete") }.doOnError { LogUtil.d("doOnError") }
            .subscribe({
                _state = PilatesState.Success
                LogUtil.d("Inserted Successfully, ${user}")

            }, {
                LogUtil.d("Error Inserting: ${it.message}")
                _state = PilatesState.Fail
            })
        )
    }

    fun clickStartDate() {
        _isClickStartDate = true
    }

    fun isEmptyInputFields(): Boolean {
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

        return endDate?.toEpochMilli()!!
    }

    fun createUser(): UserEntity {
        val startDateTime = startDateText.dateStringToTimestamp()
        val userEndDateTime =
            getEndDateTimeMilli(startDateTime, durationText)

        return UserEntity(
            name = name,
            phoneNumber = phone,
            duration = durationText,
            startDateTime = startDateTime,
            endDateTime = userEndDateTime
        )
    }

}