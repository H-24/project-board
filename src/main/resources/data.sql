-- Boards 테이블에 게시글 2개 삽입
INSERT INTO boards (id, title, content, hashtag, created_at, created_by, modified_at, modified_by)
VALUES (1, '첫 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 개화입니다.', '#개화', NOW(), 'tester', NOW(), 'tester');

INSERT INTO boards (id, title, content, hashtag, created_at, created_by, modified_at, modified_by)
VALUES (2, '두 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 reality입니다.', '#reality', NOW(), 'tester', NOW(), 'tester');

-- ArticleComments 테이블에 댓글 삽입 (boards_id가 외래키)
INSERT INTO article_comments (id, boards_id, content, created_at, created_by, modified_at, modified_by)
VALUES (1, 1, '첫 번째 게시글의 첫 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');

INSERT INTO article_comments (id, boards_id, content, created_at, created_by, modified_at, modified_by)
VALUES (2, 2, '두 번째 게시글의 첫 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');

INSERT INTO article_comments (id, boards_id, content, created_at, created_by, modified_at, modified_by)
VALUES (3, 2, '두 번째 게시글의 두 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');
