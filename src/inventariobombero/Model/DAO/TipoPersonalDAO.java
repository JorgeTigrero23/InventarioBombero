package inventariobombero.Model.DAO;

import inventariobombero.Model.Entities.TipoPersona;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
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
public class TipoPersonalDAO {
    BDConexion conexion = new BDConexion();
    private TipoPersona tipo;

    public TipoPersonalDAO(TipoPersona tipo) {
        this.tipo = tipo;
    }
    
    public boolean crear(){
        Connection con = conexion.getConnection();
        boolean resp = false;

        try {
            con.setAutoCommit(false);
            //PreparedStatement pst = con.prepareStatement("SELECT id_Persona,cedula,nombre,apellido,fecha_naimiento,rango FROM Persona WHERE estado='A'");
            PreparedStatement pst = con.prepareStatement("INSERT INTO tipo_persona(id_TipoPersona,descripcion,estado) VALUES (null,?,?)");
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
            //PreparedStatement pst = con.prepareStatement("SELECT id_Persona,cedula,nombre,apellido,fecha_naimiento,rango FROM Persona WHERE estado='A'");
            PreparedStatement pst = con.prepareStatement("UPDATE tipo_persona SET descripcion=? WHERE id_TipoPersona=? ");
            pst.setString(1, tipo.getDescripcion());
            pst.setInt(2, tipo.getId_TipoPersona());
            
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
            //PreparedStatement pst = con.prepareStatement("SELECT id_Persona,cedula,nombre,apellido,fecha_naimiento,rango FROM Persona WHERE estado='A'");
            PreparedStatement pst = con.prepareStatement("UPDATE tipo_persona SET estado=? WHERE id_TipoPersona=? ");
            pst.setString(1, "I");
            pst.setInt(2, tipo.getId_TipoPersona());
            
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
            String [] fila= new String[6];
            
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT id_TipoPersona,descripcion FROm tipo_persona WHERE estado='A'");

            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                fila[0] = String.valueOf(rs.getInt("id_TipoPersona"));
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
            PreparedStatement pst = con.prepareStatement("SELECT id_TipoPersona,descripcion FROM tipo_persona WHERE estado='A'");
            ResultSet rs = pst.executeQuery();
            listado.insertElementAt("-Seleccione-", 0);
            while(rs.next()){
                listado.insertElementAt(rs.getString("descripcion"), rs.getInt("id_TipoPersona"));
            }   
            //listado.addElement(model);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
          return listado;
    }
    
}
