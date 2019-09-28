package inventariobombero.Model.DAO;

import inventariobombero.Model.Entities.DepBombero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class DepBomberoDAO {
    BDConexion conexion = new BDConexion();
    private DepBombero departamento;

    public DepBomberoDAO(DepBombero departamento) {
        this.departamento = departamento;
    }
    
    public boolean crear(){
        boolean resp= false;
        
        return resp;
    }
    
    public boolean modificar(){
        boolean resp= false;
        
        return resp;
    }
    
    public boolean eliminar(){
        boolean resp= false;
        
        return resp;
    }
    
    public ArrayList<DepBombero> leer(){
        ArrayList listaCuartel = new ArrayList();
        return listaCuartel;
    }
    
    public DefaultComboBoxModel listarCombo() throws SQLException{
        DefaultComboBoxModel listado= new DefaultComboBoxModel();
        try {
            Vector model = new Vector();
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT id_DepBombero FROM dep_bombero");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                listado.insertElementAt(rs.getString("id_DepBombero"), 0);
            }   
            //listado.addElement(model);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
          return listado;
    }
       
}
