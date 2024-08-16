package com.unimaginablecat.smarttelegramhelper.message_processor;

import com.unimaginablecat.smarttelegramhelper.entity.BotUserEntity;
import com.unimaginablecat.smarttelegramhelper.entity.NoteCategoryEntity;
import com.unimaginablecat.smarttelegramhelper.pojo.NoteCategoryPojo;
import com.unimaginablecat.smarttelegramhelper.service.BotUserService;
import com.unimaginablecat.smarttelegramhelper.service.NoteCategoryService;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Component("notesCallbackQueryProcessor")
public class NotesCallbackQueryProcessor implements CallbackQueryProcessor {
    private String DEFAULT_RESPONSE = """
            Your categories:
            """;
    private String USER_NOT_EXISTS_RESPONSE = """
            Something went wrong, please use /start command.
            """;
    private String NO_CATEGORIES_RESPONSE = """
            Currently you don't have any added note categories.
            Add them with add category button.
            """;
    private final NoteCategoryService noteCategoryService;
    private final BotUserService botUserService;

    @Override
    public BotApiMethod<? extends Serializable> getResponse(CallbackQuery callbackQuery) {
        Long id = callbackQuery.getFrom().getId();
        BotUserEntity botUserEntity;

        try {
            botUserEntity = botUserService.findUserByTelegramId(id.toString());
        } catch (Exception e) {
            return SendMessage.builder()
                    .chatId(callbackQuery.getMessage().getChatId())
                    .text(USER_NOT_EXISTS_RESPONSE)
                    .build();
        }

        List<NoteCategoryEntity> userCategories = noteCategoryService.getUserCategories(botUserEntity);
        ReplyKeyboard replyKeyboard = getReplyKeyboard(userCategories);

        if (!userCategories.isEmpty()) {
            return SendMessage.builder()
                    .chatId(callbackQuery.getMessage().getChatId())
                    .text(DEFAULT_RESPONSE)
                    .replyMarkup(replyKeyboard)
                    .build();
        }

        return SendMessage.builder()
                .chatId(callbackQuery.getMessage().getChatId())
                .text(NO_CATEGORIES_RESPONSE)
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

    private List<List<InlineKeyboardButton>> getCategoriesButtons(List<NoteCategoryEntity> userNotesCategories) {
        return userNotesCategories
                .stream()
                .map(category -> getInlineKeyboardButton(category.getName(), "/category_" + category.getId()))
                .map(List::of)
                .toList();
    }

    protected ReplyKeyboard getReplyKeyboard(List<NoteCategoryEntity> userNotesCategories) {
        List<List<InlineKeyboardButton>> categoriesButtons = getCategoriesButtons(userNotesCategories);
        List<List<InlineKeyboardButton>> result = new ArrayList<>(categoriesButtons);

        List<List<InlineKeyboardButton>> footer = getFooter();
        result.addAll(footer);

        return InlineKeyboardMarkup.builder()
                .keyboard(result)
                .build();
    }
}
