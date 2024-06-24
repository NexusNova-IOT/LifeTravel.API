-- ACTIVITIES INFO
INSERT INTO activities (title, img_url, created_date, deleted)
VALUES
    ('Camping', 'https://iot20232.blob.core.windows.net/imgs/activities/camping.png', '2023-10-25', 0),
    ('Hiking', 'https://iot20232.blob.core.windows.net/imgs/activities/hiking.png', '2023-10-25', 0),
    ('Skiing', 'https://iot20232.blob.core.windows.net/imgs/activities/skiing.png', '2023-10-25', 0),
    ('Rafting', 'https://iot20232.blob.core.windows.net/imgs/activities/rafting.png', '2023-10-25', 0),
    ('Cycling', 'https://iot20232.blob.core.windows.net/imgs/activities/cycling.png', '2020-01-01', 0),
    ('Kayaking', 'https://iot20232.blob.core.windows.net/imgs/activities/kayaking.png', '2020-01-01', 0),
    ('Surfing', 'https://iot20232.blob.core.windows.net/imgs/activities/surfing.png', '2020-01-01', 0),
    ('Diving', 'https://iot20232.blob.core.windows.net/imgs/activities/diving.png', '2023-10-25', 0),
    ('Sightseeing', 'https://iot20232.blob.core.windows.net/imgs/activities/sightseeing.png', '2023-10-25', 0),
    ('QuadBiking', 'https://iot20232.blob.core.windows.net/imgs/activities/quadbike.png', '2023-10-25', 0);

select * from activities;


-- REGIONS INFO
INSERT INTO regions (title, img_url, description, created_date, deleted)
VALUES
    ('Coast', 'https://iot2023.blob.core.windows.net/imgs/regions/costa.jpg', 'The coast is where land and sea converge, shaping diverse ecosystems and breathtaking vistas.', '2023-10-25', 0),
    ('Mountain Range', 'https://iot20231.blob.core.windows.net/imgs/regions/sierra.jpg', 'Mountain ranges are majestic geological formations, with towering peaks and unique ecosystems.', '2023-10-25', 0),
    ('Jungle', 'https://iot20231.blob.core.windows.net/imgs/regions/selva.jpg', 'Jungles are lush, dense forests teeming with diverse wildlife, exotic plants, and untamed beauty.', '2023-10-25', 0);

select * from regions;


-- DEPARTMENTS INFO
INSERT INTO departments (name, region_id, created_date, deleted)
VALUES
    ('Lima', 1, '2023-10-25', 0),
    ('Tumbes', 1, '2023-10-25', 0),
    ('Piura', 1, '2023-10-25', 0),
    ('Lambayeque', 1, '2023-10-25', 0),
    ('La Libertad', 1, '2023-10-25', 0),
    ('Ancash', 1, '2023-10-25', 0),
    ('Ica', 1, '2023-10-25', 0),
    ('Arequipa', 1, '2023-10-25', 0),
    ('Moquegua', 1, '2023-10-25', 0),
    ('Tacna', 1, '2023-10-25', 0),

    ('Apurimac', 2, '2023-10-25', 0),
    ('Ayacucho', 2, '2023-10-25', 0),
    ('Cajamarca', 2, '2023-10-25', 0),
    ('Cuzco', 2, '2023-10-25', 0),
    ('Huanuco', 2, '2023-10-25', 0),
    ('Huancavelica', 2, '2023-10-25', 0),
    ('Junin', 2, '2023-10-25', 0),
    ('Pasco', 2, '2023-10-25', 0),
    ('Puno', 2, '2023-10-25', 0),

    ('Loreto', 3, '2023-10-25', 0),
    ('Amazonas', 3, '2023-10-25', 0),
    ('San Martin', 3, '2023-10-25', 0),
    ('Ucayali', 3, '2023-10-25', 0),
    ('Madre de Dios', 3, '2023-10-25', 0);

select * from departments;


-- AGENCY_USERS INFO
INSERT INTO users (id, email, google_name, google_photo_url, created_date, deleted, role_id)
VALUES
    ('BNqsE6gkjlZoEVIxf9qt92lXyJB3', 'agency1@gmail.com', 'Agencia_1', '', '2023-10-25', 0,2),
    ('RKOQXaT4hHhU7sCMOnXgnwKm1Cf2', 'agency2@gmail.com', 'Agencia 2', '', '2023-10-25', 0,2),
    ('c4O2PMViokW4gzD3tB3Mfkn7g1l2', 'agency3@gmail.com', 'Agencia 3', '', '2023-10-25', 0,2),
    ('6WKjj6oyzrMTUfuw8j7vEks3Yuu1', 'tourist1@gmail.com', 'Turista 1', '', '2023-10-25', 0,2),
    ('MxqYidFJ94c17BkAiCOkpGxwwKD3', 'tourist2@gmail.com', 'Turista 2', '', '2023-10-25', 0,2),
    ('TJbWODP4xwOMvgwyqrYrbPeDnlZ2', 'tourist3@gmail.com', 'Turista 3', '', '2023-10-25', 0,2)

select * from users;


-- AGENCY INFO
INSERT INTO agencies (user_id, ruc, address, agency_photo_url, description, legal_name, phone_number, web_page_url ,deleted)
VALUES
    ('BNqsE6gkjlZoEVIxf9qt92lXyJB3', 20602698603, 'Av Alcanflores 1190, Miraflores', 'https://iot20232.blob.core.windows.net/imgs/agencies/agencia1.jpg',
     'Agencia de viajes con gran experiencia en tours locales de la ciudad', 'FerturPeru Travel S.A.C.', '992817047', 'www.ferturtravel.pe',0),
    ('RKOQXaT4hHhU7sCMOnXgnwKm1Cf2', 20419624021, 'C. Jerusalen 603, Arequipa', 'https://iot20232.blob.core.windows.net/imgs/agencies/agencia2.jpg',
     N'Agencia premiada por la ONT por 3 años consecutivos.', 'Tierra Adentro S.A.C.', '996593729', 'www.tierraadentro.com.pe', 0),
    ('c4O2PMViokW4gzD3tB3Mfkn7g1l2', 20605476971, 'C. Mercaderes 224, Cuzco', 'https://iot20232.blob.core.windows.net/imgs/agencies/agencia3.jpg',
     'Somos lideres en turismo internacional y trilingue de las mejores ruinas de LATAM', 'Inka Travel S.A.C.', '987112633', 'www.incatravel.com', 0);

select * from agencies;


-- ROLES INFO
INSERT INTO roles (name)
VALUES
    ('ROLE_TOURIST'),
    ('ROLE_AGENCY');

select * from roles;