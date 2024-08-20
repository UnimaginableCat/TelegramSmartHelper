package com.unimaginablecat.smarttelegramhelper.entity;

import com.unimaginablecat.smarttelegramhelper.enums.UserReplyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_reply", schema = "public", catalog = "TelegramSmartHelperBotDb")
public class UserReplyEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserReplyType type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private BotUserEntity botUserEntity;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @Column(name = "reply_message_id")
    private String replyMessageId;
}
