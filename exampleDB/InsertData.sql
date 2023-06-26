Use plantnursery;

-- fill all tables of the bd other than scontrino

INSERT INTO Impiegato (nome, cognome, CF, stipendio, data_assunzione, id_imp)
VALUES
  ('John', 'Doe', 'ABC123', 2500.00, '2022-01-01', 1),
  ('Jane', 'Smith', 'DEF456', 3000.00, '2022-02-15', 2),
  ('Robert', 'Johnson', 'GHI789', 2800.00, '2022-03-10', 3),
  ('Emily', 'Davis', 'JKL012', 3200.00, '2022-04-20', 4),
  ('Michael', 'Wilson', 'MNO345', 2700.00, '2022-05-05', 5);

INSERT INTO Piano_di_Cura (id_piano, acqua, livello_luce, concime, temp_min, temp_max)
VALUES
  (1, 2, 'Medium', 300, 18.5, 25.5),
  (2, 13, 'High', 40, 20.0, 28.0),
  (3, 10, 'Low', 120, 15.0, 22.0),
  (4, 20, 'Medium', 300, 17.0, 24.0),
  (5, 7, 'High', 140, 19.0, 26.0);

INSERT INTO Reparto (cod_reparto, nome_reparto)
VALUES
  (1, 'dirt'),
  (2, 'flower plants'),
  (3, 'vases'),
  (4, 'indoor plants'),
  (5, 'outdoor plants');

INSERT INTO Tipo_Pianta (nome_scientifico, piano, reparto)
VALUES
  ('Rose', 1, 1),
  ('Lily', 2, 2),
  ('Sunflower', 3, 1),
  ('Orchid', 2, 3),
  ('Tulip', 1, 2),
  ('Cactus', 4, 5),
  ('Fern', 3, 4),
  ('Bonsai', 4, 2),
  ('Daisy', 2, 1),
  ('Carnation', 5, 3);

INSERT INTO Tipo_accessorio (nome_tipo, prezzo, reparto)
VALUES
  ('Pot', 10.99, 1),
  ('Gardening Gloves', 5.99, 2),
  ('Pruning Shears', 12.99, 1),
  ('Watering Can', 8.99, 3),
  ('Fertilizer', 9.99, 2),
  ('Plant Food', 7.99, 1),
  ('Trellis', 14.99, 4),
  ('Plant Stand', 19.99, 3),
  ('Garden Hose', 24.99, 5),
  ('Sprinkler', 17.99, 4);

INSERT INTO Turno (cod_reparto, data, ora_inizio, ora_fine, id_imp)
VALUES
  (1, '2023-06-21', 8, 12, 1),
  (1, '2023-06-21', 12, 16, 2),
  (1, '2023-06-21', 16, 20, 3),
  (2, '2023-06-21', 8, 12, 4),
  (2, '2023-06-21', 12, 16, 5),
  (2, '2023-06-21', 16, 20, 1),
  (3, '2023-06-21', 8, 12, 2),
  (3, '2023-06-21', 12, 16, 3),
  (3, '2023-06-21', 16, 20, 4),
  (4, '2023-06-21', 8, 12, 5),
  (4, '2023-06-21', 12, 16, 1),
  (4, '2023-06-21', 16, 20, 2),
  (5, '2023-06-21', 8, 12, 3),
  (5, '2023-06-21', 12, 16, 4),
  (5, '2023-06-21', 16, 20, 5),
  (1, '2023-06-22', 8, 12, 1),
  (1, '2023-06-22', 12, 16, 2),
  (1, '2023-06-22', 16, 20, 3),
  (2, '2023-06-22', 8, 12, 4),
  (2, '2023-06-22', 12, 16, 5),
  (2, '2023-06-22', 16, 20, 1),
  (3, '2023-06-22', 8, 12, 2),
  (3, '2023-06-22', 12, 16, 3),
  (3, '2023-06-22', 16, 20, 4),
  (4, '2023-06-22', 8, 12, 5),
  (4, '2023-06-22', 12, 16, 1),
  (4, '2023-06-22', 16, 20, 2),
  (5, '2023-06-22', 8, 12, 3),
  (5, '2023-06-22', 12, 16, 4),
  (5, '2023-06-22', 16, 20, 5),
  (1, '2023-06-23', 8, 12, 1),
  (1, '2023-06-23', 12, 16, 2),
  (1, '2023-06-23', 16, 20, 3),
  (2, '2023-06-23', 8, 12, 4),
  (2, '2023-06-23', 12, 16, 5),
  (2, '2023-06-23', 16, 20, 1),
  (3, '2023-06-23', 8, 12, 2),
  (3, '2023-06-23', 12, 16, 3),
  (3, '2023-06-23', 16, 20, 4),
  (4, '2023-06-23', 8, 12, 5),
  (4, '2023-06-23', 12, 16, 1),
  (4, '2023-06-23', 16, 20, 2),
  (5, '2023-06-23', 8, 12, 3),
  (5, '2023-06-23', 12, 16, 4),
  (5, '2023-06-23', 16, 20, 5);

