package Modelo;

import java.sql.Date;

public class ModeloProblema {
    
    private int idProblema;
    private String NombreProb;
    private String DetalleProb;
    private Date FechaCreacion;

    public int getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(int idProblema) {
        this.idProblema = idProblema;
    }

    public String getNombreProb() {
        return NombreProb;
    }

    public void setNombreProb(String NombreProb) {
        this.NombreProb = NombreProb;
    }

    public String getDetalleProb() {
        return DetalleProb;
    }

    public void setDetalleProb(String DetalleProb) {
        this.DetalleProb = DetalleProb;
    }

    public Date getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(Date FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }
    
}
