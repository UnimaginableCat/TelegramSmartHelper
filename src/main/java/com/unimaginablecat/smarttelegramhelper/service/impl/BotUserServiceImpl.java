package com.unimaginablecat.smarttelegramhelper.service.impl;

import com.unimaginablecat.smarttelegramhelper.dto.BotUserDto;
import com.unimaginablecat.smarttelegramhelper.entity.BotUserEntity;
import com.unimaginablecat.smarttelegramhelper.repository.BotUserRepository;
import com.unimaginablecat.smarttelegramhelper.service.BotUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class BotUserServiceImpl implements BotUserService {
    private final BotUserRepository botUserRepository;

    @Override
    public BotUserEntity saveUser(BotUserDto botUserDto) {
        log.info("Saving bot user entity with telegramId: {}", botUserDto.getTelegramUserId());
        BotUserEntity botUserEntity = BotUserEntity.builder()
                .telegramUserId(botUserDto.getTelegramUserId())
                .username(botUserDto.getUsername())
                .chatId(botUserDto.getChatId())
                .build();

        Optional<BotUserEntity> optionalBotUserEntity = botUserRepository.findBotUserEntityByChatIdAndTelegramUserId(
                botUserDto.getChatId(),
                botUserDto.getTelegramUserId()
        );

        if (optionalBotUserEntity.isEmpty()) {
            BotUserEntity savedBotUserEntity = botUserRepository.save(botUserEntity);
            log.info("Successfully saved entity with db id: {}", savedBotUserEntity.getId());
            return savedBotUserEntity;
        }

        BotUserEntity existingBotUserEntity = optionalBotUserEntity.get();
        log.info("User already exists with db id: {}", existingBotUserEntity.getId());
        return existingBotUserEntity;
    }

    @Override
    public BotUserEntity findUserByTelegramId(String telegramId) throws RuntimeException{
        log.info("Finding bot user entity with telegram id: {}", telegramId);
        Optional<BotUserEntity> optionalBotUserEntity = botUserRepository.findBotUserEntityByTelegramUserId(telegramId);
        if (optionalBotUserEntity.isEmpty()) {
            log.error("Can't find user by telegram id");
            throw new RuntimeException("USER IS NOT PRESENT IN DB");
        }

        BotUserEntity botUserEntity = optionalBotUserEntity.get();
        log.info("Returning bot user entity with id: {}", botUserEntity.getId());
        return botUserEntity;
    }
}