INSERT INTO Fornitore (id_fornitore, nome)
VALUES
  (1, 'Supplier Removed'),
  (2, 'Supplier B'),
  (3, 'Supplier C'),
  (4, 'Supplier D'),
  (5, 'Supplier E'),
  (6, 'Supplier '),
  (7, 'Supplier F'),
  (8, 'Supplier G'),
  (9, 'Supplier H');
  
INSERT INTO Fattura (id_documento, data, id_fornitore)
VALUES (1, '2023-05-21', 6),
       (2, '2023-05-21', 2),
       (3, '2023-05-21', 3),
       (4, '2023-05-21', 4),
       (5, '2023-05-21', 5),
       (6, '2023-05-21', 7),
       (7, '2023-05-21', 2),
       (8, '2023-05-21', 3),
       (9, '2023-05-21', 4),
       (10, '2023-05-21', 5),
       (11, '2023-05-21', 3),
       (12, '2023-05-21', 2),
       (13, '2023-05-21', 3),
       (14, '2023-05-21', 4),
       (15, '2023-05-21', 5),
       (36, '2023-06-21', 6),
       (27, '2023-06-21', 7),
       (28, '2023-06-21', 8),
       (29, '2023-06-21', 9),
       (30, '2023-06-21', 9),
       (31, '2023-06-21', 6),
       (32, '2023-06-21', 6),
       (33, '2023-06-21', 7),
       (34, '2023-06-21', 7),
       (35, '2023-06-21', 8);
       
INSERT INTO Accessorio (id_prodotto, descrizione, id_fattura, id_scontrino, tipo)
VALUES
  (1, 'Pot', 1, NULL, 'Pot'),
  (2, 'Gardening Gloves', 2, NULL, 'Gardening Gloves'),
  (3, 'Pruning Shears', 3, NULL, 'Pruning Shears'),
  (4, 'Watering Can', 4, NULL, 'Watering Can'),
  (5, 'Fertilizer', 5, NULL, 'Fertilizer'),
  (6, 'Plant Food', 6, NULL, 'Plant Food'),
  (7, 'Trellis', 7, NULL, 'Trellis'),
  (8, 'Plant Stand', 8, NULL, 'Plant Stand'),
  (9, 'Garden Hose', 9, NULL, 'Garden Hose'),
  (10, 'Sprinkler', 10, NULL, 'Sprinkler'),
  (11, 'Pot 2', 11, NULL, 'Pot'),
  (12, 'Gardening Gloves 2', 12, NULL, 'Gardening Gloves'),
  (13, 'Pruning Shears 2', 13, NULL, 'Pruning Shears'),
  (14, 'Watering Can 2', 14, NULL, 'Watering Can'),
  (15, 'Fertilizer 2', 15, NULL, 'Fertilizer'),
  (16, 'Plant Food 2', 1, NULL, 'Plant Food'),
  (17, 'Trellis 2', 2, NULL, 'Trellis'),
  (18, 'Plant Stand 2', 3, NULL, 'Plant Stand'),
  (19, 'Garden Hose 2', 4, NULL, 'Garden Hose'),
  (20, 'Sprinkler 2', 5, NULL, 'Sprinkler'),
  (21, 'Pot 3', 6, NULL, 'Pot'),
  (22, 'Gardening Gloves 3', 7, NULL, 'Gardening Gloves'),
  (23, 'Pruning Shears 3', 8, NULL, 'Pruning Shears'),
  (24, 'Watering Can 3', 9, NULL, 'Watering Can'),
  (25, 'Fertilizer 3', 10, NULL, 'Fertilizer'),
  (26, 'Plant Food 3', 11, NULL, 'Plant Food'),
  (27, 'Trellis 3', 12, NULL, 'Trellis'),
  (28, 'Plant Stand 3', 13, NULL, 'Plant Stand'),
  (29, 'Garden Hose 3', 14, NULL, 'Garden Hose'),
  (30, 'Sprinkler 3', 15, NULL, 'Sprinkler');

