package DB_CONN;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Produk {
	
	private static koneksi con = new koneksi();
	
	public void TambahProduk(String IDP, String NamaP, int Harga, String IDS){
		try {
			Statement stmt = con.getConnection().createStatement();
			stmt.executeUpdate("insert into Produk values('"+IDP+"','"+IDS+"','"+NamaP+"',"+Harga+")");
			stmt.executeUpdate("insert into StokProduk values('"+IDP+"',"+0+")");
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void UbahProduk(String IDP, String NamaP, int Harga){
		try {
			Statement stmt = con.getConnection().createStatement();
			if(NamaP.isEmpty()){
				stmt.executeUpdate("update Produk set Harga="+Harga+" where IDP='"+IDP+"'");
			}else{
				stmt.executeUpdate("update Produk set Harga="+Harga+",NamaP='"+NamaP+"' where IDP='"+IDP+"'");
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void HapusProduk(String IDP){
		try {
			Statement stmt = con.getConnection().createStatement();
			stmt.executeUpdate("delete from Produk where IDP='"+IDP+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
