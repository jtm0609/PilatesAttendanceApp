package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(phoneNumber: String) : Single<User>{
        return userRepository.getUser(phoneNumber)
    }
}