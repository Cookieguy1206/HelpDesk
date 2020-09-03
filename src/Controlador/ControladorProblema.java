package Controlador;

import Conexion.Conexion;
import Modelo.ConsultasProblema;
import Modelo.ModeloProblema;
import Vista.AñadirProblema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorProblema implements ActionListener {

    private final AñadirProblema VistaProblema;
    private final ModeloProblema Modelo;
    private final ConsultasProblema Problema;

    public ControladorProblema(AñadirProblema VistaProblema, ModeloProblema Modelo, ConsultasProblema Problema) {
        this.VistaProblema = VistaProblema;
        this.Modelo = Modelo;
        this.Problema = Problema;
        VistaProblema.BtnEnviar.addActionListener(this);
        VistaProblema.BtnCancelar.addActionListener(this);
    }

    public void Iniciar() throws SQLException {
        Conexion con = new Conexion();
        Connection conexion = con.getConnection();
        VistaProblema.setTitle("Añadir Problema");
        VistaProblema.setLocationRelativeTo(null);
        VistaProblema.setVisible(true);
        VistaProblema.TxtID.setVisible(false);
        VistaProblema.TxtFecha.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == VistaProblema.BtnEnviar) {
            Modelo.setNombreProb(VistaProblema.TxtTituloSolicitud.getText());
            Modelo.setDetalleProb(VistaProblema.TxtDetalleSolicitud.getText());

            if (Problema.Insertar(Modelo)) {
                JOptionPane.showMessageDialog(null, "Registro INSERTADO CORRECTAMENTE");
                Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR el registro NO fue insertado");
                Limpiar();
            }
        }
    }
    
    public void Limpiar(){
        VistaProblema.TxtTituloSolicitud.setText(null);
        VistaProblema.TxtDetalleSolicitud.setText(null);
        VistaProblema.JCTipoSolicitud.setSelectedIndex(0);
        VistaProblema.JCArea.setSelectedIndex(0);
        VistaProblema.JCPrioridad.setSelectedIndex(0);
    }
}
