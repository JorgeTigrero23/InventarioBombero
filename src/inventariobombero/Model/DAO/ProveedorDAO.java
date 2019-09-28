package inventariobombero.Model.DAO;

import inventariobombero.Model.Entities.Proveedor;
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
public class ProveedorDAO {
    BDConexion conexion = new BDConexion();
    private Proveedor proveedor;

    public ProveedorDAO(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    public boolean crear(){
        Connection con = conexion.getConnection();
        boolean resp = false;

        try {
            con.setAutoCommit(false);            
            PreparedStatement pst = con.prepareStatement("INSERT INTO proveedor(id_Proveedor,descripcion,estado) VALUES (null,?,?)");
            pst.setString(1, proveedor.getDescripcion());
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
            PreparedStatement pst = con.prepareStatement("UPDATE proveedor SET descripcion=? WHERE id_Proveedor=?");
            pst.setString(1, proveedor.getDescripcion());
            pst.setInt(2, proveedor.getId_Proveedor());
            
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
            PreparedStatement pst = con.prepareStatement("UPDATE proveedor SET estado=? WHERE id_Proveedor=?");
            pst.setString(1, "I");
            pst.setInt(2, proveedor.getId_Proveedor());
            
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
            PreparedStatement pst = con.prepareStatement("SELECT id_Proveedor,descripcion FROM proveedor WHERE estado='A'");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                fila[0] = String.valueOf(rs.getInt("id_Proveedor"));
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
            PreparedStatement pst = con.prepareStatement("SELECT id_Proveedor,descripcion FROM proveedor WHERE estado='A'");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Proveedor prov = new Proveedor();
                prov.setId_Proveedor(rs.getInt("id_Proveedor"));
                prov.setDescripcion(rs.getString("descripcion"));
                listado.addElement(prov);
            }   
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
          return listado;
    }
    
    
}
