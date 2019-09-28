package inventariobombero.Model.Entities;

import inventariobombero.Model.DAO.MovilDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class Movil {
    private int id_Movil;
    private String descripcion;
    private String estado;

    public int getId_Movil() {
        return id_Movil;
    }

    public void setId_Movil(int id_Movil) {
        this.id_Movil = id_Movil;
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
        MovilDAO dao = new MovilDAO(this);
        return dao.crear();
    }
    
    public boolean modificar(){
        MovilDAO dao = new MovilDAO(this);
        return dao.modificar();
    }
    
    public boolean eliminar(){
        MovilDAO dao = new MovilDAO(this);
        return dao.eliminar();
    }
    
    public DefaultTableModel lista (){
        MovilDAO dao = new MovilDAO(this);
        return dao.leer();
    }
    
    public DefaultComboBoxModel combo() throws SQLException{
        MovilDAO dao = new MovilDAO(this);
        return dao.listarCombo();
    }
    
     @Override
    public String toString() {
        return descripcion;
    }
}
