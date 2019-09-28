package inventariobombero.Model.Entities;

import inventariobombero.Model.DAO.CuartelDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class Cuartel {
    private int id_Cuartel;
    private String nombre;
    private String cuartel;
    private String estado;
    private DepBombero depBombero;

    public int getId_Cuartel() {
        return id_Cuartel;
    }

    public void setId_Cuartel(int id_Cuartel) {
        this.id_Cuartel = id_Cuartel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuartel() {
        return cuartel;
    }

    public void setCuartel(String cuartel) {
        this.cuartel = cuartel;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public DepBombero getDepBombero() {
        return depBombero;
    }

    public void setDepBombero(DepBombero depBombero) {
        this.depBombero = depBombero;
    }
    
    public boolean crear(){
        CuartelDAO dao = new CuartelDAO(this);
        return dao.crear();
    }
    
    public boolean modificar(){
        CuartelDAO dao = new CuartelDAO(this);
        return dao.modificar();
    }
    
    public boolean eliminar(){
        CuartelDAO dao = new CuartelDAO(this);
        return dao.eliminar();
    }
    
    public DefaultTableModel lista (){
        CuartelDAO dao = new CuartelDAO(this);
        return dao.leer();
    }
            
    public DefaultComboBoxModel combo() throws SQLException{
        CuartelDAO dao = new CuartelDAO(this);
        return dao.listarCombo();
    }

     
    @Override
    public String toString() {
        return cuartel;
    }
}
