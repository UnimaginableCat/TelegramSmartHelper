package com.unimaginablecat.smarttelegramhelper.message_processor.impl;

import com.unimaginablecat.smarttelegramhelper.dto.BotUserDto;
import com.unimaginablecat.smarttelegramhelper.entity.BotUserEntity;
import com.unimaginablecat.smarttelegramhelper.mapper.BotUserMapper;
import com.unimaginablecat.smarttelegramhelper.message_processor.MessageProcessorChain;
import com.unimaginablecat.smarttelegramhelper.service.BotUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

/**
 * Class that handles /start command.
 */
@Slf4j
@Component(value = "startMessageProcessor")
@RequiredArgsConstructor
public class StartMessageProcessor extends MessageProcessorChain {
    private final BotUserService botUserService;
    private final BotUserMapper botUserMapper;
    private final static String RESPONSE = """
            Hello. I am your personal helper.
            I can help you with various problems.
            """;

    @Override
    public BotApiMethod<? extends Serializable> getResponse(Update update) {
        log.info("Handling update with id: {}", update.getUpdateId());
        BotUserDto botUserDto = botUserMapper.mapUpdateToBotUserDto(update);
        botUserService.saveUser(botUserDto);
        Long chatId = update.getMessage().getChatId();
        log.info("Sending response to chat with id: {}", chatId);
        return SendMessage.builder()
                .chatId(chatId)
                .text(RESPONSE)
                .build();
    }
}
