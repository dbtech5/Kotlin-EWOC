package com.example.ewoc_kotlin.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ewoc_kotlin.R
import com.example.ewoc_kotlin.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProvider(this)[SlideshowViewModel::class.java]

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val webView = binding.webContent
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.pluginState = WebSettings.PluginState.ON;
        webView.settings.javaScriptCanOpenWindowsAutomatically = true;
        webView.settings.allowFileAccess = true;
        webView.settings.domStorageEnabled = true;



        webView.loadUrl("https://ewoc.eastwater.com/ewoc/map.php")

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}