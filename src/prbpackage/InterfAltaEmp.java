package prbpackage;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mariadb.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author Diego
 */
public class InterfAltaEmp extends javax.swing.JFrame {

    private static Connection con = null;
    String sql = "Select Id_Grupo,NombreGrupo from bussinesscard.grupo; ";
    private Arreglo arrG = new Arreglo();
    Statement st;
//Simple borders

    /**
     * Creates new form InterfAltaEmp
     *
     * @param con
     */
    public InterfAltaEmp(Connection con) {
        initComponents();
        this.con = con;
        cbGrupo = new prbpackage.Combobox();
//        cbGrupo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"", "Alfa", "Beta", "Foxtron"}));
        valida();
        cbRol = new prbpackage.Combobox();
        cbRol.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"", "Administrador", "Moderador", "Empleado"}));

        this.pnContainer.add(cbGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, -1, -1));
        this.pnContainer.add(cbRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, -1, -1));
    }

    public void valida() {
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrG.inserta("");
            while (rs.next()) {
                arrG.inserta(rs.getString(2));
            }
            cbGrupo.setModel(new javax.swing.DefaultComboBoxModel() {
                String[] strings = arrG.getArr();

                @Override
                public int getSize() {
                    return strings.length;
                }

                @Override
                public String getElementAt(int i) {
                    return strings[i];
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        arrG.vaciarArr();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnContainer = new javax.swing.JPanel();
        pnContacto = new javax.swing.JPanel();
        pnHead = new javax.swing.JPanel();
        txtEncabezado = new javax.swing.JLabel();
        lbCancelar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtContrasenia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        pnValidar = new javax.swing.JPanel();
        lbValidar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });

        pnContainer.setBackground(new java.awt.Color(255, 255, 255));
        pnContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout pnContactoLayout = new javax.swing.GroupLayout(pnContacto);
        pnContacto.setLayout(pnContactoLayout);
        pnContactoLayout.setHorizontalGroup(
            pnContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        pnContactoLayout.setVerticalGroup(
            pnContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        pnContainer.add(pnContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 520, 50));

        pnHead.setBackground(new java.awt.Color(255, 153, 0));

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

        pnContainer.add(pnHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Apellidos");
        pnContainer.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtApellidosKeyPressed(evt);
            }
        });
        pnContainer.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 150, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Nombre");
        pnContainer.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Grupo de trabajo");
        pnContainer.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Telefono");
        pnContainer.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Correo");
        pnContainer.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Contraseña");
        pnContainer.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Rol de Empleado");
        pnContainer.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, -1, -1));

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });
        pnContainer.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 140, -1));

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyPressed(evt);
            }
        });
        pnContainer.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 140, -1));

        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCorreoKeyPressed(evt);
            }
        });
        pnContainer.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 90, 20));

        txtContrasenia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraseniaKeyPressed(evt);
            }
        });
        pnContainer.add(txtContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 140, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("@bn.com");
        pnContainer.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, -1, -1));

        pnValidar.setBackground(new java.awt.Color(204, 204, 204));

        lbValidar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbValidar.setText("Validar");
        lbValidar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbValidarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnValidarLayout = new javax.swing.GroupLayout(pnValidar);
        pnValidar.setLayout(pnValidarLayout);
        pnValidarLayout.setHorizontalGroup(
            pnValidarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbValidar, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        pnValidarLayout.setVerticalGroup(
            pnValidarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbValidar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pnContainer.add(pnValidar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 110, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void lbValidarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbValidarMouseClicked

        String correo = txtCorreo.getText();
        String contra = txtContrasenia.getText();
        String apellidos = txtApellidos.getText();
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        int grupo = cbGrupo.getSelectedIndex();
        int rol = cbRol.getSelectedIndex();
        try {

            PreparedStatement pps = con.prepareStatement("Insert into `bussinesscard`.`empleado` ( `Role`,`Apellidos`,`Nombre`,`Telefono`,`Usuario`,`Contraseña`,`Id_Grupo`)"
                    + "Values (?,?,?,?,?,?,?)");
            pps.setString(1, Integer.toString(rol));
            pps.setString(2, apellidos);
            pps.setString(3, nombre);
            pps.setString(4, telefono);
            pps.setString(5, correo);
            pps.setString(6, contra);
            pps.setString(7, Integer.toString(grupo));
            pps.executeUpdate();
            txtCorreo.setText("");
            txtContrasenia.setText("");
            txtApellidos.setText("");
            txtNombre.setText("");
            txtTelefono.setText("");
            cbGrupo.setSelectedIndex(0);
            cbRol.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, "Empleado registrado con exito.");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Compruebe sus entradas y llene todos los campos.");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_lbValidarMouseClicked

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated

        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowDeactivated

    private void lbCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCancelarMouseClicked
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_lbCancelarMouseClicked

    private void txtApellidosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            txtNombre.requestFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosKeyPressed

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed

        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            txtTelefono.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            txtCorreo.requestFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyPressed

    private void txtCorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            txtContrasenia.requestFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoKeyPressed

    private void txtContraseniaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseniaKeyPressed

        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            lbValidarMouseClicked(null);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraseniaKeyPressed

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
            java.util.logging.Logger.getLogger(InterfAltaEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfAltaEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfAltaEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfAltaEmp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfAltaEmp(con).setVisible(true);
            }
        });
    }
    private prbpackage.Combobox cbGrupo;
    private prbpackage.Combobox cbRol;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lbCancelar;
    private javax.swing.JLabel lbValidar;
    private javax.swing.JPanel pnContacto;
    private javax.swing.JPanel pnContainer;
    private javax.swing.JPanel pnHead;
    private javax.swing.JPanel pnValidar;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtContrasenia;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JLabel txtEncabezado;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
