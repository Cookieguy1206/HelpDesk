package Modelo;

import java.sql.Date;

public class ModeloProblema {

    private int idProblema;
    private String NombreProb;
    private String DetalleProb;
    private Date FechaCreacion;
    private int RefIdPrioridad;
    private int RefAreaProb;
    private int RefTipoProb;
    private int RefEstado;
    private int RefPersona;
    private int RefSolucion;

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

    public int getRefIdPrioridad() {
        return RefIdPrioridad;
    }

    public void setRefIdPrioridad(int RefIdPrioridad) {
        this.RefIdPrioridad = RefIdPrioridad;
    }

    public int getRefAreaProb() {
        return RefAreaProb;
    }

    public void setRefAreaProb(int RefAreaProb) {
        this.RefAreaProb = RefAreaProb;
    }

    public int getRefTipoProb() {
        return RefTipoProb;
    }

    public void setRefTipoProb(int RefTipoProb) {
        this.RefTipoProb = RefTipoProb;
    }

    public int getRefEstado() {
        return RefEstado;
    }

    public void setRefEstado(int RefEstado) {
        this.RefEstado = RefEstado;
    }

    public int getRefPersona() {
        return RefPersona;
    }

    public void setRefPersona(int RefPersona) {
        this.RefPersona = RefPersona;
    }

    public int getRefSolucion() {
        return RefSolucion;
    }

    public void setRefSolucion(int RefSolucion) {
        this.RefSolucion = RefSolucion;
    }
    
}
