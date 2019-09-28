/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariobombero.Controller;

import inventariobombero.Model.Entities.Modelo;
import inventariobombero.View.formModelo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class ModeloController implements ActionListener, KeyListener, MouseListener{
    private int accion=1;
    formModelo frm = new formModelo();
    public ModeloController(formModelo frm) {
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
            accion=2;
            habilitar();
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
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila =frm.table_Modelo.getSelectedRow();
          if(fila>=0){
            this.frm.txt_Id.setText(frm.table_Modelo.getValueAt(fila, 0).toString());
            this.frm.txt_Descripcion.setText(frm.table_Modelo.getValueAt(fila, 1).toString());
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
            if(this.frm.txt_Descripcion.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Campos obligatorios","ALERTA",JOptionPane.WARNING_MESSAGE);
            }else{
                Modelo modelo = new Modelo();
                modelo.setDescripcion(this.frm.txt_Descripcion.getText());
                
                if(modelo.crear()){
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
                Modelo modelo = new Modelo();
                modelo.setId_Modelo(Integer.parseInt(this.frm.txt_Id.getText()));
                modelo.setDescripcion(this.frm.txt_Descripcion.getText());
                
                if(modelo.modificar()){
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
                Modelo modelo = new Modelo();
                modelo.setId_Modelo(Integer.parseInt(this.frm.txt_Id.getText()));
                
                if(modelo.eliminar()){
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
            Modelo modelo = new Modelo();
            DefaultTableModel listado;
     
            listado = modelo.lista();
            frm.table_Modelo.setModel(listado);
        }catch(Exception e){
            System.out.println("Error: Llenar tabla Marca.");
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
