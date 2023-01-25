package com.example.ewoc_kotlin.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ewoc_kotlin.databinding.FragmentGalleryBinding
import android.R
import android.widget.LinearLayout

import com.example.ewoc_kotlin.MainActivity

import android.widget.SimpleAdapter
import android.widget.ArrayAdapter
import com.example.ewoc_kotlin.ui.home.HomeFragment
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var strings_item = arrayOfNulls<String>(10
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textView3
        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var data_list: ListView = binding.dataList
        val list = arrayOf(
            "Cristiano Ronaldo",
            "Messi",
            "Neymar",
            "Isco",
            "Hazard",
            "Mbappe",
            "Hazard",
            "Ziyech",
            "Suarez"
        )

        Thread (
            Runnable {
                val url = URL("https://dbtech5.github.io/api_test/api.txt")
                val connection = url.openConnection()
                BufferedReader(InputStreamReader(connection.getInputStream())).use { inp ->
                    var line: String?
                    var n = 0
                    while (inp.readLine().also { line = it } != null) {
                        println(line)
                        println(line?.split("&n")?.size)
                        var lit = line?.split("&n")
                        for( item in lit!!){
                            if(n<10){
                                strings_item[n] = item.split(",")[0]
                            }

                            n+=1
                        }
                        println(strings_item.size)
                        println(list[0])
                        println(strings_item[0])

                    }
                }
            }
        ).start()
        data_list.adapter =
            this.context?.let { ArrayAdapter<String>(it,android.R.layout.simple_list_item_1,list) }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}