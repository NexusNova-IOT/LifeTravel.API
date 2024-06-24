CREATE TABLE departments
(
    id           bigint IDENTITY (1, 1) NOT NULL,
    created_date datetime               NOT NULL,
    updated_date datetime,
    deleted      bit                    NOT NULL,
    name         varchar(255),
    region_id    bigint                 NOT NULL,
    CONSTRAINT pk_departments PRIMARY KEY (id)
)
GO

CREATE TABLE temperatures
(
    id            bigint IDENTITY (1, 1) NOT NULL,
    department_id bigint                 NOT NULL,
    value         float(53)              NOT NULL,
    measured_at   datetime               NOT NULL,
    CONSTRAINT pk_temperatures PRIMARY KEY (id)
)
GO

CREATE TABLE activities
(
    id           bigint IDENTITY (1, 1) NOT NULL,
    created_date datetime               NOT NULL,
    updated_date datetime,
    deleted      bit                    NOT NULL,
    title        varchar(255),
    img_url      varchar(255),
    CONSTRAINT pk_activities PRIMARY KEY (id)
)
GO

CREATE TABLE agencies
(
    id               bigint IDENTITY (1, 1) NOT NULL,
    created_date     datetime               NOT NULL,
    updated_date     datetime,
    deleted          bit                    NOT NULL,
    user_id          varchar(255)           NOT NULL,
    legal_name       varchar(100),
    ruc              bigint,
    address          varchar(240),
    agency_photo_url varchar(255),
    description      varchar(255),
    phone_number     varchar(255),
    web_page_url     varchar(255),
    CONSTRAINT pk_agencies PRIMARY KEY (id)
)
GO

CREATE TABLE assigned_vehicles
(
    tour_package_id bigint NOT NULL,
    vehicle_id      bigint NOT NULL
)
GO

CREATE TABLE bookings
(
    id              bigint IDENTITY (1, 1) NOT NULL,
    created_date    datetime               NOT NULL,
    updated_date    datetime,
    deleted         bit                    NOT NULL,
    serie_number    varchar(255),
    serie           varchar(255),
    number          varchar(255),
    tourist_id      bigint                 NOT NULL,
    tourist_user_id varchar(255)           NOT NULL,
    tour_package_id bigint                 NOT NULL,
    agency_user_id  varchar(255)           NOT NULL,
    selected_date   datetime,
    start_datetime  datetime,
    end_datetime    datetime,
    CONSTRAINT pk_bookings PRIMARY KEY (id)
)
GO

CREATE TABLE correlative
(
    id           bigint IDENTITY (1, 1) NOT NULL,
    created_date datetime               NOT NULL,
    updated_date datetime,
    deleted      bit                    NOT NULL,
    serie        varchar(255),
    number       varchar(255),
    digits       int,
    type         varchar(255),
    CONSTRAINT pk_correlative PRIMARY KEY (id)
)
GO

CREATE TABLE destinations
(
    id              bigint IDENTITY (1, 1) NOT NULL,
    created_date    datetime               NOT NULL,
    updated_date    datetime,
    deleted         bit                    NOT NULL,
    latitude        decimal(13, 10),
    longitude       decimal(13, 10),
    name            varchar(255),
    tour_package_id bigint                 NOT NULL,
    CONSTRAINT pk_destinations PRIMARY KEY (id)
)
GO

CREATE TABLE regions
(
    id           bigint IDENTITY (1, 1) NOT NULL,
    created_date datetime               NOT NULL,
    updated_date datetime,
    deleted      bit                    NOT NULL,
    title        varchar(255),
    description  varchar(255),
    img_url      varchar(255),
    CONSTRAINT pk_regions PRIMARY KEY (id)
)
GO

CREATE TABLE roles
(
    id   bigint IDENTITY (1, 1) NOT NULL,
    name varchar(255),
    CONSTRAINT pk_roles PRIMARY KEY (id)
)
GO

CREATE TABLE schedule
(
    id              bigint IDENTITY (1, 1) NOT NULL,
    created_date    datetime               NOT NULL,
    updated_date    datetime,
    deleted         bit                    NOT NULL,
    day             varchar(255),
    start_hour      varchar(255),
    start_minute    varchar(255),
    start_day_time  varchar(255),
    end_hour        varchar(255),
    end_minute      varchar(255),
    end_day_time    varchar(255),
    tour_package_id bigint                 NOT NULL,
    CONSTRAINT pk_schedule PRIMARY KEY (id)
)
GO

