package com.unimaginablecat.smarttelegramhelper.service;

import com.unimaginablecat.smarttelegramhelper.entity.BotUserEntity;
import com.unimaginablecat.smarttelegramhelper.entity.NoteCategoryEntity;

import java.util.List;

public interface NoteCategoryService {
    List<NoteCategoryEntity> getUserCategories(BotUserEntity botUserEntity);
    NoteCategoryEntity saveCategory(BotUserEntity botUserEntity, String categoryName);
}
