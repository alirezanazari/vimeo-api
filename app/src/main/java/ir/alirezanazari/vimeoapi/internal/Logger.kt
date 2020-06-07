package ir.alirezanazari.vimeoapi.internal

import android.content.Context
import android.util.Log
import android.widget.Toast
import ir.alirezanazari.vimeoapi.BuildConfig


class Logger {

    companion object {

        fun showToast(context: Context?, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun showToast(context: Context?, message: Int) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun showLog(text: String?) {
            if (BuildConfig.DEBUG) Log.wtf("Vimeo Log", text)
        }
    }
}