package com.unimaginablecat.smarttelegramhelper.service.impl;

import com.unimaginablecat.smarttelegramhelper.dto.BotUserDto;
import com.unimaginablecat.smarttelegramhelper.entity.BotUserEntity;
import com.unimaginablecat.smarttelegramhelper.repository.BotUserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BotUserServiceTest {

    @InjectMocks
    private BotUserServiceImpl botUserService;
    @Mock
    private BotUserRepository botUserRepository;

    private static BotUserEntity expectedBotUserEntity; // Сущность, которую возвращает мокнутый репо.
    private static BotUserDto botUserDto; // Input data

    @BeforeAll
    static void setup() {
        String telegramUserId = "userId1";
        String chatId = "chatId1";
        String username = "Mock username";

        UUID savedEntityUUID = UUID.randomUUID();
        expectedBotUserEntity = BotUserEntity.builder()
                .id(savedEntityUUID)
                .telegramUserId(telegramUserId)
                .chatId(chatId)
                .username("Mock username")
                .build();

        botUserDto = new BotUserDto();
        botUserDto.setTelegramUserId(telegramUserId);
        botUserDto.setUsername(username);
        botUserDto.setChatId(chatId);

    }

    @Test
    @DisplayName("When start message comes from new user then should save bot_user entity to db")
    void saveNewUser() {
        when(botUserRepository.save(any(BotUserEntity.class))).thenReturn(expectedBotUserEntity);
        BotUserEntity actualBotUserEntity = botUserService.saveUser(botUserDto);

        compareBotUserEntities(actualBotUserEntity);
    }


    @Test
    @DisplayName("When start message comes from existing user then should return existing entity")
    void handleExistingUser() {
        when(botUserRepository.findBotUserEntityByChatIdAndTelegramUserId(
                expectedBotUserEntity.getChatId(),
                expectedBotUserEntity.getTelegramUserId())
        ).thenReturn(Optional.of(expectedBotUserEntity));


        BotUserEntity actualBotUserEntity = botUserService.saveUser(botUserDto);

        verify(botUserRepository, times(0)).save(any());
        compareBotUserEntities(actualBotUserEntity);
    }

    private static void compareBotUserEntities(BotUserEntity actualBotUserEntity) {
        assertEquals(expectedBotUserEntity.getTelegramUserId(), actualBotUserEntity.getTelegramUserId());
        assertEquals(expectedBotUserEntity.getUsername(), actualBotUserEntity.getUsername());
        assertEquals(expectedBotUserEntity.getChatId(), actualBotUserEntity.getChatId());
        assertEquals(expectedBotUserEntity.getId(), actualBotUserEntity.getId());
    }
}