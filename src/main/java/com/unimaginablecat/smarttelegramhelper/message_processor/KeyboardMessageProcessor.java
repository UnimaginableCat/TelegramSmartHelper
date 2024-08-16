package com.unimaginablecat.smarttelegramhelper.message_processor;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.Serializable;

public abstract class KeyboardMessageProcessor extends MessageProcessorChain {

    @Override
    public BotApiMethod<? extends Serializable> getResponse(Update update) {
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(getResponseMessageText())
                .replyMarkup(getReplyKeyboard())
                .build();
    }
    protected abstract String getResponseMessageText();

    protected abstract ReplyKeyboard getReplyKeyboard();
}
