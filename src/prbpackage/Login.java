/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prbpackage;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

/**
 *
 * 
 */
public class Login extends javax.swing.JFrame {

    private ImageIcon imagen;
    private Icon icono;
    
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        pintarImagen(txtLogo, "src/imgspackage/BusinessCard.png");
        pintarImagen(lblIngresar, "src/imgspackage/usuario.png");
        pintarImagen(lblRegistrar, "src/imgspackage/registro.png");
    }

    private void pintarImagen(JLabel lbl, String ruta){
        this.imagen = new ImageIcon(ruta);
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
        txtCerrar = new javax.swing.JLabel();
        pnContacto = new javax.swing.JPanel();
        txtContac = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblContraseña = new javax.swing.JLabel();
        txtIngresar = new javax.swing.JLabel();
        lblRegistrar = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        txtLogo = new javax.swing.JLabel();
        lblIngresar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setUndecorated(true);
        setResizable(false);

        pnContenedor.setBackground(new java.awt.Color(255, 255, 255));
        pnContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnHead.setBackground(new java.awt.Color(102, 102, 102));

        txtEncabezado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtEncabezado.setText("BUSINESSCARD");

        txtCerrar.setText("Cerrar");
        txtCerrar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        txtCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtCerrar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        txtCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCerrarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnHeadLayout = new javax.swing.GroupLayout(pnHead);
        pnHead.setLayout(pnHeadLayout);
        pnHeadLayout.setHorizontalGroup(
            pnHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHeadLayout.createSequentialGroup()
                .addComponent(txtEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                .addComponent(txtCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnHeadLayout.setVerticalGroup(
            pnHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtCerrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        txtCerrar.getAccessibleContext().setAccessibleName("txtcerrar");

        pnContenedor.add(pnHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 50));

        pnContacto.setBackground(new java.awt.Color(231, 231, 231));

        txtContac.setText("Contacto");

        javax.swing.GroupLayout pnContactoLayout = new javax.swing.GroupLayout(pnContacto);
        pnContacto.setLayout(pnContactoLayout);
        pnContactoLayout.setHorizontalGroup(
            pnContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContactoLayout.createSequentialGroup()
                .addComponent(txtContac, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 323, Short.MAX_VALUE))
        );
        pnContactoLayout.setVerticalGroup(
            pnContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtContac, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnContenedor.add(pnContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 470, 50));

        lblUsuario.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblUsuario.setText("Usuario:");
        pnContenedor.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, -1));
        pnContenedor.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 190, 30));

        lblContraseña.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblContraseña.setText("Contraseña:");
        pnContenedor.add(lblContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        txtIngresar.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        txtIngresar.setText("Ingresar");
        txtIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIngresarMouseClicked(evt);
            }
        });
        pnContenedor.add(txtIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, -1, -1));

        lblRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegistrarMouseClicked(evt);
            }
        });
        pnContenedor.add(lblRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 50, 50));

        txtContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasenaActionPerformed(evt);
            }
        });
        pnContenedor.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 190, 30));

        jLabel1.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel1.setText("Registrar");
        pnContenedor.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 60, -1));
        pnContenedor.add(txtLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, -20, 360, 290));

        lblIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIngresarMouseClicked(evt);
            }
        });
        pnContenedor.add(lblIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 60, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIngresarMouseClicked
        String correo = txtUsuario.getText();
        String contra = txtContrasena.getText();

        if (valida(correo, contra)) {
            //crear nuevo frame
        }
    }//GEN-LAST:event_txtIngresarMouseClicked

    private void txtContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaActionPerformed

    private void txtCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCerrarMouseClicked
        setVisible(false);
        dispose();
    }//GEN-LAST:event_txtCerrarMouseClicked

    private void lblRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegistrarMouseClicked
        JFrame registro =new VtnRegistro();
        registro.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_lblRegistrarMouseClicked

    private void lblIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIngresarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblIngresarMouseClicked

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
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblIngresar;
    private javax.swing.JLabel lblRegistrar;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnContacto;
    private javax.swing.JPanel pnContenedor;
    private javax.swing.JPanel pnHead;
    private javax.swing.JLabel txtCerrar;
    private javax.swing.JLabel txtContac;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JLabel txtEncabezado;
    private javax.swing.JLabel txtIngresar;
    private javax.swing.JLabel txtLogo;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
