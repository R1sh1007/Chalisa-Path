@file:OptIn(ExperimentalMaterial3Api::class)

package com.rp.chalisapath.presentation.components.chalisaDetails

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.rp.chalisapath.R
import com.rp.chalisapath.domain.model.ChalishaDetails
import kotlinx.coroutines.delay

@Composable
fun ChalisaDetailScreen(
    backStackEntry: NavBackStackEntry,
    viewModel: ChalisaDetailsViewModel,
    navController: NavController
) {
    val chalisaList by viewModel.chalishaDetails.collectAsState(initial = emptyList())
    val context = LocalContext.current
    val name = backStackEntry.arguments?.getString("chalisa_name")?.uppercase() ?: ""
    val id = backStackEntry.arguments?.getString("id")
    val isPlaying by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        id?.let { viewModel.getChalisaById(it) }
    }
    playSoundEffect(context = context, soundResId = R.raw.temple_sound)

    ChalisaDetailContent(
        name = name,
        chalisaList = chalisaList,
        navController = navController,
        paddingValues = PaddingValues()
    )
}

@Composable
fun ChalisaDetailContent(
    name: String,
    chalisaList: List<ChalishaDetails>,
    navController: NavController,
    paddingValues: PaddingValues
) {
    Scaffold(
        topBar = {
            ChalisaDetailTopBar(name, navController)
        }
    ) { innerPadding ->
        ChalisaDetailBody(
            chalisaList = chalisaList,
            paddingValues = innerPadding
        )
    }
}

@Composable
fun ChalisaDetailTopBar(name: String, navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(16.dp),
            )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color(0xFFFFC107)),
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun ChalisaDetailBody(
    chalisaList: List<ChalishaDetails>,
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        // Set the background
        BackgroundImage(R.drawable.bg_theme)

        LazyColumn {
            items(chalisaList) { chalisa ->
                ChalisaCard(chalisa)
            }
        }
    }
}

@Composable
fun ChalisaCard(chalisa: ChalishaDetails) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.card_bg)),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //BannerAdView(context = LocalContext.current, adUnitId = LocalContext.current.getString(R.string.add_key))
            Text(
                text = chalisa.hindi,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF424242)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = chalisa.explanation,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF424242)
            )
        }
    }
}

@Composable
fun BackgroundImage(resourceId: Int) {
    Image(
        painter = painterResource(id = resourceId),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun playSoundEffect(context: Context, soundResId: Int) {
    val mediaPlayer = MediaPlayer.create(context, soundResId)
    LaunchedEffect(Unit) {
        mediaPlayer.start()
        delay(5000)
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
