package com.unimaginablecat.smarttelegramhelper.service;

import com.unimaginablecat.smarttelegramhelper.pojo.Note;

import java.util.List;

public interface NoteService {
    public List<Note> getUserNotesByCategory();
}
