package com.example.domain.usecase

import com.example.domain.model.Admin
import com.example.domain.model.User
import com.example.domain.repository.AdminRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UpdateAttendanceCountUseCase @Inject constructor(private val repository: AdminRepository) {
    operator fun invoke(count: Int): Completable =
        repository.updateAttendanceCount(count)
}