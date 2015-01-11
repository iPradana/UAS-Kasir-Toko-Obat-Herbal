package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DB_CONN.Transaksi;
import DB_CONN.koneksi;

public class MainM extends JFrame{
	private JLabel lblNP = new JLabel("Nama Produk");
	private JLabel lblIDT = new JLabel("ID Transaksi");
	private JLabel lblJ = new JLabel("Jumlah");
	private JLabel lblH = new JLabel("Harga");
	private JLabel lblT = new JLabel("Total");
	private JLabel lblGT = new JLabel("Grand Total");
	private JComboBox<String> CboNmp = new JComboBox<String>();
	private JComboBox<String> CboIDp = new JComboBox<String>();
	private JComboBox<Integer> CboJ = new JComboBox<Integer>();
	private JTextField txtIDT = new JTextField();
	private JTextField txtH = new JTextField();
	private JTextField txtT = new JTextField();
	private JTextField txtGT = new JTextField();
	private JButton BtnM = new JButton("Masukkan");
	private JButton BtnC = new JButton("Clear");
	private JButton BtnCetak = new JButton("Cetak");
	private JButton BtnKl = new JButton("Keluar");
	private JPanel gbl = new JPanel(new GridBagLayout());
	private JPanel bl = new JPanel(new BorderLayout());
	private JPanel gl = new JPanel(new GridLayout(2,1));
	private JPanel ATAS = new JPanel();
	private GridBagConstraints gbc = new GridBagConstraints();
	private MainLogin lg = new MainLogin();
	private Dimension Layar = Toolkit.getDefaultToolkit().getScreenSize();
	private DefaultTableModel dataModel = new DefaultTableModel(){
		@Override
		public boolean isCellEditable(int row, int column) {
        return false;}
    };
    private JTable table = new JTable(dataModel);
	private Vector<String> v = new Vector<String>();
	private Vector<Integer> vi = new Vector<Integer>();
    private koneksi con = new koneksi();
    private Transaksi t = new Transaksi();
	private int GTotal=0,Harga=0;
	private String IDP,user;
	
