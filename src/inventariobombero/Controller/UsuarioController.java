package inventariobombero.Controller;

import inventariobombero.Model.Entities.Usuario;
import inventariobombero.View.formLogin;
import inventariobombero.View.fromPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class UsuarioController implements ActionListener, KeyListener{ 
    formLogin frm = new formLogin();

    public UsuarioController(formLogin frm) {
        this.frm = frm;
        this.frm.btn_Login.addActionListener(this);
        frm.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {       
        String op = e.getActionCommand();
        
        if(op.equalsIgnoreCase("INICIAR")){
            
            String usuario = frm.txt_Usuario.getText();
            String clave = String.valueOf(frm.txt_Clave.getText());
            System.out.println("us"+ usuario);
            if(usuario!=null && clave!=null){
                iniciar(usuario, clave);
                //return;
            }else{
                System.out.println("Por favor Ingrese usuario y clave.");
            }
            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void iniciar(String usuario, String clave){
        Usuario usua = new Usuario();
        usua.setUsuario(usuario);
        usua.setClave(clave);
        boolean resp = usua.login();
        if(resp){
         System.out.println("Entroo :)");
         fromPrincipal frm = new fromPrincipal();
         this.frm.setVisible(false);
         frm.setVisible(true);
        }else{
         System.out.println("Error :'( ");
        }
    }
    
}
