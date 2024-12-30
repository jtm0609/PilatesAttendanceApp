package com.example.domain.usecase

import com.example.domain.repository.UserRepository
import io.reactivex.Completable
import javax.inject.Inject

class UpdateMileageUseCase @Inject constructor(private val repository: UserRepository) {

    operator fun invoke(phoneNumber: String, mileage: Int): Completable =
        repository.updateUserMileage(phoneNumber, mileage)
}