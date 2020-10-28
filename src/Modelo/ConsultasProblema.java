package Modelo;

import Conexion.Conexion;
import Controlador.ControladorRecibirEmail;
import Vista.AñadirProblema;
import Vista.VerProblema;
import Vista.VistaAvances;
import Vista.VistaTicket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConsultasProblema {

    PreparedStatement ps;
    ResultSet rs;
    AñadirProblema AñadirProblema = new AñadirProblema();
    VerProblema VistaProblema = new VerProblema();
    VistaTicket VistaTicket = new VistaTicket();
    VistaAvances VistaAvances = new VistaAvances();
    ControladorRecibirEmail RecibirEmail = new ControladorRecibirEmail();
    Conexion con = new Conexion();
    Connection conexion;

    public ConsultasProblema() throws SQLException {
        this.conexion = con.getConnection();
    }

    //Funcion para insertar el porblema en la BD
    public boolean InsertarProblema(ModeloProblema Modelo, AñadirProblema AñadirProblema) {
        try {
            int idProblema = con.AutoIncrement();
            int idSolucion = con.AutoIncrementS();
            int idAvances = con.AutoIncrementA();
            int idPrioridad = AñadirProblema.JCPrioridad.getSelectedIndex();
            int idAreaProb = AñadirProblema.JCArea.getSelectedIndex();
            int idTipoProb = AñadirProblema.JCTipoSolicitud.getSelectedIndex();
            int idEstado = VistaTicket.JCEstadoTicket.getSelectedIndex();

            ps = conexion.prepareStatement("INSERT INTO problema(idProblema, NombreProb, DetalleProb, FechaCreacion, "
                    + "RefIdPrioridad, RefAreaProb, RefTipoProb, RefEstado, RefSolucion, RefAvances) "
                    + "VALUES(" + idProblema + ",?,?,CURRENT_TIMESTAMP," + idPrioridad + "," + idAreaProb + "," + idTipoProb + "," + idEstado + "," + idSolucion + "," + idAvances + ")");
            ps.setString(1, Modelo.getNombreProb());
            ps.setString(2, Modelo.getDetalleProb());

            System.out.println(ps);

            int Resultado = ps.executeUpdate();
            return Resultado > 0;

        } catch (SQLException ex) {
            System.out.println("Error" + ex);
            return false;
        }
    }

    //Iniciar trigger
    public boolean IniciarTrigger(ModeloProblema Modelo) {
        try {
            ps = conexion.prepareStatement("CREATE TRIGGER Audit_Prob_Sol AFTER INSERT ON Problema "
                    + "FOR EACH ROW "
                    + "INSERT INTO Soluciones(idSolucion, Solucion) VALUES (NEW.RefSolucion = ?, '')");
            ps.setInt(1, Modelo.getRefSolucion());

            System.out.println(ps);

            int Resultado = ps.executeUpdate();
            return Resultado > 0;

        } catch (SQLException ex) {
            System.out.println(ex + "\n");
            return false;
        }
    }

    //Funcion para mostrar datos en el JTable
    public void Mostrar(JTable TablaProblema) {
        DefaultTableModel ModeloTabla = new DefaultTableModel();
        TablaProblema.setModel(ModeloTabla);

        try {
            ps = conexion.prepareStatement("SELECT * FROM TablaProblema");
            rs = ps.executeQuery();

            ModeloTabla.addColumn("Tiket");
            ModeloTabla.addColumn("Correo");
            ModeloTabla.addColumn("Nombre");
            ModeloTabla.addColumn("Detalle");
            ModeloTabla.addColumn("Fecha De Creación");
            ModeloTabla.addColumn("Area");
            ModeloTabla.addColumn("Estado");
            ModeloTabla.addColumn("Solucion");

            ResultSetMetaData rsMD = rs.getMetaData();
            int CantidadColumnas = rsMD.getColumnCount();

            while (rs.next()) {
                Object fila[] = new Object[CantidadColumnas];

                for (int i = 0; i < CantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }

                ModeloTabla.addRow(fila);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    //Funcion para mostrar datos en el JTable
    public void MostrarAvances(JTable TablaAvances, VistaAvances VistaAvances) {
        DefaultTableModel ModeloTabla = new DefaultTableModel();
        TablaAvances.setModel(ModeloTabla);

        try {
            ps = conexion.prepareStatement("SELECT * FROM TablaAvances WHERE idProblema = " + VistaAvances.TxtIDAvance.getText() + " ORDER BY idAvances ASC");
            rs = ps.executeQuery();

            ModeloTabla.addColumn("idAvance");
            ModeloTabla.addColumn("idProblema");
            ModeloTabla.addColumn("Avance");
            ModeloTabla.addColumn("Fecha De Avance");
            ModeloTabla.addColumn("Estado");

            ResultSetMetaData rsMD = rs.getMetaData();
            int CantidadColumnas = rsMD.getColumnCount();

            while (rs.next()) {
                Object fila[] = new Object[CantidadColumnas];

                for (int i = 0; i < CantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }

                ModeloTabla.addRow(fila);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    //Modificar
    public boolean CambiarEstado(ModeloProblema Modelo, VistaTicket VistaTicket) {
        try {
            ps = conexion.prepareStatement("UPDATE Problema SET RefEstado = ?  WHERE idProblema = " + VistaTicket.TxtIDTicket.getText() + "");

            ps.setInt(1, Modelo.getRefEstado());

            int Resultado = ps.executeUpdate();
            System.out.println("Modificado Exitoso:");
            System.out.println(ps);

            if (Resultado > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex);
            return false;
        }
    }

    //Insertar la solucion
    public boolean InsertarSolucion(ModeloSolucion ModeloS, VistaTicket VistaTicket, ModeloProblema Modelo) {
        try {
            ps = conexion.prepareStatement("UPDATE Soluciones SET Solucion = ? WHERE idSolucion = " + VistaTicket.TxtIDSolucion.getText() + "");
            ps.setString(1, ModeloS.getSolucion());

            System.out.println("\nModificado Exitoso:");
            System.out.println(ps);

            int Resultado = ps.executeUpdate();
            return Resultado > 0;

        } catch (SQLException ex) {
            System.out.println("Error" + ex);
            return false;
        }
    }

    //Insertar Avance
    public boolean InsertarAvance(ModeloAvances ModeloA, VistaTicket VistaTicket, ModeloProblema Modelo) {
        try {
            ps = conexion.prepareStatement("UPDATE Avances SET Avance = ? WHERE idAvances = " + VistaTicket.TxtIDAvance.getText() + "");
            ps.setString(1, VistaTicket.TxtAvance.getText());

            System.out.println("\nModificado Exitoso:");
            System.out.println(ps);

            int Resultado = ps.executeUpdate();
            return Resultado > 0;

        } catch (SQLException ex) {
            System.out.println("Error" + ex);
            return false;
        }
    }

    //Listar Avances
    public boolean ListarAvances(ModeloAvances ModeloA, VistaTicket VistaTicket, ModeloProblema Modelo) {
        try {
            int idAvances = con.AutoIncrementA() + 3;
            int idAvancesS = idAvances;
            int idEstado = VistaTicket.JCEstadoTicket.getSelectedIndex();
            ps = conexion.prepareStatement("INSERT INTO Avances (idAvances, Avance, idAvanceProb, FechaAvance, RefEstado) "
                    + "\nSELECT * FROM (SELECT " + idAvances + "," + "?" + "," + "?" + "," + "CURRENT_TIMESTAMP" + "," + idEstado + ") AS TMP "
                    + "\nWHERE NOT EXISTS(SELECT idAvances FROM Avances WHERE idAvances = " + idAvancesS + ") "
                    + "\nLIMIT 1");
            ps.setString(1, VistaTicket.TxtAvance.getText());
            ps.setString(2, VistaTicket.TxtIDAvance.getText());

            System.out.println("\nModificado Exitoso:");
            System.out.println(ps);

            int Resultado = ps.executeUpdate();
            return Resultado > 0;

        } catch (SQLException ex) {
            System.out.println("Error" + ex);
            return false;
        }
    }

    //Busca si hay algún registro repetido en la BD
    public int BuscaRepetido(String Correo) throws SQLException {
        try {
            ps = conexion.prepareStatement("SELECT count(idPersona) FROM Persona WHERE idPersona = ?");
            ps.setString(1, Correo);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

            return 1;

        } catch (SQLException ex) {
            System.out.println("Error " + ex);
            return 1;
        }
    }

    //Apariencia para la interfaz gráfica
    public void Estetica() {
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
    }
}
