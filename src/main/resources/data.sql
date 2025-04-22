-- Boards 테이블에 게시글 2개 삽입
INSERT INTO boards (title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES ('첫 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 개화입니다.', '#개화', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES ('두 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 reality입니다.', '#reality', 'tester', NOW(), 'tester', NOW());

-- ArticleComments 테이블에 댓글 삽입 (boards_id가 외래키)
INSERT INTO article_comments (boards_id, content, created_at, created_by, modified_at, modified_by)
VALUES (1, '첫 번째 게시글의 첫 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');

INSERT INTO article_comments (boards_id, content, created_at, created_by, modified_at, modified_by)
VALUES (2, '두 번째 게시글의 첫 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');

INSERT INTO article_comments (boards_id, content, created_at, created_by, modified_at, modified_by)
VALUES (2, '두 번째 게시글의 두 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');
