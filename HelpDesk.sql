CREATE DATABASE HelpDesk;

USE HelpDesk;

INSERT INTO Problema (idProblema, NombreProb, DetalleProb, FechaCreacion, RefIdPrioridad, RefAreaProb, RefTipoProb, RefEstado, RefPersona, RefSolucion, RefAvances) 
VALUES (1, "Impresora no funciona", "Mi impresora no imprime", CURRENT_TIMESTAMP(), 2, 5, 5, 0, 1, 1, 1);

INSERT INTO Persona (idPersona, CorreoPersona) VALUES (2, "ch@gmail.com");

INSERT INTO TipoProb VALUES (7, "Impresora");

INSERT INTO AreaProb VALUES (7, "Calidad");

INSERT INTO `helpdesk`.`Soluciones` (`Solucion`) VALUES ('');

DELETE FROM `helpdesk`.`Problema` WHERE (`idProblema` = '1');
DELETE FROM `helpdesk`.`Soluciones` WHERE (`idSolucion` = '1');
DELETE FROM `helpdesk`.`Avances` WHERE (`idAvances` = '1');

ALTER TABLE `helpdesk`.`problema` DROP COLUMN `RefPersona`;

UPDATE `helpdesk`.`Prioridad` SET `idPrioridad` = '2', `Prioridad` = 'Media' WHERE (`idPrioridad` = '3');

DROP INDEX idPersona_FK_idx ON Problema;

SELECT * FROM Problema;

SELECT * FROM AreaProb;

SELECT * FROM Prioridad;

SELECT * FROM TipoProb;

SELECT * FROM STipoProb;

SELECT * FROM SSTipoProb;

SELECT * FROM Avances;

SELECT * FROM Estado;

SELECT * FROM Persona;

SELECT * FROM Soluciones;

SELECT * FROM TablaProblema;

SELECT * FROM TablaAvances;

CREATE TRIGGER Audit_Prob_Sol AFTER INSERT ON Problema 
FOR EACH ROW 
INSERT INTO Soluciones(idSolucion, Solucion) VALUES (NEW.RefSolucion, '');

CREATE TRIGGER Audit_Avances AFTER INSERT ON Problema
FOR EACH ROW
INSERT INTO Avances(idAvances, idAvanceProb, Avance, FechaAvance, RefEstado) VALUES (NEW.RefAvances, NEW.idProblema, '', CURRENT_TIMESTAMP(), NEW.RefEstado);

DROP TRIGGER Audit_Prob_Sol;
DROP TRIGGER Audit_Avances;

SELECT P.idProblema, P.NombreProb, P.DetalleProb, P.FechaCreacion, AP.AreaProb, ES.Estado, SO.Solucion FROM Problema AS P
INNER JOIN Estado AS ES ON P.RefEstado = ES.idEstado
INNER JOIN Persona AS PE ON P.RefPersona = PE.idPersona
INNER JOIN AreaProb AS AP ON P.RefAreaProb = AP.idAreaProb
INNER JOIN Soluciones AS SO ON P.RefSolucion = SO.idSolucion;

SELECT AV.idAvances, P.idProblema, AV.Avance, AV.FechaAvance, ES.Estado FROM Avances AS AV
INNER JOIN Problema AS P ON AV.idAvanceProb = P.idProblema
INNER JOIN Estado AS ES ON P.RefEstado = ES.idEstado;

#INCREMENTAR EL ID DEL AVANCE PARA HACER UN HISTORIAL
INSERT INTO Avances (idAvances, Avance, idAvanceProb, FechaAvance, RefEstado)
SELECT * FROM (SELECT 4, 'Ejemplo Avance 4', 4, CURRENT_TIMESTAMP() , 0) AS TMP
	WHERE NOT EXISTS(SELECT idAvanceProb FROM Avances WHERE idAvanceProb = 4) 
    LIMIT 1;

SET FOREIGN_KEY_CHECKS=0












