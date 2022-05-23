create table professors (
    id bigserial primary key,
    full_name varchar(100) not null,
    date_of_receipt date not null,
    date_of_dismissal date default null,
    department_id bigint references department(id)
);