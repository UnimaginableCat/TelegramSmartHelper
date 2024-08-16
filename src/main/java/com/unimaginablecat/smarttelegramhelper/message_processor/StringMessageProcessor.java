package com.unimaginablecat.smarttelegramhelper.message_processor;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

/**
 * Processor that returns plain string message.
 */
public abstract class StringMessageProcessor extends MessageProcessorChain{
    @Override
    public BotApiMethod<? extends Serializable> getResponse(Update update) {
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(getStringResponse())
                .build();
    }

    /**
     * Returns message processor response.
     * @return string as response message.
     */
    protected abstract String getStringResponse();
}
