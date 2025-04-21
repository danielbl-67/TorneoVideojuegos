-- CARGA DE DATOS - 

USE TorneoVideojuego;
SHOW TABLES;

-- TABLA: Persona
INSERT INTO PERSONAS (id_persona, nombre, email, tipo) VALUES
(1, 'Carlos Pérez', 'carlos@email.com', 'jugador'),
(2, 'María Gómez', 'maria@email.com', 'jugador'),
(3, 'Lucía Torres', 'lucia@email.com', 'organizador'),
(4, 'Jorge Salas', 'jorge@email.com', 'jugador');

-- TABLA: Jugador
INSERT INTO JUGADORES (id_jugador, nombre_usuario) VALUES
(1, 'GamerCarlos'),
(2, 'MariGames'),
(4, 'JorgeX');

-- TABLA: Organizador
INSERT INTO ORGANIZADORES (id_organizador, rol) VALUES
(3, 'admin');

-- TABLA: Juego
INSERT INTO JUEGOS (id_juego, nombre, genero) VALUES
(1, 'RAIMBOW SIX SIEGUE', 'FPS'),
(2, 'CSGO', 'FPS');

-- TABLA: Torneo
INSERT INTO TORNEOS (id_torneo, nombre, fecha_inicio, fecha_fin, id_juego) VALUES
(1, 'R6 World Cup', '2025-06-01', '2025-06-30', 1),
(2, 'CSGO Clash', '2025-07-01', '2025-07-15', 2);

-- TABLA: Equipo
INSERT INTO EQUIPOS (id_equipo, nombre, id_organizador) VALUES
(1, 'Team Alpha', 3),
(2, 'Team Omega', 3);

-- TABLA: Jugador_Equipo
INSERT INTO JUGADORES_EQUIPOS (id_jugador, id_equipo) VALUES
(1, 1),
(2, 1),
(4, 2);

-- TABLA: Equipo_Torneo
INSERT INTO EQUIPOS_TORNEO (id_equipo, id_torneo) VALUES
(1, 1),
(2, 1),
(1, 2),
(2, 2);

-- TABLA: Partida
INSERT INTO PARTIDAS (id_partida, id_torneo, id_equipo1, id_equipo2, fecha, resultado) VALUES
(1, 1, 1, 2, '2025-06-05', '2-1'),
(2, 2, 2, 1, '2025-07-05', '0-2');
