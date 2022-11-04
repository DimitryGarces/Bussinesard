/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prbpackage;

import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnContenedor = new javax.swing.JPanel();
        pnHead = new javax.swing.JPanel();
        txtEncabezado = new javax.swing.JLabel();
        pnContacto = new javax.swing.JPanel();
        txtContac = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JTextField();
        txtLogo = new javax.swing.JLabel();
        txtIngresar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");

        pnContenedor.setBackground(new java.awt.Color(255, 255, 255));
        pnContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnHead.setBackground(new java.awt.Color(102, 102, 102));

        txtEncabezado.setText("Encabezado");

        javax.swing.GroupLayout pnHeadLayout = new javax.swing.GroupLayout(pnHead);
        pnHead.setLayout(pnHeadLayout);
        pnHeadLayout.setHorizontalGroup(
            pnHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHeadLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txtEncabezado)
                .addContainerGap(319, Short.MAX_VALUE))
        );
        pnHeadLayout.setVerticalGroup(
            pnHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHeadLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(txtEncabezado)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pnContenedor.add(pnHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 50));

        pnContacto.setBackground(new java.awt.Color(231, 231, 231));

        txtContac.setText("Contacto");

        javax.swing.GroupLayout pnContactoLayout = new javax.swing.GroupLayout(pnContacto);
        pnContacto.setLayout(pnContactoLayout);
        pnContactoLayout.setHorizontalGroup(
            pnContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContactoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(txtContac)
                .addContainerGap(326, Short.MAX_VALUE))
        );
        pnContactoLayout.setVerticalGroup(
            pnContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContactoLayout.createSequentialGroup()
                .addComponent(txtContac)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        pnContenedor.add(pnContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 400, 30));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Correo: ");
        pnContenedor.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));
        pnContenedor.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 110, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Contraseña:");
        pnContenedor.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, -1, -1));

        txtContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasenaActionPerformed(evt);
            }
        });
        pnContenedor.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 110, -1));

        txtLogo.setText("Logo");
        pnContenedor.add(txtLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        txtIngresar.setText("Ingresar");
        txtIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIngresarMouseClicked(evt);
            }
        });
        pnContenedor.add(txtIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaActionPerformed

    private void txtIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIngresarMouseClicked
        String correo = txtCorreo.getText();
        String contra = txtContrasena.getText();

        if (valida(correo, contra)) {
            //crear nuevo frame
        }
    }//GEN-LAST:event_txtIngresarMouseClicked

    public boolean valida(String cor, String con) {
        //Crea conexion y guarda correos en una lista o arreglo
        ArrayList<String> correos = new ArrayList<String>();
        ArrayList<String> contra = new ArrayList<String>();
        //Ya se que actualmente estara vacia pero por algo se hara el llenado antes:b
        boolean v = false;
        int i = 0;
        if (correos != null) {
            do {
                if (correos.get(i).equals(cor)
                        && contra.get(i).equals(con)) {
                    v = true;
                }
            } while (i != correos.size() - 1);
        }
        return v;
    }

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel pnContacto;
    private javax.swing.JPanel pnContenedor;
    private javax.swing.JPanel pnHead;
    private javax.swing.JLabel txtContac;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JLabel txtEncabezado;
    private javax.swing.JLabel txtIngresar;
    private javax.swing.JLabel txtLogo;
    // End of variables declaration//GEN-END:variables
}
