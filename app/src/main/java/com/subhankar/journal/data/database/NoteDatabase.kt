package com.subhankar.journal.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.subhankar.journal.data.daos.NoteDao
import com.subhankar.journal.data.models.NoteModel


//NoteDatabasee is a room database and here singleton pattern is implemented
@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDatabase :RoomDatabase(){
    abstract fun noteDao() : NoteDao

    companion object {
        private var INSTANCE: NoteDatabase? = null
        fun getInstance(context: Context ): NoteDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "Note_database"
                    )
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}