package inventariobombero.Model.DAO;

import inventariobombero.Model.Entities.Cuartel;
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
public class CuartelDAO {
    BDConexion conexion = new BDConexion();
    private Cuartel cuartel;

    public CuartelDAO(Cuartel cuartel) {
        this.cuartel = cuartel;
    }
    
    public boolean crear(){
       Connection con = conexion.getConnection();
        boolean resp = false;

        try {
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement("INSERT INTO cuartel(id_Cuartel,nombre,cuartel,id_Departamento,estado) VALUES (null,?,?,?,?)");
            pst.setString(1, cuartel.getNombre());
            pst.setString(2, cuartel.getCuartel());
            pst.setInt(3, cuartel.getDepBombero().getId_DepBombero());
            pst.setString(4, "A");
            
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
            PreparedStatement pst = con.prepareStatement("UPDATE cuartel SET nombre=?,cuartel=?,id_Departamento=? WHERE id_Cuartel=?");
            pst.setString(1, cuartel.getNombre());
            pst.setString(2, cuartel.getCuartel());
            pst.setInt(3, cuartel.getDepBombero().getId_DepBombero());
            pst.setInt(4, cuartel.getId_Cuartel());
            
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
            PreparedStatement pst = con.prepareStatement("UPDATE cuartel estado=? WHERE id_Cuartel=?");
            pst.setString(1, "I");
            pst.setInt(2, cuartel.getId_Cuartel());
            
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
            String Titulos[]={"Id","Nombre","Cuartel","Departamento"};
            listado = new DefaultTableModel(null,Titulos);   
            String [] fila= new String[4];
            
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT id_Cuartel,nombre,cuartel,id_Departamento FROM cuartel WHERE estado='A'");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                fila[0] = String.valueOf(rs.getInt("id_Cuartel"));
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("cuartel");
                fila[3] = String.valueOf(rs.getInt("id_Departamento"));
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
            PreparedStatement pst = con.prepareStatement("SELECT id_Cuartel,cuartel FROM cuartel WHERE estado='A'");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Cuartel cuartel = new Cuartel();
                cuartel.setId_Cuartel(rs.getInt("id_Cuartel"));
                cuartel.setCuartel(rs.getString("cuartel"));
                listado.addElement(cuartel);
            }   
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
          return listado;
    }
    
}
