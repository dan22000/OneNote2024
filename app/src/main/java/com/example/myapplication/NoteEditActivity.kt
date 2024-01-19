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

class NoteEditActivity : AppCompatActivity(), DialogInterface.OnClickListener {

    private var preferences: Preferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edit)

        // Set toolbar
        setSupportActionBar(findViewById(R.id.tbEdit))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        // Init preferences
        preferences = Preferences(this)

        // Find views by Id
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
        // Set title and message to null
        preferences!!.setNoteTitle(null)
        preferences!!.setNoteMessage(null)

        // Show toast to user
        Toast.makeText(this, getString(R.string.note_deleted), Toast.LENGTH_LONG).show()

        // Finish activity
        finish()
    }
}