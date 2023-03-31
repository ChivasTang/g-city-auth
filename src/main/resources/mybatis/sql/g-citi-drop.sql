--删除
COMMIT;
alter table user_mst
    disable constraint pk_user_mst_id;
alter table user_mst
    disable constraint uq_user_mst_username;
drop table user_mst;--删除