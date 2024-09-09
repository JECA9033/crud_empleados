/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jealv
 */
public class empleado extends persona{
    private int id;
    private String codigo;
    
    Conexion cn;
    public empleado(){}
    public empleado(int id,String codigo, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, String puesto) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo = codigo;
        this.id = id;
    }
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
      

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public DefaultTableModel leer(){
    DefaultTableModel tabla = new DefaultTableModel();
    try{
     cn = new Conexion();
     cn.abrir_conexion();
     String query;
     query = "Select id_empleado as id, codigo,nombres,apellidos,direccion,telefono,fecha_nacimiento from empleados;";
     ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
     
     String encabezado [] = {"id","Codigo","Nombres","Apellidos","Direccion","Telefono","Nacimiento"};
     tabla.setColumnIdentifiers(encabezado);
          
     
     String datos [] = new String [7];
     while(consulta.next()){
     datos[0] = consulta.getString("id");
     datos[1] = consulta.getString("codigo");
     datos[2] = consulta.getString("nombres");
     datos[3] = consulta.getString("apellidos");
     datos[4] = consulta.getString("direccion");
     datos[5] = consulta.getString("telefono");
     datos[6] = consulta.getString("fecha_nacimiento");
     tabla.addRow(datos);
      }
     cn.cerrar_conexion();
     
     
    }catch(SQLException ex){
            System.out.println("Error: "+ ex.getMessage());
    }          
        return tabla;
    }
    @Override
    public void agregar(){
        try{
            PreparedStatement parametro;
            String query = "INSERT INTO empleados(codigo,nombres,apellidos,direccion,telefono,fecha_nacimiento) VALUES(?,?,?,?,?,?);";
        cn = new Conexion();
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getCodigo());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getDireccion());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getFecha_nacimiento());
               
        
        int executar = parametro.executeUpdate();
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar)  + " Registro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);
                
        
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error..." + ex.getMessage());
        }       
   }
    @Override
    public void actualizar(){
    try{
            PreparedStatement parametro;
            String query = "update empleados set codigo = ?,nombres = ?,apellidos = ?,direccion = ?,telefono= ?,fecha_nacimiento = ? " +
                    "Where id_empleado = ?";
            
        cn = new Conexion();
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getCodigo());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getDireccion());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getFecha_nacimiento());
        parametro.setInt(7, this.getId());
        
        int executar = parametro.executeUpdate();
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar)  + " Registro Actualizado","Agregar",JOptionPane.INFORMATION_MESSAGE);
                
        
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error..." + ex.getMessage());
        }
    }
    @Override
    public void eliminar(){
    try{
            PreparedStatement parametro;
            String query = "delete from empleados where id_empleado = ?";
            
        cn = new Conexion();
        cn.abrir_conexion();
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setInt(1, getId());
        
        int executar = parametro.executeUpdate();
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar)  + " Registro Eliminado","Agregar",JOptionPane.INFORMATION_MESSAGE);
                
        
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error..." + ex.getMessage());
        }
    
    
}
}
