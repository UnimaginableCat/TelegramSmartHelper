package com.unimaginablecat.smarttelegramhelper.mapper;

import com.unimaginablecat.smarttelegramhelper.dto.BotUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.telegram.telegrambots.meta.api.objects.Update;

@Mapper(componentModel = "spring")
public interface BotUserMapper {
    @Mapping(target="telegramUserId", source = "update.message.from.id")
    @Mapping(target="username", source = "update.message.from.firstName")
    @Mapping(target="chatId", source = "update.message.chat.id")
    BotUserDto mapUpdateToBotUserDto(Update update);
}
