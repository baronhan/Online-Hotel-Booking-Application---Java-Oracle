CREATE TABLE user_info
(
    userId VARCHAR(4) PRIMARY KEY,
    userName VARCHAR(30) NOT NULL,
    fullname varchar(50),
    userEmail VARCHAR(50) UNIQUE,
    userAddress VARCHAR(100),
    userPhone VARCHAR(11) UNIQUE
);

CREATE TABLE rooms
(
    roomId VARCHAR(4) PRIMARY KEY,
    roomName VARCHAR(30),
    noPeople INT,
    price FLOAT,
    roomDescription VARCHAR(255),
    roomType VARCHAR(10),
    roomPicture VARCHAR(100),
    roomVideo BLOB, 
    roomStatus NUMBER(1) DEFAULT 0,
    CONSTRAINT contraint_roomStatus CHECK (roomStatus IN (0,1))
);

CREATE TABLE services
(
    serviceId VARCHAR(4) PRIMARY KEY,
    serviceName VARCHAR(30),
    acronym VARCHAR(10),
    price FLOAT,
    serviceType VARCHAR(30)
);

CREATE TABLE bookings
(
    bookingId VARCHAR(4) PRIMARY KEY,
    userId VARCHAR(4),
    roomId VARCHAR(4),
    serviceId VARCHAR(4),
    checkIn DATE,
    checkOut DATE,
    creditNumber VARCHAR(30),
    total FLOAT,
    FOREIGN KEY (userId) REFERENCES user_info(userId),
    FOREIGN KEY (roomId) REFERENCES rooms(roomId),
    FOREIGN KEY (serviceId) REFERENCES services(serviceId)
);

/*TRIGGER*/
CREATE OR REPLACE TRIGGER generateUserId
BEFORE INSERT ON HotelBooking.user_info
FOR EACH ROW
DECLARE
    IdCount INT;
    NewId VARCHAR2(4);
BEGIN
    SELECT COUNT(*) INTO IdCount FROM HotelBooking.user_info;
    NewId := 'U' || LPAD(IdCount + 1, 3, '0');

    :NEW.userId := NewId;
END;


CREATE OR REPLACE TRIGGER generateRoomId
BEFORE INSERT ON rooms
FOR EACH ROW
DECLARE
    IdCount INT;
    NewId VARCHAR2(4);
BEGIN
    SELECT COUNT(*) INTO IdCount FROM rooms;
    NewId := 'R' || LPAD(IdCount + 1, 3, '0');

    :NEW.roomId := NewId;
END;

CREATE OR REPLACE TRIGGER generateServiceId
BEFORE INSERT ON services
FOR EACH ROW
DECLARE
    IdCount INT;
    NewId VARCHAR2(4);
BEGIN
    SELECT COUNT(*) INTO IdCount FROM services;
    NewId := 'S' || LPAD(IdCount + 1, 3, '0');

    :NEW.serviceId := NewId;
END;

CREATE OR REPLACE TRIGGER generateBookingId
BEFORE INSERT ON bookings
FOR EACH ROW
DECLARE
    IdCount INT;
    NewId VARCHAR2(4);
BEGIN
    SELECT COUNT(*) INTO IdCount FROM bookings;
    NewId := 'B' || LPAD(IdCount + 1, 3, '0');

    :NEW.bookingId := NewId;
END;

/*INSERT*/

INSERT INTO hotelbooking.user_info (userName, fullname, userEmail, userAddress, userPhone)
VALUES ('baronhan', 'Nguyen Phuc Bao Nhan', 'npbaonhan18803@gmail.com', '490/54 Le Van Sy', '0948877502');

INSERT INTO hotelbooking.user_info (userName, fullname, userEmail, userAddress, userPhone)
VALUES ('thanhdieu', 'Nguyen Ngoc Thanh Dieu', 'thanhdieu2310@gmail.com', '1002 Ta Quang Buu', '0703066016');
commit;
select * from hotelbooking.user_info;

INSERT INTO rooms (roomName, noPeople, price, roomDescription, roomType, roomPicture, roomStatus)
VALUES ('Phong Deluxe', 2, 200, 'Phong Sang Trong', 'Deluxe', '/Hinh/SUPER-DELUXE2.jpg', 1);

INSERT INTO hotelbooking.rooms (roomName, noPeople, price, roomDescription, roomType, roomPicture, roomStatus)
VALUES ('Phong Couple', 2, 230, 'Phong Doi', 'Couple', '/Hinh/SUPER-DELUXE2.jpg', 1);
commit;

UPDATE hotelbooking.rooms
SET roomStatus = 1
WHERE roomID = 'R003';
commit;

delete from hotelbooking.user_info
where userId = 'U004';
commit;
select * from hotelbooking.rooms;

INSERT INTO services (serviceName, acronym, price, serviceType)
VALUES ('D?ch v? Massage', 'MASS', 50, 'Spa');

INSERT INTO services (serviceName, acronym, price, serviceType)
VALUES ('D?ch v? ?n sáng', 'BFST', 20, 'Food');

select * from hotelbooking.services;
UPDATE hotelbooking.services
SET serviceName = 'Dich vu an sang'
WHERE serviceID = 'S002';
commit;

INSERT INTO bookings (userId, roomId, serviceId, checkIn, checkOut, creditNumber, total)
VALUES ('U001', 'R001', 'S001', TO_DATE('2022-01-01', 'YYYY-MM-DD'), TO_DATE('2022-01-05', 'YYYY-MM-DD'), '1234567890', 400);

INSERT INTO hotelbooking.bookings (userId, roomId, serviceId, checkIn, checkOut, creditNumber, total)
VALUES ('U002', 'R002', 'S002', TO_DATE('2022-02-10', 'YYYY-MM-DD'), TO_DATE('2022-02-15', 'YYYY-MM-DD'), '9876543210', 250);

select * from hotelbooking.bookings;
delete from hotelbooking.bookings
where bookingId = 'B03';

/*STORE PROCEDURE*/
--Tao SP INSERT USER THONG QUA USERNAME
CREATE OR REPLACE PROCEDURE insert_user_info(
    p_userName IN HotelBooking.user_info.userName%TYPE,
    p_fullname IN HotelBooking.user_info.fullname%TYPE,
    p_userEmail IN HotelBooking.user_info.userEmail%TYPE,
    p_userAddress IN HotelBooking.user_info.userAddress%TYPE,
    p_userPhone IN HotelBooking.user_info.userPhone%TYPE
)
IS
BEGIN
    INSERT INTO HOTELBOOKING.user_info (userName, fullname, userEmail, userAddress, userPhone)
    VALUES (p_userName, p_fullname, p_userEmail, p_userAddress, p_userPhone);
    
    COMMIT;
