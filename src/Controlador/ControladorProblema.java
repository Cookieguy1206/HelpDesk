package Controlador;

import Modelo.ConsultasProblema;
import Modelo.ModeloProblema;
import Vista.AñadirProblema;
import Vista.Soluciones;
import Vista.VerProblema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControladorProblema implements ActionListener {

    private final AñadirProblema AñadirProblema;
    private final VerProblema VistaProblema;
    private final Soluciones Soluciones;
    private final ModeloProblema Modelo;
    private final ConsultasProblema Problema;

    public ControladorProblema(AñadirProblema AñadirProblema, VerProblema VistaProblema, Soluciones Soluciones, ModeloProblema Modelo, ConsultasProblema Problema) {
        this.AñadirProblema = AñadirProblema;
        this.VistaProblema = VistaProblema;
        this.Soluciones = Soluciones;
        this.Modelo = Modelo;
        this.Problema = Problema;
        AñadirProblema.BtnEnviar.addActionListener(this);
        AñadirProblema.BtnCancelar.addActionListener(this);
        AñadirProblema.BtnVerProb.addActionListener(this);
        AñadirProblema.BtnVerAvan.addActionListener(this);
        VistaProblema.BtnVolver.addActionListener(this);
        Soluciones.BtnVolver.addActionListener(this);
    }

    public void Iniciar() throws SQLException {
        AñadirProblema.setTitle("Añadir Problema");
        AñadirProblema.setLocationRelativeTo(null);
        AñadirProblema.setVisible(true);
        AñadirProblema.TxtID.setVisible(false);
        AñadirProblema.TxtFecha.setVisible(false);
        VistaProblema.setTitle("Problemas");
        VistaProblema.setLocationRelativeTo(null);
        VistaProblema.setVisible(false);
        Soluciones.setTitle("Soluciones y Avances");
        Soluciones.setLocationRelativeTo(null);
        Soluciones.setVisible(false);
        Problema.Mostrar(VistaProblema.JTablaProblema);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == AñadirProblema.BtnEnviar) {
            Modelo.setNombreProb(AñadirProblema.TxtTituloSolicitud.getText());
            Modelo.setDetalleProb(AñadirProblema.TxtDetalleSolicitud.getText());

            if (Problema.Insertar(Modelo, AñadirProblema)) {
                JOptionPane.showMessageDialog(null, "Registro INSERTADO CORRECTAMENTE");
                Limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR el registro NO fue insertado");
                Limpiar();
            }
        }

        if (e.getSource() == AñadirProblema.BtnVerProb) {
            VistaProblema.setTitle("Problemas");
            VistaProblema.setVisible(true);
            VistaProblema.setLocationRelativeTo(null);
            AñadirProblema.setVisible(false);
            Problema.Mostrar(VistaProblema.JTablaProblema);
        }

        if (e.getSource() == VistaProblema.BtnVolver) {
            AñadirProblema.setVisible(true);
            VistaProblema.setVisible(false);
        }

        if (e.getSource() == AñadirProblema.BtnVerAvan) {         
            Soluciones.setVisible(true);
            AñadirProblema.setVisible(false);
        }
        
        if (e.getSource() == Soluciones.BtnVolver) {
            AñadirProblema.setVisible(true);
            Soluciones.setVisible(false);
        }
    }

    public void Limpiar() {
        AñadirProblema.TxtTituloSolicitud.setText(null);
        AñadirProblema.TxtDetalleSolicitud.setText(null);
        AñadirProblema.JCTipoSolicitud.setSelectedIndex(0);
        AñadirProblema.JCArea.setSelectedIndex(0);
        AñadirProblema.JCPrioridad.setSelectedIndex(0);
    }
}
