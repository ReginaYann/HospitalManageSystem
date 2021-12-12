package login;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class choose_login extends JFrame implements ActionListener{

	JButton jlb1;
	JButton jlb2;
	JButton jlb3;
	JButton jlb4;
	JButton jlb5;
	public choose_login(){
		jlb1=new JButton("管理员登录");
		jlb1.addActionListener(this);
		jlb2=new JButton("病人登录");
		jlb2.addActionListener(this);
		jlb5=new JButton("病人注册");
		jlb5.addActionListener(this);
		jlb3=new JButton("医生登录");
		jlb3.addActionListener(this);
		jlb4=new JButton("科室主任登录");
		jlb4.addActionListener(this);
		
		jlb1.setBounds(70, 260, 100, 30);
		jlb2.setBounds(230, 260, 100, 30);
		jlb3.setBounds(380, 260, 100, 30);
		jlb4.setBounds(510, 260, 120, 30);
		jlb5.setBounds(230,300,100,30);
		
		JPanel jp=(JPanel)this.getContentPane();
		jp.setLayout(null);
		

		JLabel name=new JLabel("医院管理系统");
		name.setBounds(250,30,230,40);
		name.setFont(new Font("宋体", Font.BOLD, 35));
		JLabel copyright=new JLabel("Designed by 严宇婷 周佳颖 严丽君");
		copyright.setBounds(400,430,260,30);
		copyright.setFont(new Font("宋体",Font.PLAIN ,15));
		JLabel bg1=new JLabel(new ImageIcon("images/admin_icon.png"));
		bg1.setBounds(60,120,120,120);
		JLabel bg2=new JLabel(new ImageIcon("images/patient_icon.png"));
		bg2.setBounds(220,120,120,120);
		JLabel bg3=new JLabel(new ImageIcon("images/doctor_icon.png"));
		bg3.setBounds(370,120,120,120);
		JLabel bg4=new JLabel(new ImageIcon("images/dean_icon.png"));
		bg4.setBounds(500,120,120,120);
		//JLabel bg5=new JLabel(new ImageIcon("images/beijing3.jpg"));
		//bg4.setBounds(0,0,700,450);
		
		jp.add(jlb1);
		jp.add(jlb2);
		jp.add(jlb3);
		jp.add(jlb4);
		jp.add(jlb5);
		jp.add(name);
		jp.add(copyright);
		jp.add(bg1);
		jp.add(bg2);
		jp.add(bg3);
		jp.add(bg4);

		jp.setOpaque(false);

		ImageIcon imageIcon = new ImageIcon("images/hospital_icon.png");
		Image _image = imageIcon.getImage();
		this.setIconImage(_image);


		this.setTitle("医院管理系统");
		this.setSize(700, 500);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new choose_login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jlb1){
			new Login_adm();
		}
		else if(e.getSource()==jlb2){
			new Login_patient();
		}
		else if(e.getSource()==jlb3){
			new Login_doctor();
		}
		else if(e.getSource()==jlb4) {
			new Login_dean();
		}
		else if(e.getSource()==jlb5) {
			new add_patient();
		}
	}
}
