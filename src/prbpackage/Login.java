package prbpackage;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

/**
 *
 *
 */
public class Login extends javax.swing.JFrame {

    private ImageIcon imagen;
    private Icon icono;
    private static Connection con;
    // Declaramos los datos de conexion a la bd
    private static final String driver = "org.mariadb.jdbc.Driver";
    private static final String user = "root";
    private static final String pass = "root";
    private static final String url = "jdbc:mariadb://prueba:3305/bussinesscard";

    String sql = "Select Id_Empleado,role.nombre, Apellidos,empleado.Nombre,Telefono,Usuario,Contraseña,Id_Grupo from bussinesscard.empleado "
            + "Inner Join bussinesscard.role on Empleado.Role=Role.Id_Role", nombreU = "", error = "", rol = "";
    Statement st;
    int i, grupo = -1;
    boolean mayusAct = false;

    public final void conector() {
        // Reseteamos a null la conexion a la bd
//        pnContenedor.add(lbSt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, -1, -1));
        error = "";
        con = null;
        try {
            Class.forName(driver);
            // Nos conectamos a la bd
            con = (Connection) DriverManager.getConnection(url, user, pass);
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (con != null) {
                lbSt.setText("Correcto");
                lbSt.setForeground(Color.getHSBColor(1, 243, 57));
            }
        } // Si la conexion NO fue exitosa mostramos un mensaje de error
        catch (ClassNotFoundException | SQLException e) {
            lbSt.setText("Incorrecto");
            error += e;
            lbSt.setForeground(Color.red);

        }
    }

    public Login() {
        initComponents();
        try {
            pintarImagen(txtLogo, "/imgspackage/BusinessCard.png");
            pintarImagen(lblIngresar, "/imgspackage/usuario.png");
            pintarImagen(txtCerrar, "/imgspackage/cerrar.png");
            pintarImagen(lbFondoA, "/imgspackage/FondoA.png");
        } catch (NullPointerException ex) {

        }
        lbErrorAux.setVisible(false);
        mayusAct = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        lbBlockM2.setVisible(false);
        if (mayusAct) {
            lbBlocM1.setVisible(true);
        } else {
            lbBlocM1.setVisible(false);
        }
        String c = obj.cargaC("Correo.dat");
        String co = obj.cargaC("Contra.dat");
        if (c != null) {
            txtUsuario.setText(""+c);
            txtContrasena.setText(""+co);
        }
//        System.out.println(""+arr[0]);
        conector();
    }
//txtLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgspackage/BusinessCard.png")));

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
        pnHead = new javax.swing.JPanel();
        txtEncabezado = new javax.swing.JLabel();
        txtCerrar = new javax.swing.JLabel();
        pnContacto = new javax.swing.JPanel();
        txtContac = new javax.swing.JLabel();
        lbTelefono = new javax.swing.JLabel();
        lbC = new javax.swing.JLabel();
        txtLogo = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        lbErrorAux = new javax.swing.JLabel();
        lbSt = new javax.swing.JLabel();
        pn1 = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        lblUsuario = new javax.swing.JLabel();
        lblContraseña = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        lblIngresar = new javax.swing.JLabel();
        txtIngresar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbBlocM1 = new javax.swing.JLabel();
        lbBlockM2 = new javax.swing.JLabel();
        cbSalvarContra = new javax.swing.JCheckBox();
        lbFondoA = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setUndecorated(true);
        setResizable(false);

