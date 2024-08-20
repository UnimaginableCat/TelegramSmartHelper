package com.unimaginablecat.smarttelegramhelper.service;

import com.unimaginablecat.smarttelegramhelper.dto.BotUserDto;
import com.unimaginablecat.smarttelegramhelper.entity.BotUserEntity;
import org.springframework.stereotype.Service;

/**
 * Service that works with {@link BotUserEntity}.
 */
public interface BotUserService {
    /**
     * Method that saves users to db. If user is already in db then return existing bot user entity.
     * @param botUserDto
     * @return saved or existing bot user entity.
     */
    BotUserEntity saveUser(BotUserDto botUserDto);

    /**
     * Method that finds user in db by telegram user id.
     * @param telegramUserId
     * @return
     * @throws Exception thrown if user is not found.
     */
    BotUserEntity findUserByTelegramId(String telegramUserId) throws Exception;
}
