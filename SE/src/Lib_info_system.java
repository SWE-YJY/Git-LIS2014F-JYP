
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Lib_info_system extends JFrame implements ActionListener{
	
	private JLabel l_id, l_pw, l_name, l_dept;
	private JTextField txt_id, txt_name, txt_dept;
	private JPasswordField txt_pw;
	private JButton  b_login, b_logout, b_signup, b_librarian, 
				b_student, b_register, b_cancel;
	
	private JLabel l_blank, l_title, l_authors, l_publisher, l_isbn, 
					l_availability, l_renting_student, l_search;
	private JTextField txt_title, txt_authors, txt_publisher, txt_isbn, 
				txt_renting_student, txt_keyword;
	private JTable table;
	private JScrollPane scroll;
	private JRadioButton availability_yes, availability_no;
	private ButtonGroup availability;
	private JPanel south, north, center, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12;
	private JButton  b_search, b_insert, b_delete, b_update, b_rent_list;
	
	private String select_availability, select_position;
	private String driver = "com.mysql.jdbc.Driver";
	private String user = "root";
	private String pass = "kjsryh5223";
	private String dbURL = "jdbc:mysql://localhost:3307/lib_info_system";	
	private Connection conn;
	private PreparedStatement prestate;
	private ResultSet rs, duplicate_checker;		
	
	
	private MemberModel model = new MemberModel();
	int selRow; 

	public Lib_info_system()
	{
		super("���� ���� ���� ���α׷�");
		
		// ���̾ƿ�
		l_id = new JLabel("ID");
		l_pw = new JLabel("PW");
		l_name = new JLabel("�̸�");
		l_dept = new JLabel("����");
		
		txt_id = new JTextField(10);
		txt_pw = new JPasswordField(10);
		txt_name = new JTextField(10);
		txt_dept = new JTextField(10);
		
		b_login = new JButton("�α���");
		b_logout = new JButton("�α׾ƿ�");
		b_signup = new JButton("ȸ������");
		b_librarian = new JButton("�缭����");
		b_student = new JButton("�л�����");
		b_register = new JButton("ȸ�����");
		b_cancel = new JButton("�������"); 
		
		l_blank = new JLabel("  ");
		l_title = new JLabel("�� �� �� ��");
		l_authors = new JLabel("��            ��");
		l_publisher = new JLabel("��    ��    ��");
		l_isbn = new JLabel("I    S    B    N");
		l_availability = new JLabel("�뿩���ɿ���");
		l_renting_student = new JLabel("�������л�");
		l_search = new JLabel("�����˻�");
		
		txt_title = new JTextField(20);
		txt_authors = new JTextField(20);
		txt_publisher = new JTextField(20);
		txt_isbn = new JTextField(20);
		txt_renting_student = new JTextField(20);
		txt_keyword = new JTextField(20);
		
		availability = new ButtonGroup();
		availability_yes = new JRadioButton("�뿩����", false);
		availability_no = new JRadioButton("�뿩�Ұ�", false);
		availability.add(availability_yes);
		availability.add(availability_no);
		
		b_search = new JButton("�˻�");
		b_insert = new JButton("�߰�");
		b_delete = new JButton("����");
		b_update = new JButton("����");
		b_rent_list = new JButton("�뿩���Ȯ��");
		
		table = new JTable(model);
		scroll = new JScrollPane(table);
				
		south = new JPanel();
		north = new JPanel();
		center = new JPanel();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		p6 = new JPanel();
		p7 = new JPanel();
		p8 = new JPanel();
		p9 = new JPanel();
		p10 = new JPanel();
		p11 = new JPanel();
		p12 = new JPanel();
		
		north.setPreferredSize(new Dimension(600, 400));
		center.setPreferredSize(new Dimension(500, 200));
		south.setPreferredSize(new Dimension(500, 50));

		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);		
		
		north.setLayout(new GridLayout(12, 1));
		north.add(p1);
		north.add(p2);
		north.add(p3);
		north.add(p4);
		north.add(p5);
		north.add(p6);
		north.add(p7);
		north.add(p8);
		north.add(p9);
		north.add(p10);
		north.add(p11);
		north.add(p12);
		
		p1.add(l_id);
		p1.add(txt_id);
		p1.add(l_pw);
		p1.add(txt_pw);
		p1.add(b_login);
		p1.add(b_logout);
		
		p2.add(b_signup);
		p2.add(b_librarian);
		p2.add(b_student);
		
		
		p3.add(l_name);
		p3.add(txt_name);
		p3.add(l_blank);
		p3.add(l_dept);
		p3.add(txt_dept);
		p3.add(l_blank);
		p3.add(l_blank);
		
		p4.add(b_register);
		p4.add(b_cancel);
		
		p5.add(l_blank);
		
		p6.add(l_title);
		p6.add(txt_title); 
		
		p7.add(l_authors);
		p7.add(txt_authors);
				
		p8.add(l_publisher);
		p8.add(txt_publisher);
		
		p9.add(l_isbn);
		p9.add(txt_isbn);
		
		p10.add(l_availability);
		p10.add(availability_yes);
		p10.add(availability_no);
		
		p11.add(l_renting_student);
		p11.add(txt_renting_student);
		
		p12.setLayout(new FlowLayout(FlowLayout.CENTER));
		p12.add(l_search);
		p12.add(txt_keyword);
		p12.add(b_search);
		
		center.setLayout(new GridLayout(1, 1));
		center.add(scroll);
		table.setAutoCreateRowSorter(true);		

		south.setLayout(new GridLayout(1, 4));
		south.add(b_insert);
		south.add(b_delete);
		south.add(b_update);
		south.add(b_rent_list);
		
		b_login.addActionListener(this);
		b_logout.addActionListener(this);
		b_signup.addActionListener(this);
		b_register.addActionListener(this);
		b_librarian.addActionListener(this);
		b_student.addActionListener(this);
		b_cancel.addActionListener(this);
		
		b_search.addActionListener(this);
		b_insert.addActionListener(this);
		b_delete.addActionListener(this);
		b_update.addActionListener(this);
		b_rent_list.addActionListener(this);
		

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selRow = table.getSelectedRow();

				String title = (String) table.getValueAt(selRow, 0);
				String authors = (String) table.getValueAt(selRow, 1);
				String publisher = (String) table.getValueAt(selRow, 2);
				String isbn = (String) table.getValueAt(selRow, 3);
				String renting_student = (String) table.getValueAt(selRow, 4);
				String availability = (String) table.getValueAt(selRow, 5);
				
				txt_title.setText(title);
				txt_authors.setText(authors);
				txt_publisher.setText(publisher);
				txt_isbn.setText(isbn);
				txt_renting_student.setText(renting_student);
				
				txt_title.setEditable(true);
				txt_authors.setEditable(true);
				txt_publisher.setEditable(true);
				txt_isbn.setEditable(true);
				txt_renting_student.setEditable(true);
				
				if(availability==null)
				{
					availability_yes.setSelected(false);
					availability_no.setSelected(false);
				}
				else 
				{
					 if (availability.equals("�뿩����")){
						 availability_yes.setSelected(true);
					 }
					 else if (availability.equals("�뿩�Ұ�")) {
						 availability_no.setSelected(true);
					 }
				}
				
			}
		});

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeDB();
				System.exit(0);
			}
		});
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();    
		int xPos = screenSize.width/2 - getSize().width/2 ;
		int yPos = screenSize.height/2 - getSize().height/2 ;  
		setLocation(xPos,yPos);
		
		
	
		layout_default();
		setVisible(true);
	}
	//
	public void connect()
	{
		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(dbURL, user, pass);
			
			if(conn != null)
				System.out.println("DB ���� ����");
			else
			{
				System.out.println("DB ���� ����");
			}
		}
		catch(SQLException se)
		{
			System.out.println("sql error");
		}
		catch(ClassNotFoundException cne)
		{
			System.out.println("jdbc driver not founded");
		}
	}
	//
	public void closeDB() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (prestate != null)
			try {
				prestate.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	//
	private void layout_default()
	{
	
		txt_id.setEditable(true);
		txt_pw.setEditable(true);
		txt_name.setEditable(false);
		txt_dept.setEditable(false);
		
		b_login.setEnabled(true);
		b_logout.setEnabled(false);
		b_signup.setEnabled(true);
		b_librarian.setEnabled(false);
		b_student.setEnabled(false);
		b_register.setEnabled(false);
		b_cancel.setEnabled(false);

		txt_title.setEditable(false);
		txt_authors.setEditable(false);
		txt_publisher.setEditable(false);
		txt_isbn.setEditable(false);
		txt_renting_student.setEditable(false);
		txt_keyword.setEditable(false);
		
		availability_yes.setEnabled(false);
		availability_no.setEnabled(false);
		
		b_search.setEnabled(false);
		b_insert.setEnabled(false);
		b_delete.setEnabled(false);
		b_update.setEnabled(false);
		b_rent_list.setEnabled(false);
	}
	
	private void layout_select_signtype()
	{
	
		txt_id.setEditable(false);
		txt_pw.setEditable(false);
		txt_name.setEditable(false);
		txt_dept.setEditable(false);
		
		b_login.setEnabled(false);
		b_logout.setEnabled(false);
		b_signup.setEnabled(false);
		b_librarian.setEnabled(true);
		b_student.setEnabled(true);
		b_register.setEnabled(false);
		b_cancel.setEnabled(true);

		txt_title.setEditable(false);
		txt_authors.setEditable(false);
		txt_publisher.setEditable(false);
		txt_isbn.setEditable(false);
		txt_renting_student.setEditable(false);
		txt_keyword.setEditable(false);
		
		availability_yes.setEnabled(false);
		availability_no.setEnabled(false);
		
		b_search.setEnabled(false);
		b_insert.setEnabled(false);
		b_delete.setEnabled(false);
		b_update.setEnabled(false);
		b_rent_list.setEnabled(false);
	}	
	
	private void layout_librarian_signup()
	{
	
		txt_id.setEditable(true);
		txt_pw.setEditable(true);
		txt_name.setEditable(true);
		txt_dept.setEditable(false);
		
		b_login.setEnabled(false);
		b_logout.setEnabled(false);
		b_signup.setEnabled(false);
		b_librarian.setEnabled(false);
		b_student.setEnabled(false);
		b_register.setEnabled(true);
		b_cancel.setEnabled(true);
		
		txt_title.setEditable(false);
		txt_authors.setEditable(false);
		txt_publisher.setEditable(false);
		txt_isbn.setEditable(false);
		txt_renting_student.setEditable(false);
		txt_keyword.setEditable(false);
		
		availability_yes.setEnabled(false);
		availability_no.setEnabled(false);
		
		b_search.setEnabled(false);
		b_insert.setEnabled(false);
		b_delete.setEnabled(false);
		b_update.setEnabled(false);
		b_rent_list.setEnabled(false);
	}
	
	private void layout_student_signup()
	{
	
		txt_id.setEditable(true);
		txt_pw.setEditable(true);
		txt_name.setEditable(true);
		txt_dept.setEditable(true);
		
		b_login.setEnabled(false);
		b_logout.setEnabled(false);
		b_signup.setEnabled(false);
		b_librarian.setEnabled(false);
		b_student.setEnabled(false);
		b_register.setEnabled(true);
		b_cancel.setEnabled(true);
		
		txt_title.setEditable(false);
		txt_authors.setEditable(false);
		txt_publisher.setEditable(false);
		txt_isbn.setEditable(false);
		txt_renting_student.setEditable(false);
		txt_keyword.setEditable(false);
		
		availability_yes.setEnabled(false);
		availability_no.setEnabled(false);
		
		b_search.setEnabled(false);
		b_insert.setEnabled(false);
		b_delete.setEnabled(false);
		b_update.setEnabled(false);
		b_rent_list.setEnabled(false);
	}
	//
	private void able_by_student_login() {
		// TODO Auto-generated method stub
		
		txt_id.setEditable(false);
		txt_pw.setEditable(false);
		txt_name.setEditable(false);
		txt_dept.setEditable(false);
		
		b_login.setEnabled(false);
		b_logout.setEnabled(true);
		b_signup.setEnabled(false);
		b_librarian.setEnabled(false);
		b_student.setEnabled(false);
		b_register.setEnabled(false);
		b_cancel.setEnabled(false);
		
		txt_title.setEditable(false);
		txt_authors.setEditable(false);
		txt_publisher.setEditable(false);
		txt_isbn.setEditable(false);
		txt_renting_student.setEditable(false);
		txt_keyword.setEditable(true);
		
		availability_yes.setEnabled(false);
		availability_no.setEnabled(false);
		
		b_search.setEnabled(true);
		b_insert.setEnabled(false);
		b_delete.setEnabled(false);
		b_update.setEnabled(false);
		b_rent_list.setEnabled(true);
	}
	//
	private void able_by_librarian_login() {
		// TODO Auto-generated method stub
		
		txt_id.setEditable(false);
		txt_pw.setEditable(false);
		txt_name.setEditable(false);
		txt_dept.setEditable(false);
		
		b_login.setEnabled(false);
		b_logout.setEnabled(true);
		b_signup.setEnabled(false);
		b_librarian.setEnabled(false);
		b_student.setEnabled(false);
		b_register.setEnabled(false);
		b_cancel.setEnabled(false);
		
		txt_title.setEditable(false);
		txt_authors.setEditable(false);
		txt_publisher.setEditable(false);
		txt_isbn.setEditable(false);
		txt_renting_student.setEditable(false);
		txt_keyword.setEditable(true);
		
		availability_yes.setEnabled(false);
		availability_no.setEnabled(false);
		
		b_search.setEnabled(true);
		b_insert.setEnabled(true);
		b_delete.setEnabled(true);
		b_update.setEnabled(true);
		b_rent_list.setEnabled(false);
	}
	//
	private void user_clear(){
		txt_id.setText("");
		txt_pw.setText("");
		txt_name.setText("");
		txt_dept.setText("");
	}
	//
	private void lib_clear(){		
		txt_title.setText("");
		txt_authors.setText("");
		txt_publisher.setText("");
		txt_isbn.setText("");
		txt_renting_student.setText("");
		
		availability_yes.setSelected(false);
		availability_no.setSelected(false);
		
	}
	//
	private void all_clear(){
		user_clear();
		lib_clear();
		txt_keyword.setText("");
	}
	//
	private boolean login(String id, String pw) {
		// TODO Auto-generated method stub
		
		boolean result = true; 
		String sql = "select * from user where id ='"+id+"' and pw='"+pw+"';";
		try
		{
			prestate = conn.prepareStatement(sql);
			rs = prestate.executeQuery();
			if(rs.next())
			{
				String name = (rs.getString("username"));
				String dept = (rs.getString("dept"));
				
				txt_name.setText(name);
				txt_dept.setText(dept);
			}
			else
			{
				result = false;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	//
	private void user_insert(String id, String pw, String name,
			String position) {
		// TODO Auto-generated method stub
		String sql= "insert into user (id, pw, username, position) values(?,?,?,?);";
		try
		{
			prestate = conn.prepareStatement(sql);
			prestate.setString(1, id);
			prestate.setString(2, pw);
			prestate.setString(3, name);
			prestate.setString(4, position);
			prestate.executeUpdate();
				
			System.out.println("�缭 ��� ����");
			//JOptionPane.showMessageDialog(getParent(), "ȸ����� ����");	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void user_insert(String id, String pw, String name, String dept, String position) {
		// TODO Auto-generated method stub
		String sql= "insert into user values(?,?,?,?,?);";
		try
		{
			prestate = conn.prepareStatement(sql);
			prestate.setString(1, id);
			prestate.setString(2, pw);
			prestate.setString(3, name);
			prestate.setString(4, dept);
			prestate.setString(5, position);
			prestate.executeUpdate();
				
			System.out.println("�л� ��� ����");
			//JOptionPane.showMessageDialog(getParent(), "ȸ����� ����");	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	//
	private boolean check_user_info(String id, String pw, String name, String dept, String position) {
		// TODO Auto-generated method stub
		boolean result = true;
		if(id == null || id.length() == 0)
		{
			System.out.println("ID(�й� Ȥ�� ���)�� �Է����ֽʽÿ�.");
			//JOptionPane.showMessageDialog(getParent(), "ID(�й� Ȥ�� ���)�� �Է����ֽʽÿ�.");
			result = false;
		}
		else if(id.length()>10 || id.matches("[0-9]"))
		{
			System.out.println("ID(�й� Ȥ�� ���)�� 10�� �̳��� �Է����ֽʽÿ�.");
			//JOptionPane.showMessageDialog(getParent(), "ID(�й� Ȥ�� ���)�� 10�� �̳��� ������ �Է����ֽʽÿ�.");
			txt_id.setText("");
			result = false;
		}
		else
		{
			char typecheck;
			for (int i = 0; i < id.length(); i++)
			{
				typecheck = id.charAt(i);
				if( typecheck < 48 || typecheck > 58)
				{
					//�ش� char���� ���ڰ� �ƴ� ���
					System.out.println("ID(�й� Ȥ�� ���)�� ���ڷ� �Է����ֽʽÿ�.");
					txt_id.setText("");
					result = false;
					break;
				}
				
			}		
			
		}
		
		if(pw == null || pw.length() == 0)
		{
			System.out.println("PW�� �Է����ֽʽÿ�.");
			//JOptionPane.showMessageDialog(getParent(), "PW�� �Է����ֽʽÿ�.");
			result = false;
		}
		else if(pw.length() > 15)
		{
			System.out.println("PW�� 15�� �̳��� �Է����ֽʽÿ�.");
			//JOptionPane.showMessageDialog(getParent(), "PW�� 15�� �̳��� �Է����ֽʽÿ�.");
			txt_pw.setText("");
			result = false;
		}
		
		if(name == null || name.length() == 0)
		{
			System.out.println("�̸��� �Է����ֽʽÿ�.");
			//JOptionPane.showMessageDialog(getParent(), "�̸��� �Է����ֽʽÿ�.");
			result = false;
		}
		else if(name.length() > 30)
		{
			System.out.println("�̸��� 30�� �̳��� �Է����ֽʽÿ�.");
			//JOptionPane.showMessageDialog(getParent(), "�̸��� 30�� �̳��� �Է����ֽʽÿ�.");
			txt_name.setText("");
			result = false;
		}
			
		if(position.equals("�л�"))
		{
			if(dept == null || dept.length() == 0)
			{
				System.out.println("������ �Է����ֽʽÿ�.");
				//JOptionPane.showMessageDialog(getParent(), "������ �Է����ֽʽÿ�.");
				result = false;
			}
			else if(dept.length()>30)
			{
				System.out.println("������ 30�� �̳��� �Է����ֽʽÿ�.");
				//JOptionPane.showMessageDialog(getParent(), "������ 30�� �̳��� �Է����ֽʽÿ�.");
				txt_dept.setText("");
				result = false;
			}
		}
		//error_checker = false;
		
		return result;
	}
	//not realization
	private void is_null() {
		// TODO Auto-generated method stub	
	}
	//not realization
	private void insert() {
		// TODO Auto-generated method stub
	}
	//not realization
	private void duplicate_cosmetic() {
		// TODO Auto-generated method stub		
	}
	//not realization
	private void update() {
		// TODO Auto-generated method stub
	}
	//not realization
	private void delete() {
		// TODO Auto-generated method stub
	}
	//not realization
	public void Listing_all_book() {
		// TODO Auto-generated method stub
	}
	//not realization
	private void search() {
		// TODO Auto-generated method stub
	}
	//
	private String get_position(String id, String pw) {
		String position = null;
		String sql = "select position from user where id ='"+id+"' and pw='"+pw+"';";
		try
		{
			prestate = conn.prepareStatement(sql);
			rs = prestate.executeQuery();
			if(rs.next())
			{
				position = (rs.getString("position"));
			}
		}
		catch (SQLException er)
		{
			er.printStackTrace();
		}
		return position;
	}
	
	//
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(availability_yes.isSelected())
			select_availability = "�뿩����";	
		else if(availability_no.isSelected())
			select_availability = "�뿩�Ұ�";		
		
		Object button = e.getSource();
		//
		if(button == b_login)
		{
			String id = txt_id.getText();
			String pw = txt_pw.getText();
			String get_position;
			// DB����
			connect();
			if(login(id, pw))
			{
				get_position = get_position(id, pw);
				if(get_position.equals("�缭"))
					able_by_librarian_login();
				else
					able_by_student_login();
				Listing_all_book();
				table.updateUI();
			}
			else
			{
				System.out.println("��ġ�ϴ� ������ �����ϴ�.");
				//JOptionPane.showMessageDialog(getParent(), "��ġ�ϴ� ������ �����ϴ�.");
			}	
			
		}
		else if(button == b_logout)
		{
			all_clear();
			layout_default();
			closeDB();
			model.resetlist();
			table.updateUI();
		}
		//
		else if(button == b_signup)
		{
			user_clear();
			layout_select_signtype();
			table.updateUI();
		}
		//
		else if(button == b_librarian)
		{
			select_position = "�缭";
			layout_librarian_signup();
			table.updateUI();
		}
		//
		else if(button == b_student)
		{
			select_position = "�л�";
			layout_student_signup();
			table.updateUI();
		}
		//
		else if(button == b_register)
		{
			connect();
			
			String id = txt_id.getText();
			String pw = txt_pw.getText();
			String name = txt_name.getText();
			String dept = txt_dept.getText();
			
			if(check_user_info(id, pw, name, dept, select_position))
			{
				if(select_position.equals("�缭"))
					user_insert(id, pw, name, select_position);
				else
					user_insert(id, pw, name, dept, select_position);
				
				all_clear();
				layout_default();
				table.updateUI();
			}
		}
		//
		else if(button == b_cancel)
		{
			user_clear();
			layout_default();
			table.updateUI();
		}
		//not realization
		else if(button == b_insert)
		{
			insert();
			able_by_librarian_login();
			Listing_all_book();
			lib_clear();
			table.updateUI();
		}
		//not realization
		else if(button == b_delete)
		{
			delete();
			able_by_librarian_login();
			Listing_all_book();
			lib_clear();
			table.updateUI();
		}
		//not realization
		else if(button == b_update)
		{
			update();
			able_by_librarian_login();
			Listing_all_book();
			lib_clear();
			table.updateUI();
		}
		//not realization
		else if(button == b_rent_list)
		{
			//Be scheduled to insert method that viewing user's rental list
			able_by_student_login();
			lib_clear();
			table.updateUI();
		}
		//not realization
		else if(button == b_search)
		{
			lib_clear();
			search();
			table.updateUI();
		}	
			
	}
	
	
	public static void main(String[] args)
	{
		new Lib_info_system();
	}
}
