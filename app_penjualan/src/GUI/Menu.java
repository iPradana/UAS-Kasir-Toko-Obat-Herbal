package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import DB_CONN.*;

public class Menu extends JFrame {
	
	private static koneksi con = new koneksi();
	private JPanel ATAS = new JPanel();
	private JPanel KIRI = new JPanel();
	private JPanel BAWAH = new JPanel();
	private JButton BtnProduk = new JButton();
	private JButton BtnSup = new JButton();
	private JButton BtnStok = new JButton();
	private JButton BtnUser = new JButton();
	private JButton BtnLaporan = new JButton();
	private JButton BtnKeluar = new JButton();
	private JLabel Prd = new JLabel("<HTML><H3>PRODUCTS</H3></HTML>");
	private JLabel Sup = new JLabel("<HTML><H3>SUPPLIERS</H3></HTML>");
	private JLabel Stok = new JLabel("<HTML><H3>STOCKS</H3></HTML>");
	private JLabel User = new JLabel("<HTML><H3>USERS</H3></HTML>");
	private JLabel Laporan = new JLabel("<HTML><H3>LAPORAN</H3></HTML>");
	private JLabel Keluar = new JLabel("<HTML><H3>KELUAR</H3></HTML>");
	private Dimension Layar = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel Box = new JPanel(new GridLayout(6,1,10,20));
	m_produk menu_produk = new m_produk();
	m_supplier menu_supplier = new m_supplier();
	m_stok menu_stok = new m_stok();
	m_user menu_user;
	laporan lap = new laporan();
	static MainLogin lg = new MainLogin();
	private String user;
	
	public Menu(String user){
		super(user);
		this.user=user;
		menu_user = new m_user(user);
		JLabel lblJudul = new JLabel("<HTML><H3>Maintenance (PRODUK)</H3></HTML>");
		lblJudul.setForeground(Color.CYAN);
		Prd.setForeground(Color.CYAN);
		Sup.setForeground(Color.CYAN);
		Stok.setForeground(Color.CYAN);
		User.setForeground(Color.CYAN);
		Laporan.setForeground(Color.CYAN);
		Keluar.setForeground(Color.CYAN);
		
		ATAS.setBackground(Color.BLACK);
		KIRI.setBackground(Color.DARK_GRAY);
		BtnProduk.setBackground(Color.BLACK);
		BtnProduk.add(Prd);
		BtnSup.setBackground(Color.BLACK);
		BtnSup.add(Sup);
		BtnStok.setBackground(Color.BLACK);
		BtnStok.add(Stok);
		BtnUser.setBackground(Color.BLACK);
		BtnUser.add(User);
		BtnLaporan.setBackground(Color.BLACK);
		BtnLaporan.add(Laporan);
		BtnKeluar.setBackground(Color.BLACK);
		BtnKeluar.add(Keluar);
		
		ATAS.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		KIRI.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		
		Box.setBackground(Color.DARK_GRAY);
		Box.add(BtnProduk);
		Box.add(BtnSup);
		Box.add(BtnStok);
		Box.add(BtnUser);
		Box.add(BtnLaporan);
		Box.add(BtnKeluar);
		KIRI.add(Box);
		ATAS.add(lblJudul);
		BAWAH.add(menu_produk.getBL());
		
		Container k = getContentPane();
		k.setLayout(new BorderLayout());
		k.add(ATAS, BorderLayout.NORTH);
		k.add(KIRI, BorderLayout.WEST);
		k.add(BAWAH,BorderLayout.CENTER);
		
		setSize(650,600);
		setLocation(((Layar.width - getWidth()) / 2),(Layar.height - getHeight()) / 2);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//-----------------------------------------------------------------------------------
		BtnKeluar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lg.setVisible(true);
				dispose();
			}
		});
		//-----------------------------------------------------------------------------------
		BtnProduk.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lblJudul.setText("<HTML><H3>Maintenance (PRODUK)</H3></HTML>");
				BAWAH.removeAll();
				BAWAH.setBackground(Color.WHITE);
				BAWAH.add(menu_produk.getBL());
				k.validate();
				k.repaint();
			}
		});
		//----------------------------------------------------------------------------------
		BtnSup.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lblJudul.setText("<HTML><H3>Maintenance (SUPPLIER)</H3></HTML>");
				BAWAH.removeAll();
				BAWAH.setBackground(Color.WHITE);
				BAWAH.add(menu_supplier.getBL());
				k.validate();
				k.repaint();
			}
		});
		//----------------------------------------------------------------------------------
		BtnStok.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lblJudul.setText("<HTML><H3>Maintenance (STOK)</H3></HTML>");
				BAWAH.removeAll();
				BAWAH.setBackground(Color.WHITE);
				BAWAH.add(menu_stok.getBL());
				k.validate();
				k.repaint();
			}
		});
		
		BtnUser.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lblJudul.setText("<HTML><H3>MAINTENANCE (USER)</H3></HTML>");
				BAWAH.removeAll();
				BAWAH.setBackground(Color.WHITE);
				BAWAH.add(menu_user.getGBL());
				k.validate();
				k.repaint();
			}
		});
		
		BtnLaporan.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lblJudul.setText("<HTML><H3>LAPORAN PENJUALAN</H3></HTML>");
				BAWAH.removeAll();
				BAWAH.setBackground(Color.DARK_GRAY);
				BAWAH.add(lap.getGBL());
				k.validate();
				k.repaint();
			}
		});
	}
}
