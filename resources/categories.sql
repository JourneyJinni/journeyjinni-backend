-- 기존 테이블이 있다면 삭제
DROP TABLE IF EXISTS categories;

-- 새로운 테이블 생성
CREATE TABLE categories (
    category_code INT PRIMARY KEY,
    category_name VARCHAR(50)
);

-- 데이터 삽입
INSERT INTO categories (category_code, category_name) VALUES
(12, '관광지'),
(14, '문화시설'),
(15, '행사/공연/축제'),
(25, '여행코스'),
(28, '레포츠'),
(32, '숙박'),
(38, '쇼핑'),
(39, '음식점');

-- 변경사항 저장
COMMIT;
