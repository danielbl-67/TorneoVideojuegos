use TorneoVideojuego;
-- Consulta multitabla --
-- Lista de jugadores y sus equipos con el nombre del organizador
SELECT p.nombre AS jugador_nombre,j.nombre_usuario,e.nombre AS equipo, o.rol, po.nombre AS organizador
FROM JUGADORES j JOIN PERSONAS p ON j.id_jugador = p.id_persona JOIN JUGADORES_EQUIPOS je ON j.id_jugador = je.id_jugador JOIN EQUIPOS e ON je.id_equipo = e.id_equipo
JOIN ORGANIZADORES o ON e.id_organizador = o.id_organizador JOIN PERSONAS po ON o.id_organizador = po.id_persona;

-- Total de equipos por torneo
SELECT t.nombre AS torneo, COUNT(et.id_equipo) AS total_equipos FROM TORNEOS t 
JOIN EQUIPOS_TORNEO et ON t.id_torneo = et.id_torneo GROUP BY t.id_torneo, t.nombre;

-- Jugadores que participan en mas de un equipo (Subconsulta)
SELECT p.nombre, j.nombre_usuario FROM JUGADORES j JOIN PERSONAS p ON j.id_jugador = p.id_persona
WHERE j.id_jugador IN ( SELECT id_jugador FROM JUGADORES_EQUIPOS GROUP BY id_jugador HAVING COUNT(id_equipo) > 1);

-- Partidas entre los equipos con el nombre del torneo
SELECT t.nombre AS torneo, e1.nombre AS equipo1, e2.nombre AS equipo2,p.fecha,  p.resultado
FROM PARTIDAS p JOIN TORNEOS t ON p.id_torneo = t.id_torneo JOIN EQUIPOS e1 ON p.id_equipo1 = e1.id_equipo
JOIN EQUIPOS e2 ON p.id_equipo2 = e2.id_equipo;

-- Cantidad de partidad jugadas por equipo
SELECT e.nombre, COUNT(p.id_partida) AS partidas_jugadas FROM EQUIPOS e LEFT JOIN PARTIDAS p 
ON e.id_equipo = p.id_equipo1 OR e.id_equipo = p.id_equipo2 GROUP BY e.id_equipo, e.nombre;

-- Actualizaciones --
-- Actualizacion de resultado si los erquipos estan en el mismo torneo 
UPDATE PARTIDAS p JOIN EQUIPOS_TORNEO et1 ON p.id_torneo = et1.id_torneo AND p.id_equipo1 = et1.id_equipo
JOIN EQUIPOS_TORNEO et2 ON p.id_torneo = et2.id_torneo AND p.id_equipo2 = et2.id_equipo SET p.resultado = 'Pendiente'
WHERE p.resultado IS NULL;

-- Actualizacion de cambio de nombre del torneo
UPDATE TORNEOS t JOIN JUEGOS j ON t.id_juego = j.id_juego SET t.nombre = REPLACE(t.nombre, 'CSGO Clash', 'CSGO COMPETITION'), j.nombre = 'CSGO COMPETITION'
WHERE j.nombre = 'CSGO Clash';

-- Borrado --
-- Borrar/eliminar los jugadores que no estan en ningun equipo
DELETE j FROM JUGADORES j LEFT JOIN JUGADORES_EQUIPOS je ON j.id_jugador = je.id_jugador WHERE je.id_jugador IS NULL;

-- Vistas --
-- Resumen de equipos y sus torneos
CREATE VIEW vista_equipo_torneo AS
SELECT e.nombre AS equipo, t.nombre AS torneo, t.fecha_inicio, t.fecha_fin FROM EQUIPOS_TORNEO et
JOIN EQUIPOS e ON et.id_equipo = e.id_equipo JOIN TORNEOS t ON et.id_torneo = t.id_torneo;
SELECT * FROM vista_equipo_torneo;

-- Jugadores,sus equipos y total de torneos participados 
CREATE VIEW vista_jugador_participacion AS
SELECT p.nombre, j.nombre_usuario, e.nombre AS equipo, COUNT(DISTINCT et.id_torneo) AS torneos FROM JUGADORES j
JOIN PERSONAS p ON j.id_jugador = p.id_persona JOIN JUGADORES_EQUIPOS je ON j.id_jugador = je.id_jugador
JOIN EQUIPOS e ON je.id_equipo = e.id_equipo JOIN EQUIPOS_TORNEO et ON e.id_equipo = et.id_equipo
GROUP BY j.id_jugador, e.id_equipo;
SELECT * FROM vista_jugador_participacion;


-- Funciones/Proocedimientos --
-- Registrar nuevo jugador
DELIMITER //
CREATE PROCEDURE registrar_jugador(
  IN nombre VARCHAR(100),
  IN email VARCHAR(100),
  IN nombre_usuario VARCHAR(100)
)
BEGIN
  DECLARE new_id INT;
  INSERT INTO PERSONAS(nombre, email, tipo) VALUES (nombre, email, 'jugador'); SET new_id = LAST_INSERT_ID();
  INSERT INTO JUGADORES(id_jugador, nombre_usuario) VALUES (new_id, nombre_usuario);
END;
//DELIMITER ;

-- Total de torneos de un equipo
DELIMITER //
CREATE FUNCTION torneos_por_equipo(equipo_id INT) RETURNS INT
DETERMINISTIC
BEGIN
  DECLARE total INT;
  SELECT COUNT(*) INTO total FROM EQUIPOS_TORNEO WHERE id_equipo = equipo_id;
  RETURN total;
END;
//DELIMITER ;

-- Resumen de partidas por torneo
DELIMITER //
CREATE PROCEDURE resumen_partidas(IN torneo_id INT)
BEGIN
  SELECT e1.nombre AS equipo1, e2.nombre AS equipo2, p.fecha, p.resultado FROM PARTIDAS p
  JOIN EQUIPOS e1 ON p.id_equipo1 = e1.id_equipo JOIN EQUIPOS e2 ON p.id_equipo2 = e2.id_equipo WHERE p.id_torneo = torneo_id;
END;
//DELIMITER ;

-- Disparadores --
-- Valida si un un tipo jugador no se inserte como organizador(Before)
DELIMITER //
CREATE TRIGGER before_insert_organizador
BEFORE INSERT ON ORGANIZADORES
FOR EACH ROW
BEGIN
  DECLARE tipo_persona ENUM('jugador', 'organizador');
  SELECT tipo INTO tipo_persona FROM PERSONAS WHERE id_persona = NEW.id_organizador;
  IF tipo_persona != 'organizador' THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Solo personas de tipo organizador pueden ser insertadas como ORGANIZADORES';
  END IF;
END;
//DELIMITER ;

-- Log despues de insertar una partida (After)
CREATE TABLE LOG_PARTIDAS (
  id_log INT AUTO_INCREMENT PRIMARY KEY,
  id_partida INT,
  mensaje TEXT,
  fecha_log DATETIME
);
DELIMITER //
CREATE TRIGGER after_insert_partida AFTER INSERT ON PARTIDAS FOR EACH ROW
BEGIN
  INSERT INTO LOG_PARTIDAS(id_partida, mensaje, fecha_log)
  VALUES (NEW.id_partida, CONCAT('Partida creada entre equipos ', NEW.id_equipo1, ' y ', NEW.id_equipo2), NOW());
END;
// DELIMITER ;