CREATE TABLE tour_package_activities
(
    activity_id     bigint NOT NULL,
    tour_package_id bigint NOT NULL
)
GO

CREATE TABLE tour_packages
(
    id            bigint IDENTITY (1, 1) NOT NULL,
    created_date  datetime               NOT NULL,
    updated_date  datetime,
    deleted       bit                    NOT NULL,
    title         varchar(255),
    description   nvarchar(MAX),
    img_url       varchar(255),
    price         decimal(18, 0),
    visible       bit                    NOT NULL,
    rating        float(53),
    latitude      decimal(13, 10),
    longitude     decimal(13, 10),
    region_id     bigint                 NOT NULL,
    department_id bigint                 NOT NULL,
    agency_id     bigint                 NOT NULL,
    CONSTRAINT pk_tour_packages PRIMARY KEY (id)
)
GO

CREATE TABLE tourists
(
    id                     bigint IDENTITY (1, 1) NOT NULL,
    created_date           datetime               NOT NULL,
    updated_date           datetime,
    deleted                bit                    NOT NULL,
    user_id                varchar(255)           NOT NULL,
    name                   varchar(255),
    birth_date             datetime,
    phone_number           varchar(255),
    photo_url              varchar(255),
    emergency_phone_number varchar(255),
    CONSTRAINT pk_tourists PRIMARY KEY (id)
)
GO

CREATE TABLE tracking_wereables
(
    id              bigint IDENTITY (1, 1) NOT NULL,
    created_date    datetime               NOT NULL,
    updated_date    datetime,
    deleted         bit                    NOT NULL,
    tourist_id      bigint                 NOT NULL,
    tourist_user_id varchar(255)           NOT NULL,
    latitude        decimal(13, 10),
    longitude       decimal(13, 10),
    serie_number    varchar(255),
    serie           varchar(255),
    number          varchar(255),
    CONSTRAINT pk_tracking_wereables PRIMARY KEY (id)
)
GO

CREATE TABLE users
(
    id               varchar(255) NOT NULL,
    email            varchar(100) NOT NULL,
    google_name      varchar(100) NOT NULL,
    google_photo_url varchar(255),
    role_id          bigint       NOT NULL,
    created_date     datetime     NOT NULL,
    deleted          bit          NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
)
GO

CREATE TABLE vehicles
(
    id           bigint IDENTITY (1, 1) NOT NULL,
    created_date datetime               NOT NULL,
    updated_date datetime,
    deleted      bit                    NOT NULL,
    brand        varchar(255),
    model        varchar(255),
    plate        varchar(255),
    capacity     int,
    driver_name  varchar(255),
    weight       decimal(18, 0),
    img_url      varchar(255),
    status       smallint,
    agency_id    bigint                 NOT NULL,
    CONSTRAINT pk_vehicles PRIMARY KEY (id)
)
GO

CREATE TABLE weather_sensors
(
    id             bigint IDENTITY (1, 1) NOT NULL,
    created_date   datetime               NOT NULL,
    updated_date   datetime,
    deleted        bit                    NOT NULL,
    destination_id bigint                 NOT NULL,
    agency_id      bigint                 NOT NULL,
    agency_user_id varchar(255)           NOT NULL,
    temperature    float(53),
    humidity       float(53),
    serie_number   varchar(255),
    serie          varchar(255),
    number         varchar(255),
    CONSTRAINT pk_weather_sensors PRIMARY KEY (id)
)
GO

CREATE TABLE weight_balances
(
    id           bigint IDENTITY (1, 1) NOT NULL,
    created_date datetime               NOT NULL,
    updated_date datetime,
    deleted      bit                    NOT NULL,
    vehicle_id   bigint                 NOT NULL,
    weight       float(53),
    serie_number varchar(255),
    serie        varchar(255),
    number       varchar(255),
    CONSTRAINT pk_weight_balances PRIMARY KEY (id)
)
GO

ALTER TABLE agencies
    ADD CONSTRAINT uc_agencies_user UNIQUE (user_id)
GO

ALTER TABLE tourists
    ADD CONSTRAINT uc_tourists_user UNIQUE (user_id)
GO

ALTER TABLE tracking_wereables
    ADD CONSTRAINT uc_tracking_wereables_tourist UNIQUE (tourist_id)
GO

ALTER TABLE weather_sensors
    ADD CONSTRAINT uc_weather_sensors_destination UNIQUE (destination_id)
