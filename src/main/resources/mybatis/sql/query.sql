select *
from USER_AUTH_INFO;


SELECT dob.object_name table_name,
       lo.locked_mode,
       lo.session_id,
       vss.serial#,
       vss.action action,
       vss.osuser osuser,
       vss.logon_time,
       vss.process ap_pid,
       vps.spid db_pid
FROM v$locked_object lo,
     dba_objects dob,
     v$session vss,
     v$process vps
WHERE lo.object_id = dob.object_id
  AND lo.session_id = vss.sid
  AND vss.paddr = vps.addr
ORDER BY dob.object_name;