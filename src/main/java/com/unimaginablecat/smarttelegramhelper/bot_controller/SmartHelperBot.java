package com.unimaginablecat.smarttelegramhelper.bot_controller;

import com.unimaginablecat.smarttelegramhelper.config.TelegramBotConfig;
import com.unimaginablecat.smarttelegramhelper.message_processor.CallbackQueryProcessor;
import com.unimaginablecat.smarttelegramhelper.message_processor.MessageProcessorChain;
import com.unimaginablecat.smarttelegramhelper.message_processor.NotesCallbackQueryProcessor;
import com.unimaginablecat.smarttelegramhelper.message_processor.impl.DefaultMessageProcessor;
import com.unimaginablecat.smarttelegramhelper.service.MockNoteCategoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

/**
 * Основной класс бота.
 */
@Slf4j
@Scope(SCOPE_SINGLETON)
@Component
public class SmartHelperBot extends TelegramLongPollingBot {
    private final TelegramBotConfig botConfig; // Конфиг бота
    private final Map<String, MessageProcessorChain> messageProcessorChainMap;
    private final Map<String, CallbackQueryProcessor> callBackProcessorChainMap;
    public SmartHelperBot(@Autowired TelegramBotConfig botConfig,
                          @Autowired @Qualifier("messageProcessorChainMap") Map<String, MessageProcessorChain> messageProcessorChainMap) {
        super(botConfig.getApiToken());
        this.botConfig = botConfig;
        this.messageProcessorChainMap = messageProcessorChainMap;
        this.callBackProcessorChainMap = new HashMap<>();
        callBackProcessorChainMap.put("notes", new NotesCallbackQueryProcessor(new MockNoteCategoryServiceImpl()));
    }

    @Override
    public void onUpdateReceived(Update update) {
        List<BotApiMethod<? extends Serializable>> responses = new ArrayList<>();
        if (update.hasMessage()) {
            String messageText = getUpdateMessage(update);
            MessageProcessorChain messageProcessorChain = messageProcessorChainMap.getOrDefault(messageText, new DefaultMessageProcessor());
            messageProcessorChain.processMessage(update, responses);
        }

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            CallbackQueryProcessor callbackQueryProcessor = callBackProcessorChainMap.get(data);
            BotApiMethod<? extends Serializable> response = callbackQueryProcessor.getResponse(callbackQuery);
            responses.add(response);

        }

        sendResponse(responses);
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    private String getUpdateMessage(Update update) {
        return update.getMessage().getText();
    }

    public void sendResponse(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendResponse(List<BotApiMethod<? extends Serializable>> responses) {
        responses.forEach(response -> {
            try {
                execute(response);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
