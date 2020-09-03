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

    public boolean Insertar(ModeloProblema Modelo) {
        try {
            int idProblema = con.AutoIncrement();

            ps = conexion.prepareStatement("INSERT INTO problema(idProblema, NombreProb, DetalleProb, FechaCreacion) "
                    + "VALUES(" + idProblema + ",?,?,CURRENT_TIMESTAMP)");
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

    /*    public void AgarrarId(ModeloProblema Modelo, AñadirProblema Vista) throws SQLException {
    try {
    int idPrioridad = Vista.JCPrioridad.getSelectedIndex();
    
    ps = conexion.prepareStatement("SELECT Prioridad FROM Prioridad WHERE idPrioridad = " + idPrioridad);
    rs = ps.executeQuery();
    
    System.out.println(ps);
    
    } catch (SQLException ex) {
    System.out.println("Error" + ex);
    }
    }*/
}
