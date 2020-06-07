package ir.alirezanazari.vimeoapi.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.alirezanazari.vimeoapi.R
import ir.alirezanazari.vimeoapi.ui.BaseFragment

class SearchFragment : BaseFragment() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onBackPressed(): Boolean = true

}
