SELECT
    u.id AS user_id,
    u.email,
    p.first_name,
    p.last_name,
    p.photo_link,
    r.title AS role_title
FROM
    users u
        JOIN
    profiles p ON u.id = p.user_id
        JOIN
    users_roles ur ON u.id = ur.user_id
        JOIN
    roles r ON ur.role_id = r.id
WHERE
    u.id = 256;