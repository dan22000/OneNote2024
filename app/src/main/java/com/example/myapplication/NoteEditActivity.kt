package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class NoteEditActivity : AppCompatActivity() {

    private var preferences: Preferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edit)

        setSupportActionBar(findViewById(R.id.tbEdit))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        preferences = Preferences(this)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etMessage = findViewById<EditText>(R.id.etMessage)
        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener{
            preferences!!.setNoteTitle(etTitle?.text.toString())
            preferences!!.setNoteMessage(etMessage?.text.toString())

            // Show toast for user
            Toast.makeText(this, getString(R.string.note_saved), Toast.LENGTH_LONG).show()

            finish()
        }

        // Prefill title and message
        etTitle.setText(preferences!!.getNoteTitle())
        etMessage.setText(preferences!!.getNoteMessage())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}