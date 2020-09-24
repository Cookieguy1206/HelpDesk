package Controlador;

import Modelo.ConsultasProblema;
import Modelo.ModeloPersona;
import Modelo.ModeloProblema;
import Modelo.ModeloSolucion;
import Vista.AñadirProblema;
import Vista.VistaTicket;
import Vista.VerProblema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControladorProblema implements ActionListener {

    private final AñadirProblema AñadirProblema;
    private final VerProblema VistaProblema;
    private final VistaTicket VistaTicket;
    private final ModeloProblema Modelo;
    private final ModeloPersona ModeloP;
    private final ModeloSolucion ModeloS;
    private final ConsultasProblema Problema;

    public ControladorProblema(AñadirProblema AñadirProblema, VerProblema VistaProblema, VistaTicket VistaTicket, ModeloProblema Modelo, ModeloPersona ModeloP, ModeloSolucion ModeloS, ConsultasProblema Problema) {
        this.AñadirProblema = AñadirProblema;
        this.VistaProblema = VistaProblema;
        this.VistaTicket = VistaTicket;
        this.Modelo = Modelo;
        this.ModeloP = ModeloP;
        this.ModeloS = ModeloS;
        this.Problema = Problema;
        AñadirProblema.BtnEnviar.addActionListener(this);
        AñadirProblema.BtnCancelar.addActionListener(this);
        AñadirProblema.BtnVerProb.addActionListener(this);
        AñadirProblema.BtnVerAvan.addActionListener(this);
        VistaProblema.BtnVolver.addActionListener(this);
        VistaProblema.JMenuVer.addActionListener(this);
        VistaTicket.BtnVolver.addActionListener(this);
        VistaTicket.BtnGuardar.addActionListener(this);
    }

    public void Iniciar() throws SQLException {
        AñadirProblema.setTitle("Añadir Problema");
        AñadirProblema.setLocationRelativeTo(null);
        AñadirProblema.setVisible(true);
        AñadirProblema.TxtID.setVisible(false);
        AñadirProblema.TxtFecha.setVisible(false);
        AñadirProblema.TxtCorreo.setEnabled(false);
        AñadirProblema.JCSubTipoSol.setEnabled(false);
        AñadirProblema.JCSubSubTipo.setEnabled(false);
        VistaProblema.setTitle("Problemas");
        VistaProblema.setLocationRelativeTo(null);
        VistaProblema.setVisible(false);
        VistaTicket.setTitle("Soluciones y Avances");
        VistaTicket.setLocationRelativeTo(null);
        VistaTicket.setVisible(false);
        VistaTicket.TxtCorreoTicket.setEnabled(false);
        //VistaTicket.TxtIDSolucion.setVisible(false);
        Problema.Mostrar(VistaProblema.JTablaProblema);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == AñadirProblema.BtnEnviar) {
            Modelo.setNombreProb(AñadirProblema.TxtTituloSolicitud.getText());
            Modelo.setDetalleProb(AñadirProblema.TxtDetalleSolicitud.getText());
            ModeloP.setCorreoPersona(AñadirProblema.TxtCorreo.getText());
            ModeloS.setSolucion(VistaTicket.TxtSolucion.getText());

            if (Problema.InsertarProblema(Modelo, AñadirProblema) && Problema.PlantillaSolucion(ModeloS)) {
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
            VistaTicket.setVisible(true);
            AñadirProblema.setVisible(false);
        }

        if (e.getSource() == VistaTicket.BtnVolver) {
            AñadirProblema.setVisible(true);
            VistaTicket.setVisible(false);
        }

        if (e.getSource() == VistaProblema.JMenuVer) {
            VistaProblema.setVisible(false);
            VistaTicket.setVisible(true);

            int SelectedRow = VistaProblema.JTablaProblema.getSelectedRow();
            int NumSelectedRow = VistaProblema.JTablaProblema.getSelectedRowCount();

            if (SelectedRow >= 0 && NumSelectedRow == 1) {
                VistaTicket.TxtIDTicket.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 0).toString());
                VistaTicket.JCAreaTicket.setSelectedItem(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 6).toString());
                VistaTicket.JCEstadoTicket.setSelectedItem(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 7).toString());
                VistaTicket.TxtDescripcion.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 2).toString());
                VistaTicket.TxtIDSolucion.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 0).toString());
                VistaTicket.TxtSolucion.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 9).toString());

                VistaTicket.TxtIDTicket.setEditable(false);
                VistaTicket.JCAreaTicket.setEnabled(false);
                VistaTicket.TxtDescripcion.setEditable(false);
            }
        }

        if (e.getSource() == VistaTicket.BtnGuardar) {

            Modelo.setRefEstado(VistaTicket.JCEstadoTicket.getSelectedIndex());
            ModeloS.setSolucion(VistaTicket.TxtSolucion.getText());

            if (Problema.CambiarEstado(Modelo, VistaTicket)) {
            } else {
                JOptionPane.showMessageDialog(null, "ERROR el estado NO ha podido ser cambiado");
                Problema.Mostrar(VistaProblema.JTablaProblema);
            }

            if (Problema.InsertarSolucion(ModeloS, VistaTicket, Modelo)) {
            } else {
                JOptionPane.showMessageDialog(null, "ERROR la solucion NO se pudo realizar");
                Limpiar();
            }
        }
    }

    public void Limpiar() {
        AñadirProblema.TxtTituloSolicitud.setText(null);
        AñadirProblema.TxtDetalleSolicitud.setText(null);
        AñadirProblema.TxtCorreo.setText(null);
        AñadirProblema.JCTipoSolicitud.setSelectedIndex(0);
        AñadirProblema.JCArea.setSelectedIndex(0);
        AñadirProblema.JCPrioridad.setSelectedIndex(0);
    }
}
