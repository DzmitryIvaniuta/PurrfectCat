package com.example.purrfectcat.utils.androidImageLoader

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class AndroidImageLoader(context: Context) {

    private val downloadManager: DownloadManager =
        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    fun downloadImage(url: String): Long {
        val uri = url.toUri()
        val fileName = uri.lastPathSegment ?: "downloaded_image"
        val mimeType = getMimeTypeFromUrl(url)

        val request = DownloadManager.Request(uri).apply {
            setMimeType(mimeType)
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setTitle(fileName)
            setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        }

        return downloadManager.enqueue(request)
    }

    private fun getMimeTypeFromUrl(url: String): String {
        return when {
            url.endsWith(".jpg", ignoreCase = true) || url.endsWith(
                ".jpeg",
                ignoreCase = true
            ) -> "image/jpeg"

            url.endsWith(".png", ignoreCase = true) -> "image/png"
            url.endsWith(".gif", ignoreCase = true) -> "image/gif"
            else -> "image/*"
        }
    }
}