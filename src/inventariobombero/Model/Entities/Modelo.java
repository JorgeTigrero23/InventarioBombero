/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariobombero.Model.Entities;

import inventariobombero.Model.DAO.ModeloDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class Modelo {
    private int id_Modelo;
    private String descripcion;
    private String estado;

    public int getId_Modelo() {
        return id_Modelo;
    }

    public void setId_Modelo(int id_Modelo) {
        this.id_Modelo = id_Modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
   public boolean crear(){
        ModeloDAO dao = new ModeloDAO(this);
        return dao.crear();
    }
    
    public boolean modificar(){
        ModeloDAO dao = new ModeloDAO(this);
        return dao.modificar();
    }
    
    public boolean eliminar(){
        ModeloDAO dao = new ModeloDAO(this);
        return dao.eliminar();
    }
    
    public DefaultTableModel lista (){
        ModeloDAO dao = new ModeloDAO(this);
        return dao.leer();
    }
    
    public DefaultComboBoxModel combo() throws SQLException{
        ModeloDAO dao = new ModeloDAO(this);
        return dao.listarCombo();
    }

     
    @Override
    public String toString() {
        return descripcion;
    }
    
}
