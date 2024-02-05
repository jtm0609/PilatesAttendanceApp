package com.example.cmong_pilates_attendance_project

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.ViewModelProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.cmong_pilates_attendance_project.hilt.DataModule
import com.example.cmong_pilates_attendance_project.view.admin.RegisterUserFragment
import com.example.cmong_pilates_attendance_project.view.attendance.ui.RegisterUserScreen
import com.example.cmong_pilates_attendance_project.viewmodel.RegisterUserViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@UninstallModules(DataModule::class)
@RunWith(AndroidJUnit4::class)
@LargeTest
class RegisterUserScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }
    @Test
    fun `신규_유저_등록_테스트`(){

        val viewModel = ViewModelProvider(composeTestRule.activity)[RegisterUserViewModel::class.java]
        composeTestRule.setContent {
            RegisterUserScreen().registerUserScreen(viewModel)
        }

        composeTestRule.onNodeWithTag(testTag = "ID_TEXT_FIELD")
            .performTextInput("홍길동")
        composeTestRule.onNodeWithTag(testTag = "PHONE_NUMBER_TEXT_FIELD")
            .performTextInput("01012345678")
/*        composeTestRule.onNodeWithTag(testTag = "DURATION_TEXT")
            .performClick()
        composeTestRule.onNodeWithTag(testTag = "DURATION_SELECT_COMPLETE")
            .performClick()
        composeTestRule.onNodeWithTag(testTag = "START_DATE_TEXT")
            .performClick()
        composeTestRule.onNodeWithText("확인")
            .performClick()*/
        composeTestRule.onNodeWithTag(testTag="REGISTER_USER_SCREEN").assertIsNotDisplayed()
    }

}