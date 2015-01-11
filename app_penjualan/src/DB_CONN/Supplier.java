package DB_CONN;

import java.sql.SQLException;
import java.sql.Statement;

public class Supplier {
	
	private koneksi con = new koneksi();
	
	public void addSup(String IDS, String NamaS){
		try {
			Statement stmt = con.getConnection().createStatement();
			stmt.executeUpdate("Insert into Supplier values('"+IDS+"','"+NamaS+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void gantiSup(String IDS, String NamaS){
		try {
			Statement stmt = con.getConnection().createStatement();
			stmt.executeUpdate("update Supplier set NamaS='"+NamaS+"' where IDS='"+IDS+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void HapusSup(String IDS){
		try {
			Statement stmt = con.getConnection().createStatement();
			stmt.executeUpdate("delete from Supplier where IDS='"+IDS+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
