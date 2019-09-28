/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariobombero.Model.DAO;

import inventariobombero.Model.Entities.DetalleEntrada;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class DetalleEntradaDAO {
    BDConexion conexion = new BDConexion();
    private DetalleEntrada detalle;

    public DetalleEntradaDAO(DetalleEntrada detalle) {
        this.detalle = detalle;
    }
    
    public boolean crear_nuevo(){
       Connection con = conexion.getConnection();
        boolean resp = false;
        try {
            int id=0;
            con.setAutoCommit(false);
            CallableStatement cs = con.prepareCall("{call sp_insertar_equipo(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, detalle.getProducto().getCodigo());
            cs.setString(2, detalle.getProducto().getDescripcion());
            cs.setInt(3, detalle.getProducto().getTipo_producto().getId_TipoProducto());
            cs.setInt(4, detalle.getProducto().getMarca().getId_Marca());
            cs.setInt(5, detalle.getProducto().getModelo().getId_Modelo());
            cs.setDouble(6, detalle.getProducto().getValor_unitario());
            cs.setDouble(7, detalle.getProducto().getStock());
            cs.setInt(8, detalle.getProducto().getEstado().getId_estado());
            cs.setInt(9, detalle.getProducto().getProveedor().getId_Proveedor());
            cs.setString(10, detalle.getProducto().getUbicacion());
            cs.registerOutParameter(11, Types.INTEGER);
            cs.execute();
            
            id = cs.getInt(11);
            
            System.out.println("id: " + id);
            
            if(id!=0){
                PreparedStatement pst = con.prepareStatement("INSERT INTO detalle_entrada(id_DetalleEntrada,fecha_entrada,cantidad,valor_unitario,valor_total,estado,id_Producto)"
                                                            + "VALUES(null,?,?,?,?,?,?)");
                Date date = new Date(detalle.getFecha_entrada().getTime());
                System.out.println("fecha: " +  date);
                pst.setDate(1, date);
                pst.setInt(2, detalle.getCantidad());
                pst.setDouble(3, detalle.getPrecio_unitario());
                pst.setDouble(4, detalle.getValor_total());
                pst.setString(5, "A");
                pst.setInt(6, id);
                
                int resultado = pst.executeUpdate();

                if (resultado > 0) {
                    resp = true;
                }
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
    
    
    public boolean crea_existente(){
       Connection con = conexion.getConnection();
        boolean resp = false;
        try {
            con.setAutoCommit(false);
                PreparedStatement pst = con.prepareStatement("INSERT INTO detalle_entrada(id_DetalleEntrada,fecha_entrada,cantidad,valor_unitario,valor_total,estado,id_Producto)"
                                                            + "VALUES(null,?,?,?,?,?,?)");
                Date date = new Date(detalle.getFecha_entrada().getTime());
                System.out.println("fecha: " +  date);
                pst.setDate(1, date);
                pst.setInt(2, detalle.getCantidad());
                pst.setDouble(3, detalle.getPrecio_unitario());
                pst.setDouble(4, detalle.getValor_total());
                pst.setString(5, "A");
                pst.setInt(6, detalle.getProducto().getId_Producto());
                
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
            String Titulos[]={"Id","Fecha_Entrada","Id_Prod","Descripción","Cantidad","Valor","Valor Total"};
            listado = new DefaultTableModel(null,Titulos);   
            String [] fila= new String[7];
            
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT de.id_DetalleEntrada,de.Fecha_Entrada,p.Id_Producto,p.Descripcion,de.cantidad,de.valor_unitario,de.valor_total "
                                                                + "FROM detalle_entrada de"
                                                                + "INNER JOIN producto p ON p.id_Producto=de.Id_Producto"
                                                                + " WHERE de.estado='A'");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                fila[0] = String.valueOf(rs.getInt("id_DetalleEntrada"));
                fila[1] = String.valueOf(rs.getDate("Fecha_entrada"));
                fila[2] = String.valueOf(rs.getInt("id_Producto"));
                fila[3] = rs.getString("descripcion");
                fila[4] = String.valueOf(rs.getInt("cantidad"));
                fila[5] = String.valueOf(rs.getDouble("valor_unitario"));
                fila[6] = String.valueOf(rs.getDouble("valor_total"));
                listado.addRow(fila);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return listado;
    }
    
}
