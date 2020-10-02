package HelpDesk;

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
import java.sql.SQLException;

public class HelpDesk {

    public static void main(String[] args) throws SQLException {
        //Diseño para que se vea más bonita la vista
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AñadirProblema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        AñadirProblema AñadirProblema = new AñadirProblema();
        VerProblema VistaProblema = new VerProblema();
        VistaTicket Soluciones = new VistaTicket();
        VistaAvances VistaAvances = new VistaAvances();
        ModeloProblema Modelo = new ModeloProblema();
        ModeloPersona ModeloP = new ModeloPersona();
        ModeloSolucion ModeloS = new ModeloSolucion();
        ModeloAvances ModeloA = new ModeloAvances();
        ConsultasProblema Problema = new ConsultasProblema();

        ControladorProblema ControladroProblema = new ControladorProblema(AñadirProblema, VistaProblema, Soluciones, VistaAvances, Modelo, ModeloP, ModeloS, ModeloA, Problema);
        ControladroProblema.Iniciar();
    }

}
