package com.unimaginablecat.smarttelegramhelper.message_processor;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
import java.util.List;

/**
 * Class that makes chains of responses.
 */
public abstract class MessageProcessorChain implements MessageProcessor {
    protected MessageProcessorChain nextProcessor;

    /**
     * Method that sets next message processor.
     *
     * @param nextProcessor next message processor.
     */
    public void setNextProcessor(MessageProcessorChain nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    /**
     * Method that processes messages and returns list of responses.
     * @param update incoming update.
     * @param responses list that contains responses.
     * @return list of responses.
     */
    public List<BotApiMethod<? extends Serializable>> processMessage(Update update, List<BotApiMethod<? extends Serializable>> responses) {
        BotApiMethod<? extends Serializable> response = getResponse(update);
        responses.add(response);

        if (nextProcessor != null) {
            return nextProcessor.processMessage(update, responses);
        } else {
            return responses;
        }
    }
}
