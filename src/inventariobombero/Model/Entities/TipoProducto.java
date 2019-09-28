package inventariobombero.Model.Entities;

import inventariobombero.Model.DAO.MarcaDAO;
import inventariobombero.Model.DAO.TipoProductoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class TipoProducto {
    private int id_TipoProducto;
    private String descripcion;
    private String estado;

    public int getId_TipoProducto() {
        return id_TipoProducto;
    }

    public void setId_TipoProducto(int id_TipoProducto) {
        this.id_TipoProducto = id_TipoProducto;
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
        TipoProductoDAO dao = new TipoProductoDAO(this);
        return dao.crear();
    }
    
    public boolean modificar(){
        TipoProductoDAO dao = new TipoProductoDAO(this);
        return dao.modificar();
    }
    
    public boolean eliminar(){
        TipoProductoDAO dao = new TipoProductoDAO(this);
        return dao.eliminar();
    }
    
    public DefaultTableModel lista (){
        TipoProductoDAO dao = new TipoProductoDAO(this);
        return dao.leer();
    }
    
     public DefaultComboBoxModel combo() throws SQLException{
        TipoProductoDAO dao = new TipoProductoDAO(this);
        return dao.listarCombo();
    }

     
    @Override
    public String toString() {
        return descripcion;
    }
        
     
     
}
