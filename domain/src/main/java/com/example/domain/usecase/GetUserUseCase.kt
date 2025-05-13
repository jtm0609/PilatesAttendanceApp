package com.example.domain.usecase

import com.example.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(phoneNumber: String) =
        userRepository.getUser(phoneNumber)
}