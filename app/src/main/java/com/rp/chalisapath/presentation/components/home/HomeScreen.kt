package com.rp.chalisapath.presentation.components.home


import android.content.Context
import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rp.chalisapath.R
import com.rp.chalisapath.domain.model.ChalishaListItem
import com.rp.chalisapath.presentation.components.Header
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    val chalisaList = viewModel.chalisaList.collectAsState(initial = emptyList())
    var context = LocalContext.current
    var showDialog by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        viewModel.loadChalishaInfo()

    }

    if(showDialog){
        ChalisaNotFoundDialog(onDismiss = { showDialog = false })
    }


        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("चालीसा पाठ",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = androidx.compose.ui.graphics.Color.Black,
                        modifier = Modifier.padding(16.dp),
                    ) },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = Color(0xFFFFC107) // Custom background color
                    )
                )
            }

        ) { paddingValues ->

            Box(
                modifier = Modifier.fillMaxSize() // Fill the entire screen
            ) {
                // Set the vector drawable as a background
                Image(
                    painter = painterResource(id = R.drawable.bg_theme), // Your vector drawable resource
                    contentDescription = null, // Background image doesn't need a content description
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds // Make sure the vector scales properly
                )

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 150.dp), // Adapts based on screen size
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),

                    ) {
                    items(chalisaList.value) { chalisa ->

                            ChalisaCard(chalisa, navController,showDialog, context = context)


                    }
                }

            }
        }

    }

fun imgSource(id: Int): Int {
    return when (id) {
        1 -> R.drawable.hanuman_img
        2 -> R.drawable.krishna_img
        3 -> R.drawable.ram_img
        4 -> R.drawable.durga_img
        5 -> R.drawable.kali_img
        6 -> R.drawable.jaharveer_img
        7 -> R.drawable.vishnu_img
        8 -> R.drawable.shiv_img
        else -> R.drawable.hanuman_img // In case no match is found, use a default image
    }
}

@Composable
fun ChalisaCard(item: ChalishaListItem,navController: NavController,showDialog:Boolean,context:Context) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.card_bg)),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            //.height(180.dp)
            .clickable {
                    navController.navigate("chalisaDetail/${item.fileName}/${item.name}")
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val painter: Painter = painterResource(imgSource(item.id)) // Use your image resource here

            // Chalisa Image
            Image(
                painter = painter,
                contentDescription = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        10.dp,
                        shape = RoundedCornerShape(10)
                    ),
                contentScale = ContentScale.Fit
            )
            
            Spacer(modifier = Modifier.height(10.dp))

            // Chalisa Name
            Text(
                text = item.name.uppercase(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF424242)
            )
        }
    }
}




