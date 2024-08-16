package com.unimaginablecat.smarttelegramhelper.message_processor;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

/**
 * Callback query processor interface.
 */
public interface CallbackQueryProcessor extends InlineKeyboardButtonMaker{
    /**
     * Get response for callback query.
     * @param callbackQuery incoming callback Qery.
     * @return {@link BotApiMethod}.
     */
    BotApiMethod<? extends Serializable> getResponse(CallbackQuery callbackQuery);
}
