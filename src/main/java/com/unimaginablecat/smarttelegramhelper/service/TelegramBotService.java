package com.unimaginablecat.smarttelegramhelper.service;

import com.unimaginablecat.smarttelegramhelper.config.TelegramBotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

/**
 * Основной класс бота.
 */
@Slf4j
@Component
public class TelegramBotService extends TelegramLongPollingBot {

    private final TelegramBotConfig botConfig; // Конфиг бота
    private final Map<String, BotService> botServiceMap;

    public TelegramBotService(@Autowired TelegramBotConfig botConfig, @Autowired Map<String, BotService> botServiceMap) {
        super(botConfig.getApiToken());
        this.botServiceMap = botServiceMap;
        log.info("Bot started!");
        this.botConfig = botConfig;
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

}
