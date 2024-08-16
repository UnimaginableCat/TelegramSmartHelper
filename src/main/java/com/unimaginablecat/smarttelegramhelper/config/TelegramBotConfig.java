package com.unimaginablecat.smarttelegramhelper.config;

import com.unimaginablecat.smarttelegramhelper.message_processor.BotMessageProcessorDeprecated;
import com.unimaginablecat.smarttelegramhelper.message_processor.impl.AvailableCommandsInlineKeyboardMessageProcessor;
import com.unimaginablecat.smarttelegramhelper.message_processor.MessageProcessorChain;
import com.unimaginablecat.smarttelegramhelper.message_processor.impl.CommandsMessageProcessor;
import com.unimaginablecat.smarttelegramhelper.message_processor.impl.DefaultMessageProcessor;
import com.unimaginablecat.smarttelegramhelper.message_processor.impl.InfoMessageProcessor;
import com.unimaginablecat.smarttelegramhelper.message_processor.impl.StartMessageProcessor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Основной конфиг для бота.
 */
@Data
@Configuration
@PropertySource("classpath:application.properties")
public class TelegramBotConfig {

    private final List<BotMessageProcessorDeprecated> messageProcessorList;

    public TelegramBotConfig(@Autowired List<BotMessageProcessorDeprecated> messageProcessorList) {
        this.messageProcessorList = messageProcessorList;
    }

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

    /**
     * Map в которой находятся обработчики сообщений.
     * @return Map с обработчиками сообщений.
     */
    @Bean(value = "messageProcessorMap")
    public Map<String, BotMessageProcessorDeprecated> getMessageProcessorsMap() {
        return messageProcessorList
                .stream()
                .collect(Collectors.toMap(BotMessageProcessorDeprecated::getCommand, Function.identity()));
    }

    @Bean(value = "messageProcessorChainMap")
    public Map<String, MessageProcessorChain> getMessageProcessorChainMap() {
        Map<String, MessageProcessorChain> messageProcessorChainMap = new HashMap<>();

        MessageProcessorChain startCommandChain = getStartCommandChain();
        messageProcessorChainMap.put(BotCommands.START, startCommandChain);

        messageProcessorChainMap.put(BotCommands.INFO, new InfoMessageProcessor());

        messageProcessorChainMap.put(BotCommands.DEFAULT, new DefaultMessageProcessor());
        return messageProcessorChainMap;
    }

    private MessageProcessorChain getStartCommandChain() {
        MessageProcessorChain startCommandChain = new StartMessageProcessor();
        MessageProcessorChain commandsCommandChain = new CommandsMessageProcessor();
        MessageProcessorChain inlineMessageCommandChain = new AvailableCommandsInlineKeyboardMessageProcessor();
        startCommandChain.setNextProcessor(commandsCommandChain);
        commandsCommandChain.setNextProcessor(inlineMessageCommandChain);
        return startCommandChain;
    }


}