END;

--Tao SP INSERT ROOM THONG QUA ROOMNAME
CREATE OR REPLACE PROCEDURE insert_room(
    p_roomName IN HotelBooking.rooms.roomName%TYPE,
    p_noPeople IN HotelBooking.rooms.noPeople%TYPE,
    p_price IN HotelBooking.rooms.price%TYPE,
    p_roomDescription IN HotelBooking.rooms.roomDescription%TYPE,
    p_roomType IN HotelBooking.rooms.roomType%TYPE,
    p_roomPicture IN HotelBooking.rooms.roomPicture%TYPE,
    p_roomStatus IN HotelBooking.rooms.roomStatus%TYPE
)
IS
BEGIN
    INSERT INTO HOTELBOOKING.rooms (roomName, noPeople, price, roomDescription, roomType, roomPicture, roomStatus)
    VALUES (p_roomName, p_noPeople, p_price, p_roomDescription, p_roomType, p_roomPicture, p_roomStatus);

    COMMIT;
END;
/
--Tao SP INSERT SERVICE THONG QUA SERVICENAME
CREATE OR REPLACE PROCEDURE insert_service(
    p_serviceName IN HotelBooking.services.serviceName%TYPE,
    p_acronym IN HotelBooking.services.acronym%TYPE,
    p_price IN HotelBooking.services.price%TYPE,
    p_serviceType IN HotelBooking.services.serviceType%TYPE
)
IS
BEGIN
    INSERT INTO HOTELBOOKING.services (serviceName, acronym, price, serviceType)
    VALUES (p_serviceName, p_acronym, p_price, p_serviceType);

    COMMIT;
END;
/

BEGIN
    insert_user_info('danhnguyen', 'Nguy?n Phúc B?o Danh', 'danhnguyen0703@gmail.com', '123 Tran Hung Dao', '0589609376');
END;

delete from HotelBooking.user_info
where userid = 'U010'

CREATE OR REPLACE PROCEDURE create_user_with_permissions(
    p_username IN VARCHAR2,
    p_password IN VARCHAR2
)
IS
BEGIN
    -- T?o m?i ng??i dùng
    EXECUTE IMMEDIATE 'CREATE USER ' || p_username || ' IDENTIFIED BY ' || p_password;

    -- C?p quy?n CREATE SESSION cho ng??i dùng
    EXECUTE IMMEDIATE 'GRANT CREATE SESSION TO ' || p_username;

    -- C?p quy?n INSERT trên b?ng bookings
    EXECUTE IMMEDIATE 'GRANT INSERT ON HotelBooking.bookings TO ' || p_username;

    -- C?p quy?n SELECT trên b?ng user_info v?i ?i?u ki?n username = p_username
    EXECUTE IMMEDIATE 'GRANT SELECT ON HotelBooking.user_info TO ' || p_username;

    -- C?p quy?n UPDATE trên b?ng user_info v?i ?i?u ki?n username = p_username
    EXECUTE IMMEDIATE 'GRANT UPDATE ON HotelBooking.user_info TO ' || p_username;

    -- L?u thay ??i vào c? s? d? li?u
    COMMIT;
END;

BEGIN
    create_user_with_permissions('cohac', 'cohac');
END;

/*PHÂN QUY?N*/
GRANT CREATE SESSION TO baronhan
GRANT INSERT ON HotelBooking.user_info TO baronhan

INSERT INTO HOTELBOOKING.user_info (userName, userEmail, userAddress, userPhone, fullname)
VALUES ('minhthuan', 'minhthuan@gmail.com', 'Ho Chi Minh', '0948356842', 'Nguyen Minh Thuan');
commit;

/*Kill sessions*/
SELECT s.sid, s.serial#
FROM v$session s
WHERE s.username = 'MINHTHUAN';
ALTER SYSTEM DISCONNECT SESSION 's.sid, s.serial#' IMMEDIATE;

CREATE OR REPLACE PROCEDURE kill_user_sessions(p_username IN VARCHAR2) IS
BEGIN
  FOR session_rec IN (SELECT s.sid, s.serial#
                      FROM v$session s
                      WHERE s.username = UPPER(p_username)) LOOP
    EXECUTE IMMEDIATE 'ALTER SYSTEM KILL SESSION ''' || session_rec.sid || ',' || session_rec.serial# || ''' IMMEDIATE';
  END LOOP;
END;


GRANT EXECUTE ON disconnect_user_sessions TO cohac;
BEGIN
  kill_user_sessions('COHAC');
END;
select * from hotelbooking.user_info
select * from v$session where username = 'MINHTHUAN'
select * from v$session where user is not null

--Tao SP DELETE USER THONG QUA USERNAME
CREATE OR REPLACE PROCEDURE delete_user_by_username(
    username_in IN VARCHAR2
)
IS
    user_count NUMBER;
    BEGIN
        -- Ki?m tra xem ng??i dùng có t?n t?i không
        SELECT COUNT(*)
        INTO user_count
        FROM hotelbooking.user_info
        WHERE username = username_in;
    
        IF user_count > 0 THEN
            -- Th?c hi?n xóa ng??i dùng t? b?ng user_info
            DELETE FROM hotelbooking.user_info
            WHERE username = username_in;
            -- Xóa ng??i dùng và các ??i t??ng liên quan
            EXECUTE IMMEDIATE 'DROP USER ' || username_in || ' CASCADE';
    
            -- In ra thông báo thành công
            DBMS_OUTPUT.PUT_LINE('Ng??i dùng ' || username_in || ' ?ã ???c xóa thành công.');
        ELSE
            -- N?u ng??i dùng không t?n t?i
            RAISE_APPLICATION_ERROR(-20001, 'Ng??i dùng không t?n t?i.');
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            -- X? lý l?i
            DBMS_OUTPUT.PUT_LINE('L?i: ' || SQLERRM);
    COMMIT;
