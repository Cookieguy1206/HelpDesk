package Controlador;

import Conexion.Conexion;
import Modelo.ModeloCorreo;
import Vista.AñadirProblema;
import Vista.VistaTicket;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

public class ControladorRecibirEmail {

    public String Correo;
    public String Sujeto;
    public String Contenido;

    public ArrayList<ModeloCorreo> RecibirEmail() throws MessagingException, IOException, SQLException {

        ArrayList listaCorreos = new ArrayList();
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
            ModeloCorreo ModeloCorreo = new ModeloCorreo();
            Address[] in = m.getFrom();

            for (Address address : in) {
                ModeloCorreo.setCorreo(address.toString());
                Correo = ModeloCorreo.getCorreo();
                System.out.println("TIENES EL SIGUIENTE MENSAJE:");
                System.out.println("DE: " + Correo + "");
            }
            Multipart mp = (Multipart) m.getContent();
            ModeloCorreo.setMp(mp);
            BodyPart bp = mp.getBodyPart(0);
            ModeloCorreo.setBp(bp);
            ModeloCorreo.setSujeto(m.getSubject());
            ModeloCorreo.setContenido(bp.getContent().toString());
            listaCorreos.add(ModeloCorreo);

            Sujeto = ModeloCorreo.getSujeto();
            Contenido = ModeloCorreo.getContenido();

            System.out.println("FECHA DE ENVIO: " + m.getSentDate());
            System.out.println("ASUNTO: " + Sujeto);
            System.out.println("CONTENUDO: " + Contenido);

            InsertarCorreo();
            InsertarContenido();
        }
        return listaCorreos;
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
            ps.setString(1, Sujeto);

            System.out.println(ps);

            System.out.println("\nVariable Correo: " + Sujeto + "\n");

            int Resultado = ps.executeUpdate();
            return Resultado > 0;

        } catch (SQLException ex) {
            System.out.println("Error" + ex + "\n");
            return false;
        }
    }

    //Insertar contenido a la BD
    public boolean InsertarContenido() {
        try {
            AñadirProblema AñadirProblema = new AñadirProblema();
            VistaTicket VistaTicket = new VistaTicket();
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
            ps.setString(1, Sujeto);
            ps.setString(2, Contenido);

            System.out.println(ps);

            System.out.println("\nVariable Asunto: " + Sujeto);
            System.out.println("Variable Contenido: " + Contenido + "\n");

            int Resultado = ps.executeUpdate();
            return Resultado > 0;

        } catch (SQLException ex) {
            System.out.println("Error" + ex + "\n");
            return false;
        }
    }
}
