package com.example.domain.usecase

import com.example.domain.model.Admin
import com.example.domain.repository.AdminRepository
import javax.inject.Inject

class GetAdminUseCase @Inject constructor(private val repository: AdminRepository) {
    suspend operator fun invoke() = repository.getAdminData()
}