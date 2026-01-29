package com.example.mentalhealth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("mh_assessment")
public class AssessmentEntity {
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  private Long userId;
  private String scale;
  private Integer score;
  private String level;
  private String summary;
  private LocalDateTime createdAt;
}
