/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariobombero.Model.DAO;

import inventariobombero.Model.Entities.Persona;
import inventariobombero.Model.Entities.TipoPersona;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class PersonaDAO {
    BDConexion conexion = new BDConexion();
    private Persona p;

    public PersonaDAO(Persona p) {
        this.p = p;
    }
       
    public boolean crear(){
        Connection con = conexion.getConnection();
        boolean resp = false;

        try {
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
            con.setAutoCommit(false);
            //PreparedStatement pst = con.prepareStatement("SELECT id_Persona,cedula,nombre,apellido,fecha_naimiento,rango FROM Persona WHERE estado='A'");
            PreparedStatement pst = con.prepareStatement("INSERT INTO personal(id_Personal,cedula,nombre,apellido,fecha_nacimiento,id_TipoPersonal,estado) VALUES (null,?,?,?,?,?,?)");
            pst.setString(1, p.getCedula());
            pst.setString(2, p.getNombre());
            pst.setString(3, p.getApellido());
            pst.setDate(4, new java.sql.Date(p.getFecha_nacimiento().getTime()));
            pst.setInt(5, p.getTipo_persona().getId_TipoPersona());
            pst.setString(6, "A");
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
            PreparedStatement pst = con.prepareStatement("UPDATE personal SET cedula=?,nombre=?,apellido=?,fecha_nacimiento=?,id_TipoPersonal=? WHERE id_Personal=? ");
            pst.setString(1, p.getCedula());
            pst.setString(2, p.getNombre());
            pst.setString(3, p.getApellido());
            pst.setDate(4, new java.sql.Date(p.getFecha_nacimiento().getTime()));
            pst.setInt(5, p.getTipo_persona().getId_TipoPersona());
            pst.setInt(6, p.getId_Persona());
            
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
            PreparedStatement pst = con.prepareStatement("UPDATE personal SET estado=? WHERE id_Personal=? ");
            pst.setString(1, "I");
            pst.setInt(2, p.getId_Persona());
            
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
            Connection con = conexion.getConnection();
            String Titulos[]={"Id","Cedula","Nombres","Apellidos","F. Nacimiento","Id_Rango","Rango"};
            listado = new DefaultTableModel(null,Titulos);   
            String [] fila= new String[7];
            PreparedStatement pst = con.prepareStatement("SELECT p.id_Personal,p.cedula,"
                                                        + "p.nombre,p.apellido,"
                                                        + "p.fecha_nacimiento,p.id_TipoPersonal, tp.descripcion"
                                                        + " FROM personal p"
                                                        + " INNER JOIN tipo_persona tp ON p.id_TipoPersonal = tp.id_TipoPersona"
                                                        + " WHERE p.estado='A' and tp.estado='A'");
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                fila[0] = String.valueOf(rs.getInt("id_Personal"));
                fila[1] = rs.getString("cedula");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getString("apellido");
                fila[4] = rs.getString("fecha_nacimiento");
                fila[5] = String.valueOf(rs.getInt("id_TipoPersonal"));
                fila[6] = rs.getString("descripcion");
                listado.addRow(fila);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listado;
    }
    
    
    public DefaultComboBoxModel listarCombo(int id) throws SQLException{
        DefaultComboBoxModel listado= new DefaultComboBoxModel();
        try {
            Vector model = new Vector();
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT id_Personal,nombre,apellido FROM personal WHERE estado='A' and id_Personal=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Persona persona = new Persona();
                persona.setId_Persona(rs.getInt("id_personal"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                listado.addElement(persona);
            }   
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
      return listado;
    }
    
    
    public DefaultComboBoxModel listarCombo2() throws SQLException{
        DefaultComboBoxModel listado= new DefaultComboBoxModel();
        try {
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT id_Personal,nombre,apellido FROM personal WHERE estado='A'");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Persona persona = new Persona();
                persona.setId_Persona(rs.getInt("id_personal"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                listado.addElement(persona);
            }   
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
      return listado;
    }
    
}