        pnContenedor.setBackground(new java.awt.Color(255, 153, 51));
        pnContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnHead.setBackground(new java.awt.Color(255, 255, 255));
        pnHead.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnHead.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnHeadMouseDragged(evt);
            }
        });
        pnHead.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnHeadMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnHeadMousePressed(evt);
            }
        });

        txtEncabezado.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtEncabezado.setText("ENTERPRISE MANAGEMENT");

        txtCerrar.setBackground(new java.awt.Color(255, 255, 255));
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
                .addContainerGap()
                .addComponent(txtEncabezado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 309, Short.MAX_VALUE)
                .addComponent(txtCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnHeadLayout.setVerticalGroup(
            pnHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
            .addGroup(pnHeadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtCerrar.getAccessibleContext().setAccessibleName("txtcerrar");

        pnContenedor.add(pnHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 50));

        pnContacto.setBackground(new java.awt.Color(255, 255, 255));
        pnContacto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtContac.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtContac.setText("Contacto Tecnico");

        lbTelefono.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbTelefono.setText("Telefono Cel: +52 722 499 8939");

        lbC.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbC.setText("Correo: Dgarcesm@Toluca.Tecnm.mx");

        javax.swing.GroupLayout pnContactoLayout = new javax.swing.GroupLayout(pnContacto);
        pnContacto.setLayout(pnContactoLayout);
        pnContactoLayout.setHorizontalGroup(
            pnContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContactoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtContac, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnContactoLayout.createSequentialGroup()
                        .addComponent(lbTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(lbC)))
                .addContainerGap(164, Short.MAX_VALUE))
        );
        pnContactoLayout.setVerticalGroup(
            pnContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContactoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtContac)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnContactoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelefono)
                    .addComponent(lbC))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pnContenedor.add(pnContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 610, 50));
        pnContenedor.add(txtLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 210, 170));

        lbStatus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbStatus.setText("Estatus: ");
        pnContenedor.add(lbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, -1));

        lbErrorAux.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbErrorAux.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorAux.setText("Error");
        lbErrorAux.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbErrorAuxMouseEntered(evt);
            }
        });
        pnContenedor.add(lbErrorAux, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 450, 30));

        lbSt.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbSt.setText("C");
        lbSt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbStMouseEntered(evt);
            }
        });
        pnContenedor.add(lbSt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, -1, -1));

        pn1.setBackground(new java.awt.Color(255, 255, 255));
        pn1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pn1KeyPressed(evt);
            }
        });

        txtUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUsuarioMouseClicked(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });

        lblUsuario.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblUsuario.setText("Usuario:");

        lblContraseña.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblContraseña.setText("Contraseña:");

        txtContrasena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtContrasenaMouseClicked(evt);
            }
        });
        txtContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasenaActionPerformed(evt);
            }
        });
        txtContrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContrasenaKeyPressed(evt);
            }
        });

        lblIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIngresarMouseClicked(evt);
            }
        });

        txtIngresar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtIngresar.setText("Ingresar");
        txtIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIngresarMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Iniciar Sesión");

        lbBlocM1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbBlocM1.setForeground(new java.awt.Color(255, 0, 0));
        lbBlocM1.setText("Block Mayus Activado");

        lbBlockM2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbBlockM2.setForeground(new java.awt.Color(255, 0, 0));
        lbBlockM2.setText("Block Mayus Activado");

        cbSalvarContra.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbSalvarContra.setText("Salva Contraseña");
        cbSalvarContra.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout pn1Layout = new javax.swing.GroupLayout(pn1);
        pn1.setLayout(pn1Layout);
        pn1Layout.setHorizontalGroup(
            pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn1Layout.createSequentialGroup()
                .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pn1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuario)
                            .addComponent(lbBlockM2)
                            .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtContrasena, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lbBlocM1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblContraseña, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn1Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn1Layout.createSequentialGroup()
                                .addComponent(txtIngresar)
                                .addGap(119, 119, 119))
                            .addGroup(pn1Layout.createSequentialGroup()
                                .addComponent(lblIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbSalvarContra))))
                    .addGroup(pn1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn1Layout.setVerticalGroup(
            pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lbBlocM1)
                .addGap(12, 12, 12)
                .addComponent(lblContraseña)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBlockM2)
                .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(cbSalvarContra)))
                .addGap(0, 0, 0)
                .addComponent(txtIngresar)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pnContenedor.add(pn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 300, 310));
        pnContenedor.add(lbFondoA, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 610, 470));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIngresarMouseClicked

    }//GEN-LAST:event_txtIngresarMouseClicked

    private void txtContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaActionPerformed

    private void txtCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCerrarMouseClicked
        setVisible(false);
        System.exit(0);
    }//GEN-LAST:event_txtCerrarMouseClicked

    private void lblIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIngresarMouseClicked

