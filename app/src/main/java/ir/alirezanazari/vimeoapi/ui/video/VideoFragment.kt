package ir.alirezanazari.vimeoapi.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ir.alirezanazari.vimeoapi.R
import ir.alirezanazari.vimeoapi.internal.Logger
import ir.alirezanazari.vimeoapi.ui.BaseFragment
import kotlinx.android.synthetic.main.video_fragment.*
import org.koin.android.ext.android.inject

class VideoFragment : BaseFragment() {

    companion object {
        fun newInstance(uri: String) = VideoFragment().apply {
            arguments = bundleOf("uri" to uri)
        }
    }

    private val viewModel: VideoViewModel by inject()
    private val picturesAdapter: VideoPicturesAdapter by inject()
    private val commentsAdapter: VideoCommentsAdapter by inject()
    private lateinit var uri: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.video_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupRecyclers()

        arguments?.let {
            uri = it.getString("uri", "")
            Logger.showLog(uri)
            viewModel.getVideoInfo(uri)
        }
    }

    private fun setupRecyclers() {
        rvPictures.apply {
            adapter = picturesAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        }

        rvComments.apply {
            adapter = commentsAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    private fun setupListeners() {
        btnBack.setOnClickListener {
            popupBackStack()
        }

        btnRetry.setOnClickListener {
            viewModel.getVideoInfo(uri)
        }

        viewModel.videoResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                tvTitle.text = it.name
                picturesAdapter.setItems(it.pictures.sizes)
                //show player
            }
        })

        viewModel.commentsResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                commentsAdapter.setItems(it)
                if (it.isEmpty()) tvNoComment.visibility = View.VISIBLE
            }
        })

        viewModel.videoMP4Response.observe(viewLifecycleOwner , Observer {
            it?.let {
                Logger.showLog(it)
            }
        })

        viewModel.loaderVisibilityListener.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    pbLoading.visibility = View.VISIBLE
                    grContent.visibility = View.GONE
                } else {
                    pbLoading.visibility = View.GONE
                    grContent.visibility = View.VISIBLE
                }
            }
        })

        viewModel.errorVisibilityListener.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    tvError.visibility = View.VISIBLE
                    btnRetry.visibility = View.VISIBLE
                    grContent.visibility = View.GONE
                } else {
                    tvError.visibility = View.GONE
                    btnRetry.visibility = View.GONE
                    grContent.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onBackPressed(): Boolean = true
}
