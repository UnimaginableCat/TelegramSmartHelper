package com.unimaginablecat.smarttelegramhelper.bot_initializer;

import com.unimaginablecat.smarttelegramhelper.bot_controller.SmartHelperBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
@Component
public class BotInitializer {

    private final SmartHelperBot smartHelperBot;

    public BotInitializer(@Autowired SmartHelperBot smartHelperBot) {
        this.smartHelperBot = smartHelperBot;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void initBots() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(smartHelperBot);

    }

}
