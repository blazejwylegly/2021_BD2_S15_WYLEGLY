INSERT INTO credentials(id, email_address, password, salt, username)
VALUES (1,'root', 'root', 'root', 'root');
;
INSERT INTO permissions(id)
VALUES (1);

INSERT INTO user(id, first_name, last_name, photo_url, credentials_id, permissions_id)
VALUES (1, 'root_firstname', 'root_lastanem', 'root_photo_url', 1, 1);
