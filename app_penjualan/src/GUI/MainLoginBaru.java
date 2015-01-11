package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainLoginBaru extends JFrame{
	
	private Dimension Layar = Toolkit.getDefaultToolkit().getScreenSize();
	private loginBaru login1 = new loginBaru();
	private JPanel lg = new JPanel();
	private Container k = getContentPane();
	private int pos=0;
	
	public MainLoginBaru(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 175);
		setLocation((Layar.width - getWidth()) / 2, (Layar.height - getHeight()) / 2);
		setResizable(false);
		
		JButton BtnLg = new JButton("Login");
		
		lg.add(login1.getGL());
		
		k.setLayout(new BorderLayout());
		k.add(lg,BorderLayout.CENTER);
		k.add(BtnLg,BorderLayout.SOUTH);
		
		BtnLg.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pos=login1.ask();
				if(pos!=0){
					String un = login1.getUsername();
					if(pos==1){
						Menu m = new Menu(un);
						m.setVisible(true);
					}else{
						MainM m = new MainM(un);
						m.setVisible(true);
					}
					dispose();
				}else{
					JOptionPane.showMessageDialog(null,"Username atau password salah","Login Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	public static void main (String []args){
		MainLoginBaru x = new MainLoginBaru();
		x.setVisible(true);
	}
}