INSERT INTO Pianta (id_prodotto, descrizione, larghezza_vaso, altezza, prezzo, id_fattura, id_scontrino, nome)
VALUES
  (31, 'Daisy', 8.2, 27, 4.99, 1, NULL, 'Daisy'),
  (32, 'Rose', 10.5, 50, 6.99, 2, NULL, 'Rose'),
  (33, 'Sunflower', 12.8, 63, 7.99, 3, NULL, 'Sunflower'),
  (34, 'Bonsai', 18.3, 71, 14.99, 4, NULL, 'Bonsai'),
  (35, 'Lily', 11.6, 57, 9.99, 5, NULL, 'Lily'),
  (36, 'Tulip', 6.9, 43, 5.99, 6, NULL, 'Tulip'),
  (37, 'Carnation', 10.2, 39, 6.99, 7, NULL, 'Carnation'),
  (38, 'Orchid', 15.4, 48, 11.99, 8, NULL, 'Orchid'),
  (39, 'Fern', 9.1, 33, 5.99, 9, NULL, 'Fern'),
  (40, 'Cactus', 7.5, 24, 8.99, 10, NULL, 'Cactus'),
  (41, 'Daisy', 8.2, 27, 4.99, 11, NULL, 'Daisy'),
  (42, 'Rose', 10.5, 50, 6.99, 12, NULL, 'Rose'),
  (43, 'Sunflower', 12.8, 63, 7.99, 13, NULL, 'Sunflower'),
  (44, 'Bonsai', 18.3, 71, 14.99, 14, NULL, 'Bonsai'),
  (45, 'Lily', 11.6, 57, 9.99, 15, NULL, 'Lily'),
  (46, 'Tulip', 6.9, 43, 5.99, 1, NULL, 'Tulip'),
  (47, 'Carnation', 10.2, 39, 6.99, 2, NULL, 'Carnation'),
  (48, 'Orchid', 15.4, 48, 11.99, 3, NULL, 'Orchid'),
  (49, 'Fern', 9.1, 33, 5.99, 4, NULL, 'Fern'),
  (50, 'Cactus', 7.5, 24, 8.99, 5, NULL, 'Cactus'),
  (51, 'Daisy', 8.2, 27, 4.99, 6, NULL, 'Daisy'),
  (52, 'Rose', 10.5, 50, 6.99, 7, NULL, 'Rose'),
  (53, 'Sunflower', 12.8, 63, 7.99, 8, NULL, 'Sunflower'),
  (54, 'Bonsai', 18.3, 71, 14.99, 9, NULL, 'Bonsai'),
  (55, 'Lily', 11.6, 57, 9.99, 10, NULL, 'Lily'),
  (56, 'Tulip', 6.9, 43, 5.99, 11, NULL, 'Tulip'),
  (57, 'Carnation', 10.2, 39, 6.99, 12, NULL, 'Carnation'),
  (58, 'Orchid', 15.4, 48, 11.99, 13, NULL, 'Orchid'),
  (59, 'Fern', 9.1, 33, 5.99, 14, NULL, 'Fern'),
  (60, 'Cactus', 7.5, 24, 8.99, 15, NULL, 'Cactus'),
  (61, 'Daisy', 8.2, 27, 4.99, 1, NULL, 'Daisy'),
  (62, 'Rose', 10.5, 50, 6.99, 2, NULL, 'Rose'),
  (63, 'Sunflower', 12.8, 63, 7.99, 3, NULL, 'Sunflower'),
  (64, 'Bonsai', 18.3, 71, 14.99, 4, NULL, 'Bonsai'),
  (65, 'Lily', 11.6, 57, 9.99, 5, NULL, 'Lily'),
  (66, 'Tulip', 6.9, 43, 5.99, 6, NULL, 'Tulip'),
  (67, 'Carnation', 10.2, 39, 6.99, 7, NULL, 'Carnation'),
  (68, 'Orchid', 15.4, 48, 11.99, 8, NULL, 'Orchid'),
  (69, 'Fern', 9.1, 33, 5.99, 9, NULL, 'Fern'),
  (70, 'Cactus', 7.5, 24, 8.99, 10, NULL, 'Cactus'),
  (71, 'Daisy', 8.2, 27, 4.99, 11, NULL, 'Daisy'),
  (72, 'Rose', 10.5, 50, 6.99, 12, NULL, 'Rose'),
  (73, 'Sunflower', 12.8, 63, 7.99, 13, NULL, 'Sunflower'),
  (74, 'Bonsai', 18.3, 71, 14.99, 14, NULL, 'Bonsai'),
  (75, 'Lily', 11.6, 57, 9.99, 15, NULL, 'Lily'),
  (76, 'Tulip', 6.9, 43, 5.99, 1, NULL, 'Tulip'),
  (77, 'Carnation', 10.2, 39, 6.99, 2, NULL, 'Carnation'),
  (78, 'Orchid', 15.4, 48, 11.99, 3, NULL, 'Orchid'),
  (79, 'Fern', 9.1, 33, 5.99, 4, NULL, 'Fern'),
  (80, 'Cactus', 7.5, 24, 8.99, 5, NULL, 'Cactus'),
  (81, 'Daisy', 8.2, 27, 4.99, 6, NULL, 'Daisy');
  
