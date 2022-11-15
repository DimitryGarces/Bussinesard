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
public final class InterfEmpleados extends javax.swing.JFrame {

    private ImageIcon imagen;
    private Icon icono;
    private static Connection con;
    private static String nombreU = "";
    String sqlContactos = "SELECT Nombre FROM bussinesscard.empleado where Nombre NOT LIKE ";
    String sqlMensajes = "SELECT Id_Empleado_O, Id_Empleado_D, Fecha, Mensaje, Ruta_Arch , Nombre  FROM "
            + " bussinesscard.Mensaje INNER JOIN bussinesscard.Empleado on Empleado.Id_Empleado = Mensaje.Id_Empleado_D"
            + " WHERE Nombre LIKE ";
    Statement st;
    int i;
    String O, D;
    private String arr[] = null;

    /**
     * Creates new form InterfEmpleados
     *
     * @param con
     * @param nombre
     */
    public InterfEmpleados(Connection con, String nombre) {
        initComponents();
        this.con = con;
        this.nombreU = nombre;
        pintarImagen(lbRegistrar, "src/imgspackage/registro.png");
        lbMsjBienvenida.setText("Hola " + nombreU + " ! ");
        valida();
    }

    public void valida() {
        i = 0;
        sqlContactos += " \"" + nombreU + "\";";
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sqlContactos);
            while (rs.next()) {
                inserta(rs.getString(1));
                i++;
            }

            listContactos.setModel(new javax.swing.AbstractListModel<String>() {

                String[] strings = arr;

                public int getSize() {
                    return strings.length;
                }

                public String getElementAt(int i) {
                    return strings[i];
                }

            });
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void inserta(String a) {
        if (arr == null) {
            arr = new String[1];
            arr[0] = a;
        } else {
            String nvo[] = new String[arr.length + 1];
            System.arraycopy(arr, 0, nvo, 0, arr.length);
            nvo[arr.length] = a;
            arr = nvo;
        }
    }

    private void pintarImagen(JLabel lbl, String ruta) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnContenedor = new javax.swing.JPanel();
        pnContactos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listContactos = new javax.swing.JList<>();
        lbRecargar = new javax.swing.JLabel();
        pnMensajes = new javax.swing.JPanel();
        txtMensaje = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listMensajes = new javax.swing.JList<>();
        pnHead = new javax.swing.JPanel();
        txtEncabezado = new javax.swing.JLabel();
        lbRegistrar = new javax.swing.JLabel();
        lbMsjBienvenida = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mensajes");

        pnContenedor.setBackground(new java.awt.Color(255, 255, 255));
        pnContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnContactos.setBackground(new java.awt.Color(153, 153, 153));

        listContactos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        listContactos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listContactosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listContactos);

        lbRecargar.setText("Refrescar");
        lbRecargar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbRecargarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnContactosLayout = new javax.swing.GroupLayout(pnContactos);
        pnContactos.setLayout(pnContactosLayout);
        pnContactosLayout.setHorizontalGroup(
            pnContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContactosLayout.createSequentialGroup()
                .addGroup(pnContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnContactosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnContactosLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lbRecargar)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnContactosLayout.setVerticalGroup(
            pnContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContactosLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbRecargar)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnContenedor.add(pnContactos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 450));

        pnMensajes.setBackground(new java.awt.Color(204, 204, 204));

        txtMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMensajeActionPerformed(evt);
            }
        });

        jLabel1.setText("Enviar");
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setText("Archivo");
        jLabel2.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Mensaje");

        listMensajes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        listMensajes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Mensaje 1" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listMensajes);

        javax.swing.GroupLayout pnMensajesLayout = new javax.swing.GroupLayout(pnMensajes);
        pnMensajes.setLayout(pnMensajesLayout);
        pnMensajesLayout.setHorizontalGroup(
            pnMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMensajesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnMensajesLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnMensajesLayout.setVerticalGroup(
            pnMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMensajesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(pnMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );

        pnContenedor.add(pnMensajes, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 610, 380));

        txtEncabezado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtEncabezado.setText("BUSINESSCARD");

        javax.swing.GroupLayout pnHeadLayout = new javax.swing.GroupLayout(pnHead);
        pnHead.setLayout(pnHeadLayout);
        pnHeadLayout.setHorizontalGroup(
            pnHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHeadLayout.createSequentialGroup()
                .addComponent(txtEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 365, Short.MAX_VALUE))
        );
        pnHeadLayout.setVerticalGroup(
            pnHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        pnContenedor.add(pnHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 610, 40));

        lbRegistrar.setText("Registrar");
        lbRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbRegistrarMouseClicked(evt);
            }
        });
        pnContenedor.add(lbRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, 40, 30));

        lbMsjBienvenida.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbMsjBienvenida.setText("Hola");
        pnContenedor.add(lbMsjBienvenida, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 340, 30));

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

    private void txtMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMensajeActionPerformed

    }//GEN-LAST:event_txtMensajeActionPerformed

    private void lbRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRegistrarMouseClicked
        InterfAltaEmp interf = new InterfAltaEmp(con);
        interf.setVisible(true);

    }//GEN-LAST:event_lbRegistrarMouseClicked

    private void listContactosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listContactosMouseClicked

        O=D="";
        String selected = listContactos.getSelectedValue();
        arr = null;
        sqlMensajes += "\"" + selected + "\" AND Mensaje.Id_Empleado_O IN (SELECT Id_Empleado From bussinesscard.Empleado "
                + "WHERE Nombre LIKE \"" + nombreU + "\") ORDER BY Fecha DESC; ";
        i = 0;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sqlMensajes);
            while (rs.next()) {
                inserta(rs.getString(4));
                O=rs.getString(1);
                D=rs.getString(2);
            }
            i++;
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (arr != null) {
            listMensajes.setModel(new javax.swing.AbstractListModel<String>() {
                String[] strings = arr;

                public int getSize() {
                    return strings.length;
                }

                public String getElementAt(int i) {
                    return strings[i];
                }
            });
        }else{
            JOptionPane.showMessageDialog(null,"Inicia una conversacion!");
        }

    }//GEN-LAST:event_listContactosMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        try {
            PreparedStatement pps = con.prepareStatement("Insert into `bussinesscard`.`mensaje` (`Id_Empleado_O`,`Id_Empleado_D`,`Fecha`,`Mensaje`,`Ruta_Arch`)"
                    + "Values (?,?,?,?,?)");
            pps.setString(1, O);
            pps.setString(2, D);
            pps.setString(3, "\"GETDATE()\"");
            pps.setString(4, txtMensaje.getText());
            pps.setString(5, "");
        }catch(SQLException ex){
            Logger.getLogger(InterfAltaEmp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jLabel1MouseClicked

    private void lbRecargarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRecargarMouseClicked
        valida();
    }//GEN-LAST:event_lbRecargarMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfEmpleados(con, nombreU).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbMsjBienvenida;
    private javax.swing.JLabel lbRecargar;
    private javax.swing.JLabel lbRegistrar;
    private javax.swing.JList<String> listContactos;
    private javax.swing.JList<String> listMensajes;
    private javax.swing.JPanel pnContactos;
    private javax.swing.JPanel pnContenedor;
    private javax.swing.JPanel pnHead;
    private javax.swing.JPanel pnMensajes;
    private javax.swing.JLabel txtEncabezado;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables
}
