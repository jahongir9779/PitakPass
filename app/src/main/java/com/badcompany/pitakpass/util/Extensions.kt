package com.badcompany.pitakpass.util

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay


/**
 * Created by jahon on 22-May-20
 */
val <T> LiveData<T>.valueNN
    get() = this.value!!


fun CoroutineScope.launchPeriodicAsync(
    repeatMillis: Long,
    maxMillis: Long,
    action: (Long) -> Unit
) = this.async {
    if (repeatMillis > 0 && maxMillis > 0 && repeatMillis < maxMillis) {
        var tempMillis = 0L
        while (repeatMillis < maxMillis) {
            action(maxMillis - tempMillis)
            delay(repeatMillis)
            tempMillis += repeatMillis
        }
    } else {
        action(0)
    }
}

fun ImageView.loadImageUrl(url: String) {
    Glide.with(this.context).load(url).into(this)
}

fun ImageView.loadCircleImageUrl(url: String) {
    Glide.with(this.context).load(url).apply(RequestOptions().circleCrop()).into(this)
}

fun View.hideKeyboard() {
    val imm = this.context!!.getSystemService(Context.INPUT_METHOD_SERVICE)!! as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showKeyboard() {
    val imm = this.context!!.getSystemService(Context.INPUT_METHOD_SERVICE)!! as InputMethodManager
    imm.toggleSoftInputFromWindow(this.applicationWindowToken, InputMethodManager.SHOW_FORCED, 0)
}

fun ContentResolver.getFileName(fileUri: Uri): String {
    var name = ""
    val returnCursor = this.query(fileUri, null, null, null, null)
    if (returnCursor != null) {
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        name = returnCursor.getString(nameIndex)
        returnCursor.close()
    }
    return name
}


val <T> T.exhaustive: T
    get() = this

fun String.numericOnly(): String {
    return Regex("[^0-9]").replace(this, "")
}
///**
// * Extension function to simplify setting an afterTextChanged action to EditText components.
// */
//fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
//    this.addTextChangedListener(object : TextWatcher {
//        override fun afterTextChanged(editable: Editable?) {
//            afterTextChanged.invoke(editable.toString())
//        }
//
//        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//
//        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
//    })
//}

