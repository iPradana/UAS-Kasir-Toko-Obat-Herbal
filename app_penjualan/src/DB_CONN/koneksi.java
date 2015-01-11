package DB_CONN;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class koneksi {
	private static Connection con;
	private String url = "jdbc:mckoi://localhost/";
	private String username = "admin";
	private String password = "admin";
	public void tesKoneksi() {
		try {
			Class.forName("com.mckoi.JDBCDriver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.exit(0);
		}
	}
	
	public Connection getConnection() {
		try {
			Class.forName("com.mckoi.JDBCDriver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.exit(0);
		}
		return con;
	}	
}