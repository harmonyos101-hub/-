package com.example.mentalhealth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("mh_counselor")
public class CounselorEntity {
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  private Long userId;
  private String credential;
  private String specialty;
  private Boolean available;
  private BigDecimal pricePerSession;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
