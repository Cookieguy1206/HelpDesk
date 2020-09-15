package HelpDesk;

import Controlador.ControladorProblema;
import Modelo.ConsultasProblema;
import Modelo.ModeloProblema;
import Vista.AñadirProblema;
import Vista.VerProblema;
import java.sql.SQLException;

public class HelpDesk {

    public static void main(String[] args) throws SQLException {
        //Diseño para que se vea mas bonita la vista
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
        ModeloProblema Modelo = new ModeloProblema();
        ConsultasProblema Problema = new ConsultasProblema();

        ControladorProblema ControladroProblema = new ControladorProblema(AñadirProblema, VistaProblema, Modelo, Problema);
        ControladroProblema.Iniciar();
    }

}
