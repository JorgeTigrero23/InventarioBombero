/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariobombero.Model.Entities;

import inventariobombero.Model.DAO.MarcaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class Marca {
    private int id_Marca;
    private String descripcion;
    private String estado;

    public int getId_Marca() {
        return id_Marca;
    }

    public void setId_Marca(int id_Marca) {
        this.id_Marca = id_Marca;
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
        MarcaDAO dao = new MarcaDAO(this);
        return dao.crear();
    }
    
    public boolean modificar(){
        MarcaDAO dao = new MarcaDAO(this);
        return dao.modificar();
    }
    
    public boolean eliminar(){
        MarcaDAO dao = new MarcaDAO(this);
        return dao.eliminar();
    }
    
    public DefaultTableModel lista (){
        MarcaDAO dao = new MarcaDAO(this);
        return dao.leer();
    }
    
    public DefaultComboBoxModel combo() throws SQLException{
        MarcaDAO dao = new MarcaDAO(this);
        return dao.listarCombo();
    }

     
    @Override
    public String toString() {
        return descripcion;
    }
    
}
