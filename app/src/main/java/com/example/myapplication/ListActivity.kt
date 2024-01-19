package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class ListActivity : AppCompatActivity() {

    private var tvTitle: TextView? = null
    private var tvMessage: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setSupportActionBar(findViewById(R.id.tbMain))

        tvTitle = findViewById(R.id.tvTitle)
        tvMessage = findViewById(R.id.tvMessage)
        val lvNotes = findViewById<ListView>(R.id.lvNotes)
        val notes = arrayOf(
            "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test",
            "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test"
        )
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notes)
        lvNotes.adapter = arrayAdapter
    }

    override fun onResume() {
        super.onResume()

        tvTitle!!.text = Preferences(this).getNoteTitle()
        tvMessage!!.text = Preferences(this).getNoteMessage()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add) {
            val intent = Intent(this, NoteEditActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}