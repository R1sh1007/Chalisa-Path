package com.rp.chalisapath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rp.chalisapath.presentation.components.chalisaDetails.ChalisaDetailScreen
import com.rp.chalisapath.presentation.components.chalisaDetails.ChalisaDetailsViewModel
import com.rp.chalisapath.presentation.components.home.HomeScreen
import com.rp.chalisapath.presentation.components.home.HomeViewModel
import com.rp.chalisapath.presentation.components.splash.SplashScreen
import com.rp.chalisapath.presentation.theme.ChalisaPathTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel :HomeViewModel by viewModels()
    private val detailsViewModel:ChalisaDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            NavHost(navController, startDestination = "home") {
//                composable("splash") {
//                    SplashScreen(navController = navController)
//                }
                composable("home") {
                    HomeScreen(viewModel, navController)
                }
                composable("chalisaDetail/{id}/{chalisa_name}") { backStackEntry ->
                    ChalisaDetailScreen(backStackEntry, detailsViewModel,navController,)
                }
            }
        }
       // MobileAds.initialize(this) { }

    }
    }

