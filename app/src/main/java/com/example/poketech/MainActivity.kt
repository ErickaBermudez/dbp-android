package com.example.poketech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.poketech.models.Info
import com.example.poketech.models.Trainer
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtEricka = findViewById<TextView>(R.id.txtEricka)
        val txtLulu = findViewById<TextView>(R.id.txtLulu)

        val queue = Volley.newRequestQueue(this)
        val url = "https://poketech.herokuapp.com/about"
        val jsonRequest : StringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener { response ->
                //Log.i("JSON", response.toString());
                val gson = Gson()
                val info : Info = gson.fromJson(response.toString(), Info::class.java)
                //Log.i("OBJECT", "equipo: ${info.servidor}")

                val ericka : Trainer = info.equipo[0]
                val lulu : Trainer = info.equipo[4]

                txtEricka.setText(ericka.name + " " + ericka.lastName)
                txtLulu.setText(lulu.name + " " + lulu.lastName)

            }, Response.ErrorListener {error ->
                Log.e("JSON", "Error en la petición ${error.toString()}")
            }
        )

        queue.add(jsonRequest)


    }
}
