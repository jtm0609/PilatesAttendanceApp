package com.example.domain.usecase

import com.example.domain.repository.UserRepository
import javax.inject.Inject

class UpdateMileageUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(phoneNumber: String, mileage: Int) =
        repository.updateUserMileage(phoneNumber, mileage)
}