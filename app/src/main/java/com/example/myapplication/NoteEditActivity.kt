package com.example.myapplication

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.room.Room

class NoteEditActivity : AppCompatActivity(), DialogInterface.OnClickListener {

    private var noteDao: NoteDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edit)

        val id = intent.getLongExtra("id", -1)
        Toast.makeText(this, id.toString(), Toast.LENGTH_LONG).show()
        // TODO Set title and message
        // TODO Update note
        // TODO Delete note

        // Set toolbar
        setSupportActionBar(findViewById(R.id.tbEdit))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        // Find views by Id
        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etMessage = findViewById<EditText>(R.id.etMessage)
        val btnSave = findViewById<Button>(R.id.btnSave)

        // Initialize Room DB
        val db = Room.databaseBuilder(
            applicationContext,
            NotesDatabase::class.java, "notes"
        ).allowMainThreadQueries().build()
        noteDao = db.noteDao()

        // Set OnClickListener
        btnSave.setOnClickListener{
            val title = etTitle?.text.toString()
            val message = etMessage?.text.toString()

            noteDao!!.insertAll(Note(title, message))
            // Show toast for user
            Toast.makeText(this, noteDao!!.getAll().toString(), Toast.LENGTH_LONG).show()

            finish()
        }

        // TODO Prefill title and message
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.delete -> showDeleteDialog()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showDeleteDialog() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.delete_message))
            .setPositiveButton(getString(R.string.yes), this)
            .setNegativeButton(getString(R.string.no), null)
            .show()
    }

    override fun onClick(p0: DialogInterface?, p1: Int) {
        // TODO Set title and message to null

        // Show toast to user
        Toast.makeText(this, getString(R.string.note_deleted), Toast.LENGTH_LONG).show()

        // Finish activity
        finish()
    }
}