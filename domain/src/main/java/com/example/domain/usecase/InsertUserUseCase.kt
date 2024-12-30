package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import io.reactivex.Completable
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(user: User): Completable =
        repository.insertUser(user)
}