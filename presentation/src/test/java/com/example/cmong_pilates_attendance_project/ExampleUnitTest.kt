package com.example.cmong_pilates_attendance_project

import android.content.Context
import android.support.annotation.NonNull
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.data.data.AdminEntity
import com.example.data.data.UserEntity
import com.example.data.db.UserDao
import com.example.data.db.UserDatabase
import io.mockk.every
import io.mockk.mockkStatic
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.Calendar
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {
    private lateinit var userDao: UserDao
    @Mock
    private lateinit var context: Context

    /*@Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()*/

    /* @ClassRule
        val schedulers: RxSchedulerRule = RxSchedulerRule()*/


    @Before
    fun setUp() {
        val database = Room.inMemoryDatabaseBuilder(
            context,
            UserDatabase::class.java
        ).build()

        userDao = database.userDao()
    }


    @Test
    fun `DB INSERT 테스트`() {
        userDao.insertUser(
            UserEntity(
                name = "홍길동",
                phoneNumber = "01012345678",
                duration = "2주",
                startDateTime = Calendar.getInstance().timeInMillis,
                endDateTime = Calendar.getInstance().timeInMillis + 1000,
            )
        )
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
        userDao.updateUser(
            UserEntity(
                name = "홍길동2",
                phoneNumber = "01012341234",
                duration = "4주",
                startDateTime = Calendar.getInstance().timeInMillis,
                endDateTime = Calendar.getInstance().timeInMillis + 1000,
            )
        )
    }
}
