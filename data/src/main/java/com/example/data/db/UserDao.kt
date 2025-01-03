package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.UserEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {
    //회원 추가 (신규)
    @Insert
    fun insertUser(user: UserEntity): Completable

    //회원 수정 (출석체크, 마일리지 변경)
    @Query("UPDATE user SET mileage=:mileage WHERE phoneNumber=:phoneNumber")
    fun updateMileage(phoneNumber: String, mileage: Int): Completable

    //회원 수정 (기간 재등록, 회원 출석 정보(출석날짜, 출석횟수) 변경)
    @Update
    fun updateUser(user: UserEntity): Completable

    //회원 번호를 이용한 회원 조회
    @Query("SELECT * FROM user WHERE phoneNumber=:phoneNumber")
    fun getUserFromPhoneNumber(phoneNumber: String): Single<UserEntity>

    //전체 회원 조회
    @Query("SELECT * FROM user")
    fun getUsers(): Single<List<UserEntity>>
}