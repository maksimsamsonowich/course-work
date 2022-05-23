create table tests (
    id bigserial primary key,
    title varchar not null,
    assign_date date not null,
    mark integer not null,
    link text not null,
    students_id bigint references students(id),
    assigner_professor_id bigint references professors(id)
);