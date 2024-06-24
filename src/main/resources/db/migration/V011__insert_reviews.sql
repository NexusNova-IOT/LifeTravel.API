-- REVIEWS INFO
INSERT INTO reviews (created_date, updated_date, deleted, tourist_id, tour_package_id, rating, review_text)
VALUES
    (GETDATE(), NULL, 0, 1, 5, 4, 'The tour was amazing! I wish there were more benches with shade along the way.'),
    (GETDATE(), NULL, 0, 2, 15, 5, 'Absolutely loved the tour! The guides were so knowledgeable and friendly.'),
    (GETDATE(), NULL, 0, 3, 25, 3, 'An average experience. It would have been better with clearer directions.'),
    (GETDATE(), NULL, 0, 1, 10, 4, 'Fantastic tour, but it would be perfect with more restroom breaks.'),
    (GETDATE(), NULL, 0, 2, 20, 5, 'Best tour ever! The scenery was breathtaking, and the activities were so much fun.'),
    (GETDATE(), NULL, 0, 3, 30, 2, 'A bit disappointed, the tour didn''t quite meet my expectations.'),
    (GETDATE(), NULL, 0, 1, 1, 5, 'Unforgettable experience! I loved every moment of it.'),
    (GETDATE(), NULL, 0, 2, 12, 4, 'Very informative tour. It would have been even better with more interaction with locals.'),
    (GETDATE(), NULL, 0, 3, 28, 3, 'Good tour, but the organization could improve. It was hard to follow at times.'),
    (GETDATE(), NULL, 0, 1, 8, 4, 'Highly recommended! I wish I had more time to explore each stop on the tour.');