-- Insert statements for Accessorio table
INSERT INTO Accessorio (id_prodotto, descrizione, id_fattura, id_scontrino, tipo)
VALUES
  (82, 'Pot', 1, NULL, 'Pot'),
  (83, 'Gardening Gloves', 2, NULL, 'Gardening Gloves'),
  (84, 'Pruning Shears', 3, NULL, 'Pruning Shears'),
  (85, 'Watering Can', 4, NULL, 'Watering Can'),
  (86, 'Fertilizer', 5, NULL, 'Fertilizer'),
  (87, 'Plant Food', 6, NULL, 'Plant Food'),
  (88, 'Trellis', 7, NULL, 'Trellis'),
  (89, 'Plant Stand', 8, NULL, 'Plant Stand'),
  (90, 'Garden Hose', 9, NULL, 'Garden Hose'),
  (91, 'Sprinkler', 10, NULL, 'Sprinkler'),
  (92, 'Pot 2', 11, NULL, 'Pot'),
  (93, 'Gardening Gloves 2', 12, NULL, 'Gardening Gloves'),
  (94, 'Pruning Shears 2', 13, NULL, 'Pruning Shears'),
  (95, 'Watering Can 2', 14, NULL, 'Watering Can'),
  (96, 'Fertilizer 2', 15, NULL, 'Fertilizer');

