package DB_CONN;

import java.sql.SQLException;
import java.sql.Statement;

public class StokProduk {
	
	private koneksi con = new koneksi();
	
	public void tambah(String IDP, int Jumlah){
		try {
			Statement stmt = con.getConnection().createStatement();
			stmt.executeUpdate("update StokProduk set Stok=(Stok+"+Jumlah+") where IDP='"+IDP+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void kurang(String IDP, int Jumlah){
		try {
			Statement stmt = con.getConnection().createStatement();
			stmt.executeUpdate("update StokProduk set Stok=Stok-"+Jumlah+" where IDP='"+IDP+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
