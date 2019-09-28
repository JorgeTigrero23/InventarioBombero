package inventariobombero.Model.Entities;

import inventariobombero.Model.DAO.ProveedorDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class Proveedor {
    private int id_Proveedor;
    private String descripcion;
    private String estado;

    public int getId_Proveedor() {
        return id_Proveedor;
    }

    public void setId_Proveedor(int id_Proveedor) {
        this.id_Proveedor = id_Proveedor;
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
        ProveedorDAO dao = new ProveedorDAO(this);
        return dao.crear();
    }
    
    public boolean modificar(){
        ProveedorDAO dao = new ProveedorDAO(this);
        return dao.modificar();
    }
    
    public boolean eliminar(){
        ProveedorDAO dao = new ProveedorDAO(this);
        return dao.eliminar();
    }
    
    public DefaultTableModel lista (){
        ProveedorDAO dao = new ProveedorDAO(this);
        return dao.leer();
    }
    
    public DefaultComboBoxModel combo() throws SQLException{
        ProveedorDAO dao = new ProveedorDAO(this);
        return dao.listarCombo();
    }

     
    @Override
    public String toString() {
        return descripcion;
    }
        
}
