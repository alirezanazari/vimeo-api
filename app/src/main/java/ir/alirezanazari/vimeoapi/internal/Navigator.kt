package ir.alirezanazari.vimeoapi.internal

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ir.alirezanazari.vimeoapi.R
import ir.alirezanazari.vimeoapi.ui.search.SearchFragment
import ir.alirezanazari.vimeoapi.ui.video.VideoFragment


class Navigator {

    companion object {

        fun openSearch(fm: FragmentManager) {
            load(true, fm, SearchFragment())
        }

        fun openVideo(id: String, fm: FragmentManager?) {
            load(false, fm, VideoFragment.newInstance(id))
        }

        private fun load(isReplace: Boolean, fm: FragmentManager?, fragment: Fragment) {
            if (isReplace) {
                fm?.let {
                    it.beginTransaction()
                        .addToBackStack(fragment.javaClass.name)
                        .replace(R.id.fragments_container, fragment, fragment.javaClass.name)
                        .commit()
                }
            } else {
                fm?.let {
                    it.beginTransaction()
                        .addToBackStack(fragment.javaClass.name)
                        .add(R.id.fragments_container, fragment, fragment.javaClass.name)
                        .commit()
                }
            }
        }
    }
}