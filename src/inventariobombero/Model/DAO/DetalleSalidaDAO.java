package inventariobombero.Model.DAO;

import inventariobombero.Model.Entities.DetalleSalida;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class DetalleSalidaDAO {
    BDConexion conexion = new BDConexion();
    private DetalleSalida detalle;

    public DetalleSalidaDAO(DetalleSalida detalle) {
        this.detalle = detalle;
    }
    
    public boolean crear(){
       Connection con = conexion.getConnection();
        boolean resp = false;
        try {
            con.setAutoCommit(false);
                PreparedStatement pst = con.prepareStatement("INSERT INTO detalle_salida(id_DetalleSalida,fecha_salida,cantidad,descripcion,id_PersCuartel,id_Movil,ubicacion,id_Producto,estado)"
                                                            + "VALUES(null,?,?,?,?,?,?,?,?)");
                Date date = new Date(detalle.getFecha_salida().getTime());
                pst.setDate(1, date);
                pst.setInt(2, detalle.getCantidad());
                pst.setString(3, detalle.getDescripcion());
                pst.setDouble(4, detalle.getPers_cuartel().getId_PersCuartel());
                pst.setInt(5, detalle.getMovil().getId_Movil());
                pst.setString(6, detalle.getUbicacion());
                pst.setInt(7, detalle.getProducto().getId_Producto());
                pst.setString(8, "A");
                
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
}
