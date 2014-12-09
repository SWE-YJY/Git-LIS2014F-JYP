
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
	private ButtonGroup group_availability;
	private JPanel south, north, center, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12;
	private JButton  b_search, b_insert, b_delete, b_update, b_rent_list;
	
	private String select_availability, select_position;
	private String driver = "com.mysql.jdbc.Driver";
	private String user = "root";
	private String pass = "kjsryh5223";
	private String dbURL = "jdbc:mysql://localhost:3307/lib_info_system";	
	private Connection conn;
	private PreparedStatement prestate;
	private ResultSet rs;		
	
	
	private MemberModel model = new MemberModel();
	int selRow; 

	public Lib_info_system()
	{
		super("도서 정보 관리 프로그랭");
		
		// 레이아웃
		l_id = new JLabel("ID");
		l_pw = new JLabel("PW");
		l_name = new JLabel("이름");
		l_dept = new JLabel("전공");
		
		txt_id = new JTextField(10);
		txt_pw = new JPasswordField(10);
		txt_name = new JTextField(10);
		txt_dept = new JTextField(10);
		
		b_login = new JButton("로그인");
		b_logout = new JButton("로그아웃");
		b_signup = new JButton("회원가입");
		b_librarian = new JButton("사서가입");
		b_student = new JButton("학생가입");
		b_register = new JButton("회원등록");
		b_cancel = new JButton("가입취소"); 
		
		l_blank = new JLabel("  ");
		l_title = new JLabel("도 서 제 목");
		l_authors = new JLabel("저            자");
		l_publisher = new JLabel("출    판    사");
		l_isbn = new JLabel("I    S    B    N");
		l_availability = new JLabel("대여가능여부");
		l_renting_student = new JLabel("빌려간학생");
		l_search = new JLabel("도서검색");
		
		txt_title = new JTextField(20);
		txt_authors = new JTextField(20);
		txt_publisher = new JTextField(20);
		txt_isbn = new JTextField(20);
		txt_renting_student = new JTextField(20);
		txt_keyword = new JTextField(20);
		
		group_availability = new ButtonGroup();
		availability_yes = new JRadioButton("대여가능", false);
		availability_no = new JRadioButton("대여불가", false);
		group_availability.add(availability_yes);
		group_availability.add(availability_no);
		
		b_search = new JButton("검색");
		b_insert = new JButton("추가");
		b_delete = new JButton("삭제");
		b_update = new JButton("수정");
		b_rent_list = new JButton("대여목록확인");
		
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

				String att_title = (String) table.getValueAt(selRow, 0);
				String att_authors = (String) table.getValueAt(selRow, 1);
				String att_publisher = (String) table.getValueAt(selRow, 2);
				String att_isbn = (String) table.getValueAt(selRow, 3);
				String att_availability = (String) table.getValueAt(selRow, 4);
				String att_renting_student = (String) table.getValueAt(selRow, 5);
				
				txt_title.setText(att_title);
				txt_authors.setText(att_authors);
				txt_publisher.setText(att_publisher);
				txt_isbn.setText(att_isbn);
				txt_renting_student.setText(att_renting_student);
				
				txt_title.setEditable(true);
				txt_authors.setEditable(true);
				txt_publisher.setEditable(true);
				txt_isbn.setEditable(true);
				txt_renting_student.setEditable(true);
				
				if(att_availability==null)
				{
					availability_yes.setSelected(false);
					availability_no.setSelected(false);
				}
				else 
				{
					 if (att_availability.equals("대여가능")){
						 availability_yes.setSelected(true);
					 }
					 else if (att_availability.equals("대여불가")) {
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
				System.out.println("DB 연결 성공");
			else
			{
				System.out.println("DB 연결 실패");
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
		
		txt_title.setEditable(true);
		txt_authors.setEditable(true);
		txt_publisher.setEditable(true);
		txt_isbn.setEditable(true);
		txt_renting_student.setEditable(true);
		txt_keyword.setEditable(true);
		
		availability_yes.setEnabled(true);
		availability_yes.setSelected(true);
		availability_no.setEnabled(true);
		
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
				
			System.out.println("사서 등록 성공");
			//JOptionPane.showMessageDialog(getParent(), "회원등록 성공");	
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
				
			System.out.println("학생 등록 성공");
			//JOptionPane.showMessageDialog(getParent(), "회원등록 성공");	
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
		
				
		if(id.isEmpty())
		{
			System.out.println("ID(학번 혹은 사번)를 입력해주십시오.");
			//JOptionPane.showMessageDialog(getParent(), "ID(학번 혹은 사번)를 입력해주십시오.");
			result = false;
		}
		else if(id.length()>10)
		{
			System.out.println("ID(학번 혹은 사번)는 10자 이내로 입력해주십시오.");
			//JOptionPane.showMessageDialog(getParent(), "ID(학번 혹은 사번)는 10자 이내로 정수로 입력해주십시오.");
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
					//해당 char값이 숫자가 아닐 경우
					System.out.println("ID(학번 혹은 사번)는 숫자로 입력해주십시오.");
					txt_id.setText("");
					result = false;
					break;
				}
				
			}
			String sql = "select id from user where id ='"+id+"' ;";
			try
			{
				prestate = conn.prepareStatement(sql);
				rs = prestate.executeQuery();
				if(rs.next())
				{
					if(rs.getString("id").equals(id))
					{
						System.out.println("이미 등록된 ID입니다.");
						//JOptionPane.showMessageDialog(getParent(), "이미 등록된 ID입니다.");
						txt_id.setText("");
						result = false;	
					}			
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		if(pw.isEmpty())
		{
			System.out.println("PW를 입력해주십시오.");
			//JOptionPane.showMessageDialog(getParent(), "PW를 입력해주십시오.");
			result = false;
		}
		else if(pw.length() > 15)
		{
			System.out.println("PW는 15자 이내로 입력해주십시오.");
			//JOptionPane.showMessageDialog(getParent(), "PW는 15자 이내로 입력해주십시오.");
			txt_pw.setText("");
			result = false;
		}
		
		if(name.isEmpty())
		{
			System.out.println("이름을 입력해주십시오.");
			//JOptionPane.showMessageDialog(getParent(), "이름을 입력해주십시오.");
			result = false;
		}
		else if(name.length() > 30)
		{
			System.out.println("이름은 30자 이내로 입력해주십시오.");
			//JOptionPane.showMessageDialog(getParent(), "이름은 30자 이내로 입력해주십시오.");
			txt_name.setText("");
			result = false;
		}
			
		if(position.equals("학생"))
		{
			if(dept.isEmpty())
			{
				System.out.println("전공을 입력해주십시오.");
				//JOptionPane.showMessageDialog(getParent(), "전공을 입력해주십시오.");
				result = false;
			}
			else if(dept.length()>30)
			{
				System.out.println("전공은 30자 이내로 입력해주십시오.");
				//JOptionPane.showMessageDialog(getParent(), "전공은 30자 이내로 입력해주십시오.");
				txt_dept.setText("");
				result = false;
			}
		}
		
		return result;
	}
	//not realization
	private boolean check_book_info(String title, String authors, String publisher, String isbn, String availability, String renting_student) {
		// TODO Auto-generated method stub	
		
		boolean result = true;
		
		if(title.isEmpty())
		{
			System.out.println("제목을 입력해주십시오.");
			//JOptionPane.showMessageDialog(getParent(), "제목을 입력해주십시오.");
			result = false;
		}
		else if(title.length() > 50)
		{
			System.out.println("제목은 50자 이내로 입력해주십시오.");
			//JOptionPane.showMessageDialog(getParent(), "제목은 50자 이내로 입력해주십시오.");
			txt_title.setText("");
			result = false;
		}
		
		if(authors.isEmpty())
		{
			System.out.println("저자를 입력해주십시오.");
			//JOptionPane.showMessageDialog(getParent(), "저자를 입력해주십시오.");
			result = false;
		}
		else if(authors.length() > 50)
		{
			System.out.println("저자는 50자 이내로 입력해주십시오.");
			//JOptionPane.showMessageDialog(getParent(), "저자는 50자 이내로 입력해주십시오.");
			txt_authors.setText("");
			result = false;
		}
		
		if(publisher.isEmpty())
		{
			System.out.println("출판사를 입력해주십시오.");
			//JOptionPane.showMessageDialog(getParent(), "저자를 입력해주십시오.");
			result = false;
		}
		else if(publisher.length() > 20)
		{
			System.out.println("출판사는 20자 이내로 입력해주십시오.");
			//JOptionPane.showMessageDialog(getParent(), "출판사는 20자 이내로 입력해주십시오.");
			txt_publisher.setText("");
			result = false;
		}
		
		if(isbn.isEmpty())
		{
			System.out.println("ISBN을 입력해주십시오.");
			//JOptionPane.showMessageDialog(getParent(), "ISBN을 입력해주십시오.");
			result = false;
		}
		else if(isbn.length() > 30)
		{
			System.out.println("ISBN은 30자 이내로 입력해주십시오.");
			//JOptionPane.showMessageDialog(getParent(), "ISBN은 30자 이내로 입력해주십시오.");
			txt_isbn.setText("");
			result = false;
		}
		
		if(availability.equals("대여가능"))
		{
			if(!renting_student.isEmpty())
			{
				System.out.println("빌려간 학생의 학번을 입력할 수 없습니다.");
				//JOptionPane.showMessageDialog(getParent(), "빌려간 학생의 학번을 입력할 수 없습니다.");
				txt_renting_student.setText("");
				result = false;
			}
		}
		else if(availability.equals("대여불가"))
		{
			if(renting_student.isEmpty())
			{
				System.out.println("빌려간 학생의 학번을 입력해주십시오.");
				//JOptionPane.showMessageDialog(getParent(), "빌려간 학생의 학번을 입력해주십시오.");
				result = false;
			}
			else if(renting_student.length()>10)
			{
				System.out.println("빌려간 학생의 학번은 10자 이내로 입력해주십시오.");
				//JOptionPane.showMessageDialog(getParent(), "빌려간 학생의 학번은 10자 이내로 정수로 입력해주십시오.");
				txt_renting_student.setText("");
				result = false;
			}
			else
			{
				char typecheck;
				for (int i = 0; i < renting_student.length(); i++)
				{
					typecheck = renting_student.charAt(i);
					if( typecheck < 48 || typecheck > 58)
					{
						//해당 char값이 숫자가 아닐 경우
						System.out.println("빌려간 학생의 학번은 숫자로 입력해주십시오.");
						txt_renting_student.setText("");
						result = false;
						break;
					}
					
				}			
			}
		}
		else
		{
			System.out.println("대여가능여부를 선택해주십시오.");
			//JOptionPane.showMessageDialog(getParent(), "대여가능여부를 선택해주십시오.");
			result = false;
		}
		return result;
	}
	//
	private void insert(String title, String authors, String publisher,
			String isbn, String availability) {
		// TODO Auto-generated method stub
		String sql= "insert into book (title, author, publisher, isbn, availability) "
				+ "values(?,?,?,?,?);";
		try
		{
			prestate = conn.prepareStatement(sql);
			prestate.setString(1, title);
			prestate.setString(2, authors);
			prestate.setString(3, publisher);
			prestate.setString(4, isbn);
			prestate.setString(5, availability);
			prestate.executeUpdate();
				
			System.out.println("책 등록 성공");
			//JOptionPane.showMessageDialog(getParent(), "책 등록 성공");	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	//not realization
	private void insert(String title, String authors, String publisher, String isbn, String availability, String renting_student) {
		// TODO Auto-generated method stub
		String sql= "insert into book (title, author, publisher, isbn, availability, rental_id) "
				+ "values(?,?,?,?,?,?);";
		try
		{
			prestate = conn.prepareStatement(sql);
			prestate.setString(1, title);
			prestate.setString(2, authors);
			prestate.setString(3, publisher);
			prestate.setString(4, isbn);
			prestate.setString(5, availability);
			prestate.setString(6, renting_student);
			prestate.executeUpdate();
				
			System.out.println("책 등록 성공");
			//JOptionPane.showMessageDialog(getParent(), "책 등록 성공");	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
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
	private boolean View_my_rentallist(String id) {
		// TODO Auto-generated method stub
		boolean result = false;
		Vector list = new Vector();
		String sql = "select * from book where rental_id ='"+id+"' ;";
	    try
	    {
	        prestate = conn.prepareStatement(sql);
	        rs = prestate.executeQuery();
	        while (rs.next())
	        {
	            Vector record = new Vector();    
	            record.add(rs.getString("title"));
	            record.add(rs.getString("author"));
	            record.add(rs.getString("publisher"));
	            record.add(rs.getString("isbn"));
	            record.add(rs.getString("availability"));
	            record.add(rs.getString("rental_id"));
	            record.add(rs.getInt("book_number"));
	            list.add(record);
	            
	            result = true;
	        }
	        model.setList(list);
	        this.repaint();
	    }
	    catch (SQLException e)
	    {
	        e.printStackTrace();
	    }
	    return result;
		
	}
	//not realization
	private void search() {
		// TODO Auto-generated method stub
		Vector list = new Vector();
	    String keyword = txt_keyword.getText();
	    String id = txt_id.getText();
	    String sql = "select * from book "
	            + "where title like '%"+keyword+"%'";         
	    try
	    {
	        prestate = conn.prepareStatement(sql);
	        rs = prestate.executeQuery();
	        while (rs.next())
	        {
	            Vector record = new Vector();
	             
	            record.add(rs.getString("title"));
	            record.add(rs.getString("author"));
	            record.add(rs.getString("publisher"));
	            record.add(rs.getString("isbn"));
	            record.add(rs.getString("availability"));
	            record.add(rs.getString("rental_id"));
	            record.add(rs.getInt("book_number"));
	            list.add(record);
	        }
	         
	        model.setList(list);
	        this.repaint();
	    }
	    catch (SQLException e)
	    {
	        e.printStackTrace();
	    }
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
			select_availability = "대여가능";	
		else if(availability_no.isSelected())
			select_availability = "대여불가";
		
		Object button = e.getSource();
		//
		if(button == b_login)
		{
			String id = txt_id.getText();
			String pw = txt_pw.getText();
			String get_position;
			// DB연결
			connect();
			if(login(id, pw))
			{
				get_position = get_position(id, pw);
				if(get_position.equals("사서"))
					able_by_librarian_login();
				else
					able_by_student_login();
				Listing_all_book();
				table.updateUI();
			}
			else
			{
				System.out.println("일치하는 정보가 없습니다.");
				//JOptionPane.showMessageDialog(getParent(), "일치하는 정보가 없습니다.");
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
			select_position = "사서";
			layout_librarian_signup();
			table.updateUI();
		}
		//
		else if(button == b_student)
		{
			select_position = "학생";
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
				if(select_position.equals("사서"))
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
			
			String title = txt_title.getText();
			String authors = txt_authors.getText();
			String publisher = txt_publisher.getText();
			String isbn = txt_isbn.getText();
			String renting_student = txt_renting_student.getText();
			
			
			if(check_book_info(title, authors, publisher, isbn, 
					select_availability, renting_student))
			{
				if(select_availability.equals("대여가능"))
					insert(title, authors, publisher, isbn, select_availability);
				else
					insert(title, authors, publisher, isbn, select_availability, renting_student);
				Listing_all_book();
				lib_clear();
				table.updateUI();
			}
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
			String id = txt_id.getText();
			// String View_my_rentallist;
			if(View_my_rentallist(id))
            {
                able_by_student_login();
                lib_clear();
                table.updateUI();
            }
            else
            {
                System.out.println("대여한 책이 없습니다.");
                //JOptionPane.showMessageDialog(getParent(), "일치하는 정보가 없습니다.");
            }
			  
			
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
