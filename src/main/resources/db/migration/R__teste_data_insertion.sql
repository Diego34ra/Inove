USE inove;

DELETE FROM tb_feedback;
DELETE FROM tb_content;
DELETE FROM tb_section;
DELETE FROM tb_admin_course;
DELETE FROM tb_instructor_course;
DELETE FROM tb_user;
DELETE FROM tb_school;
DELETE FROM tb_course;

INSERT INTO tb_school (id, city, federative_unit, name) VALUES
(1, "Orizona", "GO", "MABEV"),
(2, "Orizona", "GO", "Francelino"),
(3, "Pires do Rio", "GO", "Estadual");

INSERT INTO tb_user (id, birth_date, cpf, email, name, passworld, role, school_id) VALUES
(1, '2003-09-25', '12345678901', 'flavio@gmail.com', 'Flávio', '123', 'ADMINISTRATOR', null),
(2, '2003-01-12', '23456789012', 'joao@gmail.com', 'João Gabriel', '123', 'ADMINISTRATOR', null),
(3, '1990-03-11', '34567890123', 'diego@gmail.com', 'Diego', '123', 'INSTRUCTOR', null),
(4, '1992-06-30', '45678901234', 'italo@gmail.com', 'Italo', '123', 'INSTRUCTOR', null),
(5, '2000-08-05', '56789012345', 'jars@ifgoiano.com', 'Jose Antonio', '123', 'STUDENT', 2),
(6, '2001-09-10', '67890123456', 'breno@ifgoiano.com', 'Breno', '123', 'STUDENT', 3);

INSERT INTO tb_course (description, name) VALUES
('Curso de informatica basica', 'Informatica basica'),
('Curso de Exel', 'Exel basico'),
('Curso de PowerPoint', 'PowerPoint Basico');

INSERT INTO tb_admin_course (admin_id, course_id) VALUES
(1, 1),
(2, 2);

INSERT INTO tb_instructor_course (instructor_id, course_id) VALUES
(3, 1),
(4, 2);

INSERT INTO tb_section (description, title, course_id) VALUES
('O que é um computador?', 'Seção 1: Introdução', 1),
('Dicas de Feramentas uteis', 'Seção 2: Dicas para iniciantes', 1),
('Configurando o Ambiente', 'Seção 1: Introdução ao PowerPoint', 3);

INSERT INTO tb_content (description, title, section_id) VALUES
('O que são Sistemas Operacionais', 'Conteúdo 1: Bem vindo ao Curso!!', 1),
('Documentos de testo salvos na internet', 'Conteúdo 2: Google Docs', 1),
('Baixando e configurando a Ferramenta', 'Conteúdo 1: Baixando a Ferramenta', 3);

INSERT INTO tb_student_course (student_id, course_id) VALUES
(5, 1),
(6, 2);

INSERT INTO tb_feedback (comment, course_id, student_id) VALUES
('Ótimo curso, muito completo!', 1, 5),
('Amei aprender Powerpoint com esse curso!', 2, 6);