/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariobombero.Model.Entities;

import inventariobombero.Model.DAO.UsuarioDAO;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class Usuario {
    private int id_Usuario;
    private String usuario;
    private String clave;

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }


    public boolean login(){
        UsuarioDAO dao = new UsuarioDAO(this);
        return dao.login();
    }
    
}
