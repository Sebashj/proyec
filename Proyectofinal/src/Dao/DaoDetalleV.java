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
import Modelo.DetalleV;

public class DaoDetalleV {
	Conexion cx =null;
	
	public DaoDetalleV() {
		cx=new Conexion();
	}
	
	public boolean insertarDetalleV(DetalleV user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO detalleV VALUES(null,?,?,?,?,?)");
			ps.setInt(1, user.getIdproducto());
			ps.setInt(2, user.getIdventa());
			ps.setInt(3, user.getCosto());
			ps.setInt(4, user.getCantidad());
			ps.setInt(5, user.getImporte());
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
	public ArrayList<DetalleV> fetchDetalleVs(){
		ArrayList<DetalleV> lista=new ArrayList<DetalleV>();
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM detalleV");
			rs=ps.executeQuery();
			while(rs.next()) {
				DetalleV u=new DetalleV();
				u.setIddetalle(rs.getInt("Iddetalle"));
				u.setIdproducto(rs.getInt("Idproducto"));
				u.setIdventa(rs.getInt("Idventa"));
				u.setCosto(rs.getInt("Costo"));
				u.setCantidad(rs.getInt("Cantidad"));
				u.setImporte(rs.getInt("Importe"));
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
	
	public ArrayList<DetalleV> fecthBuscar(String palabra) {
		ArrayList<DetalleV> lista2 = new ArrayList<DetalleV>();
		try {
			String sql = "SELECT * FROM detalleV WHERE " + "(Idproducto LIKE ?) OR " + "(Idventa LIKE ?) OR " +"(Costo LIKE ?) OR " +"(Cantidad LIKE ?) OR " + "(Importe LIKE ?); ";
			PreparedStatement ps = cx.conectar().prepareStatement(sql);
			ps.setString(1, "%" + palabra + "%");
			ps.setString(2, "%" + palabra + "%");
			ps.setString(3, "%" + palabra + "%");
			ps.setString(4, "%" + palabra + "%");
			ps.setString(5, "%" + palabra + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DetalleV p = new DetalleV();
				p.setIddetalle(rs.getInt("Iddetalle"));
				p.setIdproducto(rs.getInt("Idproducto"));
				p.setIdventa(rs.getInt("Idventa"));
				p.setCosto(rs.getInt("Costo"));
				p.setCantidad(rs.getInt("Cantidad"));
				p.setImporte(rs.getInt("Importe"));
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

	public boolean eliminarDetalleV(int idDetalleV) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("DELETE FROM detalleV WHERE iddetalle=?");
			ps.setInt(1, idDetalleV);
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
	
	public boolean editarDetalleV(DetalleV user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("UPDATE detalleV SET idproducto=?, idventa=?, costo=?, cantidad=?, importe=? WHERE iddetalle=?");
			ps.setInt(1, user.getIdproducto());
			ps.setInt(2, user.getIdventa());
			ps.setInt(3, user.getCosto());
			ps.setInt(4, user.getCantidad());
			ps.setInt(5, user.getImporte());
			ps.setInt(6, user.getIddetalle());
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
}
