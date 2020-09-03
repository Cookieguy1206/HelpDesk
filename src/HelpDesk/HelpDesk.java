package HelpDesk;

import Controlador.ControladorProblema;
import Modelo.ConsultasProblema;
import Modelo.ModeloProblema;
import Vista.AñadirProblema;
import java.sql.SQLException;

public class HelpDesk {

    public static void main(String[] args) throws SQLException {
        AñadirProblema VistaProblema = new AñadirProblema();
        ModeloProblema Modelo = new ModeloProblema();
        ConsultasProblema Problema = new ConsultasProblema();
        ControladorProblema ControladroProblema = new ControladorProblema(VistaProblema, Modelo, Problema);
        
        ControladroProblema.Iniciar();
    }
    
}
