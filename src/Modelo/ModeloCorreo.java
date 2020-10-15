package Modelo;

import javax.mail.BodyPart;
import javax.mail.Multipart;

public class ModeloCorreo {

    public Multipart mp;
    public BodyPart bp;
    public String Correo;
    public String Sujeto;
    public String Contenido;

    public ModeloCorreo() {
    }

    public ModeloCorreo(Multipart mp, BodyPart bp, String Correo, String Sujeto, String Contenido) {
        this.mp = mp;
        this.bp = bp;
        this.Correo = Correo;
        this.Sujeto = Sujeto;
        this.Contenido = Contenido;
    }

    public Multipart getMp() {
        return mp;
    }

    public void setMp(Multipart mp) {
        this.mp = mp;
    }

    public BodyPart getBp() {
        return bp;
    }

    public void setBp(BodyPart bp) {
        this.bp = bp;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getSujeto() {
        return Sujeto;
    }

    public void setSujeto(String Sujeto) {
        this.Sujeto = Sujeto;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String Contenido) {
        this.Contenido = Contenido;
    }   
}
