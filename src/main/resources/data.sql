INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (1, 'Cracking the Coding Interview', 'Gayle Laakmann McDowell', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (2, 'Clean Code: A Handbook of Agile Software Craftsmanship', 'Robert C. Martin', null, true);
INSERT INTO book (id, name, author, image_s3_id, is_visible)
VALUES (3, 'Designing Data-Intensive Applications', 'Martin Kleppmann', null, true);

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
VALUES (3, 'member@gmail.com', 'memberjonathan', 'Member Jonathan', '$2a$12$r99IjRf42iIiyMnnQNnLu.JDkWm.f5YTKgj5L2KZrxj9TTjPheL96', 3);
INSERT INTO user (id, email, username, full_name, password, role_id)
VALUES (4, 'member2@gmail.com', 'memberchris', 'Member Chris', '$2a$12$r99IjRf42iIiyMnnQNnLu.JDkWm.f5YTKgj5L2KZrxj9TTjPheL96', 3);

INSERT INTO guest_book (id, book_id, user_id, date_to_return, returned_date)
VALUES (1, 1, 3, null, null);
INSERT INTO guest_book (id, book_id, user_id, date_to_return, returned_date)
VALUES (2, 2, 4, '2023-10-15', null);
