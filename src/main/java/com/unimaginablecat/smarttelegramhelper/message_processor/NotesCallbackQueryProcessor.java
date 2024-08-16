package com.unimaginablecat.smarttelegramhelper.message_processor;

import com.unimaginablecat.smarttelegramhelper.pojo.NoteCategory;
import com.unimaginablecat.smarttelegramhelper.service.NoteCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotesCallbackQueryProcessor implements CallbackQueryProcessor, InlineKeyboardButtonMaker {
    private final String RESPONSE = """
            Your categories:
            """;
    private final NoteCategoryService noteCategoryService;

    @Override
    public BotApiMethod<? extends Serializable> getResponse(CallbackQuery callbackQuery) {
        ReplyKeyboard replyKeyboard = getReplyKeyboard();

        return SendMessage.builder()
                .chatId(callbackQuery.getMessage().getChatId())
                .text(RESPONSE)
                .replyMarkup(replyKeyboard)
                .build();
    }

    private List<List<InlineKeyboardButton>> getFooter() {
        List<List<InlineKeyboardButton>> buttonRows = new ArrayList<>();

        InlineKeyboardButton addCategoryButton = getInlineKeyboardButton("Add category", "add_category");
//        InlineKeyboardButton updateCategoryNameButton = getInlineKeyboardButton("Update category name", "update_category");
//        InlineKeyboardButton removeCategoryNameButton = getInlineKeyboardButton("Remove category name", "remove_category");


        List<InlineKeyboardButton> firstRow = new ArrayList<>();
//        List<InlineKeyboardButton> secondRow = new ArrayList<>();
//        List<InlineKeyboardButton> thirdRow = new ArrayList<>();

        firstRow.add(addCategoryButton);
//        secondRow.add(updateCategoryNameButton);
//        thirdRow.add(removeCategoryNameButton);

        buttonRows.add(firstRow);
//        buttonRows.add(secondRow);
//        buttonRows.add(thirdRow);

        return buttonRows;
    }

    private List<List<InlineKeyboardButton>> getCategoriesButtons(List<NoteCategory> userCategories) {
        return userCategories
                .stream()
                .map(category -> getInlineKeyboardButton(category.getName(), category.getUserId()))
                .map(List::of)
                .toList();
    }

    protected ReplyKeyboard getReplyKeyboard() {
        List<List<InlineKeyboardButton>> result = new ArrayList<>();

        List<NoteCategory> userNotesCategories = noteCategoryService.getUserNotesCategories();
        List<List<InlineKeyboardButton>> categoriesButtons = getCategoriesButtons(userNotesCategories);
        List<List<InlineKeyboardButton>> footer = getFooter();

        result.addAll(categoriesButtons);
        result.addAll(footer);

        return InlineKeyboardMarkup.builder()
                .keyboard(result)
                .build();
    }
}
