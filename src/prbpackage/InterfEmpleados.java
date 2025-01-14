package prbpackage;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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
    private static String nombreU = "", rol = "", o = "", d = "";
    byte[] archivo = null;
    String sqlContactos = "", sqlMensajes = "", selected, archivoRuta = "", archivoNombre = null;
    Statement st;
    private Arreglo arrC = new Arreglo(), arrM = new Arreglo(), arrG = new Arreglo();
    Thread hiloA;
    private static int grupo = -1;
    int b = 0;
    Notificacion not;

    /**
     * Creates new form InterfEmpleados
     *
     * @param con
     * @param nombre
     * @param rol
     * @param grupo
     */
    public InterfEmpleados(Connection con, String nombre, String rol, int grupo) {
        initComponents();
        InterfEmpleados.con = con;
        InterfEmpleados.nombreU = nombre;
        InterfEmpleados.rol = rol;
        InterfEmpleados.grupo = grupo;

        lbMsjBienvenida.setText("Hola de nuevo " + nombreU + " ! ");
        try {
            pintarImagen(lbRegistrar, "/imgspackage/registro.png");
            pintarImagen(lbEnviar, "/imgspackage/Enviar.png");
            pintarImagen(lbArchivp, "/imgspackage/Archivo.png");
            pintarImagen(lbAltaGrupo, "/imgspackage/Grupos.png");
            pintarImagen(lbCerrarSesion, "/imgspackage/CerrarSesion.png");
            pintarImagen(lbEmergencia, "/imgspackage/Emergency.png");
            pintaRol();
        } catch (NullPointerException ex) {

        }
        listMensajes.setVisible(false);
        lbM.setVisible(false);
        txtMensaje.setVisible(false);
        lbEnviar.setVisible(false);
        lbArchivp.setVisible(false);
        lbNombreChat.setVisible(false);
        hiloA = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        valida();
                        validaGrupos();
//                        System.out.println("Contactos Actualizados");
                        if (b == 1) {
                            mensajes();
//                            System.out.println("Msj privados Actualizados");
                        } else if (b == 2) {
                            grupos();
//                            System.out.println("Msj grupales Actualizados");
                        }
                        if (rol.contains("Administrador")) {
                            validaReportes();
                        }
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
//        this.pnContactos.add(myList, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 450));
    }

    public void valida() {
        sqlContactos = "SELECT Nombre FROM bussinesscard.empleado where Nombre NOT LIKE \"" + nombreU + "\";";
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sqlContactos);
            while (rs.next()) {
                String aux = rs.getString(1);
                if (aux.contains("Grupo")) {

                } else {
                    arrC.inserta(aux);
                }

            }
