package DB_CONN;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Users {
	private koneksi con = new koneksi();
	
	public void tambahU(String IDU, String Nama, String Pass, int Jenis){
		try {
			Statement stmt = con.getConnection().createStatement();
			stmt.executeUpdate("Insert into Users values('"+IDU+"','"+Nama+"','"+Pass+"',"+Jenis+")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void HapusU(String IDU){
		try {
			Statement stmt = con.getConnection().createStatement();
			stmt.executeUpdate("delete from Users where IDU='"+IDU+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateU(String IDU, String NamaU, String Pass, int Jenis){
		try {
			Statement stmt = con.getConnection().createStatement();
			if(Pass.isEmpty()){
				stmt.executeUpdate("update Users set NamaU='"+NamaU+"',Jenis="+Jenis+" where IDU='"+IDU+"'");	
			}else{
				stmt.executeUpdate("update Users set NamaU='"+NamaU+"',Jenis="+Jenis+",Pass='"+Pass+"' where IDU='"+IDU+"'");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
