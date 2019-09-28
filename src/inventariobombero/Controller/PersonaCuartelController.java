/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariobombero.Controller;

import inventariobombero.Model.Entities.Cuartel;
import inventariobombero.Model.Entities.Persona;
import inventariobombero.Model.Entities.PersonaCuartel;
import inventariobombero.View.formCuartel;
import inventariobombero.View.formPersonaCuartel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class PersonaCuartelController implements ActionListener, KeyListener, MouseListener {
     private int accion=1;
    formPersonaCuartel frm = new formPersonaCuartel();

    public PersonaCuartelController(formPersonaCuartel frm) throws SQLException {
        this.frm = frm;
        this.frm.btn_Guardar.addActionListener(this);
        this.frm.btn_Editar.addActionListener(this);
        this.frm.btn_Eliminar.addActionListener(this);
        this.frm.btn_Nuevo.addActionListener(this);
        frm.setVisible(true);
        inhabilitar();
        llenar_tabla();
        llenar_combo_Cuartel();
        llenar_combo_Persona();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = e.getActionCommand();
        if(op.equalsIgnoreCase("NUEVO")){
            accion =1;
            habilitar();
            limpiar();
            this.frm.btn_Guardar.setEnabled(true);
        }else if(op.equalsIgnoreCase("EDITAR")){
            accion=2;
            habilitar();
            this.frm.btn_Guardar.setEnabled(true);
            this.frm.btn_Eliminar.setEnabled(false);
            this.frm.btn_Editar.setEnabled(false);
            
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
          int fila =frm.table_PersCuartel.getSelectedRow();
          if(fila>=0){
            this.frm.txt_Id.setText(frm.table_PersCuartel.getValueAt(fila, 0).toString());
            this.frm.cmb_Cuartel.setSelectedItem(frm.table_PersCuartel.getValueAt(fila, 1).toString());
            this.frm.cmb_Persona.setSelectedItem(frm.table_PersCuartel.getValueAt(fila, 2).toString());
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
            PersonaCuartel pc = new PersonaCuartel();
            Cuartel c = new Cuartel();
            c = (Cuartel)this.frm.cmb_Cuartel.getSelectedItem();
            Persona p = new Persona();
            p =(Persona)this.frm.cmb_Persona.getSelectedItem();
            pc.setCuartel(c);
            pc.setPersona(p);
        
            if(pc.crear()){
                JOptionPane.showMessageDialog(null,"Datos guardados con exito","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                inhabilitar();
            }else{
                JOptionPane.showMessageDialog(null,"Error al almacenar los datos","ALERTA",JOptionPane.ERROR_MESSAGE);
            }
        }else if(accion==2){
            if(this.frm.txt_Id.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleccione un item de la lista.","ALERTA",JOptionPane.WARNING_MESSAGE);
            }else{
                PersonaCuartel pc = new PersonaCuartel();
                Cuartel c = new Cuartel();
                c = (Cuartel)this.frm.cmb_Cuartel.getSelectedItem();
                Persona p = new Persona();
                p =(Persona)this.frm.cmb_Persona.getSelectedItem();
                pc.setCuartel(c);
                pc.setPersona(p);
                pc.setId_PersCuartel(Integer.parseInt(this.frm.txt_Id.getText()));

                if(pc.modeificar()){
                    JOptionPane.showMessageDialog(null,"Datos guardados con exito","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    inhabilitar();
                }else{
                    JOptionPane.showMessageDialog(null,"Error al almacenar los datos","ALERTA",JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if(accion==3){
            if(this.frm.txt_Id.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleccione un item de la lista.","ALERTA",JOptionPane.WARNING_MESSAGE);
            }else{
                PersonaCuartel pc = new PersonaCuartel();
                pc.setId_PersCuartel(Integer.parseInt(this.frm.txt_Id.getText()));

                if(pc.eliminar()){
                    JOptionPane.showMessageDialog(null,"Datos guardados con exito","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    inhabilitar();
                }else{
                    JOptionPane.showMessageDialog(null,"Error al almacenar los datos","ALERTA",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private void llenar_tabla(){
        
        try{
            PersonaCuartel cuartel = new PersonaCuartel();
            DefaultTableModel listado;
     
            listado = cuartel.lista();
            frm.table_PersCuartel.setModel(listado);
        }catch(Exception e){
            System.out.println("Error: Llenar tabla Persona Cuartel.");
        }
    }
    
    private void llenar_combo_Persona() throws SQLException{
        
        try{
            Persona persona = new Persona();
            DefaultComboBoxModel listado;
            listado = persona.combo2();

            for(int i=0;i< listado.getSize(); i++){
                persona = (Persona)listado.getElementAt(i);
                this.frm.cmb_Persona.addItem(persona);        
            }
            
        }catch(Exception e){
            System.out.println("Error: Llenar combo Persona.");
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
    
    
     
    private void habilitar(){
        this.frm.cmb_Persona.setEnabled(true);
        this.frm.cmb_Cuartel.setEnabled(true);
        
    }
    
    private void inhabilitar(){
        this.frm.cmb_Persona.setEnabled(false);
        this.frm.cmb_Cuartel.setEnabled(false);
        this.frm.btn_Guardar.setEnabled(false);
        this.frm.btn_Editar.setEnabled(false);
        this.frm.btn_Eliminar.setEnabled(false);
    }
    
    private void limpiar(){
        this.frm.txt_Id.setText("");
    }
}
