---- drop ----
DROP TABLE IF EXISTS `posts`;
DROP TABLE IF EXISTS `topics`;

---- create ----
create table IF not exists `topics`
(
 id               INT(20) AUTO_INCREMENT,
 name             VARCHAR(20) NOT NULL,
 created_at       Datetime  default current_timestamp,
 updated_at       Timestamp default current_timestamp on update current_timestamp,
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table IF not exists `posts`
(
 id               INT(20) AUTO_INCREMENT,
 topic_id         INT(20),
 name             VARCHAR(20) NOT NULL,
 body             VARCHAR(50) NOT NULL,
 created_at       Datetime  default current_timestamp,
 updated_at       Timestamp default current_timestamp on update current_timestamp,
    PRIMARY KEY (id),
    CONSTRAINT fk_topic_id
      FOREIGN KEY (topic_id) 
      REFERENCES topics (id)
      ON DELETE RESTRICT ON UPDATE RESTRICT
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

