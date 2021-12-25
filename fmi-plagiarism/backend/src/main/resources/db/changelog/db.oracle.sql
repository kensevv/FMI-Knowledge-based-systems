--changeset 1:kenan
create table DATA_FILES
(
    ID              VARCHAR2(100) PRIMARY KEY,
    FILE_NAME       VARCHAR2(50)        not null,
    UPLOAD_DATE     DATE                not null,
    TEXT            BLOB                not null,
    VERIFIED        CHAR(1) DEFAULT 'N' not null,
    PLAGIARISM_RATE NUMBER(5, 2)
)
