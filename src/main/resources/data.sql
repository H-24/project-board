-- 테스트 계정
-- TODO: 테스트용이지만 비밀번호가 노출된 데이터 세팅. 개선하는 것이 좋을 지 고민해 보자.
INSERT INTO user_account (user_id, user_password, nickname, email, memo, created_at, created_by, modified_at, modified_by) values
    ('win', 'pyj0101', 'win', 'winter@mail.com', 'I am win.', now(), 'win', now(), 'win');

INSERT INTO user_account (user_id, user_password, nickname, email, memo, created_at, created_by, modified_at, modified_by) values
    ('jake', 'pyj1115', 'jake', 'jake@mail.com', 'I am jake.', now(), 'jake', now(), 'jake');

-- Boards 테이블에 게시글 2개 삽입
INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '첫 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 개화입니다.', '#개화', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (2, '두 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Reality입니다.', '#reality', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '세 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Bring the heat back입니다.', '#bringthehitback', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (2, '네 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 One in a billion입니다.', '#oneinabillion', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '다섯 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Whiplash입니다.', '#whiplash', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '여섯 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Supernova입니다.', '#supernova', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (2, '일곱 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Loose입니다.', '#loose', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '여덟 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 잉걸입니다.', '#잉걸', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '아홉 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Lucky입니다.', '#lucky', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '열 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Candy입니다.', '#candy', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '열 한 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '열 두 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Silly Dance입니다.', '#sillydance', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '열 세 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Beatbox입니다.', '#beatbox', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '열 네 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Baby입니다.', '#baby', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '열 다섯 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 As It Was입니다.', '#asitwas', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '열 여섯 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Sparkle입니다.', '#sparkle', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '열 일곱 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 21입니다.', '#21', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '열 여덟 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Drowning입니다.', '#drowning', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '열 아홉 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Crazy입니다.', '#crazy', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '스무 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Betcha입니다.', '#betcha', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '스물 한 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 자장가입니다.', '#자장가', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '스물 두 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 무릎입니다.', '#무릎', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '스물 세 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '스물 네 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '스물 다섯 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '스물 여섯 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '스물 일곱 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '스물 여덟 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '스물 아홉 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '서른 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '서른 한 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '서른 두 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '서른 세 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '서른 네 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '서른 다섯 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '서른 여섯 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '서른 일곱 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '서른 여덟 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '서른 아홉 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '마흔 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '마흔 한 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '마흔 두 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '마흔 세 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '마흔 네 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '마흔 다섯 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '마흔 여섯 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '마흔 일곱 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '마흔 여덟 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '마흔 아홉 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '쉰 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '쉰 한 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '쉰 둘 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '쉰 셋 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '쉰 넷 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '쉰 다섯 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '쉰 여섯 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '쉰 일곱 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '쉰 여덟 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '쉰 아홉 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '예순 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '예순 한 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '예순 두 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '예순 세 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '예순 네 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '예순 다섯 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '예순 여섯 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '예순 일곱 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '예순 여덟 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '예순 아홉 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '일흔 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (1, '일흔 한 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

INSERT INTO boards (user_account_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
VALUES (2, '일흔 두 번째 게시글', '테스트용 내용입니다. 지금 듣고 있는 노래는 Blue입니다.', '#blue', 'tester', NOW(), 'tester', NOW());

-- ArticleComments 테이블에 댓글 삽입 (boards_id가 외래키)
INSERT INTO article_comments (boards_id, user_account_id, content, created_at, created_by, modified_at, modified_by)
VALUES (1, 1, '첫 번째 게시글의 첫 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');

INSERT INTO article_comments (boards_id, user_account_id, content, created_at, created_by, modified_at, modified_by)
VALUES (2, 1, '두 번째 게시글의 첫 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');

INSERT INTO article_comments (boards_id, user_account_id, content, created_at, created_by, modified_at, modified_by)
VALUES (2, 1, '두 번째 게시글의 두 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');

INSERT INTO article_comments (boards_id, user_account_id, content, created_at, created_by, modified_at, modified_by)
VALUES (3, 1, '세 번째 게시글의 첫 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');

INSERT INTO article_comments (boards_id, user_account_id, content, created_at, created_by, modified_at, modified_by)
VALUES (4, 1, '네 번째 게시글의 첫 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');

INSERT INTO article_comments (boards_id, user_account_id, content, created_at, created_by, modified_at, modified_by)
VALUES (4, 1, '네 번째 게시글의 두 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');

INSERT INTO article_comments (boards_id, user_account_id, content, created_at, created_by, modified_at, modified_by)
VALUES (7, 1, '일곱 번째 게시글의 첫 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');

INSERT INTO article_comments (boards_id, user_account_id, content, created_at, created_by, modified_at, modified_by)
VALUES (8, 1, '여덟 번째 게시글의 첫 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');

INSERT INTO article_comments (boards_id, user_account_id, content, created_at, created_by, modified_at, modified_by)
VALUES (10, 1, '열 번째 게시글의 첫 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');

INSERT INTO article_comments (boards_id, user_account_id, content, created_at, created_by, modified_at, modified_by)
VALUES (11, 1, '열한 번째 게시글의 첫 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');

INSERT INTO article_comments (boards_id, user_account_id, content, created_at, created_by, modified_at, modified_by)
VALUES (11, 1, '열한 번째 게시글의 두 번째 댓글입니다.', NOW(), 'tester', NOW(), 'tester');
