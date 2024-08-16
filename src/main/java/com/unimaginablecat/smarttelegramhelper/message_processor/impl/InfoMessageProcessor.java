package com.unimaginablecat.smarttelegramhelper.message_processor.impl;

import com.unimaginablecat.smarttelegramhelper.message_processor.MessageProcessorChain;
import com.unimaginablecat.smarttelegramhelper.message_processor.StringMessageProcessor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

/**
 * Class that handles /info command.
 */
public class InfoMessageProcessor extends StringMessageProcessor {
    private final static String RESPONSE = "Developed by UnimaginableCat, 2024.";

    @Override
    protected String getStringResponse() {
        return RESPONSE;
    }
}
