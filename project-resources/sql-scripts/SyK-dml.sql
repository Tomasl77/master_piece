/* 
DML script for teamsharing database
Script can be used with mysql

Last update : 2020-08-20

*/

USE teamsharingdev;


INSERT INTO `member` (account_non_expired, password, username, account_non_locked, credentials_non_expired, enabled)
	VALUES 
    ('T','$2a$10$JbSdgniSs9PoNJM3XN6qUuS9s6uVJwpS1fLIOddNemQgx8FuUw67O','Tomas','T','T','T'), -- password = Totototo9!
	('T','$2a$10$PxZEkHGLpGgeV8mO8ehxz..dGlyxwFo3FgTGfdC/2LqOYd8R4DI/a','Johanna','T','T','T'),  -- password = Joanhime77!
    ('T','$2a$10$tToGMTh3vyXByVqOQHdk9uqbip3wm5rZz1GLbZSoaBB1ileL5w8qi','Lily','T','T','T');  -- password = Lily2709!

INSERT INTO `role`(code, default_role) 
    VALUES 
    ('ROLE_USER','T'), 
    ('ROLE_ADMIN','F');

COMMIT;

SET @Tomas = (SELECT id  FROM teamsharingdev.member WHERE username = 'Tomas');
SET @Johanna = (SELECT id  FROM teamsharingdev.member WHERE username = 'Johanna');
SET @Lily = (SELECT id  FROM teamsharingdev.member WHERE username = 'Lily');
SET @user = (SELECT id FROM teamsharingdev.role WHERE code = 'ROLE_USER');
SET @admin = (SELECT id FROM teamsharingdev.role WHERE code = 'ROLE_ADMIN');

INSERT INTO `member_role` (user_id, role_id) 
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
    ('DATABASE', 'I need some basics on mysql join queries', 'Mysql queries', 1, @Johanna);

COMMIT;
