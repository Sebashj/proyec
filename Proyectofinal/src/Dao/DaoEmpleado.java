package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Conexion.Conexion;
import Modelo.Autos;
import Modelo.Empleado;

public class DaoEmpleado {
	Conexion cx =null;
	
	public DaoEmpleado() {
		cx=new Conexion();
	}
	
	public boolean insertarEmpleado(Empleado user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO Empleado VALUES(null,?,?,?,?,?)");
			ps.setString(1, user.getNombre());
			ps.setInt(2, user.getTelefono());
			ps.setString(3, user.getDomicilio());
			ps.setString(4, user.getPuesto());
			ps.setString(5, convertirSHA256(user.getPassword()));
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
        	JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, e.toString());
			
			e.printStackTrace();
			return false;
		}
		
	}
	public ArrayList<Empleado> fetchEmpleados(){
		ArrayList<Empleado> lista=new ArrayList<Empleado>();
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM Empleado");
			rs=ps.executeQuery();
			while(rs.next()) {
				Empleado u=new Empleado();
				u.setIdempleado(rs.getInt("Idempleado"));
				u.setNombre(rs.getString("Nombre"));
				u.setTelefono(rs.getInt("Telefono"));
				u.setDomicilio(rs.getString("Domicilio"));
				u.setPuesto(rs.getString("Puesto"));
				u.setPassword(rs.getString("password"));
				lista.add(u);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
        	JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
		}
		return lista;
		
	}
	public ArrayList<Empleado> fecthBuscar(String palabra) {
		ArrayList<Empleado> lista2 = new ArrayList<Empleado>();
		try {
			String sql = "SELECT * FROM Empleado WHERE " + "(Nombre LIKE ?) OR " + "(Telefono LIKE ?) OR " +"(Domicilio LIKE ?) OR " +"(Puesto LIKE ?) OR " + "(password LIKE ?); ";
			PreparedStatement ps = cx.conectar().prepareStatement(sql);
			ps.setString(1, "%" + palabra + "%");
			ps.setString(2, "%" + palabra + "%");
			ps.setString(3, "%" + palabra + "%");
			ps.setString(4, "%" + palabra + "%");
			ps.setString(5, "%" + palabra + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Empleado p = new Empleado();
				p.setIdempleado(rs.getInt("Idempleado"));
				p.setNombre(rs.getString("Nombre"));
				p.setTelefono(rs.getInt("Telefono"));
				p.setDomicilio(rs.getString("Domicilio"));
				p.setPuesto(rs.getString("Puesto"));
				p.setPassword(rs.getString("password"));
				lista2.add(p);
			}
			ps.close();
			ps = null;
			//cx.desconectar();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
        	JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, ex.toString());
			ex.printStackTrace();
			System.out.println("Error en BUSCAR");
		}
		return lista2;

	}
	
	public boolean loginEmpleado(Empleado user) {
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM Empleado WHERE nombre=? AND password=?");
			ps.setString(1, user.getNombre());
			ps.setString(2, convertirSHA256(user.getPassword()));
			rs=ps.executeQuery();
			if(rs.next()) {
			return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
        	JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean eliminarEmpleado(int idempleado) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("DELETE FROM Empleado WHERE idempleado=?");
			ps.setInt(1, idempleado);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
        	JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean editarEmpleado(Empleado user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("UPDATE Empleado SET nombre=?, telefono=?, domicilio=?, puesto=?, password=? WHERE idempleado=?");
			ps.setString(1, user.getNombre());
			ps.setInt(2, user.getTelefono());
			ps.setString(3, user.getDomicilio());
			ps.setString(4, user.getPuesto());
			ps.setString(5, convertirSHA256(user.getPassword()));
			ps.setInt(6, user.getIdempleado());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
        	JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, e.toString());
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public String convertirSHA256(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} 
		catch (NoSuchAlgorithmException e) {		
			e.printStackTrace();
			return null;
		}
		    
		byte[] hash = md.digest(password.getBytes());
		StringBuffer sb = new StringBuffer();
		    
		for(byte b : hash) {        
			sb.append(String.format("%02x", b));
		}
		    
		return sb.toString();
	}

}
