package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Conexion.Conexion;
import Modelo.Autos;
import Modelo.Cliente;

public class DaoAutos {

    Conexion cx = new Conexion();

    public DaoAutos() {

    }

    public boolean create(Autos a) {
        try {
            String sql = "INSERT INTO Autos (idauto,marca,año,tipodeauto,cilindros,imagen) VALUES(null,?,?,?,?,?)";
            PreparedStatement ps = cx.conectar().prepareStatement(sql);
            ps.setString(1, a.getMarca());
            ps.setInt(2, a.getAño());
            ps.setString(3, a.getTipodeauto());
            ps.setString(4, a.getCilindros());
            ps.setString(5, a.getImagen());
            ps.execute();
            ps.close();
            ps = null;
            cx.conectar();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DaoAutos.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }

    }

    public ArrayList<Autos> fetchAutos() {
        ArrayList<Autos> lista = new ArrayList<Autos>();
       // PreparedStatement ps=null;
		//ResultSet rs =null;
        try {
        	//ps=cx.conectar().prepareStatement("SELECT * FROM Autos");
			//rs=ps.executeQuery();
            String sql = "SELECT * FROM Autos";
            PreparedStatement ps = cx.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Autos a = new Autos();
                a.setIdauto(rs.getInt("Idauto"));
                a.setMarca(rs.getString("Marca"));
                a.setAño(rs.getInt("Año"));
                a.setTipodeauto(rs.getString("Tipodeauto"));
                a.setCilindros(rs.getString("Cilindros"));
                a.setImagen(rs.getString("Imagen"));
                lista.add(a);
            }
            ps.close();
            ps = null;
            cx.conectar();
        } catch (SQLException ex) {
            System.out.println("Fallo metodo read categoria");
            ex.printStackTrace();
        }
        return lista;
    }
    
	public ArrayList<Autos> fecthBuscar(String palabra) {
		ArrayList<Autos> lista2 = new ArrayList<Autos>();
		try {
			String sql = "SELECT * FROM Autos WHERE " + "(Marca LIKE ?) OR " + "(Año LIKE ?) OR " +"(Tipodeauto LIKE ?) OR " +"(Cilindros LIKE ?) OR " + "(Imagen LIKE ?); ";
			PreparedStatement ps = cx.conectar().prepareStatement(sql);
			ps.setString(1, "%" + palabra + "%");
			ps.setString(2, "%" + palabra + "%");
			ps.setString(3, "%" + palabra + "%");
			ps.setString(4, "%" + palabra + "%");
			ps.setString(5, "%" + palabra + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Autos p = new Autos();
				p.setIdauto(rs.getInt("Idauto"));
                p.setMarca(rs.getString("Marca"));
                p.setAño(rs.getInt("Año"));
                p.setTipodeauto(rs.getString("Tipodeauto"));
                p.setCilindros(rs.getString("Cilindros"));
                p.setImagen(rs.getString("Imagen"));
				lista2.add(p);
			}
			ps.close();
			ps = null;
			//cx.desconectar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Error en BUSCAR");
		}
		return lista2;

	}

    public Autos read(int idauto) {
        Autos a = new Autos();
        try {
            String sql = "SELECT * FROM Autos WHERE idauto=?";
            PreparedStatement ps = cx.conectar().prepareStatement(sql);
            ps.setInt(1, idauto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a.setIdauto(rs.getInt("Idautos"));
                a.setMarca(rs.getString("Marca"));
                a.setAño(rs.getInt("Año"));
                a.setTipodeauto(rs.getString("Tipodeauto"));
                a.setCilindros(rs.getString("Cilindros"));
                a.setImagen(rs.getString("imagen"));
            }
            ps.close();
            ps = null;
            cx.conectar();
        } catch (SQLException ex) {
            System.out.println("Fallo metodo read categoria");
            ex.printStackTrace();
        }
        return a;
    }

    public boolean update(Autos a) {
        try {
            String sql = "UPDATE Autos SET marca=?, año=?, tipodeauto=?, cilindros=?, imagen=?  WHERE idauto=?";
            PreparedStatement ps = cx.conectar().prepareStatement(sql);
            ps.setString(1, a.getMarca());
            ps.setInt(2, a.getAño());
            ps.setString(3, a.getTipodeauto());
            ps.setString(4, a.getCilindros());
            ps.setString(5, a.getImagen());
            ps.setInt(6, a.getIdauto());
            ps.execute();
            ps.close();
            ps = null;
            cx.conectar();
            return true;
        } catch (SQLException ex) {
        	 ex.printStackTrace();
            return false;
        }
    }

    public boolean delete(int idauto) {
        try {
            String sql = "DELETE FROM Autos WHERE idauto=?";
            PreparedStatement ps = cx.conectar().prepareStatement(sql);
            ps.setInt(1, idauto);
            ps.execute();
            ps.close();
            ps = null;
            cx.conectar();
            return true;
        } catch (SQLException ex) {
        	 ex.printStackTrace();
            return false;
        }
    }

}