package com.example.domain.usecase

import com.example.domain.model.Admin
import com.example.domain.repository.AdminRepository
import io.reactivex.Single
import javax.inject.Inject

class GetAdminUseCase @Inject constructor(private val repository: AdminRepository) {
    operator fun invoke(): Single<Admin> =
        repository.getAdminData()
}