package GUI;

import DB_CONN.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class loginBaru extends JFrame {
    private static koneksi con = new koneksi();
	private JPanel GL = new JPanel(new GridLayout(2,2,5,10));
	private JTextField txtUsr = new JTextField(16);
	private JPasswordField txtPw = new JPasswordField(16);
	
	public loginBaru(){
		
		JLabel user = new JLabel("Username");
		JLabel pass = new JLabel("Password");
		
		txtUsr.setPreferredSize(new Dimension(70,24));
		
		GL.add(user); GL.add(txtUsr);
		GL.add(pass); GL.add(txtPw);
	}
	
	public Component getGL(){
		return GL;
	}
	
	public int ask(){
		int y=0;
		try {
			int a = 0;
			String username=txtUsr.getText();
			String password=txtPw.getText();
			Statement stmt = con.getConnection().createStatement();
			ResultSet r = stmt.executeQuery("select count(IDU) from Users where IDU='"+username+"' and Pass='"+password+"'");
			while(r.next()){
				a=r.getInt(1);
			}
			if(a>0){
				r = stmt.executeQuery("select Jenis from Users where IDU='"+username+"'");
				while(r.next()){
					y=r.getInt(1);
				}
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return y;
	}

	public String getUsername() {
		return txtUsr.getText();
	}
}
