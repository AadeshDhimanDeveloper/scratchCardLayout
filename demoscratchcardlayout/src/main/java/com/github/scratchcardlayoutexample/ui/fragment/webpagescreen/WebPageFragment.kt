package com.github.scratchcardlayoutexample.ui.fragment.webpagescreen

import com.github.scratchcardlayoutexample.util.AppConstants.URLs
import com.aadevelopers.scratchcardview.databinding.FragmentWebpageBinding
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.DialogFragment

class WebPageFragment : DialogFragment() {

    companion object {
        private val CONTENT_TYPE: String? = "contentType"

        fun getInstance(content: WebPageContent): WebPageFragment {
            val bundle = Bundle()
            val webPageFragment = WebPageFragment()
            bundle.putSerializable(CONTENT_TYPE, content)
            webPageFragment.arguments = bundle
            return webPageFragment
        }
    }

    private lateinit var binding: FragmentWebpageBinding

    private val mChromeClient: WebChromeClient? = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (!isAdded) return
            binding.progressBar.progress = newProgress
        }
    }

    private val mWebClient: WebViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            if (!isAdded) return
            binding.progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            if (!isAdded) return
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWebpageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            setupWebViewDefaults()
            val bundle = arguments
            if (bundle != null) {
                val content = bundle.getSerializable(CONTENT_TYPE) as? WebPageContent
                        ?: WebPageContent.PAGE_VIEW_IN_GITHUB
                when (content) {
                    WebPageContent.PAGE_VIEW_ABOUT_LIBRARY -> URLs.VIEW_READ_ME?.let {
                        binding.webView.loadUrl(
                            it
                        )
                    }
                    WebPageContent.PAGE_VIEW_IN_GITHUB -> URLs.GITHUB_REPO_URL?.let {
                        binding.webView.loadUrl(
                            it
                        )
                    }
                    WebPageContent.PAGE_ISSUE_AND_FEEDBACK -> URLs.ISSUE_FEEDBACK_URL?.let {
                        binding.webView.loadUrl(
                            it
                        )
                    }
                    WebPageContent.PAGE_DONATE_BEER -> URLs.DONATE_BEER_URL?.let {
                        binding.webView.loadUrl(
                            it
                        )
                    }
                }
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebViewDefaults() {
        binding.webView.apply {
            webChromeClient = mChromeClient
            webViewClient = mWebClient

            settings.displayZoomControls = false
            settings.setSupportZoom(false)
            settings.builtInZoomControls = false
            settings.javaScriptEnabled = true
        }
    }
}