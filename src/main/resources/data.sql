DROP TABLE IF EXISTS files_upload;

CREATE TABLE files_upload (
    file_name VARCHAR(250) NOT NULL PRIMARY KEY,
    file_data MEDIUMBLOB NOT NULL
);

