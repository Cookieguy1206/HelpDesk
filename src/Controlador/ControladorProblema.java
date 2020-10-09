package Controlador;

import Conexion.Conexion;
import Conexion.RecibirEmail;
import Modelo.ConsultasProblema;
import Modelo.ModeloAvances;
import Modelo.ModeloPersona;
import Modelo.ModeloProblema;
import Modelo.ModeloSolucion;
import Vista.AñadirProblema;
import Vista.VistaTicket;
import Vista.VerProblema;
import Vista.VistaAvances;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

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
    private final RecibirEmail RecibirEmail;

    public ControladorProblema(AñadirProblema AñadirProblema, VerProblema VistaProblema, VistaTicket VistaTicket, VistaAvances VistaAvances, ModeloProblema Modelo, ModeloPersona ModeloP, ModeloSolucion ModeloS, ModeloAvances ModeloA, ConsultasProblema Problema, RecibirEmail RecibirEmail) {
        this.AñadirProblema = AñadirProblema;
        this.VistaProblema = VistaProblema;
        this.VistaTicket = VistaTicket;
        this.VistaAvances = VistaAvances;
        this.Modelo = Modelo;
        this.ModeloP = ModeloP;
        this.ModeloS = ModeloS;
        this.ModeloA = ModeloA;
        this.Problema = Problema;
        this.RecibirEmail = RecibirEmail;
        AñadirProblema.BtnEnviar.addActionListener(this);
        AñadirProblema.BtnCancelar.addActionListener(this);
        AñadirProblema.BtnVerProb.addActionListener(this);
        AñadirProblema.BtnVerAvan.addActionListener(this);
        VistaProblema.BtnVolver.addActionListener(this);
        VistaProblema.JMenuVer.addActionListener(this);
        VistaProblema.JMenuVerAv.addActionListener(this);
        VistaTicket.BtnVolver.addActionListener(this);
        VistaTicket.BtnGuardar.addActionListener(this);
        VistaAvances.BtnCerrar.addActionListener(this);
    }

    public void Iniciar() throws SQLException, MessagingException, IOException {
        AñadirProblema.setTitle("Añadir Problema");
        AñadirProblema.setLocationRelativeTo(null);
        AñadirProblema.setVisible(true);
        AñadirProblema.TxtID.setVisible(false);
        AñadirProblema.TxtFecha.setVisible(false);
        AñadirProblema.TxtCorreo.setEnabled(false);
        //AñadirProblema.JCSubTipoSol.setEnabled(false);
        //AñadirProblema.JCSubSubTipo.setEnabled(false);
        VistaProblema.setTitle("Problemas");
        VistaProblema.setLocationRelativeTo(null);
        VistaProblema.setVisible(false);
        VistaTicket.setTitle("Soluciones y Avances");
        VistaTicket.setLocationRelativeTo(null);
        VistaTicket.setVisible(false);
        VistaAvances.setTitle("Avances");
        VistaAvances.setLocationRelativeTo(null);
        VistaAvances.setVisible(false);
        VistaTicket.TxtIDSolucion.setVisible(false);
        VistaTicket.TxtCorreoTicket.setEnabled(false);
        VistaTicket.TxtIDAvance.setVisible(false);
        Problema.Mostrar(VistaProblema.JTablaProblema);
        Problema.IniciarTrigger(Modelo);
        RecibirEmail.RecibirEmail();
        System.out.println("\nVariable Correo: " + RecibirEmail.Correo);
        System.out.println("Variable Asunto: " + RecibirEmail.Sujeto);
        System.out.println("Variable Contenido: " + RecibirEmail.Contenido + "\n");
        InsertarCorreo();
        InsertarContenido();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == AñadirProblema.BtnEnviar) {
            Modelo.setNombreProb(AñadirProblema.TxtTituloSolicitud.getText());
            Modelo.setDetalleProb(AñadirProblema.TxtDetalleSolicitud.getText());
            ModeloP.setCorreoPersona(AñadirProblema.TxtCorreo.getText());
            ModeloS.setSolucion(VistaTicket.TxtSolucion.getText());

            if (Problema.InsertarProblema(Modelo, AñadirProblema)) {
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
            VistaProblema.setVisible(true);
            VistaTicket.setVisible(false);
        }

        if (e.getSource() == VistaAvances.BtnCerrar) {
            VistaAvances.setVisible(false);
        }

        if (e.getSource() == VistaProblema.JMenuVer) {
            VistaProblema.setVisible(false);
            VistaTicket.setVisible(true);

            int SelectedRow = VistaProblema.JTablaProblema.getSelectedRow();
            int NumSelectedRow = VistaProblema.JTablaProblema.getSelectedRowCount();

            if (SelectedRow >= 0 && NumSelectedRow == 1) {
                VistaTicket.TxtIDTicket.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 0).toString());
                VistaTicket.JCEstadoTicket.setSelectedItem(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 5).toString());
                VistaTicket.TxtDescripcion.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 2).toString());
                VistaTicket.TxtIDSolucion.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 0).toString());
                VistaTicket.TxtIDAvance.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 0).toString());
                VistaTicket.TxtSolucion.setText(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 6).toString());
                VistaTicket.JCAreaTicket.setSelectedItem(VistaProblema.JTablaProblema.getValueAt(SelectedRow, 4).toString());

                VistaTicket.TxtIDTicket.setEditable(false);
                VistaTicket.JCAreaTicket.setEnabled(false);
                VistaTicket.TxtDescripcion.setEditable(false);
            }
        }

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
    }

    public void Limpiar() {
        AñadirProblema.TxtTituloSolicitud.setText(null);
        AñadirProblema.TxtDetalleSolicitud.setText(null);
        AñadirProblema.TxtCorreo.setText(null);
        AñadirProblema.JCTipoSolicitud.setSelectedIndex(0);
        AñadirProblema.JCArea.setSelectedIndex(0);
        AñadirProblema.JCPrioridad.setSelectedIndex(0);
    }

    //Insertar correo a la BD
    public boolean InsertarCorreo() {
        try {
            Conexion con = new Conexion();
            Connection conexion;
            conexion = con.getConnection();
            int idPersona = con.AutoIncrementP();

            PreparedStatement ps = conexion.prepareStatement("INSERT INTO persona(idPersona, CorreoPersona) "
                    + "VALUES(" + idPersona + "," + "?" + ")");
            ps.setString(1, RecibirEmail.getCorreo());

            System.out.println(ps);

            System.out.println("\nVariable Correo: " + RecibirEmail.getCorreo() + "\n");

            int Resultado = ps.executeUpdate();
            return Resultado > 0;

        } catch (SQLException ex) {
            System.out.println("Error" + ex + "\n");
            return false;
        }
    }

    //Insertar correo a la BD
    public boolean InsertarContenido() {
        try {
            Conexion con = new Conexion();
            Connection conexion;
            conexion = con.getConnection();
            int idProblema = con.AutoIncrement();
            int idSolucion = con.AutoIncrementS();
            int idAvances = con.AutoIncrementA();
            int idPrioridad = AñadirProblema.JCPrioridad.getSelectedIndex();
            int idAreaProb = AñadirProblema.JCArea.getSelectedIndex();
            int idTipoProb = AñadirProblema.JCTipoSolicitud.getSelectedIndex();
            int idEstado = VistaTicket.JCEstadoTicket.getSelectedIndex();

            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Problema(idProblema, NombreProb, DetalleProb, FechaCreacion, "
                    + "RefIdPrioridad, RefAreaProb, RefTipoProb, RefEstado, RefSolucion, RefAvances) "
                    + "VALUES(" + idProblema + ",?, ?, CURRENT_TIMESTAMP," + idPrioridad + "," + idAreaProb + "," + idTipoProb + "," + idEstado + "," + idSolucion + "," + idAvances + ")");
            ps.setString(1, RecibirEmail.getSujeto());
            ps.setString(2, RecibirEmail.getContenido());

            System.out.println(ps);
            
            System.out.println("\nVariable Asunto: " + RecibirEmail.getSujeto());
            System.out.println("Variable Contenido: " + RecibirEmail.getContenido() + "\n");
            
            

            int Resultado = ps.executeUpdate();
            return Resultado > 0;

        } catch (SQLException ex) {
            System.out.println("Error" + ex + "\n");
            return false;
        }
    }
}
