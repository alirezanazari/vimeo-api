package ir.alirezanazari.vimeoapi.ui

import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {

    fun popupBackStack() {
        activity?.onBackPressed()
    }

    abstract fun onBackPressed(): Boolean

}