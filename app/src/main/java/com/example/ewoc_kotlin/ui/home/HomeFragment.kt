package com.example.ewoc_kotlin.ui.home

import android.app.Activity
import android.app.Application
import android.os.AsyncTask
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.ewoc_kotlin.R
import com.example.ewoc_kotlin.databinding.FragmentHomeBinding
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.*
import java.io.*
import java.net.URL

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private var recyclerView:RecyclerView? = null

    private val binding get() = _binding!!
    var textView: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recyclerView = binding.userlist
        val users = arrayOfNulls<String>(8
        )
        recyclerView!!.adapter = RecyclerViewAdapter()

        Thread(Runnable {
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
                        users[n] = item
                        n+=1
                    }
                    /*
                    val adapter = activity?.let {
                        ArrayAdapter<String>(
                            it,
                            android.R.layout.simple_spinner_item,
                            users
                        )
                    }
                    mListView.adapter = adapter*/
                }






            }
        })


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    public class RecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            var v:View = LayoutInflater.from(Activity().applicationContext).inflate(R.layout.item_list, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.txt?.text = "sss"
        }

        override fun getItemCount(): Int {
            return 1;
        }

    }
    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt:TextView? = null
        fun onCreateViewHolder(parent: ViewGroup, viewType: Int) {
            txt = itemView.findViewById(R.id.textView4)
        }

        fun onBindViewHolder(holder: ViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

        fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

    }



}