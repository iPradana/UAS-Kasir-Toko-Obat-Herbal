package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.xml.stream.events.Comment;

import DB_CONN.Users;
import DB_CONN.koneksi;

public class m_user extends JFrame{
	private JLabel IDU = new JLabel("ID User");
	private JLabel NMU = new JLabel("Nama User");
	private JLabel ACC = new JLabel("Akses");
	private JLabel PW = new JLabel("Password");
	private JLabel Tambah = new JLabel("<HTML><H3>TAMBAH</H3></HTML>");
	private JLabel Ubah = new JLabel("<HTML><H3>UBAH</H3></HTML>");
	private JLabel Hapus = new JLabel("<HTML><H3>HAPUS</H3></HTML>");
	private JComboBox<String> CboIDU = new JComboBox<String>();
	private JTextField txtNMU = new JTextField();
	private JTextField txtPW = new JTextField(12);
	private JTextField txtID = new JTextField(6);
	private JComboBox CboACC = new JComboBox();
	private JButton BtnS = new JButton("TAMBAH");
	private JButton BtnTambah = new JButton();
	private JButton BtnUbah = new JButton();
	private JButton BtnHapus = new JButton();
	private JPanel gbl = new JPanel(new GridBagLayout());
	private JPanel PanelID = new JPanel();
	private GridBagConstraints gbc = new GridBagConstraints();
	private DefaultTableModel dataModel = new DefaultTableModel(){
		@Override
		public boolean isCellEditable(int row, int column) {
        return false;}
    };
    private JTable table = new JTable(dataModel);
    private JPanel bl = new JPanel(new BorderLayout());
    private JPanel gl = new JPanel(new GridLayout(1,3,15,10));
	private int pos;
	private Vector<String> v = new Vector<>();
    private koneksi con = new koneksi();
    private Users usr = new Users();
    private String UserLogin;
    
	public m_user(String UserLogin){
		this.UserLogin=UserLogin;
		IDU.setForeground(Color.cyan);
		NMU.setForeground(Color.cyan);
		ACC.setForeground(Color.cyan);
		PW.setForeground(Color.cyan);
		
		CboIDU.setPreferredSize(new Dimension(70,24));
		PanelID.add(txtID);
		pos=1;
		
		BtnTambah.setBackground(Color.DARK_GRAY);;
		BtnUbah.setBackground(Color.BLACK);
		BtnHapus.setBackground(Color.BLACK);
		
		Tambah.setForeground(Color.GREEN);
		Ubah.setForeground(Color.GREEN);
		Hapus.setForeground(Color.GREEN);
		
		BtnTambah.add(Tambah);
		BtnUbah.add(Ubah);
		BtnHapus.add(Hapus);
		
		dataModel.addColumn("ID");
		dataModel.addColumn("Nama User");
		dataModel.addColumn("Akses");
	    table.getColumnModel().getColumn(1).setPreferredWidth(400);
	    JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(480, 380));
		
		rUser();
		
		CboACC.addItem("Kasir");
		CboACC.addItem("Admin");
		
		gl.add(BtnTambah);
		gl.add(BtnUbah);
		gl.add(BtnHapus);
		
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gbc.gridx = 0; gbc.gridy = 0;
		gbl.add(IDU,gbc);
		gbc.gridx = 1;
		gbl.add(PanelID, gbc);
		gbc.gridx = 2;
		gbl.add(NMU,gbc);
		gbc.gridx = 3;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbl.add(txtNMU, gbc);
		
		gbc.gridx = 0; gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbl.add(ACC,gbc);
		gbc.gridx = 1;
		gbl.add(CboACC, gbc);
		gbc.gridx = 2;
		gbl.add(PW,gbc);
		gbc.gridx = 3;
		gbl.add(txtPW, gbc);
		gbc.gridx = 4;
		gbl.add(BtnS,gbc);
		
		gbc.gridx=0; gbc.gridy=2;
		gbc.gridwidth=5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbl.add(scrollpane,gbc);
		
		gbl.setBackground(Color.DARK_GRAY);
		
		bl.add(gl,BorderLayout.NORTH);
		bl.add(new JLabel(),BorderLayout.CENTER);
		bl.add(gbl,BorderLayout.SOUTH);
		
