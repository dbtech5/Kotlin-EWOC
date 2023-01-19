package com.example.ewoc_kotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ewoc_kotlin.R
import com.example.ewoc_kotlin.databinding.FragmentHomeBinding
import java.io.*
import java.net.URL

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private var recyclerView:RecyclerView? = null

    private val binding get() = _binding!!
    var textView: TextView? = null
    private var strings_item = arrayOfNulls<String>(8
    )
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

        recyclerView!!.setHasFixedSize(true);
        var layoutManager = LinearLayoutManager(parentFragment?.context);
        layoutManager.orientation = LinearLayoutManager.VERTICAL;
        recyclerView!!.layoutManager = layoutManager;


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
                            strings_item[n] = item
                            n+=1
                        }
                        println(strings_item)
                        recyclerView!!.adapter = RecyclerViewAdapter(strings_item)
                    }
                }
            }
            ).start()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    public class RecyclerViewAdapter(var strings_item: Array<String?>) : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            var v:View = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)


            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.txt?.text  = "sssss"
            //holder.txt!!.text = strings_item[position]
            //holder.img?.setImageResource(R.drawable.ic_menu_camera)
        }

        override fun getItemCount(): Int {
            return strings_item.size;
        }

    }
    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt:TextView? = null
        var img:ImageView?= null
        fun onCreateViewHolder(parent: ViewGroup, viewType: Int) {
            txt = itemView.findViewById(R.id.Reservoir)
        }

        fun onBindViewHolder(holder: ViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

        fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

    }



}