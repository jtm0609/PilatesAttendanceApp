package com.example.domain.usecase

import com.example.domain.repository.AdminRepository
import javax.inject.Inject

class UpdateAttendanceCountUseCase @Inject constructor(private val repository: AdminRepository) {

    suspend operator fun invoke(count: Int) =
        repository.updateAttendanceCount(count)
}