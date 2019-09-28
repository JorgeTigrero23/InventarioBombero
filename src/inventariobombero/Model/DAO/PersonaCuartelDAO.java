/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariobombero.Model.DAO;

import inventariobombero.Model.Entities.PersonaCuartel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class PersonaCuartelDAO {
    BDConexion conexion = new BDConexion();
    private PersonaCuartel pc;

    public PersonaCuartelDAO(PersonaCuartel pc) {
        this.pc = pc;
    }
    
    
    public boolean crear(){
        Connection con = conexion.getConnection();
        boolean resp = false;

        try {
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
            con.setAutoCommit(false);
            //PreparedStatement pst = con.prepareStatement("SELECT id_Persona,cedula,nombre,apellido,fecha_naimiento,rango FROM Persona WHERE estado='A'");
            PreparedStatement pst = con.prepareStatement("INSERT INTO pers_cuartel(id_PersCuartel,id_Personal,id_Cuartel,estado) VALUES (null,?,?)");
            pst.setInt(1, pc.getPersona().getId_Persona());
            pst.setInt(2, pc.getCuartel().getId_Cuartel());
            pst.setString(3, "A");
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
            PreparedStatement pst = con.prepareStatement("UPDATE pers_cuartel SET id_personal=?,id_Cuartel=? WHERE id_PersCuartel=? ");
            pst.setInt(1, pc.getPersona().getId_Persona());
            pst.setInt(2, pc.getCuartel().getId_Cuartel());
            pst.setInt(3, pc.getId_PersCuartel());
            
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
            PreparedStatement pst = con.prepareStatement("UPDATE pers_cuartel SET estado=? WHERE id_PersCuartel=? ");
            pst.setString(1, "I");
            pst.setInt(2, pc.getId_PersCuartel());
            
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
    
    public int obtener_id(){
        int resp =0;
        try {
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT id_PersCuartel FROM pers_cuartel WHERE estado='A' and id_Personal=? and id_Cuartel=? ");
            pst.setInt(1, pc.getPersona().getId_Persona());
            pst.setInt(2, pc.getCuartel().getId_Cuartel());
            
             ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                resp = rs.getInt("id_PersCuartel");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return resp;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel listado=null;
        try {
            String Titulos[]={"Id","Cuartel","Nombre","Apellido"};
            listado = new DefaultTableModel(null,Titulos);   
            String [] fila= new String[4];
            
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT pc.id_PersCuartel,p.nombre,p.apellido,c.cuartel"
                                                        +" FROM pers_cuartel pc"
                                                        +" INNER JOIN personal p ON p.id_Personal=pc.id_Personal"
                                                        +" INNER JOIN cuartel c ON c.id_Cuartel=pc.id_Cuartel"
                                                        +" WHERE pc.estado='A' and p.estado='A' and c.estado='A'");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                fila[0] = String.valueOf(rs.getInt("id_PersCuartel"));
                fila[1] = rs.getString("cuartel");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getString("apellido");
                listado.addRow(fila);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return listado;
    }
}
