package com.unimaginablecat.smarttelegramhelper.service;

import com.unimaginablecat.smarttelegramhelper.pojo.Note;

import java.util.List;
import java.util.UUID;

public class MockNoteServiceImpl implements NoteService {
    private final List<Note> userNotes = List.of(
            new Note(UUID.randomUUID(), "1", "FIRST NOTE", "1", "1"),
            new Note(UUID.randomUUID(), "2", "SECOND NOTE", "1", "1"),
            new Note(UUID.randomUUID(), "3", "THIRD NOTE", "1", "1"),
            new Note(UUID.randomUUID(), "4", "FOURTH NOTE", "1", "1")
    );
    @Override
    public List<Note> getUserNotesByCategory() {
        return userNotes;
    }
}
