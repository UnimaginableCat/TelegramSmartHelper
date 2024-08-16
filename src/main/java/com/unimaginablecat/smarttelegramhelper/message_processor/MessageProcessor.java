package com.unimaginablecat.smarttelegramhelper.message_processor;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

/**
 * Message processor interface.
 */
public interface MessageProcessor {
    /**
     * Get response for update from message processor.
     * @param update incoming update.
     * @return {@link BotApiMethod}.
     */
    BotApiMethod<? extends Serializable> getResponse(Update update);
}
