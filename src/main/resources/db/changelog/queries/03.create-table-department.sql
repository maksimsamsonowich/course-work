create table department (
    id bigserial primary key,
    title varchar(100) not null,
    faculty_id bigint references faculties(id)
);