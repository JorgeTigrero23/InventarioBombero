package inventariobombero.Model.Entities;

import inventariobombero.Model.DAO.DetalleSalidaDAO;
import java.util.Date;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class DetalleSalida {
    private int id_DetalleSalida;
    private Date fecha_salida;
    private int cantidad;
    private String Descripcion;
    private PersonaCuartel pers_cuartel;
    private Movil movil;
    private String ubicacion;
    private Producto producto;

    public int getId_DetalleSalida() {
        return id_DetalleSalida;
    }

    public void setId_DetalleSalida(int id_DetalleSalida) {
        this.id_DetalleSalida = id_DetalleSalida;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public PersonaCuartel getPers_cuartel() {
        return pers_cuartel;
    }

    public void setPers_cuartel(PersonaCuartel pers_cuartel) {
        this.pers_cuartel = pers_cuartel;
    }

    public Movil getMovil() {
        return movil;
    }

    public void setMovil(Movil movil) {
        this.movil = movil;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public boolean crear(){
        DetalleSalidaDAO dao = new DetalleSalidaDAO(this);
        return dao.crear();
    }
}
