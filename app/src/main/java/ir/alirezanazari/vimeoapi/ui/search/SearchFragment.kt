package ir.alirezanazari.vimeoapi.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import ir.alirezanazari.vimeoapi.R
import ir.alirezanazari.vimeoapi.internal.Constants.Net.DIRECTION_ASC
import ir.alirezanazari.vimeoapi.internal.Constants.Net.DIRECTION_DESC
import ir.alirezanazari.vimeoapi.internal.Logger
import ir.alirezanazari.vimeoapi.ui.BaseFragment
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.ext.android.inject

class SearchFragment : BaseFragment() {

    private val viewModel: SearchViewModel by inject()
    private var page = 1
    private var isEndOfList = false
    private var isSortAscending = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        btnSort.setOnClickListener {
            btnSort.setImageResource(if (isSortAscending) R.drawable.decending else R.drawable.accending)
            isSortAscending = !isSortAscending
        }

        btnSearch.setOnClickListener {
            val query: String = edtSearch.text.toString()
            if (query.isNotEmpty() && query.trim().length > 3) {
                viewModel.searchVideo(
                    query,
                    page,
                    if (isSortAscending) DIRECTION_ASC else DIRECTION_DESC
                )
            } else {
                Logger.showToast(activity, R.string.invalid_input)
            }
        }

        viewModel.searchResponse.observe(viewLifecycleOwner, Observer { results ->
            results?.let {
                Logger.showLog("result is ready to show ${it.size}")
            }
        })


        viewModel.loaderVisibilityListener.observe(viewLifecycleOwner, Observer {
            it?.let { state ->
                if (page == 1) {
                    pbLoading.visibility = if (state) View.VISIBLE else View.GONE
                }
            }
        })

        viewModel.errorVisibilityListener.observe(viewLifecycleOwner, Observer {
            it?.let { state ->
                if (state) {
                    if (page == 1) {
                        tvError.visibility = View.VISIBLE
                        btnRetry.visibility = View.VISIBLE
                    } else {
                        Logger.showToast(tvError.context, R.string.no_item)
                    }
                } else {
                    tvError.visibility = View.GONE
                    btnRetry.visibility = View.GONE
                }
            }
        })

    }

    override fun onBackPressed(): Boolean = true

}
