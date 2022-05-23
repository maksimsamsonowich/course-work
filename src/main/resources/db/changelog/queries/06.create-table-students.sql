create table students (
    id bigserial primary key,
    full_name varchar(50) not null,
    course integer not null,
    study_group varchar(10) not null,
    enrollment_date date not null,
    faculties_id bigint references faculties(id),
    specialities_id bigint references specialities(id)
);