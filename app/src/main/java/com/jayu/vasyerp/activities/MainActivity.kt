package com.jayu.vasyerp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.jayu.vasyerp.R
import com.jayu.vasyerp.adapters.PersonListViewAdapter
import com.jayu.vasyerp.utils.Arrays

class MainActivity : AppCompatActivity() {

    private lateinit var listViewPerson: ListView
    private val arrays = Arrays()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listViewPerson = findViewById(R.id.listViewPerson)
        val myListAdapter = PersonListViewAdapter(
            this,
            arrays.firstNameArray,
            arrays.lastNameArray,
            arrays.emailArray,
            arrays.avatarArray
        )
        listViewPerson.adapter = myListAdapter
        arrays.avatarArray.clear()
        arrays.firstNameArray.clear()
        arrays.emailArray.clear()
        arrays.lastNameArray.clear()
        val queue = Volley.newRequestQueue(this)
        val url = "https://reqres.in/api/users?page=1"
        val jsonObjectRequest = object : JsonObjectRequest(
            url,
            Response.Listener {
                val data = it.getJSONArray("data")
                for (i in 0 until data.length()) {
                    val personDataJsonObject = data.getJSONObject(i)
                    arrays.firstNameArray.add(personDataJsonObject.getString("first_name"))
                    arrays.lastNameArray.add(personDataJsonObject.getString("last_name"))
                    arrays.emailArray.add(personDataJsonObject.getString("email"))
                    arrays.avatarArray.add(personDataJsonObject.getString("avatar"))
                    myListAdapter.notifyDataSetChanged()
                }
            },
            Response.ErrorListener {
                Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-type"] = "application/json"
                return super.getHeaders()
            }
        }
        queue.add(jsonObjectRequest)
    }

}