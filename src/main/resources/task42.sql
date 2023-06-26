create TABLE IF NOT EXISTS public.course
(
    id serial NOT NULL,
    name text NOT NULL,
    PRIMARY KEY (id)
);

alter table IF EXISTS public.course
    OWNER to postgres;

    create TABLE IF NOT EXISTS public.person
(
    id serial NOT NULL,
    role text NOT NULL,
    last_name text NOT NULL,
    first_name text,
    phone text,
    email text,
    course_id integer,
    PRIMARY KEY (id)
);

alter table IF EXISTS public.person
    OWNER to postgres;

alter table IF EXISTS public.person
    ADD CONSTRAINT f_course_id FOREIGN KEY (course_id)
    REFERENCES public.course (id) MATCH SIMPLE
    ON update NO ACTION
    ON delete NO ACTION
    NOT VALID;

create TABLE IF NOT EXISTS public.lecture
(
    id serial NOT NULL,
    create_at time without time zone NOT NULL,
    lecture_date time without time zone NOT NULL,
    name text NOT NULL,
    course_id integer,
    person_id integer,
    description text,
    PRIMARY KEY (id)
);
alter table IF EXISTS public.lecture
    ADD CONSTRAINT f_course_id FOREIGN KEY (course_id)
    REFERENCES public.course (id) MATCH SIMPLE
    ON update NO ACTION
    ON delete NO ACTION
    NOT VALID;

alter table IF EXISTS public.lecture
    ADD CONSTRAINT f_person_id FOREIGN KEY (person_id)
    REFERENCES public.person (id) MATCH SIMPLE
    ON update NO ACTION
    ON delete NO ACTION
    NOT VALID;

    create TABLE IF NOT EXISTS public.homework
(
    id serial NOT NULL,
    task text NOT NULL,
    lecture_id integer NOT NULL,
    deadline time without time zone,
    PRIMARY KEY (id)
);

alter table IF EXISTS public.homework
    OWNER to postgres;
alter table IF EXISTS public.homework
    ADD CONSTRAINT f_lecture_id FOREIGN KEY (lecture_id)
    REFERENCES public.lecture (id) MATCH SIMPLE
    ON update NO ACTION
    ON delete NO ACTION
    NOT VALID;

create TABLE IF NOT EXISTS public.addition_material
(
    id serial NOT NULL,
    name text NOT NULL,
    lecture_id integer NOT NULL,
    resource_type text,
    PRIMARY KEY (id)
);

alter table IF EXISTS public.addition_material
    OWNER to postgres;
alter table IF EXISTS public.addition_material
    ADD CONSTRAINT lecture_id FOREIGN KEY (lecture_id)
    REFERENCES public.lecture (id) MATCH SIMPLE
    ON update NO ACTION
    ON delete NO ACTION
    NOT VALID;

create TABLE IF NOT EXISTS public.student_course
(
    student_id integer NOT NULL,
    course_id integer NOT NULL,
    CONSTRAINT id_id PRIMARY KEY (student_id, course_id),
    CONSTRAINT f_course_id FOREIGN KEY (course_id)
        REFERENCES public.course (id) MATCH SIMPLE
        ON update NO ACTION
        ON delete NO ACTION
        NOT VALID,
    CONSTRAINT f_student_id FOREIGN KEY (student_id)
        REFERENCES public.person (id) MATCH SIMPLE
        ON update NO ACTION
        ON delete NO ACTION
        NOT VALID
);

alter table IF EXISTS public.student_course
    OWNER to postgres;