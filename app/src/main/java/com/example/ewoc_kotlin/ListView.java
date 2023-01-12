package com.example.senthil.kotlin_listview.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.senthil.kotlin_listview.Model.ListViewModel
import com.example.senthil.kotlin_listview.R

class ListViewModelAdapter(val context: Context, val listModelArrayList: ArrayList<ListViewModel>) : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ViewHolder

        if (convertView == null) {
        val layoutInflater = LayoutInflater.from(context)
        view = layoutInflater.inflate(R.layout.list_view_item, parent, false)
        vh = ViewHolder(view)
        view.tag = vh
        } else {
        view = convertView
        vh = view.tag as ViewHolder
        }

        vh.tvTitle.text = listModelArrayList[position].title
        vh.tvContent.text = listModelArrayList[position].content
        return view
        }

        override fun getItem(position: Int): Any {
        return listModelArrayList[position]
        }

        override fun getItemId(position: Int): Long {
        return position.toLong()
        }

        override fun getCount(): Int {
        return listModelArrayList.size
        }
        }

private class ViewHolder(view: View?) {
    val tvTitle: TextView = view?.findViewById<TextView>(R.id.tvTitle) as TextView
    val tvContent: TextView = view?.findViewById<TextView>(R.id.tvContent) as TextView
}

package com.tgirard12.sqlitektgen.sample

        import android.content.Context
        import android.database.Cursor
        import android.database.sqlite.SQLiteDatabase
        import android.database.sqlite.SQLiteOpenHelper
        import java.util.*

class AppSqliteOpenHelper(
        context: Context?)
: SQLiteOpenHelper(
        context,
        "sqlitektgen.database",
        null,
        1) {

        override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(UserDb.CREATE_TABLE)
        db.execSQL(GroupDb.CREATE_TABLE)

        db.insert(GroupDb.TABLE_NAME, null, GroupDb(groupId = 1L, groupNname = "Github", users = null).contentValue)
        db.insert(GroupDb.TABLE_NAME, null, GroupDb(groupId = 2L, groupNname = "Google", users = null).contentValue)
        db.insert(GroupDb.TABLE_NAME, null, GroupDb(groupId = 2L, groupNname = "Twitter", users = null).contentValue)

        db.insert(UserDb.TABLE_NAME, null, UserDb(name = "John", email = "john@mail.com", createdAt = Date().time, groupId = 1L, group = null).contentValue)
        db.insert(UserDb.TABLE_NAME, null, UserDb(name = "James", email = "james@mail.com", createdAt = Date().time, groupId = 1L, group = null).contentValue)
        db.insert(UserDb.TABLE_NAME, null, UserDb(name = "Gary", email = "gary@gmail.com", createdAt = Date().time, groupId = 2L, group = null).contentValue)
        db.insert(UserDb.TABLE_NAME, null, UserDb(name = "Jake", email = "jake@gmail.com", createdAt = Date().time, groupId = 3L, group = null).contentValue)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        }
        }

/*
    Helpers methods
 */
        fun <R> Cursor.first(body: (cursor: Cursor) -> R): R? {
        this.use {
        if (!this.moveToFirst())
        return null

        return body(this)
        }
        }

        fun <R> Cursor.list(body: (cursor: Cursor) -> R): List<R> {

        val mutableList = mutableListOf<R>()
        this.use {
        if (!this.moveToFirst())
        return listOf()
        do {
        mutableList.add(body(this))

        } while (this.moveToNext())

        return mutableList.toList()
        }
        }