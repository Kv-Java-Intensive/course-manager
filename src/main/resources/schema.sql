-- DROP
-- DATABASE IF EXISTS course_management ;
-- CREATE
-- DATABASE course_management;
DROP table IF exists tags cascade;
CREATE TABLE IF NOT EXISTS tags
(
    id
    bigint
    NOT
    NULL,
    name
    character
    varying
(
    255
),
    uuid
    character
    varying
(
    255
),
    PRIMARY KEY
(
    id
)
    );

DROP table IF exists categories cascade;
CREATE TABLE IF NOT EXISTS categories
(
    id
    bigint
    NOT
    NULL,
    category_name
    character
    varying
(
    255
),
    uuid
    character
    varying
(
    255
),
    PRIMARY KEY
(
    id
)
    );


DROP table IF exists courses cascade;
CREATE TABLE IF NOT EXISTS courses
(
    id
    bigint
    NOT
    NULL,
    course_name
    character
    varying
(
    255
),
    description character varying
(
    255
),
    duration double precision,
    language character varying
(
    255
),
    price double precision,
    update_date date,
    course_category bigint,
    uuid
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
    course_category
)
    REFERENCES categories
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
),
    FOREIGN KEY
(
    tag_id
)
    REFERENCES tags
(
    id
)
    );


DROP table IF exists groups cascade;
CREATE TABLE IF NOT EXISTS groups
(
    id
    bigint
    NOT
    NULL,
    capacity
    integer
    NOT
    NULL,
    name
    character
    varying
(
    255
),
    start_date timestamp without time zone,
    course_id bigint NOT NULL,
    PRIMARY KEY
(
    id
),
    FOREIGN KEY
(
    course_id
)
    REFERENCES courses
(
    id
)
    );


DROP table IF exists modules cascade;
CREATE TABLE IF NOT EXISTS modules
(
    id
    bigint
    NOT
    NULL,
    content
    character
    varying
(
    255
),
    description character varying
(
    255
),
    lesson_num integer,
    course_id bigint,
    uuid
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
    course_id
)
    REFERENCES courses
(
    id
)
    );

DROP table IF exists users cascade;
CREATE TABLE IF NOT EXISTS users
(
    id
    bigint
    NOT
    NULL,
    about
    character
    varying
(
    255
),
    account_card double precision,
    email character varying
(
    255
),
    first_name character varying
(
    255
),
    last_name character varying
(
    255
),
    password character varying
(
    255
),
    role character varying
(
    255
),
    active
    boolean
    ,
    uuid
    character
    varying
(
    255
),
    PRIMARY KEY
(
    id
)
    );


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
    user_id bigint NOT NULL,
    course_id bigint NOT NULL,
    uuid
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
),
    FOREIGN KEY
(
    course_id
) REFERENCES courses
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
    FOREIGN KEY
(
    course_id
)
    REFERENCES courses
(
    id
),
    FOREIGN KEY
(
    user_id
)
    REFERENCES users
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
    FOREIGN
    KEY
(
    user_id
)
    REFERENCES users
(
    id
),
    FOREIGN KEY
(
    group_id
)
    REFERENCES groups
(
    id
)
    );
