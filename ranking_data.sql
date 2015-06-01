--Marki samochodów

INSERT INTO brand (id, name) VALUES (1, 'Alfa Romeo');
INSERT INTO brand (id, name) VALUES (2, 'Audi');
INSERT INTO brand (id, name) VALUES (3, 'BMW');
INSERT INTO brand (id, name) VALUES (4, 'Cadillac');
INSERT INTO brand (id, name) VALUES (5, 'Chevrolet');
INSERT INTO brand (id, name) VALUES (6, 'Chrysler');
INSERT INTO brand (id, name) VALUES (7, 'Citroen');
INSERT INTO brand (id, name) VALUES (8, 'Daewoo');
INSERT INTO brand (id, name) VALUES (9, 'Dodge');
INSERT INTO brand (id, name) VALUES (10, 'Ferrari');
INSERT INTO brand (id, name) VALUES (11, 'FIAT');
INSERT INTO brand (id, name) VALUES (12, 'Ford');
INSERT INTO brand (id, name) VALUES (13, 'Honda');
INSERT INTO brand (id, name) VALUES (14, 'Hyundai');
INSERT INTO brand (id, name) VALUES (15, 'Jeep');
INSERT INTO brand (id, name) VALUES (16, 'Kia');
INSERT INTO brand (id, name) VALUES (17, 'Lamborghini');
INSERT INTO brand (id, name) VALUES (18, 'Mazda');
INSERT INTO brand (id, name) VALUES (19, 'Mercedes-Benz');
INSERT INTO brand (id, name) VALUES (20, 'Nissan');
INSERT INTO brand (id, name) VALUES (21, 'Opel');
INSERT INTO brand (id, name) VALUES (22, 'Peugeot');
INSERT INTO brand (id, name) VALUES (23, 'Porsche');
INSERT INTO brand (id, name) VALUES (24, 'Renault');
INSERT INTO brand (id, name) VALUES (25, 'SEAT');
INSERT INTO brand (id, name) VALUES (26, 'Skoda');
INSERT INTO brand (id, name) VALUES (27, 'Subaru');
INSERT INTO brand (id, name) VALUES (28, 'Toyota');
INSERT INTO brand (id, name) VALUES (29, 'Volkswagen');
INSERT INTO brand (id, name) VALUES (30, 'Volvo');

--Rodzaje paliwa

INSERT INTO fuel (id, name, ratio, density) VALUES(1, 'Benzyna', 14.7, 0.74);
INSERT INTO fuel (id, name, ratio, density) VALUES(2, 'Olej napędowy', 14.6, 0.79);
INSERT INTO fuel (id, name, ratio, density) VALUES(3, 'Gaz ziemny', 17.2, 0.45);
INSERT INTO fuel (id, name, ratio, density) VALUES(4, 'Etanol', 9.0, 0.78);
INSERT INTO fuel (id, name, ratio, density) VALUES(5, 'Metanol', 6.4, 0.78);

--Modele samochodów

INSERT INTO model (id, name, brand) VALUES (1, 'A7', 2);
INSERT INTO model (id, name, brand) VALUES (2, 'A8', 2);
INSERT INTO model (id, name, brand) VALUES (3, 'Matiz', 8);
INSERT INTO model (id, name, brand) VALUES (4, 'Lanos', 8);
INSERT INTO model (id, name, brand) VALUES (5, 'Multipla', 11);
INSERT INTO model (id, name, brand) VALUES (6, 'Mondeo', 12);
INSERT INTO model (id, name, brand) VALUES (7, 'Focus', 12);
INSERT INTO model (id, name, brand) VALUES (8, '323F', 18);
INSERT INTO model (id, name, brand) VALUES (9, 'Astra F', 21);
INSERT INTO model (id, name, brand) VALUES (10, 'Insignia', 21);

--Samochody

INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (1, 3, 805, 796, 51);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (2, 4, 1094, 1349, 75);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (3, 4, 1094, 1498, 86);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (4, 4, 1094, 1498, 100);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (5, 4, 1094, 1598, 106);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (6, 7, 1217, 1388, 75);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (7, 7, 1217, 1596, 101);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (8, 7, 1217, 1796, 116);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (9, 7, 1217, 1988, 112);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (10, 7, 1217, 1988, 131);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (11, 7, 1217, 1988, 173);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (12, 7, 1217, 2261, 150);

--Architektury

INSERT INTO architecture (id, vehicle, fuel) VALUES (1, 1, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (2, 2, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (3, 3, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (4, 4, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (5, 5, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (6, 6, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (7, 7, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (8, 8, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (9, 9, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (10, 10, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (11, 11, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (12, 12, 1);

--Kierowcy

INSERT INTO driver (id, nickname, architecture) VALUES (1, 'Driver1 Arch1', 1);
INSERT INTO driver (id, nickname, architecture) VALUES (2, 'Driver2 Arch2', 2);
INSERT INTO driver (id, nickname, architecture) VALUES (3, 'Driver3 Arch7', 7);
INSERT INTO driver (id, nickname, architecture) VALUES (4, 'Driver4 Arch10', 10);
INSERT INTO driver (id, nickname, architecture) VALUES (5, 'Driver5 Arch1', 1);
INSERT INTO driver (id, nickname, architecture) VALUES (6, 'Driver6 Arch1', 1);



