package com.example.animeserarch

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.selects.select

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val spinner: Spinner = findViewById(R.id.spinner)
        val listItem = arrayOf(
            "select",
            "Hunter X Hunter",
            "Dragon Ball",
            "Death Note",
            "One Piece"
        )
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listItem
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

               val animeName=listItem[position]
                if (position>0){
                    searchGoogle(animeName)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }

        }
    }
    fun searchGoogle(animeName: String){
        val uri="https://www.google.com/search?q= $animeName".toUri()
        val intent= Intent(Intent.ACTION_VIEW,uri)
        startActivity(intent)
    }
}