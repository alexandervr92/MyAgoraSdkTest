package com.example.corpbizqchatsdk.base

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

class EaseBaseFragment : Fragment() {
    var mContext: Activity? = null
    var onClickBackPress = false
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as Activity
    }

    /**
     * Get the current view control by id,
     * which needs to be called in the life cycle after onViewCreated()
     * @param id
     * @param <T>
     * @return
    </T> */
    protected fun <T : View?> findViewById(@IdRes id: Int): T {
        return requireView().findViewById(id)
    }

    /**
     * back
     */
    fun onBackPress() {
        onClickBackPress = true
        mContext!!.onBackPressed()
    }

    /**
     * hide keyboard
     */
    protected fun hideKeyboard() {
        if (requireActivity().window.attributes.softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (requireActivity().currentFocus != null) {
                val inputManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        ?: return
                inputManager.hideSoftInputFromWindow(
                    requireActivity().currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
    }

    val isActivityDisable: Boolean
        /**
         * Determine whether the current activity is available
         * @return
         */
        get() = mContext == null || mContext!!.isFinishing

    /**
     * Switch to UI thread
     * @param runnable
     */
    fun runOnUiThread(runnable: Runnable?) {
        EaseThreadManager.getInstance().runOnMainThread(runnable)
    }
}