package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val repository: UserRepository){
    suspend operator fun invoke(user: User) =
        repository.updateUser(user)
}