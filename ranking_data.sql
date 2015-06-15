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
INSERT INTO model (id, name, brand) VALUES (11, 'F30', 3);
INSERT INTO model (id, name, brand) VALUES (12, 'E70', 3);
INSERT INTO model (id, name, brand) VALUES (13, 'C5', 7);
INSERT INTO model (id, name, brand) VALUES (14, 'C6', 7);
INSERT INTO model (id, name, brand) VALUES (15, 'Civic IX', 13);
INSERT INTO model (id, name, brand) VALUES (16, 'Insight', 13);
INSERT INTO model (id, name, brand) VALUES (17, 'Qashqai', 20);
INSERT INTO model (id, name, brand) VALUES (18, 'Tiida', 20);
INSERT INTO model (id, name, brand) VALUES (19, 'Megane', 24);
INSERT INTO model (id, name, brand) VALUES (20, 'Laguna', 24);
INSERT INTO model (id, name, brand) VALUES (21, 'Viano', 19);
INSERT INTO model (id, name, brand) VALUES (22, 'E71', 3);
INSERT INTO model (id, name, brand) VALUES (23, 'Sorento', 16);

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
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (13, 9, 1274, 1389, 82);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (14, 9, 1274, 1598, 75);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (15, 9, 1274, 1794, 116);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (16, 9, 1274, 1998, 136);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (17, 14, 1844, 2946, 211);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (18, 14, 1844, 2179, 170); --DIESEL
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (19, 14, 1844, 2729, 204); --DIESEL
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (20, 14, 1844, 2992, 241); --DIESEL
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (21, 20, 1255, 1794, 95);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (22, 20, 1255, 1998, 113);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (23, 20, 1255, 2963, 167);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (24, 20, 1255, 2946, 190);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (25, 20, 1255, 2188, 83); --DIESEL
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (26, 21, 1962, 2990, 204);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (27, 21, 1962, 2200, 160); --DIESEL
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (28, 22, 2165, 2979, 306);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (29, 22, 2165, 4395, 407);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (30, 22, 2165, 4395, 485);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (31, 22, 2165, 4395, 555);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (32, 23, 1986, 2351, 139);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (33, 23, 1986, 3497, 195);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (34, 23, 1986, 3778, 266);
INSERT INTO vehicle (id, model, mass, engine_volume, power) VALUES (35, 23, 1986, 2497, 140); --DIESEL

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
INSERT INTO architecture (id, vehicle, fuel) VALUES (13, 13, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (14, 14, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (15, 15, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (16, 16, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (17, 17, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (18, 18, 2);
INSERT INTO architecture (id, vehicle, fuel) VALUES (19, 19, 2);
INSERT INTO architecture (id, vehicle, fuel) VALUES (20, 20, 2);
INSERT INTO architecture (id, vehicle, fuel) VALUES (21, 21, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (22, 22, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (23, 23, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (24, 24, 1);
INSERT INTO architecture (id, vehicle, fuel) VALUES (25, 25, 2);
INSERT INTO architecture (id, vehicle, fuel) VALUES (26, 26, 1); 
INSERT INTO architecture (id, vehicle, fuel) VALUES (27, 27, 2); 
INSERT INTO architecture (id, vehicle, fuel) VALUES (28, 28, 1); 
INSERT INTO architecture (id, vehicle, fuel) VALUES (29, 29, 1); 
INSERT INTO architecture (id, vehicle, fuel) VALUES (30, 30, 1); 
INSERT INTO architecture (id, vehicle, fuel) VALUES (31, 31, 1); 
INSERT INTO architecture (id, vehicle, fuel) VALUES (32, 32, 1); 
INSERT INTO architecture (id, vehicle, fuel) VALUES (33, 33, 1); 
INSERT INTO architecture (id, vehicle, fuel) VALUES (34, 34, 1); 
INSERT INTO architecture (id, vehicle, fuel) VALUES (35, 35, 2); 
INSERT INTO architecture (id, vehicle, fuel) VALUES (36, 17, 3);
INSERT INTO architecture (id, vehicle, fuel) VALUES (37, 18, 3);
INSERT INTO architecture (id, vehicle, fuel) VALUES (38, 14, 3);
INSERT INTO architecture (id, vehicle, fuel) VALUES (39, 15, 3);
INSERT INTO architecture (id, vehicle, fuel) VALUES (40, 24, 3);
INSERT INTO architecture (id, vehicle, fuel) VALUES (41, 25, 3);

--Kierowcy

INSERT INTO driver (id, nickname, architecture) VALUES (1, 'Driver1 Arch1', 1);
INSERT INTO driver (id, nickname, architecture) VALUES (2, 'Driver2 Arch2', 2);
INSERT INTO driver (id, nickname, architecture) VALUES (3, 'Driver3 Arch3', 3);
INSERT INTO driver (id, nickname, architecture) VALUES (4, 'Driver4 Arch4', 4);
INSERT INTO driver (id, nickname, architecture) VALUES (5, 'Driver5 Arch5', 5);
INSERT INTO driver (id, nickname, architecture) VALUES (6, 'Driver6 Arch6', 6);
INSERT INTO driver (id, nickname, architecture) VALUES (7, 'Driver7 Arch7', 7);
INSERT INTO driver (id, nickname, architecture) VALUES (8, 'Driver8 Arch8', 8);
INSERT INTO driver (id, nickname, architecture) VALUES (9, 'Driver9 Arch9', 9);
INSERT INTO driver (id, nickname, architecture) VALUES (10, 'Driver10 Arch10', 10);
INSERT INTO driver (id, nickname, architecture) VALUES (11, 'Driver11 Arch11', 11);
INSERT INTO driver (id, nickname, architecture) VALUES (12, 'Driver12 Arch12', 12);
INSERT INTO driver (id, nickname, architecture) VALUES (13, 'Driver13 Arch13', 13);
INSERT INTO driver (id, nickname, architecture) VALUES (14, 'Driver14 Arch14', 14);
INSERT INTO driver (id, nickname, architecture) VALUES (15, 'Driver15 Arch15', 15);
INSERT INTO driver (id, nickname, architecture) VALUES (16, 'Driver16 Arch16', 16);
INSERT INTO driver (id, nickname, architecture) VALUES (17, 'Driver17 Arch17', 17);
INSERT INTO driver (id, nickname, architecture) VALUES (18, 'Driver18 Arch18', 18);
INSERT INTO driver (id, nickname, architecture) VALUES (19, 'Driver19 Arch19', 19);
INSERT INTO driver (id, nickname, architecture) VALUES (20, 'Driver20 Arch20', 20);
INSERT INTO driver (id, nickname, architecture) VALUES (21, 'Driver21 Arch21', 21);
INSERT INTO driver (id, nickname, architecture) VALUES (22, 'Driver22 Arch22', 22);
INSERT INTO driver (id, nickname, architecture) VALUES (23, 'Driver23 Arch23', 23);
INSERT INTO driver (id, nickname, architecture) VALUES (24, 'Driver24 Arch24', 24);
INSERT INTO driver (id, nickname, architecture) VALUES (25, 'Driver25 Arch25', 25);
INSERT INTO driver (id, nickname, architecture) VALUES (26, 'Driver26 Arch26', 26);
INSERT INTO driver (id, nickname, architecture) VALUES (27, 'Driver27 Arch27', 27);
INSERT INTO driver (id, nickname, architecture) VALUES (28, 'Driver28 Arch28', 28);
INSERT INTO driver (id, nickname, architecture) VALUES (29, 'Driver29 Arch29', 29);
INSERT INTO driver (id, nickname, architecture) VALUES (30, 'Driver30 Arch30', 30);
INSERT INTO driver (id, nickname, architecture) VALUES (31, 'Driver31 Arch31', 31);
INSERT INTO driver (id, nickname, architecture) VALUES (32, 'Driver32 Arch32', 32);
INSERT INTO driver (id, nickname, architecture) VALUES (33, 'Driver33 Arch33', 33);
INSERT INTO driver (id, nickname, architecture) VALUES (34, 'Driver34 Arch34', 34);
INSERT INTO driver (id, nickname, architecture) VALUES (35, 'Driver35 Arch35', 35);
INSERT INTO driver (id, nickname, architecture) VALUES (36, 'Driver36 Arch36', 36);
INSERT INTO driver (id, nickname, architecture) VALUES (37, 'Driver37 Arch37', 37);
INSERT INTO driver (id, nickname, architecture) VALUES (38, 'Driver38 Arch38', 38);
INSERT INTO driver (id, nickname, architecture) VALUES (39, 'Driver39 Arch39', 39);
INSERT INTO driver (id, nickname, architecture) VALUES (40, 'Driver40 Arch40', 40);
INSERT INTO driver (id, nickname, architecture) VALUES (41, 'Driver41 Arch41', 41);


