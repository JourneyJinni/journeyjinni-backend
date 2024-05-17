CREATE TABLE board (
       article_no INT AUTO_INCREMENT PRIMARY KEY,
       user_id VARCHAR(50) NOT NULL,
       subject VARCHAR(200) NOT NULL,
       content TEXT NOT NULL,
       hit INT DEFAULT 0,
       register_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       FOREIGN KEY (user_id) REFERENCES members(user_id)
);