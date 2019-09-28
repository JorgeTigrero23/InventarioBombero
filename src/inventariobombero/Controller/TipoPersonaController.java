package inventariobombero.Controller;

import inventariobombero.Model.Entities.TipoPersona;
import inventariobombero.View.formTipoPersona;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TipoPersonaController implements ActionListener, KeyListener, MouseListener{
    private int accion=1;
    formTipoPersona frm = new formTipoPersona();
    
    public TipoPersonaController(formTipoPersona frm) {
        this.frm = frm;
        this.frm.btn_Guardar.addActionListener(this);
        this.frm.btn_Editar.addActionListener(this);
        this.frm.btn_Eliminar.addActionListener(this);
        this.frm.btn_Nuevo.addActionListener(this);
        frm.setVisible(true);
        inhabilitar();
        llenar_tabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        if(op.equalsIgnoreCase("NUEVO")){
            accion =1;
            limpiar();
            habilitar();
            this.frm.btn_Guardar.setEnabled(true);
            this.frm.txt_Descripcion.requestFocusInWindow();
        }else if(op.equalsIgnoreCase("EDITAR")){
            habilitar();
            accion=2;
            this.frm.btn_Guardar.setEnabled(true);
            this.frm.btn_Eliminar.setEnabled(false);
            this.frm.btn_Editar.setEnabled(false);
            this.frm.txt_Descripcion.requestFocusInWindow();
        }else if(op.equalsIgnoreCase("GUARDAR")){
            Operacion();
        }else if(op.equalsIgnoreCase("ELIMINAR")){
            accion=3;
            Operacion();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila =frm.table_Rango.getSelectedRow();
          if(fila>=0){
            this.frm.txt_Id.setText(frm.table_Rango.getValueAt(fila, 0).toString());
            this.frm.txt_Descripcion.setText(frm.table_Rango.getValueAt(fila, 1).toString());
            inhabilitar();
            this.frm.btn_Editar.setEnabled(true);
            this.frm.btn_Eliminar.setEnabled(true);
          }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void Operacion(){
        if(accion==1){
            if(this.frm.txt_Descripcion.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Campos obligatorios","ALERTA",JOptionPane.WARNING_MESSAGE);
            }else{
                TipoPersona tipo = new TipoPersona();
                tipo.setDescripcion(this.frm.txt_Descripcion.getText());
                
                if(tipo.crear()){
                    JOptionPane.showMessageDialog(null,"Datos guardados con exito","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    inhabilitar();
                    llenar_tabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Error al almacenar los datos","ALERTA",JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if(accion==2){
            if(this.frm.txt_Id.getText().isEmpty() && this.frm.txt_Descripcion.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleccione un item de la lista.","ALERTA",JOptionPane.WARNING_MESSAGE);
            }else{
                TipoPersona tipo = new TipoPersona();
                tipo.setId_TipoPersona(Integer.parseInt(this.frm.txt_Id.getText()));
                tipo.setDescripcion(this.frm.txt_Descripcion.getText());
                
                if(tipo.modificar()){
                    JOptionPane.showMessageDialog(null,"Datos guardados con exito","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    inhabilitar();
                    llenar_tabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Error al almacenar los datos","ALERTA",JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if(accion==3){
            if(this.frm.txt_Id.getText().isEmpty() && this.frm.txt_Descripcion.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleccione un item de la lista.","ALERTA",JOptionPane.WARNING_MESSAGE);
            }else{
                TipoPersona tipo = new TipoPersona();
                tipo.setId_TipoPersona(Integer.parseInt(this.frm.txt_Id.getText()));
                
                if(tipo.eliminar()){
                    JOptionPane.showMessageDialog(null,"Datos eliminados con exito.","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    inhabilitar();
                    llenar_tabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Error al almacenar los datos.","ALERTA",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private void llenar_tabla(){
        
        try{
            TipoPersona tipo = new TipoPersona();
            DefaultTableModel listado;
     
            listado = tipo.lista();
            System.out.println("aaa");
            frm.table_Rango.setModel(listado);
        }catch(Exception e){
            System.out.println("Error: Llenar tabla Tipo Persona");
        }
    }
    
    private void habilitar(){
        this.frm.txt_Descripcion.setEnabled(true);
    }   
       
    private void inhabilitar(){
        this.frm.txt_Id.setEditable(false);
        this.frm.txt_Descripcion.setEnabled(false);
        this.frm.btn_Guardar.setEnabled(false);
        this.frm.btn_Editar.setEnabled(false);
        this.frm.btn_Eliminar.setEnabled(false);
    }
    
    private void limpiar(){
        this.frm.txt_Id.setText("");
        this.frm.txt_Descripcion.setText("");
    }
    
}
