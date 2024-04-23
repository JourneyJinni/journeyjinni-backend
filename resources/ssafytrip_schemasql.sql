-- sido 테이블
CREATE TABLE IF NOT EXISTS `sido` (
	`sido_code`	INT	NOT NULL,
	`sido_name`	VARCHAR(30)	NULL,
	PRIMARY KEY (`sido_code`)
);

-- gugun 테이블
CREATE TABLE IF NOT EXISTS `gugun` (
	`gugun_code`	INT	NOT NULL,
	`sido_code`	INT	NOT NULL,
	`gugun_name`	VARCHAR(30)	NULL,
	PRIMARY KEY (`gugun_code`, `sido_code`),
	FOREIGN KEY (`sido_code`) REFERENCES `sido` (`sido_code`)
);

-- attraction_info 테이블
CREATE TABLE IF NOT EXISTS `attraction_info` (
	`content_id`	INT	NOT NULL,
	`content_type_id`	INT	NULL,
	`title`	VARCHAR(100)	NULL,
	`addr1`	VARCHAR(100)	NULL,
	`addr2`	VARCHAR(50)	NULL,
	`zipcode`	VARCHAR(50)	NULL,
	`tel`	VARCHAR(50)	NULL,
	`first_image`	VARCHAR(200)	NULL,
	`first_image2`	VARCHAR(200)	NULL,
	`readcount`	INT	NULL,
	`sido_code`	INT	NULL,
	`gugun_code`	INT	NULL,
	`latitude`	DECIMAL(20,17)	NULL,
	`longitude`	DECIMAL(20,17)	NULL,
	`mlevel`	VARCHAR(2)	NULL,
	PRIMARY KEY (`content_id`),
	INDEX `attraction_to_content_type_id_fk_idx` (`content_type_id`),
	INDEX `attraction_to_sido_code_fk_idx` (`sido_code`),
	INDEX `attraction_to_gugun_code_fk_idx` (`gugun_code`),
	FOREIGN KEY (`sido_code`) REFERENCES `sido` (`sido_code`),
	FOREIGN KEY (`gugun_code`, `sido_code`) REFERENCES `gugun` (`gugun_code`, `sido_code`)
);

-- attraction_description 테이블
CREATE TABLE IF NOT EXISTS `attraction_description` (
	`content_id`	INT	NOT NULL,
	`homepage`	VARCHAR(100)	NULL,
	`overview`	VARCHAR(10000)	NULL,
	`telname`	VARCHAR(45)	NULL,
	PRIMARY KEY (`content_id`),
	FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`)
);

-- attraction_detail 테이블
CREATE TABLE IF NOT EXISTS `attraction_detail` (
	`content_id`	INT	NOT NULL,
	`cat1`	VARCHAR(3)	NULL,
	`cat2`	VARCHAR(5)	NULL,
	`cat3`	VARCHAR(9)	NULL,
	`created_time`	VARCHAR(14)	NULL,
	`modified_time`	VARCHAR(14)	NULL,
	`booktour`	VARCHAR(5)	NULL,
	PRIMARY KEY (`content_id`),
	FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`)
);

-- members 테이블
CREATE TABLE IF NOT EXISTS `members` (
	`user_id`	VARCHAR(16)	NOT NULL,
	`user_name`	VARCHAR(20)	NOT NULL,
	`user_password`	VARCHAR(16)	NOT NULL,
	`email_id`	VARCHAR(20)	NULL,
	`email_domain`	VARCHAR(45)	NULL,
	`join_date`	TIMESTAMP	NOT NULL,
	PRIMARY KEY (`user_id`)
);

DROP TABLE IF EXISTS `board`;

-- board 테이블
CREATE TABLE IF NOT EXISTS `board` (
	`article_no`	INT	NOT NULL,
	`user_id`	VARCHAR(20)	NOT NULL,
	`subject`	VARCHAR(255)	NOT NULL,
	`content`	TEXT	NULL,
	`hit`	INT	NULL	DEFAULT '0',
	`register_time`	TIMESTAMP	NULL,
	PRIMARY KEY (`article_no`),
	INDEX `board_to_memebers_member_id_fk_idx` (`user_id`),
	FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
);

-- memberattraction 테이블 삭제
DROP TABLE IF EXISTS `memberattraction`;

-- memberattraction 테이블 재생성
CREATE TABLE IF NOT EXISTS `memberattraction` (
	`content_id`	INT	NOT NULL,
	`user_id`	VARCHAR(16)	NOT NULL,
	FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`),
	FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
);

DROP TABLE IF EXISTS `attraction_metadata`;
-- attraction_metadata 테이블
CREATE TABLE IF NOT EXISTS `attraction_metadata` (
	`content_id`	INT	NOT NULL,
	`user_id`	VARCHAR(16)	NOT NULL,
	`score`	INT	NULL,
	`review_text`	TEXT	NULL,
	PRIMARY KEY (`content_id`),
	FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`),
	FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
);

DROP TABLE IF EXISTS `attraction_course`;
-- attraction_course 테이블
CREATE TABLE IF NOT EXISTS `attraction_course` (
	`course_id`	INT	NOT NULL,
	`description`	TEXT	NULL,
	`user_id`	VARCHAR(16)	NOT NULL,
	`hit`	INT	NULL,
	`register_time`	TIMESTAMP	NULL,
	`subject`	VARCHAR(16)	NULL,
	PRIMARY KEY (`course_id`),
	INDEX `attraction_course_to_user_id_idx` (`user_id`),
	FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
);

DROP TABLE IF EXISTS `course_content`;
-- course_content 테이블
CREATE TABLE IF NOT EXISTS `course_content` (
	`course_id`	INT	NOT NULL,
	`content_id`	INT	NOT NULL,
	PRIMARY KEY (`course_id`, `content_id`),
	FOREIGN KEY (`course_id`) REFERENCES `attraction_course` (`course_id`),
	FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`)
);