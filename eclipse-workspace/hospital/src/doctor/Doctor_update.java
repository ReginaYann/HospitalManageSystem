package doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Doctor_update extends JFrame implements ActionListener {

	//定义需要的控件
	JLabel jlD_id,jlD_name,jlD_tel,jlD_mail,jlD_uName,jlD_pwd,jlD_title,jlD_domain;
	JButton jb1,jb2;
	JTextField jtD_id,jtD_name,jtD_tel,jtD_mail,jtD_uName,jtD_pwd,jtD_title,jtD_domain;
	JPanel jp1,jp2,jp3,jp4,jp5;

	//连接数据库要用的东西
	PreparedStatement ps= null;
	ResultSet rs=null;
	String URL = "jdbc:mysql://localhost/hospital?";
	String user="root";
	String password="123";

	int d_id;

	public Doctor_update(int Doctor_id) {
		d_id = Doctor_id;
		jlD_id = new JLabel("医生id");
		jlD_name = new JLabel("医生姓名");
		jlD_tel = new JLabel("电话号码");
		jlD_mail = new JLabel("邮箱");
		jlD_uName= new JLabel("用户昵称");
		jlD_pwd= new JLabel("用户密码");
		jlD_title= new JLabel("技术职称");
		jlD_domain = new JLabel("专业特长");

		jtD_id=new JTextField(10);
		jtD_name=new JTextField(10);
		jtD_tel=new JTextField(10);
		jtD_mail=new JTextField(10);
		jtD_uName=new JTextField(10);
		jtD_pwd=new JTextField(10);
		jtD_title=new JTextField(10);
		jtD_domain=new JTextField(10);

		try (Connection ct=DriverManager.getConnection(URL,user,password)) {
			try {
				ps = ct.prepareStatement("select * from doctor where doctor_id=?");
				ps.setInt(1,d_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					//展示所有的信息的同时，只允许修改部分信息
					jtD_id.setText(rs.getString(1));
					jtD_id.setEditable(false);
					jtD_name.setText(rs.getString(4));
					jtD_name.setEditable(false);
					jtD_tel.setText(rs.getString(8));
					//jtD_tel.setEditable(false);
					jtD_mail.setText(rs.getString(9));
					//jtD_mail.setEditable(false);
					jtD_uName.setText(rs.getString(10));
					jtD_uName.setEditable(false);
					jtD_pwd.setText(rs.getString(11));
					//jtD_pwd.setEditable(false);
					jtD_title.setText(rs.getString(14));
					//jtD_title.setEditable(false);
					jtD_domain.setText(rs.getString(15));
					//jtD_domain.setEditable(false);
				}
			} catch (Exception e2) {
			} finally {
				//关闭资源
				try {
					if (rs != null) rs.close();
					if (ps != null) ps.close();
					if (ct != null) ct.close();
				} catch (Exception e1) {
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jb1=new JButton("确认修改");
		jb1.addActionListener(this);
		jb2=new JButton("取消");
		jb2.addActionListener(this);

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();

		jp1.add(jlD_id);
		jp1.add(jtD_id);
		jp1.add(jlD_name);
		jp1.add(jtD_name);

		jp2.add(jlD_tel);
		jp2.add(jtD_tel);
		jp2.add(jlD_mail);
		jp2.add(jtD_mail);

		jp3.add(jlD_uName);
		jp3.add(jtD_uName);
		jp3.add(jlD_pwd);
		jp3.add(jtD_pwd);

		jp4.add(jlD_title);
		jp4.add(jtD_title);
		jp4.add(jlD_domain);
		jp4.add(jtD_domain);

		jp5.add(jb1);
		jp5.add(jb2);

		this.setLayout(new GridLayout(5,1));
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);

		//展现
		this.setSize(500,300);
		this.setTitle("信息修改");
		this.setLocationRelativeTo(null);// 居中
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new Doctor_update(1);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jb1){
			String sql = "update doctor set doctor_tel=?,doctor_mail=?,doctor_password=?,title=?,domain=?where doctor_id=?";
			try(Connection ct=DriverManager.getConnection(URL,user,password)) {
				try {
					//3、创建ps
					ps = ct.prepareStatement(sql, d_id);
					//预编译语句对象
					ps.setString(1, jtD_tel.getText());
					ps.setString(2, jtD_mail.getText());
					ps.setString(3, jtD_pwd.getText());
					ps.setString(4, jtD_title.getText());
					ps.setString(5, jtD_domain.getText());    //获取想要修改的内容
					ps.setInt(6, d_id);    //根据id修改
					ps.executeUpdate();
				} catch (Exception e2) {

				} finally {
					//关闭资源（关闭顺序：谁后创建则先关闭)
					try {
						if (rs != null) rs.close();
						if (ps != null) ps.close();
						if (ct != null) ct.close();
					} catch (Exception e1) {
					}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				new Doctor_inf(d_id);	//展示更新后的医生信息
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			this.dispose();
		}else if(e.getSource()==jb2){
			this.dispose();
		}
	}
}
