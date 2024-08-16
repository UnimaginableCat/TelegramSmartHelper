package com.unimaginablecat.smarttelegramhelper.repository;

import com.unimaginablecat.smarttelegramhelper.entity.BotUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface BotUserRepository extends JpaRepository<BotUserEntity, UUID> {
    Optional<BotUserEntity> findBotUserEntityByChatIdAndTelegramUserId(String chatId, String telegramUserId);
    Optional<BotUserEntity> findBotUserEntityByTelegramUserId(String telegramUserId);
}
