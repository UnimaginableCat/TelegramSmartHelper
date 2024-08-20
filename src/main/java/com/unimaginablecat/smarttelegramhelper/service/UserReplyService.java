package com.unimaginablecat.smarttelegramhelper.service;

import com.unimaginablecat.smarttelegramhelper.entity.UserReplyEntity;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Service that works with {@link UserReplyEntity} entity.
 */
public interface UserReplyService {
    /**
     * Method that adds user reply to db.
     * @param sentMessage
     * @return
     */
    UserReplyEntity addUserReply(Message sentMessage);
    void processUserReply(Message incomingMessage);
}
