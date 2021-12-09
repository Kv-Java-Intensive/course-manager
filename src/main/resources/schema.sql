-- DROP
-- DATABASE IF EXISTS course_management ;
-- CREATE
-- DATABASE course_management;
DROP table IF exists certificates cascade;
CREATE TABLE IF NOT EXISTS certificates
(
    ID
    bigint
    NOT
    NULL,
    name
    character
    varying
(
    255
),
    PRIMARY KEY
(
    id
),
    FOREIGN KEY
(
    user_id
) REFERENCES users
(
    id
)
    );

DROP table IF exists course_tags cascade;
CREATE TABLE IF NOT EXISTS course_tags
(
    tag_id
    bigint
    NOT
    NULL,
    course_id
    bigint
    NOT
    NULL,
    FOREIGN
    KEY
(
    course_id
)
    REFERENCES courses
(
    id
) FOREIGN KEY
(
    tag_id
)
    REFERENCES tags
(
    id
)
    );
DROP table IF exists courses cascade;
CREATE TABLE IF NOT EXISTS courses
(
    id bigint NOT NULL DEFAULT nextval
(
    'courses_id_seq'::regclass
),
    course_name character varying
(
    255
) COLLATE pg_catalog."default",
    description character varying
(
    255
) COLLATE pg_catalog."default",
    duration double precision,
    language character varying
(
    255
) COLLATE pg_catalog."default",
    price double precision,
    update_date date,
    course_category bigint,
    CONSTRAINT courses_pkey PRIMARY KEY
(
    id
),
    CONSTRAINT fk62pt78ky0kpme2p7qsoiuiewo FOREIGN KEY
(
    course_category
)
    REFERENCES public.categories
(
    id
) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    );
DROP table IF exists groups cascade;

CREATE TABLE IF NOT EXISTS groups
(
    id bigint NOT NULL DEFAULT nextval
(
    'groups_id_seq'::regclass
),
    capacity integer NOT NULL,
    name character varying
(
    255
) COLLATE pg_catalog."default",
    start_date timestamp without time zone,
    course_id bigint NOT NULL,
    CONSTRAINT groups_pkey PRIMARY KEY
(
    id
),
    CONSTRAINT fkf19vc2y7m7nnmbr8usoitb3gp FOREIGN KEY
(
    course_id
)
    REFERENCES public.courses
(
    id
) MATCH SIMPLE
                         ON UPDATE NO ACTION
                         ON DELETE NO ACTION
    );
DROP table IF exists modules cascade;

CREATE TABLE IF NOT EXISTS modules
(
    id bigint NOT NULL DEFAULT nextval
(
    'modules_id_seq'::regclass
),
    content character varying
(
    255
) COLLATE pg_catalog."default",
    description character varying
(
    255
) COLLATE pg_catalog."default",
    lesson_num integer,
    course_id bigint,
    CONSTRAINT modules_pkey PRIMARY KEY
(
    id
),
    CONSTRAINT fk8qnnp812q1jd38fx7mxrhpw9 FOREIGN KEY
(
    course_id
)
    REFERENCES public.courses
(
    id
) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    );
DROP table IF exists tags cascade;

CREATE TABLE IF NOT EXISTS tags
(
    id bigint NOT NULL DEFAULT nextval
(
    'tags_id_seq'::regclass
),
    name character varying
(
    255
) COLLATE pg_catalog."default",
    CONSTRAINT tags_pkey PRIMARY KEY
(
    id
)
    );
DROP table IF exists user_course cascade;

CREATE TABLE IF NOT EXISTS user_course
(
    course_id
    bigint
    NOT
    NULL,
    user_id
    bigint
    NOT
    NULL,
    course_status
    integer,
    is_author
    boolean,
    CONSTRAINT
    user_course_pkey
    PRIMARY
    KEY
(
    course_id,
    user_id
),
    CONSTRAINT fkf4qni5wnlq0x70fm39w9tv7x4 FOREIGN KEY
(
    course_id
)
    REFERENCES public.courses
(
    id
) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkoc4yl478i6o8hf240vumhjgrb FOREIGN KEY
(
    user_id
)
    REFERENCES public.users
(
    id
) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    );
DROP table IF exists users cascade;

CREATE TABLE IF NOT EXISTS users
(
    id bigint NOT NULL DEFAULT nextval
(
    'users_id_seq'::regclass
),
    about character varying
(
    255
) COLLATE pg_catalog."default",
    account_card double precision,
    email character varying
(
    255
) COLLATE pg_catalog."default",
    first_name character varying
(
    255
) COLLATE pg_catalog."default",
    last_name character varying
(
    255
) COLLATE pg_catalog."default",
    password character varying
(
    255
) COLLATE pg_catalog."default",
    role character varying
(
    255
) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY
(
    id
)
    );
DROP table IF exists users_groups cascade;

CREATE TABLE IF NOT EXISTS users_groups
(
    group_id
    bigint
    NOT
    NULL,
    user_id
    bigint
    NOT
    NULL,
    CONSTRAINT
    fkg6fu0mfuj9eqfd9aro1nc40nn
    FOREIGN
    KEY
(
    user_id
)
    REFERENCES public.users
(
    id
) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkggimqo8cv8s5p5wcjmlioodyw FOREIGN KEY
(
    group_id
)
    REFERENCES public.groups
(
    id
) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    );
