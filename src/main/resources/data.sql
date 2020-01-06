INSERT INTO USERS(email,password,user_name,last_name,active) VALUES ('elif@elif','123','elif','serttaş',1);
INSERT INTO USERS_DETAIL(description,user_id) VALUES ('Bu benim description yazımdır.','1');
INSERT INTO ROLE(role) VALUES ('USER');
INSERT INTO ROLE(role) VALUES ('ADMIN');
INSERT INTO USER_ROLE(user_id,role_id) VALUES (1,1);
INSERT INTO USER_ROLE(user_id,role_id) VALUES (1,2);