package inventariobombero.Model.Entities;

import inventariobombero.Model.DAO.TipoPersonalDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class TipoPersona {
    private int id_TipoPersona;
    private String descripcion;
    private String estado;

    public TipoPersona() {
    }

    
    public TipoPersona(int id_TipoPersona, String descripcion) {
        this.id_TipoPersona = id_TipoPersona;
        this.descripcion = descripcion;
    }

    
    
    public int getId_TipoPersona() {
        return id_TipoPersona;
    }

    public void setId_TipoPersona(int id_TipoPersona) {
        this.id_TipoPersona = id_TipoPersona;
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
        TipoPersonalDAO dao = new TipoPersonalDAO(this);
        return dao.crear();
    }
    
    public boolean modificar(){
        TipoPersonalDAO dao = new TipoPersonalDAO(this);
        return dao.modificar();
    }
    
    public boolean eliminar(){
        TipoPersonalDAO dao = new TipoPersonalDAO(this);
        return dao.eliminar();
    }
    
    public DefaultTableModel lista (){
        TipoPersonalDAO dao = new TipoPersonalDAO(this);
        return dao.leer();
    }
    
    public DefaultComboBoxModel combo() throws SQLException{
        TipoPersonalDAO dao = new TipoPersonalDAO(this);
        return dao.listarCombo();
    }
    
}
