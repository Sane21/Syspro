CREATE TABLE IF NOT EXISTS users ( 
	user_id VARCHAR(32) PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	password VARCHAR(2058) NOT NULL,
	profile VARCHAR(1024)
);

CREATE TABLE IF NOT EXISTS book ( 
	book_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(1024) NOT NULL,
	author VARCHAR(1024) NOT NULL,
	ISBN BIGINT NOT NULL,
	last_edit_date DATETIME,
	editor_id VARCHAR(32) REFERENCES users 
);

CREATE TABLE IF NOT EXISTS comments ( 
	comment_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	target_book_id BIGINT REFERENCES book,
	edit_user_id VARCHAR(32) REFERENCES users,
	content VARCHAR(1024) NOT NULL,
	published_date DATETIME NOT NULL
);
