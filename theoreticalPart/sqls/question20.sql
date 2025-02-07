DELETE FROM profiles
WHERE user_id = 78;

DELETE FROM users_roles
WHERE user_id = 78;

DELETE FROM comments
WHERE author_id = 78;

DELETE FROM users
WHERE id = 78;

-- if foreign keys sets with ON DELETE CASCADE, it will enough to execute this:

DELETE FROM users
WHERE id = 78;