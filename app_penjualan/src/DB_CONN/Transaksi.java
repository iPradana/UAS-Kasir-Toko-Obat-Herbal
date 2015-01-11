package DB_CONN;

import java.sql.SQLException;
import java.sql.Statement;

public class Transaksi {
	
	private static koneksi con = new koneksi();
	
	public void Dtrx(String IDT, String IDP, int Jumlah, int Harga){
		try {
			Statement stmt = con.getConnection().createStatement();
			stmt.executeUpdate("insert into dTransaksi values('"+IDT+"','"+IDP+"',"+Jumlah+","+Harga+")");
			stmt.executeUpdate("update StokProduk set Stok=Stok-"+Jumlah+" where IDP='"+IDP+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void trx(String IDT, String IDU){
		Statement stmt;
		try {
			stmt = con.getConnection().createStatement();
			stmt.executeUpdate("insert into Transaksi values('"+IDT+"',dateOB(),'"+IDU+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
