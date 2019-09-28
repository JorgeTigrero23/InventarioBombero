package inventariobombero.Model.Entities;

import inventariobombero.Model.DAO.DetalleEntradaDAO;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class DetalleEntrada {
    private int id_DetalleEntrada;
    private Date fecha_entrada;
    private int cantidad;
    private Double precio_unitario;
    private Double valor_total;
    private String estado;
    private Producto producto;

    public int getId_DetalleEntrada() {
        return id_DetalleEntrada;
    }

    public void setId_DetalleEntrada(int id_DetalleEntrada) {
        this.id_DetalleEntrada = id_DetalleEntrada;
    }

    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
    public boolean crea_nuevo(){
        DetalleEntradaDAO dao = new DetalleEntradaDAO(this);
        return dao.crear_nuevo();
    }
    
    public boolean crea_existente(){
        DetalleEntradaDAO dao = new DetalleEntradaDAO(this);
        return dao.crea_existente();
    }
    
    /*
    public boolean modificar(){
        DetalleEntradaDAO dao = new DetalleEntradaDAO(this);
        return dao.modificar();
    }
    
    public boolean eliminar(){
        DetalleEntradaDAO dao = new DetalleEntradaDAO(this);
        return dao.eliminar();
    }
    
    public DefaultTableModel lista (){
        DetalleEntradaDAO dao = new DetalleEntradaDAO(this);
        return dao.leer();
    }
      */      
}
