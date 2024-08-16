package com.unimaginablecat.smarttelegramhelper.service.impl;

import com.unimaginablecat.smarttelegramhelper.entity.BotUserEntity;
import com.unimaginablecat.smarttelegramhelper.entity.NoteCategoryEntity;
import com.unimaginablecat.smarttelegramhelper.repository.NoteCategoryRepository;
import com.unimaginablecat.smarttelegramhelper.service.NoteCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteCategoryServiceImpl implements NoteCategoryService {

    private final NoteCategoryRepository noteCategoryRepository;

    @Override
    public List<NoteCategoryEntity> getUserCategories(BotUserEntity botUserEntity) {
        log.info("Getting categories for user with id: {}", botUserEntity.getId());
        return noteCategoryRepository.findNoteCategoryEntitiesByBotUserEntity(botUserEntity);
    }

    @Override
    public NoteCategoryEntity saveCategory(BotUserEntity botUserEntity, String categoryName) {
        log.info("Saving category with name: {} for user with id: {}", categoryName, botUserEntity.getId());

        NoteCategoryEntity noteCategoryEntity = NoteCategoryEntity.builder()
                .chatId(botUserEntity.getChatId())
                .botUserEntity(botUserEntity)
                .name(categoryName)
                .build();

        NoteCategoryEntity savedNoteCategoryEntity = noteCategoryRepository.save(noteCategoryEntity);
        log.info("Successfully saved note category with id: {}", savedNoteCategoryEntity.getId());
        return savedNoteCategoryEntity;
    }
}
