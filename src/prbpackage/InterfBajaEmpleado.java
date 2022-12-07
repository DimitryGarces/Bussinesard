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
public class InterfBajaEmpleado extends javax.swing.JFrame {

    private static Connection con;
    private static Arreglo arr = new Arreglo();
    private static String rol = "", nombreU = "";
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
    public InterfBajaEmpleado(Connection con, String nombreU, String rol) {
        initComponents();
        InterfBajaEmpleado.con = con;
        InterfBajaEmpleado.arr = new Arreglo();
        InterfBajaEmpleado.nombreU = nombreU;
        pintarImagen(lbFondo, "/imgspackage/FondoBaja.png");
        pintarImagen(lbLogo, "/imgspackage/BusinessCard.png");
        pintarImagen(lbBoton, "/imgspackage/BajaEmpleado.png");
        valida();

        hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (selected != null) {
                        valida();
                        System.out.println("Contactos Actualizados");
                    }
                    try {
                        Thread.sleep(1200);
                    } catch (InterruptedException ex) {
                        JOptionPane.showMessageDialog(null, "Error de sincronización", "Fatal", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
        hilo.start();
    }

    public void valida() {
        sqlListContactos = "SELECT Nombre FROM bussinesscard.empleado where Nombre NOT LIKE \"" + nombreU + "\" "
                + " AND Nombre NOT LIKE \"Grupo%\";";
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sqlListContactos);
            while (rs.next()) {
                arr.inserta(rs.getString(1));
            }
            lst.setModel(new javax.swing.AbstractListModel<String>() {
                String[] strings = arr.getArr();

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
        arr.vaciarArr();
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

    public void pintaRol() {
        if (rol.contains("Administrador")) {
            pintarImagen(lbRol, "/imgspackage/Admin.png");
        } else {
            pintarImagen(lbRol, "/imgspackage/Moderador.png");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtEncabezado = new javax.swing.JLabel();
        lbRol = new javax.swing.JLabel();
        lbLogo = new javax.swing.JLabel();
        pnList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lst = new javax.swing.JList<>();
        lb1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbBoton = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtRol = new javax.swing.JTextField();
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
                .addComponent(txtEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
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

        pnContainer.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 563, 60));

        pnList.setBackground(new java.awt.Color(255, 255, 255));

        lst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lst);

        javax.swing.GroupLayout pnListLayout = new javax.swing.GroupLayout(pnList);
        pnList.setLayout(pnListLayout);
        pnListLayout.setHorizontalGroup(
            pnListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnListLayout.setVerticalGroup(
            pnListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnListLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pnContainer.add(pnList, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, 280));

        lb1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lb1.setText("Datos Generales del Empleado:");
        pnContainer.add(lb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Nombre");
        pnContainer.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Apellidos");
        pnContainer.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Rol");
        pnContainer.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, -1, -1));

        lbBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbBotonMouseClicked(evt);
            }
        });
        pnContainer.add(lbBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 290, 60, 50));
        pnContainer.add(txtNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 120, -1));
        pnContainer.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 120, -1));
        pnContainer.add(txtRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 150, -1));
        pnContainer.add(lbFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 60, 560, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBotonMouseClicked
        if (selected != null) {
            try {
                String drop = "Delete from auditoria where Id_EmpleadoR = (SELECT Id_Empleado From empleado where Nombre LIKE \"" + selected + "\") OR Id_Moderador"
                        + " = (SELECT Id_Empleado From empleado where Nombre LIKE \"" + selected + "\");";
                String sql = "Delete from empleado where nombre like \"" + selected + "\"";
                st = con.createStatement();
                st.executeUpdate(drop);
                st = con.createStatement();
                st.executeUpdate(sql);
                txtNom.setText("");
                txtApellidos.setText("");
                txtRol.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ups! Algo a salido mal.", "Advertencia", JOptionPane.WARNING_MESSAGE);

            }
        } else {

        }
    }//GEN-LAST:event_lbBotonMouseClicked

    private void lstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstMouseClicked
        selected = lst.getSelectedValue();
        sqlContactos = "SELECT Apellidos,role.Nombre FROM bussinesscard.empleado INNER JOIN bussinesscard.role on"
                + " empleado.role= role.id_role where empleado.nombre like \"" + selected + "\";";

        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sqlContactos);
            rs.next();
            txtNom.setText(selected);
            txtApellidos.setText(rs.getString(1));
            txtRol.setText(rs.getString(2));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Parece ser que algo no salio bien.");
        }
    }//GEN-LAST:event_lstMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfBajaEmpleado(con, nombreU, rol).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lbBoton;
    private javax.swing.JLabel lbFondo;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lbRol;
    private javax.swing.JList<String> lst;
    private javax.swing.JPanel pnContainer;
    private javax.swing.JPanel pnList;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JLabel txtEncabezado;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtRol;
    // End of variables declaration//GEN-END:variables
}
