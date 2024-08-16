package com.unimaginablecat.smarttelegramhelper.service;

import com.unimaginablecat.smarttelegramhelper.pojo.NoteCategoryPojo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("mockNoteCategoryService")
public class MockNoteCategoryServiceImpl implements NoteCategoryServiceMock {
    private static final List<NoteCategoryPojo> noteCategories = List.of(
            new NoteCategoryPojo(UUID.randomUUID(), "category1", "1"),
            new NoteCategoryPojo(UUID.randomUUID(), "category2", "1"),
            new NoteCategoryPojo(UUID.randomUUID(), "category3", "1"),
            new NoteCategoryPojo(UUID.randomUUID(), "category4", "1"),
            new NoteCategoryPojo(UUID.randomUUID(), "category5", "1"),
            new NoteCategoryPojo(UUID.randomUUID(), "category6", "1"),
            new NoteCategoryPojo(UUID.randomUUID(), "category7", "1"),
            new NoteCategoryPojo(UUID.randomUUID(), "category8", "1"),
            new NoteCategoryPojo(UUID.randomUUID(), "category9", "1"),
            new NoteCategoryPojo(UUID.randomUUID(), "category10", "1"),
            new NoteCategoryPojo(UUID.randomUUID(), "category11", "1"),
            new NoteCategoryPojo(UUID.randomUUID(), "category12", "1")
    );

    @Override
    public List<NoteCategoryPojo> getUserNotesCategories() {
        return noteCategories;
    }
}
