-- 개행이 없어야 함 (명령어 하나는 무조건 한줄)

INSERT INTO `main_code` (`main_code_id`, `cd_name`, `description`) VALUES (1, 'role', '권한');
INSERT INTO `sub_code` (`sub_code_id`, `main_code_id`, `val`, `description`) VALUES (1, 1, 'ROLE_USER', '일반 사용자'), (2, 1, 'ROLE_ADMIN', '관리자');


INSERT INTO `user` (`user_id`, `provider_type`, `uid`, `upw`, `nm`, `pic`, `created_at`, `updated_at`) VALUES (1, 4, 'user1', '$2a$10$ug96yjVFcI.71PNnOZ6RD.ydayprtvz0xbDBUKHOdNzTCJztqWIDS', '일반사용자', '363403f9-8d51-4a5c-a891-abaf8b317910.jpg', '2024-05-03 14:35:08', '2024-07-15 12:47:05'), (2, 4, 'user2', '$2a$10$ug96yjVFcI.71PNnOZ6RD.ydayprtvz0xbDBUKHOdNzTCJztqWIDS', '관리자', '111fad30-256c-484b-aea3-bca8e3f434aa.jpg', '2024-05-20 10:34:56', '2024-07-15 12:41:04'), (3, 4, 'user3', '$2a$10$ug96yjVFcI.71PNnOZ6RD.ydayprtvz0xbDBUKHOdNzTCJztqWIDS', '사용자_관리자', '81695b24-7863-407c-8f40-ca0a3be511ed.jpg', '2024-05-20 10:33:48', '2024-07-15 12:41:06');

INSERT INTO `user_role` (`user_id`, `role_cd`, `role`) VALUES (1, 1, 'ROLE_USER'), (2, 2, 'ROLE_ADMIN'), (3, 1, 'ROLE_USER'), (3, 2, 'ROLE_ADMIN');

INSERT INTO `feed` (`feed_id`, `writer_id`, `contents`, `location`, `created_at`, `updated_at`) VALUES (1, 1, '아무거나', '대구시 시청', '2024-05-07 10:56:29', '2024-05-07 10:56:29'), (2, 2, '허허', '대구 중구', '2024-05-07 11:36:19', '2024-05-07 11:36:19');

INSERT INTO `feed_comment` (`created_at`, `feed_id`, `updated_at`, `user_id`, `comment`) VALUES ('2024-07-30 10:10:21.000000', 1, '2024-07-30 10:10:20.000000', 1, '1번째'), ('2024-07-30 10:10:21.000000', 1, '2024-07-30 10:10:20.000000', 2, '2번째'), ('2024-07-30 10:10:21.000000', 1, '2024-07-30 10:10:20.000000', 2, '3번째'), ('2024-07-30 10:10:21.000000', 1, '2024-07-30 10:10:20.000000', 2, '4번째'), ('2024-07-30 10:10:21.000000', 1, '2024-07-30 10:10:20.000000', 2, '5번째'), ('2024-07-30 10:10:21.000000', 2, '2024-07-30 10:10:20.000000', 3, '1번째'), ('2024-07-30 10:10:21.000000', 2, '2024-07-30 10:10:20.000000', 3, '2번째');
