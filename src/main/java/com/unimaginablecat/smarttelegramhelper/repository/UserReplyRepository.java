package com.unimaginablecat.smarttelegramhelper.repository;

import com.unimaginablecat.smarttelegramhelper.entity.UserReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserReplyRepository extends JpaRepository<UserReplyEntity, UUID> {
    UserReplyEntity findUserReplyEntityByReplyMessageId(String messageId);
}
