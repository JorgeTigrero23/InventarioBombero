/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariobombero.Model.DAO;

import inventariobombero.Model.Entities.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class UsuarioDAO {
    BDConexion conexion = new BDConexion();
    private Usuario usuario;

    public UsuarioDAO(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public boolean login(){
        boolean resp=false;
        try {
            Connection con = conexion.getConnection();
            CallableStatement cs = con.prepareCall("{call sp_login(?,?)}");
            cs.setString(1, usuario.getUsuario());
            cs.setString(2, usuario.getClave());
            
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                System.out.println("id usu: " + rs.getString("usuario"));
                if(usuario.getUsuario().equalsIgnoreCase(rs.getString("usuario"))){
                    resp=true;
                }
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    
}
