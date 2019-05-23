SELECT * FROM users order by id;

SELECT * FROM jobs WHERE date >= DATE_TRUNC('month', NOW()) - INTERVAL '0 hour' ORDER BY user_id;

INSERT INTO public.jobs(
    id, date, title, user_id)
VALUES (14, '2019-01-01 12:00:00.000000', 'story3.docx', 1);


INSERT INTO users (id, login, password) values (
 7, 'tomek8', '$2a$10$wb2bCJss3flkLUCpq6D5GemoANM9jwaIrt7mNTAjo5UFt2P77ruwK');



SELECT u.login, count(*) count FROM users u
INNER JOIN jobs j ON u.id = j.user_id
WHERE date >= DATE_TRUNC('month', NOW())
GROUP BY u.login ORDER BY count DESC;


SELECT j.title, count(*) count
FROM users u
INNER JOIN jobs j ON u.id = j.user_id
WHERE date >= DATE_TRUNC('month', NOW())
GROUP BY j.title
ORDER BY count DESC ;


SELECT DATE_TRUNC('day', date) AS date_trunc, count(id)
FROM jobs
GROUP BY date_trunc
ORDER BY date_trunc DESC

SELECT * FROM jobs;

5	2019-05-11 15:26:47.252	new-sotry.docx	2
6	2019-05-12 00:00:00	story.docx	3
7	2019-05-12 01:00:00	story2.docx	4
9	2019-05-13 12:00:00	story3.docx	5
11	2019-05-16 12:00:00	story3.docx	6
12	2019-05-15 12:00:00	story3.docx	6
13	2019-05-15 12:00:00	story3.docx	7
INSERT INTO jobs (id, date, title, user_id) values
        (8,	'2019-05-12 12:00:00',	'story3.docx',	1),
    (5,	'2019-05-11 15:26:47.252',	'new-sotry.docx',	2),
    (6,	'2019-05-12 00:00:00',	'story.docx',	3),
    (7,	'2019-05-12 01:00:00',	'story2.docx',	4),
    (9,	'2019-05-13 12:00:00',	'story3.docx',	5),
    (11, '2019-05-16 12:00:00',	'story3.docx',	6),
    (12, '2019-05-15 12:00:00',	'story3.docx',	6),
    (13,	'2019-05-15 12:00:00',	'story3.docx',	7);

UPDATE jobs SET title ='α μ Ω' WHERE jobs.title = 'story3.docx';

INSERT INTO users (id, login) VALUES
(2, 'tomek2'),
(3, 'tomek3'),
(4, 'tomek4'),
(5, 'tomek5'),
(6, 'tomek6'),
(7, 'tomek7');

DROP SEQUENCE hibernate_sequence;
