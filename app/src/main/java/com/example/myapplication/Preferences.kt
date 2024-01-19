package com.example.myapplication

import android.content.Context

class Preferences {

    fun setNoteTitle(context: Context, noteTitle: String?) {
        val preferences = context.getSharedPreferences("preferences_note", Context.MODE_PRIVATE)
        preferences.edit().putString("note_title", noteTitle).apply()
    }

    fun getNoteTitle(context: Context): String? {
        val preferences = context.getSharedPreferences("preferences_note", Context.MODE_PRIVATE)
        return preferences.getString("note_title", null)
    }
}