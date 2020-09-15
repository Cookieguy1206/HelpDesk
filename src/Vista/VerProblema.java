package Vista;

import Conexion.Conexion;
import Modelo.ConsultasProblema;
import java.sql.Connection;
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
    Conexion con = new Conexion();

    //Al escribir en el JTextField se buscará lo deseado
    public void Buscar(String Buscar) throws SQLException {
        String[] Columnas = {"Tiket", "Nombre", "Detalle", "Fecha De Creacion", "Tipo", "Prioridad", "Area"};
        String[] Registros = new String[7];

        DefaultTableModel ModeloTabla = new DefaultTableModel(null, Columnas);

        String SQL = "SELECT * FROM TablaProblema WHERE idProblema LIKE '%" + Buscar + "%' "
                + "OR  NombreProb LIKE '%" + Buscar + "%' "
                + "OR  DetalleProb LIKE '%" + Buscar + "%' "
                + "OR  TipoProb LIKE '%" + Buscar + "%' "
                + "OR  AreaProb LIKE '%" + Buscar + "%' ";

        try {
            Connection conexion  = con.getConnection();
            Statement st = conexion.createStatement();
            rs = st.executeQuery(SQL);

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

        JCBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Seleccione>", "Tiket", "Nombre", "Detalle", "Area", "Tipo" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JCBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    public javax.swing.JComboBox<String> JCBuscar;
    public javax.swing.JTable JTablaProblema;
    public javax.swing.JTextField TxtBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
