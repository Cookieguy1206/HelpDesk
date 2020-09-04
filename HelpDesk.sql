CREATE DATABASE HelpDesk;

USE HelpDesk;

INSERT INTO Problema (idProblema, NombreProb, DetalleProb, FechaCreacion, idPrioridad) 
VALUES (1, "Impresora no funciona", "Mi impresora no imprime", CURRENT_TIMESTAMP(), 2); 

INSERT INTO Prioridad VALUES (3, "Baja");

INSERT INTO TipoProb VALUES (NULL, "Internet");

INSERT INTO AreaProb VALUES (NULL, "Insumos");

DELETE FROM `helpdesk`.`Problema` WHERE (`idProblema` = '3');

ALTER TABLE `helpdesk`.`problema` CHANGE COLUMN `FechaCreacion` `FechaCreacion` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP;

UPDATE `helpdesk`.`Prioridad` SET `idPrioridad` = '2', `Prioridad` = 'Media' WHERE (`idPrioridad` = '3');

SELECT * FROM Problema;

SELECT * FROM AreaProb;

SELECT * FROM Prioridad;

SELECT * FROM TipoProb;

SELECT * FROM UnionAP;

SELECT P.NombreProb, P.DetalleProb, P.FechaCreacion, TP.TipoProb, PR.Prioridad, AP.AreaProb FROM Problema AS P
INNER JOIN TipoProb AS TP ON P.idProblema = TP.idTipoProb
INNER JOIN Prioridad AS PR ON P.idProblema = PR.idPrioridad
INNER JOIN AreaProb AS AP ON P.idProblema = AP.idAreaProb;

SET FOREIGN_KEY_CHECKS=0












