
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

public class cosmetic extends JFrame implements ActionListener{
	
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
	
	
	private MemberModel model = new MemberModel();
	int selRow; 

	public cosmetic()
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
		
		availability = new ButtonGroup();
		availability_yes = new JRadioButton("대여가능", false);
		availability_no = new JRadioButton("대여불가", false);
		availability.add(availability_yes);
		availability.add(availability_no);
		
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

				String company = (String) table.getValueAt(selRow, 0);
				String cosname = (String) table.getValueAt(selRow, 1);
				String costype = (String) table.getValueAt(selRow, 2);
				String func = (String) table.getValueAt(selRow, 3);
				String skintype = (String) table.getValueAt(selRow, 4);
				String availability = (String) table.getValueAt(selRow, 5);
				
				txt_title.setText(company);
				txt_authors.setText(cosname);
				txt_title.setEditable(false);
				txt_authors.setEditable(false);
				
				if(availability==null)
				{
					availability_yes.setSelected(false);
					availability_no.setSelected(false);
				}
				else 
				{
					 if (availability.equals("대여가능")){
						 availability_yes.setSelected(true);
					 }
					 else if (availability.equals("대여불가")) {
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
		
	}
	//
	public void closeDB() {
		
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
	private void able_by_login() {
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
	private void user_clear(){
		txt_id.setText("");
		txt_pw.setText("");
		txt_name.setText("");
		txt_dept.setText("");
	}
	//
	private void cosmetic_clear(){		
		txt_title.setText("");
		txt_authors.setText("");
		
		availability_yes.setSelected(true);
		availability_no.setSelected(false);
		
	}
	//
	private void all_clear(){
		user_clear();
		cosmetic_clear();
	}
	//
	private void login() {
		// TODO Auto-generated method stub
	}
	//
	private void user_insert() {
		// TODO Auto-generated method stub
	}
	//
	private void user_is_null() {
		// TODO Auto-generated method stub
	}
	//
	private void is_null() {
		// TODO Auto-generated method stub	
	}
	//
	private void insert() {
		// TODO Auto-generated method stub
	}
	//
	private void duplicate_cosmetic() {
		// TODO Auto-generated method stub		
	}
	//
	private void update() {
		// TODO Auto-generated method stub
	}
	//
	private void delete() {
		// TODO Auto-generated method stub
	}
	//
	public void searchall() {
		// TODO Auto-generated method stub
	}
	//
	private void search() {
		// TODO Auto-generated method stub
	}
	//
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(availability_yes.isSelected())
		{}
		else if(availability_no.isSelected())
		{}
		
		Object button = e.getSource();
		//
		if(button == b_login)
		{
			// DB연결
				able_by_login();
				table.updateUI();
		}
		else if(button == b_logout)
		{
			all_clear();
			layout_default();
			model.resetlist();
			table.updateUI();
		}
		//
		else if(button == b_signup)
		{
			layout_select_signtype();
			table.updateUI();
		}
		//
		else if(button == b_librarian)
		{
			layout_librarian_signup();
			table.updateUI();
		}
		//
		else if(button == b_student)
		{
			layout_student_signup();
			table.updateUI();
		}
		//
		else if(button == b_register)
		{
			user_insert();
			all_clear();
			layout_default();
			table.updateUI();
		}
		//
		else if(button == b_cancel)
		{
			layout_default();
			table.updateUI();
		}
		//
		else if(button == b_insert)
		{
			able_by_login();
			cosmetic_clear();
			table.updateUI();
		}
		//
		else if(button == b_delete)
		{
			able_by_login();
			cosmetic_clear();
			table.updateUI();
		}
		//
		else if(button == b_update)
		{
			able_by_login();
			cosmetic_clear();
			table.updateUI();
		}
		//
		else if(button == b_rent_list)
		{
			able_by_login();
			cosmetic_clear();
			table.updateUI();
		}
		//
		else if(button == b_search)
		{
			able_by_login();
			cosmetic_clear();
			table.updateUI();
		}	
			
	}

	

	public static void main(String[] args)
	{
		new cosmetic();
	}

	
}
