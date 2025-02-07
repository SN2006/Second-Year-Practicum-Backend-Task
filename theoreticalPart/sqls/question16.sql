SELECT
    a.id AS article_id,
    a.title AS article_title,
    a.body AS article_body,
    c.id AS comment_id,
    c.title AS comment_title,
    c.body AS comment_body,
    c.parent_id AS comment_parent_id
FROM
    articles a
        JOIN
    comments c ON a.id = c.article_id
WHERE
    a.id = 101;