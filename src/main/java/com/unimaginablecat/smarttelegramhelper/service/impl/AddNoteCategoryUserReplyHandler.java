package com.unimaginablecat.smarttelegramhelper.service.impl;

import com.unimaginablecat.smarttelegramhelper.service.NoteCategoryService;
import com.unimaginablecat.smarttelegramhelper.service.UserReplyHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class AddNoteCategoryUserReplyHandler implements UserReplyHandler {
    private final NoteCategoryService noteCategoryService;
    @Override
    public void handleReply(Message incomingMessage) {

    }
}
