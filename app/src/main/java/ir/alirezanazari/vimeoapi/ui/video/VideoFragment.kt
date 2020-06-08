package ir.alirezanazari.vimeoapi.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import ir.alirezanazari.vimeoapi.R
import ir.alirezanazari.vimeoapi.ui.BaseFragment
import kotlinx.android.synthetic.main.video_fragment.*

class VideoFragment : BaseFragment() {

    companion object {
        fun newInstance(uri: String) = VideoFragment().apply {
            arguments = bundleOf("uri" to uri)
        }
    }

    private lateinit var viewModel: VideoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.video_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners(){
        btnBack.setOnClickListener {
            popupBackStack()
        }
    }

    override fun onBackPressed(): Boolean = true
}
