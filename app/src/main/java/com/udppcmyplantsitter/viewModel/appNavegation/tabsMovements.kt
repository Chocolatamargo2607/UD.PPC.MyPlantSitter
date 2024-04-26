package com.udppcmyplantsitter.viewModel.appNavegation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.udppcmyplantsitter.ui.theme.MainColor
import com.example.udppcmyplantsitter.viewModel.appNavegation.appScreens
import com.udppcmyplantsitter.modelpackage.ItemsTabs
import com.udppcmyplantsitter.modelpackage.ItemsTabs.*
import kotlinx.coroutines.launch




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
            TopAppBar(
                title = { Text(text=" My Plant Sitter ღ") },
                colors = TopAppBarDefaults.topAppBarColors(
                    MainColor,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick ={ navController.navigate(route = appScreens.screenWelcome.router)}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
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