END;
/
--Tao SP DELETE ROOM THONG QUA ROOMNAME
CREATE OR REPLACE PROCEDURE delete_room_by_room_name(
    roomname_in IN VARCHAR2
)
IS
BEGIN
    -- Th?c hi?n xóa phòng t? b?ng rooms
    DELETE FROM hotelbooking.rooms
    WHERE roomName = roomname_in;

    -- COMMIT ?? l?u các thay ??i
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- X? lý l?i và in thông tin chi ti?t
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        -- Log l?i ho?c th?c hi?n các hành ??ng khác n?u c?n thi?t
END delete_room_by_room_name;

--Tao SP DELETE SERVICE THONG QUA SERVICENAME
CREATE OR REPLACE PROCEDURE delete_service_by_service_name(
    service_name_in IN VARCHAR2
)
IS
BEGIN
    -- Th?c hi?n xóa d?ch v? t? b?ng services
    DELETE FROM hotelbooking.services
    WHERE serviceName = service_name_in;
EXCEPTION
    WHEN OTHERS THEN
        -- X? lý l?i và in thông tin chi ti?t
        DBMS_OUTPUT.PUT_LINE('L?i: ' || SQLERRM);
        -- Log l?i ho?c th?c hi?n các hành ??ng khác n?u c?n thi?t
END delete_service_by_service_name;
/

--Tao SP UPDATE USER_INFO THONG QUA USERNAME
CREATE OR REPLACE PROCEDURE update_user_info_by_username(
    p_username IN VARCHAR2,
    p_newName IN VARCHAR2,
    p_newAddress IN VARCHAR2,
    p_newPhone IN VARCHAR2,
    p_newEmail IN VARCHAR2
)
AS
BEGIN
    IF p_username IS NOT NULL AND LENGTH(p_username) > 0 THEN
        UPDATE hotelbooking.user_info
        SET fullname = p_newName,
            userEmail = p_newEmail,
            userAddress = p_newAddress,
            userPhone = p_newPhone
        WHERE userName = p_username;
    END IF;
    COMMIT;
END update_user_info_by_username;

--Tao SP UPDATE ROOM THONG QUA ROOMNAME
CREATE OR REPLACE PROCEDURE update_room_info_by_room_name(
    p_roomName IN VARCHAR2,
    p_newNoPeople IN NUMBER,
    p_newPrice IN NUMBER,
    p_newDescription IN VARCHAR2,
    p_newType IN VARCHAR2,
    p_newPicture IN VARCHAR2,
    p_newStatus IN NUMBER
)
AS
BEGIN
    IF p_roomName IS NOT NULL AND LENGTH(p_roomName) > 0 THEN
        UPDATE hotelbooking.rooms
        SET noPeople = p_newNoPeople,
            price = p_newPrice,
            roomDescription = p_newDescription,
            roomType = p_newType,
            roomPicture = p_newPicture,
            roomStatus = p_newStatus
        WHERE roomName = p_roomName;
    END IF;
    COMMIT;
END update_room_info_by_room_name;

--Tao SP UPDATE SERVICE THONG QUA SERVICENAME
CREATE OR REPLACE PROCEDURE update_service_by_service_name(
    p_serviceName IN VARCHAR2,
    p_newAcronym IN VARCHAR2,
    p_newPrice IN NUMBER,
    p_newServiceType IN VARCHAR2
)
AS
BEGIN
    IF p_serviceName IS NOT NULL AND LENGTH(p_serviceName) > 0 THEN
        UPDATE hotelbooking.services
        SET acronym = p_newAcronym,
            price = p_newPrice,
            serviceType = p_newServiceType
        WHERE serviceName = p_serviceName;
    END IF;
    COMMIT;
END update_service_by_service_name;
/

select * from hotelbooking.rooms

BEGIN
    update_room_info_by_room_name('Phong Gia Dinh', 6, 1250, 'Phong Gia Dinh', 'Family', '/Hinh/SUPER-DELUXE2.jpg', 1);
END;
BEGIN
    update_user_info_by_username('minhthuan', 'Nguyen Minh Thuan', 'TP Ho Chi Minh', '0933058330', 'minhthuan@gmail.com');
END;

CREATE OR REPLACE PROCEDURE check_session(
    p_username IN VARCHAR2,
    p_session_exists OUT BOOLEAN
)
AS
    v_count NUMBER;
BEGIN
    -- Ki?m tra xem có b?n ghi nào trong b?ng v$session có username là p_username hay không
    SELECT COUNT(*) INTO v_count
    FROM v$session
    WHERE username = p_username;

    -- N?u có ít nh?t m?t b?n ghi, tr? v? TRUE, ng??c l?i tr? v? FALSE
    p_session_exists := v_count > 0;
END check_session;
/

DECLARE
    v_username VARCHAR2(50) := 'MINHTHUAN'; -- Thay th? 'your_username_here' b?ng tên ng??i dùng c?n ki?m tra
    v_session_exists BOOLEAN;
BEGIN
    -- G?i th? t?c check_session
    check_session(p_username => v_username, p_session_exists => v_session_exists);

    -- Hi?n th? k?t qu?
    IF v_session_exists THEN
        DBMS_OUTPUT.PUT_LINE('Session for user ' || v_username || ' exists.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Session for user ' || v_username || ' does not exist.');
    END IF;
END;
/
select * from hotelbooking.rooms
UPDATE hotelbooking.rooms
    SET roomStatus = 1
    WHERE roomId = 'R004';
commit;

SELECT username, last_login FROM dba_users
SELECT TO_CHAR(last_login, 'DD-MON-YYYY HH24:MI:SS') AS formatted_last_login
FROM dba_users
WHERE username = 'BARONHAN';

CREATE OR REPLACE PROCEDURE get_last_login(username_in IN VARCHAR2, last_login_out OUT VARCHAR2) IS
BEGIN
    SELECT TO_CHAR(last_login, 'DD-MON-YYYY HH24:MI:SS')
    INTO last_login_out
    FROM dba_users
    WHERE username = username_in;
END get_last_login;
/
DECLARE
    last_login_result VARCHAR2(50);
BEGIN
    sys.get_last_login('BARONHAN', last_login_result);
    DBMS_OUTPUT.PUT_LINE(last_login_result);
END;
/

select * from hotelbooking.user_info
where username = 'minhthuan';

update hotelbooking.user_info
set userEmail = 'npbn18803@gmail.com'
where username = 'baronhan'


