--changeset 1:kenan
create table DATA_FILES
(
    ID                  VARCHAR2(100),
    FILE_NAME           VARCHAR2(50),
    UPLOAD_DATE         DATE,
    TEXT                VARCHAR2(4000),
    VERIFIED            CHAR(1) DEFAULT 'N',
    PLAGIARISM_DETECTED CHAR(1)
)