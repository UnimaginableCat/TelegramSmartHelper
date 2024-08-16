package com.unimaginablecat.smarttelegramhelper.message_processor;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public interface InlineKeyboardButtonMaker {
    default InlineKeyboardButton getInlineKeyboardButton(String text, String callbackData){
        return InlineKeyboardButton
                .builder()
                .text(text)
                .callbackData(callbackData)
                .build();
    }
}
