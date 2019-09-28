package inventariobombero.Model.Entities;

import inventariobombero.Model.DAO.PersonaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class Persona {
    private int id_Persona;
    private String cedula;
    private String nombre;
    private String apellido;
    private Date fecha_nacimiento;
    private TipoPersona tipo_persona;
    private String estado;

    public int getId_Persona() {
        return id_Persona;
    }

    public void setId_Persona(int id_Persona) {
        this.id_Persona = id_Persona;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public TipoPersona getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(TipoPersona tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
      
    public boolean crear(){
        PersonaDAO dao = new PersonaDAO(this);
        return dao.crear();
    }
    
    public boolean modificar(){
        PersonaDAO dao = new PersonaDAO(this);
        return dao.modificar();
    }
    
    public boolean eliminar(){
        PersonaDAO dao = new PersonaDAO(this);
        return dao.eliminar();
    }
    
    public DefaultTableModel lista (){
        PersonaDAO dao = new PersonaDAO(this);
        return dao.leer();
    }
    
    public DefaultComboBoxModel combo(int id) throws SQLException{
        PersonaDAO dao = new PersonaDAO(this);
        return dao.listarCombo(id);
    }
    
    public DefaultComboBoxModel combo2() throws SQLException{
        PersonaDAO dao = new PersonaDAO(this);
        return dao.listarCombo2();
    }
    
    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
