package Modelo;

import Conexion.Conexion;
import Vista.AñadirProblema;
import Vista.VerProblema;
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
            int idPrioridad = AñadirProblema.JCPrioridad.getSelectedIndex();
            int idAreaProb = AñadirProblema.JCArea.getSelectedIndex();
            int idTipoProb = AñadirProblema.JCTipoSolicitud.getSelectedIndex();
            int idEstado = VistaTicket.JCEstadoTicket.getSelectedIndex();

            ps = conexion.prepareStatement("INSERT INTO problema(idProblema, NombreProb, DetalleProb, FechaCreacion, "
                    + "RefIdPrioridad, RefAreaProb, RefTipoProb, RefEstado, RefSolucion) "
                    + "VALUES(" + idProblema + ",?,?,CURRENT_TIMESTAMP," + idPrioridad + "," + idAreaProb + "," + idTipoProb + "," + idEstado + "," + idSolucion + ")");
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

    //Funcion para insertar el porblema en la BD
    public boolean PlantillaSolucion(ModeloSolucion ModeloS) {
        try {
            int idSolucion = con.AutoIncrementS();

            ps = conexion.prepareStatement("INSERT INTO Soluciones(idSolucion, Solucion) "
                    + "VALUES(" + idSolucion + ",?)");
            ps.setString(1, ModeloS.getSolucion());

            System.out.println(ps);

            int Resultado = ps.executeUpdate();
            return Resultado > 0;

        } catch (SQLException ex) {
            System.out.println("Error" + ex);
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
            ModeloTabla.addColumn("Nombre");
            ModeloTabla.addColumn("Detalle");
            ModeloTabla.addColumn("Fecha De Creación");
            ModeloTabla.addColumn("Tipo");
            ModeloTabla.addColumn("Prioridad");
            ModeloTabla.addColumn("Area");
            ModeloTabla.addColumn("Estado");
            ModeloTabla.addColumn("Correo");
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

            ps = conexion.prepareStatement("UPDATE Soluciones SET Solucion = ? WHERE idSolucion = "+ VistaTicket.TxtIDSolucion.getText() +"");
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
}
