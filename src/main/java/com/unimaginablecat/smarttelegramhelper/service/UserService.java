package com.unimaginablecat.smarttelegramhelper.service;

import com.unimaginablecat.smarttelegramhelper.entity.BotUserEntity;
import com.unimaginablecat.smarttelegramhelper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public BotUserEntity saveUser() {
        return null;
    }
}
