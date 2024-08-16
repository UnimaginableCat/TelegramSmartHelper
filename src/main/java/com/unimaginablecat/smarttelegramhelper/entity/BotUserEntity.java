package com.unimaginablecat.smarttelegramhelper.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Bot user entity. Contains user information.
 */
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bot_user", schema = "public", catalog = "TelegramSmartHelperBotDb")
public class BotUserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "username")
    private String username;
    @Column(name = "telegram_user_id")
    private String telegramUserId;
    @Column(name = "chat_id")
    private String chatId;

}
