CREATE DATABASE HelpDesk;

USE HelpDesk;

INSERT INTO Problema (idProblema, NombreProb, DetalleProb, FechaCreacion, RefIdPrioridad, RefAreaProb, RefTipoProb, RefEstado, RefPersona, RefSolucion) 
VALUES (2, "Impresora no funciona", "Mi impresora no imprime", CURRENT_TIMESTAMP(), 2, 5, 5, 0, 1, 2);

INSERT INTO Persona (idPersona, CorreoPersona) 
VALUES (2, "ch@gmail.com");

INSERT INTO Prioridad VALUES (3, "Baja");

INSERT INTO TipoProb VALUES (7, "Impresora");

INSERT INTO AreaProb VALUES (7, "Calidad");

DELETE FROM `helpdesk`.`Problema` WHERE (`idProblema` = '3');

ALTER TABLE `helpdesk`.`problema` DROP COLUMN `RefPersona`;

UPDATE `helpdesk`.`Prioridad` SET `idPrioridad` = '2', `Prioridad` = 'Media' WHERE (`idPrioridad` = '3');

DROP INDEX idPersona_FK_idx ON Problema;

SELECT * FROM Problema;

SELECT * FROM AreaProb;

SELECT * FROM Prioridad;

SELECT * FROM TipoProb;

SELECT * FROM Avances; 

SELECT * FROM Estado;

SELECT * FROM Persona;

SELECT * FROM Soluciones;

SELECT * FROM TablaProblema;

CREATE TRIGGER Audit_Prob_Sol AFTER INSERT ON Problema 
FOR EACH ROW 
INSERT INTO Soluciones(idSolucion, Solucion) VALUES (NEW.RefSolucion, '');

DROP TRIGGER Audit_Prob_Sol;

SELECT P.idProblema, P.NombreProb, P.DetalleProb, P.FechaCreacion, TP.TipoProb, PR.Prioridad, AP.AreaProb, ES.Estado, PE.CorreoPersona, SO.Solucion FROM Problema AS P
INNER JOIN TipoProb AS TP ON P.RefTipoProb = TP.idTipoProb
INNER JOIN Prioridad AS PR ON P.RefIdPrioridad = PR.idPrioridad
INNER JOIN Estado AS ES ON P.RefEstado = ES.idEstado
INNER JOIN Persona AS PE ON P.RefPersona = PE.idPersona
INNER JOIN Soluciones AS SO ON P.RefSolucion = SO.idSolucion
INNER JOIN AreaProb AS AP ON P.RefAreaProb = AP.idAreaProb;

SET FOREIGN_KEY_CHECKS=0












