package Conexion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

public class RecibirEmail {

    public Multipart mp;
    public BodyPart bp;
    public String Correo;
    public String Sujeto;
    public String Contenido;

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

    public void RecibirEmail() throws MessagingException, IOException, SQLException {

        Properties p = new Properties();
        p.setProperty("mail.store.protocol", "imaps");

        //Instanciamos la clase Session de JavaMail
        Session sesion = Session.getInstance(p);
        sesion.setDebug(false);

        //Es hora de obtener el Store y el Folder de Inbox (Carpeta de entrada y servidor de correo)
        Store store = sesion.getStore();
        store.connect("imap.gmail.com", "soacrenovado@gmail.com", "Wannabebolso2020");
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        //Obtener los mensajes
        Message[] mensajes = folder.getMessages();

        for (Message m : mensajes) {

            Address[] in = m.getFrom();

            for (Address address : in) {
                Correo = address.toString();
                System.out.println("TIENES EL SIGUIENTE MENSAJE:");
                System.out.println("DE: " + Correo + "");
            }

            mp = (Multipart) m.getContent();
            bp = mp.getBodyPart(0);

            Sujeto = m.getSubject();
            Contenido = bp.getContent().toString();

            System.out.println("FECHA DE ENVIO: " + m.getSentDate());
            System.out.println("ASUNTO: " + Sujeto);
            System.out.println("CONTENUDO: " + Contenido);
        }
    }
}
