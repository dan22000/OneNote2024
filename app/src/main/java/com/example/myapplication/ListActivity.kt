package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.room.Room

class ListActivity : AppCompatActivity() {

    private var noteDao: NoteDao? = null
    private var adapter: NoteAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setSupportActionBar(findViewById(R.id.tbMain))

        // Initialize Room DB
        val db = Room.databaseBuilder(
            applicationContext,
            NotesDatabase::class.java, "notes"
        ).allowMainThreadQueries().build()
        noteDao = db.noteDao()

        // Find view by Ids
        val lvNotes = findViewById<ListView>(R.id.lvNotes)
        adapter = NoteAdapter(this, noteDao!!.getAll())
        lvNotes.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        // Update View
        adapter?.notes = noteDao!!.getAll()
        adapter?.notifyDataSetChanged()
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