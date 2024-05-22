-- 기존 테이블이 있다면 삭제
DROP TABLE IF EXISTS my_images;
DROP TABLE IF EXISTS my_attractions;
DROP TABLE IF EXISTS my_trips;

-- trip 테이블 생성
CREATE TABLE my_trips (
    trip_id INT AUTO_INCREMENT,
    user_id VARCHAR(50) NOT NULL,
    trip_name VARCHAR(50),
    is_shared bool default false,
    PRIMARY KEY(trip_id),
    FOREIGN KEY (user_id) REFERENCES members(user_id) ON DELETE CASCADE
);

-- attraction 테이블 생성
CREATE TABLE my_attractions (
    attraction_id INT AUTO_INCREMENT,
    trip_id INT,
    attraction_name VARCHAR(50),
    attraction_description TEXT,
    PRIMARY KEY(attraction_id),
    FOREIGN KEY (trip_id) REFERENCES my_trips(trip_id) ON DELETE CASCADE
);

-- image 테이블 생성
CREATE TABLE my_images (
    image_id INT AUTO_INCREMENT,
    attraction_id INT,
    image LONGBLOB,
    image_description TEXT,
    latitude DOUBLE,
    longitude DOUBLE,
    date TIMESTAMP,
    PRIMARY KEY(image_id),
    FOREIGN KEY (attraction_id) REFERENCES my_attractions(attraction_id) ON DELETE CASCADE
);
