package com.jayu.vasyerp.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.jayu.vasyerp.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class PersonListViewAdapter(
    private val context: Activity,
    private val firstName: ArrayList<String>,
    private val lastName: ArrayList<String>,
    private val email: ArrayList<String>,
    private val avatar: ArrayList<String>
) : ArrayAdapter<String>(context, R.layout.row_item_person, firstName) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, View: View?, parent: ViewGroup): View {

        val rowView = context.layoutInflater.inflate(R.layout.row_item_person, parent, false)

        val personFirstName = rowView.findViewById(R.id.txtFirstName) as TextView
        val personLastName = rowView.findViewById(R.id.txtLastName) as TextView
        val personEmail = rowView.findViewById(R.id.txtEmail) as TextView
        val personAvatar = rowView.findViewById(R.id.imgPerson) as CircleImageView

        personFirstName.text = firstName[position]
        personLastName.text = lastName[position]
        personEmail.text = email[position]
        Picasso.get().load(avatar[position]).error(R.mipmap.ic_launcher).into(personAvatar)

        return rowView
    }

}