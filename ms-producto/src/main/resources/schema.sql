DROP TABLE IF EXISTS producto;

CREATE TABLE producto (
  id IDENTITY PRIMARY KEY,
  nombre VARCHAR(250) NOT NULL,
  precio DECIMAL NOT NULL
);
