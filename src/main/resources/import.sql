INSERT INTO auth_role VALUES (1, 'SUPER_ADMIN', 'This user has ultimate rights for everything');
INSERT INTO auth_role VALUES (2, 'ADMIN', 'This user has admin rights for administrative work');
INSERT INTO auth_role VALUES (3, 'USER', 'This user has access to billing, after login - normal user');

CREATE TABLE auth_user_role (auth_user_id int(11) NOT NULL,auth_role_id int(11) NOT NULL,PRIMARY KEY (auth_user_id, auth_role_id),KEY FK_user_role (auth_role_id),CONSTRAINT FK_auth_user FOREIGN KEY (auth_user_id) REFERENCES auth_user (auth_user_id),CONSTRAINT FK_auth_user_role FOREIGN KEY (auth_role_id) REFERENCES auth_role (auth_role_id));

insert into auth_user (auth_user_id, first_name, last_name, username, password, status) values (1, 'sandeep', 'mk', 'sandeepmk', '$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i', 'VERIFIED');
insert into auth_user_role (auth_user_id, auth_role_id) values (1, 1);
insert into auth_user_role (auth_user_id, auth_role_id) values (1, 2);
insert into auth_user_role (auth_user_id, auth_role_id) values (1, 3);