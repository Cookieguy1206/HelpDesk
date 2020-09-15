package Controlador;

import Modelo.ConsultasProblema;
import Modelo.ModeloProblema;
import Vista.AñadirProblema;
import Vista.VerProblema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControladorProblema implements ActionListener {

    private final AñadirProblema AñadirProblema;
    private final VerProblema VistaProblema;
    private final ModeloProblema Modelo;
    private final ConsultasProblema Problema;

    public ControladorProblema(AñadirProblema AñadirProblema, VerProblema VistaProblema, ModeloProblema Modelo, ConsultasProblema Problema) {
        this.VistaProblema = VistaProblema;
        this.AñadirProblema = AñadirProblema;
        this.Modelo = Modelo;
        this.Problema = Problema;
        AñadirProblema.BtnEnviar.addActionListener(this);
        AñadirProblema.BtnCancelar.addActionListener(this);
        AñadirProblema.BtnVerProb.addActionListener(this);
    }

    public void Iniciar() throws SQLException {
        AñadirProblema.setTitle("Añadir Problema");
        AñadirProblema.setLocationRelativeTo(null);
        AñadirProblema.setVisible(true);
        AñadirProblema.TxtID.setVisible(false);
        AñadirProblema.TxtFecha.setVisible(false);
        VistaProblema.JCBuscar.setVisible(false);
        VistaProblema.setTitle("Problemas");
        VistaProblema.setLocationRelativeTo(null);
        VistaProblema.setVisible(false);
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
    }

    public void Limpiar() {
        AñadirProblema.TxtTituloSolicitud.setText(null);
        AñadirProblema.TxtDetalleSolicitud.setText(null);
        AñadirProblema.JCTipoSolicitud.setSelectedIndex(0);
        AñadirProblema.JCArea.setSelectedIndex(0);
        AñadirProblema.JCPrioridad.setSelectedIndex(0);
    }
}
