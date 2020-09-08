package HelpDesk;

import Controlador.ControladorProblema;
import Modelo.ConsultasProblema;
import Modelo.ModeloProblema;
import Vista.AñadirProblema;
import Vista.VerProblema;
import java.sql.SQLException;

public class HelpDesk {

    public static void main(String[] args) throws SQLException {
        AñadirProblema AñadirProblema = new AñadirProblema();
        VerProblema VistaProblema = new VerProblema();
        ModeloProblema Modelo = new ModeloProblema();
        ConsultasProblema Problema = new ConsultasProblema();
        
        ControladorProblema ControladroProblema = new ControladorProblema(AñadirProblema, VistaProblema, Modelo, Problema);       
        ControladroProblema.Iniciar();
    }
    
}
