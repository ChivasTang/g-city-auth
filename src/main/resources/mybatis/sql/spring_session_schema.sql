alter table SPRING_SESSION_ATTRIBUTES
    disable constraint pk_spring_session_attributes;
alter table SPRING_SESSION_ATTRIBUTES
    disable constraint fk_spring_session_attribute;
alter table SPRING_SESSION
    disable constraint pk_spring_session;
DROP TABLE SPRING_SESSION_ATTRIBUTES;
DROP TABLE SPRING_SESSION;

CREATE TABLE SPRING_SESSION
(
    PRIMARY_ID            varchar2(36) NOT NULL,
    SESSION_ID            varchar2(36) NOT NULL,
    CREATION_TIME         NUMBER       NOT NULL,
    LAST_ACCESS_TIME      NUMBER       NOT NULL,
    MAX_INACTIVE_INTERVAL NUMBER       NOT NULL,
    EXPIRY_TIME           NUMBER       NOT NULL,
    PRINCIPAL_NAME        varchar2(100)
);

CREATE TABLE SPRING_SESSION_ATTRIBUTES
(
    SESSION_PRIMARY_ID varchar2(36)  NOT NULL,
    ATTRIBUTE_NAME     varchar2(200) NOT NULL,
    ATTRIBUTE_BYTES    BLOB          NOT NULL
);
alter table SPRING_SESSION
    add constraint pk_spring_session primary key (PRIMARY_ID);
alter table SPRING_SESSION_ATTRIBUTES
    add constraint fk_spring_session_attribute foreign key (SESSION_PRIMARY_ID) references SPRING_SESSION (PRIMARY_ID) ON DELETE CASCADE;
alter table SPRING_SESSION_ATTRIBUTES
    add constraint pk_spring_session_attributes primary key (SESSION_PRIMARY_ID, ATTRIBUTE_NAME);




