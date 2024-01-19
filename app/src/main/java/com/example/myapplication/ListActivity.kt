package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setSupportActionBar(findViewById(R.id.tbMain))

        val title = findViewById<TextView>(R.id.tvTitle)
        title.text = "dasdasdasdasd"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)

        return super.onCreateOptionsMenu(menu)
    }
}