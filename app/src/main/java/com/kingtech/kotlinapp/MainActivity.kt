package com.kingtech.kotlinapp

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val listView = findViewById<ListView>(R.id.list_item)
//        listView.adapter = MyCustomAdapter(this)
        list_item.adapter = MyCustomAdapter(this)

    }
    private class MyCustomAdapter(private var context: Context): BaseAdapter() {

        val namesArray = arrayListOf("Jason Borne", "Manchester", "Van Persie", "Surrender")

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            if (convertView != null){
                // inflate our Layout
                val rowLayout = LayoutInflater.from(convertView.context).inflate(R.layout.item_row,parent,false)
                // find the Views from our Inflated layout
                val tvName =   rowLayout.findViewById<TextView>(R.id.tv_name)
                val tvRowCount =   rowLayout.findViewById<TextView>(R.id.tv_numberOfViews)
                tvName.text = namesArray.get(position)
                tvRowCount.text = "Row Position $position"
                return rowLayout
            }else{
                return TextView(context)
            }
        }

        override fun getItem(position: Int): Any {
            return "Kotlin ListView"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return namesArray.size
        }

    }
}

