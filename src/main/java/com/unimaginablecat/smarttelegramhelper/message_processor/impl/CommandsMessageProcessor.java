package com.unimaginablecat.smarttelegramhelper.message_processor.impl;

import com.unimaginablecat.smarttelegramhelper.message_processor.MessageProcessorChain;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommandsMessageProcessor extends MessageProcessorChain {
    @Override
    public BotApiMethod<? extends Serializable> getResponse(Update update) {
        BotCommand startCommand = BotCommand
                .builder()
                .command("/start")
                .description("Start bot")
                .build();

        BotCommand infoCommand = BotCommand
                .builder()
                .command("/info")
                .description("Info about bot")
                .build();
        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(startCommand);
        botCommandList.add(infoCommand);

        return SetMyCommands.builder()
                .commands(botCommandList)
                .languageCode("en")
                .build();
    }
}
