package com.unimaginablecat.smarttelegramhelper.service.impl;

import com.unimaginablecat.smarttelegramhelper.entity.BotUserEntity;
import com.unimaginablecat.smarttelegramhelper.entity.UserReplyEntity;
import com.unimaginablecat.smarttelegramhelper.enums.UserReplyType;
import com.unimaginablecat.smarttelegramhelper.repository.UserReplyRepository;
import com.unimaginablecat.smarttelegramhelper.service.BotUserService;
import com.unimaginablecat.smarttelegramhelper.service.UserReplyHandler;
import com.unimaginablecat.smarttelegramhelper.service.UserReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserReplyServiceImpl implements UserReplyService {
    private final UserReplyRepository userReplyRepository;
    private final BotUserService botUserService;
    private final Map<UserReplyType, UserReplyHandler> userReplyHandlerMap; // todo
    @Override
    public UserReplyEntity addUserReply(Message sentMessage) {
        log.info("Adding user reply to db");
        Integer messageId = sentMessage.getMessageId();
        BotUserEntity userByTelegramId;
        try {
            userByTelegramId = botUserService.findUserByTelegramId(String.valueOf(sentMessage.getChatId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        UserReplyEntity userReplyEntity = UserReplyEntity.builder()
                .botUserEntity(userByTelegramId)
                .replyMessageId(String.valueOf(messageId))
                .isCompleted(false)
                .type(UserReplyType.ADD_NOTE_CATEGORY)
                .build();

        UserReplyEntity savedUserReply = userReplyRepository.save(userReplyEntity);

        log.info("Successfully added user reply to db with id: {}", savedUserReply.getId());
        return savedUserReply;
    }

    @Override
    public void processUserReply(Message incomingMessage) {
        Message replyToMessage = incomingMessage.getReplyToMessage();
        Integer messageId = replyToMessage.getMessageId();
        UserReplyEntity userReplyEntityByReplyMessageId = userReplyRepository.findUserReplyEntityByReplyMessageId(messageId.toString());

        if (!userReplyEntityByReplyMessageId.isCompleted()) {
            UserReplyType type = userReplyEntityByReplyMessageId.getType();
            UserReplyHandler userReplyHandler = userReplyHandlerMap.get(type);
            userReplyHandler.handleReply(incomingMessage);
        }
    }
}
