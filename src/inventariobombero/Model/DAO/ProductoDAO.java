/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariobombero.Model.DAO;

import inventariobombero.Model.Entities.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOrGiTokiLLjOyS
 */
public class ProductoDAO {
    BDConexion conexion = new BDConexion();
    private Producto producto;

    public ProductoDAO(Producto producto) {
        this.producto = producto;
    }
    
    public boolean crear(){
        boolean resp= false;
        
        return resp;
    }
    
    public boolean modificar(){
        boolean resp= false;
        
        return resp;
    }
    
    public boolean eliminar(){
        boolean resp= false;
        
        return resp;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel listado=null;
        try {
            String Titulos[]={"Id","Codigo","Descripción","Tipo Equipo","Marca","Modelo","Valor","Stock","Estado","Proveedor","Ubicación"};
            listado = new DefaultTableModel(null,Titulos);   
            String [] fila= new String[11];
            
            Connection con = conexion.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT p.id_Producto,p.codigo,p.descripcion as prod_desc,tp.descripcion as te_desc,ma.descripcion as marc_desc,mo.descripcion as mo_desc," +
                                                         " p.valor_unitario,p.stock,e.descripcion as est_desc,pro.descripcion as pro_desc,p.ubicacion " +
                                                         " FROM producto p " +
                                                         " INNER JOIN tipo_producto tp ON tp.id_TipoProducto=p.Id_TipoProducto " +
                                                         " INNER JOIN marca ma ON ma.id_Marca=p.Id_Marca " +
                                                         " INNER JOIN modelo mo ON mo.id_Modelo=p.Id_Modelo " +
                                                         " INNER JOIN estado e ON e.id_Estado=p.Id_Estado " +
                                                         " INNER JOIN proveedor pro ON pro.id_Proveedor=p.Id_Proveedor " +
                                                         " WHERE tp.estado='A' and ma.estado='A' and mo.estado='A' and e.Estado='A' and pro.estado='A'");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                fila[0] = String.valueOf(rs.getInt("id_Producto"));
                fila[1] = rs.getString("codigo");
                fila[2] = rs.getString("prod_desc");
                fila[3] = rs.getString("te_desc");
                fila[4] = rs.getString("marc_desc");
                fila[5] = rs.getString("mo_desc");
                fila[6] = String.valueOf(rs.getDouble("valor_unitario"));
                fila[7] = String.valueOf(rs.getInt("stock"));
                fila[8] = rs.getString("est_desc");
                fila[9] = rs.getString("pro_desc");
                fila[10] = rs.getString("ubicacion");
                listado.addRow(fila);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return listado;
    }
}
