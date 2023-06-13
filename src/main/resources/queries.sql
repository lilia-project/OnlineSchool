1)
SELECT id, role, last_name, first_name, phone, email, course_id
	FROM public.person
	ORDER BY last_name;
2)
SELECT name,lecture_date, (SELECT count(*) FROM public.addition_material where lecture_id = lec.id) AS am_count
    FROM public.lecture lec
    WHERE lecture_date < '2023-01-01'
    ORDER BY lecture_date;
3)
SELECT *, (SELECT count(*) FROM public.homework where lecture_id = lec.id) AS hw_count
    FROM public.lecture lec
    ORDER BY create_at ASC, hw_count DESC
    LIMIT 1;
4)
SELECT COUNT(resource_type)
    FROM public.addition_material
    WHERE resource_type='URL' ;

SELECT resource_type, COUNT(resource_type) AS count
	FROM public.addition_material
	WHERE resource_type='BOOK'
	GROUP BY resource_type;

SELECT resource_type, COUNT(resource_type) AS count
	FROM public.addition_material
	WHERE resource_type='VIDEO'
	GROUP BY resource_type;
5)
SELECT role, last_name
	FROM public.person
	WHERE role='TEACHER' AND last_name <'N';
6)
SELECT last_name, first_name,(SELECT count (course_id) FROM public.student_course WHERE student_id=per.id) AS count
	FROM public.person per
	WHERE role = 'STUDENT'
	ORDER BY last_name;