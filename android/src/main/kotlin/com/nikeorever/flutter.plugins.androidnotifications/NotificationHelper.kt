@file:JvmMultifileClass

package com.nikeorever.flutter.plugins.androidnotifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RestrictTo
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import com.nikeorever.flutter.plugins.androidnotifications.NotificationManagerHelper.Companion.PRIMARY_CHANNEL_ID

@RestrictTo(value = [RestrictTo.Scope.LIBRARY])
internal class NotificationManagerHelper {

    private var applicationContext: Context? = null
    private var manager: NotificationManager? = null

    fun createNotificationManager(applicationContext: Context) {
        this.applicationContext = applicationContext
        manager = applicationContext.getSystemService()
        manager?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                        PRIMARY_CHANNEL_ID,
                        PRIMARY_CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT).also {
                    it.lightColor = Color.GREEN
                    it.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
                }

                createNotificationChannel(channel)
            }
        }
    }

    fun notify(tag: String?, id: Int, notificationSupplier: (Context) -> Notification) {
        if (applicationContext != null && manager != null) {
            manager!!.notify(tag, id, notificationSupplier(applicationContext!!))
        }
    }

    fun cancel(tag: String?, id: Int) {
        manager?.cancel(tag, id)
    }

    fun destroyNotificationManager() {
        applicationContext = null
        manager = null
    }

    companion object {
        const val PRIMARY_CHANNEL_ID = "default"
        private const val PRIMARY_CHANNEL_NAME = "Primary Channel"
    }
}

fun primaryNotification(context: Context, contentTitle: String, contentText: String): NotificationCompat.Builder {
    return NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
            .setContentTitle(contentTitle)
            .setContentText(contentText)
            .setSmallIcon(R.drawable.ic_launcher)
            .setAutoCancel(true)
}