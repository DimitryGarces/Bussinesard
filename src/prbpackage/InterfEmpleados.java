package prbpackage;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    private static String nombreU = "", rol = "";
    String sqlContactos = "SELECT Nombre FROM bussinesscard.empleado where Nombre NOT LIKE ";
    String sqlMensajes = "";
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
    public InterfEmpleados(Connection con, String nombre, String rol) {
        initComponents();
        this.con = con;
        this.nombreU = nombre;
        this.rol = rol;
        pintarImagen(lbRegistrar, "/imgspackage/registro.png");
        pintarImagen(lbArchivo, "/imgspackage/Archivo.png");
        pintarImagen(lbEnviar, "/imgspackage/Enviar.png");
        pintaRol();
//        pintarImagen(lbRecargar, "/imgspackage/reload.png");
        lbMsjBienvenida.setText("Hola de nuevo " + nombreU + " ! ");
        valida();

//        this.pnContactos.add(myList, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 450));
    }

    public void pintaRol() {
        if (rol.contains("Administrador")) {
            pintarImagen(lbRol, "/imgspackage/Admin.png");
            pintarImagen(lbDarBaja, "/imgspackage/BajaEmpleado.png");
            lbRegistrar.setVisible(true);
            lbDarBaja.setVisible(true);
        } else if (rol.contains("Moderador")) {
            pintarImagen(lbRol, "/imgspackage/Moderador.png");
            lbRegistrar.setVisible(false);
            lbDarBaja.setVisible(false);
        } else {
            pintarImagen(lbRol, "/imgspackage/Empleado.png");
            lbRegistrar.setVisible(false);
            lbDarBaja.setVisible(false);
        }
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
//             for (int j = 0; j < arr.length; j++) {
//                myList.addItem(new Item(arr[j], new ImageIcon(getClass().getResource("/imgspackage/Conected.png"))));
//            }
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

        pnContenedor = new javax.swing.JPanel();
        pnContactos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listContactos = new javax.swing.JList<>();
        lbRecargar = new javax.swing.JLabel();
        lbRol = new javax.swing.JLabel();
        pnMensajes = new javax.swing.JPanel();
        txtMensaje = new javax.swing.JTextField();
        lbEnviar = new javax.swing.JLabel();
        lbArchivo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listMensajes = new javax.swing.JList<>();
        lbNombreChat = new javax.swing.JLabel();
        pnHead = new javax.swing.JPanel();
        txtEncabezado = new javax.swing.JLabel();
        lbRegistrar = new javax.swing.JLabel();
        lbMsjBienvenida = new javax.swing.JLabel();
        lbDarBaja = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mensajes");

        pnContenedor.setBackground(new java.awt.Color(255, 255, 255));
        pnContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnContactos.setBackground(new java.awt.Color(255, 102, 0));

        listContactos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        listContactos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listContactosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listContactos);

        lbRecargar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbRecargarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnContactosLayout = new javax.swing.GroupLayout(pnContactos);
        pnContactos.setLayout(pnContactosLayout);
        pnContactosLayout.setHorizontalGroup(
            pnContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnContactosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbRol, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(pnContactosLayout.createSequentialGroup()
                .addGroup(pnContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnContactosLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lbRecargar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnContactosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        pnContactosLayout.setVerticalGroup(
            pnContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContactosLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lbRol, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143)
                .addComponent(lbRecargar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnContenedor.add(pnContactos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 450));

        pnMensajes.setBackground(new java.awt.Color(255, 255, 255));

        txtMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMensajeActionPerformed(evt);
            }
        });
        txtMensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMensajeKeyPressed(evt);
            }
        });

        lbEnviar.setOpaque(true);
        lbEnviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbEnviarMouseClicked(evt);
            }
        });

        lbArchivo.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Mensaje");

        listMensajes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jScrollPane2.setViewportView(listMensajes);

        lbNombreChat.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbNombreChat.setText("Usuario");

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
                .addComponent(lbArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnMensajesLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNombreChat)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnMensajesLayout.setVerticalGroup(
            pnMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMensajesLayout.createSequentialGroup()
                .addComponent(lbNombreChat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(pnMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );

        pnContenedor.add(pnMensajes, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 610, 360));

        pnHead.setBackground(new java.awt.Color(255, 153, 51));

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

        lbRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbRegistrarMouseClicked(evt);
            }
        });
        pnContenedor.add(lbRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 40, 60, 50));

        lbMsjBienvenida.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbMsjBienvenida.setText("Hola");
        pnContenedor.add(lbMsjBienvenida, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 340, 30));

        lbDarBaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDarBajaMouseClicked(evt);
            }
        });
        pnContenedor.add(lbDarBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 50, 40));

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

        arr = null;
        lbNombreChat.setText("");
        O = D = "";
        String selected = listContactos.getSelectedValue();
        lbNombreChat.setText(selected);
        arr = null;
        sqlMensajes = " SELECT Id_Empleado_O, Id_Empleado_D, Fecha, Mensaje, Ruta_Arch , Nombre  FROM "
                + " bussinesscard.Mensaje INNER JOIN bussinesscard.Empleado on Empleado.Id_Empleado = Mensaje.Id_Empleado_D"
                + " WHERE Nombre LIKE  \"" + selected + "\" AND Mensaje.Id_Empleado_O IN (SELECT Id_Empleado From bussinesscard.Empleado "
                + "WHERE Nombre LIKE \"" + nombreU + "\") ORDER BY Fecha DESC; ";
        i = 0;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sqlMensajes);
            while (rs.next()) {
                inserta("De: " + nombreU + "\nPara: " + selected + "\n(" + rs.getString(3) + ")\t" + rs.getString(4) + "\n");
                O = rs.getString(1);
                D = rs.getString(2);
            }
            i++;
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        actualizaMensajes();

    }//GEN-LAST:event_listContactosMouseClicked

    public void actualizaMensajes() {
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
        } else {
            JOptionPane.showMessageDialog(null, "Inicia una conversacion!");
        }
    }
    private void lbEnviarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbEnviarMouseClicked

        Calendar fecha = new GregorianCalendar();
        String ano = Integer.toString(fecha.get(Calendar.YEAR));
        String mes = Integer.toString(fecha.get(Calendar.MONTH));
        String dia = Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
        String hora = Integer.toString(fecha.get(Calendar.HOUR_OF_DAY));
        String minuto = Integer.toString(fecha.get(Calendar.MINUTE));
        String seg = Integer.toString(fecha.get(Calendar.SECOND));
        String fechaF = ano + "-" + mes + "-" + dia + "-" + hora + "-" + minuto + "-" + seg;
        try {
            PreparedStatement pps = con.prepareStatement("Insert into `bussinesscard`.`mensaje` (`Id_Empleado_O`,`Id_Empleado_D`,`Fecha`,`Mensaje`,`Ruta_Arch`)"
                    + "Values (?,?,?,?,?)");
            pps.setString(1, O);
            pps.setString(2, D);
            pps.setString(3, fechaF);
            pps.setString(4, txtMensaje.getText());
            pps.setString(5, "");
            pps.executeUpdate();
            txtMensaje.setText("");
            JOptionPane.showMessageDialog(null, "Enviado!");
            listContactosMouseClicked(null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ups! Algo a salido mal.", "Advertencia", JOptionPane.WARNING_MESSAGE);

//            Logger.getLogger(InterfAltaEmp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_lbEnviarMouseClicked

    private void lbRecargarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRecargarMouseClicked
        valida();
    }//GEN-LAST:event_lbRecargarMouseClicked

    private void txtMensajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMensajeKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            if (txtMensaje.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Escriba un mensaje antes de enviarlo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                lbEnviarMouseClicked(null);
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMensajeKeyPressed

    private void lbDarBajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDarBajaMouseClicked

        InterfBajaEmpleado baja = new InterfBajaEmpleado(con,arr);
        baja.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_lbDarBajaMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfEmpleados(con, nombreU, rol).setVisible(true);
            }
        });
    }
    private prbpackage.MyList myList = new prbpackage.MyList();
    ;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbArchivo;
    private javax.swing.JLabel lbDarBaja;
    private javax.swing.JLabel lbEnviar;
    private javax.swing.JLabel lbMsjBienvenida;
    private javax.swing.JLabel lbNombreChat;
    private javax.swing.JLabel lbRecargar;
    private javax.swing.JLabel lbRegistrar;
    private javax.swing.JLabel lbRol;
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
