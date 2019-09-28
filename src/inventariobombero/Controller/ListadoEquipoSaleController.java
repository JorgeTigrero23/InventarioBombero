package inventariobombero.Controller;

import inventariobombero.Model.Entities.Cuartel;
import inventariobombero.Model.Entities.DetalleSalida;
import inventariobombero.Model.Entities.Movil;
import inventariobombero.Model.Entities.Persona;
import inventariobombero.Model.Entities.PersonaCuartel;
import inventariobombero.Model.Entities.Producto;
import inventariobombero.View.formListadoProdSale;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class ListadoEquipoSaleController implements ActionListener, KeyListener, MouseListener {
    private TableRowSorter trsFiltro;
    formListadoProdSale frm = new formListadoProdSale();

    public ListadoEquipoSaleController(formListadoProdSale frm) throws SQLException {
        this.frm=frm;
        this.frm.btn_Nuevo.addActionListener(this);
        this.frm.btn_Guardar.addActionListener(this);
        this.frm.txt_Buscar.addKeyListener(this);
        this.frm.cmb_Cuartel.addActionListener(this);
        this.frm.cmb_Persona.addActionListener(this);
        this.frm.cmb_Movil.addActionListener(this);
        this.frm.setVisible(true);  
         llenar_combo_Movil();
        llenar_combo_Cuartel();
        llenar_tabla();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) { 
        String op = e.getActionCommand();
        if(op.equalsIgnoreCase("GUARDAR")){
           operacion();
        }else if(e.getSource().equals(this.frm.cmb_Cuartel)){
            try {
                llenar_combo_Persona();
            } catch (SQLException ex) {
                Logger.getLogger(ListadoEquipoSaleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(e.getSource().equals(this.frm.cmb_Cuartel)){
            this.frm.txt_UbicacionDest.setText("");
            this.frm.txt_UbicacionDest.setText("Cuartel: " + this.frm.cmb_Cuartel.getSelectedItem().toString() +
                                               "Personal " + this.frm.cmb_Persona.getSelectedItem().toString() +
                                               "Movil: " + this.frm.cmb_Movil.getSelectedItem().toString());
        }else if(e.getSource().equals(this.frm.cmb_Persona)){
            this.frm.txt_UbicacionDest.setText("");
            this.frm.txt_UbicacionDest.setText("Cuartel: " + this.frm.cmb_Cuartel.getSelectedItem().toString() +
                                               "Personal " + this.frm.cmb_Persona.getSelectedItem().toString() +
                                               "Movil: " + this.frm.cmb_Movil.getSelectedItem().toString());
        }else if(e.getSource().equals(this.frm.cmb_Movil)){
            this.frm.txt_UbicacionDest.setText("");
            this.frm.txt_UbicacionDest.setText("Cuartel: " + this.frm.cmb_Cuartel.getSelectedItem().toString() +
                                               "Personal " + this.frm.cmb_Persona.getSelectedItem().toString() +
                                               "Movil: " + this.frm.cmb_Movil.getSelectedItem().toString());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        trsFiltro = new TableRowSorter(this.frm.table_ListaEquipos.getModel());
        this.frm.table_ListaEquipos.setRowSorter(trsFiltro);
        filtro();
    }

    @Override
    public void keyPressed(KeyEvent e) {
     
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila =frm.table_ListaEquipos.getSelectedRow();
          if(fila>=0){
            this.frm.txt_Id.setText(frm.table_ListaEquipos.getValueAt(fila, 0).toString());
            this.frm.txt_Codigo.setText(frm.table_ListaEquipos.getValueAt(fila, 1).toString());
            this.frm.txt_Descripcion.setText(frm.table_ListaEquipos.getValueAt(fila, 2).toString());
            this.frm.txt_Stock.setText(frm.table_ListaEquipos.getValueAt(fila, 8).toString());
            this.frm.txt_Ubicacion.setText(frm.table_ListaEquipos.getValueAt(fila, 10).toString());
            
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
    
    private void operacion(){
        PersonaCuartel pc = new PersonaCuartel();
        Cuartel c = new Cuartel();
        c = (Cuartel)this.frm.cmb_Cuartel.getSelectedItem();
        Persona p = new Persona();
        p =(Persona)this.frm.cmb_Persona.getSelectedItem();
        pc.setCuartel(c);
        pc.setPersona(p);
        int id = pc.obtener_id();
        pc.setId_PersCuartel(id);
        
        if(this.frm.txt_Descripcion.getText().isEmpty() && this.frm.txt_Codigo.getText().isEmpty() &&
           this.frm.txt_Cantidad.getText().isEmpty() && this.frm.txt_Ubicacion.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Campos obligatorios","ALERTA",JOptionPane.WARNING_MESSAGE);
        }else{
            Date fecha = new Date();
            DetalleSalida detalle  = new DetalleSalida();
            detalle.setCantidad(Integer.parseInt(this.frm.txt_Cantidad.getText()));
            detalle.setFecha_salida(fecha);
            detalle.setCantidad(Integer.parseInt(this.frm.txt_Cantidad.getText()));
            detalle.setDescripcion(this.frm.txt_Descripcion.getText());
            Movil m = new Movil();
            m = (Movil) this.frm.cmb_Movil.getSelectedItem();
            detalle.setMovil(m);
            detalle.setPers_cuartel(pc);
            detalle.setUbicacion(this.frm.txt_UbicacionDest.getText());
            Producto prod = new Producto();       
            prod.setId_Producto(Integer.parseInt(this.frm.txt_Id.getText()));
            detalle.setProducto(prod);
            
            if(detalle.crear()){
                 JOptionPane.showMessageDialog(null,"Datos guardados con exito","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                  limpiar();
                  llenar_tabla();
            }else{
                 JOptionPane.showMessageDialog(null,"Error al almacenar los datos","ALERTA",JOptionPane.ERROR_MESSAGE);
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
    
    
    private void llenar_combo_Movil(){
        try{
            Movil movil = new Movil();
            DefaultComboBoxModel listado;
     
            listado = movil.combo();
            
            for(int i=0;i< listado.getSize(); i++){
                movil = (Movil)listado.getElementAt(i);
                frm.cmb_Movil.addItem(movil);        
            }
            
        }catch(Exception e){
            System.out.println("Error: Llenar combo Movil.");
        }
    }
    
    private void llenar_combo_Persona() throws SQLException{
        
        try{
            Persona persona = new Persona();
            DefaultComboBoxModel listado;
            Cuartel cuartel = new Cuartel();
            cuartel = (Cuartel) this.frm.cmb_Cuartel.getSelectedItem();
            listado = persona.combo(cuartel.getId_Cuartel());
            
            for(int i=0;i< listado.getSize(); i++){
                persona = (Persona)listado.getElementAt(i);
                this.frm.cmb_Persona.addItem(persona);        
            }
            
        }catch(Exception e){
            System.out.println("Error: Llenar combo Marca.");
        }
    }
    
    private void llenar_combo_Cuartel(){
        try{
            Cuartel cuartel = new Cuartel();
            DefaultComboBoxModel listado;
     
            listado = cuartel.combo();
                        
            for(int i=0;i< listado.getSize(); i++){
                cuartel = (Cuartel)listado.getElementAt(i);
                frm.cmb_Cuartel.addItem(cuartel);  
            }
            
        }catch(Exception e){
            System.out.println("Error: Llenar combo Modelo.");
        }
    }
    private void limpiar(){
        this.frm.txt_Buscar.setText("");
        this.frm.txt_Cantidad.setText("");
        this.frm.txt_Id.setText("");
        this.frm.txt_Codigo.setText("");
        this.frm.txt_Descripcion.setText("");
        this.frm.txt_Ubicacion.setText("");
        this.frm.txt_UbicacionDest.setText("");
        this.frm.txt_Stock.setText("");
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
