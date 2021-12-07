package com.example.ca3

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.*

class contactsAdapter(private val context: Activity, private val arrayList: ArrayList<contacts>) :
    ArrayAdapter<contacts>(context, R.layout.listitem, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.listitem, null)

//        hooks
        val firstChar: TextView = view.findViewById(R.id.profile)
        val name: TextView = view.findViewById(R.id.name)
        val phone: TextView = view.findViewById(R.id.phone)


        firstChar.text = arrayList[position].name.get(0).toString()
//        firstChar.background.colorFilter = randomColor()
        name.text = arrayList[position].name
        phone.text = arrayList[position].phoneNo


        return view
    }


    private fun randomColor(): Int {
        val rnd = Random()
        val color: Int = Color.argb(
            255, rnd.nextInt(256),
            rnd.nextInt(256), rnd.nextInt(256)
        )
        return color
    }
}