/* 
DML script for teamsharing database
Script can be used with mysql

Last update : 2020-08-12

*/

USE teamsharing;

INSERT INTO `member` (id, account_non_expired, password, username, account_non_locked, credentials_non_expired, enabled)
	VALUES 
    (1,'T','$2a$10$JbSdgniSs9PoNJM3XN6qUuS9s6uVJwpS1fLIOddNemQgx8FuUw67O','Tomas','T','T','T'), -- password = Totototo9!
	(2,'T','$2a$10$PxZEkHGLpGgeV8mO8ehxz..dGlyxwFo3FgTGfdC/2LqOYd8R4DI/a','Johanna','T','T','T'),  -- password = Joanhime77!
    (3,'T','$2a$10$tToGMTh3vyXByVqOQHdk9uqbip3wm5rZz1GLbZSoaBB1ileL5w8qi','Lily','T','T','T');  -- password = Lily2709!

INSERT INTO `role`(code, default_role) 
    VALUES 
    (1,'ROLE_USER','T'), 
    (2,'ROLE_ADMIN','F');

COMMIT;

INSERT INTO `member_role` (user_id, role_id) 
    VALUES 
    (1,1),
    (3,1),
    (2,2),
    (3,2);

COMMIT;

INSERT INTO `subject` (category, description, title, total_vote, requester_id) 
    VALUES 
    ('FRONTEND', 'My knowledge of Angular modals is nearly zero. I need someone to help me', 'Angular 8 Modals', 3, 1),
    ('BACKEND', 'JPQL, Derived queries... Someone could tell me how to request database properly from my springboot app, please?', 'Spring database requests', 2, 3),
    ('BACKEND', 'I need some serious stuff on how to handle errors in my spring app. Anyone\'s good on this?', 'Spring errors handling', 2, 2),
    ('DATABASE', 'I need some basics on mysql join queries', 'Mysql queries', 1, 2);

COMMIT;
