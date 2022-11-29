
package prbpackage;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author Diego
 */
public class InterfAltaGrupos extends javax.swing.JFrame {

    private static Connection con;
    String sql = "Select Id_Grupo,NombreGrupo from bussinesscard.grupo; ";
    private Arreglo arrG = new Arreglo();
    Statement st;
    private ImageIcon imagen;
    private Icon icono;
    private static String nombreU = "";

    public InterfAltaGrupos(Connection con, String nombre) {
        initComponents();
        InterfAltaGrupos.con = con;
        InterfAltaGrupos.nombreU = nombre;
        valida();
        pintarImagen(lbConfirmar, "/imgspackage/Confirmar.png");
    }

    public void valida() {
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                arrG.inserta(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pintarImagen(JLabel lbl, String ruta) {
        this.imagen = new ImageIcon(getClass().getResource(ruta));
        this.icono = new ImageIcon(
                this.imagen.getImage().getScaledInstance(
                        lbl.getWidth(),
                        lbl.getHeight(),
                        Image.SCALE_DEFAULT
                )
        );
        lbl.setIcon(this.icono);
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnHead = new javax.swing.JPanel();
        txtEncabezado = new javax.swing.JLabel();
        lbCancelar = new javax.swing.JLabel();
        lbGrupo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JTextField();
        lbConfirmar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear Grupos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtEncabezado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtEncabezado.setText("BUSINESSCARD");

        lbCancelar.setText("Cancelar");
        lbCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCancelarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnHeadLayout = new javax.swing.GroupLayout(pnHead);
        pnHead.setLayout(pnHeadLayout);
        pnHeadLayout.setHorizontalGroup(
            pnHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHeadLayout.createSequentialGroup()
                .addComponent(txtEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                .addComponent(lbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnHeadLayout.setVerticalGroup(
            pnHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHeadLayout.createSequentialGroup()
                .addComponent(txtEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnHeadLayout.createSequentialGroup()
                .addComponent(lbCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbGrupo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbGrupo.setText("Nombre del Grupo");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Crear un Area de Trabajo");

        txtGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGrupoKeyPressed(evt);
            }
        });

        lbConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbConfirmarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(lbGrupo)
                        .addGap(42, 42, 42)
                        .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(lbConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbGrupo)
                    .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCancelarMouseClicked
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_lbCancelarMouseClicked

    private void lbConfirmarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbConfirmarMouseClicked
        String id = "";
        String nG = txtGrupo.getText();
        if (!nG.equals("")) {
            if (ejecucion(nG)) {
                try {
                    //Obteniendo el Id del usuario con el que estamos conectados
                    String obtenId = "Select Id_Empleado from bussinesscard.empleado Where Nombre LIKE \"" + nombreU + "\";";
                    st = con.createStatement();
                    ResultSet rs = st.executeQuery(obtenId);
                    rs.next();
                    id = rs.getString(1);

                    //Creando un grupo con el usuario que estamos conectados
                    PreparedStatement pps = con.prepareStatement("Insert into `bussinesscard`.`grupo` (`Id_Owner`, `NombreGrupo`)"
                            + "Values (?,?);");
                    pps.setString(1, id);
                    pps.setString(2, nG);
                    pps.executeUpdate();

                    //Obteniendo el Id del grupo que acabamos de crear
                    obtenId = "Select Id_Grupo from bussinesscard.grupo Where NombreGrupo LIKE \"" + nG + "\";";
                    st = con.createStatement();
                    rs = st.executeQuery(obtenId);
                    rs.next();
                    id = rs.getString(1);
                    //Creando el usuario al que todos enviaran mensaje
                    pps = con.prepareStatement("Insert into `bussinesscard`.`empleado` (`Role`,`Apellidos`,`Nombre`,`Usuario`,`Contraseña`,`Id_Grupo`)"
                            + "Values (?,?,?,?,?,?);");
                    pps.setString(1, "5");
                    pps.setString(2, "Area de Trabajo");
                    pps.setString(3, ("Grupo " + nG));
                    pps.setString(4, (nG + "@bn.com"));
                    pps.setString(5, "root");
                    pps.setString(6, id);
                    pps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Grupo creado con exito.");

                    txtGrupo.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(InterfAltaEmp.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Ups! Algo a salido mal.", "Severe", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nombre en uso", "Fatal", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El grupo no puede estar vacio.", "Fatal", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_lbConfirmarMouseClicked

    public boolean ejecucion(String nG) {
        int i = 0;
        while (i < arrG.tamanio()) {
            if (arrG.verPos(i).equals(nG)) {
                return false;
            }
            i++;
        }
        return true;
    }
    private void txtGrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            lbConfirmarMouseClicked(null);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrupoKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfAltaGrupos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfAltaGrupos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfAltaGrupos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfAltaGrupos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfAltaGrupos(con, nombreU).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbCancelar;
    private javax.swing.JLabel lbConfirmar;
    private javax.swing.JLabel lbGrupo;
    private javax.swing.JPanel pnHead;
    private javax.swing.JLabel txtEncabezado;
    private javax.swing.JTextField txtGrupo;
    // End of variables declaration//GEN-END:variables
}
