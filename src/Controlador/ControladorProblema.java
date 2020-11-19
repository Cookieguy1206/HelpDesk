package Controlador;

import Modelo.ConsultasProblema;
import Modelo.ModeloAvances;
import Modelo.ModeloCorreo;
import Modelo.ModeloPersona;
import Modelo.ModeloProblema;
import Modelo.ModeloSolucion;
import Vista.AñadirProblema;
import Vista.VistaTicket;
import Vista.VerProblema;
import Vista.VistaAvances;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class ControladorProblema implements ActionListener {

    private final AñadirProblema AñadirProblema;
    private final VerProblema VistaProblema;
    private final VistaTicket VistaTicket;
    private final VistaAvances VistaAvances;
    private final ModeloProblema Modelo;
    private final ModeloPersona ModeloP;
    private final ModeloSolucion ModeloS;
    private final ModeloAvances ModeloA;
    private final ConsultasProblema Problema;
    private final ControladorRecibirEmail RecEm;

    public ControladorProblema(AñadirProblema AñadirProblema, VerProblema VistaProblema, VistaTicket VistaTicket, VistaAvances VistaAvances, ModeloProblema Modelo, ModeloPersona ModeloP, ModeloSolucion ModeloS, ModeloAvances ModeloA, ModeloCorreo ModeloC, ConsultasProblema Problema, ControladorRecibirEmail RecEm) {
        this.AñadirProblema = AñadirProblema;
        this.VistaProblema = VistaProblema;
        this.VistaTicket = VistaTicket;
        this.VistaAvances = VistaAvances;
        this.Modelo = Modelo;
        this.ModeloP = ModeloP;
        this.ModeloS = ModeloS;
        this.ModeloA = ModeloA;
        this.Problema = Problema;
        this.RecEm = RecEm;
        AñadirProblema.BtnCategorizar.addActionListener(this);
        AñadirProblema.BtnVerProb.addActionListener(this);
        AñadirProblema.BtnVerProb.addActionListener(this);
        AñadirProblema.BtnVerAvan.addActionListener(this);
        VistaProblema.JMenuVer.addActionListener(this);
        VistaProblema.JMenuVerAv.addActionListener(this);
        VistaProblema.JMenuCategorizar.addActionListener(this);
        VistaProblema.BtnRepSol.addActionListener(this);
        VistaProblema.BtnRepProc.addActionListener(this);
        VistaProblema.BtnRepPen.addActionListener(this);
        VistaTicket.BtnVolver.addActionListener(this);
        VistaTicket.BtnGuardar.addActionListener(this);
        VistaAvances.BtnCerrar.addActionListener(this);
    }

    public void Iniciar() throws SQLException, MessagingException, IOException, javax.mail.MessagingException {
        VistaProblema.setTitle("Problemas");
        VistaProblema.setLocationRelativeTo(null);
        VistaProblema.setVisible(true);
        VistaProblema.setResizable(false);
        AñadirProblema.setTitle("Añadir Problema");
        AñadirProblema.setLocationRelativeTo(null);
        AñadirProblema.setVisible(false);
        AñadirProblema.setResizable(false);
        AñadirProblema.TxtID.setVisible(false);
        AñadirProblema.TxtFecha.setVisible(false);
        AñadirProblema.TxtCorreo.setEditable(false);
        AñadirProblema.TxtTituloSolicitud.setEditable(false);
        AñadirProblema.TxtDetalleSolicitud.setEditable(false);
        VistaTicket.setTitle("Soluciones y Avances");
        VistaTicket.setLocationRelativeTo(null);
        VistaTicket.setVisible(false);
        VistaTicket.setResizable(false);
        VistaAvances.setTitle("Avances");
        VistaAvances.setLocationRelativeTo(null);
        VistaAvances.setVisible(false);
        VistaAvances.setResizable(false);
        VistaTicket.TxtIDSolucion.setVisible(false);
        VistaTicket.TxtCorreoTicket.setEnabled(false);
        VistaTicket.TxtIDAvance.setVisible(false);
        VistaTicket.setResizable(false);
        Problema.Mostrar(VistaProblema.JTablaProblema);
        RecEm.RecibirEmail();
        ActualizarEmail();
        ActualizarTabla();
    }

    Timer timer;

    //Timer
    public void ActualizarTabla() {

        SwingUtilities.invokeLater(() -> {
            ActionListener Action = (ActionEvent evt) -> {

                Problema.Mostrar(VistaProblema.JTablaProblema);
                System.out.println("Actualizando...");

            };
            timer = new Timer(10000, Action);
            timer.setInitialDelay(6000);
            timer.start();
        });
    }

    public void ActualizarEmail() {
        SwingUtilities.invokeLater(() -> {
            ActionListener Action = (ActionEvent evt) -> {

                System.out.println("Insertando...");
                try {
                    RecEm.RecibirEmail();
                } catch (javax.mail.MessagingException | IOException | SQLException | com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException ex) {
                    Logger.getLogger(ControladorRecibirEmail.class.getName()).log(Level.SEVERE, null, ex);
                }
            };
            int Minutos = 30;
            int Tiempo = 60 * Minutos;
            int Delay = Tiempo;
            timer = new Timer(Delay, Action);
            timer.setInitialDelay(Delay);
            timer.start();
        });
    }

    //Detener Timer
    public void DetenerTimer() {
        timer.stop();
        System.out.println("Timer Detenido");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Botones Añadir Problema
        try {
            if (e.getSource() == AñadirProblema.BtnCategorizar) {
                Modelo.setRefTipoProb(AñadirProblema.JCTipoSolicitud.getSelectedIndex());
                Modelo.setRefSTipoProb(AñadirProblema.JCSubTipoSol.getSelectedIndex());
                Modelo.setRefSSTipoProb(AñadirProblema.JCSubSubTipo.getSelectedIndex());
                Modelo.setNombreProb(AñadirProblema.TxtTituloSolicitud.getText());
                Modelo.setDetalleProb(AñadirProblema.TxtDetalleSolicitud.getText());
                Modelo.setRefAreaProb(AñadirProblema.JCArea.getSelectedIndex());
                Modelo.setRefIdPrioridad(AñadirProblema.JCPrioridad.getSelectedIndex());
                Modelo.setIdProblema(Integer.parseInt(AñadirProblema.TxtID.getText()));

                if (Problema.Categorizar(Modelo, AñadirProblema)) {
                    JOptionPane.showMessageDialog(null, "Categorizado!!");
                    Problema.Mostrar(VistaProblema.JTablaProblema);
                    AñadirProblema.setVisible(false);
                    VistaProblema.setVisible(true);
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR el estado NO ha podido ser categorizado");
                    Limpiar();
                }
            }
        } catch (HeadlessException ex) {
            System.out.println("Error " + ex);
        }

        if (e.getSource() == AñadirProblema.BtnVerProb) {
            VistaProblema.setTitle("Problemas");
            VistaProblema.setVisible(true);
            VistaProblema.setLocationRelativeTo(null);
            AñadirProblema.setVisible(false);
            Problema.Mostrar(VistaProblema.JTablaProblema);
        }

        if (e.getSource() == AñadirProblema.BtnVerAvan) {
            VistaTicket.setVisible(true);
            AñadirProblema.setVisible(false);
        }
        //Cierre Botones Añadir Problema

        //Botones Ver Problema
        if (e.getSource() == VistaProblema.JMenuVerAv) {
            int SelectedRow = VistaProblema.JTablaProblema.getSelectedRow();
            int NumSelectedRow = VistaProblema.JTablaProblema.getSelectedRowCount();

            if (SelectedRow >= 0 && NumSelectedRow == 1) {
                VistaAvances.TxtIDAvance.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 0).toString());

                VistaTicket.TxtIDTicket.setVisible(false);
                VistaProblema.setTitle("Avances");
                VistaAvances.setVisible(true);
                VistaProblema.setLocationRelativeTo(null);
                Problema.MostrarAvances(VistaAvances.JTablaAvances, VistaAvances);
            }
        }

        if (e.getSource() == VistaProblema.JMenuCategorizar) {
            VistaProblema.setVisible(false);
            AñadirProblema.setVisible(true);
            Limpiar2();

            int SelectedRow = VistaProblema.JTablaProblema.getSelectedRow();
            int NumSelectedRow = VistaProblema.JTablaProblema.getSelectedRowCount();

            if (SelectedRow >= 0 && NumSelectedRow == 1) {
                AñadirProblema.TxtID.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 0).toString());
                AñadirProblema.TxtCorreo.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 1).toString());
                AñadirProblema.TxtTituloSolicitud.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 2).toString());
                AñadirProblema.TxtDetalleSolicitud.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 3).toString());
            }
        }

        if (e.getSource() == VistaProblema.BtnRepSol) {
            Problema.GenerarReporteSol();
        }

        if (e.getSource() == VistaProblema.BtnRepProc) {
            Problema.GenerarReporteProc();
        }

        if (e.getSource() == VistaProblema.BtnRepPen) {
            Problema.GenerarReportePen();
        }
        //Cierre Botones Ver Problema

        //Botones Vista Ticket
        if (e.getSource() == VistaProblema.JMenuVer) {
            VistaProblema.setVisible(false);
            VistaTicket.setVisible(true);
            Limpiar2();

            int SelectedRow = VistaProblema.JTablaProblema.getSelectedRow();
            int NumSelectedRow = VistaProblema.JTablaProblema.getSelectedRowCount();

            if (SelectedRow >= 0 && NumSelectedRow == 1) {
                VistaTicket.TxtIDTicket.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 0).toString());
                VistaTicket.TxtIDSolucion.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 0).toString());
                VistaTicket.TxtIDAvance.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 0).toString());
                VistaTicket.TxtCorreoTicket.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 1).toString());
                VistaTicket.txtTitulo.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 2).toString());
                VistaTicket.TxtDescripcion.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 3).toString());
                VistaTicket.JCAreaTicket.setSelectedItem(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 5).toString());
                VistaTicket.JCEstadoTicket.setSelectedItem(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 6).toString());
                VistaTicket.TxtSolucion.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 7).toString());

                VistaTicket.TxtIDTicket.setEditable(false);
                VistaTicket.JCAreaTicket.setEnabled(false);
                VistaTicket.TxtDescripcion.setEditable(false);
                VistaTicket.txtTitulo.setEditable(false);
            }
        }

        if (e.getSource() == VistaTicket.BtnGuardar) {

            Modelo.setRefEstado(VistaTicket.JCEstadoTicket.getSelectedIndex());
            ModeloS.setSolucion(VistaTicket.TxtSolucion.getText());
            ModeloA.setAvance(VistaTicket.TxtAvance.getText());

            if (Problema.CambiarEstado(Modelo, VistaTicket)) {
            } else {
                JOptionPane.showMessageDialog(null, "ERROR el estado NO ha podido ser cambiado");
                Problema.Mostrar(VistaProblema.JTablaProblema);
            }

            if (Problema.InsertarSolucion(ModeloS, VistaTicket, Modelo)) {
                Problema.Mostrar(VistaProblema.JTablaProblema);
                VistaTicket.setVisible(false);
                VistaProblema.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR la solucion NO se pudo realizar");
                Limpiar();
            }

            if (Problema.InsertarAvance(ModeloA, VistaTicket, Modelo)) {
                Problema.Mostrar(VistaProblema.JTablaProblema);
                VistaTicket.setVisible(false);
                VistaProblema.setVisible(true);
            } else {
                Limpiar();
            }

            if (Problema.ListarAvances(ModeloA, VistaTicket, Modelo)) {
                JOptionPane.showMessageDialog(null, "Listo!!");
                Problema.Mostrar(VistaProblema.JTablaProblema);
                VistaTicket.setVisible(false);
                VistaProblema.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR el avance NO se pudo realizar");
                Limpiar();
            }
        }

        if (e.getSource() == VistaTicket.BtnVolver) {
            VistaProblema.setVisible(true);
            VistaTicket.setVisible(false);
        }
        //Cierre de botones Vista Ticket

        //Botones Vista Avances
        if (e.getSource() == VistaAvances.BtnCerrar) {
            VistaAvances.setVisible(false);
        }
    }
    //Final del ActionPeromed

    public void Limpiar() {
        AñadirProblema.JCTipoSolicitud.setSelectedIndex(0);
        AñadirProblema.JCArea.setSelectedIndex(0);
        AñadirProblema.JCPrioridad.setSelectedIndex(0);
    }

    public void Limpiar2() {
        VistaTicket.TxtAvance.setText(null);
    }
}
