package com.unimaginablecat.smarttelegramhelper.message_processor.impl;

import com.unimaginablecat.smarttelegramhelper.message_processor.MessageProcessorChain;
import com.unimaginablecat.smarttelegramhelper.message_processor.StringMessageProcessor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

/**
 * Default command handler.
 */
public class DefaultMessageProcessor extends StringMessageProcessor {
    private static final String RESPONSE = "I don't know what to say...";

    @Override
    protected String getStringResponse() {
        return RESPONSE;
    }
}
