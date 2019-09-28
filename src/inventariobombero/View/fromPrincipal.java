package inventariobombero.View;

import inventariobombero.Controller.CuartelController;
import inventariobombero.Controller.EstadoController;
import inventariobombero.Controller.ListadoEquipoEntraController;
import inventariobombero.Controller.ListadoEquipoSaleController;
import inventariobombero.Controller.MarcaController;
import inventariobombero.Controller.ModeloController;
import inventariobombero.Controller.MovilController;
import inventariobombero.Controller.PersonaController;
import inventariobombero.Controller.PersonaCuartelController;
import inventariobombero.Controller.ProveedorController;
import inventariobombero.Controller.TipoPersonaController;
import inventariobombero.Controller.TipoProductoController;
import inventariobombero.Model.DAO.BDConexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Image;
import java.awt.Graphics;
import java.net.URL;
import java.sql.Connection;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class fromPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form fromPrincipal
     */
    public fromPrincipal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageIcon icon = new ImageIcon(getClass().getResource("/inventariobombero/Resources/img_portada.jpg"));
        Image imagen = icon.getImage();
        Image newimage = imagen.getScaledInstance(1400, 750, Image.SCALE_AREA_AVERAGING);
        contenedor = new javax.swing.JDesktopPane(){
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(newimage, 0, 0, this);
            }
        };
        menuBar = new javax.swing.JMenuBar();
        menu_Sistema = new javax.swing.JMenu();
        menu_ItemPersona = new javax.swing.JMenuItem();
        menu_ItemCambioPass = new javax.swing.JMenuItem();
        menu_ItemCerrar = new javax.swing.JMenuItem();
        menu_Datos = new javax.swing.JMenu();
        menu_ItemMarca = new javax.swing.JMenuItem();
        menu_ItemModelo = new javax.swing.JMenuItem();
        menu_ItemProveedor = new javax.swing.JMenuItem();
        menu_ItemEstado = new javax.swing.JMenuItem();
        menu_ItemCuartel = new javax.swing.JMenuItem();
        menu_ItemMovil = new javax.swing.JMenuItem();
        menu_ItemTipoEq = new javax.swing.JMenuItem();
        menu_ItemRango = new javax.swing.JMenuItem();
        menu_ItemPC = new javax.swing.JMenuItem();
        menu_Detalle = new javax.swing.JMenu();
        menu_ItemEquipoEntrada = new javax.swing.JMenuItem();
        menu_ItemEquipoSale = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menu_ItemListaProd = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menu_Sistema.setMnemonic('f');
        menu_Sistema.setText("Sistema");

        menu_ItemPersona.setMnemonic('o');
        menu_ItemPersona.setText("Personal");
        menu_ItemPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ItemPersonaActionPerformed(evt);
            }
        });
        menu_Sistema.add(menu_ItemPersona);

        menu_ItemCambioPass.setMnemonic('s');
        menu_ItemCambioPass.setText("Cambio contraseña");
        menu_Sistema.add(menu_ItemCambioPass);

        menu_ItemCerrar.setMnemonic('x');
        menu_ItemCerrar.setText("Cerrar Sesion");
        menu_ItemCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ItemCerrarActionPerformed(evt);
            }
        });
        menu_Sistema.add(menu_ItemCerrar);

        menuBar.add(menu_Sistema);

        menu_Datos.setMnemonic('e');
        menu_Datos.setText("Datos");

        menu_ItemMarca.setMnemonic('t');
        menu_ItemMarca.setText("Marca");
        menu_ItemMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ItemMarcaActionPerformed(evt);
            }
        });
        menu_Datos.add(menu_ItemMarca);

        menu_ItemModelo.setMnemonic('y');
        menu_ItemModelo.setText("Modelo");
        menu_ItemModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ItemModeloActionPerformed(evt);
            }
        });
        menu_Datos.add(menu_ItemModelo);

        menu_ItemProveedor.setMnemonic('p');
        menu_ItemProveedor.setText("Proveedor");
        menu_ItemProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ItemProveedorActionPerformed(evt);
            }
        });
        menu_Datos.add(menu_ItemProveedor);

        menu_ItemEstado.setMnemonic('d');
        menu_ItemEstado.setText("Estado");
        menu_ItemEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ItemEstadoActionPerformed(evt);
            }
        });
        menu_Datos.add(menu_ItemEstado);

        menu_ItemCuartel.setText("Cuartel");
        menu_ItemCuartel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ItemCuartelActionPerformed(evt);
            }
        });
        menu_Datos.add(menu_ItemCuartel);

        menu_ItemMovil.setText("Movil");
        menu_ItemMovil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ItemMovilActionPerformed(evt);
            }
        });
        menu_Datos.add(menu_ItemMovil);

        menu_ItemTipoEq.setText("Tipo Equipo");
        menu_ItemTipoEq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ItemTipoEqActionPerformed(evt);
            }
        });
        menu_Datos.add(menu_ItemTipoEq);

        menu_ItemRango.setText("Rango");
        menu_ItemRango.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ItemRangoActionPerformed(evt);
            }
        });
        menu_Datos.add(menu_ItemRango);

        menu_ItemPC.setText("Persona - Cuartel");
        menu_ItemPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ItemPCActionPerformed(evt);
            }
        });
        menu_Datos.add(menu_ItemPC);

        menuBar.add(menu_Datos);

        menu_Detalle.setMnemonic('h');
        menu_Detalle.setText("Detalles");

        menu_ItemEquipoEntrada.setMnemonic('c');
        menu_ItemEquipoEntrada.setText("Detalle Entrada");
        menu_ItemEquipoEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ItemEquipoEntradaActionPerformed(evt);
            }
        });
        menu_Detalle.add(menu_ItemEquipoEntrada);

        menu_ItemEquipoSale.setMnemonic('a');
        menu_ItemEquipoSale.setText("Detalle Salida");
        menu_ItemEquipoSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ItemEquipoSaleActionPerformed(evt);
            }
        });
        menu_Detalle.add(menu_ItemEquipoSale);

        menuBar.add(menu_Detalle);

        jMenu1.setText("Reportes");

        menu_ItemListaProd.setText("Lista de Producto");
        menu_ItemListaProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ItemListaProdActionPerformed(evt);
            }
        });
        jMenu1.add(menu_ItemListaProd);

        menuBar.add(jMenu1);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_ItemCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ItemCerrarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menu_ItemCerrarActionPerformed

    private void menu_ItemPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ItemPersonaActionPerformed
        // TODO add your handling code here:
        formPersona form  = new formPersona();
        PersonaController control;
        try {
            control = new PersonaController(form);
            contenedor.add(form);
            form.ConectarControlador(control);
        } catch (SQLException ex) {
            Logger.getLogger(fromPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_menu_ItemPersonaActionPerformed

    private void menu_ItemMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ItemMarcaActionPerformed
        // TODO add your handling code here:
        formMarca form  = new formMarca();
        MarcaController control = new MarcaController(form);
        contenedor.add(form);
        form.ConectarControlador(control);
    }//GEN-LAST:event_menu_ItemMarcaActionPerformed

    private void menu_ItemModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ItemModeloActionPerformed
        formModelo form = new formModelo();
        ModeloController control = new ModeloController(form);
        contenedor.add(form);
        form.ConectarControlador(control);
    }//GEN-LAST:event_menu_ItemModeloActionPerformed

    private void menu_ItemProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ItemProveedorActionPerformed
        formProveedor form = new formProveedor();
        ProveedorController control = new ProveedorController(form);
        contenedor.add(form);
        form.ConectarControlador(control);
    }//GEN-LAST:event_menu_ItemProveedorActionPerformed

    private void menu_ItemEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ItemEstadoActionPerformed
        formEstado form = new formEstado();
        EstadoController control = new EstadoController(form);
        contenedor.add(form);
        form.ConectarControlador(control);
    }//GEN-LAST:event_menu_ItemEstadoActionPerformed

    private void menu_ItemCuartelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ItemCuartelActionPerformed
        formCuartel form = new formCuartel();
        CuartelController control;
        try {
            control = new CuartelController(form);
            contenedor.add(form);
            form.ConectarControlador(control);
        } catch (SQLException ex) {
            Logger.getLogger(fromPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_menu_ItemCuartelActionPerformed

    private void menu_ItemMovilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ItemMovilActionPerformed
        formMovil form = new formMovil();
        MovilController control = new MovilController(form);
        contenedor.add(form);
        form.ConectarControlador(control);
    }//GEN-LAST:event_menu_ItemMovilActionPerformed

    private void menu_ItemTipoEqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ItemTipoEqActionPerformed
        formTipoProducto form = new formTipoProducto();
        TipoProductoController control = new TipoProductoController(form);
        contenedor.add(form);
        form.ConectarControlador(control);
    }//GEN-LAST:event_menu_ItemTipoEqActionPerformed

    private void menu_ItemRangoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ItemRangoActionPerformed
        // TODO add your handling code here:
        formTipoPersona form = new formTipoPersona();
        TipoPersonaController control = new TipoPersonaController(form);
        contenedor.add(form);
        form.ConectarControlador(control);
    }//GEN-LAST:event_menu_ItemRangoActionPerformed

    private void menu_ItemEquipoEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ItemEquipoEntradaActionPerformed
        // TODO add your handling code here:
        formListadoProdEntra form = new formListadoProdEntra();
        ListadoEquipoEntraController control;
        try {
            control = new ListadoEquipoEntraController(form);
            contenedor.add(form);
            form.ConectarControlador(control);
        } catch (SQLException ex) {
            Logger.getLogger(fromPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_menu_ItemEquipoEntradaActionPerformed

    private void menu_ItemEquipoSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ItemEquipoSaleActionPerformed
        // TODO add your handling code here:
        formListadoProdSale form = new formListadoProdSale();
        ListadoEquipoSaleController control;
        try {
            control = new ListadoEquipoSaleController(form);
            contenedor.add(form);
            form.ConectarControlador(control);
        } catch (SQLException ex) {
            Logger.getLogger(fromPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_menu_ItemEquipoSaleActionPerformed

    private void menu_ItemPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ItemPCActionPerformed
        // TODO add your handling code here:
        formPersonaCuartel form = new formPersonaCuartel();
        PersonaCuartelController control;
        try {
            control = new PersonaCuartelController(form);
            contenedor.add(form);
            form.ConectarControlador(control);
        } catch (SQLException ex) {
            Logger.getLogger(fromPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_menu_ItemPCActionPerformed

    private void menu_ItemListaProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ItemListaProdActionPerformed
        // TODO add your handling code here:
        formReportListaProd form = new formReportListaProd();
        contenedor.add(form);
        form.setVisible(true);
    }//GEN-LAST:event_menu_ItemListaProdActionPerformed

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
            java.util.logging.Logger.getLogger(fromPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fromPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fromPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fromPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fromPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDesktopPane contenedor;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar menuBar;
    public javax.swing.JMenu menu_Datos;
    public javax.swing.JMenu menu_Detalle;
    public javax.swing.JMenuItem menu_ItemCambioPass;
    public javax.swing.JMenuItem menu_ItemCerrar;
    public javax.swing.JMenuItem menu_ItemCuartel;
    public javax.swing.JMenuItem menu_ItemEquipoEntrada;
    public javax.swing.JMenuItem menu_ItemEquipoSale;
    public javax.swing.JMenuItem menu_ItemEstado;
    public javax.swing.JMenuItem menu_ItemListaProd;
    public javax.swing.JMenuItem menu_ItemMarca;
    public javax.swing.JMenuItem menu_ItemModelo;
    public javax.swing.JMenuItem menu_ItemMovil;
    public javax.swing.JMenuItem menu_ItemPC;
    public javax.swing.JMenuItem menu_ItemPersona;
    public javax.swing.JMenuItem menu_ItemProveedor;
    public javax.swing.JMenuItem menu_ItemRango;
    public javax.swing.JMenuItem menu_ItemTipoEq;
    private javax.swing.JMenu menu_Sistema;
    // End of variables declaration//GEN-END:variables

}
