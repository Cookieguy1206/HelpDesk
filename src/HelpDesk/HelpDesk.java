package HelpDesk;

import Conexion.RecibirEmail;
import Controlador.ControladorProblema;
import Modelo.ConsultasProblema;
import Modelo.ModeloAvances;
import Modelo.ModeloPersona;
import Modelo.ModeloProblema;
import Modelo.ModeloSolucion;
import Vista.AñadirProblema;
import Vista.VistaTicket;
import Vista.VerProblema;
import Vista.VistaAvances;
import java.io.IOException;
import java.sql.SQLException;
import javax.mail.MessagingException;

public class HelpDesk {

    public static void main(String[] args) throws SQLException, IOException, MessagingException {

        ConsultasProblema Problema = new ConsultasProblema();

        Problema.Estetica();

        AñadirProblema AñadirProblema = new AñadirProblema();
        VerProblema VistaProblema = new VerProblema();
        VistaTicket Soluciones = new VistaTicket();
        VistaAvances VistaAvances = new VistaAvances();
        ModeloProblema Modelo = new ModeloProblema();
        ModeloPersona ModeloP = new ModeloPersona();
        ModeloSolucion ModeloS = new ModeloSolucion();
        ModeloAvances ModeloA = new ModeloAvances();
        RecibirEmail RecibirEmail = new RecibirEmail();

        ControladorProblema ControladroProblema = new ControladorProblema(AñadirProblema, VistaProblema, Soluciones, VistaAvances, Modelo, ModeloP, ModeloS, ModeloA, Problema, RecibirEmail);

        ControladroProblema.Iniciar();
    }
}
