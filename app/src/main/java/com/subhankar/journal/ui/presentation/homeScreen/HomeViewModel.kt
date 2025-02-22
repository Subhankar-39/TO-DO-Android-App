package com.subhankar.journal.ui.presentation.homeScreen

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.subhankar.journal.data.models.NoteModel
import com.subhankar.journal.domain.NoteRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepo = NoteRepository(application)
    var notesModel by mutableStateOf(emptyList<NoteModel>())

    fun getAllNotes() {
        viewModelScope.launch {
            noteRepo.getAllNotesFromRoom().collect { response ->
                notesModel = response
            }
        }
    }

    fun deleteNote(noteModel: NoteModel) {
        viewModelScope.launch {
            noteRepo.deleteNoteFromRoom(noteModel)
        }
    }
}