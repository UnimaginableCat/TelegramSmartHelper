package com.unimaginablecat.smarttelegramhelper.callback_processor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.ReplyParameters;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;

import java.io.Serializable;

@Component("addNoteCategoryCallbackProcessor")
public class AddNoteCategoryCallbackQueryProcessor implements CallbackQueryProcessor {
    private static String RESPONSE = "Reply to this message with category name.";
    @Override
    public BotApiMethod<? extends Serializable> getResponse(CallbackQuery callbackQuery) {
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        forceReplyKeyboard.setForceReply(true);
        forceReplyKeyboard.setInputFieldPlaceholder("Category name");

        return SendMessage.builder()
                .chatId(callbackQuery.getFrom().getId())
                .text(RESPONSE)
                .replyMarkup(forceReplyKeyboard)
                .build();
    }
}
