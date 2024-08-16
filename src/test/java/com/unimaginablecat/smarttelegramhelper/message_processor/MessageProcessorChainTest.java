package com.unimaginablecat.smarttelegramhelper.message_processor;

import com.unimaginablecat.smarttelegramhelper.message_processor.impl.CommandsMessageProcessor;
import com.unimaginablecat.smarttelegramhelper.message_processor.impl.StartMessageProcessor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageProcessorChainTest {
    private static MessageProcessorChain chain;
    private static MessageProcessorChain nextProcessor;
    private static Update update;
    @BeforeAll
    static void init() {
        chain = new StartMessageProcessor();
        nextProcessor = new CommandsMessageProcessor();
        chain.setNextProcessor(nextProcessor);
        update = getUpdate();

    }
    @Test
    @DisplayName("Set next processor")
    void setNextProcessor() {
        assertEquals(nextProcessor, chain.nextProcessor);
    }
    @Test
    @DisplayName("Process message with chain of two processors")
    void processMessage() {
        List<BotApiMethod<? extends Serializable>> responses = new ArrayList<>();
        List<BotApiMethod<? extends Serializable>> actualResponse = chain.processMessage(update, responses);
        assertEquals(responses, actualResponse);
    }

    private static Update getUpdate() {
        var chat = new Chat();
        chat.setId(2L);

        var message = new Message();
        message.setMessageId(1);
        message.setText("TEST MESSAGE");
        message.setChat(chat);


        var update = new Update();
        update.setMessage(message);
        return update;
    }
}