package ir.alirezanazari.vimeoapi.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.alirezanazari.vimeoapi.R
import ir.alirezanazari.vimeoapi.data.net.entity.search.Video
import ir.alirezanazari.vimeoapi.internal.Constants.Net.DIRECTION_ASC
import ir.alirezanazari.vimeoapi.internal.Constants.Net.DIRECTION_DESC
import ir.alirezanazari.vimeoapi.internal.Logger
import ir.alirezanazari.vimeoapi.internal.Navigator
import ir.alirezanazari.vimeoapi.ui.BaseFragment
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.ext.android.inject

class SearchFragment : BaseFragment() {

    private val viewModel: SearchViewModel by inject()
    private val searchAdapter: SearchResultAdapter by inject()
    private var query = ""
    private var sort = DIRECTION_ASC
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
        setupRecyclerView()
        setupListeners()
    }

    private fun setupRecyclerView() {
        val lManager = LinearLayoutManager(rvVideos.context)
        rvVideos.apply {
            adapter = searchAdapter
            layoutManager = lManager
        }

        searchAdapter.onClick = {
            it?.let {
                Navigator.openVideo(it, activity?.supportFragmentManager)
            }
        }

        var visibleItemCount: Int
        var totalItemCount: Int
        var pastItemCount: Int

        rvVideos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = lManager.childCount
                    totalItemCount = lManager.itemCount
                    pastItemCount = lManager.findFirstVisibleItemPosition()

                    if (!viewModel.isLoading && !isEndOfList) {
                        if ((visibleItemCount + pastItemCount) >= totalItemCount) {
                            page++
                            viewModel.searchVideo(query, page, sort)
                        }
                    }
                }
            }
        })

    }

    private fun setupListeners() {
        btnRetry.setOnClickListener {
            page = 1
            viewModel.searchVideo(query, page, sort)
        }

        btnSort.setOnClickListener {
            btnSort.setImageResource(if (isSortAscending) R.drawable.decending else R.drawable.accending)
            isSortAscending = !isSortAscending
        }

        btnSearch.setOnClickListener {
            val _query: String = edtSearch.text.toString()
            if (_query.isNotEmpty() && _query.trim().length > 2) {
                searchAdapter.clear()
                page = 1
                query = _query
                sort = if (isSortAscending) DIRECTION_ASC else DIRECTION_DESC
                viewModel.searchVideo(query, page, sort)
            } else {
                Logger.showToast(activity, R.string.invalid_input)
            }
        }

        viewModel.searchResponse.observe(viewLifecycleOwner, Observer { results ->
            results?.let {
                handleSearchResponse(it)
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
                        handleSearchResponse(listOf()) // remove loader
                    }
                } else {
                    tvError.visibility = View.GONE
                    btnRetry.visibility = View.GONE
                }
            }
        })

    }

    private fun handleSearchResponse(items: List<Video>) {
        if (page != 1) searchAdapter.removeLoader()
        searchAdapter.setItems(items)

        if (items.isNotEmpty()) {
            searchAdapter.addLoader()
        } else {
            isEndOfList = true
        }

    }

    override fun onBackPressed(): Boolean = true

}
