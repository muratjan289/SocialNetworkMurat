CREATE TABLE Users_has_Users (
user_id INT NOT NULL,
friend_id INT NOT NULL,
PRIMARY KEY (user_id, friend_id),
CONSTRAINT fk_Users_has_Users_users FOREIGN KEY (user_id)
REFERENCES Users (id),
CONSTRAINT fk_Users_has_Users_Users1 FOREIGN KEY (friend_id)
REFERENCES Users (id)
);
