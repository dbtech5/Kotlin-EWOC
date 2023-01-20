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
import androidx.recyclerview.widget.RecyclerView.*
import com.example.ewoc_kotlin.R
import com.example.ewoc_kotlin.databinding.FragmentHomeBinding
import java.io.*
import java.net.URL
import org.json.JSONObject




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
        var layoutManager = LinearLayoutManager(this?.context);
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
    public class RecyclerViewAdapter(private var strings_item: Array<String?>) : Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var v:View = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)


            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when(holder){
                is ViewHolder ->{
                    strings_item[position]?.let { holder.bind(it) }
                }
            }
        }

        override fun getItemCount(): Int {
            return strings_item.size-1;
        }


    }
    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*
        var reader: JSONObject = JSONObject("{" +
                "'dk':'ดอกกราย'," +
                "'npl':'หนองปลาไหล'," +
                "'ky':'คลองใหญ่'," +
                "'ps':'ประแสร์'," +
                "'nk':'หนองค้อ'," +
                "'bp':'บางพระ'," +
                "'kl':'คลองหลวง รัชชโลทร'," +
                "'ksy':'คลองสียัด'," +
                "'mpc':'มาบประชัน'," +
                "'cn':'ซากนอก'," +
                "'hkj':'ห้วยขุนจิต'," +
                "'hsp':'ห้วยสะพาน'," +
                "'nkd':'หนองกลางดง'," +
                "'krb':'คลองระบม'," +
                "}")

         */
        var name:TextView? = itemView.findViewById<TextView>(R.id.Reservoir)
        var inflow:TextView? = itemView.findViewById<TextView>(R.id.Inflow)
        var outflow:TextView? = itemView.findViewById<TextView>(R.id.Outflow)
        var img:ImageView? = itemView.findViewById<ImageView>(R.id.img)
        fun bind(name: String){
            var txt_lit = name?.split(",")
            this.name?.text = txt_lit[0]
            //reader[txt_lit[0]].toString()
            this.inflow?.text = "ปริมาณน้ำไหลเข้า "+txt_lit[2]+" ล้าน ลบ.ม."
            this.outflow?.text = "ปริมาณน้ำไหลออก "+txt_lit[3]+" ล้าน ลบ.ม."
            this.img!!.setImageResource(R.drawable.reservoir)
        }
    }



}