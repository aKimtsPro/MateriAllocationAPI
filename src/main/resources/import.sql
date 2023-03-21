INSERT INTO material (created_at, last_modified_at, "name") VALUES (now(), null, 'projecteur');
INSERT INTO material (created_at, last_modified_at, "name") VALUES (now(), null, 'tableau blanc');
INSERT INTO material (created_at, last_modified_at, "name") VALUES (now(), null, 'tableau numérique');
INSERT INTO material (created_at, last_modified_at, "name") VALUES (now(), null, 'machine à café');

INSERT INTO "user" ("role", created_at,  address_city, address_country, address_number, address_postal_code, address_region, address_street, email, first_name, last_name, "login", "password", phone, "enabled") values ('ADMIN', now(), 'Namur', 'Belgique', '40', '5100', 'Namur', 'Place de l Ange', 'admin@user.be', 'admin', 'Histateur', 'admin_01', '$2a$12$6l1d0htIPTEDzQcbqpl1K.0D7lRugQ2VIyTjxeDW3g30M3cry0iGO', '0400/00.00.00', true);
INSERT INTO "user" ("role", created_at,  address_city, address_country, address_number, address_postal_code, address_region, address_street, email, first_name, last_name, "login", "password", phone, "enabled") values ('STUDENT', now(), 'Namur', 'Belgique', '40', '5100', 'Namur', 'Place de l Ange', 'student@user.be', 'admin', 'Histateur', 'stud_01', '$2a$12$JLMj/2sd5Dbwy571lvePEOhxVsFqcxx/T2JtA4S4dCya2TAGJ5Nd6', '0400/00.00.01', true);
INSERT INTO "user" ("role", created_at,  address_city, address_country, address_number, address_postal_code, address_region, address_street, email, first_name, last_name, "login", "password", phone, "enabled") values ('TEACHER', now(), 'Namur', 'Belgique', '40', '5100', 'Namur', 'Place de l Ange', 'teacher@user.be', 'admin', 'Histateur', 'teach_01', '$2a$12$krMfjh.X4dFuALL/2btLT.zISQKLb6qqYJOyci8on7kLnLhdOwQFe', '0400/00.00.02', true);

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

INSERT INTO room_materials (room_room_id, materials_material_id) VALUES ( 1, 4);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES ( 3, 1);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES ( 3, 3);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES ( 4, 1);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES ( 4, 3);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES ( 5, 1);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES ( 5, 3);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES ( 5, 4);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES ( 6, 1);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES ( 6, 3);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES ( 6, 4);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES ( 7, 4);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES ( 8, 1);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES ( 8, 4);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES ( 9, 1);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (10, 1);
INSERT INTO room_materials (room_room_id, materials_material_id) VALUES (10, 3);

INSERT INTO request(created_at, current_status, "date", begin_time, end_time, justification, needed_capacity, made_by_id) VALUES (now(), 'PENDING','2023-05-05', '09:00:00', '12:00:00', 'because', 20, 3)

INSERT INTO request_status(created_at, changed_by_id, justification, request_status, request_id) VALUES (now(), 1, 'accepted', 'PENDING', 1)