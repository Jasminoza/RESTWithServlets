CREATE TABLE IF NOT EXISTS users_files (
    id          INT AUTO_INCREMENT NOT NULL,
    user_id     INT NOT NULL,
    file_id     INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_file_id FOREIGN KEY (file_id) REFERENCES files(id) ON DELETE CASCADE ON UPDATE CASCADE
);