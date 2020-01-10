/* Populate table clients */

INSERT INTO users (user_name, password, enabled, name, surname, email) VALUES ('andres', '$2a$10$/KYyxw9MZZ7KGmEqoNWHb.9qkTrj/7tLIa1BNoBNyaFa/KevuMfUe', 1, 'Andres', 'Flores', 'takitaki@yahoo.com');
INSERT INTO users (user_name, password, enabled, name, surname, email) VALUES ('paco', '$2a$10$YrQSVENFt/oR2Am9DM9eZOdLcB2W.RuGZ63tx3T6DcokwIQXWR/.6', 1, 'Paco', 'Méndez', 'pacoFiesta@hotmail.es');

INSERT INTO roles (role_name) VALUES ('ROLE_USER');
INSERT INTO roles (role_name) VALUES ('ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES(1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES(2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES(2, 1);

INSERT INTO regions (id, name) VALUES(1,'Europe');
INSERT INTO regions (id, name) VALUES(2,'Asia');
INSERT INTO regions (id, name) VALUES(3,'Africa');
INSERT INTO regions (id, name) VALUES(4,'Oceanía');
INSERT INTO regions (id, name) VALUES(5,'America');


INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(1, 'Andrés', 'Guzmán', 'profesor@bolsadeideas.com', '2018-01-01');
INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(2, 'Mr. John', 'Doe', 'john.doe@gmail.com', '2018-01-02');
INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(3, 'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2018-01-03');
INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(4, 'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2018-01-04');
INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(5, 'Erich', 'Gamma', 'erich.gamma@gmail.com', '2018-02-01');
INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(2, 'Richard', 'Helm', 'richard.helm@gmail.com', '2018-02-10');
INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(3, 'Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2018-02-18');
INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(1, 'John', 'Vlissides', 'john.vlissides@gmail.com', '2018-02-28');
INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(1, 'Dr. James', 'Gosling', 'james.gosling@gmail.com', '2018-03-03');
INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(2, 'Magma', 'Lee', 'magma.lee@gmail.com', '2018-03-04');
INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(5, 'Tornado', 'Roe', 'tornado.roe@gmail.com', '2018-03-05');
INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(5, 'Luis David', 'Amador', 'luisdapolixe@gmail.com', '2018-03-06');

INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(2, 'Dr. James2', 'Gosling', 'james.gosling2@gmail.com', '2018-03-03');
INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(3, 'Magma2', 'Lee', 'magma.lee2@gmail.com', '2018-03-04');
INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(3, 'Tornado2', 'Roe', 'tornado.roe2@gmail.com', '2018-03-05');
INSERT INTO clients (region_id, name, surname, email, create_at) VALUES(1, 'Luis David2', 'Amador', 'luisdapolixe2@gmail.com', '2018-03-06');


