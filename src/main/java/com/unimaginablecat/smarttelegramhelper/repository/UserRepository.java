package com.unimaginablecat.smarttelegramhelper.repository;

import com.unimaginablecat.smarttelegramhelper.entity.BotUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<BotUserEntity, UUID> {
}
