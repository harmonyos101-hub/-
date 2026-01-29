package com.example.mentalhealth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("mh_appointment")
public class AppointmentEntity {
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  private Long userId;
  private Long counselorId;
  private LocalDateTime startTime;
  private String channel;
  private String status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
