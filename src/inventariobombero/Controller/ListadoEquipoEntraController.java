/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariobombero.Controller;

import inventariobombero.Model.Entities.DetalleEntrada;
import inventariobombero.Model.Entities.Estado;
import inventariobombero.Model.Entities.Marca;
import inventariobombero.Model.Entities.Modelo;
import inventariobombero.Model.Entities.Producto;
import inventariobombero.Model.Entities.Proveedor;
import inventariobombero.Model.Entities.TipoProducto;
import inventariobombero.View.formEquipoNuevo;
import inventariobombero.View.formListadoProdEntra;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.plaf.DesktopPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class ListadoEquipoEntraController implements ActionListener, KeyListener, MouseListener {
    private int accion=1;
    private TableRowSorter trsFiltro;
    formListadoProdEntra frm = new formListadoProdEntra();

    public ListadoEquipoEntraController(formListadoProdEntra frm) throws SQLException {
        this.frm = frm;
        this.frm.btn_Nuevo.addActionListener(this);
        this.frm.btn_Guardar.addActionListener(this);
        this.frm.txt_Buscar.addKeyListener(this);
        this.frm.txt_ValorUnitario.addKeyListener(this);
        this.frm.txt_Cantidad.addKeyListener(this);
        this.frm.setVisible(true);   
        limpiar();
        llenar_combo_TipoEquipo();
        llenar_combo_Proveedor();
        llenar_combo_Marca();
        llenar_combo_Modelo();
        llenar_combo_Estado();
        llenar_tabla();
        inhabilitar();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        if(op.equalsIgnoreCase("NUEVO")){
           accion=1;
           limpiar();
           habilitar();
        }else if(op.equalsIgnoreCase("GUARDAR")){
            Operacion();
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        if(e.getSource().equals(this.frm.txt_Cantidad)){
            if(!this.frm.txt_Cantidad.getText().isEmpty() && !this.frm.txt_ValorUnitario.getText().isEmpty()){
                Double suma = Integer.parseInt(this.frm.txt_Cantidad.getText()) * Double.valueOf(this.frm.txt_ValorUnitario.getText());
                this.frm.txt_ValorTotal.setText(String.valueOf(suma));
            }            
        }else if(e.getSource().equals(this.frm.txt_ValorUnitario)){
            if(!this.frm.txt_Cantidad.getText().isEmpty() && !this.frm.txt_ValorUnitario.getText().isEmpty()){
                Double suma = Integer.parseInt(this.frm.txt_Cantidad.getText()) * Double.valueOf(this.frm.txt_ValorUnitario.getText());
                this.frm.txt_ValorTotal.setText(String.valueOf(suma));
            }
        }else if(e.getSource().equals(this.frm.txt_Buscar)){
            trsFiltro = new TableRowSorter(this.frm.table_ListaEquipos.getModel());
            this.frm.table_ListaEquipos.setRowSorter(trsFiltro);
            filtro();
        }
        
        
        
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
         
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource().equals(this.frm.txt_Cantidad)){
            if(!this.frm.txt_Cantidad.getText().isEmpty() && !this.frm.txt_ValorUnitario.getText().isEmpty()){
                Double suma = Integer.parseInt(this.frm.txt_Cantidad.getText()) * Double.valueOf(this.frm.txt_ValorUnitario.getText());
                this.frm.txt_ValorTotal.setText(String.valueOf(suma));
            }            
        }else if(e.getSource().equals(this.frm.txt_ValorUnitario)){
            if(!this.frm.txt_Cantidad.getText().isEmpty() && !this.frm.txt_ValorUnitario.getText().isEmpty()){
                Double suma = Integer.parseInt(this.frm.txt_Cantidad.getText()) * Double.valueOf(this.frm.txt_ValorUnitario.getText());
                this.frm.txt_ValorTotal.setText(String.valueOf(suma));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila =frm.table_ListaEquipos.getSelectedRow();
          if(fila>=0){
            this.frm.txt_Id.setText(frm.table_ListaEquipos.getValueAt(fila, 0).toString());
            this.frm.txt_Codigo.setText(frm.table_ListaEquipos.getValueAt(fila, 1).toString());
            this.frm.txt_Descripcion.setText(frm.table_ListaEquipos.getValueAt(fila, 2).toString());
            this.frm.cmb_TipoEquipo.setSelectedItem(frm.table_ListaEquipos.getValueAt(fila, 3).toString());
            this.frm.cmb_Marca.setSelectedItem(frm.table_ListaEquipos.getValueAt(fila, 4).toString());
            this.frm.cmb_Modelo.setSelectedItem(frm.table_ListaEquipos.getValueAt(fila, 5).toString());
            this.frm.txt_ValorUnitario.setText(frm.table_ListaEquipos.getValueAt(fila, 6).toString());
            this.frm.cmb_Estado.setSelectedItem(frm.table_ListaEquipos.getValueAt(fila, 8).toString());
            this.frm.cmb_Proveedor.setSelectedItem(frm.table_ListaEquipos.getValueAt(fila, 9).toString());
            this.frm.txt_Ubicacion.setText(frm.table_ListaEquipos.getValueAt(fila, 10).toString());
            //this.frm.txt_ValorUnitario.setText(frm.table_ListaEquipos.getValueAt(fila, 3).toString());
            //this.frm.txt_ValorUnitario.setText(frm.table_ListaEquipos.getValueAt(fila, 3).toString());
            inhabilitar();
            accion=2;
          }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    
    private void Operacion(){
        Date fecha = new Date();
        if(accion==1){
            if(this.frm.txt_Descripcion.getText().isEmpty() && this.frm.txt_Codigo.getText().isEmpty() &&
               this.frm.txt_Cantidad.getText().isEmpty() && this.frm.txt_ValorUnitario.getText().isEmpty() && 
               this.frm.txt_ValorTotal.getText().isEmpty() && this.frm.txt_Ubicacion.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Campos obligatorios","ALERTA",JOptionPane.WARNING_MESSAGE);
            }else{
                
                Producto prod = new Producto();
                prod.setCodigo(this.frm.txt_Codigo.getText());
                prod.setDescripcion(this.frm.txt_Descripcion.getText());
                prod.setStock(0);
                prod.setValor_unitario(Double.valueOf(this.frm.txt_ValorUnitario.getText()));
                TipoProducto tipo = new TipoProducto();
                tipo = (TipoProducto) this.frm.cmb_TipoEquipo.getSelectedItem();
                prod.setTipo_producto(tipo);
                Marca marca = new Marca();
                marca = (Marca) this.frm.cmb_Marca.getSelectedItem();
                prod.setMarca(marca);
                Modelo modelo = new Modelo();
                modelo = (Modelo) this.frm.cmb_Modelo.getSelectedItem();
                prod.setModelo(modelo);
                Estado estado = new Estado();
                estado = (Estado) this.frm.cmb_Estado.getSelectedItem();
                prod.setEstado(estado);
                Proveedor prov = new Proveedor();
                prov = (Proveedor) this.frm.cmb_Proveedor.getSelectedItem();
                prov.setId_Proveedor(accion);
                prod.setProveedor(prov);
                prod.setUbicacion(this.frm.txt_Ubicacion.getText());
                
                DetalleEntrada detalle  = new DetalleEntrada();
                detalle.setCantidad(Integer.parseInt(this.frm.txt_Cantidad.getText()));
                detalle.setFecha_entrada(fecha);
                detalle.setPrecio_unitario(Double.valueOf(this.frm.txt_ValorUnitario.getText()));
                detalle.setValor_total(Double.valueOf(this.frm.txt_ValorTotal.getText()));
                detalle.setProducto(prod);
                
                if(detalle.crea_nuevo()){
                    JOptionPane.showMessageDialog(null,"Datos guardados con exito","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    inhabilitar();
                    llenar_tabla();
                }else{
                     JOptionPane.showMessageDialog(null,"Error al almacenar los datos","ALERTA",JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if(accion==2){
            if(this.frm.txt_Descripcion.getText().isEmpty() && this.frm.txt_Codigo.getText().isEmpty() &&
               this.frm.txt_Cantidad.getText().isEmpty() && this.frm.txt_ValorUnitario.getText().isEmpty() && 
               this.frm.txt_ValorTotal.getText().isEmpty() && this.frm.txt_Ubicacion.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Campos obligatorios","ALERTA",JOptionPane.WARNING_MESSAGE);
            }else{
                DetalleEntrada detalle  = new DetalleEntrada();
                detalle.setCantidad(Integer.parseInt(this.frm.txt_Cantidad.getText()));
                detalle.setFecha_entrada(fecha);
                detalle.setPrecio_unitario(Double.valueOf(this.frm.txt_ValorUnitario.getText()));
                detalle.setValor_total(Double.valueOf(this.frm.txt_ValorTotal.getText()));
                Producto prod = new Producto();
                prod.setId_Producto(Integer.parseInt(this.frm.txt_Id.getText()));
                detalle.setProducto(prod);
                
                if(detalle.crea_existente()){
                    JOptionPane.showMessageDialog(null,"Datos guardados con exito","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    inhabilitar();
                    llenar_tabla();
                }else{
                     JOptionPane.showMessageDialog(null,"Error al almacenar los datos","ALERTA",JOptionPane.ERROR_MESSAGE);
                }
            }
        
        }
    }
    
     private void llenar_tabla(){
        
        try{
            Producto producto = new Producto();
            DefaultTableModel listado;
     
            listado = producto.lista();
            frm.table_ListaEquipos.setModel(listado);
        }catch(Exception e){
            System.out.println("Error: Llenar tabla Equipo.");
        }
    }
    
    private void llenar_combo_TipoEquipo() throws SQLException{
        
        try{
            TipoProducto tipo = new TipoProducto();
            DefaultComboBoxModel listado;
     
            listado = tipo.combo();
            
            for(int i=0;i< listado.getSize(); i++){
                TipoProducto tipo1 = new TipoProducto();
                tipo1 = (TipoProducto)listado.getElementAt(i);
                frm.cmb_TipoEquipo.addItem(tipo1);        
            }
            
        }catch(Exception e){
            System.out.println("Error: Llenar combo Tipo Equipo");
        }
    }
    
    private void llenar_combo_Proveedor() throws SQLException{
        
        try{
            Proveedor proveedor = new Proveedor();
            DefaultComboBoxModel listado;
     
            listado = proveedor.combo();
            
            for(int i=0;i< listado.getSize(); i++){
                Proveedor prov = new Proveedor();
                prov = (Proveedor)listado.getElementAt(i);
                frm.cmb_Proveedor.addItem(prov);        
            }
            
        }catch(Exception e){
            System.out.println("Error: Llenar combo Proveedor.");
        }
    }
    
    
    private void llenar_combo_Marca() throws SQLException{
        
        try{
            Marca marca = new Marca();
            DefaultComboBoxModel listado;
     
            listado = marca.combo();
            
            for(int i=0;i< listado.getSize(); i++){
                Marca marc = new Marca();
                marc = (Marca)listado.getElementAt(i);
                frm.cmb_Marca.addItem(marc);        
            }
            
        }catch(Exception e){
            System.out.println("Error: Llenar combo Marca.");
        }
    }
    
    
    private void llenar_combo_Modelo() throws SQLException{
        
        try{
            Modelo modelo = new Modelo();
            DefaultComboBoxModel listado;
     
            listado = modelo.combo();
            
            for(int i=0;i< listado.getSize(); i++){
                modelo = (Modelo)listado.getElementAt(i);
                frm.cmb_Modelo.addItem(modelo);        
            }
            
        }catch(Exception e){
            System.out.println("Error: Llenar combo Modelo.");
        }
    }
    
    
    private void llenar_combo_Estado() throws SQLException{
        
        try{
            Estado estado = new Estado();
            DefaultComboBoxModel listado;
     
            listado = estado.combo();
            
            for(int i=0;i< listado.getSize(); i++){
                estado = (Estado)listado.getElementAt(i);
                frm.cmb_Estado.addItem(estado);        
            }
            
        }catch(Exception e){
            System.out.println("Error: Llenar combo Estado");
        }
    }
    
    private void limpiar(){
        this.frm.txt_Id.setText("");
        this.frm.txt_Codigo.setText("");
        this.frm.txt_Descripcion.setText("");
        this.frm.txt_Cantidad.setText("");
        this.frm.txt_ValorUnitario.setText("");
        this.frm.txt_ValorTotal.setText("");
        this.frm.txt_Ubicacion.setText("");
        
    }
    
    private void habilitar(){
        this.frm.txt_Id.setEnabled(true);
        this.frm.txt_Codigo.setEnabled(true);
        this.frm.txt_Descripcion.setEnabled(true);
    }
    
    private void inhabilitar(){
        this.frm.txt_Id.setEnabled(false);
        this.frm.txt_Codigo.setEnabled(false);
        this.frm.txt_Descripcion.setEnabled(false);
    }
    
    public void filtro() {
        int columnaABuscar = 1;
        if (frm.cmb_Buscar.getSelectedItem().toString().equalsIgnoreCase("Codigo")) {
            columnaABuscar = 1;
        }
        if (frm.cmb_Buscar.getSelectedItem().toString().equalsIgnoreCase("Tipo Producto")) {
            columnaABuscar = 3;
        }
        if (frm.cmb_Buscar.getSelectedItem().toString().equalsIgnoreCase("Marca")) {
            columnaABuscar = 4;
        }
        if (frm.cmb_Buscar.getSelectedItem().toString().equalsIgnoreCase("Modelo")) {
            columnaABuscar = 5;
        }
        trsFiltro.setRowFilter(RowFilter.regexFilter(frm.txt_Buscar.getText(), columnaABuscar));
    }
}
