--创建触发器
CREATE
    OR REPLACE TRIGGER trg_after_insert_user_auth_info
    AFTER INSERT
    on USER_AUTH_INFO
    FOR EACH ROW

BEGIN

    UPDATE USER_AUTH_INFO SET CREATE_USER_ID=:NEW.USER_ID, UPDATE_USER_ID=:NEW.USER_ID WHERE USER_ID=:NEW.USER_ID;
END;
/
alter trigger trg_after_insert_user_auth_info enable;
/

--删除触发器
DROP TRIGGER trg_after_insert_user_auth_info;