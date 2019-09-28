package inventariobombero.Model.DAO;

import inventariobombero.Model.Entities.TipoProducto;
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
public class TipoProductoDAO {
    BDConexion conexion = new BDConexion();
    private TipoProducto tipo;

    public TipoProductoDAO(TipoProducto tipo) {
        this.tipo = tipo;
    }
    
    public boolean crear(){
        Connection con = conexion.getConnection();
        boolean resp = false;

        try {
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement("INSERT INTO tipo_producto(id_TipoProducto,descripcion,estado) VALUES (null,?,?)");
            pst.setString(1, tipo.getDescripcion());
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
            PreparedStatement pst = con.prepareStatement("UPDATE tipo_producto SET descripcion=? WHERE id_TipoProducto=?");
            pst.setString(1, tipo.getDescripcion());
            pst.setInt(2, tipo.getId_TipoProducto());
            
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
            PreparedStatement pst = con.prepareStatement("UPDATE tipo_producto SET estado=? WHERE id_TipoProducto=?");
            pst.setString(1, "I");
            pst.setInt(2, tipo.getId_TipoProducto());
            
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
            PreparedStatement pst = con.prepareStatement("SELECT id_TipoProducto,descripcion FROM tipo_producto WHERE estado='A'");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                fila[0] = String.valueOf(rs.getInt("id_TipoProducto"));
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
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT id_TipoProducto,descripcion FROM tipo_producto WHERE estado='A'");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                TipoProducto tipo = new TipoProducto();
                tipo.setId_TipoProducto(rs.getInt("id_TipoProducto"));
                tipo.setDescripcion(rs.getString("descripcion"));
                listado.addElement(tipo);
            }   
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
          return listado;
    }
   
}
