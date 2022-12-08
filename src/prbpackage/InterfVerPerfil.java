/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prbpackage;

import java.awt.Image;
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
public class InterfVerPerfil extends javax.swing.JFrame {

    private static Connection con;
    private static Arreglo arr = new Arreglo();
    private static String rol = "", nombreU = "", empleado = "";
    private ImageIcon imagen;
    private Icon icono;
    Statement st;
    String sqlContactos = "", sqlListContactos = "", selected = "";
    Thread hilo;

    /**
     * Creates new form InterfBajaEmpleado
     *
     * @param con
     * @param nombreU
     * @param rol
     */
    public InterfVerPerfil(Connection con, String nombreU, String rol, String empleado) {
        initComponents();
        InterfVerPerfil.con = con;
        InterfVerPerfil.arr = new Arreglo();
        InterfVerPerfil.nombreU = nombreU;
        InterfVerPerfil.empleado = empleado;
        pintarImagen(lbFondo, "/imgspackage/FondoBaja.png");
        pintarImagen(lbLogo, "/imgspackage/BusinessCard.png");
        pintaRol(rol);
        sqlContactos = "SELECT Nombre, Apellidos, Role, Telefono FROM Empleado WHERE Nombre Like \"" + empleado + "\";";
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sqlContactos);
            rs.next();
            String rol2 = "";
            switch (rs.getString(3)) {
                case "1":
                    rol2 = "Administrador";
                    break;
                case "2":
                    rol2 = "Moderador";
                    break;
                case "3":
                    rol2 = "Empleado";
                    break;    
                default:
                    throw new AssertionError();
            }
            txtNom.setText(rs.getString(1));
            txtApellidos.setText(rs.getString(2));
            txtRol.setText(rol2);
            txtContact.setText(rs.getString(4));
        } catch (SQLException ex) {

        }
    }

    public void pintaRol(String rol) {
        if (rol.contains("Administrador")) {
            pintarImagen(lbRol, "/imgspackage/Admin.png");
            lblContacto.setVisible(true);
            txtContact.setVisible(true);
        } else if (rol.contains("Moderador")) {
            pintarImagen(lbRol, "/imgspackage/Moderador.png");
            lblContacto.setVisible(false);
            txtContact.setVisible(false);
        } else {
            pintarImagen(lbRol, "/imgspackage/Empleado.png");
            lblContacto.setVisible(false);
            txtContact.setVisible(false);
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

        pnContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtEncabezado = new javax.swing.JLabel();
        lbRol = new javax.swing.JLabel();
        lbLogo = new javax.swing.JLabel();
        lb1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbBoton = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtRol = new javax.swing.JTextField();
        lblContacto = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        lbFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnContainer.setBackground(new java.awt.Color(255, 102, 0));
        pnContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtEncabezado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtEncabezado.setText("BUSINESSCARD");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbRol, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(txtEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
            .addComponent(lbRol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnContainer.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 60));

        lb1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lb1.setText("Datos Generales del Empleado:");
        pnContainer.add(lb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Nombre");
        pnContainer.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Apellidos");
        pnContainer.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Rol");
        pnContainer.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, -1, -1));

        lbBoton.setText("Regresar");
        lbBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbBotonMouseClicked(evt);
            }
        });
        pnContainer.add(lbBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 60, 50));

        txtNom.setEditable(false);
        pnContainer.add(txtNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 120, -1));

        txtApellidos.setEditable(false);
        pnContainer.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 120, -1));

        txtRol.setEditable(false);
        pnContainer.add(txtRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 150, -1));

        lblContacto.setText("Contacto");
        pnContainer.add(lblContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, -1, -1));

        txtContact.setEditable(false);
        pnContainer.add(txtContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 150, -1));
        pnContainer.add(lbFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 60, 480, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBotonMouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_lbBotonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfVerPerfil(con, nombreU, rol, empleado).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lbBoton;
    private javax.swing.JLabel lbFondo;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lbRol;
    private javax.swing.JLabel lblContacto;
    private javax.swing.JPanel pnContainer;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtContact;
    private javax.swing.JLabel txtEncabezado;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtRol;
    // End of variables declaration//GEN-END:variables
}
