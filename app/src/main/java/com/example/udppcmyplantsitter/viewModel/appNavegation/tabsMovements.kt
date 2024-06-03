package com.example.udppcmyplantsitter.viewModel.appNavegation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.udppcmyplantsitter.R
import com.example.udppcmyplantsitter.ui.theme.MainColor
import com.example.udppcmyplantsitter.ui.theme.SecondColor
import com.udppcmyplantsitter.modelpackage.ItemsTabs
import com.udppcmyplantsitter.modelpackage.ItemsTabs.*
import kotlinx.coroutines.launch




@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun tabsMovements(navController: NavController){

    val tabs= listOf(
        tabMain,
        tabPlants,
        tabMyPlants
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        tabs.size
    }
    Column {
        Row() {
            var showMenu by remember{
                mutableStateOf(false)
            }
            TopAppBar(
                title = { Text(text=" My Plant Sitter ღ") },
                colors = TopAppBarDefaults.topAppBarColors(
                    MainColor,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { showMenu = !showMenu}) {
                        Icon(imageVector = Icons.Filled.MoreVert , contentDescription = "Options", tint = Color.White)
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        modifier= Modifier.width(150.dp)
                    ){
                        DropdownMenuItem(
                            text = { Text("Account", color = SecondColor) },
                            onClick = {
                                navController.navigate(route = appScreens.screenAccount.router)
                                showMenu = false
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = "Account",
                                    tint = SecondColor
                                )
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Language", color = SecondColor) },
                            onClick = {  },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.iconlanguage),
                                    contentDescription = "Language",
                                    modifier = Modifier.size(31.dp),
                                    tint = SecondColor
                                )

                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Log out", color = SecondColor) },
                            onClick = {  },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.ExitToApp,
                                    contentDescription = "Log out",
                                    tint = SecondColor
                                )

                            }
                        )
                    }
                }

            )
        }
        Tabs(tabs,pagerState)
        TabsContent(tabs,pagerState,navController)

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(
    tabs: List<ItemsTabs>,
    pagerState: PagerState,
    navController: NavController
){
    HorizontalPager(
        state = pagerState
    ) {page ->
        tabs[page].screen(navController)

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(tabs: List<ItemsTabs>,
         pagerState: PagerState
) {
    var selectedTab = pagerState.currentPage
    var scope= rememberCoroutineScope()
    TabRow(selectedTabIndex = selectedTab) {
        tabs.forEachIndexed { index,items ->
            Tab(
                selected = selectedTab == index,
                onClick = {
                    scope.launch {pagerState.animateScrollToPage(index) }
                },
                text = { Text(text = items.title)},
                icon = {
                    Icon(
                        if(index == selectedTab)
                            items.iconSelected
                        else
                            items.iconUnselected
                        , items.title)

                }
            )
        }
    }
}