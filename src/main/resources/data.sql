INSERT INTO BOOKS(NAME, DESCRIPTION, PUBLICATION_YEAR)
VALUES ('The Hunger Games', 'Sixteen-year-old Katniss Everdeen, who lives alone with her mother and younger sister, regards it as a death sentence when she steps forward to take her sister''s place in the Games. But Katniss has been close to dead before—and survival, for her, is second nature. Without really meaning to, she becomes a contender. But if she is to win, she will have to start making choices that weight survival against humanity and life against love.', 2008),
       ('Pride and Prejudice', 'Since its immediate success in 1813, Pride and Prejudice has remained one of the most popular novels in the English language. Jane Austen called this brilliant work "her own darling child" and its vivacious heroine, Elizabeth Bennet, "as delightful a creature as ever appeared in print." The romantic clash between the opinionated Elizabeth and her proud beau, Mr. Darcy, is a splendid performance of civilized sparring. And Jane Austen''s radiant wit sparkles as her characters dance a delicate quadrille of flirtation and intrigue, making this book the most superb comedy of manners of Regency England.', 1813),
       ('The Complete Novels', 'Few novelists have conveyed the subtleties and nuances of their own social milieu with the wit and insight of Jane Austen. Through her vivacious and spirited heroines and their circle, she paints vivid portraits of English middle-class life as the eighteenth century came to a close. Each of the novels is a love story and a story about marriage — marriage for love, for financial security, for social status. But they are not mere romances; ironic, comic and wise, they are masterly studies of the society Jane Austen observed. The seven novels in this volume contain some of the most brilliant, dazzling prose in the English language.', 1981);


INSERT INTO AUTHORS (NAME)
VALUES ('Suzanne Collins'),
       ('Jane Austen'),
       ('Anna Quindlen');

INSERT INTO GENRES (NAME)
VALUES ('classic'),
       ('fiction'),
       ('romance'),
       ('historical'),
       ('novel'),
       ('fantasy'),
       ('science fiction');

INSERT INTO BOOKS_GENRES(BOOK_ID, GENRE_ID)
VALUES (1, 2),
       (1, 6),
       (1, 7),
       (2, 2),
       (2, 4),
       (2, 5),
       (3, 1),
       (3, 2),
       (3, 3),
       (3, 4);

INSERT INTO BOOKS_AUTHORS(BOOK_ID, AUTHOR_ID)
VALUES (1, 1),
       (2, 2),
       (2, 3),
       (3, 2);

INSERT INTO USERS (EMAIL, PASSWORD, ROLE)
VALUES ('client1@gmail.com', 'password123', 'CLIENT'),
       ('client2@gmail.com', 'qwerty', 'CLIENT'),
       ('admin@gmail.com', 'admin', 'ADMIN');

INSERT INTO FAVOURITE_BOOKS(USER_ID, BOOK_ID)
VALUES (1, 1),
       (1, 3),
       (2, 2)