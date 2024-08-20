package com.unimaginablecat.smarttelegramhelper.callback_processor;

import com.unimaginablecat.smarttelegramhelper.message_processor.InlineKeyboardButtonMaker;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

/**
 * Callback query processor interface.
 */
public interface CallbackQueryProcessor{
    /**
     * Get response for callback query.
     * @param callbackQuery incoming callback Qery.
     * @return {@link BotApiMethod}.
     */
    BotApiMethod<? extends Serializable> getResponse(CallbackQuery callbackQuery);
}
