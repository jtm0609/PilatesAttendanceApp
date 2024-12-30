package com.example.presentation.navigation

import com.example.domain.model.User
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

sealed interface AppDestination {
    @Serializable
    data class ReRegister(val user: User) : AppDestination {
        companion object {
            val typeMap = mapOf(
                typeOf<User>() to serializableType<User>()
            )
        }
    }

    @Serializable
    data class ChangeUserMileage(val user: User) : AppDestination {
        companion object {
            val typeMap = mapOf(
                typeOf<User>() to serializableType<User>()
            )
        }
    }

    @Serializable
    data class AttendanceComplete(val name: String, val mileage: Int) : AppDestination

    @Serializable
    data object AttendanceMain : AppDestination
    @Serializable
    data object AdminMenu : AppDestination
    @Serializable
    data object ChangeAttendanceCount : AppDestination
    @Serializable
    data object SearchUser : AppDestination
    @Serializable
    data object ManageUser : AppDestination
    @Serializable
    data object RegisterUser : AppDestination
}