-- Insert statements for Pianta table
INSERT INTO Pianta (id_prodotto, descrizione, larghezza_vaso, altezza, prezzo, id_fattura, id_scontrino, nome)
VALUES
  (97, 'Daisy', 8, 27, 4.99, 1, NULL, 'Daisy'),
  (98, 'Rose', 10, 50, 6.99, 2, NULL, 'Rose'),
  (99, 'Sunflower', 12, 63, 7.99, 3, NULL, 'Sunflower'),
  (100, 'Bonsai', 18, 71, 14.99, 4, NULL, 'Bonsai'),
  (101, 'Lily', 11, 57, 9.99, 5, NULL, 'Lily'),
  (102, 'Tulip', 6, 43, 5.99, 6, NULL, 'Tulip'),
  (103, 'Carnation', 10, 39, 6.99, 7, NULL, 'Carnation'),
  (104, 'Orchid', 15, 48, 11.99, 8, NULL, 'Orchid'),
  (105, 'Fern', 9, 33, 5.99, 9, NULL, 'Fern'),
  (106, 'Cactus', 7, 24, 8.99, 10, NULL, 'Cactus'),
  (107, 'Daisy', 8, 27, 4.99, 11, NULL, 'Daisy'),
  (108, 'Rose', 10, 50, 6.99, 12, NULL, 'Rose'),
  (109, 'Sunflower', 12, 63, 7.99, 13, NULL, 'Sunflower'),
  (110, 'Bonsai', 18, 71, 14.99, 14, NULL, 'Bonsai'),
  (111, 'Lily', 11, 57, 9.99, 15, NULL, 'Lily'),
  (112, 'Tulip', 6, 43, 5.99, 6, NULL, 'Tulip'),
  (113, 'Carnation', 10, 39, 6.99, 7, NULL, 'Carnation'),
  (114, 'Orchid', 15, 48, 11.99, 8, NULL, 'Orchid'),
  (115, 'Fern', 9, 33, 5.99, 9, NULL, 'Fern'),
  (116, 'Cactus', 7, 24, 8.99, 2, NULL, 'Cactus'),
  (117, 'Daisy', 8, 27, 4.99, 2, NULL, 'Daisy'),
  (118, 'Rose', 10, 50, 6.99, 2, NULL, 'Rose'),
  (119, 'Sunflower', 12, 63, 7.99, 3, NULL, 'Sunflower'),
  (120, 'Bonsai', 18, 71, 14.99, 2, NULL, 'Bonsai'),
  (121, 'Lily', 11, 57, 9.99, 2, NULL, 'Lily'),
  (122, 'Tulip', 6, 43, 5.99, 6, NULL, 'Tulip'),
  (123, 'Carnation', 10, 39, 6.99, 7, NULL, 'Carnation'),
  (124, 'Orchid', 15, 48, 11.99, 2, NULL, 'Orchid'),
  (125, 'Fern', 9, 33, 5.99, 2, NULL, 'Fern'),
  (126, 'Cactus', 7, 24, 8.99, 3, NULL, 'Cactus'),
  (127, 'Daisy', 8, 27, 4.99, 3, NULL, 'Daisy'),
  (128, 'Rose', 10, 50, 6.99, 12, NULL, 'Rose'),
  (129, 'Sunflower', 12, 63, 7.99, 13, NULL, 'Sunflower'),
  (130, 'Bonsai', 18, 71, 14.99, 14, NULL, 'Bonsai'),
  (131, 'Lily', 11, 57, 9.99, 15, NULL, 'Lily'),
  (132, 'Tulip', 6, 43, 5.99, 6, NULL, 'Tulip'),
  (133, 'Carnation', 10, 39, 6.99, 7, NULL, 'Carnation'),
  (134, 'Orchid', 15, 48, 11.99, 8, NULL, 'Orchid'),
  (135, 'Fern', 9, 33, 5.99, 9, NULL, 'Fern'),
  (136, 'Cactus', 7, 24, 8.99, 10, NULL, 'Cactus');


