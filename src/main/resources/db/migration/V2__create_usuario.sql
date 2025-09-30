CREATE TABLE sprint3_usuario (
    id_usuario INT IDENTITY(1,1) PRIMARY KEY,
    ds_email NVARCHAR(255) NOT NULL UNIQUE,
    ds_senha NVARCHAR(255) NOT NULL,
    ds_role NVARCHAR(20) NOT NULL
);