package com.example.cmong_pilates_attendance_project.ui.admin.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import com.example.cmong_pilates_attendance_project.R
import com.example.cmong_pilates_attendance_project.ui.component.Toolbar
import com.example.cmong_pilates_attendance_project.utils.Constant
import com.example.cmong_pilates_attendance_project.utils.LogUtil
import com.example.cmong_pilates_attendance_project.utils.showToast
import com.example.cmong_pilates_attendance_project.viewmodel.ChangeUserMileageViewModel
import com.example.cmong_pilates_attendance_project.viewmodel.UserViewModel


@Composable
fun ChangeUserMileageScreen(
    navController: NavHostController,
    viewModel: ChangeUserMileageViewModel = hiltViewModel(),
    userViewModel: UserViewModel = viewModel()
) {
    val context = LocalContext.current

    val changeMileage: (Int) -> Unit = { flag ->
        when (flag) {
            Constant.UP_Mileage_COUNT -> {
                with(viewModel) {
                    setMileage(mileage + 1)
                }
            }

            Constant.DOWN_Mileage_COUNT -> {
                with(viewModel) {
                    if (mileage > 0) {
                        setMileage(mileage - 1)
                    }
                }
            }
        }
    }

    val clickSaveButton: () -> Unit = {
        val phone = userViewModel.searchedUser?.phoneNumber ?: "null"
        viewModel.changeUserMileage(
            phone,
            viewModel.mileage
        )
    }

    //init
    LaunchedEffect(null) {
        viewModel.setMileage(userViewModel.searchedUser?.mileage ?: 0)
        viewModel.setName(userViewModel.searchedUser?.name ?: "null")
    }

    //observe
    if (viewModel.isChangeMileage) {
        userViewModel.updateSearchedUserMileage(viewModel.mileage)
        context.showToast(R.string.text_complete_change_mileage)
        navController.popBackStack()
    }

    ChangeUserMileageContent(
        navController = navController,
        viewModel = viewModel,
        changeMileage = changeMileage,
        clickSaveButton = clickSaveButton
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeUserMileageContent(
    navController: NavHostController,
    viewModel: ChangeUserMileageViewModel,
    changeMileage: (Int) -> Unit,
    clickSaveButton: () -> Unit
) {
    Scaffold(
        containerColor = Color(0xFF2b2b2b),
        topBar = {
            Toolbar(
                navController = navController,
                titleText = stringResource(R.string.text_menu_change_mileage)
            )
        },
        content = {
            Divider(
                color = Color(0xFF333333),
                thickness = 1.dp,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .padding(it)
            )
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(bottom = 60.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .background(
                                Color(0xFF333333),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(60.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.text_user_mileage, viewModel.name),
                            color = Color.White,
                            fontSize = 40.sp,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(R.string.text_user_score, viewModel.mileage),
                                color = Color.White,
                                fontSize = 80.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = "↑",
                                color = Color.White,
                                fontSize = 110.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.clickable {
                                    changeMileage(Constant.UP_ATTENDANCE_COUNT)
                                }
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "↓",
                                color = Color.White,
                                fontSize = 110.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.clickable {
                                    changeMileage(Constant.DOWN_ATTENDANCE_COUNT)
                                }
                            )
                        }

                        Text(
                            text = "-",
                            color = Color.White,
                            fontSize = 70.sp,
                            textAlign = TextAlign.Center
                        )

                    }
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 30.dp, start = 60.dp, end = 60.dp)
                        .clickable {
                            clickSaveButton()
                        },
                ) {
                    Text(
                        text = stringResource(R.string.text_save_button),
                        color = Color.White,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(
                                Color(0xFF333333),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .wrapContentSize(align = Alignment.Center)

                    )
                }
            }
        }
    )
}