INSERT INTO cura (pianta, data, concime, id_imp)
VALUES
  (31, '2023-05-01', True, 1),
  (32, '2023-05-02', True, 2),
  (33, '2023-05-03', True, 3),
  (34, '2023-05-04', True, 4),
  (35, '2023-05-05', True, 5),
  (36, '2023-05-06', True, 1),
  (37, '2023-05-07', True, 2),
  (38, '2023-05-08', True, 3),
  (39, '2023-05-09', True, 4),
  (40, '2023-05-10', True, 5),
  (41, '2023-05-11', True, 1),
  (42, '2023-05-12', True, 2),
  (43, '2023-05-13', True, 3),
  (44, '2023-05-14', True, 4),
  (45, '2023-05-15', True, 5),
  (46, '2023-05-01', True, 1),
  (47, '2023-05-02', True, 2),
  (48, '2023-05-03', True, 3),
  (49, '2023-05-04', True, 4),
  (50, '2023-05-05', True, 5),
  (51, '2023-05-06', True, 1),
  (52, '2023-05-07', True, 2),
  (53, '2023-05-08', True, 3),
  (54, '2023-05-09', True, 4),
  (55, '2023-05-10', True, 5),
  (56, '2023-05-11', True, 1),
  (57, '2023-05-12', True, 2),
  (58, '2023-05-13', True, 3),
  (59, '2023-05-14', True, 4),
  (60, '2023-05-15', True, 5),
  (61, '2023-05-01', True, 1),
  (62, '2023-05-02', True, 2),
  (63, '2023-05-03', True, 3),
  (64, '2023-05-04', True, 4),
  (65, '2023-05-05', True, 5),
  (66, '2023-05-06', True, 1),
  (67, '2023-05-07', True, 2),
  (68, '2023-05-08', True, 3),
  (69, '2023-05-09', True, 4),
  (70, '2023-05-10', True, 5),
  (71, '2023-05-11', True, 1),
  (72, '2023-05-12', True, 2),
  (73, '2023-05-13', True, 3),
  (74, '2023-05-14', True, 4),
  (75, '2023-05-15', True, 5),
  (76, '2023-05-01', False, 1),
  (77, '2023-05-02', False, 2),
  (78, '2023-05-03', False, 3),
  (79, '2023-05-04', False, 4),
  (80, '2023-05-05', False, 5),
  (81, '2023-05-06', False, 1),
  (42, '2023-05-07', False, 2),
  (43, '2023-05-08', False, 3),
  (44, '2023-05-09', False, 4),
  (45, '2023-05-10', False, 5),
  (46, '2023-05-11', False, 1),
  (47, '2023-05-12', False, 2),
  (48, '2023-05-13', False, 3),
  (49, '2023-05-14', False, 4),
  (40, '2023-05-15', False, 5),
  (51, '2023-05-01', False, 1),
  (52, '2023-05-02', False, 2),
  (53, '2023-05-03', False, 3),
  (54, '2023-05-04', False, 4),
  (55, '2023-05-05', False, 5),
  (56, '2023-05-06', False, 1),
  (57, '2023-05-07', False, 2),
  (58, '2023-05-08', False, 3),
  (59, '2023-05-09', False, 4),
  (50, '2023-05-10', False, 5),
  (31, '2023-06-01', True, 1),
  (32, '2023-06-02', True, 2),
  (33, '2023-06-03', True, 3),
  (34, '2023-06-04', True, 4),
  (35, '2023-06-05', True, 5),
  (36, '2023-06-06', True, 1),
  (37, '2023-06-07', True, 2),
  (38, '2023-06-08', True, 3),
  (39, '2023-06-09', True, 4),
  (40, '2023-06-10', True, 5),
  (41, '2023-06-11', True, 1),
  (42, '2023-06-12', True, 2),
  (43, '2023-06-13', True, 3),
  (44, '2023-06-14', True, 4),
  (45, '2023-06-15', True, 5),
  (46, '2023-06-01', True, 1),
  (47, '2023-06-02', True, 2),
  (48, '2023-06-03', True, 3),
  (49, '2023-06-04', True, 4),
  (50, '2023-06-05', True, 5),
  (51, '2023-06-06', True, 1),
  (52, '2023-06-07', True, 2),
  (53, '2023-06-08', True, 3),
  (54, '2023-06-09', True, 4),
  (55, '2023-06-10', True, 5),
  (56, '2023-06-11', True, 1),
  (57, '2023-06-12', True, 2),
  (58, '2023-06-13', True, 3),
  (59, '2023-06-14', True, 4),
  (60, '2023-06-15', True, 5),
  (61, '2023-06-01', True, 1),
  (62, '2023-06-02', True, 2),
  (63, '2023-06-03', True, 3),
  (64, '2023-06-04', True, 4),
  (65, '2023-06-05', True, 5),
  (66, '2023-06-06', True, 1),
  (67, '2023-06-07', True, 2),
  (68, '2023-06-08', True, 3),
  (69, '2023-06-09', True, 4),
  (70, '2023-06-10', True, 5),
  (71, '2023-06-11', True, 1),
  (72, '2023-06-12', True, 2),
  (73, '2023-06-13', True, 3),
  (74, '2023-06-14', True, 4),
  (75, '2023-06-15', True, 5),
  (76, '2023-06-01', False, 1),
  (77, '2023-06-02', False, 2),
  (78, '2023-06-03', False, 3),
  (79, '2023-06-04', False, 4),
  (80, '2023-06-05', False, 5),
  (81, '2023-06-06', False, 1),
  (31, '2023-06-15', True, 1),
  (32, '2023-06-16', True, 2),
  (33, '2023-06-17', True, 3),
  (34, '2023-06-18', True, 4),
  (35, '2023-06-19', True, 5),
  (36, '2023-06-20', True, 1),
  (37, '2023-06-21', True, 2),
  (38, '2023-06-22', True, 3),
  (39, '2023-06-23', True, 4),
  (40, '2023-06-24', True, 5),
  (41, '2023-06-25', True, 1),
  (42, '2023-06-26', True, 2),
  (43, '2023-06-27', True, 3),
  (44, '2023-06-28', True, 4),
  (45, '2023-06-29', True, 5),
  (46, '2023-06-30', True, 1),
  (47, '2023-07-01', True, 2),
  (48, '2023-07-02', True, 3),
  (49, '2023-07-03', True, 4),
  (50, '2023-07-04', True, 5),
  (51, '2023-07-05', True, 1),
  (52, '2023-07-06', True, 2),
  (53, '2023-07-07', True, 3),
  (54, '2023-07-08', True, 4),
  (55, '2023-07-09', True, 5),
  (56, '2023-07-10', True, 1),
  (57, '2023-07-11', True, 2),
  (58, '2023-07-12', True, 3),
  (59, '2023-07-13', True, 4),
  (60, '2023-07-14', True, 5),
  (61, '2023-07-15', True, 1),
  (62, '2023-07-16', True, 2),
  (63, '2023-07-17', True, 3),
  (64, '2023-07-18', True, 4),
  (65, '2023-07-19', True, 5),
  (66, '2023-07-20', True, 1),
  (67, '2023-07-21', True, 2),
  (68, '2023-07-22', True, 3),
  (69, '2023-07-23', True, 4),
  (70, '2023-07-24', True, 5),
  (71, '2023-07-25', True, 1),
  (72, '2023-07-26', True, 2),
  (73, '2023-07-27', True, 3),
  (74, '2023-07-28', True, 4),
  (75, '2023-07-29', True, 5),
  (76, '2023-07-30', False, 1),
  (77, '2023-07-31', False, 2),
  (78, '2023-08-01', False, 3),
  (79, '2023-08-02', False, 4),
  (80, '2023-08-03', False, 5),
  (81, '2023-08-04', False, 1),
  (97, '2023-06-15', True, 1),
  (98, '2023-06-16', True, 2),
  (99, '2023-06-17', True, 3),
  (100, '2023-06-18', True, 4),
  (101, '2023-06-19', True, 5),
  (102, '2023-06-20', True, 1),
  (103, '2023-06-21', True, 2),
  (104, '2023-06-22', True, 3),
  (105, '2023-06-23', True, 4),
  (106, '2023-06-24', True, 5),
  (107, '2023-06-25', True, 1),
  (108, '2023-06-26', True, 2),
  (109, '2023-06-27', True, 3),
  (110, '2023-06-28', True, 4),
  (111, '2023-06-29', True, 5),
  (112, '2023-06-15', True, 1),
  (113, '2023-06-16', True, 2),
  (114, '2023-06-17', True, 3),
  (115, '2023-06-18', True, 4),
  (116, '2023-06-19', True, 5),
  (117, '2023-06-20', True, 1),
  (118, '2023-06-21', True, 2),
  (119, '2023-06-22', True, 3),
  (120, '2023-06-23', True, 4),
  (121, '2023-06-24', True, 5),
  (122, '2023-06-25', True, 1),
  (123, '2023-06-26', True, 2),
  (124, '2023-06-27', True, 3),
  (125, '2023-06-28', True, 4),
  (126, '2023-06-29', True, 5),
  (127, '2023-06-15', True, 1),
  (128, '2023-06-16', True, 2),
  (129, '2023-06-17', True, 3),
  (130, '2023-06-18', True, 4),
  (131, '2023-06-19', True, 5),
  (132, '2023-06-20', True, 1),
  (133, '2023-06-21', True, 2),
  (134, '2023-06-22', True, 3),
  (135, '2023-06-23', True, 4),
  (126, '2023-06-24', True, 5),
  (127, '2023-06-25', True, 1),
  (128, '2023-06-26', True, 2),
  (129, '2023-06-27', True, 3),
  (120, '2023-06-28', True, 4),
  (121, '2023-06-29', True, 5),
  (122, '2023-06-15', False, 1),
  (123, '2023-06-16', False, 2),
  (124, '2023-06-17', False, 3),
  (125, '2023-06-18', False, 4),
  (117, '2023-06-19', False, 5),
  (118, '2023-06-20', False, 1),
  (119, '2023-06-21', False, 2),
  (110, '2023-06-22', False, 3),
  (111, '2023-06-23', False, 4),
  (112, '2023-06-24', False, 5),
  (113, '2023-06-25', False, 1),
  (114, '2023-06-26', False, 2),
  (115, '2023-06-27', False, 3),
  (116, '2023-06-28', False, 4),
  (98, '2023-06-29', False, 5),
  (107, '2023-06-15', False, 1),
  (108, '2023-06-16', False, 2),
  (109, '2023-06-17', False, 3),
  (101, '2023-06-18', False, 4),
  (102, '2023-06-19', False, 5),
  (103, '2023-06-20', False, 1),
  (104, '2023-06-21', False, 2),
  (105, '2023-06-22', False, 3),
  (106, '2023-06-23', False, 4),
  (136, '2023-06-24', False, 5);


