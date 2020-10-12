/* 
DML script for teamsharing database
Script can be used with mysql

Last update : 2020-09-18

*/
USE teamsharingtest;

DELETE FROM `subject`;
DELETE FROM user_role;
DELETE FROM `user`;
DELETE FROM user_profile;
DELETE FROM `role`;


INSERT INTO `role`(code, default_role) 
    VALUES 
    ('ROLE_USER','T'), 
    ('ROLE_ADMIN','F');

COMMIT;

INSERT INTO `user` (account_non_expired, password, username, account_non_locked, credentials_non_expired, enabled)
	VALUES 
    ('T','$2a$10$JbSdgniSs9PoNJM3XN6qUuS9s6uVJwpS1fLIOddNemQgx8FuUw67O','Tomas','T','T','T'), -- password = Totototo9!
	('T','$2a$10$PxZEkHGLpGgeV8mO8ehxz..dGlyxwFo3FgTGfdC/2LqOYd8R4DI/a','Johanna','T','T','T'),  -- password = Joanhime77!
    ('T','$2a$10$tToGMTh3vyXByVqOQHdk9uqbip3wm5rZz1GLbZSoaBB1ileL5w8qi','Lily','T','T','T'),  -- password = Lily2709!
    ('T','$2a$10$fGGDHcgG3JF6e3kX12C9JO8jkWF6wFJd.eZQQOEoM9479.Ypy.Fbe','Benjamin', 'T','T','T'); -- password = Benjamin9!

COMMIT; 

SET @Tomas = (SELECT id  FROM teamsharingdev.user WHERE username= 'Tomas');
SET @Johanna = (SELECT id  FROM teamsharingdev.user WHERE username = 'Johanna');
SET @Lily = (SELECT id  FROM teamsharingdev.user WHERE username = 'Lily');
SET @Benjamin = (SELECT id FROM teamsharingdev.user WHERE username = 'Benjamin');
SET @user = (SELECT id FROM teamsharingdev.role WHERE code = 'ROLE_USER');
SET @admin = (SELECT id FROM teamsharingdev.role WHERE code = 'ROLE_ADMIN');

INSERT INTO `user_role` (user_id, role_id) 
    VALUES 
    (@Tomas,@user),
    (@Lily,@user),
    (@Benjamin, @user),
    (@Johanna,@admin),
    (@Lily,@admin),
    (@Benjamin, @admin);

COMMIT;

INSERT INTO `user_profile` (email, user_credentials_id)
	VALUES
    ('lily@gmail.com', @Lily),
    ('tomas@gmail.com', @Tomas),
    ('johanna@gmail.com', @Johanna),
    ('benjamin@gmail.com', @Benjamin);


COMMIT;

SET @Tomas = (SELECT id  FROM teamsharingdev.user_profile WHERE email= 'tomas@gmail.com');
SET @Johanna = (SELECT id  FROM teamsharingdev.user_profile WHERE email= 'johanna@gmail.com');
SET @Lily = (SELECT id  FROM teamsharingdev.user_profile WHERE email = 'lily@gmail.com');
SET @Benjamin = (SELECT id FROM teamsharingdev.user_profile WHERE email = 'benjamin@gmail.com');

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
