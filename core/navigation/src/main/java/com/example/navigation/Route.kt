package com.example.navigation

import com.example.domain.model.User
import kotlin.reflect.typeOf
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data class ReRegister(val user: User) : Route {
        companion object {
            val typeMap = mapOf(
                typeOf<User>() to serializableType<User>()
            )
        }
    }

    @Serializable
    data class ChangeUserMileage(val user: User) : Route {
        companion object {
            val typeMap = mapOf(
                typeOf<User>() to serializableType<User>()
            )
        }
    }

    @Serializable
    data class AttendanceComplete(val name: String, val mileage: Int) : Route

    @Serializable
    data object AttendanceMain : Route
    @Serializable
    data object AdminMenu : Route
    @Serializable
    data object ChangeAttendanceCount : Route
    @Serializable
    data object SearchUser : Route
    @Serializable
    data object ManageUser : Route
    @Serializable
    data object RegisterUser : Route
}