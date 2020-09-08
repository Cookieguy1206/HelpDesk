package Modelo;

import Conexion.Conexion;
import Vista.AñadirProblema;
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
    Conexion con = new Conexion();
    Connection conexion;

    public ConsultasProblema() throws SQLException {
        this.conexion = con.getConnection();
    }

    //Funcion para insertar el porblema en la BD
    public boolean Insertar(ModeloProblema Modelo, AñadirProblema AñadirProblema) {
        try {
            int idProblema = con.AutoIncrement();
            int idPrioridad = AñadirProblema.JCPrioridad.getSelectedIndex();
            int idAreaProb = AñadirProblema.JCArea.getSelectedIndex();
            int idTipoProb = AñadirProblema.JCTipoSolicitud.getSelectedIndex();

            ps = conexion.prepareStatement("INSERT INTO problema(idProblema, NombreProb, DetalleProb, FechaCreacion, "
                    + "RefIdPrioridad, RefAreaProb, RefTipoProb) "
                    + "VALUES(" + idProblema + ",?,?,CURRENT_TIMESTAMP," + idPrioridad + "," + idAreaProb + "," + idTipoProb + ")");
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

    public void Mostrar(JTable TablaProblema) {
        DefaultTableModel ModeloTabla = new DefaultTableModel();
        TablaProblema.setModel(ModeloTabla);

        try {
            ps = conexion.prepareStatement("SELECT * FROM TablaProblema");
            rs = ps.executeQuery();

            ModeloTabla.addColumn("Nombre");
            ModeloTabla.addColumn("Detalle");
            ModeloTabla.addColumn("Fecha De Creación");
            ModeloTabla.addColumn("Tipo");
            ModeloTabla.addColumn("Prioridad");
            ModeloTabla.addColumn("Area");

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
        /* finally {
        try {
        conexion.close();
        } catch (SQLException ex) {
        System.out.println("Error" + ex);
        }
        }*/
    }
}
