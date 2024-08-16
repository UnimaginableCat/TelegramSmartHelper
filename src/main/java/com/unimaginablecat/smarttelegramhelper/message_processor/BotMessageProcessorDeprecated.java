package com.unimaginablecat.smarttelegramhelper.message_processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

/**
 * Message processor interface.
 */
public interface BotMessageProcessorDeprecated {
    Logger log = LoggerFactory.getLogger(BotMessageProcessorDeprecated.class);
//    /**
//     * Processes message that was sent to bot.
//     * @param update
//     */
//    SendMessage processCommand(Update update);

    /**
     * Returns bot command.
     * @return bot command.
     */
    String getCommand();

    String getResponseMessage();

    /**
     * Processes message that was sent to bot.
     * @param update
     */
    default SendMessage processCommand(Update update) {
        long chatId = getChatId(update);
        return prepareSendMessage(chatId, getResponseMessage());
    }

    /**
     * Returns chatId from update.
     * @param update
     * @return chat id.
     */
    default long getChatId(Update update) {
        return update.getMessage()
                .getChat()
                .getId();
    }

    /**
     * Method that prepares send message object.
     * @param chatId chatId to reply
     * @param text text in reply
     * @return {@link SendMessage} object.
     */
    default SendMessage prepareSendMessage(long chatId, String text) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();
        try {
            sendMessage.validate();
        } catch (TelegramApiValidationException e) {
            log.error(e.toString());
        }
        return sendMessage;
    }
}
