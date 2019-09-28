package inventariobombero.Model.DAO;

import inventariobombero.Model.Entities.Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class ModeloDAO {
    BDConexion conexion = new BDConexion();
    private Modelo modelo;

    public ModeloDAO(Modelo modelo) {
        this.modelo = modelo;
    }
    
    public boolean crear(){
        Connection con = conexion.getConnection();
        boolean resp = false;

        try {
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement("INSERT INTO modelo(id_Modelo,descripcion,estado) VALUES (null,?,?)");
            pst.setString(1, modelo.getDescripcion());
            pst.setString(2, "A");
            
            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                resp = true;
            }
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return resp;
    }
    
    public boolean modificar(){
        Connection con = conexion.getConnection();
        boolean resp = false;

        try {
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement("UPDATE modelo SET descripcion=? WHERE id_Modelo=?");
            pst.setString(1, modelo.getDescripcion());
            pst.setInt(2, modelo.getId_Modelo());
            
            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                resp = true;
            }
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return resp;
    }
    
    public boolean eliminar(){
        Connection con = conexion.getConnection();
        boolean resp = false;

        try {
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement("UPDATE modelo SET estado=? WHERE id_Modelo=?");
            pst.setString(1, "I");
            pst.setInt(2, modelo.getId_Modelo());
            
            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                resp = true;
            }
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return resp;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel listado=null;
        try {
            String Titulos[]={"Id","Descripcion"};
            listado = new DefaultTableModel(null,Titulos);   
            String [] fila= new String[2];
            
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT id_Modelo,descripcion FROM modelo WHERE estado='A'");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                fila[0] = String.valueOf(rs.getInt("id_Modelo"));
                fila[1] = rs.getString("descripcion");
                listado.addRow(fila);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return listado;
    }
    
    
    public DefaultComboBoxModel listarCombo() throws SQLException{
        DefaultComboBoxModel listado= new DefaultComboBoxModel();
        try {
            Vector model = new Vector();
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT id_Modelo,descripcion FROM modelo WHERE estado='A'");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Modelo modelo = new Modelo();
                modelo.setId_Modelo(rs.getInt("id_Modelo"));
                modelo.setDescripcion(rs.getString("descripcion"));
                //listado.insertElementAt(rs.getString("descripcion"), rs.getInt("id_TipoPersona"));
                listado.addElement(modelo);
            }   
            //listado.addElement(model);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
          return listado;
    }
    
}
