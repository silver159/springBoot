-- article 더미 데이터
INSERT INTO article(id, title, content) VALUES (1, '홍길동', '천재');
INSERT INTO article(id, title, content) VALUES (2, '임꺽정', '바보');
INSERT INTO article(id, title, content) VALUES (3, '장길산', '멍청이');
INSERT INTO article(id, title, content) VALUES (4, '당신의 인생 드라마는?', '댓글');
INSERT INTO article(id, title, content) VALUES (5, '당신의 소울 푸드는?', '댓글');
INSERT INTO article(id, title, content) VALUES (6, '당신의 취미는?', '댓글');

-- comment 더미 데이터
-- 4번 게시물의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (1, 4, '한꼬미', '동밲꽃 필 무렵');
INSERT INTO comment(id, article_id, nickname, body) VALUES (2, 4, '두꼬미', '소년심판');
INSERT INTO comment(id, article_id, nickname, body) VALUES (3, 4, '세꼬미', '덩게르크');

-- 5번 게시물의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (4, 5, '한꼬미', '연어');
INSERT INTO comment(id, article_id, nickname, body) VALUES (5, 5, '두꼬미', '타코와사비');
INSERT INTO comment(id, article_id, nickname, body) VALUES (6, 5, '세꼬미', '돈까스');

-- 6번 게시물의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (7, 6, '한꼬미', '등산');
INSERT INTO comment(id, article_id, nickname, body) VALUES (8, 6, '두꼬미', '바둑');
INSERT INTO comment(id, article_id, nickname, body) VALUES (9, 6, '세꼬미', '낚시');