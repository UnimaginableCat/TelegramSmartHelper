package com.unimaginablecat.smarttelegramhelper.service;

import com.unimaginablecat.smarttelegramhelper.pojo.NoteCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("mockNoteCategoryService")
public class MockNoteCategoryServiceImpl implements NoteCategoryService {
    private static final List<NoteCategory> noteCategories = List.of(
            new NoteCategory(UUID.randomUUID(), "category1", "1"),
            new NoteCategory(UUID.randomUUID(), "category2", "1"),
            new NoteCategory(UUID.randomUUID(), "category3", "1"),
            new NoteCategory(UUID.randomUUID(), "category4", "1"),
            new NoteCategory(UUID.randomUUID(), "category5", "1"),
            new NoteCategory(UUID.randomUUID(), "category6", "1"),
            new NoteCategory(UUID.randomUUID(), "category7", "1"),
            new NoteCategory(UUID.randomUUID(), "category8", "1"),
            new NoteCategory(UUID.randomUUID(), "category9", "1"),
            new NoteCategory(UUID.randomUUID(), "category10", "1"),
            new NoteCategory(UUID.randomUUID(), "category11", "1"),
            new NoteCategory(UUID.randomUUID(), "category12", "1")
    );

    @Override
    public List<NoteCategory> getUserNotesCategories() {
        return noteCategories;
    }
}
