package inventariobombero.Model.Entities;

import inventariobombero.Model.DAO.EstadoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class Estado {
    private int id_estado;
    private String descripcion;

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    public boolean crear(){
        EstadoDAO dao = new EstadoDAO(this);
        return dao.crear();
    }
    
    public boolean modificar(){
        EstadoDAO dao = new EstadoDAO(this);
        return dao.modificar();
    }
    
    public boolean eliminar(){
        EstadoDAO dao = new EstadoDAO(this);
        return dao.eliminar();
    }
    
    public DefaultTableModel lista (){
        EstadoDAO dao = new EstadoDAO(this);
        return dao.leer();
    }
    
    public DefaultComboBoxModel combo() throws SQLException{
        EstadoDAO dao = new EstadoDAO(this);
        return dao.listarCombo();
    }

     
    @Override
    public String toString() {
        return descripcion;
    }
    
}
