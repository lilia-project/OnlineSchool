1)
SELECT lec.name AS lecture_name, per.last_name, per.first_name
	FROM public.lecture AS lec
	RIGHT JOIN public.person AS per
	ON lec.person_id = per.id
	WHERE per.role = 'TEACHER'
	ORDER BY lecture_date;
2)
SELECT last_name, COUNT (last_name) AS res
	FROM public.person
	GROUP BY last_name
	WHERE role='TEACHER';
3)
SELECT name AS lecture_name, lecture_date
	FROM public.lecture
	WHERE person_id = 13
	ORDER BY lecture_date;
4)
SELECT c.name AS course_name,
    COUNT(DISTINCT l.id) AS lecture_count,
    COUNT(DISTINCT per.role)AS teacher_count,
    COUNT(DISTINCT s_c.student_id)AS student_count,
    COUNT(DISTINCT hw.id) AS hw_count,
    COUNT(DISTINCT am.id) AS am_count
FROM public.course AS c
	INNER JOIN public.lecture AS l ON c.id = l.course_id
	INNER JOIN public.person AS per ON per.id=l.person_id  AND per.course_id=c.id
	INNER JOIN public.student_course AS s_c ON s_c.course_id=c.id
	INNER JOIN public.homework AS hw ON hw.lecture_id = l.id
	INNER JOIN public.addition_material AS am ON am.lecture_id = l.id
	GROUP BY c.name;
5)
SELECT EXTRACT(MONTH FROM lecture.lecture_date) AS month, COUNT(lecture.id) AS num_lecture
FROM public.lecture
    WHERE lecture.lecture_date BETWEEN '2020-01-01' AND '2024-01-01'
    GROUP BY month
    ORDER BY month;
6)
SELECT name_entity,res
FROM (SELECT 'homework' AS name_entity, COUNT(id) AS res FROM public.homework) as hw_count
UNION
SELECT 'addition material' AS name_entity, COUNT(id) AS res FROM public.addition_material AS am
    ORDER BY res DESC LIMIT 1;
