DROP DATABASE TorneoVideojuego;
CREATE DATABASE TorneoVideojuego;
use TorneoVideojuego;
CREATE TABLE PERSONAS (
  id_persona INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  email VARCHAR(100),
  tipo ENUM('jugador', 'organizador')
);

CREATE TABLE JUGADORES (
  id_jugador INT PRIMARY KEY,
  nombre_usuario VARCHAR(100),
  FOREIGN KEY (id_jugador) REFERENCES PERSONAS(id_persona)
);

CREATE TABLE ORGANIZADORES (
  id_organizador INT PRIMARY KEY,
  rol VARCHAR(100),
  FOREIGN KEY (id_organizador) REFERENCES PERSONAS(id_persona)
);

CREATE TABLE JUEGOS (
  id_juego INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  genero VARCHAR(50)
);

CREATE TABLE TORNEOS (
  id_torneo INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  fecha_inicio DATE,
  fecha_fin DATE,
  id_juego INT,
  FOREIGN KEY (id_juego) REFERENCES JUEGOS(id_juego)
);

CREATE TABLE EQUIPOS (
  id_equipo INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  id_organizador INT,
  FOREIGN KEY (id_organizador) REFERENCES ORGANIZADORES(id_organizador)
);

CREATE TABLE JUGADORES_EQUIPOS (
  id_jugador INT,
  id_equipo INT,
  PRIMARY KEY (id_jugador, id_equipo),
  FOREIGN KEY (id_jugador) REFERENCES JUGADORES(id_jugador),
  FOREIGN KEY (id_equipo) REFERENCES EQUIPOS(id_equipo)
);

CREATE TABLE EQUIPOS_TORNEO (
  id_torneo INT,
  id_equipo INT,
  PRIMARY KEY (id_torneo, id_equipo),
  FOREIGN KEY (id_torneo) REFERENCES TORNEOS(id_torneo),
  FOREIGN KEY (id_equipo) REFERENCES EQUIPOS(id_equipo)
);

CREATE TABLE PARTIDAS (
  id_partida INT AUTO_INCREMENT PRIMARY KEY,
  id_torneo INT,
  id_equipo1 INT,
  id_equipo2 INT,
  fecha DATETIME,
  resultado VARCHAR(50),
  FOREIGN KEY (id_torneo) REFERENCES TORNEOS(id_torneo),
  FOREIGN KEY (id_equipo1) REFERENCES EQUIPOS(id_equipo),
  FOREIGN KEY (id_equipo2) REFERENCES EQUIPOS(id_equipo)
);
