CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(100) NOT NULL,
                       published_year INTEGER,
                       isbn VARCHAR(20) UNIQUE,
                       available BOOLEAN DEFAULT TRUE
);