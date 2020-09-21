package Vista;

import Conexion.Conexion;
import Modelo.ConsultasProblema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String[] Columnas = {"Tiket", "Nombre", "Detalle", "Fecha De Creacion", "Tipo", "Prioridad", "Area"};
        String[] Registros = new String[7];

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
            case 4:
                Connection conexion4 = con.getConnection();
                ps = conexion4.prepareStatement("SELECT * FROM TablaProblema WHERE TipoProb LIKE '%" + Buscar + "%' ");
                break;
            case 5:
                Connection conexion5 = con.getConnection();
                ps = conexion5.prepareStatement("SELECT * FROM TablaProblema WHERE AreaProb LIKE '%" + Buscar + "%' ");
                break;
        }
        
        try {

            rs = ps.executeQuery();

            while (rs.next()) {
                Registros[0] = rs.getString("idProblema");
                Registros[1] = rs.getString("NombreProb");
                Registros[2] = rs.getString("DetalleProb");
                Registros[3] = rs.getString("FechaCreacion");
                Registros[4] = rs.getString("TipoProb");
                Registros[5] = rs.getString("Prioridad");
                Registros[6] = rs.getString("AreaProb");

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

        jScrollPane1 = new javax.swing.JScrollPane();
        JTablaProblema = new javax.swing.JTable();
        TxtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        JCBuscar = new javax.swing.JComboBox<>();
        BtnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        jScrollPane1.setViewportView(JTablaProblema);

        TxtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtBuscarKeyReleased(evt);
            }
        });

        jLabel1.setText("Buscar");

        JCBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Seleccione>", "Tiket", "Nombre", "Detalle", "Tipo Problmea", "Area" }));

        BtnVolver.setText("Volver a la vista anterior");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6)
                        .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BtnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(JCBuscar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(219, 219, 219)))
                .addContainerGap())
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
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
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
    public javax.swing.JTable JTablaProblema;
    public javax.swing.JTextField TxtBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