//             for (int j = 0; j < arr.length; j++) {
//                myList.addItem(new Item(arr[j], new ImageIcon(getClass().getResource("/imgspackage/Conected.png"))));
//            }
            listContactos.setModel(new javax.swing.AbstractListModel<String>() {
                String[] strings = arrC.getArr();

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
        arrC.vaciarArr();
    }

    public void validaGrupos() {
        if (grupo != -1) {
            String origenI = "";
            lbGrupos.setVisible(true);
            listGrupos.setVisible(true);
            String auxO = "SELECT Id_Empleado FROM empleado WHERE Nombre LIKE \"" + nombreU + "\"";
            st = con.createStatement();
            ResultSet rs;
            try {
                rs = st.executeQuery(auxO);
                rs.next();
                origenI = rs.getString(1);
            } catch (SQLException ex) {

            }
            sqlContactos = "SELECT NombreGrupo FROM bussinesscard.grupo where Id_Grupo =" + grupo + " OR Id_Owner = " + origenI + ";";
            try {
                st = con.createStatement();
                rs = st.executeQuery(sqlContactos);
                while (rs.next()) {
                    arrG.inserta(rs.getString(1));
                }
                listGrupos.setModel(new javax.swing.AbstractListModel<String>() {
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
        } else {
            try {
                String grups = "Select NombreGrupo from bussinesscard.grupo INNER JOIN"
                        + " bussinesscard.empleado on grupo.Id_Owner=empleado.Id_Empleado Where "
                        + "Nombre LIKE \"" + nombreU + "\";";
                st = con.createStatement();
                ResultSet rs = st.executeQuery(grups);
                while (rs.next()) {
                    arrG.inserta(rs.getString(1));
                }
                if (!arrG.vacio()) {
                    listGrupos.setVisible(true);
                    listGrupos.setModel(new javax.swing.AbstractListModel<String>() {
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
                    arrG.vaciarArr();
                }
            } catch (SQLException ex) {
                lbGrupos.setVisible(false);
                listGrupos.setVisible(false);
            }
        }

    }

    public void validaReportes() {
        if (rol.equals("Administrador")) {
            sqlContactos = "SELECT Id_Auditoria,Id_Moderador,Id_EmpleadoR,Descripcion FROM bussinesscard.auditoria where"
                    + " Estado = 0;";
            try {
                if (not == null) {
                    st = con.createStatement();
                    ResultSet rs = st.executeQuery(sqlContactos);
                    rs.next();
                    String sql1 = "SELECT Nombre FROM bussinesscard.empleado where Id_Empleado = " + rs.getString(2);
                    String sql2 = "SELECT Nombre FROM bussinesscard.empleado where Id_Empleado = " + rs.getString(3);
                    Statement st1 = con.createStatement();
                    ResultSet rs1 = st1.executeQuery(sql1);
                    rs1.next();
                    String mod = rs1.getString(1);
                    rs1 = st.executeQuery(sql2);
                    rs1.next();
                    String emp = rs1.getString(1);
                    not = new Notificacion(mod, emp, rs.getString(4), rs.getString(1), con);
                    not.setVisible(true);
                }
                if (!not.isShowing()) {
                    not = null;
                }
            } catch (SQLException ex) {

            }
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

    public void pintaRol() {
        if (getRol().contains("Administrador")) {
            pintarImagen(lbRol, "/imgspackage/Admin.png");
            pintarImagen(lbDarBaja, "/imgspackage/BajaEmpleado.png");
            lbRegistrar.setVisible(true);
            lbDarBaja.setVisible(true);
            lbEmergencia.setVisible(false);
        } else if (getRol().contains("Moderador")) {
            pintarImagen(lbRol, "/imgspackage/Moderador.png");
            lbRegistrar.setVisible(false);
            lbDarBaja.setVisible(false);
            lbAltaGrupo.setVisible(false);
            lbEmergencia.setVisible(true);
        } else {
            pintarImagen(lbRol, "/imgspackage/Empleado.png");
            lbRegistrar.setVisible(false);
            lbDarBaja.setVisible(false);
            lbAltaGrupo.setVisible(false);
            lbEmergencia.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        perfil = new javax.swing.JMenuItem();
        pnContenedor = new javax.swing.JPanel();
        pnContactos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listContactos = new javax.swing.JList<>();
        lbRecargar = new javax.swing.JLabel();
        lbRol = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listGrupos = new javax.swing.JList<>();
        lbGrupos = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnMensajes = new javax.swing.JPanel();
        txtMensaje = new javax.swing.JTextField();
        lbEnviar = new javax.swing.JLabel();
        lbArchivp = new javax.swing.JLabel();
        lbM = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listMensajes = new javax.swing.JList<>();
        lbNombreChat = new javax.swing.JLabel();
        pnHead = new javax.swing.JPanel();
        txtEncabezado = new javax.swing.JLabel();
        lbCerrarSesion = new javax.swing.JLabel();
        lbRegistrar = new javax.swing.JLabel();
        lbMsjBienvenida = new javax.swing.JLabel();
        lbDarBaja = new javax.swing.JLabel();
        lbAltaGrupo = new javax.swing.JLabel();
        lbEmergencia = new javax.swing.JLabel();

        perfil.setText("VerPerfil");
        perfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perfilMouseClicked(evt);
            }
        });
        perfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perfilActionPerformed(evt);
            }
        });
        popupMenu.add(perfil);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mensajes");

        pnContenedor.setBackground(new java.awt.Color(255, 255, 255));
        pnContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnContactos.setBackground(new java.awt.Color(255, 102, 0));

        listContactos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        listContactos.setComponentPopupMenu(popupMenu);
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

        listGrupos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        listGrupos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listGruposMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(listGrupos);

        lbGrupos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbGrupos.setText("Grupo(s)");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Contactos");

        javax.swing.GroupLayout pnContactosLayout = new javax.swing.GroupLayout(pnContactos);
        pnContactos.setLayout(pnContactosLayout);
        pnContactosLayout.setHorizontalGroup(
            pnContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContactosLayout.createSequentialGroup()
                .addGroup(pnContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnContactosLayout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addComponent(lbRecargar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnContactosLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lbGrupos))
                        .addGroup(pnContactosLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(jLabel2))
                        .addGroup(pnContactosLayout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addComponent(lbRol, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnContactosLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnContactosLayout.setVerticalGroup(
            pnContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContactosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbRol, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lbGrupos)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbRecargar, javax.swing.GroupLayout.DEFAULT_SIZE, 3, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnContenedor.add(pnContactos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 450));

        pnMensajes.setBackground(new java.awt.Color(255, 255, 255));

        txtMensaje.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMensajeActionPerformed(evt);
            }
        });
        txtMensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMensajeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMensajeKeyTyped(evt);
            }
        });

        lbEnviar.setOpaque(true);
        lbEnviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbEnviarMouseClicked(evt);
            }
        });

        lbArchivp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbArchivpMouseClicked(evt);
            }
        });

        lbM.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbM.setText("Mensaje");

        listMensajes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        listMensajes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMensajesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listMensajes);

        lbNombreChat.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbNombreChat.setText("Usuario");

        javax.swing.GroupLayout pnMensajesLayout = new javax.swing.GroupLayout(pnMensajes);
        pnMensajes.setLayout(pnMensajesLayout);
        pnMensajesLayout.setHorizontalGroup(
            pnMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMensajesLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pnMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnMensajesLayout.createSequentialGroup()
                        .addComponent(lbM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbArchivp, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNombreChat, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        pnMensajesLayout.setVerticalGroup(
            pnMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMensajesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNombreChat, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(pnMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbM)
                        .addComponent(lbArchivp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );

        pnContenedor.add(pnMensajes, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 580, 360));

        pnHead.setBackground(new java.awt.Color(255, 153, 51));

        txtEncabezado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtEncabezado.setText("BUSINESSCARD");

        lbCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCerrarSesionMouseClicked(evt);
            }
        });
        lbCerrarSesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lbCerrarSesionKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnHeadLayout = new javax.swing.GroupLayout(pnHead);
        pnHead.setLayout(pnHeadLayout);
        pnHeadLayout.setHorizontalGroup(
            pnHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHeadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                .addComponent(lbCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnHeadLayout.setVerticalGroup(
            pnHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(lbCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnContenedor.add(pnHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 580, 40));

        lbRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbRegistrarMouseClicked(evt);
            }
        });
        pnContenedor.add(lbRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 50, 40));

        lbMsjBienvenida.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbMsjBienvenida.setText("Hola");
        pnContenedor.add(lbMsjBienvenida, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 310, 30));

        lbDarBaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDarBajaMouseClicked(evt);
            }
        });
        pnContenedor.add(lbDarBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 50, 40));

        lbAltaGrupo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAltaGrupoMouseClicked(evt);
            }
        });
        pnContenedor.add(lbAltaGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 80, 70));

        lbEmergencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbEmergenciaMouseClicked(evt);
            }
        });
        pnContenedor.add(lbEmergencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 90, 70));

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
        selected = listContactos.getSelectedValue();
        listMensajes.setVisible(true);
        lbM.setVisible(true);
        txtMensaje.setVisible(true);
        lbEnviar.setVisible(true);
        lbArchivp.setVisible(true);
        lbNombreChat.setVisible(true);
        b = 1;
        mensajes();
    }//GEN-LAST:event_listContactosMouseClicked

    private void mensajes() {
        if (selected != null) {
            String horario = "";
            String selectedRole = "SELECT Role FROM Empleado WHERE Nombre LIKE \"" + selected + "\";";
            st = con.createStatement();
            ResultSet rs4;
            try {
                rs4 = st.executeQuery(selectedRole);
                rs4.next();
                switch (rs4.getString(1)) {
                    case "1":
                        horario = "9:00-18:00 hrs";
                        break;
                    case "2":
                        horario = "9:00-20:00 hrs";
                        break;
                    case "3":
                        horario = "7:00-18:00 hrs";
                        break;
                    default:
                        throw new AssertionError();
                }
            } catch (SQLException ex) {
                Logger.getLogger(InterfEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
            lbNombreChat.setText("Estas en chat con " + selected + " \nCuyo horario es: " + horario);
            sqlMensajes = "SELECT Id_Empleado_O, Id_Empleado_D, Fecha, Mensaje, Ruta_Arch , Nombre, NombreArch  FROM bussinesscard.Mensaje"
                    + " INNER JOIN bussinesscard.Empleado on Empleado.Id_Empleado = Mensaje.Id_Empleado_D WHERE Nombre LIKE  "
                    + "\"" + selected + "\" AND Mensaje.Id_Empleado_O IN (SELECT Id_Empleado From bussinesscard.Empleado "
                    + "WHERE Nombre LIKE \"" + nombreU + "\")"
                    + "UNION SELECT Id_Empleado_O, Id_Empleado_D, Fecha, Mensaje, Ruta_Arch , Nombre, NombreArch FROM bussinesscard.Mensaje"
                    + " INNER JOIN bussinesscard.Empleado on Empleado.Id_Empleado = Mensaje.Id_Empleado_D WHERE Nombre LIKE  "
                    + "\"" + nombreU + "\" AND Mensaje.Id_Empleado_O IN (SELECT Id_Empleado From bussinesscard.Empleado "
                    + "WHERE Nombre LIKE \"" + selected + "\") ORDER BY Fecha ASC; ";
            try {
                st = con.createStatement();
                ResultSet rs = st.executeQuery(sqlMensajes);
                while (rs.next()) {
                    o = rs.getString(1);
                    d = rs.getString(2);
                    String origen = "SELECT Nombre FROM Empleado WHERE Id_Empleado = " + o + ";";
                    String destino = "SELECT Nombre FROM Empleado WHERE Id_Empleado = " + d + ";";
                    ResultSet rs2 = st.executeQuery(origen);
                    ResultSet rs3 = st.executeQuery(destino);
                    rs2.next();
                    rs3.next();
                    if (rs2.getString(1).contains("Grupo")) {

                    } else {
                        if (rs.getString(7) != null) {
                            if (rs.getString(4).length() > 0) {
                                arrM.inserta("De: " + rs2.getString(1) + " Para: " + rs3.getString(1) + " (" + rs.getString(3) + ")" + rs.getString(4));
                                arrM.inserta("De: " + rs2.getString(1) + " Para: " + rs3.getString(1) + " (" + rs.getString(3) + ")" + rs.getString(7));
                            } else {
                                arrM.inserta("De: " + rs2.getString(1) + " Para: " + rs3.getString(1) + " (" + rs.getString(3) + ")" + rs.getString(7));
                            }
                        } else {
                            arrM.inserta("De: " + rs2.getString(1) + " Para: " + rs3.getString(1) + " (" + rs.getString(3) + ")" + rs.getString(4));
                        }
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            actualizaMensajes();
            arrM.vaciarArr();
        }
    }

    public void abrirArchivo(String archi) {
        byte[] b = null;
        st = con.createStatement();
        ResultSet rs4;
        try {
            rs4 = st.executeQuery("SELECT Ruta_Arch FROM bussinesscard.Mensaje WHERE NombreArch = \"" + archi + "\";");
            while (rs4.next()) {
                b = rs4.getBytes(1);
            }
            InputStream bos = new ByteArrayInputStream(b);

            int tamanoInput = bos.available();
            byte[] datosArch = new byte[tamanoInput];
            bos.read(datosArch, 0, tamanoInput);

            OutputStream out = new FileOutputStream("C:\\Users\\Marco\\Downloads\\" + archi);
            out.write(datosArch);
            JOptionPane.showMessageDialog(this, "Archivo descargado en Descargas");
            //abrir archivo
            out.close();
            bos.close();
        } catch (SQLException ex) {

        } catch (IOException ex) {
            Logger.getLogger(InterfEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        archivo = null;
        archivoRuta = "";
        archivoNombre = null;
    }

    public void actualizaMensajes() {
        if (arrM.vacio()) {
            arrM.inserta("");
        }
        listMensajes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = arrM.getArr();

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });
    }


    private void lbEnviarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbEnviarMouseClicked

        Calendar fecha = new GregorianCalendar();
        String ano = Integer.toString(fecha.get(Calendar.YEAR));
        String mes = Integer.toString(fecha.get(Calendar.MONTH) + 1);
        String dia = Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
        String hora = Integer.toString(fecha.get(Calendar.HOUR_OF_DAY));
        String minuto = Integer.toString(fecha.get(Calendar.MINUTE));
        String seg = Integer.toString(fecha.get(Calendar.SECOND));
        String fechaF = ano + "-" + mes + "-" + dia + "-" + hora + "-" + minuto + "-" + seg;
        if (archivoNombre != null || txtMensaje.getText().length() > 0) {
            try {
                if (b == 2) {
                    sqlContactos = "SELECT Id_Empleado FROM bussinesscard.empleado where Nombre LIKE \"Grupo " + selected + "\";";
                    String origen = "SELECT Id_Empleado FROM Empleado WHERE Nombre LIKE \"" + nombreU + "\";";
                    try {
                        ResultSet rs2 = st.executeQuery(origen);
                        rs2.next();
                        o = rs2.getString(1);
                        st = con.createStatement();
                        ResultSet rs = st.executeQuery(sqlContactos);
                        while (rs.next()) {
                            d = rs.getString(1);
                        }
                    } catch (SQLException ex) {

                    }
                } else {
                    String origen = "SELECT Id_Empleado FROM Empleado WHERE Nombre LIKE \"" + nombreU + "\";";
                    String destino = "SELECT Id_Empleado FROM Empleado WHERE Nombre LIKE \"" + selected + "\";";
                    ResultSet rs2 = st.executeQuery(origen);
                    ResultSet rs3 = st.executeQuery(destino);
                    rs2.next();
                    rs3.next();
                    o = rs2.getString(1);
                    d = rs3.getString(1);
                }
                File ruta = new File(archivoRuta);
                if (archivoRuta.trim().length() != 0) {
                    archivo = new byte[(int) ruta.length()];
                    InputStream input = new FileInputStream(ruta);
                    input.read(archivo);
                    input.close();
                }
                PreparedStatement pps = con.prepareStatement("Insert into `bussinesscard`.`mensaje` (`Id_Empleado_O`,`Id_Empleado_D`,`Fecha`,`Mensaje`,`Ruta_Arch`,`NombreArch`)"
                        + "Values (?,?,?,?,?,?)");
                pps.setString(1, o);
                pps.setString(2, d);
                pps.setString(3, fechaF);
                pps.setString(4, txtMensaje.getText());
                pps.setBytes(5, archivo);
                pps.setString(6, archivoNombre);
                pps.executeUpdate();
                txtMensaje.setText("");
                //JOptionPane.showMessageDialog(null, "Enviado!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ups! Algo a salido mal.", "Advertencia", JOptionPane.WARNING_MESSAGE);

                Logger
                        .getLogger(InterfAltaEmp.class
                                .getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InterfEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(InterfEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
            archivo = null;
            archivoRuta = "";
            archivoNombre = null;
        }
        archivo = null;
        archivoRuta = "";
        archivoNombre = null;

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

        InterfBajaEmpleado baja = new InterfBajaEmpleado(con, nombreU, getRol());
        baja.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_lbDarBajaMouseClicked

    private void lbAltaGrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAltaGrupoMouseClicked
        InterfAltaGrupos grupos = new InterfAltaGrupos(con, nombreU);
        grupos.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_lbAltaGrupoMouseClicked

    private void listGruposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listGruposMouseClicked
        actualizaGrupos();
        b = 2;
        selected = listGrupos.getSelectedValue();
        listMensajes.setVisible(true);
        lbM.setVisible(true);
        txtMensaje.setVisible(true);
        lbEnviar.setVisible(true);
        lbArchivp.setVisible(true);
        lbNombreChat.setVisible(true);
        grupos();
    }//GEN-LAST:event_listGruposMouseClicked

    private void lbCerrarSesionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbCerrarSesionKeyPressed

    }//GEN-LAST:event_lbCerrarSesionKeyPressed

    private void lbCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCerrarSesionMouseClicked
//        nombreU = "";
//        rol = "";
        Login log = new Login();
        log.setVisible(true);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_lbCerrarSesionMouseClicked

    private void lbEmergenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbEmergenciaMouseClicked

        InterfAuditorias aud = new InterfAuditorias(con, nombreU);
        aud.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_lbEmergenciaMouseClicked

    private void lbArchivpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbArchivpMouseClicked
        seleccionarArchivo();
    }//GEN-LAST:event_lbArchivpMouseClicked

    private void listMensajesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMensajesMouseClicked
        String archBien = "", archReves = "";
        try {
            char chararr[] = listMensajes.getSelectedValue().toCharArray();
            for (int i = chararr.length - 1; i >= 0; i--) {
                if (chararr[i] != ')') {
                    archReves += chararr[i];
                } else {
                    break;
                }
            }
            for (int i = archReves.length() - 1; i >= 0; i--) {
                archBien += archReves.charAt(i);
            }
            System.out.println(archBien);
            abrirArchivo(archBien);
        } catch (NullPointerException ex) {
            System.out.println(ex);
        }
        archivo = null;
        archivoRuta = "";
    }//GEN-LAST:event_listMensajesMouseClicked

    private void txtMensajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMensajeKeyTyped
        if (txtMensaje.getText().length() >= 700) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMensajeKeyTyped

    private void perfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perfilMouseClicked

    }//GEN-LAST:event_perfilMouseClicked

    private void perfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilActionPerformed
        InterfVerPerfil perfil = new InterfVerPerfil(con, nombreU, rol, selected);
        perfil.setVisible(true);
    }//GEN-LAST:event_perfilActionPerformed
    private void grupos() {
        if (selected != null) {
            lbNombreChat.setText("Estas en chat grupal " + selected);
            try {
                //Obtenemos el Id del Owner del grupo en el que estamos seleccionados actualmente
                sqlMensajes = "SELECT Id_Empleado FROM bussinesscard.Empleado WHERE Nombre LIKE "
                        + "\"Grupo " + selected + "\";";
                st = con.createStatement();
                ResultSet rs = st.executeQuery(sqlMensajes);
                rs.next();
                d = rs.getString(1);
                //Preguntamos por todos los mensajes que se hallan mandado a este owner, los cuales pertenecen a ese grupo
                sqlMensajes = "SELECT Id_Empleado_O,Id_Empleado_D, Fecha, Mensaje, Ruta_Arch , Nombre, NombreArch  FROM bussinesscard.Mensaje"
                        + " INNER JOIN bussinesscard.Empleado on Empleado.Id_Empleado = Mensaje.Id_Empleado_D WHERE Id_Empleado_D"
                        + " =  " + d + " ORDER BY Fecha ASC; ";
                st = con.createStatement();
                rs = st.executeQuery(sqlMensajes);
                //A cada mensaje le sacamos el nombre de origen y concatenamos a la lista de mensajes
                while (rs.next()) {
                    o = rs.getString(1);
                    String origen = "SELECT Nombre FROM Empleado WHERE Id_Empleado = " + o + ";";
                    ResultSet rs2 = st.executeQuery(origen);
                    rs2.next();
                    if (rs.getString(7) != null) {
                        if (rs.getString(4).length() > 0) {
                            arrG.inserta("De: " + rs2.getString(1) + " Para: (Todos) " + " (" + rs.getString(3) + ")" + rs.getString(4));
                            arrG.inserta("De: " + rs2.getString(1) + " Para: (Todos) " + " (" + rs.getString(3) + ")" + rs.getString(7));
                        } else {
                            arrG.inserta("De: " + rs2.getString(1) + " Para: (Todos) " + " (" + rs.getString(3) + ")" + rs.getString(7));
                        }
                    } else {
                        arrG.inserta("De: " + rs2.getString(1) + " Para: (Todos) " + " (" + rs.getString(3) + ")" + rs.getString(4));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            actualizaGrupos();
            arrG.vaciarArr();
        }
    }

    public void actualizaGrupos() {
        if (arrG.vacio()) {
            arrG.inserta("");
        }
        listMensajes.setModel(new javax.swing.AbstractListModel<String>() {
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
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfEmpleados(con, nombreU, getRol(), grupo).setVisible(true);
            }
        });
    }
    private prbpackage.MyList myList = new prbpackage.MyList();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbAltaGrupo;
    private javax.swing.JLabel lbArchivp;
    private javax.swing.JLabel lbCerrarSesion;
    private javax.swing.JLabel lbDarBaja;
    private javax.swing.JLabel lbEmergencia;
    private javax.swing.JLabel lbEnviar;
    private javax.swing.JLabel lbGrupos;
    private javax.swing.JLabel lbM;
    private javax.swing.JLabel lbMsjBienvenida;
    private javax.swing.JLabel lbNombreChat;
    private javax.swing.JLabel lbRecargar;
    private javax.swing.JLabel lbRegistrar;
    private javax.swing.JLabel lbRol;
    private javax.swing.JList<String> listContactos;
    private javax.swing.JList<String> listGrupos;
    private javax.swing.JList<String> listMensajes;
    private javax.swing.JMenuItem perfil;
    private javax.swing.JPanel pnContactos;
    private javax.swing.JPanel pnContenedor;
    private javax.swing.JPanel pnHead;
    private javax.swing.JPanel pnMensajes;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JLabel txtEncabezado;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the rol
     */
    public static String getRol() {
        return rol;
    }

    private void seleccionarArchivo() {
        JFileChooser arch = new JFileChooser();
        FileNameExtensionFilter fi = new FileNameExtensionFilter("Archivos", "pdf", "docx", "pptx", "xlsx");
        arch.setFileFilter(fi);
        int se = arch.showOpenDialog(this);
        if (se == 0) {
            archivoRuta = arch.getSelectedFile().getAbsolutePath();
            archivoNombre = arch.getSelectedFile().getName();
        } else {
        }
    }

}
