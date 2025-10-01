//package com.rp.chalisapath.presentation.addBanner
//
//import android.content.Context
//import android.widget.LinearLayout
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.viewinterop.AndroidView
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.AdView
//import com.google.android.gms.ads.AdSize
//
//@Composable
//fun BannerAdView(context: Context, adUnitId: String) {
//    AndroidView(factory = {
//        AdView(it).apply {
//            setAdSize(AdSize.BANNER)
//            this.adUnitId = adUnitId
//            loadAd(AdRequest.Builder().build())
//        }
//    }, update = { adView ->
//        adView.loadAd(AdRequest.Builder().build())
//    })
//}
