/* 
DML script for teamsharing database
Script can be used with mysql

Last update : 2020-10-26

*/

USE teamsharingtest;

DELETE FROM `sharing_sessions`;
DELETE FROM `subjects`;
DELETE FROM `categories`;
DELETE FROM `user_role`;
DELETE FROM `users`;
DELETE FROM `roles`;


INSERT INTO `roles`(code, default_role) 
    VALUES 
    ('ROLE_USER','T'), 
    ('ROLE_ADMIN','F');

COMMIT;

INSERT INTO `users` (account_non_expired, password, username, account_non_locked, credentials_non_expired, enabled, email)
	VALUES 
    ('T','$2a$10$JbSdgniSs9PoNJM3XN6qUuS9s6uVJwpS1fLIOddNemQgx8FuUw67O','Tomas','T','T','T', 'lobgeois.tomas@free.fr'), -- password = Totototo9!
	('T','$2a$10$PxZEkHGLpGgeV8mO8ehxz..dGlyxwFo3FgTGfdC/2LqOYd8R4DI/a','Johanna','T','T','T', 'joan@gmail.com'),  -- password = Joanhime77!
    ('T','$2a$10$tToGMTh3vyXByVqOQHdk9uqbip3wm5rZz1GLbZSoaBB1ileL5w8qi','Lily','T','T','T','lily@gmail.com'),  -- password = Lily2709!
    ('T','$2a$10$fGGDHcgG3JF6e3kX12C9JO8jkWF6wFJd.eZQQOEoM9479.Ypy.Fbe','Benjamin', 'T','T','T', 'benjamin@gmail.com'); -- password = Benjamin9!

COMMIT; 

SET @Tomas = (SELECT id  FROM users WHERE username= 'Tomas');
SET @Johanna = (SELECT id  FROM users WHERE username = 'Johanna');
SET @Lily = (SELECT id  FROM users WHERE username = 'Lily');
SET @Benjamin = (SELECT id FROM users WHERE username = 'Benjamin');
SET @user = (SELECT id FROM roles WHERE code = 'ROLE_USER');
SET @admin = (SELECT id FROM roles WHERE code = 'ROLE_ADMIN');

INSERT INTO `user_role` (user_id, role_id) 
    VALUES 
    (@Tomas,@user),
    (@Lily,@user),
    (@Benjamin, @user),
    (@Johanna,@admin),
    (@Lily,@admin),
    (@Benjamin, @admin);

COMMIT;

INSERT INTO `categories` (id, name)
    VALUES
    (1, 'FRONTEND'),
    (2, 'BACKEND'),
    (3, 'DATABASE'),
    (4, 'RIFT'),
    (5, 'OTHER');
    
SET @frontend = (SELECT id FROM categories WHERE name = 'FRONTEND');
SET @backend = (SELECT id FROM categories WHERE name = 'BACKEND');
SET @database = (SELECT id FROM categories WHERE name = 'DATABASE');
SET @rift = (SELECT id FROM categories WHERE name = 'RIFT');
SET @other = (SELECT id FROM categories WHERE name = 'OTHER');

INSERT INTO `subjects` (description, title, request_date, schedule, requester_id, category_id) 
    VALUES 
    ('My knowledge of Angular modals is nearly zero. I need someone to help me', 'Angular 8 Modals','2020-10-12 14:30:00','T', @Tomas, @frontend),
    ('JPQL, Derived queries... Someone could tell me how to request database properly from my springboot app, please?', 'Spring database requests','2020-10-11 14:30:00','F', @Lily, @backend),
    ('I need some serious stuff on how to handle errors in my spring app. Anyone\'s good on this?', 'Spring errors handling','2020-10-10 14:30:00','F', @Johanna, @backend),
    ('I need some basics on mysql join queries', 'Mysql queries','2020-10-09 14:30:00','F', @Johanna, @database),
    ('Angular testing methods', 'Angular 8Test','2020-10-08 14:30:00','F', @Tomas, @frontend),
    ('Spring data jpa', 'Spring','2020-10-07 14:30:00','F', @Lily, @backend),
    ('Junit 5 is a revolution. I need some skills ! ', 'JUnit 5','2020-10-06 14:30:00','T', @Johanna, @backend),
    ('MySql 8. Need some tips on queries', 'Mysql 8','2020-09-05 14:30:00','F', @Johanna, @database),
    ('How to make a pagination in Angular client-side', 'Angular 8 pagination','2020-08-12 14:30:00','F', @Tomas, @frontend),
    ('I want to know stuff about redux', 'React/Redux','2020-10-01 14:32:00','F', @Tomas, @frontend),
    ('I need some help with Ajax.', 'Ajax','2020-01-10 15:30:00','F', @Lily, @frontend),
    ('How effective is node.js? Someone can tell me more about?', 'Node JS','2020-05-19 09:30:00','F', @Lily, @backend),
	('Someone please explain me MDX call', 'Rift MDX','2020-06-03 17:50:00','F', @Johanna, @rift),
    ('How to improve skills by making a good tech watch', 'Misc','2020-01-01 14:30:00','F', @Johanna, @other);

COMMIT;

SET @angular8modals = (SELECT id FROM subjects WHERE title = "Angular 8 Modals");
SET @junit = (SELECT id FROM subjects WHERE title = "JUnit 5");
SET @spring = (SELECT id FROM subjects WHERE title = "Spring");

INSERT INTO `sharing_sessions` (`start_time`, `end_time`, `subject_id`, `lecturer_id`)
	VALUES
    ('2020-11-30 14:00:00', '2022-11-30 15:00:00', @spring ,@Tomas),
    ('2022-12-12 14:30:00', '2022-12-12 15:30:00', @angular8modals ,@Tomas),
	('2022-12-14 14:30:00', '2022-12-14 15:30:00', @junit ,@Johanna);
    
INSERT INTO `user_vote_subject` (`user_id`,`subject_id`)
	VALUES
    (@Tomas, @angular8modals),
    (@Tomas, @junit),
    (@Tomas, @spring),
    (@Johanna, @angular8modals),
    (@Johanna, @junit),
    (@Lily, @junit),
    (@Benjamin, @junit),
    (@Benjamin, @spring);