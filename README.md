# Social Media Backend

Social Media Backend built using Spring Boot with MySQL database.

## Table of Content

- [Table of Content](#table-of-content)
    - [Set-Up](#set-up)

## Set-up

```mysql
CREATE TABLE users ( 
id int(11) NOT NULL AUTO_INCREMENT,
name varchar(40),
email varchar(400),
username varchar(40),
password varchar(4000),
role varchar(40),
PRIMARY KEY (id)
);

CREATE TABLE posts ( 
id int(11) NOT NULL AUTO_INCREMENT,
type varchar(20),
caption varchar(4000),
media_src varchar(4000),
user_id int(11),
created_by varchar(40),
created_dt DATETIME,
modified_by varchar(40),
modified_dt DATETIME,
PRIMARY KEY (id),

KEY `FK_USER_ID_idx` (`user_id`),

CONSTRAINT `FK_USER`
FOREIGN KEY (`user_id`)
REFERENCES `users` (`id`)

ON DELETE NO ACTION ON UPDATE NO ACTION
);
  

CREATE TABLE posts_metrics( 
id int(11) NOT NULL AUTO_INCREMENT,
views int(11),
post_id int(11),
created_by varchar(40),
created_dt DATETIME,
modified_by varchar(40),
modified_dt DATETIME,
PRIMARY KEY (id),

KEY `FK_POST_ID_idx` (`post_id`),

CONSTRAINT `FK_POST`
FOREIGN KEY (`post_id`)
REFERENCES `posts` (`id`)

ON DELETE NO ACTION ON UPDATE NO ACTION
);
```