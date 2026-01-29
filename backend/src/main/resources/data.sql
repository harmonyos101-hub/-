INSERT INTO mh_user (id, name, email, phone, role, status, created_at, updated_at)
VALUES
  (1001, '张三', 'user@example.com', '13800000000', 'USER', 'ACTIVE', NOW(), NOW()),
  (2001, '王老师', 'counselor@example.com', '13900000000', 'COUNSELOR', 'ACTIVE', NOW(), NOW());

INSERT INTO mh_counselor (id, user_id, credential, specialty, available, price_per_session, created_at, updated_at)
VALUES
  (3001, 2001, '国二', '焦虑疏导', 1, 299.00, NOW(), NOW());

INSERT INTO mh_article (id, title, category, content, view_count, published, created_at)
VALUES
  (4001, '压力管理技巧', '压力', '记录每日压力源并制定舒缓计划。', 1200, 1, NOW()),
  (4002, '睡眠与情绪', '睡眠', '保持规律作息与睡前放松。', 980, 1, NOW());

INSERT INTO mh_community_post (id, user_id, topic, content, anonymous, status, created_at)
VALUES
  (5001, 1001, '如何缓解焦虑', '欢迎分享有效的方法。', 1, 'OPEN', NOW());

INSERT INTO mh_assessment (id, user_id, scale, score, level, summary, created_at)
VALUES
  (6001, 1001, 'GAD-7', 8, 'Mild', '轻度焦虑', NOW());

INSERT INTO mh_appointment (id, user_id, counselor_id, start_time, channel, status, created_at, updated_at)
VALUES
  (7001, 1001, 3001, DATE_ADD(NOW(), INTERVAL 3 DAY), '视频', 'PENDING', NOW(), NOW());

INSERT INTO mh_ai_chat_log (id, user_id, message, reply, crisis_flag, created_at)
VALUES
  (8001, 1001, '最近睡不好，很焦虑。', '建议尝试睡前放松练习，必要时预约咨询师。', 0, NOW());

INSERT INTO mh_emotion_record (id, user_id, calm_score, anxious_score, sad_score, summary, created_at)
VALUES
  (9001, 1001, 0.72, 0.18, 0.10, '情绪整体平稳，存在轻度焦虑倾向。', NOW());

INSERT INTO mh_trend_record (id, user_id, stress_score, mood_score, risk_level, record_date)
VALUES
  (10001, 1001, 30, 78, 'low', NOW());
