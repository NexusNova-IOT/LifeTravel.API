CREATE TABLE report_reviews
(
    report_id bigint NOT NULL,
    review_id bigint NOT NULL,
    CONSTRAINT pk_report_reviews PRIMARY KEY (report_id, review_id)
)
GO

CREATE TABLE reports
(
    id           bigint IDENTITY (1, 1) NOT NULL,
    created_date datetime               NOT NULL,
    updated_date datetime,
    deleted      bit                    NOT NULL,
    agency_id    bigint                 NOT NULL,
    content           nvarchar(MAX),
    ai_recommendation nvarchar(MAX),
    CONSTRAINT pk_reports PRIMARY KEY (id)
)
GO

CREATE TABLE reviews
(
    id              bigint IDENTITY (1, 1) NOT NULL,
    created_date    datetime               NOT NULL,
    updated_date    datetime,
    deleted         bit                    NOT NULL,
    tourist_id      bigint                 NOT NULL,
    tour_package_id bigint                 NOT NULL,
    rating          int,
    review_text     varchar(255),
    CONSTRAINT pk_reviews PRIMARY KEY (id)
)
GO

ALTER TABLE reports
    ADD CONSTRAINT FK_REPORTS_ON_AGENCY FOREIGN KEY (agency_id) REFERENCES agencies (id)
GO

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_TOURIST FOREIGN KEY (tourist_id) REFERENCES tourists (id)
GO

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_TOUR_PACKAGE FOREIGN KEY (tour_package_id) REFERENCES tour_packages (id)
GO

ALTER TABLE report_reviews
    ADD CONSTRAINT fk_reprev_on_report FOREIGN KEY (report_id) REFERENCES reports (id)
GO

ALTER TABLE report_reviews
    ADD CONSTRAINT fk_reprev_on_review FOREIGN KEY (review_id) REFERENCES reviews (id)
GO