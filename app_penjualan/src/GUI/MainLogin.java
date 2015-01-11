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

public class MainLogin extends JFrame{
	
	private Dimension Layar = Toolkit.getDefaultToolkit().getScreenSize();
	private Login login1 = new Login();
	private JPanel gl = new JPanel(new GridLayout(1,2));
	private JPanel lg = new JPanel();
	private JLabel lbT = new JLabel("<HTML><H3>TRANSAKSI</H3></HTML>");
	private JLabel lbM = new JLabel("<HTML><H3>MAINTENANCE</H3></HTML>");
	private BorderLayout bl = new BorderLayout();
	private Container k = getContentPane();
	private int pos=1;
	
	public MainLogin(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 175);
		setLocation((Layar.width - getWidth()) / 2, (Layar.height - getHeight()) / 2);
		setResizable(false);
		
		lbT.setForeground(Color.CYAN);
		lbM.setForeground(Color.CYAN);
		
		JButton BtnT = new JButton(); BtnT.add(lbT);
		JButton BtnM = new JButton(); BtnM.add(lbM);
		JButton BtnLg = new JButton("Login");
		BtnT.setBackground(Color.BLACK);
		BtnM.setBackground(Color.DARK_GRAY);
		
		gl.add(BtnM);
		gl.add(BtnT);
		
		lg.add(login1.getGL());
		
		k.setLayout(new BorderLayout());
		k.add(gl,BorderLayout.NORTH);
		k.add(lg,BorderLayout.CENTER);
		k.add(BtnLg,BorderLayout.SOUTH);
		
		BtnM.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BtnM.setBackground(Color.DARK_GRAY);
				BtnT.setBackground(Color.BLACK);
				pos=1;
			}
		});
		
		BtnT.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BtnT.setBackground(Color.DARK_GRAY);
				BtnM.setBackground(Color.BLACK);
				pos=2;
			}
		});
		
		BtnLg.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(login1.ask(pos)==true){
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
		MainLogin x = new MainLogin();
		x.setVisible(true);
	}
}
