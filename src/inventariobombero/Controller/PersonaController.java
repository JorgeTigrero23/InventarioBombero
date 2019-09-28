package inventariobombero.Controller;

import inventariobombero.Model.Entities.Persona;
import inventariobombero.Model.Entities.TipoPersona;
import inventariobombero.View.formPersona;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class PersonaController implements ActionListener, KeyListener, MouseListener{
    private int accion=1;
    formPersona frm = new formPersona();
    
    public PersonaController(formPersona frm) throws SQLException {
        this.frm = frm;
        this.frm.btn_Guardar.addActionListener(this);
        this.frm.btn_Editar.addActionListener(this);
        this.frm.btn_Eliminar.addActionListener(this);
        this.frm.btn_Nuevo.addActionListener(this);
        //frmp.contenedor.add(frm);
        this.frm.txt_Id.setVisible(false);
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
            limpiar();
            habiltar();
            this.frm.btn_Guardar.setEnabled(true);
            this.frm.txt_Cedula.requestFocusInWindow();
        }else if(op.equalsIgnoreCase("EDITAR")){
            accion=2;
            habiltar();
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
        int fila =frm.table_Persona.getSelectedRow();
          if(fila>=0){
            this.frm.txt_Id.setText(frm.table_Persona.getValueAt(fila, 0).toString());
            this.frm.txt_Cedula.setText(frm.table_Persona.getValueAt(fila, 1).toString());
            this.frm.txt_Nombre.setText(frm.table_Persona.getValueAt(fila, 2).toString());
            this.frm.txt_Apellido.setText(frm.table_Persona.getValueAt(fila, 3).toString());
            this.frm.date_fechaNacimiento.setDate(Date.valueOf(frm.table_Persona.getValueAt(fila, 4).toString()));
            this.frm.cmb_Rango.setSelectedIndex(Integer.parseInt(frm.table_Persona.getValueAt(fila, 5).toString()));
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
            if(this.frm.txt_Cedula.getText().isEmpty() && this.frm.txt_Nombre.getText().isEmpty() && this.frm.txt_Apellido.getText().isEmpty()
                    && !this.frm.cmb_Rango.getSelectedItem().equals("-Seleccione-")){
                JOptionPane.showMessageDialog(null,"Campos obligatorios","ALERTA",JOptionPane.WARNING_MESSAGE);
            }else{
                Persona persona = new Persona();
                persona.setCedula(this.frm.txt_Cedula.getText());
                persona.setNombre(this.frm.txt_Nombre.getText());
                persona.setApellido(this.frm.txt_Apellido.getText());
                persona.setFecha_nacimiento(this.frm.date_fechaNacimiento.getDate());
                TipoPersona tipo = new TipoPersona();
                tipo.setId_TipoPersona(this.frm.cmb_Rango.getSelectedIndex());
                persona.setTipo_persona(tipo);
                
                if(persona.crear()){
                    JOptionPane.showMessageDialog(null,"Datos guardados con exito","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    inhabilitar();
                    llenar_tabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Error al almacenar los datos","ALERTA",JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if(accion==2){
            if(this.frm.txt_Id.getText().isEmpty() && this.frm.txt_Cedula.getText().isEmpty() && this.frm.txt_Nombre.getText().isEmpty() && this.frm.txt_Apellido.getText().isEmpty()
                    && !this.frm.cmb_Rango.getSelectedItem().equals("-Seleccione-")){
                JOptionPane.showMessageDialog(null,"Seleccione un item de la lista.","ALERTA",JOptionPane.WARNING_MESSAGE);
            }else{
                Persona persona = new Persona();
                persona.setId_Persona(Integer.parseInt(this.frm.txt_Id.getText()));
                persona.setCedula(this.frm.txt_Cedula.getText());
                persona.setNombre(this.frm.txt_Nombre.getText());
                persona.setApellido(this.frm.txt_Apellido.getText());
                persona.setFecha_nacimiento(this.frm.date_fechaNacimiento.getDate());
                TipoPersona tipo = new TipoPersona();
                tipo.setId_TipoPersona(this.frm.cmb_Rango.getSelectedIndex());
                persona.setTipo_persona(tipo);
                
                if(persona.modificar()){
                    JOptionPane.showMessageDialog(null,"Datos guardados con exito","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    inhabilitar();
                    llenar_tabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Error al almacenar los datos","ALERTA",JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if(accion==3){
            if(this.frm.txt_Id.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleccione un item de la lista.","ALERTA",JOptionPane.WARNING_MESSAGE);
            }else{
                Persona persona = new Persona();
                persona.setId_Persona(Integer.parseInt(this.frm.txt_Id.getText()));
                if(persona.eliminar()){
                    JOptionPane.showMessageDialog(null,"Datos eliminados con exito","ALERTA",JOptionPane.INFORMATION_MESSAGE);
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
            Persona persona = new Persona();
            DefaultTableModel listado;
     
            listado = persona.lista();
            System.out.println("aaa");
            frm.table_Persona.setModel(listado);
            
            //Ocultamoc columna
            frm.table_Persona.getColumnModel().getColumn(5).setMaxWidth(0);
            frm.table_Persona.getColumnModel().getColumn(5).setMinWidth(0);
            frm.table_Persona.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
            frm.table_Persona.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
            
            frm.table_Persona.getColumnModel().getColumn(0).setMaxWidth(50);
            frm.table_Persona.getColumnModel().getColumn(1).setMinWidth(65);
            frm.table_Persona.getColumnModel().getColumn(2).setMinWidth(120);
            frm.table_Persona.getColumnModel().getColumn(3).setMinWidth(120);
            frm.table_Persona.getColumnModel().getColumn(4).setMinWidth(80);
            frm.table_Persona.getColumnModel().getColumn(6).setMinWidth(70);            
            
        }catch(Exception e){
            System.out.println("Error: Llenar tabla Persona");
        }
    }
    
    private void llenar_combo() throws SQLException{
        
        try{
            TipoPersona tipo = new TipoPersona();
            DefaultComboBoxModel listado;
     
            listado = tipo.combo();
    
            frm.cmb_Rango.setModel(listado);
            
            frm.cmb_Rango.setSelectedIndex(0);
        }catch(Exception e){
            System.out.println("Error: Llenar combo Tipo Persona");
        }
    }
        
    private void habiltar(){
        this.frm.txt_Cedula.setEnabled(true);
        this.frm.txt_Nombre.setEnabled(true);
        this.frm.txt_Apellido.setEnabled(true);
        this.frm.cmb_Rango.setEnabled(true);
        this.frm.date_fechaNacimiento.setEnabled(true);
        
    }
    
    private void inhabilitar(){
        this.frm.txt_Cedula.setEnabled(false);
        this.frm.txt_Nombre.setEnabled(false);
        this.frm.txt_Apellido.setEnabled(false);
        this.frm.cmb_Rango.setEnabled(false);
        this.frm.btn_Editar.setEnabled(false);
        this.frm.btn_Guardar.setEnabled(false);
        this.frm.btn_Eliminar.setEnabled(false);
        this.frm.date_fechaNacimiento.setEnabled(false);
    }
    
    private void limpiar(){
        this.frm.txt_Cedula.setText("");
        this.frm.txt_Nombre.setText("");
        this.frm.txt_Apellido.setText("");
        this.frm.date_fechaNacimiento.setDate(null);
    }
    
}
