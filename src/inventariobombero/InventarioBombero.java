package inventariobombero;

import inventariobombero.Controller.UsuarioController;
import inventariobombero.View.formLogin;
import inventariobombero.View.fromPrincipal;

public class InventarioBombero {
    public static void main(String[] args) {
        formLogin frm = new formLogin();
        UsuarioController control = new UsuarioController(frm);
        frm.ConectarControlador(control);
        frm.setVisible(true);
       // fromPrincipal form = new fromPrincipal();
       // form.setVisible(true);
    }
    
}