--Create New Profile
CREATE OR REPLACE PROCEDURE CREATE_PROFILE(tenProfile IN VARCHAR2, sessionN IN INT, connectTime IN INT, reUsePass IN INT, failLogin IN INT, passLock IN INT)
IS
BEGIN
    EXECUTE IMMEDIATE 'CREATE PROFILE ' || tenProfile || ' LIMIT
    SESSIONS_PER_USER ' || sessionN || ' 
    CONNECT_TIME ' || connectTime || ' 
    PASSWORD_REUSE_MAX ' || reUsePass || ' 
    FAILED_LOGIN_ATTEMPTS ' || failLogin || ' 
    PASSWORD_LOCK_TIME ' || passLock || '';
END;


BEGIN
  SYS.CREATE_PROFILE('PROFILE_3', 2, 500, 3, 5, 1);
END;


--Delete Profile 
CREATE OR REPLACE PROCEDURE DELETE_PROFILE(tenProfile IN VARCHAR2)
IS
BEGIN
    EXECUTE IMMEDIATE 'DROP PROFILE ' || tenProfile || '';
END;

EXEC DELETE_PROFILE('PROFILE_1');

-- Select Profile By UserName
SELECT DISTINCT PROFILE FROM DBA_PROFILES;
SELECT PROFILE FROM DBA_USERS WHERE USERNAME = 'XUANPHUONG';

-- Update User Profile
CREATE OR REPLACE PROCEDURE UPDATE_PROFILE_USER(tenUser IN VARCHAR2, tenProfile IN VARCHAR2)
IS
BEGIN
    EXECUTE IMMEDIATE 'ALTER USER ' || tenUser || ' PROFILE ' || tenProfile || '';
END;

EXEC UPDATE_PROFILE_USER('huynhhung', 'PROFILE_2');

-- Select The FAILED_LOGIN_ATTEMPTS OF PROFILE
SELECT LIMIT FROM DBA_PROFILES
WHERE PROFILE = 'PROFILE_1' AND RESOURCE_NAME = 'SESSIONS_PER_USER'

-- CREATE ROLE
CREATE ROLE profile_executor;

--CREATE VIEW
CREATE VIEW MY_PROFILES_VIEW AS
SELECT DISTINCT PROFILE FROM DBA_PROFILES;

-- GRANT PERMISSIONS TO ROLE
GRANT EXECUTE ON DELETE_PROFILE TO profile_executor;
GRANT EXECUTE ON UPDATE_PROFILE_USER TO profile_executor;
GRANT EXECUTE ON CREATE_PROFILE TO profile_executor;
GRANT SELECT ON DBA_ROLE_PRIVS TO profile_executor;
GRANT SELECT ON MY_PROFILES_VIEW TO profile_executor;

--CREATE SP TO REVOKE PERMISSIONS
CREATE OR REPLACE PROCEDURE REVOKE_PROFILE_PERMISSIONS (
  UserName IN VARCHAR2
)
AS
BEGIN
  -- Execute the REVOKE statement dynamically
  EXECUTE IMMEDIATE 'REVOKE profile_executor FROM ' || UserName;

  COMMIT;
END REVOKE_PROFILE_PERMISSIONS;

BEGIN
  REVOKE_PROFILE_PERMISSIONS('minhthuan');
END;
/

-- CREATE STORED PROCEDURE TO GRANT PERMISSIONS TO USER
CREATE OR REPLACE PROCEDURE GRANT_PROFILE_PERMISSIONS (
  UserName IN VARCHAR
)
AS
BEGIN

  EXECUTE IMMEDIATE 'GRANT profile_executor TO ' || UserName;

  COMMIT;
END GRANT_PROFILE_PERMISSIONS;

--
BEGIN
    GRANT_PROFILE_PERMISSIONS('baronhan');
END;
/
revoke RESOURCE from baronhan
--CHECK TO SEE IF THE USER HAS BEEN GRANTED PERMISSION OR NOT
SELECT * FROM DBA_ROLE_PRIVS WHERE GRANTEE = 'BARONHAN';
SELECT *
FROM DBA_TAB_PRIVS
WHERE GRANTEE = 'BARONHAN' AND TABLE_NAME = 'BOOKINGS' AND PRIVILEGE = 'INSERT'

REVOKE ALL PRIVILEGES FROM baronhan;
select roomName, price, roomPicture from hotelbooking.rooms
where roomId = 'R001'

select fullname,userEmail, userAddress, userPhone  from hotelbooking.user_info
where userId = 'U001'

select serviceName, price from hotelbooking.services
where serviceId = 'S001'

-- SP l?y thông tin phòng d?a trên roomId
create or replace NONEDITIONABLE PROCEDURE GetRoomInfo (
    roomId_ IN VARCHAR2,
    v_roomName OUT VARCHAR2,
    v_price OUT FLOAT,
    v_roomPicture OUT VARCHAR2
)
IS
BEGIN
    SELECT roomName, price, roomPicture 
    INTO v_roomName, v_price, v_roomPicture 
    FROM hotelbooking.rooms 
    WHERE roomId = roomId_;
END;

-- SP l?y thông tin ng??i dùng d?a trên userId
CREATE OR REPLACE PROCEDURE GetUserInfo (
    userName_ IN VARCHAR2,
    v_fullname OUT VARCHAR2,
    v_userEmail OUT VARCHAR2,
    v_userAddress OUT VARCHAR2,
    v_userPhone OUT VARCHAR2,
    v_userId OUT VARCHAR2
)
IS
BEGIN
    SELECT userId, fullname, userEmail, userAddress, userPhone 
    INTO v_userId, v_fullname, v_userEmail, v_userAddress, v_userPhone
    FROM hotelbooking.user_info 
    WHERE userName = userName_;
END;
/


-- SP l?y thông tin d?ch v? d?a trên serviceId
CREATE OR REPLACE PROCEDURE GetServiceInfo (
    serviceId_ IN VARCHAR2,
    v_serviceName OUT VARCHAR2,
    v_price OUT FLOAT
)
IS
BEGIN
    SELECT serviceName, price
    INTO v_serviceName, v_price
    FROM hotelbooking.services 
    WHERE serviceId = serviceId_;
END;
/


-- T?o role
CREATE ROLE sp_execute_role;

-- Gán quy?n execute cho các stored procedure cho role
GRANT EXECUTE ON GetRoomInfo TO sp_execute_role;
GRANT EXECUTE ON GetUserInfo TO sp_execute_role;
GRANT EXECUTE ON GetServiceInfo TO sp_execute_role;
GRANT EXECUTE ON AddBooking TO sp_execute_role;
GRANT EXECUTE ON GetAllBookingDetails TO sp_execute_role;
GRANT EXECUTE ON GetUserIdByUsername TO sp_execute_role;
GRANT EXECUTE ON CheckDates TO sp_execute_role;

