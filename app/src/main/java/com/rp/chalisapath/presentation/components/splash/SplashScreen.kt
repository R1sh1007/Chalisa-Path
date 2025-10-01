package com.rp.chalisapath.presentation.components.splash

import android.media.MediaPlayer
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rp.chalisapath.R
import kotlinx.coroutines.delay
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SplashScreen(navController: NavController){
    var isPlaying by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val infiniteTransition = rememberInfiniteTransition ()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    LaunchedEffect(Unit) {
        val mediaPlayer = MediaPlayer.create(context, R.raw.temple_sound)
        mediaPlayer.start()
        isPlaying = true
        delay(5000)
        mediaPlayer.stop()
        mediaPlayer.release()
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column{
            //FlowerAnimation()
            val painter: Painter = painterResource(id = R.drawable.bg_theme) // Use your image resource here

            Image(
                painter = painter,
                contentDescription = "Splash Screen Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth // Adjust as needed
            )
        }
    }

}

//@Composable
//fun FlowerAnimation() {
//    val flowerPainter = painterResource(id = R.drawable.ic_launcher_background)
//    val randomOffset = with(LocalDensity.current) { (50..150).random().toDp() }
//    Image(
//        painter = flowerPainter,
//        contentDescription = "Flower",
//        modifier = Modifier
//            .size(50.dp)
//            .offset(y = -randomOffset) // Apply random vertical offset
//    )
//}

