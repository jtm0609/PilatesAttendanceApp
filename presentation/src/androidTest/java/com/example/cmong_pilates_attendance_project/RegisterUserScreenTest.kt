package com.example.cmong_pilates_attendance_project

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.isPopup
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cmong_pilates_attendance_project.hilt.DataModule
import com.example.cmong_pilates_attendance_project.utils.Utils
import com.example.cmong_pilates_attendance_project.view.admin.AdminActivity
import com.example.cmong_pilates_attendance_project.view.admin.RegisterUserFragment
import com.example.cmong_pilates_attendance_project.view.admin.ui.RegisterUserScreen
import com.example.cmong_pilates_attendance_project.viewmodel.RegisterUserViewModel
import com.example.data.data.UserEntity
import com.example.data.repository.UserRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Calendar
import javax.inject.Inject

@HiltAndroidTest
class RegisterUserScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    // RegisterUserViewModel 을 생성시 Repository가 필요하기 때문에 Repository inject가 필요.
    @Inject
    lateinit var repository: UserRepository
    lateinit var viewModel: RegisterUserViewModel

    @Before
    fun init() {
        hiltRule.inject()
        viewModel = createViewModel()
        composeTestRule.setContent {
            RegisterUserScreen(
                viewModel = viewModel,
                navController = NavHostController(LocalContext.current),
                mContext = composeTestRule.activity
            )
        }
    }

    @Test
    fun `신규_유저_등록_UI_테스트`() {
        composeTestRule.onNodeWithTag(testTag = "ID_TEXT_FIELD")
            .performTextInput("홍길동")
        composeTestRule.onNodeWithTag(testTag = "PHONE_NUMBER_TEXT_FIELD")
            .performTextInput("01012345678")
        composeTestRule.onNodeWithTag(testTag = "DURATION_TEXT")
            .performClick()
        composeTestRule.onNodeWithTag(testTag = "DURATION_SELECT_COMPLETE", useUnmergedTree = true)
            .performClick()
        composeTestRule.onNodeWithTag(testTag = "START_DATE_TEXT")
            .performClick()
        onView(withId(android.R.id.button1)).perform(click());
        composeTestRule.onNodeWithTag(testTag = "SAVE_BOX")
            .performClick()

        //입력한 값이 정상적으로 화면에 표시가 되었는지 체크
        composeTestRule.onNodeWithText("홍길동").assertExists() //이름이 입력되었다면 pass
        composeTestRule.onNodeWithText("01012345678").assertExists() //휴대폰 번호가 입력되었다면 pass
        composeTestRule.onNodeWithText("2주").assertExists() //picker 화면을 성공적으로 띄우고 설정 버튼을 클릭했다면 pass
        composeTestRule.onNodeWithText(getCurrentDate()).assertExists() //datepicker를 성공적으로 띄우고 ok버튼을 클릭했다면 pass
    }

    @Test
    fun `신규_유저_등록_기능_테스트`(){
        viewModel.addUser(
            UserEntity(
            name = "홍길동",
            phoneNumber = "01012345679",
            duration = "2주",
            startDateTime = Calendar.getInstance().timeInMillis,
            endDateTime = Calendar.getInstance().timeInMillis + 1000,
        )
        )
        Thread.sleep(3000)
        println("isSuccessAddUser: ${viewModel.isSuccessAddUser.value}")

        //유저 등록 성공 체크
        assertEquals(viewModel.isSuccessAddUser.value,true)
    }


    // repository 주입이 필요하기 때문에 ViewModel을 생성.
    private fun createViewModel() = RegisterUserViewModel(repository)

    //현재 날짜 조회
    private fun getCurrentDate(): String{
        val calendar = Calendar.getInstance()
        val currentTime = calendar.timeInMillis
        val currentDate = Utils.convertTimeStampToDateString(currentTime)
        return currentDate
    }
}