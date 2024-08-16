package com.unimaginablecat.smarttelegramhelper.message_processor.impl;

import com.unimaginablecat.smarttelegramhelper.message_processor.InlineKeyboardButtonMaker;
import com.unimaginablecat.smarttelegramhelper.message_processor.KeyboardMessageProcessor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class AvailableCommandsInlineKeyboardMessageProcessor extends KeyboardMessageProcessor implements InlineKeyboardButtonMaker {
    private static final String RESPONSE = """
            This is what i can do:
            """;
    @Override
    protected String getResponseMessageText() {
        return RESPONSE;
    }

    @Override
    protected ReplyKeyboard getReplyKeyboard() {
        InlineKeyboardButton notesButton = getInlineKeyboardButton("Notes", "notes");
        InlineKeyboardButton notificationsButton = getInlineKeyboardButton("Notifications", "notif");

        List<InlineKeyboardButton> firstRow = new ArrayList<>();

        firstRow.add(notesButton);
        firstRow.add(notificationsButton);

        return InlineKeyboardMarkup.builder()
                .keyboardRow(firstRow)
                .build();
    }
}
