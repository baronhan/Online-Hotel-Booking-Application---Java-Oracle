CREATE OR REPLACE PROCEDURE Check_Insert_Privilege (
    p_user_name IN VARCHAR2,
    p_result OUT NUMBER
) AS
    v_count NUMBER;
BEGIN
   
    SELECT COUNT(*) INTO v_count
    FROM user_tab_privs
    WHERE privilege = 'INSERT'
    AND table_name = 'BOOKINGS'
    AND grantee = p_user_name;

  
    IF v_count > 0 THEN
        p_result := 1; 
    ELSE
        p_result := 0;
    END IF;
END;
/

CREATE OR REPLACE PROCEDURE Check_Select_Privilege (
    p_user_name IN VARCHAR2,
    p_result OUT NUMBER
) AS
    v_count NUMBER;
BEGIN
   
    SELECT COUNT(*) INTO v_count
    FROM dba_tab_privs
    WHERE privilege = 'SELECT'
    AND table_name = 'USER_INFO'
    AND grantee = p_user_name;

    
    IF v_count > 0 THEN
        p_result := 1; 
    ELSE
        p_result := 0; 
    END IF;
END;
/

CREATE OR REPLACE PROCEDURE Check_Update_Privilege (
    p_user_name IN VARCHAR2,
    p_result OUT NUMBER
) AS
    v_count NUMBER;
BEGIN
    
    SELECT COUNT(*) INTO v_count
    FROM dba_tab_privs
    WHERE privilege = 'UPDATE'
    AND table_name = 'USER_INFO'
    AND grantee = p_user_name;

    
    IF v_count > 0 THEN
        p_result := 1; 
    ELSE
        p_result := 0; 
    END IF;
END;
/

SELECT *
    FROM dba_tab_privs
    WHERE grantee = 'BARONHAN' AND privilege = 'INSERT'
    AND table_name = 'BOOKINGS'
    AND grantee = 'BARONHAN';
    
select * from dba_sys_privs
select * from dba_sys_privs where grantee='BARONHAN1'
select * from dba_sys_privs where grantee = 'BARONHAN'
and privilege='CREATE SESSION'
select admin_option from dba_sys_privs where grantee = 'BARONHAN1'
and privilege = 'CREATE SESSION'

CREATE SEQUENCE booking_id_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE SEQUENCE room_id_seq
    START WITH 5
    INCREMENT BY 1
    NOCACHE;

CREATE SEQUENCE service_id_seq
    START WITH 2
    INCREMENT BY 1
    NOCACHE;

CREATE SEQUENCE user_id_seq
    START WITH 3
    INCREMENT BY 1
    NOCACHE;

CREATE OR REPLACE PROCEDURE REVOKE_WITH_ADMIN_OPTION(
    p_grantee VARCHAR2,
    p_privilege VARCHAR2
) AS
BEGIN
    FOR admin_rec IN (
        SELECT * FROM dba_sys_privs 
        WHERE grantee = p_grantee AND privilege = p_privilege AND admin_option = 'YES'
    ) LOOP
        EXECUTE IMMEDIATE 'REVOKE ' || p_privilege || ' FROM ' || p_grantee;
        EXECUTE IMMEDIATE 'GRANT ' || p_privilege || ' TO ' || p_grantee;
    END LOOP;
END;
/
BEGIN
    REVOKE_WITH_ADMIN_OPTION('BARONHAN', 'CREATE SESSION');
END;
/


select * from rooms
delete from rooms
where roomId = 'R003';
commit;