	public MainM(String user){
		super("Transaksi Penjualan ("+user+")");
		this.user=user;
		JLabel lblJudul = new JLabel("<HTML><H3>PENJUALAN PRODUK</H3></HTML>");
		lblJudul.setForeground(Color.CYAN);
		lblNP.setForeground(Color.GREEN);
		lblIDT.setForeground(Color.GREEN);
		lblJ.setForeground(Color.GREEN);
		lblH.setForeground(Color.GREEN);
		lblT.setForeground(Color.GREEN);
		lblGT.setForeground(Color.GREEN);
		
		txtIDT.setEditable(false); txtIDT.setBackground(Color.LIGHT_GRAY);
		txtH.setEditable(false); txtH.setBackground(Color.LIGHT_GRAY);
		txtT.setEditable(false); txtT.setBackground(Color.LIGHT_GRAY);
		txtGT.setEditable(false); txtGT.setBackground(Color.LIGHT_GRAY);
		txtIDT.setPreferredSize(new Dimension(100,24));
		CboJ.setPreferredSize(new Dimension(100,24));
		
		ATAS.setBackground(Color.BLACK);
		ATAS.add(lblJudul);
		
		dataModel.addColumn("IDP");
		dataModel.addColumn("Nama Produk");
		dataModel.addColumn("Jumlah");
		dataModel.addColumn("Harga");
		dataModel.addColumn("Total");
	    table.getColumnModel().getColumn(1).setPreferredWidth(250);
	    JScrollPane scrollpane = new JScrollPane(table);
	    scrollpane.setPreferredSize(new Dimension(400, 330));
	    
	    rProduk();
		
	    gbc.insets = new Insets(5, 5, 5, 5);
	    
	    gbc.gridx = 0; gbc.gridy = 0;
	    gbl.add(lblIDT, gbc);
	    gbc.gridx = 1;
	    gbl.add(txtIDT,gbc);
	    
	    gbc.gridx = 0; gbc.gridy = 1;
	    gbl.add(lblNP, gbc);
	    gbc.gridx = 1;
	    gbc.gridwidth = 3;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbl.add(CboNmp,gbc);
	    
	    gbc.gridx = 0; gbc.gridy = 2;
	    gbc.gridwidth = 1;
	    gbl.add(lblH,gbc);
	    gbc.gridx = 1;
	    gbl.add(txtH,gbc);
	    gbc.gridx = 2;
	    gbl.add(lblJ,gbc);
	    gbc.gridx = 3;
	    gbl.add(CboJ,gbc);
	    
	    gbc.gridx = 0; gbc.gridy = 3;
	    gbl.add(lblT,gbc);
	    gbc.gridx = 1;
	    gbl.add(txtT,gbc);
	    gbc.gridx = 2;
	    gbl.add(BtnM,gbc);
	    gbc.gridx = 3;
	    gbl.add(BtnC,gbc);
	    
	    gbc.gridx = 0; gbc.gridy = 4;
	    gbl.add(lblGT,gbc);
	    gbc.gridx = 1; gbc.gridwidth = 3;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbl.add(txtGT,gbc);
	    
	    gbc.gridx = 1; gbc.gridy = 5;
	    gbc.gridwidth = 2;
	    BtnCetak.setPreferredSize(new Dimension(100, 50));
	    gbl.add(BtnCetak,gbc);
	    
	    gbl.setBackground(Color.DARK_GRAY);
	    gl.setBackground(Color.DARK_GRAY);
	    gl.add(gbl);
	    
	    bl.add(BtnKl,BorderLayout.SOUTH);
	    gl.add(bl);
	    
		Container k = getContentPane();
		k.setLayout(new BorderLayout());
		k.add(ATAS, BorderLayout.NORTH);
		k.add(gl, BorderLayout.WEST);
		k.add(scrollpane,BorderLayout.CENTER);
		
		setSize(800,600);
		setLocation(((Layar.width - getWidth()) / 2),(Layar.height - getHeight()) / 2);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		BtnKl.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lg.setVisible(true);
				dispose();
			}
		});
		
		BtnM.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				v.addElement(CboIDp.getSelectedItem().toString());
				v.addElement(CboNmp.getSelectedItem().toString());
				vi.addElement(CboJ.getSelectedIndex());
				vi.addElement(Harga);
				v.addElement(txtT.getText());
				dataModel.addRow(new Object[]{v.elementAt(0),v.elementAt(1),vi.elementAt(0),vi.elementAt(1),v.elementAt(2)});
				v.removeAllElements();
				vi.removeAllElements();
				GTotal+=Harga*(CboJ.getSelectedIndex());
				txtGT.setText(GTotal+"");
			}
		});
		
		BtnC.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dataModel.setRowCount(0);
			}
		});
		
		BtnCetak.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(dataModel.getRowCount()>0){
					int baris,kolom=3;
					baris = dataModel.getRowCount();
					baris-=1;
					t.trx(txtIDT.getText(), user);
					while(baris>=0){
						String IDTransaksi = txtIDT.getText();
						String IDProduk = dataModel.getValueAt(baris, 0).toString();
						int Jumlah=(int)dataModel.getValueAt(baris, 2);
						int HargaBrg=(int)dataModel.getValueAt(baris, 3);
						t.Dtrx(txtIDT.getText(), IDProduk, Jumlah, HargaBrg);
						baris--;
					}
					Harga = 0; txtH.setText(Harga+"");
					GTotal = 0; txtGT.setText(GTotal+"");
					rProduk();
					dataModel.setRowCount(0);
				}else{
					JOptionPane.showMessageDialog(null,"Masukkan transaksi ke tabel","Insert Rejected",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		CboNmp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Statement stmt;
				try {
					CboIDp.setSelectedIndex(CboNmp.getSelectedIndex());
					stmt = con.getConnection().createStatement();
					ResultSet r = stmt.executeQuery("select Harga from Produk where IDP='"+CboIDp.getSelectedItem()+"'");
					while(r.next()){
						Harga = r.getInt(1);
						txtH.setText(Integer.toString(Harga));
					}
					stmt.close();
					stmt = con.getConnection().createStatement();
					r = stmt.executeQuery("select Stok from StokProduk where IDP='"+CboIDp.getSelectedItem()+"'");
					while(r.next()){
						int x=r.getInt(1),y=0;
						CboJ.removeAllItems();
						while(x>=0){
							CboJ.addItem(y);
							y++;
							x--;
						}
					}
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		CboJ.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtH.getText().equalsIgnoreCase(null) || txtH.getText().equalsIgnoreCase("")){
					txtT.setText("0");
				}else{
					txtT.setText(Harga*CboJ.getSelectedIndex()+"");
				}
			}
		});
	}

	private void rProduk() {
		try {
			CboNmp.removeAllItems();
			Statement stmt = con.getConnection().createStatement();
			ResultSet r = stmt.executeQuery("select NamaP, IDP from Produk order by NamaP Asc");
			while(r.next()){
				v.addElement(r.getString(1));
				v.addElement(r.getString(2));
				CboNmp.addItem(v.elementAt(0));
				CboIDp.addItem(v.elementAt(1));
				v.removeAllElements();
			}
			r=stmt.executeQuery("Select count(IDT) from Transaksi");
			while(r.next()){
				String nomor;
				if(r.getInt(1)<8){
					nomor="00"+(r.getInt(1)+1);
				}else if(r.getInt(1)<98){
					nomor="0"+(r.getInt(1)+1);
				}else{
					nomor=""+(r.getInt(1)+1);
				}
				txtIDT.setText("TRX"+nomor);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
