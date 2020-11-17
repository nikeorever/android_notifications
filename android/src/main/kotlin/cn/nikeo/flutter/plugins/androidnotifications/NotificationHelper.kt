@file:JvmMultifileClass

package cn.nikeo.flutter.plugins.androidnotifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RestrictTo
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService

internal const val PRIMARY_CHANNEL_ID = "default"
private const val PRIMARY_CHANNEL_NAME = "Primary Channel"

/**
 * Same as [android.content.res.Resources.ID_NULL].
 */
internal const val ID_NULL = 0

@RestrictTo(value = [RestrictTo.Scope.LIBRARY])
internal class NotificationManagerHelper(private val applicationContext: Context) {

    private val manager: NotificationManager by lazy {
        checkNotNull<NotificationManager>(applicationContext.getSystemService(), {
            "Failure to create android.app.NotificationManager."
        }).also { manager ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                        PRIMARY_CHANNEL_ID,
                        PRIMARY_CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT)

                manager.createNotificationChannel(channel)
            }
        }
    }

    fun defaultSmallIcon(): Int {
        val mipmap = "${applicationContext.packageName}.R\$mipmap"
        val icLauncher = "ic_launcher"
        return runCatching {
            Class.forName(mipmap).getField(icLauncher).getInt(null)
        }.getOrElse {
            error("""
                Failure to use the default small icon($mipmap.$icLauncher) as the notification`s small icon.
                There are two solutions:
                1.If you want to use $mipmap.$icLauncher as the notification`s small icon, please add it.
                2.Please register this plugin(AndroidNotificationsPlugin) manually and add the resource id of the small icon to it.
            """.trimIndent())
        }
    }

    fun notify(tag: String?, id: Int, notificationSupplier: (Context) -> Notification) {
        manager.notify(tag, id, notificationSupplier(applicationContext))
    }

    fun cancel(tag: String?, id: Int) = manager.cancel(tag, id)
}

fun primaryNotificationBuilder(
        context: Context,
        contentTitle: String,
        contentText: String,
        smallIcon: Int): NotificationCompat.Builder {
    return NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
            .setContentTitle(contentTitle)
            .setContentText(contentText)
            .setSmallIcon(smallIcon)
}