		//-------------------------------------------------------------------------
		//-------------------------------------------------------------------------
		BtnTambah.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BtnTambah.setBackground(Color.DARK_GRAY);
				BtnUbah.setBackground(Color.BLACK);
				BtnHapus.setBackground(Color.BLACK);
				pos = 1;
				txtPW.setText("");
				txtPW.setEnabled(true);
				txtNMU.setText("");
				txtNMU.setEnabled(true);
				CboIDU.setEnabled(false);
				CboACC.setEnabled(true);
				BtnS.setText("Tambah");
				PanelID.removeAll();
				PanelID.add(txtID);
				rUser();
				PanelID.validate();
				PanelID.repaint();
			}
		});
		
		BtnUbah.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BtnTambah.setBackground(Color.BLACK);
				BtnUbah.setBackground(Color.DARK_GRAY);
				BtnHapus.setBackground(Color.BLACK);
				pos = 2;
				CboIDU.setEnabled(true);
				CboACC.setEnabled(true);
				txtPW.setText("");
				txtPW.setEnabled(true);
				txtNMU.setText("");
				txtNMU.setEnabled(true);
				BtnS.setText("Ubah");
				PanelID.removeAll();
				PanelID.add(CboIDU);
				rUser();
				PanelID.validate();
				PanelID.repaint();
			}
		});
		
		BtnHapus.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BtnTambah.setBackground(Color.BLACK);
				BtnUbah.setBackground(Color.BLACK);
				BtnHapus.setBackground(Color.DARK_GRAY);
				CboIDU.setEnabled(true);
				CboACC.setEnabled(false);
				txtPW.setText("");
				txtPW.setEnabled(false);
				txtNMU.setText("");
				txtNMU.setEnabled(false);
				pos = 3;
				BtnS.setText("Hapus");
				PanelID.removeAll();
				PanelID.add(CboIDU);
				rUser();
				PanelID.validate();
				PanelID.repaint();
			}
		});
		
		BtnS.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(pos==1){
					if(txtID.getText().equals("") || txtPW.getText().equals("") || txtNMU.getText().equals("")){
						JOptionPane.showMessageDialog(null,"Isilah field yang kosong","Insert Error",JOptionPane.ERROR_MESSAGE);
					}else if(txtID.getDocument().getLength()>6 || txtID.getDocument().getLength()<6){
						JOptionPane.showMessageDialog(null,"ID harus 6 digit","Insert Error",JOptionPane.ERROR_MESSAGE);
					}else{
						Statement stmt;
						try {
							stmt = con.getConnection().createStatement();
							int a=0;
							ResultSet r = stmt.executeQuery("select count(IDU) from Users where IDU='"+txtID.getText()+"'");
							while(r.next()){
								a=r.getInt(1);
							}
							if(a==0){
								int x=0;
								if(CboACC.getSelectedItem().equals("Admin")){
									x=1;
								}else{
									x=2;
								}
								usr.tambahU(txtID.getText(), txtNMU.getText(), txtPW.getText(), x);
								rUser();
								JOptionPane.showMessageDialog(null,"user telah diMasukkan");
							}else{
								JOptionPane.showMessageDialog(null,"User sudah ada","Delete Error",JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}else if(pos==2){
					if(txtNMU.equals("") || txtNMU.getText().isEmpty()){
						JOptionPane.showMessageDialog(null,"Masukkan nama user","Update Error",JOptionPane.ERROR_MESSAGE);
					}else{
						int x=0;
						if(CboACC.getSelectedItem().equals("Admin")){
							x=1;
						}else{
							x=2;
						}
						usr.updateU(CboIDU.getSelectedItem().toString(), txtNMU.getText(), txtPW.getText(), x);
						JOptionPane.showMessageDialog(null,"user telah diubah");
						rUser();
					}
				}else{
					if(CboIDU.getSelectedItem().toString().equalsIgnoreCase(UserLogin)){
						JOptionPane.showMessageDialog(null,"Anda tidak bisa menghapus akun anda","Delete Error",JOptionPane.ERROR_MESSAGE);
					}else{
						usr.HapusU(CboIDU.getSelectedItem().toString());
						rUser();
						JOptionPane.showMessageDialog(null,"user telah diHapus");
					}
				}
			}
		});
	}
	
	private void rUser() {
		dataModel.setRowCount(0);
		try {
			CboIDU.removeAllItems();
			Statement stmt = con.getConnection().createStatement();
			ResultSet r = stmt.executeQuery("select * from Users");
			while(r.next()){
				v.addElement(r.getString(1));
				v.addElement(r.getString(2));
				if(r.getInt(4)==1){
					v.addElement("Admin");
				}else{
					v.addElement("Kasir");
				}
				dataModel.addRow(new Object[]{v.elementAt(0),v.elementAt(1),v.elementAt(2)});
				CboIDU.addItem(r.getString(1));
				v.removeAllElements();
			}
			CboIDU.setSelectedIndex(0);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Component getGBL(){
		rUser();
		return bl;
	}
}
