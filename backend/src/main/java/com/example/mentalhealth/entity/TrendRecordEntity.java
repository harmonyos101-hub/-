package com.example.mentalhealth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("mh_trend_record")
public class TrendRecordEntity {
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  private Long userId;
  private Integer stressScore;
  private Integer moodScore;
  private String riskLevel;
  private LocalDateTime recordDate;
}
