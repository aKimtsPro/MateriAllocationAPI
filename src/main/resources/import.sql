INSERT INTO material (created_at, last_modified_at, "name") VALUES (now(), null, 'projecteur');
INSERT INTO material (created_at, last_modified_at, "name") VALUES (now(), null, 'tableau blanc');
INSERT INTO material (created_at, last_modified_at, "name") VALUES (now(), null, 'tableau numérique');
INSERT INTO material (created_at, last_modified_at, "name") VALUES (now(), null, 'machine à café');

INSERT INTO person ("role", created_at,  address_city, address_country, address_number, address_postal_code, address_region, address_street, email, first_name, last_name, "login", "password", phone) values ('ADMIN', now(), 'Namur', 'Belgique', '40', '5100', 'Namur', 'Place de l Ange', 'admin@user.be', 'admin', 'Histateur', 'admin_01', 'pass', '0400/00.00.00');
INSERT INTO person ("role", created_at,  address_city, address_country, address_number, address_postal_code, address_region, address_street, email, first_name, last_name, "login", "password", phone) values ('STUDENT', now(), 'Namur', 'Belgique', '40', '5100', 'Namur', 'Place de l Ange', 'student@user.be', 'admin', 'Histateur', 'stud_01', 'pass', '0400/00.00.01');
INSERT INTO person ("role", created_at,  address_city, address_country, address_number, address_postal_code, address_region, address_street, email, first_name, last_name, "login", "password", phone) values ('TEACHER', now(), 'Namur', 'Belgique', '40', '5100', 'Namur', 'Place de l Ange', 'teacher@user.be', 'admin', 'Histateur', 'teach_01', 'pass', '0400/00.00.02');

INSERT INTO room (created_at, capacity, room_number, student_access) VALUES (now(), 10, 1, true);
INSERT INTO room (created_at, capacity, room_number, student_access) VALUES (now(), 10, 2, false);
INSERT INTO room (created_at, capacity, room_number, student_access) VALUES (now(), 26, 3, true);
INSERT INTO room (created_at, capacity, room_number, student_access) VALUES (now(), 26, 4, false);
INSERT INTO room (created_at, capacity, room_number, student_access) VALUES (now(), 50, 11, true);
INSERT INTO room (created_at, capacity, room_number, student_access) VALUES (now(), 50, 12, false);
INSERT INTO room (created_at, capacity, room_number, student_access) VALUES (now(), 100, 13, true);
INSERT INTO room (created_at, capacity, room_number, student_access) VALUES (now(), 100, 14, false);
INSERT INTO room (created_at, capacity, room_number, student_access) VALUES (now(), 200, 21, true);
INSERT INTO room (created_at, capacity, room_number, student_access) VALUES (now(), 200, 26, false);

INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (1, 4);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (3, 1);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (3, 3);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (4, 1);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (4, 3);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (5, 1);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (5, 3);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (5, 4);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (6, 1);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (6, 3);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (6, 4);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (7, 4);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (8, 1);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (8, 4);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (9, 1);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (10, 1);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (10, 3);