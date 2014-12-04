
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
	
	JLabel l_id, l_pw, l_name, l_age, l_sex;
	JTextField txt_id, txt_name, txt_age;
	JPasswordField txt_pw;
	JRadioButton sex_m, sex_f;
	ButtonGroup sex;
	JButton  b_login, b_logout, b_signup, b_register, b_user_update, 
				b_user_delete, b_cancel;
	
	JLabel l_blank, l_company, l_cosname, l_ctype, l_func, l_stype, l_ucomment;
	JTextField txt_company, txt_cosname, txt_keyword;
	JComboBox ch_search;
	JTable table;
	JScrollPane scroll;
	JRadioButton ctype_toner, ctype_lotion, ctype_cream, 
				ctype_serum, ctype_pack;
	JRadioButton func_moisturizing, func_whitening, 
				func_trouble, func_wrinkle, func_sunblock;
	JRadioButton stype_normal, stype_dry, stype_oily, 
				stype_complex, stype_sensitive;
	JRadioButton ucomment_good, ucomment_soso, ucomment_bad;
	ButtonGroup costype, func, skintype, usercomment;
	JPanel south, north, center, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11;
	JButton  b_search, b_insert, b_delete,b_update, b_searchall;
	String select_sex, select_costype, select_func, select_skintype, select_usercomment;
	String select_search = null;
	
	String driver = "com.mysql.jdbc.Driver";
	String user = "root";
	String pass = "kjsryh5223";
	String dbURL = "jdbc:mysql://localhost:3307/cosmetic";	
	Connection conn;
	PreparedStatement prestate;
	ResultSet rs, duplicate_checker;		
	
	MemberModel model = new MemberModel();
	int selRow; 

	public cosmetic()
	{
		super("1210185 전소라 화장품 관리 프로그랭");
		
		// 레이아웃
		l_id = new JLabel("ID");
		l_pw = new JLabel("PW");
		l_name = new JLabel("이름");
		l_age = new JLabel("나이");
		l_sex = new JLabel("성별");
		
		txt_id = new JTextField(10);
		txt_pw = new JPasswordField(10);
		txt_name = new JTextField(20);
		txt_age = new JTextField(5);
		
		sex = new ButtonGroup();
		sex_m = new JRadioButton("남", false);
		sex_f = new JRadioButton("여", false);
		sex.add(sex_m);
		sex.add(sex_f);
		
		b_login = new JButton("로그인");
		b_logout = new JButton("로그아웃");
		b_signup = new JButton("회원가입");
		b_register = new JButton("회원등록");
		b_user_update = new JButton("정보수정");
		b_user_delete = new JButton("회원탈퇴");
		b_cancel = new JButton("가입취소"); 
		
		l_blank = new JLabel("  ");
		l_company = new JLabel("브 랜 드 명");
		l_cosname = new JLabel("제 품 이 름");
		l_ctype = new JLabel("제 품 유 형");
		l_func = new JLabel("제 품 기 능");
		l_stype = new JLabel("피 부 타 입");
		l_ucomment = new JLabel("사 용 결 과");
		
		txt_company = new JTextField(30);
		txt_cosname = new JTextField(30);
		txt_keyword = new JTextField(30);
		
		ch_search = new JComboBox();
		ch_search.addItem("검색설정");
		ch_search.addItem("브랜드명");
		ch_search.addItem("제품이름");
		ch_search.addItem("제품유형");
		ch_search.addItem("제품기능");
		ch_search.addItem("피부타입");
		ch_search.addItem("사용결과");
		
		costype = new ButtonGroup();
		ctype_toner = new JRadioButton("스킨", false);
		ctype_lotion = new JRadioButton("로션", false);
		ctype_cream = new JRadioButton("크림", false);
		ctype_serum = new JRadioButton("에센스/세럼/앰플", false);
		ctype_pack = new JRadioButton("팩", false);
		costype.add(ctype_toner);
		costype.add(ctype_lotion);
		costype.add(ctype_cream);
		costype.add(ctype_serum);
		costype.add(ctype_pack);
		
		func = new ButtonGroup();
		func_moisturizing  = new JRadioButton("수분/보습", false);
		func_whitening  = new JRadioButton("미백", false);
		func_trouble = new JRadioButton("트러블", false);
		func_wrinkle = new JRadioButton("탄력/주름", false);
		func_sunblock = new JRadioButton("자외선차단", false);
		func.add(func_moisturizing);
		func.add(func_whitening);
		func.add(func_trouble);
		func.add(func_wrinkle);
		func.add(func_sunblock);
		
		skintype = new ButtonGroup();
		stype_normal = new JRadioButton("모든피부", false);
		stype_dry = new JRadioButton("건성", false);
		stype_oily = new JRadioButton("지성", false);
		stype_complex = new JRadioButton("복합성", false);
		stype_sensitive = new JRadioButton("민감성", false);
		skintype.add(stype_normal);
		skintype.add(stype_dry);
		skintype.add(stype_oily);
		skintype.add(stype_complex);
		skintype.add(stype_sensitive);
		
		usercomment = new ButtonGroup();
		ucomment_good = new JRadioButton("좋음", false);
		ucomment_soso = new JRadioButton("그저그럼", false);
		ucomment_bad = new JRadioButton("나쁨", false);
		usercomment.add(ucomment_good);
		usercomment.add(ucomment_soso);
		usercomment.add(ucomment_bad);
		
		b_search = new JButton("검색");
		b_insert = new JButton("추가");
		b_delete = new JButton("삭제");
		b_update = new JButton("수정");
		b_searchall = new JButton("전체조회");
		
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
		
		north.setPreferredSize(new Dimension(600, 400));
		center.setPreferredSize(new Dimension(500, 200));
		south.setPreferredSize(new Dimension(500, 50));

		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);		
		
		north.setLayout(new GridLayout(11, 1));
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
		
		p1.add(l_id);
		p1.add(txt_id);
		p1.add(l_pw);
		p1.add(txt_pw);
		p1.add(b_login);
		p1.add(b_logout);
		p1.add(b_signup);
		
		p2.add(l_name);
		p2.add(txt_name);
		p2.add(l_blank);
		p2.add(l_age);
		p2.add(txt_age);
		p2.add(l_blank);
		p2.add(l_sex);
		p2.add(sex_m);
		p2.add(sex_f);
		p2.add(l_blank);
		
		p3.add(b_register);
		p3.add(b_user_update);
		p3.add(b_user_delete);
		p3.add(b_cancel);
		
		p4.add(l_blank);
		
		p5.add(l_company);
		p5.add(txt_company); 
		
		p6.add(l_cosname);
		p6.add(txt_cosname);
		
		p7.add(l_ctype);
		p7.add(ctype_toner);
		p7.add(ctype_lotion);
		p7.add(ctype_cream);
		p7.add(ctype_serum);
		p7.add(ctype_pack);
		
		p8.add(l_func);
		p8.add(func_moisturizing);
		p8.add(func_whitening);
		p8.add(func_trouble);
		p8.add(func_wrinkle);
		p8.add(func_sunblock);
		
		p9.add(l_stype);
		p9.add(stype_normal);
		p9.add(stype_dry);
		p9.add(stype_oily);
		p9.add(stype_complex);
		p9.add(stype_sensitive);
		
		p10.add(l_ucomment);
		p10.add(ucomment_good);
		p10.add(ucomment_soso);
		p10.add(ucomment_bad);
		
		p11.setLayout(new FlowLayout(FlowLayout.CENTER));
		p11.add(ch_search);
		p11.add(txt_keyword);
		p11.add(b_search);
		
		ch_search.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				select_search = ch_search.getSelectedItem().toString();
				
				if(select_search.equals("브랜드명"))
				{
					select_search = "using_cosmetic.using_company";
				}
				else if(select_search.equals("제품이름"))
				{
					select_search = "using_cosmetic.using_cosname";
				}
				else if(select_search.equals("제품유형"))
				{
					select_search = "cosmetic.costype";
				}
				else if(select_search.equals("제품기능"))
				{
					select_search = "cosmetic.func";
				}
				else if(select_search.equals("피부타입"))
				{
					select_search = "cosmetic.skintype";
				}
				
				else if(select_search.equals("사용결과"))
				{
					select_search = "using_cosmetic.usercomment";
				}
				
			}
		});
		
		center.setLayout(new GridLayout(1, 1));
		center.add(scroll);
		table.setAutoCreateRowSorter(true);		

		south.setLayout(new GridLayout(1, 4));
		south.add(b_insert);
		south.add(b_delete);
		south.add(b_update);
		south.add(b_searchall);
		
		b_login.addActionListener(this);
		b_logout.addActionListener(this);
		b_signup.addActionListener(this);
		b_register.addActionListener(this);
		b_user_update.addActionListener(this);
		b_user_delete.addActionListener(this);
		b_cancel.addActionListener(this);
		
		b_search.addActionListener(this);
		b_insert.addActionListener(this);
		b_delete.addActionListener(this);
		b_update.addActionListener(this);
		b_searchall.addActionListener(this);
		

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selRow = table.getSelectedRow();

				String company = (String) table.getValueAt(selRow, 0);
				String cosname = (String) table.getValueAt(selRow, 1);
				String costype = (String) table.getValueAt(selRow, 2);
				String func = (String) table.getValueAt(selRow, 3);
				String skintype = (String) table.getValueAt(selRow, 4);
				String usercomment = (String) table.getValueAt(selRow, 5);
				
				txt_company.setText(company);
				txt_cosname.setText(cosname);
				txt_company.setEditable(false);
				txt_cosname.setEditable(false);
				
				if(costype==null)
				{
					ctype_toner.setSelected(false);
					ctype_lotion.setSelected(false);
					ctype_cream.setSelected(false);
					ctype_serum.setSelected(false);
					ctype_pack.setSelected(false);
				}
				else 
				{
					 if (costype.equals("스킨")){
						 ctype_toner.setSelected(true);
					 }
					 else if (costype.equals("로션")) {
						 ctype_lotion.setSelected(true);
					 }
					 else if (costype.equals("크림")) {
						 ctype_cream.setSelected(true);
					 }
					 else if (costype.equals("에센스/세럼/앰플")) {
						 ctype_serum.setSelected(true);
					 }
					 else if (costype.equals("팩")) {
						 ctype_pack.setSelected(true);
					 }
				}
				
				if(func==null)
				{
					func_moisturizing.setSelected(false);
					func_whitening.setSelected(false);
					func_trouble.setSelected(false);
					func_wrinkle.setSelected(false);
					func_sunblock.setSelected(false);
				}
				else 
				{
					 if (func.equals("수분/보습")){
						 func_moisturizing.setSelected(true);
					 }
					 else if (func.equals("미백")) {
						 func_whitening.setSelected(true);
					 }
					 else if (func.equals("트러블")) {
						 func_trouble.setSelected(true);
					 }
					 else if (func.equals("탄력/주름")) {
						 func_wrinkle.setSelected(true);
					 }
					 else if (func.equals("자외선차단")) {
						 func_sunblock.setSelected(true);
					 }
				}
				
				if(skintype==null)
				{
					stype_normal.setSelected(false);
					stype_dry.setSelected(false);
					stype_oily.setSelected(false);
					stype_complex.setSelected(false);
					stype_sensitive.setSelected(false);
				}
				else 
				{
					 if (skintype.equals("모든피부")){
						 stype_normal.setSelected(true);
					 }
					 else if (skintype.equals("건성")) {
						 stype_dry.setSelected(true);
					 }
					 else if (skintype.equals("지성")) {
						 stype_oily.setSelected(true);
					 }
					 else if (skintype.equals("복합성")) {
						 stype_complex.setSelected(true);
					 }
					 else if (skintype.equals("민감성")) {
						 stype_sensitive.setSelected(true);
					 }
				}
				
				
				if(usercomment==null)
				{
					ucomment_good.setSelected(false);
					ucomment_soso.setSelected(false);
					ucomment_bad.setSelected(false);
				}
				else 
				{
					 if (usercomment.equals("좋음")){
						 ucomment_good.setSelected(true);
					 }
					 else if (usercomment.equals("그저그럼")) {
						 ucomment_soso.setSelected(true);
					 }
					 else if (usercomment.equals("나쁨")) {
						 ucomment_bad.setSelected(true);
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
		
		
	
		able_default();
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
			{
			}
			else
			{
				JOptionPane.showMessageDialog(getParent(), "DB연결 실패");
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
	private void able_default()
	{
	
		txt_id.setEditable(true);
		txt_pw.setEditable(true);
		txt_name.setEditable(false);
		txt_age.setEditable(false);
				
		sex_m.setEnabled(false);
		sex_f.setEnabled(false);
		
		b_login.setEnabled(true);
		b_logout.setEnabled(false);
		b_signup.setEnabled(true);
		b_register.setEnabled(false);
		b_user_update.setEnabled(false);
		b_user_delete.setEnabled(false);
		b_cancel.setEnabled(false);

		txt_company.setEditable(false);
		txt_cosname.setEditable(false);
		txt_keyword.setEditable(false);

		ch_search.setEnabled(false);	

		ctype_toner.setEnabled(false);
		ctype_lotion.setEnabled(false);
		ctype_cream.setEnabled(false);
		ctype_serum.setEnabled(false);
		ctype_pack.setEnabled(false);

		func_moisturizing.setEnabled(false);
		func_whitening.setEnabled(false);
		func_trouble.setEnabled(false);
		func_wrinkle.setEnabled(false);
		func_sunblock.setEnabled(false);
		
		stype_normal.setEnabled(false);
		stype_dry.setEnabled(false);
		stype_oily.setEnabled(false);
		stype_complex.setEnabled(false);
		stype_sensitive.setEnabled(false);
		
		ucomment_good.setEnabled(false);
		ucomment_soso.setEnabled(false);
		ucomment_bad.setEnabled(false);

		b_search.setEnabled(false);
		b_insert.setEnabled(false);
		b_delete.setEnabled(false);
		b_update.setEnabled(false);
		b_searchall.setEnabled(false);
	}
	
	private void able_by_signup()
	{
	
		txt_id.setEditable(true);
		txt_pw.setEditable(true);
		txt_name.setEditable(true);
		txt_age.setEditable(true);
				
		sex_m.setEnabled(true);
		sex_f.setEnabled(true);
		
		b_login.setEnabled(false);
		b_logout.setEnabled(false);
		b_signup.setEnabled(false);
		b_register.setEnabled(true);
		b_user_update.setEnabled(false);
		b_user_delete.setEnabled(false);
		b_cancel.setEnabled(true);
		
		txt_company.setEditable(false);
		txt_cosname.setEditable(false);
		txt_keyword.setEditable(false);

		ch_search.setEnabled(false);	

		ctype_toner.setEnabled(false);
		ctype_lotion.setEnabled(false);
		ctype_cream.setEnabled(false);
		ctype_serum.setEnabled(false);
		ctype_pack.setEnabled(false);

		func_moisturizing.setEnabled(false);
		func_whitening.setEnabled(false);
		func_trouble.setEnabled(false);
		func_wrinkle.setEnabled(false);
		func_sunblock.setEnabled(false);
		
		stype_normal.setEnabled(false);
		stype_dry.setEnabled(false);
		stype_oily.setEnabled(false);
		stype_complex.setEnabled(false);
		stype_sensitive.setEnabled(false);
		
		ucomment_good.setEnabled(false);
		ucomment_soso.setEnabled(false);
		ucomment_bad.setEnabled(false);

		b_search.setEnabled(false);
		b_insert.setEnabled(false);
		b_delete.setEnabled(false);
		b_update.setEnabled(false);
		b_searchall.setEnabled(false);
	}
	//
	private void able_by_login() {
		// TODO Auto-generated method stub
		
		txt_id.setEditable(false);
		txt_pw.setEditable(true);
		txt_name.setEditable(true);
		txt_age.setEditable(true);
		sex_m.setEnabled(true);
		sex_f.setEnabled(true);
		
		b_login.setEnabled(false);
		b_logout.setEnabled(true);
		b_signup.setEnabled(false);
		b_register.setEnabled(false);
		b_user_update.setEnabled(true);
		b_user_delete.setEnabled(true);
		b_cancel.setEnabled(false);
		
		txt_company.setEditable(true);
		txt_cosname.setEditable(true);
		txt_keyword.setEditable(true);
		
		ctype_toner.setEnabled(true);
		ctype_lotion.setEnabled(true);
		ctype_cream.setEnabled(true);
		ctype_serum.setEnabled(true);
		ctype_pack.setEnabled(true);
		
		func_moisturizing.setEnabled(true);
		func_whitening.setEnabled(true);
		func_trouble.setEnabled(true);
		func_wrinkle.setEnabled(true);
		func_sunblock.setEnabled(true);
		
		stype_normal.setEnabled(true);
		stype_dry.setEnabled(true);
		stype_oily.setEnabled(true);
		stype_complex.setEnabled(true);
		stype_sensitive.setEnabled(true);
		
		ucomment_good.setEnabled(true);
		ucomment_soso.setEnabled(true);
		ucomment_bad.setEnabled(true);
		
		ch_search.setEnabled(true);
		
		b_search.setEnabled(true);
		b_insert.setEnabled(true);
		b_delete.setEnabled(true);
		b_update.setEnabled(true);
		b_searchall.setEnabled(true);
	}
	//
	private void user_clear(){
		txt_id.setText("");
		txt_pw.setText("");
		txt_name.setText("");
		txt_age.setText("");
		sex_m.setSelected(true);
		sex_f.setSelected(false);
	}
	//
	private void cosmetic_clear(){		
		txt_company.setText("");
		txt_cosname.setText("");
		
		ctype_toner.setSelected(true);
		ctype_lotion.setSelected(false);
		ctype_cream.setSelected(false);
		ctype_serum.setSelected(false);
		ctype_pack.setSelected(false);

		func_moisturizing.setSelected(true);
		func_whitening.setSelected(false);
		func_trouble.setSelected(false);
		func_wrinkle.setSelected(false);
		func_sunblock.setSelected(false);
		
		stype_normal.setSelected(true);
		stype_dry.setSelected(false);
		stype_oily.setSelected(false);
		stype_complex.setSelected(false);
		stype_sensitive.setSelected(false);
		
		ucomment_good.setSelected(true);
		ucomment_soso.setSelected(false);
		ucomment_bad.setSelected(false);

	}
	//
	private void all_clear(){
		user_clear();
		cosmetic_clear();
	}
	//
	private boolean login() {
		// TODO Auto-generated method stub
		String id = txt_id.getText();
		String pw = txt_pw.getText();
		
		String sql = "select * from cosuser where id ='"+id+"' and pw='"+pw+"';";
		try
		{
			prestate = conn.prepareStatement(sql);
			rs = prestate.executeQuery();
			if(rs.next())
			{
				String name = (rs.getString("uname"));
				int age = (rs.getInt("age"));
				String sex = (rs.getString("sex"));
				
				txt_name.setText(name);
				txt_age.setText(Integer.toString(age));
				
				if(sex==null)
				{
					sex_m.setSelected(false);
					sex_f.setSelected(false);
				}
				else 
				{
					 if (sex.equals("남")){
						 sex_m.setSelected(true);
					 }
					 else if (sex.equals("여")) {
						 sex_f.setSelected(true);
					 }
				}
			}
			else
			{
				return false;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return true;

	}
	//
	private void user_insert() {
		// TODO Auto-generated method stub
		if(user_is_null()){}
		else
		{
			String sql= "insert into cosuser values(?,?,?,?,?);";
			try
			{
				prestate = conn.prepareStatement(sql);
				prestate.setString(1, txt_id.getText());
				prestate.setString(2, txt_pw.getText());
				prestate.setString(3, txt_name.getText());
				prestate.setInt(4, Integer.parseInt(txt_age.getText()));
				prestate.setString(5, select_sex);
				prestate.executeUpdate();
				
				JOptionPane.showMessageDialog(getParent(), "회원등록 성공");	
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	//
	@SuppressWarnings("deprecation")
	private void user_update() {
		// TODO Auto-generated method stub
		if(user_is_null()){}
		else
		{
			if(JOptionPane.showConfirmDialog(getParent()," 회원정보를 수정하시겠습니까?")==0)
			{
				String sql = "Update cosuser set pw=?, uname=?, age=?, sex=? where id=?; ";
				try
				{
					prestate = conn.prepareStatement(sql);
					prestate.setString(1, txt_pw.getText());
					prestate.setString(2, txt_name.getText());
					prestate.setInt(3, Integer.parseInt(txt_age.getText()));
					prestate.setString(4, select_sex);
					prestate.setString(5, txt_id.getText());
					if(JOptionPane.showConfirmDialog(getParent()," 수정 완료 후의 비밀번호가 '"+txt_pw.getText()+"' 가 맞습니까?")==0)
					{
						prestate.executeUpdate();
						JOptionPane.showMessageDialog(getParent(), "수정 성공");
					}
					else
						JOptionPane.showMessageDialog(getParent(), "수정 취소");
					
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(getParent(), "수정 실패");
				} 
			}
			else
			{
				JOptionPane.showMessageDialog(getParent(), "수정 취소");	
			}

		}
	}
	//
	private void user_delete() {
		// TODO Auto-generated method stub
		if(JOptionPane.showConfirmDialog(getParent(),"탈퇴하시겠습니까?")==0)
		{
			String sql = "delete from cosuser where id=?;";
			try
			{
				prestate = conn.prepareStatement(sql);
				prestate.setString(1, txt_id.getText());
				prestate.executeUpdate();
				JOptionPane.showMessageDialog(getParent(), "탈퇴 성공");
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(getParent(), "탈퇴 취소");
			
		}
	}
	//
	private boolean user_is_null() {
		// TODO Auto-generated method stub
		boolean result = true;
		if(txt_id.getText() == null || txt_id.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(getParent(), "ID를 입력해주십시오.");
			txt_id.setText("");
			txt_id.requestFocus();
		}
		else if(txt_id.getText().length() > 15)
		{
			JOptionPane.showMessageDialog(getParent(), "ID는 15자 이내로 입력해주십시오.");
			txt_id.setText("");
			txt_id.requestFocus();
		}
		else if(txt_pw.getText() == null || txt_pw.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(getParent(), "PW를 입력해주십시오.");
			txt_pw.setText("");
			txt_pw.requestFocus();
		}
		else if(txt_pw.getText().length() > 15)
		{
			JOptionPane.showMessageDialog(getParent(), "PW는 15자 이내로 입력해주십시오.");
			txt_pw.setText("");
			txt_pw.requestFocus();
		}
		else if(txt_name.getText() == null || txt_name.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(getParent(), "이름을 입력해주십시오.");
			txt_name.setText("");
			txt_name.requestFocus();
		}
		else if(txt_name.getText().length() > 30)
		{
			JOptionPane.showMessageDialog(getParent(), "이름은 30자 이내로 입력해주십시오.");
			txt_name.setText("");
			txt_name.requestFocus();
		}
		else if(txt_age.getText() == null || txt_age.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(getParent(), "나이를 입력해주십시오.");
			txt_age.setText("");
			txt_age.requestFocus();
		}
		else if(txt_age.getText().length()>3 || txt_age.getText().matches("[0-9]"))
		{
			JOptionPane.showMessageDialog(getParent(), "나이는 3자 이내의 정수로 입력해주십시오.");
			txt_age.setText("");
			txt_age.requestFocus();
		}
		else if(select_sex == null || select_sex.length() == 0)
		{
			JOptionPane.showMessageDialog(getParent(), "성별을 선택해주십시오.");
		}
		else
		{
			result = false;
		}
		return result;
	}
	//
	private boolean is_null() {
		// TODO Auto-generated method stub
		
		boolean result = true;
		if(txt_company.getText() == null || txt_company.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(getParent(), "브랜드명을 입력해주십시오.");
			txt_company.setText("");
			txt_company.requestFocus();
		}
		else if(txt_company.getText().length() > 30)
		{
			JOptionPane.showMessageDialog(getParent(), "브랜드명은 30자 이내로 입력해주십시오.");
			txt_company.setText("");
			txt_company.requestFocus();
		}
		else if(txt_cosname.getText() == null || txt_cosname.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(getParent(), "제품이름을 입력해주십시오.");
			txt_cosname.setText("");
			txt_cosname.requestFocus();
		}
		else if(txt_cosname.getText().length() > 30)
		{
			JOptionPane.showMessageDialog(getParent(), "제품이름은 30자 이내로 입력해주십시오.");
			txt_cosname.setText("");
			txt_cosname.requestFocus();
		}
		else if(select_costype == null || select_costype.length() == 0)
		{
			JOptionPane.showMessageDialog(getParent(), "제품유형을 선택해주십시오.");
		}
		else if(select_func == null || select_costype.length() == 0)
		{
			JOptionPane.showMessageDialog(getParent(), "제품기능을 선택해주십시오.");
		}
		else if(select_skintype == null || select_costype.length() == 0)
		{
			JOptionPane.showMessageDialog(getParent(), "피부타입을 선택해주십시오.");
		}
		else if(select_usercomment == null || select_costype.length() == 0)
		{
			JOptionPane.showMessageDialog(getParent(), "사용결과를 선택해주십시오.");
		}
		else
		{
			result = false;
		}
		return result;
		
	}
	//
	private void insert() {
		// TODO Auto-generated method stub
		
		if(is_null()){}
		else
		{
			duplicate_using_cosmetic();
		}
	}
	//
	private void duplicate_using_cosmetic() {
		// TODO Auto-generated method stub
		String id = txt_id.getText();
		String input_company = txt_company.getText();
		String input_cosname = txt_cosname.getText();
		
		String sql = "select * from using_cosmetic where user_id ='"+id+"' and using_company ='"+input_company+"' and using_cosname='"+input_cosname+"';";
		try
		{
			prestate = conn.prepareStatement(sql);
			rs = prestate.executeQuery();
			if(rs.next())
			{
				JOptionPane.showMessageDialog(getParent(), "이미 등록된 화장품입니다.");
			}
			else
			{
				duplicate_cosmetic();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	//
	private void duplicate_cosmetic() {
		// TODO Auto-generated method stub
		String input_company = txt_company.getText();
		String input_cosname = txt_cosname.getText();
		
		String sql = "select costype, func, skintype from cosmetic where company ='"+input_company+"' and cosname='"+input_cosname+"';";
		try
		{
			prestate = conn.prepareStatement(sql);
			rs = prestate.executeQuery();
			
			if(rs.next())
			{
				if(rs.first())
				{
					if(!rs.getString("costype").equals(select_costype))
					{
						String sql_update_costype = "Update cosmetic set costype=? where company=? and cosname=?; ";
						try
						{
							prestate = conn.prepareStatement(sql_update_costype);
							prestate.setString(1, select_costype);
							prestate.setString(2, txt_company.getText());
							prestate.setString(3, txt_cosname.getText());
							prestate.executeUpdate();	
						} 
						catch (SQLException e) 
						{
							e.printStackTrace();
						}
					}
					
					if(!rs.getString("func").equals(select_func))
					{
						String sql_update_func = "Update cosmetic set func=? where company=? and cosname=?; ";
						try
						{
							prestate = conn.prepareStatement(sql_update_func);
							prestate.setString(1, select_func);
							prestate.setString(2, txt_company.getText());
							prestate.setString(3, txt_cosname.getText());
							prestate.executeUpdate();
						} 
						catch (SQLException e) 
						{
							e.printStackTrace();
						}
					}
					
					if(!rs.getString("skintype").equals(select_skintype))
					{
						String sql_update_skintype = "Update cosmetic set skintype=? where company=? and cosname=?; ";
						try
						{
							prestate = conn.prepareStatement(sql_update_skintype);
							prestate.setString(1, select_skintype);
							prestate.setString(2, txt_company.getText());
							prestate.setString(3, txt_cosname.getText());
							prestate.executeUpdate();
						} 
						catch (SQLException e) 
						{
							e.printStackTrace();
						}
					}
				}
				String sql_insert_usingcosmetic = "insert into using_cosmetic values(?,?,?,?);";
				try
				{
					prestate = conn.prepareStatement(sql_insert_usingcosmetic);
					prestate.setString(1, txt_id.getText());
					prestate.setString(2, txt_company.getText());
					prestate.setString(3, txt_cosname.getText());
					prestate.setString(4, select_usercomment);
					prestate.executeUpdate();
					
					JOptionPane.showMessageDialog(getParent(), "추가 성공");	
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			else
			{	
				String sql_insert_cosmetic = "insert into cosmetic values(?,?,?,?,?);";
				String sql_insert_usingcosmetic2 = "insert into using_cosmetic values(?,?,?,?);";
				try
				{
					prestate = conn.prepareStatement(sql_insert_cosmetic);
					prestate.setString(1, txt_company.getText());
					prestate.setString(2, txt_cosname.getText());
					prestate.setString(3, select_costype);
					prestate.setString(4, select_func);
					prestate.setString(5, select_skintype);
					prestate.executeUpdate();
					
					prestate = conn.prepareStatement(sql_insert_usingcosmetic2);
					prestate.setString(1, txt_id.getText());
					prestate.setString(2, txt_company.getText());
					prestate.setString(3, txt_cosname.getText());
					prestate.setString(4, select_usercomment);
					prestate.executeUpdate();
					
					JOptionPane.showMessageDialog(getParent(), "추가 성공");
					
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}

			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	//
	private void update() {
		// TODO Auto-generated method stub
		if(is_null()){}
		else
		{
			if(JOptionPane.showConfirmDialog(getParent(),(String)table.getValueAt(selRow, 0)+
					" 브랜드의 "+(String)table.getValueAt(selRow, 1)+" 제품을 수정하시겠습니까?")==0)
			{
				String sql = "Update cosmetic set costype=?, func=?, skintype=?"
						+ "where company=? and cosname=?; ";
				String sql2 = "Update using_cosmetic set usercomment=?"
						+ "where user_id=? and using_company=? and using_cosname=?";
				try
				{
					prestate = conn.prepareStatement(sql);
					prestate.setString(1, select_costype);
					prestate.setString(2, select_func);
					prestate.setString(3, select_skintype);
					prestate.setString(4, txt_company.getText());
					prestate.setString(5, txt_cosname.getText());
					prestate.executeUpdate();
					prestate = conn.prepareStatement(sql2);
					prestate.setString(1, select_usercomment);
					prestate.setString(2, txt_id.getText());
					prestate.setString(3, txt_company.getText());
					prestate.setString(4, txt_cosname.getText());		
					prestate.executeUpdate();
					JOptionPane.showMessageDialog(getParent(), "수정 성공");
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(getParent(), "수정 실패");
				} 
			}
			else
			{
				JOptionPane.showMessageDialog(getParent(), "수정 취소");	
			}
		}
		
	}
	//
	private void delete() {

		// TODO Auto-generated method stub
		if(JOptionPane.showConfirmDialog(getParent(),(String)table.getValueAt(selRow, 0)+
				" 브랜드의 "+(String)table.getValueAt(selRow, 1)+" 제품을 삭제하시겠습니까?")==0)
		{
			String sql = "delete from using_cosmetic where user_id=? and using_company=? and using_cosname=?;";
			try
			{
				prestate = conn.prepareStatement(sql);
				prestate.setString(1, txt_id.getText());
				prestate.setString(2, txt_company.getText());
				prestate.setString(3, txt_cosname.getText());
				prestate.executeUpdate();
				JOptionPane.showMessageDialog(getParent(), "삭제 성공");
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(getParent(), "삭제 취소");
			
		}
	}
	//
	public void searchall() {
		// TODO Auto-generated method stub
		String id = txt_id.getText();
		Vector list = new Vector();
		String sql = "select using_company, using_cosname, costype, func,"
				+ "skintype, usercomment from using_cosmetic, cosmetic "
				+ "where using_cosmetic.user_id='"+id+"' and cosmetic.company = using_cosmetic.using_company "
				+ "and cosmetic.cosname = using_cosmetic.using_cosname;";
		try
		{
			prestate = conn.prepareStatement(sql);
			rs = prestate.executeQuery();
			while (rs.next())
			{
				Vector record = new Vector();
				
				record.add(rs.getString("using_company"));
				record.add(rs.getString("using_cosname"));
				record.add(rs.getString("costype"));
				record.add(rs.getString("func"));
				record.add(rs.getString("skintype"));
				record.add(rs.getString("usercomment"));

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
	private void search() {
		// TODO Auto-generated method stub
		Vector list = new Vector();
		String keyword = txt_keyword.getText();
		String id = txt_id.getText();
		String sql = "select using_company, using_cosname, costype, func,"
				+ "skintype, usercomment from using_cosmetic, cosmetic "
				+ "where using_cosmetic.user_id='"+id+"' "
				+ "and "+select_search+" like '%"+keyword+"%'"
				+ "and cosmetic.company = using_cosmetic.using_company "
				+ "and cosmetic.cosname = using_cosmetic.using_cosname ;";
				//+ "";
		try
		{
			prestate = conn.prepareStatement(sql);
			rs = prestate.executeQuery();
			while (rs.next())
			{
				Vector record = new Vector();
				
				record.add(rs.getString("using_company"));
				record.add(rs.getString("using_cosname"));
				record.add(rs.getString("costype"));
				record.add(rs.getString("func"));
				record.add(rs.getString("skintype"));
				record.add(rs.getString("usercomment"));
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

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if(sex_m.isSelected())
			select_sex = "남";
		else if(sex_f.isSelected())
			select_sex = "여";
		
		if(ctype_toner.isSelected())
			select_costype = "스킨";
		else if(ctype_lotion.isSelected())
			select_costype = "로션";
		else if(ctype_cream.isSelected())
			select_costype = "크림";
		else if(ctype_serum.isSelected())
			select_costype = "에센스/세럼/앰플";
		else if(ctype_pack.isSelected())
			select_costype = "팩";
		
		if(func_moisturizing.isSelected())
			select_func = "수분/보습";
		else if(func_whitening.isSelected())
			select_func = "미백";
		else if(func_trouble.isSelected())
			select_func = "트러블";
		else if(func_wrinkle.isSelected())
			select_func = "탄력/주름";
		else if(func_sunblock.isSelected())
			select_func = "자외선차단";
		
		if(stype_normal.isSelected())
			select_skintype = "모든피부";
		else if(stype_dry.isSelected())
			select_skintype = "건성";
		else if(stype_oily.isSelected())
			select_skintype = "지성";
		else if(stype_complex.isSelected())
			select_skintype = "복합성";
		else if(stype_sensitive.isSelected())
			select_skintype = "민감성";
		
		if(ucomment_good.isSelected())
			select_usercomment = "좋음";
		else if(ucomment_soso.isSelected())
			select_usercomment = "그저그럼";
		else if(ucomment_bad.isSelected())
			select_usercomment = "나쁨";
		
		Object button = e.getSource();
		//
		if(button == b_login)
		{
			// DB연결
			connect();
			if(login())
			{
				able_by_login();
				searchall();
				table.updateUI();
			}
			else
			{
				JOptionPane.showMessageDialog(getParent(), "일치하는 정보가 없습니다.");
			}
		}
		else if(button == b_logout)
		{
			all_clear();
			able_default();
			closeDB();
			model.resetlist();
			table.updateUI();
		}
		//
		else if(button == b_signup)
		{
			able_by_signup();
			table.updateUI();
		}
		//
		else if(button == b_register)
		{
			connect();
			user_insert();
			all_clear();
			able_default();
			table.updateUI();
		}
		//
		else if(button == b_user_update)
		{
			user_update();
			table.updateUI();
		}
		//
		else if(button == b_user_delete)
		{
			user_delete();
			all_clear();
			able_default();
			closeDB();
			model.resetlist();
			table.updateUI();
		}
		//
		else if(button == b_cancel)
		{
			user_clear();
			able_default();
			table.updateUI();
		}
		//
		else if(button == b_insert)
		{
			insert();
			able_by_login();
			searchall();
			cosmetic_clear();
			table.updateUI();
		}
		//
		else if(button == b_delete)
		{
			delete();
			able_by_login();
			searchall();
			cosmetic_clear();
			table.updateUI();
		}
		//
		else if(button == b_update)
		{
			update();
			able_by_login();
			searchall();
			cosmetic_clear();
			table.updateUI();
		}
		//
		else if(button == b_searchall)
		{
			searchall();
			able_by_login();
			cosmetic_clear();
			table.updateUI();
		}
		//
		else if(button == b_search)
		{
			able_by_login();
			cosmetic_clear();
			search();
			table.updateUI();
		}	
			
	}

	

	public static void main(String[] args)
	{
		new cosmetic();
	}

	
}
