package com.unimaginablecat.smarttelegramhelper.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Основной конфиг для бота.
 */
@Data
@Configuration
@PropertySource("classpath:application.properties")
public class TelegramBotConfig {
    /**
     * Имя бота.
     */
    @Value("${telegrambots.config.botname}")
    private String botName;

    /**
     * токен для запросов к боту.
     */
    @Value("${telegrambots.config.apitoken}")
    private String apiToken;
}