-- Gán role cho user
GRANT sp_execute_role TO baronhan;


select * from hotelbooking.bookings
delete from hotelbooking.bookings
where bookingId = 'B001'


--Tao SP them moi mot Booking
CREATE OR REPLACE PROCEDURE AddBooking (
    userId_ IN VARCHAR2,
    roomId_ IN VARCHAR2,
    serviceId_ IN VARCHAR2,
    checkIn_ IN VARCHAR2,
    checkOut_ IN VARCHAR2,
    creditNumber_ IN VARCHAR2,
    total_ IN NUMBER
)
IS
BEGIN
    INSERT INTO hotelbooking.bookings (userId, roomId, serviceId, checkIn, checkOut, creditNumber, total)
    VALUES (userId_, roomId_, serviceId_, TO_DATE(checkIn_, 'YYYY-MM-DD'), TO_DATE(checkOut_, 'YYYY-MM-DD'), creditNumber_, total_);
    
    -- Cap nhat trang thai cua phong
    UPDATE hotelbooking.rooms
    SET roomStatus = 0
    WHERE roomId = roomId_;
    
    COMMIT;
END;
/


select * from hotelbooking.services

CREATE OR REPLACE PROCEDURE GetAllBookingDetails(
    bookingId OUT VARCHAR2,
    userId OUT VARCHAR2,
    roomName OUT VARCHAR2,
    noOfPeople OUT NUMBER,
    roomType OUT VARCHAR2,
    serviceName OUT VARCHAR2,
    servicePrice OUT NUMBER,
    roomPrice OUT NUMBER,
    total OUT NUMBER,
    checkIn OUT DATE,
    checkOut OUT DATE
)
AS
BEGIN
    SELECT 
        b.bookingId,
        b.userId,
        r.roomName,
        r.noPeople,
        r.roomType,
        s.serviceName,
        s.price,
        r.price,
        b.total,
        b.checkIn,
        b.checkOut
    INTO
        bookingId,
        userId,
        roomName,
        noOfPeople,
        roomType,
        serviceName,
        servicePrice,
        roomPrice,
        total,
        checkIn,
        checkOut
    FROM 
        hotelbooking.bookings b
    INNER JOIN 
        hotelbooking.rooms r ON b.roomId = r.roomId
    INNER JOIN 
        hotelbooking.services s ON b.serviceId = s.serviceId;
END;
/

CREATE OR REPLACE PROCEDURE GetUserIdByUsername(
    p_username IN VARCHAR2,
    p_userId OUT VARCHAR2
)
AS
BEGIN
    SELECT userId INTO p_userId
    FROM hotelbooking.user_info
    WHERE userName = p_username;
END;
/

DECLARE
    -- Khai báo bien 
    bookingId VARCHAR2(100);
    roomName VARCHAR2(100);
    noOfPeople NUMBER;
    roomType VARCHAR2(100);
    serviceName VARCHAR2(100);
    servicePrice NUMBER;
    roomPrice NUMBER;
    total NUMBER;
    checkIn DATE;
    checkOut DATE;
BEGIN
    -- Goi stored procedure và truyen tham so
    GetBookingDetailsByUserID( 'U003', bookingId, roomName, noOfPeople, roomType, serviceName, servicePrice, roomPrice, total, checkIn, checkOut);

    -- Hien thi ket qua
    DBMS_OUTPUT.PUT_LINE('Booking ID: ' || bookingId);
    DBMS_OUTPUT.PUT_LINE('Room Name: ' || roomName);
    DBMS_OUTPUT.PUT_LINE('Number of People: ' || noOfPeople);
    DBMS_OUTPUT.PUT_LINE('Room Type: ' || roomType);
    DBMS_OUTPUT.PUT_LINE('Service Name: ' || serviceName);
    DBMS_OUTPUT.PUT_LINE('Service Price: ' || TO_CHAR(servicePrice));
    DBMS_OUTPUT.PUT_LINE('Room Price: ' || TO_CHAR(roomPrice));
    DBMS_OUTPUT.PUT_LINE('Total: ' || TO_CHAR(total));
    DBMS_OUTPUT.PUT_LINE('Check In: ' || TO_CHAR(checkIn, 'DD-MON-YYYY'));
    DBMS_OUTPUT.PUT_LINE('Check Out: ' || TO_CHAR(checkOut, 'DD-MON-YYYY'));
END;
/

CREATE OR REPLACE FUNCTION CheckDates(checkInDate IN DATE, checkOutDate IN DATE)
RETURN BOOLEAN
IS
    currentDate DATE := SYSDATE;
    formattedCheckInDate DATE;
    formattedCheckOutDate DATE;
BEGIN   
    -- Dinh dang ngày check-in và check-out
    formattedCheckInDate := TRUNC(checkInDate);
    formattedCheckOutDate := TRUNC(checkOutDate);
    
    IF (formattedCheckInDate >= currentDate AND formattedCheckOutDate >= currentDate) THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
END;
/
select * from hotelbooking.bookings
where userId = 'U002'
delete from hotelbooking.bookings
where bookingId = 'B002'
select * from hotelbooking.rooms
set roomStatus = 0
where roomId = 'R002'

CREATE OR REPLACE FUNCTION get_suggested_username (
    p_username IN VARCHAR2
) RETURN VARCHAR2
AS
    l_username VARCHAR2(50);
    l_suffix_number NUMBER := 1;
    l_count NUMBER;
BEGIN
    l_username := p_username;
    
    
    SELECT COUNT(*) INTO l_count FROM hotelbooking.user_info WHERE username = l_username;
    
    WHILE l_count > 0 LOOP
        
        l_suffix_number := l_suffix_number + 1;
       
        IF REGEXP_LIKE(l_username, '\d$') THEN
            l_username := REGEXP_REPLACE(l_username, '\d+$', '');
        END IF;
        
        l_username := l_username || l_suffix_number;
        
        
        SELECT COUNT(*) INTO l_count FROM hotelbooking.user_info WHERE username = l_username;
    END LOOP;

    RETURN l_username;
END get_suggested_username;
/


