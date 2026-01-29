package com.example.mentalhealth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("mh_article")
public class ArticleEntity {
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  private String title;
  private String category;
  private String content;
  private Integer viewCount;
  private Boolean published;
  private LocalDateTime createdAt;
}