//        InterfEmpleados interf = new InterfEmpleados(con, "Diego");
//            interf.setVisible(true);
//            this.setVisible(false);
        String correo = txtUsuario.getText();
        char[] arrayC = txtContrasena.getPassword();
        String contra = new String(arrayC);
        if (valida(correo, contra)) {
            if (cbSalvarContra.isSelected()) {
                obj.guardaC("Correo.dat", correo);
                obj.guardaC("Contra.dat", contra);
            }
            String aux = "SELECT Role FROM empleado WHERE Nombre LIKE \"" + nombreU + "\"";
            st = con.createStatement();
            ResultSet rs;
            try {
                rs = st.executeQuery(aux);
                rs.next();
                switch (rs.getString(1)) {
                    case "1":
                        rol = "Administrador";
                        break;
                    case "2":
                        rol="Moderador";
                        break;
                    case "3":
                        rol = "Empleado";
                        break;
                    case "5":
                        rol = "Grupo";
                        break;
                    default:
                        throw new AssertionError();
                }
            } catch (SQLException ex) {

            }
            InterfEmpleados interf = new InterfEmpleados(con, nombreU, rol, grupo);
            interf.setVisible(true);
            this.setVisible(false);
        } else if (i == 0) {
            JOptionPane.showMessageDialog(null, "No hay usuarios con acceso al sistema\nPor favor, contacte a un Administrador");
        } else {
            JOptionPane.showMessageDialog(null, "Compruebe sus datos ingresados\nSi cree que hay un error\nPor favor, contacte a un Administrador", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lblIngresarMouseClicked

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            if (txtUsuario.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor, Llene todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                if (mayusAct) {
                    lbBlocM1.setVisible(false);
                    lbBlockM2.setVisible(true);
                }
                txtContrasena.requestFocus();
            }
        } else if (evt.getKeyCode() == 20 && mayusAct) {
            lbBlocM1.setVisible(false);
            mayusAct = false;
        } else if (evt.getKeyCode() == 20 && !mayusAct) {
            lbBlocM1.setVisible(true);
            mayusAct = true;
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtContrasenaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContrasenaKeyPressed
        char[] arrayC = txtContrasena.getPassword();
        String contra = new String(arrayC);

        if (evt.getKeyCode() == 20 && mayusAct) {
            lbBlockM2.setVisible(false);
            mayusAct = false;
        } else if (evt.getKeyCode() == 20 && !mayusAct) {
            lbBlockM2.setVisible(true);
            mayusAct = true;
        } else if (contra.equals("")) {
            switch (evt.getExtendedKeyCode()) {
                case KeyEvent.VK_ENTER:
                    JOptionPane.showMessageDialog(null, "Por favor, Llene todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    if (mayusAct) {
                        lbBlocM1.setVisible(true);
                        lbBlockM2.setVisible(false);
                    }
                    txtUsuario.requestFocus();
                    break;
            }

        } else {
            if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
                lblIngresarMouseClicked(null);
            }
        }
    }//GEN-LAST:event_txtContrasenaKeyPressed

    private void lbStMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStMouseEntered

        if (!error.equals("")) {
            lbErrorAux.setText(error);
            lbErrorAux.setVisible(true);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_lbStMouseEntered

    private void lbErrorAuxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbErrorAuxMouseEntered
        lbErrorAux.setVisible(false);
    }//GEN-LAST:event_lbErrorAuxMouseEntered

    private void pnHeadMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnHeadMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_pnHeadMouseDragged

    private void pnHeadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnHeadMouseClicked

    }//GEN-LAST:event_pnHeadMouseClicked

    private void pnHeadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnHeadMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_pnHeadMousePressed

    private void pn1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pn1KeyPressed

    }//GEN-LAST:event_pn1KeyPressed

    private void txtUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioMouseClicked
        if (mayusAct) {
            lbBlocM1.setVisible(true);
            lbBlockM2.setVisible(false);
        }
    }//GEN-LAST:event_txtUsuarioMouseClicked

    private void txtContrasenaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtContrasenaMouseClicked
        if (mayusAct) {
            lbBlockM2.setVisible(true);
            lbBlocM1.setVisible(false);
        }
    }//GEN-LAST:event_txtContrasenaMouseClicked
    int xMouse, yMouse;

    public boolean valida(String cor, String cont) {
        i = 0;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(6).equals(cor)
                        && rs.getString(7).equals(cont)) {
                    nombreU = rs.getString(4);
                    rol = rs.getString(2);
                    try {
                        grupo = Integer.parseInt(rs.getString(8));
                    } catch (Exception ex) {

                    }
                    return true;
                }
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtUsuario.setText("");
        txtContrasena.setText("");
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    private prbpackage.GuardarContrasenia obj = new GuardarContrasenia();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbSalvarContra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbBlocM1;
    private javax.swing.JLabel lbBlockM2;
    private javax.swing.JLabel lbC;
    private javax.swing.JLabel lbErrorAux;
    private javax.swing.JLabel lbFondoA;
    private javax.swing.JLabel lbSt;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblIngresar;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pn1;
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