CREATE OR REPLACE TRIGGER before_insert_user_info
BEFORE INSERT ON hotelbooking.user_info
FOR EACH ROW
BEGIN
    IF :new.username IS NOT NULL THEN
        :new.username := get_suggested_username(:new.username);
    END IF;
END;
/

DECLARE
    suggested_username VARCHAR2(50);
BEGIN
    suggested_username := get_suggested_username('baronhan1');
    DBMS_OUTPUT.PUT_LINE('Suggested username: ' || suggested_username);
END;
/

CREATE OR REPLACE PROCEDURE check_username_exist (
    p_username IN VARCHAR2,
    p_exist OUT NUMBER
) AS
BEGIN
    SELECT COUNT(*) INTO p_exist FROM hotelbooking.user_info WHERE username = p_username;
END check_username_exist;
/
DECLARE
    p_exist NUMBER;
BEGIN
    check_username_exist('baronhan', p_exist);
    DBMS_OUTPUT.PUT_LINE('Number of existing usernames: ' || p_exist);
END;
/


select * from hotelbooking.user_info
select * from hotelbooking.bookings
delete from hotelbooking.bookings
where bookingId = 'B004'

select roomId from hotelbooking.rooms

CREATE OR REPLACE PROCEDURE get_user_ids(userIds OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN userIds FOR
    SELECT userId FROM hotelbooking.user_info;
END;
/

CREATE OR REPLACE PROCEDURE get_service_ids(serviceIds OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN serviceIds FOR
    SELECT serviceId FROM hotelbooking.services;
END;
/

CREATE OR REPLACE PROCEDURE get_room_ids(roomIds OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN roomIds FOR
    SELECT roomId FROM hotelbooking.rooms;
END;
/
CREATE OR REPLACE PROCEDURE deleteBookingByBookingId(
    p_bookingId IN VARCHAR2,
    p_roomId IN VARCHAR2
)
IS
BEGIN
    
    DELETE FROM hotelbooking.bookings
    WHERE bookingId = p_bookingId;

    
    UPDATE hotelbooking.rooms
    SET roomStatus = 1
    WHERE roomId = p_roomId;

   
    COMMIT; 
END deleteBookingByBookingId;
/

CREATE OR REPLACE PROCEDURE addBooking(
    p_userId IN VARCHAR2,
    p_roomId IN VARCHAR2,
    p_serviceId IN VARCHAR2,
    p_checkIn IN DATE,
    p_checkOut IN DATE,
    p_creditNumber IN VARCHAR2,
    p_total IN NUMBER
)
IS
BEGIN
    
    INSERT INTO hotelbooking.bookings (userId, roomId, serviceId, checkIn, checkOut, creditNumber, total)
    VALUES (p_userId, p_roomId, p_serviceId, p_checkIn, p_checkOut, p_creditNumber, p_total);

    
    UPDATE hotelbooking.rooms
    SET roomStatus = 0
    WHERE roomId = p_roomId;

    
    COMMIT; 
END addBooking;
/

select roomStatus from hotelbooking.rooms
where roomId = 'R001';

CREATE OR REPLACE PROCEDURE getRoomStatusByRoomId(
    p_roomId IN VARCHAR2,
    p_roomStatus OUT NUMBER
)
IS
BEGIN
    SELECT roomStatus INTO p_roomStatus
    FROM hotelbooking.rooms
    WHERE roomId = p_roomId;
END getRoomStatusByRoomId;
/

select * from hotelbooking.rooms
where roomId = 'R001'
select price from hotelbooking.services
where serviceId = 'S001'

CREATE OR REPLACE FUNCTION calculateTotalPrice(
    p_roomId IN VARCHAR2,
    p_serviceId IN VARCHAR2
)
RETURN NUMBER
IS
    v_roomPrice NUMBER;
    v_servicePrice NUMBER;
    v_totalPrice NUMBER;
BEGIN

    SELECT price INTO v_roomPrice
    FROM hotelbooking.rooms
    WHERE roomId = p_roomId;

    
    SELECT price INTO v_servicePrice
    FROM hotelbooking.services
    WHERE serviceId = p_serviceId;

  
    v_totalPrice := v_roomPrice + v_servicePrice;

   
    RETURN v_totalPrice;
END calculateTotalPrice;
/

CREATE OR REPLACE PROCEDURE updateBookingByBookingId(
    p_bookingId IN VARCHAR2,
    p_userId IN VARCHAR2,
    p_roomId IN VARCHAR2,
    p_serviceId IN VARCHAR2,
    p_checkIn IN DATE,
    p_checkOut IN DATE,
    p_creditNumber IN VARCHAR2,
    p_total IN NUMBER,
    p_oldRoomId IN VARCHAR2
)
IS
BEGIN
   
    UPDATE hotelbooking.bookings
    SET userId = p_userId,
        roomId = p_roomId,
        serviceId = p_serviceId,
        checkIn = p_checkIn,
        checkOut = p_checkOut,
        creditNumber = p_creditNumber,
        total = p_total
    WHERE bookingId = p_bookingId;

    UPDATE hotelbooking.rooms
    SET roomStatus = 1
    WHERE roomId = p_oldRoomId;
    
    UPDATE hotelbooking.rooms
    SET roomStatus = 0
    WHERE roomId = p_roomId;
   
    COMMIT; 
END updateBookingByBookingId;
/

SELECT * FROM DBA_SYS_PRIVS where GRANTEE = 'SYS'
SELECT * FROM USER_SYS_PRIVS;
SELECT * FROM DBA_TAB_PRIVS WHERE grantee = 'BARONHAN' AND table_name = 'USER_INFO' AND privilege = 'INSERT' AND grantable = 'YES';
SELECT * FROM USER_TAB_PRIVS WHERE grantee = 'baronhan';
SELECT * 
FROM DBA_SYS_PRIVS 
WHERE PRIVILEGE = 'GRANT ANY ROLE'
CREATE OR REPLACE VIEW open_users_view AS
SELECT username, account_status 
FROM dba_users 
WHERE account_status = 'OPEN';

CREATE OR REPLACE VIEW sys_privs_view AS
SELECT * FROM DBA_SYS_PRIVS WHERE GRANTEE = 'SYS' ;
CREATE OR REPLACE PROCEDURE grantPrivilegeWithAdminOption(
    p_username IN VARCHAR2,
    p_privilege IN VARCHAR2
)
IS
BEGIN
    EXECUTE IMMEDIATE 'GRANT ' || p_privilege || ' TO ' || p_username || ' WITH ADMIN OPTION';
    COMMIT;
END grantPrivilegeWithAdminOption;
/
CREATE OR REPLACE PROCEDURE grantPrivilege(
    p_username IN VARCHAR2,
    p_privilege IN VARCHAR2
)
IS
BEGIN
    EXECUTE IMMEDIATE 'GRANT ' || p_privilege || ' TO ' || p_username;
    COMMIT;
END grantPrivilege;
/
CREATE OR REPLACE VIEW user_sys_privs_view AS
SELECT *
FROM DBA_SYS_PRIVS
WHERE grantee = :p_username;
SELECT * FROM user_sys_privs_view WHERE grantee = 'SYS';

CREATE OR REPLACE PROCEDURE revokeAdminOption(
    p_username IN VARCHAR2,
    p_privilege IN VARCHAR2
)
IS
BEGIN
    EXECUTE IMMEDIATE 'REVOKE GRANT OPTION FOR ' || p_privilege || ' FROM ' || p_username;
    COMMIT;
END revokeAdminOption;
/

REVOKE  OPTION FOR CREATE ANY TABLE FROM BARONHAN1;

SELECT ADMIN_OPTION
FROM DBA_SYS_PRIVS
WHERE GRANTEE = 'BARONHAN1'
AND PRIVILEGE = 'CREATE SESSION';

CREATE OR REPLACE PROCEDURE revokeSpecificPrivilege(
    p_username IN VARCHAR2,
    p_privilege IN VARCHAR2
)
IS
BEGIN
    EXECUTE IMMEDIATE 'REVOKE ' || p_privilege || ' FROM ' || p_username;
    COMMIT;
END revokeSpecificPrivilege;
/

CREATE OR REPLACE PROCEDURE checkPrivilegeExistence(
    p_username IN VARCHAR2,
    p_privilege IN VARCHAR2,
    p_result OUT NUMBER
)
IS
    v_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count
    FROM DBA_SYS_PRIVS
    WHERE GRANTEE = p_username
    AND PRIVILEGE = p_privilege;
    
    IF v_count > 0 THEN
        p_result := 1; -- Quy?n t?n t?i cho ng??i dùng
    ELSE
        p_result := 0; -- Quy?n không t?n t?i cho ng??i dùng
    END IF;
END checkPrivilegeExistence;


BEGIN
    revokeAdminOption('baronhan1', 'CREATE SESSION');
END;

SELECT table_name FROM all_tables WHERE owner = 'HOTELBOOKING'
CREATE OR REPLACE VIEW all_tables_view AS
SELECT table_name FROM all_tables;

select * from all_tables_view where owner = 'HOTELBOOKING'
DECLARE
    checkInDate DATE := '2024-04-01';
    checkOutDate DATE := '2024-04-02';
    currentDate DATE := SYSDATE;
    result BOOLEAN;
BEGIN
    result := CheckDates(checkInDate, checkOutDate);
    IF result THEN
        DBMS_OUTPUT.PUT_LINE('Ngày ki?m tra h?p l?');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Ngày ki?m tra không h?p l?');
    END IF;
END;
/
DECLARE
    currentDate DATE;
BEGIN
    currentDate := SYSDATE;
    DBMS_OUTPUT.PUT_LINE('Current Date: ' || TO_CHAR(currentDate, 'YYYY-MM-DD'));
END;

create or replace NONEDITIONABLE PROCEDURE GrantSelectToUser(
    p_table_name IN VARCHAR2,
    p_user_name IN VARCHAR2
) AS
BEGIN
    EXECUTE IMMEDIATE 'GRANT SELECT ON ' || p_table_name || ' TO ' || p_user_name;
    DBMS_OUTPUT.PUT_LINE('Quyen SELECT da cap cho nguoi dung ' || p_user_name || ' tren bang ' || p_table_name || '.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Loi: ' || SQLERRM);
END;

CREATE OR REPLACE PROCEDURE GrantSelectWGOToUser (
    p_table_name IN VARCHAR2,
    p_user_name IN VARCHAR2
) AS
BEGIN
    EXECUTE IMMEDIATE 'GRANT SELECT ON ' || p_table_name || ' TO ' || p_user_name || ' WITH GRANT OPTION';
    DBMS_OUTPUT.PUT_LINE('Quyen SELECT voi tuy chon WITH GRANT OPTION da cap cho nguoi dung ' || p_user_name || ' tren bang ' || p_table_name || '.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Loi: ' || SQLERRM);
END;
/

SELECT * FROM DBA_TAB_PRIVS WHERE GRANTEE = 'BARONHAN' AND PRIVILEGE = 'SELECT' AND TABLE_NAME = 'USER_INFO';
CREATE OR REPLACE PROCEDURE CheckUserTablePrivilege (
    p_username IN VARCHAR2,
    p_privilege IN VARCHAR2,
    p_table_name IN VARCHAR2,
    p_result OUT NUMBER
)
IS
    v_count NUMBER;
BEGIN
    
    SELECT COUNT(*)
    INTO v_count
    FROM DBA_TAB_PRIVS
    WHERE GRANTEE = p_username
    AND PRIVILEGE = p_privilege
    AND TABLE_NAME = p_table_name;

 
    IF v_count > 0 THEN
        p_result := 1; 
    ELSE
        p_result := 0;
    END IF;
END CheckUserTablePrivilege;
/

SELECT privilege FROM dba_tab_privs WHERE grantee = 'BARONHAN' AND table_name = 'USER_INFO' AND (privilege = 'SELECT' OR privilege = 'INSERT' OR privilege = 'UPDATE' OR privilege = 'DELETE');
create or replace NONEDITIONABLE PROCEDURE GrantInsertToUser(
    p_table_name IN VARCHAR2,
    p_user_name IN VARCHAR2
) AS
BEGIN
    EXECUTE IMMEDIATE 'GRANT INSERT ON ' || p_table_name || ' TO ' || p_user_name;
    DBMS_OUTPUT.PUT_LINE('Quyen INSERT da cap cho nguoi dung ' || p_user_name || ' tren bang ' || p_table_name || '.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Loi: ' || SQLERRM);
END;
CREATE OR REPLACE PROCEDURE GrantInsertToUserWithGrant(
    p_table_name IN VARCHAR2,
    p_user_name IN VARCHAR2
) AS
BEGIN
    EXECUTE IMMEDIATE 'GRANT INSERT ON ' || p_table_name || ' TO ' || p_user_name || ' WITH GRANT OPTION';
    DBMS_OUTPUT.PUT_LINE('Quyen INSERT da cap cho nguoi dung ' || p_user_name || ' tren bang ' || p_table_name || ' voi tuy chon WITH GRANT OPTION.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Loi: ' || SQLERRM);
END;

create or replace NONEDITIONABLE PROCEDURE GrantUpdateToUser(
    p_table_name IN VARCHAR2,
    p_user_name IN VARCHAR2
) AS
BEGIN
    EXECUTE IMMEDIATE 'GRANT UPDATE ON ' || p_table_name || ' TO ' || p_user_name;
    DBMS_OUTPUT.PUT_LINE('Quyen DELETE da cap cho nguoi dung ' || p_user_name || ' tren bang ' || p_table_name || '.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Loi: ' || SQLERRM);
END;
CREATE OR REPLACE PROCEDURE GrantUpdateToUserWGO(
    p_table_name IN VARCHAR2,
    p_user_name IN VARCHAR2
) AS
BEGIN
    EXECUTE IMMEDIATE 'GRANT UPDATE ON ' || p_table_name || ' TO ' || p_user_name || ' WITH GRANT OPTION';
    DBMS_OUTPUT.PUT_LINE('Quyen UPDATE da cap cho nguoi dung ' || p_user_name || ' tren bang ' || p_table_name || ' voi tuy chon GRANT.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Loi: ' || SQLERRM);
END;

create or replace NONEDITIONABLE PROCEDURE GrantDeleteToUser(
    p_table_name IN VARCHAR2,
    p_user_name IN VARCHAR2
) AS
BEGIN
    EXECUTE IMMEDIATE 'GRANT DELETE ON ' || p_table_name || ' TO ' || p_user_name;
    DBMS_OUTPUT.PUT_LINE('Quyen DELETE da cap cho nguoi dung ' || p_user_name || ' tren bang ' || p_table_name || '.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Loi: ' || SQLERRM);
END;

CREATE OR REPLACE PROCEDURE GrantDeleteToUserWGO(
    p_table_name IN VARCHAR2,
    p_user_name IN VARCHAR2
) AS
BEGIN
    EXECUTE IMMEDIATE 'GRANT DELETE ON ' || p_table_name || ' TO ' || p_user_name || ' WITH GRANT OPTION';
    DBMS_OUTPUT.PUT_LINE('Quyen DELETE da cap cho nguoi dung ' || p_user_name || ' tren bang ' || p_table_name || ' voi tuy chon GRANT.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Loi: ' || SQLERRM);
END;

create or replace NONEDITIONABLE PROCEDURE thu_hoi_quyen (
    p_user_name IN VARCHAR2,
    p_schema IN VARCHAR2,
    p_table_name IN VARCHAR2,
    p_select IN BOOLEAN,
    p_insert IN BOOLEAN,
    p_update IN BOOLEAN,
    p_delete IN BOOLEAN
)
IS
BEGIN
    IF p_select OR p_insert OR p_update OR p_delete THEN
        IF p_select THEN
            EXECUTE IMMEDIATE 'REVOKE SELECT ON ' || p_schema || '.' || p_table_name || ' FROM ' || p_user_name;
        END IF;

        IF p_insert THEN
            EXECUTE IMMEDIATE 'REVOKE INSERT ON ' || p_schema || '.' || p_table_name || ' FROM ' || p_user_name;
        END IF;

        IF p_update THEN
            EXECUTE IMMEDIATE 'REVOKE UPDATE ON ' || p_schema || '.' || p_table_name || ' FROM ' || p_user_name;
        END IF;

        IF p_delete THEN
            EXECUTE IMMEDIATE 'REVOKE DELETE ON ' || p_schema || '.' || p_table_name || ' FROM ' || p_user_name;
        END IF;

        DBMS_OUTPUT.PUT_LINE('Thu hoi quyen thanh cong cho nguoi dung ' || p_user_name);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Vui long chon it nhat mot quyen de thu hoi!');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Loi xay ra: ' || SQLERRM);
END;


CREATE OR REPLACE PROCEDURE REVOKE_PRIVILEGE_WGO (
    p_user VARCHAR2,
    p_table_name VARCHAR2,
    p_privilege VARCHAR2
) AS
BEGIN
    EXECUTE IMMEDIATE 'REVOKE ' || p_privilege || ' ON ' || p_table_name || ' FROM ' || p_user || ' CASCADE';
END;
/

CREATE OR REPLACE PROCEDURE REVOKE_PRIVILEGE (
    p_user VARCHAR2,
    p_table_name VARCHAR2,
    p_privilege VARCHAR2
) AS
BEGIN
    IF p_privilege = 'SELECT' THEN
        EXECUTE IMMEDIATE 'REVOKE SELECT ON ' || p_table_name || ' FROM ' || p_user;
    ELSIF p_privilege = 'INSERT' THEN
        EXECUTE IMMEDIATE 'REVOKE INSERT ON ' || p_table_name || ' FROM ' || p_user;
    ELSIF p_privilege = 'UPDATE' THEN
        EXECUTE IMMEDIATE 'REVOKE UPDATE ON ' || p_table_name || ' FROM ' || p_user;
    ELSIF p_privilege = 'DELETE' THEN
        EXECUTE IMMEDIATE 'REVOKE DELETE ON ' || p_table_name || ' FROM ' || p_user;
    ELSE
        
        NULL;
    END IF;
END;
/

CREATE OR REPLACE PROCEDURE CHECK_WITH_GRANT_OPTION (
    p_user VARCHAR2,
    p_table_name VARCHAR2,
    p_privilege VARCHAR2,
    p_with_grant OUT NUMBER
) AS
    v_count NUMBER;
BEGIN
    
    SELECT COUNT(*)
    INTO v_count
    FROM dba_tab_privs
    WHERE grantee = p_user AND table_name = p_table_name AND privilege = p_privilege AND grantable = 'YES';

    
    IF v_count > 0 THEN
        p_with_grant := 1; 
    ELSE
        p_with_grant := 0; 
    END IF;
END;
/ USER_TAB_PRIVS
select * FROM USER_TAB_PRIVS
    WHERE grantee = BARONHAN' AND table_name = 'HOTELBOOKING.USER_INFO' AND privilege = 'INSERT' AND grantable = 'YES';