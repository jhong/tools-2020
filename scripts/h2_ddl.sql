
CREATE TABLE IF NOT EXISTS ZZ_DATA (
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
	TITLE VARCHAR(300) NULL COMMENT '제목',
	URL VARCHAR(300) NULL COMMENT 'URL',
	DATA_TYPE VARCHAR(100) NULL COMMENT '분류',
	SOURCE VARCHAR(100) NULL COMMENT '출처',
	CONTENT CLOB NOT NULL COMMENT '내용',
	HIT_COUNT INT NOT NULL COMMENT '조회수',
	PUB_DATE DATETIME NOT NULL COMMENT '게재일',
	COL_DATE DATETIME NOT NULL COMMENT '수집일',
	USE_YN CHAR(1) NOT NULL COMMENT '사용여부'
);
