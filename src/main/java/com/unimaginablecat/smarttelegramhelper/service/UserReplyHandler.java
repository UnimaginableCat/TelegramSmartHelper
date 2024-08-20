package com.unimaginablecat.smarttelegramhelper.service;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface UserReplyHandler {
    void handleReply(Message incomingMessage);
}
