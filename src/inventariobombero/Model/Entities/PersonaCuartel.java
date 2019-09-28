package inventariobombero.Model.Entities;

import inventariobombero.Model.DAO.PersonaCuartelDAO;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class PersonaCuartel {
    private int id_PersCuartel;
    private Persona persona;
    private Cuartel cuartel;

    public int getId_PersCuartel() {
        return id_PersCuartel;
    }

    public void setId_PersCuartel(int id_PersCuartel) {
        this.id_PersCuartel = id_PersCuartel;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Cuartel getCuartel() {
        return cuartel;
    }

    public void setCuartel(Cuartel cuartel) {
        this.cuartel = cuartel;
    }
    
    public int obtener_id(){
        PersonaCuartelDAO dao = new PersonaCuartelDAO(this);
        return dao.obtener_id();
    }
    
    public boolean crear(){
        PersonaCuartelDAO dao = new PersonaCuartelDAO(this);
        return dao.crear();
    }
    
    public boolean modeificar(){
        PersonaCuartelDAO dao = new PersonaCuartelDAO(this);
        return dao.modificar();
    }
    
    public boolean eliminar(){
        PersonaCuartelDAO dao = new PersonaCuartelDAO(this);
        return dao.eliminar();
    }
    
     public DefaultTableModel lista (){
        PersonaCuartelDAO dao = new PersonaCuartelDAO(this);
        return dao.leer();
    }
     
    
     
}
