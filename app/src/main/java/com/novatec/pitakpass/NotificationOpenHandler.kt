//package com.novatec.pitakpass
//
//import android.content.Intent
//import com.novatec.pitakpass.core.enums.ENotificationType
//import com.novatec.pitakpass.ui.main.MainActivity
//import com.novatec.pitakpass.ui.passenger_post.PassengerPostActivity.Companion.EXTRA_POST_ID
//import com.onesignal.OSNotificationOpenResult
//import com.onesignal.OneSignal.NotificationOpenedHandler
//import splitties.activities.start
//
// class NotificationOpenHandler : NotificationOpenedHandler {
//    companion object {
//    }
//
//
//    // This fires when a notification is opened by tapping on it.
//    override fun notificationOpened(result: OSNotificationOpenResult) {
//        val actionType = result.action.type
//        val data = result.notification.payload.additionalData
//
//        val notificationType =data["notificationType"]
//        val postId: Long? = data?.optLong("postId")
//
//
//        if (postId != null && notificationType == ENotificationType.OFFER_CREATE.name) {
//            App.getInstance()
//                ?.startActivity(Intent(App.getInstance(), MainActivity::class.java).apply {
//                    putExtra(EXTRA_POST_ID, postId)
//                    addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
//                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                })
//        } /*else if(postId != null && notificationType == ENotificationType.POST_FINISHED.name) {
//            App.getInstance()?.start<MainActivity> {
//                addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
//                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            }
//        } */else {
//            App.getInstance()?.start<MainActivity> {
//                addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
//                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            }
//        }
//    }
//}