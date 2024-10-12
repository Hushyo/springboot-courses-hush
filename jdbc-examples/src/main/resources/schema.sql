create table if not exists `user`
(
    id  char(19) not null primary key,
    name varchar(45),
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp
    );

create table if not exists `address`
(
    id  char(19) not null primary key,
    detail varchar(45),
    user_id char(19),
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,

    index (user_id)
    );

create table if not exists `department`
(
    id char(19) not null primary key,
    name varchar(20) not null,
    insert_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp
);

create table if not exists `teacher`
(
    id char(19) not null primary key,
    name varchar(10) not null,
    department json comment '{"depId","name"}这里只是注释，没有实际作用',
    insert_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,

    index((cast(department->>'$.depId' as char(19)) collate utf8mb4_bin))
    );
explain
select * from teacher t where t.department->>'$.depId'=1;

create table if not exists `process`
(
    id char(19) not null primary key,
    name varchar(20) not null,
    items json null comment '[{"number","name","point","description"}]',
    insert_time datetime not null default current_timestamp,
    update_time datetime not null default  current_timestamp on update current_timestamp
);

create table if not exists `process_score`(
    id char(19) not null primary key,
    student_id char(19) not null,
    process_id char(19) not null,
    teacher_id char(19) not null,
    detail json not null comment '{"teacherName","detail":[{"number","score"}]}',
    insert_time datetime not null default current_timestamp,
    update_time datetime not null default  current_timestamp on update current_timestamp,

    index (process_id,student_id,teacher_id)
);

create table if not exists `process_score`(
  id char(19) not null primary key,
  student_id char(19) not null,
  process_id char(19) not null,
  detail json not null comment '{"teacherId", "teacherName","detail":[{"number","score"}]}',
  insert_time datetime not null default current_timestamp,
  update_time datetime not null default  current_timestamp on update current_timestamp,

  unique (process_id,student_id)
);

