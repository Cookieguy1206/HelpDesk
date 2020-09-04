package Modelo;

import Conexion.Conexion;
import Vista.AñadirProblema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasProblema {

    PreparedStatement ps;
    ResultSet rs;
    AñadirProblema VistaProblema = new AñadirProblema();
    Conexion con = new Conexion();
    Connection conexion;

    public ConsultasProblema() throws SQLException {
        this.conexion = con.getConnection();
    }

    //Funcion para insertar el porblema en la BD
    public boolean Insertar(ModeloProblema Modelo, AñadirProblema VistaProblema) {
        try {
            int idProblema = con.AutoIncrement();
            int idPrioridad = VistaProblema.JCPrioridad.getSelectedIndex();
            int idAreaProb = VistaProblema.JCArea.getSelectedIndex();
            int idTipoProb = VistaProblema.JCTipoSolicitud.getSelectedIndex();

            ps = conexion.prepareStatement("INSERT INTO problema(idProblema, NombreProb, DetalleProb, FechaCreacion, "
                    + "RefIdPrioridad, RefAreaProb, RefTipoProb) "
                    + "VALUES(" + idProblema + ",?,?,CURRENT_TIMESTAMP," + idPrioridad + "," +idAreaProb + "," +idTipoProb + ")");
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
}
