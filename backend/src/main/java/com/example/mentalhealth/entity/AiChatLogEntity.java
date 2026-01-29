package com.example.mentalhealth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("mh_ai_chat_log")
public class AiChatLogEntity {
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  private Long userId;
  private String message;
  private String reply;
  private Boolean crisisFlag;
  private LocalDateTime createdAt;
}
