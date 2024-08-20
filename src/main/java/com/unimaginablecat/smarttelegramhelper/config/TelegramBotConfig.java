package com.unimaginablecat.smarttelegramhelper.config;

import com.unimaginablecat.smarttelegramhelper.callback_processor.CallbackQueryProcessor;
import com.unimaginablecat.smarttelegramhelper.message_processor.impl.AvailableCommandsInlineKeyboardMessageProcessor;
import com.unimaginablecat.smarttelegramhelper.message_processor.MessageProcessorChain;
import com.unimaginablecat.smarttelegramhelper.message_processor.impl.CommandsMessageProcessor;
import com.unimaginablecat.smarttelegramhelper.message_processor.impl.DefaultMessageProcessor;
import com.unimaginablecat.smarttelegramhelper.message_processor.impl.InfoMessageProcessor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * Base bot config.
 */
@Data
@Configuration
@PropertySource("classpath:application.properties")
public class TelegramBotConfig {

    private final Map<String, MessageProcessorChain> messageProcessorChainMap;
    private final Map<String, CallbackQueryProcessor> callbackQueryProcessorMap;
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
     * Returns map with messageProcessorChain as a value and {@link BotCommands} field({@link BotCommands#START}) as a key.
     * @return
     */
    public Map<String, MessageProcessorChain> getMessageProcessorChainMap() {
        Map<String, MessageProcessorChain> messageProcessorChainMap = new HashMap<>();

        MessageProcessorChain startCommandChain = getStartCommandChain();
        messageProcessorChainMap.put(BotCommands.START, startCommandChain);

        messageProcessorChainMap.put(BotCommands.INFO, new InfoMessageProcessor());

        messageProcessorChainMap.put(BotCommands.DEFAULT, new DefaultMessageProcessor());
        return messageProcessorChainMap;
    }

    private MessageProcessorChain getStartCommandChain() {
        MessageProcessorChain startCommandChain = messageProcessorChainMap.get("startMessageProcessor");
        MessageProcessorChain commandsCommandChain = new CommandsMessageProcessor();
        MessageProcessorChain inlineMessageCommandChain = new AvailableCommandsInlineKeyboardMessageProcessor();
        startCommandChain.setNextProcessor(commandsCommandChain);
        commandsCommandChain.setNextProcessor(inlineMessageCommandChain);
        return startCommandChain;
    }

    public Map<String, CallbackQueryProcessor> getCallbackQueryProcessorMap() {
        Map<String, CallbackQueryProcessor> resultMap = new HashMap<>();
        CallbackQueryProcessor notesCallbackQueryProcessor = callbackQueryProcessorMap.get("notesCallbackQueryProcessor");
        CallbackQueryProcessor addNoteCategoryCallbackQueryProcessor = callbackQueryProcessorMap.get("addNoteCategoryCallbackProcessor");

        resultMap.put(BotCommands.NOTES, notesCallbackQueryProcessor);
        resultMap.put(BotCommands.ADD_CATEGORY, addNoteCategoryCallbackQueryProcessor);
        return resultMap;
    }

}



