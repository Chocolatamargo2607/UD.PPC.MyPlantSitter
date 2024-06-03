package com.udppcmyplantsitter.dataManagement

import android.annotation.SuppressLint
import android.content.ContentValues

class AssignmentService(private val dbHelper:Helper) {

    @SuppressLint("Range")
    fun getAllPlants(): List<AssignmentDTO> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("Song", null, null, null, null, null, null)
        val songs = mutableListOf<AssignmentDTO>()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val date = cursor.getString(cursor.getColumnIndex("date"))
                val lyric = cursor.getString(cursor.getColumnIndex("lyric"))
                val song = AssignmentDTO(id, name, date, lyric)
                songs.add(song)
            } while (cursor.moveToNext())

        }

        cursor.close()
        return songs
    }
    fun save(song: AssignmentDTO): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", song.name)
            put("date", song.date)
            put("lyric", song.task)
        }
        return db.insert("Song", null, values)
    }
    fun delete(id: Int) {
        val db = dbHelper.writableDatabase
        db.delete("Song", "id=?", arrayOf(id.toString()))
    }
    fun findByNombre(nombre: String): AssignmentDTO? {
        val db = dbHelper.readableDatabase
        val projection = arrayOf("id", "name", "date", "lyric")
        val selection = "name = ?"
        val selectionArgs = arrayOf(nombre)
        val sortOrder = "name DESC"

        val cursor = db.query(
            "Song",
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )
        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow("id"))
                val name = getString(getColumnIndexOrThrow("name"))
                val date = getString(getColumnIndexOrThrow("date"))
                val lyric = getString(getColumnIndexOrThrow("lyric"))
                return AssignmentDTO(itemId.toInt(), name, date, lyric)
            }
        }
        return null
    }


}