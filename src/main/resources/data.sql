insert into public.course(id, name)
	VALUES (1, 'Math'),
           (2, 'Informatics'),
           (3, 'Biology'),
           (4,'Literature');

insert into public.person(id, role, last_name, first_name, phone, email, course_id)
	VALUES (1, 'STUDENT','Leonov','Leon','0963459203','0963459203@gmail.com',1),
	       (2, 'STUDENT','Makarova','Marina','0917159203','0917159203@gmail.com',1),
	       (3, 'STUDENT','Markov','Mark','0967159203','0967159203@gmail.com',2),
	       (4, 'STUDENT','Andreev','Andrey','0963459333','0963459333@gmail.com',4),
	       (5, 'STUDENT','Alekseev','Aleksey','0963459444','0963459444@gmail.com',2),
	       (6, 'STUDENT','Aleksandrov','Alex','0963459555','0963459555@gmail.com',2),
	       (7, 'STUDENT','Buzova','Liza','0963459004','0963459004@gmail.com',2),
	       (8, 'STUDENT','Nazarenko','Nazar','0963459999','0963459999@gmail.com',1),
	       (9, 'STUDENT','Azarova','Lina','0963459977','0963459977@gmail.com',3);

insert into public.person(id, role, last_name, first_name, phone, email, course_id)
	VALUES (10, 'TEACHER','Altov','Alex','0953459999','0953459999@gmail.com',1),
	       (11, 'TEACHER','Mohov','Evgen','0953459993','0953459993@gmail.com',2),
           (12, 'TEACHER','Ilster','Vlad','0353459993','0353459993@gmail.com',3),
	       (13, 'TEACHER','Baranov','Ilia','0353450993','0353450993@gmail.com',4);

insert into public.lecture(id, name, course_id, person_id, create_at, lecture_date)
	VALUES (1, 'Introduction2', 2,	11,	'2023-06-11 00:00:00', '2023-06-14 00:00:00'),
    	   (2, 'First lecture', 1, 10, '2023-06-08 00:00:00', '2023-06-17 00:00:00'),
    	   (3, 'Introduction', 1, 10, '2023-06-10 00:00:00', '2023-06-19 00:00:00'),
    	   (4, 'Lecture 4.2', 2, 11, '2022-04-21 00:00:00', '2022-05-05 00:00:00'),
    	   (5, 'Lecture 5.2', 2, 11, '2023-06-04 00:00:00', '2023-06-14 00:00:00'),
    	   (6, 'Lecture 2.3', 3, 12, '2023-06-16 00:00:00', '2023-06-19 00:00:00'),
    	   (7, 'Lecture 3.3', 3, 12, '2022-08-04 00:00:00', '2022-09-10 00:00:00'),
    	   (8, 'Lecture 4.4', 4, 13, '2023-06-15 00:00:00', '2023-06-20 00:00:00'),
    	   (9, 'Lecture 1.4', 4, 13, '2022-06-12 00:00:00', '2022-06-15 00:00:00'),
    	   (10, 'Lecture 2.4', 4, 13, '2023-06-18 00:00:00', '2023-06-24 00:00:00'),
    	   (11, 'Lecture 3.4', 4, 13, '2023-03-18 00:00:00', '2023-03-24 00:00:00'),
    	   (12, 'Lecture 4.3', 3, 12, '2023-01-18 00:00:00', '2023-01-24 00:00:00'),
    	   (13, 'Lecture 1.1', 1, 10, '2023-06-23 00:00:00', '2023-06-30 00:00:00'),
    	   (14, 'Lecture 5.4', 4, 13, '2022-06-18 00:00:00', '2022-06-24 00:00:00');


insert into public.addition_material(id, name, lecture_id, resource_type)
	VALUES (1, 'Material 1', 1,	'URL'),
           (2, 'Material 1', 1,	'BOOK'),
           (3, 'Material 2', 2, 'BOOK'),
           (4, 'Material 2', 2, 'URL'),
           (5, 'Material 3', 3, 'URL'),
           (6, 'Material 4', 4, 'URL'),
           (7, 'Material 5', 5, 'URL'),
           (8, 'Material 6', 6, 'BOOK'),
           (9, 'Material 7', 7, 'VIDEO'),
           (10, 'Material 8', 8, 'BOOK'),
           (11, 'Material 9', 9, 'VIDEO'),
           (12,	'Material 10', 10, 'BOOK'),
           (13,	'Material 10', 10, 'URL'),
           (14,	'Material 9', 9, 'VIDEO'),
           (15,	'Material 5', 5, 'BOOK'),
           (16,	'Material 5', 5, 'BOOK'),
           (17,	'Material 3', 3, 'URL');

insert into public.homework(id, task, lecture_id)
	VALUES (1,	'Task 11', 1),
           (2,	'Task 21', 1),
           (3,	'Task 12', 2),
           (4,	'Task 22', 2),
           (5,	'Task 13', 3),
           (6,	'Task 14', 4),
           (7,	'Task 15', 5),
           (8,	'Task 16', 6),
           (9,	'Task 26', 6),
           (10, 'Task 27', 7),
           (11, 'Task 17', 7),
           (12, 'Task 18', 8),
           (13, 'Task 19', 9),
           (14, 'Task 49', 9),
           (15, 'Task 40', 10),
           (16, 'Task 30', 10),
           (17, 'Task 38', 11),
           (18, 'Task 39', 11),
           (19, 'Task 41', 11),
           (20, 'Task 44', 12),
           (21, 'Task 45', 13),
           (22, 'Task 46', 13),
           (23, 'Task 50', 14),
           (24, 'Task 51', 14),
           (25, 'Task 52', 14);

insert into public.student_course(
	student_id, course_id)
	VALUES (1, 1),
           (1, 4),
           (2, 1),
           (2, 2),
           (3, 2),
           (4, 4),
           (5, 2),
           (6, 2),
           (7, 2),
           (8, 1),
           (9, 1),
           (9, 2),
           (9, 3);
select setval((select pg_get_serial_sequence('public.course', 'id')), (select max(id) from public.course));
select setval((select pg_get_serial_sequence('public.person', 'id')), (select max(id) from public.person));
select setval((select pg_get_serial_sequence('public.lecture', 'id')), (select max(id) from public.lecture));
select setval((select pg_get_serial_sequence('public.addition_material', 'id')), (select max(id) from public.addition_material));
select setval((select pg_get_serial_sequence('public.homework', 'id')), (select max(id) from public.homework));






