package com.unimaginablecat.smarttelegramhelper.repository;

import com.unimaginablecat.smarttelegramhelper.entity.BotUserEntity;
import com.unimaginablecat.smarttelegramhelper.entity.NoteCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface NoteCategoryRepository extends JpaRepository<NoteCategoryEntity, UUID> {
    List<NoteCategoryEntity> findNoteCategoryEntitiesByBotUserEntity(BotUserEntity botUserEntity);
}