insert into scontrino (id_documento,data,impiegato)
values (16, '2023-06-21' ,1),
	(17, '2023-06-21' ,1),
	(18, '2023-06-22' ,1),
	(19, '2023-06-22' ,1),
	(20, '2023-06-22' ,2),
	(21, '2023-06-23' ,3),
	(22, '2023-06-23' ,4),
	(23, '2023-06-23' ,5),
	(24, '2023-06-21' ,5),
	(25, '2023-06-21' ,4),
	(26, '2023-06-21' ,5);
    
SET SQL_SAFE_UPDATES = 0;
UPDATE accessorio
SET id_scontrino = 16
WHERE id_prodotto BETWEEN 0 AND 7;

UPDATE pianta
SET id_scontrino = 16
WHERE id_prodotto BETWEEN 0 AND 7;

UPDATE accessorio
SET id_scontrino = 17
WHERE id_prodotto BETWEEN 7 AND 14;

UPDATE pianta
SET id_scontrino = 17
WHERE id_prodotto BETWEEN 7 AND 14;

UPDATE accessorio
SET id_scontrino = 18
WHERE id_prodotto BETWEEN 14 AND 21;

UPDATE pianta
SET id_scontrino = 18
WHERE id_prodotto BETWEEN 14 AND 21;

UPDATE accessorio
SET id_scontrino = 19
WHERE id_prodotto BETWEEN 21 AND 28;

