package com.test.projectmanagementapplication.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.tapadoo.alerter.Alerter


class CommonUtils {

    private lateinit var mCommonUtils: CommonUtils

    fun showAlert(
        setDuration: Long,
        title: String?,
        text: String?,
        drawable: Int,
        color: Int,
        activity: Activity

    ) {
        Alerter.create(activity)
            .setTitle(title)
            .setText(text)
            .setDuration(setDuration)
            .setIcon(drawable)
            .setBackgroundColorRes(color)
            .show()
    }
    fun LogPrint(tag: String?, message: String?) {
        if (message != null) {
            Log.d(tag, message)
        }

    }

    fun ToastPrint(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }








    fun hideKeyboard(activity: Context) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
















}