GO

ALTER TABLE agencies
    ADD CONSTRAINT FK_AGENCIES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id)
GO

ALTER TABLE bookings
    ADD CONSTRAINT FK_BOOKINGS_ON_AGENCY_USER FOREIGN KEY (agency_user_id) REFERENCES users (id)
GO

ALTER TABLE bookings
    ADD CONSTRAINT FK_BOOKINGS_ON_TOURIST FOREIGN KEY (tourist_id) REFERENCES tourists (id)
GO

ALTER TABLE bookings
    ADD CONSTRAINT FK_BOOKINGS_ON_TOURIST_USER FOREIGN KEY (tourist_user_id) REFERENCES users (id)
GO

ALTER TABLE bookings
    ADD CONSTRAINT FK_BOOKINGS_ON_TOUR_PACKAGE FOREIGN KEY (tour_package_id) REFERENCES tour_packages (id)
GO

ALTER TABLE departments
    ADD CONSTRAINT FK_DEPARTMENTS_ON_REGION FOREIGN KEY (region_id) REFERENCES regions (id)
GO

ALTER TABLE destinations
    ADD CONSTRAINT FK_DESTINATIONS_ON_TOUR_PACKAGE FOREIGN KEY (tour_package_id) REFERENCES tour_packages (id)
GO

ALTER TABLE schedule
    ADD CONSTRAINT FK_SCHEDULE_ON_TOUR_PACKAGE FOREIGN KEY (tour_package_id) REFERENCES tour_packages (id)
GO

ALTER TABLE tourists
    ADD CONSTRAINT FK_TOURISTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id)
GO

ALTER TABLE tour_packages
    ADD CONSTRAINT FK_TOUR_PACKAGES_ON_AGENCY FOREIGN KEY (agency_id) REFERENCES agencies (id)
GO

ALTER TABLE tour_packages
    ADD CONSTRAINT FK_TOUR_PACKAGES_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES departments (id)
GO

ALTER TABLE tour_packages
    ADD CONSTRAINT FK_TOUR_PACKAGES_ON_REGION FOREIGN KEY (region_id) REFERENCES regions (id)
GO

ALTER TABLE tracking_wereables
    ADD CONSTRAINT FK_TRACKING_WEREABLES_ON_TOURIST FOREIGN KEY (tourist_id) REFERENCES tourists (id)
GO

ALTER TABLE tracking_wereables
    ADD CONSTRAINT FK_TRACKING_WEREABLES_ON_TOURIST_USER FOREIGN KEY (tourist_user_id) REFERENCES users (id)
GO

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id)
GO

ALTER TABLE vehicles
    ADD CONSTRAINT FK_VEHICLES_ON_AGENCY FOREIGN KEY (agency_id) REFERENCES agencies (id)
GO

ALTER TABLE weather_sensors
    ADD CONSTRAINT FK_WEATHER_SENSORS_ON_AGENCY FOREIGN KEY (agency_id) REFERENCES agencies (id)
GO

ALTER TABLE weather_sensors
    ADD CONSTRAINT FK_WEATHER_SENSORS_ON_AGENCY_USER FOREIGN KEY (agency_user_id) REFERENCES users (id)
GO

ALTER TABLE weather_sensors
    ADD CONSTRAINT FK_WEATHER_SENSORS_ON_DESTINATION FOREIGN KEY (destination_id) REFERENCES destinations (id)
GO

ALTER TABLE weight_balances
    ADD CONSTRAINT FK_WEIGHT_BALANCES_ON_VEHICLE FOREIGN KEY (vehicle_id) REFERENCES vehicles (id)
GO

ALTER TABLE assigned_vehicles
    ADD CONSTRAINT fk_assveh_on_tour_package FOREIGN KEY (tour_package_id) REFERENCES tour_packages (id)
GO

ALTER TABLE assigned_vehicles
    ADD CONSTRAINT fk_assveh_on_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles (id)
GO

ALTER TABLE tour_package_activities
    ADD CONSTRAINT fk_toupacact_on_activity FOREIGN KEY (activity_id) REFERENCES activities (id)
GO

ALTER TABLE tour_package_activities
    ADD CONSTRAINT fk_toupacact_on_tour_package FOREIGN KEY (tour_package_id) REFERENCES tour_packages (id)
GO

ALTER TABLE temperatures
    ADD CONSTRAINT FK_TEMPERATURES_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES departments (id)
GO
