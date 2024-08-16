package com.unimaginablecat.smarttelegramhelper.dto;

import lombok.Data;

@Data
public class BotUserDto {
    private String username;
    private String telegramUserId;
    private String chatId;
}
