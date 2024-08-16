package com.unimaginablecat.smarttelegramhelper.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Note {
    private UUID id;
    private String name;
    private String data;
    private String userId;
    private String categoryId;
}
