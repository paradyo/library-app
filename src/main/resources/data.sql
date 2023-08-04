INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (1, 'Cracking the Coding Interview', 'Gayle Laakmann McDowell', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (2, 'Clean Code: A Handbook of Agile Software Craftsmanship', 'Robert C. Martin', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (3, 'Designing Data-Intensive Applications', 'Martin Kleppmann', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (4, 'The Pragmatic Programmer', 'Andrew Hunt, David Thomas', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (5, 'Head First Design Patterns', 'Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (6, 'Code Complete: A Practical Handbook of Software Construction', 'Steve McConnell', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (7, 'Introduction to Algorithms', 'Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (8, 'Refactoring: Improving the Design of Existing Code', 'Martin Fowler', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (9, 'Clean Architecture: A Craftsmans Guide to Software Structure and Design', 'Robert C. Martin', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (10, 'Domain-Driven Design: Tackling Complexity in the Heart of Software', 'Eric Evans', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (11, 'The Clean Coder: A Code of Conduct for Professional Programmers', 'Robert C. Martin', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (12, 'Effective Java', 'Joshua Bloch', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (13, 'Principles of Object-Oriented Programming in JavaScript', 'Nicholas C. Zakas', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (14, 'Learning React: Functional Web Development with React and Redux', 'Alex Banks, Eve Porcello', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (15, 'Cracking Codes with Python: An Introduction to Building and Breaking Ciphers', 'Al Sweigart', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (16, 'Algorithms to Live By: The Computer Science of Human Decisions', 'Brian Christian, Tom Griffiths', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (17, 'Python Crash Course: A Hands-On, Project-Based Introduction to Programming', 'Eric Matthes', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (18, 'Eloquent JavaScript: A Modern Introduction to Programming', 'Marijn Haverbeke', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (19, 'The Clean Architecture in Python', 'Leonardo Giordani', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (20, 'The Phoenix Project: A Novel about IT, DevOps, and Helping Your Business Win', 'Gene Kim, Kevin Behr, George Spafford', null, true);

INSERT INTO role (id, name)
VALUES (1, 'admin');
INSERT INTO role (id, name)
VALUES (2, 'employee');
INSERT INTO role (id, name)
VALUES (3, 'member');

INSERT INTO user (id, email, username, full_name, password, role_id)
VALUES (1, 'admin@gmail.com', 'admin', 'Admin John', '$2a$12$r99IjRf42iIiyMnnQNnLu.JDkWm.f5YTKgj5L2KZrxj9TTjPheL96', 1);
INSERT INTO user (id, email, username, full_name, password, role_id)
VALUES (2, 'employee@gmail.com', 'empjessy', 'Employee Jessy', '$2a$12$r99IjRf42iIiyMnnQNnLu.JDkWm.f5YTKgj5L2KZrxj9TTjPheL96', 2);
INSERT INTO user (id, email, username, full_name, password, role_id)
VALUES (3, 'employee2@gmail.com', 'empjohn', 'Employee John', '$2a$12$r99IjRf42iIiyMnnQNnLu.JDkWm.f5YTKgj5L2KZrxj9TTjPheL96', 2);
INSERT INTO user (id, email, username, full_name, password, role_id)
VALUES (4, 'employee3@gmail.com', 'empchris', 'Employee Chris', '$2a$12$r99IjRf42iIiyMnnQNnLu.JDkWm.f5YTKgj5L2KZrxj9TTjPheL96', 2);
INSERT INTO user (id, email, username, full_name, password, role_id)
VALUES (5, 'employee4@gmail.com', 'empalice', 'Employee Alice', '$2a$12$r99IjRf42iIiyMnnQNnLu.JDkWm.f5YTKgj5L2KZrxj9TTjPheL96', 2);
INSERT INTO user (id, email, username, full_name, password, role_id)
VALUES (6, 'employee5@gmail.com', 'empbob', 'Employee Bob', '$2a$12$r99IjRf42iIiyMnnQNnLu.JDkWm.f5YTKgj5L2KZrxj9TTjPheL96', 2);
INSERT INTO user (id, email, username, full_name, password, role_id)
VALUES (7, 'member@gmail.com', 'memberjonathan', 'Member Jonathan', '$2a$12$r99IjRf42iIiyMnnQNnLu.JDkWm.f5YTKgj5L2KZrxj9TTjPheL96', 3);
INSERT INTO user (id, email, username, full_name, password, role_id)
VALUES (8, 'member2@gmail.com', 'memberchris', 'Member Chris', '$2a$12$r99IjRf42iIiyMnnQNnLu.JDkWm.f5YTKgj5L2KZrxj9TTjPheL96', 3);
INSERT INTO user (id, email, username, full_name, password, role_id)
VALUES (9, 'member3@gmail.com', 'memberalice', 'Member Alice', '$2a$12$r99IjRf42iIiyMnnQNnLu.JDkWm.f5YTKgj5L2KZrxj9TTjPheL96', 3);
INSERT INTO user (id, email, username, full_name, password, role_id)
VALUES (10, 'member4@gmail.com', 'memberbob', 'Member Bob', '$2a$12$r99IjRf42iIiyMnnQNnLu.JDkWm.f5YTKgj5L2KZrxj9TTjPheL96', 3);
INSERT INTO user (id, email, username, full_name, password, role_id)
VALUES (11, 'member5@gmail.com', 'memberemily', 'Member Emily', '$2a$12$r99IjRf42iIiyMnnQNnLu.JDkWm.f5YTKgj5L2KZrxj9TTjPheL96', 3);

INSERT INTO guest_book (id, book_id, user_id, date_to_return, returned_date)
VALUES (1, 1, 3, null, null);
INSERT INTO guest_book (id, book_id, user_id, date_to_return, returned_date)
VALUES (2, 2, 4, '2023-10-15', null);
INSERT INTO guest_book (id, book_id, user_id, date_to_return, returned_date)
VALUES (3, 3, 5, '2023-10-20', null);
INSERT INTO guest_book (id, book_id, user_id, date_to_return, returned_date)
VALUES (4, 1, 6, '2023-10-25', null);
INSERT INTO guest_book (id, book_id, user_id, date_to_return, returned_date)
VALUES (5, 2, 3, '2023-11-05', null);