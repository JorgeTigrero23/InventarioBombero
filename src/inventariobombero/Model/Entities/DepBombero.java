package inventariobombero.Model.Entities;

import inventariobombero.Model.DAO.DepBomberoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class DepBombero {
    private int id_DepBombero;
    private String provincia;
    private String canton;
    private String latitud;
    private String longitud;
    private String frecuencia;

    public int getId_DepBombero() {
        return id_DepBombero;
    }

    public void setId_DepBombero(int id_DepBombero) {
        this.id_DepBombero = id_DepBombero;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    public boolean crear(){
        DepBomberoDAO dao = new DepBomberoDAO(this);
        return dao.crear();
    }
    
    public boolean modificar(){
        DepBomberoDAO dao = new DepBomberoDAO(this);
        return dao.modificar();
    }
    
    public boolean eliminar(){
        DepBomberoDAO dao = new DepBomberoDAO(this);
        return dao.eliminar();
    }
    
    public ArrayList<DepBombero> lista (){
        DepBomberoDAO dao = new DepBomberoDAO(this);
        return dao.leer();
    }
    
    public DefaultComboBoxModel combo() throws SQLException{
        DepBomberoDAO dao = new DepBomberoDAO(this);
        return dao.listarCombo();
    }
    
}
