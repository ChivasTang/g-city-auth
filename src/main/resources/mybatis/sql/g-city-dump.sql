--创建
create table user_mst
(
    user_id   varchar2(36) default sys_guid(),
    username  varchar2(20) not null,
    password  varchar2(64) not null,
    c_user_id varchar2(36) default null,
    c_time    TIMESTAMP default sysdate,
    u_user_id varchar2(36) default null,
    u_time    TIMESTAMP default sysdate,
    deleted   NUMBER(1) default 0
);
alter table user_mst
    add constraint pk_user_mst_id primary key (user_id);
alter table user_mst
    add constraint uq_user_mst_username unique (username);

