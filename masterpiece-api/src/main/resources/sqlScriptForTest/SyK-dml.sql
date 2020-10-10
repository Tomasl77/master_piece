/* 
DML script for teamsharing database
Script can be used with mysql

Last update : 2020-09-18

*/

USE teamsharingdev;

INSERT INTO `user_info` (email)
	VALUES
    ('lily@gmail.com'),
    ('tomas@gmail.com'),
    ('johanna@gmail.com');

INSERT INTO `user` (account_non_expired, password, username, account_non_locked, credentials_non_expired, enabled, user_info_id)
	VALUES 
    ('T','$2a$10$JbSdgniSs9PoNJM3XN6qUuS9s6uVJwpS1fLIOddNemQgx8FuUw67O','Tomas','T','T','T', 2), -- password = Totototo9!
	('T','$2a$10$PxZEkHGLpGgeV8mO8ehxz..dGlyxwFo3FgTGfdC/2LqOYd8R4DI/a','Johanna','T','T','T', 3),  -- password = Joanhime77!
    ('T','$2a$10$tToGMTh3vyXByVqOQHdk9uqbip3wm5rZz1GLbZSoaBB1ileL5w8qi','Lily','T','T','T', 1);  -- password = Lily2709!

INSERT INTO `role`(code, default_role) 
    VALUES 
    ('ROLE_USER','T'), 
    ('ROLE_ADMIN','F');

COMMIT;

SET @Tomas = (SELECT id  FROM teamsharingdev.user WHERE username= 'Tomas');
SET @Johanna = (SELECT id  FROM teamsharingdev.user WHERE username = 'Johanna');
SET @Lily = (SELECT id  FROM teamsharingdev.user WHERE username = 'Lily');
SET @user = (SELECT id FROM teamsharingdev.role WHERE code = 'ROLE_USER');
SET @admin = (SELECT id FROM teamsharingdev.role WHERE code = 'ROLE_ADMIN');

INSERT INTO `user_role` (user_id, role_id) 
    VALUES 
    (@Tomas,@user),
    (@Lily,@user),
    (@Johanna,@admin),
    (@Lily,@admin);

COMMIT;

INSERT INTO `subject` (category, description, title, total_vote, requester_id) 
    VALUES 
    ('FRONTEND', 'My knowledge of Angular modals is nearly zero. I need someone to help me', 'Angular 8 Modals', 3, @Tomas),
    ('BACKEND', 'JPQL, Derived queries... Someone could tell me how to request database properly from my springboot app, please?', 'Spring database requests', 2, @Lily),
    ('BACKEND', 'I need some serious stuff on how to handle errors in my spring app. Anyone\'s good on this?', 'Spring errors handling', 2, @Johanna),
    ('DATABASE', 'I need some basics on mysql join queries', 'Mysql queries', 1, @Johanna),
    ('FRONTEND', 'Angular testing methods', 'Angular 8Test', 3, @Tomas),
    ('BACKEND', 'Spring data jpa', 'Spring', 5, @Lily),
    ('BACKEND', 'Junit 5 is a revolution. I need some skills ! ', 'JUnit 5', 8, @Johanna),
    ('DATABASE', 'MySql 8. Need some tips on queries', 'Mysql 8', 0, @Johanna),
    ('FRONTEND', 'How to make a pagination in Angular client-side', 'Angular 8 pagination', 13, @Tomas),
    ('FRONTEND', 'I want to know stuff about redux', 'React/Redux', 2, @Tomas),
    ('FRONTEND', 'I need some help with Ajax.', 'Ajax', 7, @Lily),
    ('BACKEND', 'How effective is node.js? Someone can tell me more about?', 'Node JS', 10, @Lily),
	('RIFT', 'Someone please explain me MDX call', 'Rift MDX', 9, @Johanna),
    ('OTHER', 'How to improve skills by making a good tech watch', 'Misc', 5, @Johanna);

COMMIT;