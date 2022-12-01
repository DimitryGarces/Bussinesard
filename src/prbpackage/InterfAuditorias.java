/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
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
public class InterfAuditorias extends javax.swing.JFrame {

    private ImageIcon imagen;
    private Icon icono;
    private static Connection con;
    private static String nombreU = "";
    String sqlReportes = "";
    String sqlEmpleados = "";
    Statement st;
    Thread hiloA;
    String id = "";
    private Arreglo arrE = new Arreglo(), arrR = new Arreglo();

    public InterfAuditorias(Connection con, String nombre) {
        initComponents();
        InterfAuditorias.con = con;
        InterfAuditorias.nombreU = nombre;
        cbEm = new prbpackage.Combobox();
        valida();
        try {
            pintarImagen(lbNuevaSol, "/imgspackage/llamada.png");
            pintarImagen(lbEnviar, "/imgspackage/enviarEm.png");

        } catch (NullPointerException ex) {

        }
        pnSolicitud.setEnabled(false);
        txtDescripcion.setEnabled(false);
        lbEnviar.setEnabled(false);
        lbD.setEnabled(false);
        cbEm.setEnabled(false);
        lbE.setEnabled(false);
        this.pnSolicitud.add(cbEm, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));
        hiloA = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        validaReportes();
                    } catch (Exception exF) {

                    }
                    try {
                        Thread.sleep(1200);
                    } catch (InterruptedException ex) {
                        JOptionPane.showMessageDialog(null, "Error de sincronización", "Fatal", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
        hiloA.start();
    }

    public void valida() {
        sqlEmpleados = "Select Nombre from bussinesscard.empleado Where Nombre NOT LIKE  \"" + nombreU + "\" AND Nombre NOT LIKE \"Grupo%\";";
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sqlEmpleados);
            arrE.inserta("");
            while (rs.next()) {
                arrE.inserta(rs.getString(1));
            }
            cbEm.setModel(new javax.swing.DefaultComboBoxModel() {
                String[] strings = arrE.getArr();

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
        arrE.vaciarArr();
    }

    public ResultSet met(ResultSet rst) {
        String s = "", r = "", estatus = "";
        try {
            s = rst.getString(1);
            r = rst.getString(2);
            estatus = rst.getString(3);
            String st = "";
            if (estatus.equals("1")) {
                st += "Verificado";
            } else {
                st += "Pendiente";
            }
            arrR.inserta("Se solicito a: (" + s + ") Razon: (" + r + ") Estatus: " + st);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            arrR.inserta("Se solicito a: (" + s + ") Razon: (" + r + ") Estatus: Rechazada");
        }
        return (rst);
    }

    public void validaReportes() {
        sqlReportes = "Select Id_EmpleadoR,Descripcion,Estado from bussinesscard.auditoria"
                + " INNER JOIN bussinesscard.empleado on auditoria.Id_Moderador=empleado.Id_Empleado Where Nombre"
                + " LIKE  \"" + nombreU + "\";";
        ResultSet rs = null;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sqlReportes);
            while(rs.next()){
                rs=met(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } 
        listSolicitudes.setModel(new javax.swing.DefaultComboBoxModel() {
            String[] strings = arrR.getArr();

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });
        arrR.vaciarArr();
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
        pnHead = new javax.swing.JPanel();
        txtEncabezado = new javax.swing.JLabel();
        lbCancelar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listSolicitudes = new javax.swing.JList<>();
        lbNuevaSol = new javax.swing.JLabel();
        pnSolicitud = new javax.swing.JPanel();
        txtDescripcion = new javax.swing.JTextField();
        lbD = new javax.swing.JLabel();
        lbEnviar = new javax.swing.JLabel();
        lbE = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnContainer.setBackground(new java.awt.Color(255, 255, 255));
        pnContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
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

        pnContainer.add(pnHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 50));

        jLabel1.setText("Tus solicitudes:");
        pnContainer.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        listSolicitudes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(listSolicitudes);

        pnContainer.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 430, 100));

        lbNuevaSol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNuevaSolMouseClicked(evt);
            }
        });
        pnContainer.add(lbNuevaSol, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 60, 50));

        pnSolicitud.setBackground(new java.awt.Color(204, 204, 204));
        pnSolicitud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDescripcion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyPressed(evt);
            }
        });
        pnSolicitud.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 272, -1));

        lbD.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbD.setText("Descripcion");
        pnSolicitud.add(lbD, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        lbEnviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbEnviarMouseClicked(evt);
            }
        });
        pnSolicitud.add(lbEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 45, 36));

        lbE.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbE.setText("Define el empleado que requieres");
        pnSolicitud.add(lbE, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        pnContainer.add(pnSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 430, 140));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCancelarMouseClicked
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_lbCancelarMouseClicked

    private void lbNuevaSolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNuevaSolMouseClicked

        String sacaId = "Select Id_Empleado from bussinesscard.empleado Where Nombre LIKE \"" + nombreU + "\"";
        st = con.createStatement();
        ResultSet rs;
        try {
            rs = st.executeQuery(sacaId);
            rs.next();
            id = rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(InterfAuditorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        pnSolicitud.setEnabled(true);
        txtDescripcion.setEnabled(true);
        lbEnviar.setEnabled(true);
        lbEnviar.setEnabled(true);
        lbD.setEnabled(true);
        cbEm.setEnabled(true);
        lbE.setEnabled(true);
        valida();
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNuevaSolMouseClicked

    private void lbEnviarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbEnviarMouseClicked

        if (cbEm.getSelectedIndex() == 0 || txtDescripcion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione todos los campos.", "Fatal", JOptionPane.ERROR_MESSAGE);

        } else {
            try {
                String obtenId = "Select Id_Empleado from bussinesscard.empleado Where Nombre LIKE \"" + cbEm.getSelectedItem() + "\";";
                st = con.createStatement();
                ResultSet rs = st.executeQuery(obtenId);
                rs.next();
                PreparedStatement pps = con.prepareStatement("Insert into `bussinesscard`.`auditoria` ( `Id_Moderador`,`Id_EmpleadoR`,`Descripcion`,`Estado`)"
                        + "Values (?,?,?,?)");
                pps.setString(1, id);
                pps.setString(2, "" + rs.getString(1));
                pps.setString(3, txtDescripcion.getText());
                pps.setString(4, "0");
                pps.executeUpdate();
                txtDescripcion.setText("");
                cbEm.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, "Reporte enviado con exito.");
            } catch (SQLException ex) {
                Logger.getLogger(InterfAltaEmp.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnSolicitud.setEnabled(false);
            txtDescripcion.setEnabled(false);
            lbEnviar.setEnabled(false);
            lbD.setEnabled(false);
            cbEm.setEnabled(false);
            lbE.setEnabled(false);
        }
    }//GEN-LAST:event_lbEnviarMouseClicked

    private void txtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtDescripcion.getText().equals("")) {
                lbEnviarMouseClicked(null);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, Llene todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);

            }
        }
    }//GEN-LAST:event_txtDescripcionKeyPressed

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
            java.util.logging.Logger.getLogger(InterfAuditorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfAuditorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfAuditorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfAuditorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfAuditorias(con, nombreU).setVisible(true);
            }
        });
    }

    private prbpackage.Combobox cbEm;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCancelar;
    private javax.swing.JLabel lbD;
    private javax.swing.JLabel lbE;
    private javax.swing.JLabel lbEnviar;
    private javax.swing.JLabel lbNuevaSol;
    private javax.swing.JList<String> listSolicitudes;
    private javax.swing.JPanel pnContainer;
    private javax.swing.JPanel pnHead;
    private javax.swing.JPanel pnSolicitud;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JLabel txtEncabezado;
    // End of variables declaration//GEN-END:variables
}
