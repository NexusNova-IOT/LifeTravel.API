-- REGIONS INFO
INSERT INTO regions (title, img_url, description, created_date, deleted)
VALUES
    ('Coast', 'https://iot2023.blob.core.windows.net/imgs/regions/costa.jpg', 'The coast is where land and sea converge, shaping diverse ecosystems and breathtaking vistas.', GETDATE(), 0),
    ('Mountain Range', 'https://iot20231.blob.core.windows.net/imgs/regions/sierra.jpg', 'Mountain ranges are majestic geological formations, with towering peaks and unique ecosystems.', GETDATE(), 0),
    ('Jungle', 'https://iot20231.blob.core.windows.net/imgs/regions/selva.jpg', 'Jungles are lush, dense forests teeming with diverse wildlife, exotic plants, and untamed beauty.', GETDATE(), 0);
