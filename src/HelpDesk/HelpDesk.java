package HelpDesk;

import Controlador.ControladorRecibirEmail;
import Controlador.ControladorProblema;
import Modelo.ConsultasProblema;
import Modelo.ModeloAvances;
import Modelo.ModeloCorreo;
import Modelo.ModeloPersona;
import Modelo.ModeloProblema;
import Modelo.ModeloSolucion;
import Vista.AñadirProblema;
import Vista.SplashScreen;
import Vista.VistaTicket;
import Vista.VerProblema;
import Vista.VistaAvances;
import com.sun.awt.AWTUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.Timer;

public class HelpDesk {

    static Timer timer;
    static ActionListener AL;

    public static void main(String[] args) throws SQLException, IOException, MessagingException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {

        ConsultasProblema Problema = new ConsultasProblema();

        Problema.Estetica();

        AñadirProblema AñadirProblema = new AñadirProblema();
        VerProblema VistaProblema = new VerProblema();
        VistaTicket Soluciones = new VistaTicket();
        VistaAvances VistaAvances = new VistaAvances();
        SplashScreen SplashScreen = new SplashScreen();
        ModeloProblema Modelo = new ModeloProblema();
        ModeloPersona ModeloP = new ModeloPersona();
        ModeloSolucion ModeloS = new ModeloSolucion();
        ModeloAvances ModeloA = new ModeloAvances();
        ModeloCorreo ModeloC = new ModeloCorreo();
        ControladorRecibirEmail RecEm = new ControladorRecibirEmail();

        ControladorProblema ControladroProblema = new ControladorProblema(AñadirProblema, VistaProblema, Soluciones, VistaAvances, SplashScreen, Modelo, ModeloP, ModeloS, ModeloA, ModeloC, Problema, RecEm);

        AL = (ActionEvent e) -> {
            if (SplashScreen.BarraCarga.getValue() < 200) {
                SplashScreen.setVisible(true);
                SplashScreen.setLocationRelativeTo(null);
                SplashScreen.BarraCarga.setValue((SplashScreen.BarraCarga.getValue() + 1));
            }
            if (SplashScreen.BarraCarga.getValue() == 150) {
                try {
                    SplashScreen.setVisible(true);
                    SplashScreen.setLocationRelativeTo(null);
                    RecEm.RecibirEmail();
                } catch (MessagingException | IOException | SQLException | com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException ex) {
                    Logger.getLogger(HelpDesk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (SplashScreen.BarraCarga.getValue() >= 200) {
                try {
                    timer.stop();
                    SplashScreen.dispose();
                    ControladroProblema.Iniciar();
                } catch (SQLException | com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException | IOException | MessagingException ex) {
                    Logger.getLogger(HelpDesk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        timer = new Timer(10, AL);
        AWTUtilities.setWindowOpaque(SplashScreen, false);
        timer.start();
    }
}
