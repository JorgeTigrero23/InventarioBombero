package inventariobombero.Controller;

import inventariobombero.Model.Entities.Cuartel;
import inventariobombero.Model.Entities.DepBombero;
import inventariobombero.View.formCuartel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class CuartelController implements ActionListener, KeyListener, MouseListener {
    private int accion=1;
    formCuartel frm = new formCuartel();

    public CuartelController(formCuartel frm) throws SQLException  {
        this.frm = frm;
        this.frm.btn_Guardar.addActionListener(this);
        this.frm.btn_Editar.addActionListener(this);
        this.frm.btn_Eliminar.addActionListener(this);
        this.frm.btn_Nuevo.addActionListener(this);
        frm.setVisible(true);
        inhabilitar();
        llenar_tabla();
        llenar_combo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        if(op.equalsIgnoreCase("NUEVO")){
            accion =1;
            habilitar();
            limpiar();
            this.frm.btn_Guardar.setEnabled(true);
            this.frm.txt_Nombre.requestFocusInWindow();            
        }else if(op.equalsIgnoreCase("EDITAR")){
            accion=2;
            habilitar();
            this.frm.btn_Guardar.setEnabled(true);
            this.frm.btn_Eliminar.setEnabled(false);
            this.frm.btn_Editar.setEnabled(false);
            this.frm.txt_Nombre.requestFocusInWindow();
        }else if(op.equalsIgnoreCase("GUARDAR")){
            Operacion();
        }else if(op.equalsIgnoreCase("ELIMINAR")){
            accion=3;
            Operacion();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila =frm.table_Cuartel.getSelectedRow();
          if(fila>=0){
            this.frm.txt_Id.setText(frm.table_Cuartel.getValueAt(fila, 0).toString());
            this.frm.txt_Nombre.setText(frm.table_Cuartel.getValueAt(fila, 1).toString());
            this.frm.txt_Cuartel.setText(frm.table_Cuartel.getValueAt(fila, 2).toString());
            this.frm.cmb_Departamento.setSelectedItem(frm.table_Cuartel.getValueAt(fila, 3));
            inhabilitar();
            this.frm.btn_Editar.setEnabled(true);
            this.frm.btn_Eliminar.setEnabled(true);
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
        if(accion==1){
            if(this.frm.txt_Nombre.getText().isEmpty() && this.frm.txt_Cuartel.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Campos obligatorios","ALERTA",JOptionPane.WARNING_MESSAGE);
            }else{
                Cuartel cuartel = new Cuartel();
                DepBombero depBomb = new DepBombero();
                cuartel.setNombre(this.frm.txt_Nombre.getText());
                cuartel.setCuartel(this.frm.txt_Cuartel.getText());
                depBomb.setId_DepBombero(Integer.parseInt(this.frm.cmb_Departamento.getSelectedItem().toString()));
                cuartel.setDepBombero(depBomb);
                
                if(cuartel.crear()){
                    JOptionPane.showMessageDialog(null,"Datos guardados con exito","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    inhabilitar();
                    llenar_tabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Error al almacenar los datos","ALERTA",JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if(accion==2){
            if(this.frm.txt_Id.getText().isEmpty() && this.frm.txt_Nombre.getText().isEmpty() && this.frm.txt_Cuartel.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleccione un item de la lista.","ALERTA",JOptionPane.WARNING_MESSAGE);
            }else{
                Cuartel cuartel = new Cuartel();
                DepBombero depBomb = new DepBombero();
                cuartel.setId_Cuartel(Integer.parseInt(this.frm.txt_Id.getText()));
                cuartel.setNombre(this.frm.txt_Nombre.getText());
                cuartel.setCuartel(this.frm.txt_Cuartel.getText());
                depBomb.setId_DepBombero(Integer.parseInt(this.frm.cmb_Departamento.getSelectedItem().toString()));
                cuartel.setDepBombero(depBomb);
                
                if(cuartel.modificar()){
                    JOptionPane.showMessageDialog(null,"Datos guardados con exito","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    inhabilitar();
                    llenar_tabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Error al almacenar los datos","ALERTA",JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if(accion==3){
            if(this.frm.txt_Id.getText().isEmpty() && this.frm.txt_Nombre.getText().isEmpty() && this.frm.txt_Cuartel.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleccione un item de la lista.","ALERTA",JOptionPane.WARNING_MESSAGE);
            }else{
                Cuartel cuartel = new Cuartel();
                cuartel.setId_Cuartel(Integer.parseInt(this.frm.txt_Id.getText()));
                
                if(cuartel.eliminar()){
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
            Cuartel cuartel = new Cuartel();
            DefaultTableModel listado;
     
            listado = cuartel.lista();
            frm.table_Cuartel.setModel(listado);
        }catch(Exception e){
            System.out.println("Error: Llenar tabla Cuartel.");
        }
    }
     
    private void llenar_combo() throws SQLException{
        
        try{
            DepBombero departamento = new DepBombero();
            DefaultComboBoxModel listado;
     
            listado = departamento.combo();
            frm.cmb_Departamento.setModel(listado);            
            frm.cmb_Departamento.setSelectedIndex(0);
        }catch(Exception e){
            System.out.println("Error: Llenar combo Dep. Bombero.");
        }
    }
    
    private void habilitar(){
        this.frm.txt_Nombre.setEnabled(true);
        this.frm.txt_Cuartel.setEnabled(true);
        this.frm.cmb_Departamento.setEnabled(true);
    }
    
    private void inhabilitar(){
        this.frm.txt_Id.setEnabled(false);
        this.frm.txt_Nombre.setEnabled(false);
        this.frm.txt_Cuartel.setEnabled(false);
        this.frm.btn_Editar.setEnabled(false);
        this.frm.btn_Guardar.setEnabled(false);
        this.frm.btn_Eliminar.setEnabled(false);
        this.frm.cmb_Departamento.setEnabled(false);
    }
    
    private void limpiar(){
        this.frm.txt_Id.setText("");
        this.frm.txt_Nombre.setText("");
        this.frm.txt_Cuartel.setText("");
    }
}
