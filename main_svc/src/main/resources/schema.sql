DROP TABLE IF EXISTS friendships;
DROP TABLE IF EXISTS subscriptions;
DROP TABLE IF EXISTS requests;
DROP TABLE IF EXISTS events_compilations;
DROP TABLE IF EXISTS compilations;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS locations;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	name VARCHAR(250) NOT NULL,
	email VARCHAR(254) NOT NULL UNIQUE
);

CREATE TABLE categories
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE locations
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    lat NUMERIC NOT NULL,
    lon NUMERIC NOT NULL
);

CREATE TABLE events
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY UNIQUE,
    initiator_id       BIGINT REFERENCES users (id) ON DELETE CASCADE,
    title              VARCHAR(120) NOT NULL,
    annotation         VARCHAR(2000) NOT NULL,
    description        VARCHAR(7000),
    category_id        BIGINT REFERENCES categories (id) ON DELETE RESTRICT,
    state              VARCHAR(256),
    paid               BOOLEAN,
    request_moderation BOOLEAN DEFAULT TRUE,
    participant_limit  INTEGER DEFAULT 0,
    location_id        BIGINT REFERENCES locations (id) ON DELETE SET NULL,
    created_on         TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    event_date         TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    published_on       TIMESTAMP WITHOUT TIME zone
);

CREATE TABLE compilations
(
    id     BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title  VARCHAR(50) NOT NULL UNIQUE,
    pinned BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS events_compilations
(
    compilation_id BIGINT REFERENCES compilations (id) ON DELETE CASCADE,
    event_id       BIGINT REFERENCES events (id) ON DELETE CASCADE,
    CONSTRAINT compilation_event_pk PRIMARY KEY(compilation_id, event_id)
);

CREATE TABLE requests (
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    requestor_id BIGINT REFERENCES users (id) ON DELETE CASCADE,
    event_id     BIGINT REFERENCES events (id) ON DELETE CASCADE,
    created      TIMESTAMP WITHOUT TIME ZONE,
    status       VARCHAR(64)
);

CREATE TABLE friendships (
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    friend1_id BIGINT REFERENCES users (id) ON DELETE CASCADE,
    friend2_id BIGINT REFERENCES users (id) ON DELETE CASCADE,
    status     VARCHAR(64) NOT NULL
    CONSTRAINT friendships_pk PRIMARY KEY(friend1_id, friend2_id)
);

CREATE TABLE subscriptions (
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id   BIGINT REFERENCES users (id) ON DELETE CASCADE,
    friend_id BIGINT REFERENCES users (id) ON DELETE CASCADE
    CONSTRAINT subscriptions_pk PRIMARY KEY(user_id, friend_id)
);