UPDATE pianta
SET id_scontrino = 19
WHERE id_prodotto BETWEEN 21 AND 28;

UPDATE accessorio
SET id_scontrino = 20
WHERE id_prodotto BETWEEN 28 AND 35;

UPDATE pianta
SET id_scontrino = 20
WHERE id_prodotto BETWEEN 28 AND 35;

UPDATE accessorio
SET id_scontrino = 21
WHERE id_prodotto BETWEEN 35 AND 42;

UPDATE pianta
SET id_scontrino = 21
WHERE id_prodotto BETWEEN 35 AND 42;

UPDATE accessorio
SET id_scontrino = 22
WHERE id_prodotto BETWEEN 42 AND 49;

UPDATE pianta
SET id_scontrino = 22
WHERE id_prodotto BETWEEN 42 AND 49;

UPDATE accessorio
SET id_scontrino = 23
WHERE id_prodotto BETWEEN 49 AND 56;

UPDATE pianta
SET id_scontrino = 23
WHERE id_prodotto BETWEEN 49 AND 56;

UPDATE accessorio
SET id_scontrino = 24
WHERE id_prodotto BETWEEN 56 AND 63;

UPDATE pianta
SET id_scontrino = 24
WHERE id_prodotto BETWEEN 56 AND 63;

UPDATE accessorio
SET id_scontrino = 25
WHERE id_prodotto BETWEEN 63 AND 70;

UPDATE pianta
SET id_scontrino = 25
WHERE id_prodotto BETWEEN 63 AND 70;

UPDATE accessorio
SET id_scontrino = 26
WHERE id_prodotto BETWEEN 70 AND 77;

UPDATE pianta
SET id_scontrino = 26
WHERE id_prodotto BETWEEN 70 AND 77;


SET SQL_SAFE_UPDATES = 1;
