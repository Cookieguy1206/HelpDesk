package Vista;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class VerProblema extends javax.swing.JFrame {

    public VerProblema() {
        initComponents();
    }

    ResultSet rs;
    PreparedStatement ps;
    Conexion con = new Conexion();

    //Al escribir en el JTextField se buscará lo deseado
    public void Buscar(String Buscar) throws SQLException {
        String[] Columnas = {"Tiket", "Correo", "Nombre", "Detalle", "Fecha De Creacion", "Estado", "Solucion", "Area"};
        String[] Registros = new String[8];

        DefaultTableModel ModeloTabla = new DefaultTableModel(null, Columnas);

        switch (JCBuscar.getSelectedIndex()) {
            case 1:
                Connection conexion = con.getConnection();
                ps = conexion.prepareStatement("SELECT * FROM TablaProblema WHERE idProblema LIKE '%" + Buscar + "%' ");
                break;
            case 2:
                Connection conexion2 = con.getConnection();
                ps = conexion2.prepareStatement("SELECT * FROM TablaProblema WHERE NombreProb LIKE '%" + Buscar + "%' ");
                break;
            case 3:
                Connection conexion3 = con.getConnection();
                ps = conexion3.prepareStatement("SELECT * FROM TablaProblema WHERE DetalleProb LIKE '%" + Buscar + "%' ");
                break;
            /*case 4:
                Connection conexion4 = con.getConnection();
                ps = conexion4.prepareStatement("SELECT * FROM TablaProblema WHERE TipoProb LIKE '%" + Buscar + "%' ");
                break;*/
            case 5:
                Connection conexion5 = con.getConnection();
                ps = conexion5.prepareStatement("SELECT * FROM TablaProblema WHERE AreaProb LIKE '%" + Buscar + "%' ");
                break;
        }
        
        try {

            rs = ps.executeQuery();

            while (rs.next()) {
                Registros[0] = rs.getString("idProblema");
                Registros[1] = rs.getString("CorreoPersona");
                Registros[2] = rs.getString("NombreProb");
                Registros[3] = rs.getString("DetalleProb");
                Registros[4] = rs.getString("FechaCreacion");
                Registros[5] = rs.getString("Estado");
                Registros[6] = rs.getString("Solucion");
                Registros[7] = rs.getString("Areaprob");

                ModeloTabla.addRow(Registros);
            }

            JTablaProblema.setModel(ModeloTabla);

        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPMenuVer = new javax.swing.JPopupMenu();
        JMenuVer = new javax.swing.JMenuItem();
        JMenuVerAv = new javax.swing.JMenuItem();
        TxtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        JCBuscar = new javax.swing.JComboBox<>();
        BtnVolver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTablaProblema = new javax.swing.JTable();

        JMenuVer.setText("Ver");
        JPMenuVer.add(JMenuVer);

        JMenuVerAv.setText("Ver Avances");
        JPMenuVer.add(JMenuVerAv);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TxtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtBuscarKeyReleased(evt);
            }
        });

        jLabel1.setText("Buscar");

        JCBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Seleccione>", "Tiket", "Nombre", "Detalle", "Tipo Problmea", "Area" }));

        BtnVolver.setText("Volver a la vista anterior");

        JTablaProblema.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        JTablaProblema.setComponentPopupMenu(JPMenuVer);
        jScrollPane1.setViewportView(JTablaProblema);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(JCBuscar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(225, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JCBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnVolver)
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtBuscarKeyReleased
        try {
            Buscar(TxtBuscar.getText());
        } catch (SQLException ex) {
            Logger.getLogger(VerProblema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TxtBuscarKeyReleased
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerProblema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new VerProblema().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BtnVolver;
    public javax.swing.JComboBox<String> JCBuscar;
    public javax.swing.JMenuItem JMenuVer;
    public javax.swing.JMenuItem JMenuVerAv;
    public javax.swing.JPopupMenu JPMenuVer;
    public javax.swing.JTable JTablaProblema;
    public javax.swing.JTextField TxtBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
