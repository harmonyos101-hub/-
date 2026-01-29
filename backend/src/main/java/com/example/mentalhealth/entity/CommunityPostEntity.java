package com.example.mentalhealth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("mh_community_post")
public class CommunityPostEntity {
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  private Long userId;
  private String topic;
  private String content;
  private Boolean anonymous;
  private String status;
  private LocalDateTime createdAt;
}
