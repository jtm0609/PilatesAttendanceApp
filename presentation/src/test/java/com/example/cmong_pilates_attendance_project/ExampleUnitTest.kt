package com.example.cmong_pilates_attendance_project

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.data.UserEntity
import com.example.data.db.UserDao
import com.example.data.db.UserDatabase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.Calendar

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class ExampleUnitTest {
    private lateinit var userDao: UserDao


    /*@Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()*/

    /* @ClassRule
        val schedulers: RxSchedulerRule = RxSchedulerRule()*/


    @Before
    fun setUp() {
        val database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDatabase::class.java
        ).allowMainThreadQueries().build()

        userDao = database.userDao()
    }


    @Test
    fun `DB INSERT 테스트`() {
        val data = UserEntity(
            name = "홍길동",
            phoneNumber = "01012345678",
            duration = "2주",
            startDateTime = Calendar.getInstance().timeInMillis,
            endDateTime = Calendar.getInstance().timeInMillis + 1000,
        )
        userDao.insertUser(data).test().assertComplete()
    }


    @Test
    fun `DB SELECT 테스트`() {
        println("ASdasdad");
        val test = userDao
            .selectUsers()
            .subscribe({ it ->
                assertEquals(1,it.size)
                println("ASdasdad: $it");
                println("ASdasdad: ${it.size}");
            }, {
                println("ASdasdad2: $it");
            })
    }


    @Test
    fun `DB UPDATE 테스트`() {

    }
}
