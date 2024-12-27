package com.example.cmong_pilates_attendance_project.test

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.example.cmong_pilates_attendance_project.launchFragmentInHiltContainer
import com.example.cmong_pilates_attendance_project.ui.attendance.fragment.AttendanceMainFragment
import com.example.cmong_pilates_attendance_project.viewmodel.AdminViewModel
import com.example.cmong_pilates_attendance_project.feature.attendance.AttendanceViewModel
import com.example.data.model.UserEntity
import com.example.data.repository.AdminRepository
import com.example.data.repository.UserRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertEquals

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Calendar
import javax.inject.Inject

@HiltAndroidTest
class AttendanceScreenTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Inject
    lateinit var attendanceRepository: UserRepository
    @Inject
    lateinit var adminRepository: AdminRepository

    lateinit var attendanceViewModel: AttendanceViewModel
    lateinit var adminViewModel: AdminViewModel

    @Before
    fun setup(){
        hiltRule.inject()

        /** NavController 주입 */
        UiThreadStatement.runOnUiThread {
            navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            navController.setGraph(com.example.cmong_pilates_attendance_project.R.navigation.attendance_nav_graph)
        }

        /**
         * Fragment의 전반적인 테스트를 하고 싶을 때 아래 코드 사용
         * fragment를 생성해야 onCreate를 실행하고 setDataObserver 통해 livedata를 연결한다.
         * 기존 composeTestRule을 통해서 뷰를 생성하게 되면 fragment의 setDataObserver로 livedata를 연결하지 못하여,
         *  dialog를 표시하는 코드가 실행되지 않는다.
         */
        launchFragmentInHiltContainer<AttendanceMainFragment>(navHostController = navController) {}

        /** DB INSERT 작업**/
        insertDB()
        attendanceViewModel = createAttendanceViewModel()
        adminViewModel = createAdminViewModel()
    }

    @Test
    fun `출석화면_UI_TEST`(){
        composeTestRule.onNodeWithTag(testTag = "1")
            .performClick()
        composeTestRule.onNodeWithTag(testTag = "1")
            .performClick()
        composeTestRule.onNodeWithTag(testTag = "2", useUnmergedTree = true)
            .performClick()
        composeTestRule.onNodeWithTag(testTag = "2")
            .performClick()
        composeTestRule.onNodeWithTag(testTag = "4")
            .performClick()
        composeTestRule.onNodeWithTag(testTag = "4")
            .performClick()
        composeTestRule.onNodeWithTag(testTag = "5")
            .performClick()
        composeTestRule.onNodeWithTag(testTag = "5")
            .performClick()

        composeTestRule.onNodeWithTag("PHONE_NUMBER_TEXT").assertTextEquals("11224455")
    }

    //실제 뷰모델의 기능(function)이 잘 동작 되는지 확인
    @Test
    fun `출석_기능_테스트`(){
        attendanceViewModel.checkUser(
            phoneNumber = "01012345678"
        )
        Thread.sleep(2000)

        //유저 출석 성공 체크
        assertEquals(attendanceViewModel.searchedUser.value?.phoneNumber, "01012345678")
    }

    @SuppressLint("CheckResult")
    private fun insertDB(){
        val data = UserEntity(
            name = "홍길동",
            phoneNumber = "01012345678",
            duration = "2주",
            startDateTime = Calendar.getInstance().timeInMillis,
            endDateTime = Calendar.getInstance().timeInMillis + 100000,
        )
        attendanceRepository.insertUser(data).test()
    }

    // repository 주입이 필요하기 때문에 ViewModel을 생성.
    private fun createAttendanceViewModel() = AttendanceViewModel(attendanceRepository)
    private fun createAdminViewModel() = AdminViewModel(adminRepository)
}