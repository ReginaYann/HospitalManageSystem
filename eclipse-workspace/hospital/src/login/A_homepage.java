package login;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*import administrator.Doctor;
import administrator.Medicine;
import administrator.Nurse;
import administrator.Office;
import administrator.Operate;
import com.administrator.Patient;
import com.administrator.Room;*/

public class A_homepage extends JFrame implements ActionListener{
		//定义一些组件
		JLabel bg,lbaddp,welcome,lbid;
		JButton addp,delp,revisep,adddoc,deldoc,revisedoc,adddean,deldean,revisedean;
		JButton adddept,deldept,revisedept,jbquit;
		JTextField jtf;
		JPanel jp;
		String username,userID;
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			new A_homepage("ylj","1800013005");
		}

		//构造函数
		public A_homepage(String username_,String userID_){
			username=username_;
			userID=userID_;
			
			welcome=new JLabel("您好！管理员");
			welcome.setFont(new Font("宋体",Font.BOLD ,15));
			welcome.setBounds(10,10,200,20);
			
			jbquit=new JButton("退出登录");
			jbquit.setBounds(560,440,120,20);
			jbquit.addActionListener(this);
			//增加、删除 患者、医生、科长、科室
			//修改 医生、科长、科室信息
			
			lbid=new JLabel("      需要进行删除、修改操作时，请将对应人员/科室id填入最上方框中");
			lbid.setFont(new Font("宋体",Font.BOLD,15));
			lbid.setBounds(30,430,600,40);
			jtf=new JTextField(10);
			jtf.setBounds(275,60,120,30);
			
			//患者
			addp=new JButton("增加患者");
			addp.setFont(new Font("宋体",Font.PLAIN,20));
			addp.setBounds(50,100,120,40);
			addp.addActionListener(this);
			lbaddp=new JLabel("备注：由管理员进行人员增加时，密码请默认填为123456");
			lbaddp.setFont(new Font("宋体",Font.BOLD,15));
			lbaddp.setBounds(30,400,600,40);
			delp=new JButton("删除患者");
			delp.setFont(new Font("宋体",Font.PLAIN,20));
			delp.setBounds(50,200,120,40);
			delp.addActionListener(this);
			
			//医生
			adddoc=new JButton("增加医生");
			adddoc.setFont(new Font("宋体",Font.PLAIN,20));
			adddoc.setBounds(200,100,120,40);
			adddoc.addActionListener(this);
			deldoc=new JButton("删除医生");
			deldoc.setFont(new Font("宋体",Font.PLAIN,20));
			deldoc.setBounds(200,200,120,40);
			deldoc.addActionListener(this);
			revisedoc=new JButton("修改医生");
			revisedoc.setFont(new Font("宋体",Font.PLAIN,20));
			revisedoc.setBounds(200,300,120,40);
			revisedoc.addActionListener(this);
			
			//科长
			adddean=new JButton("增加科长");
			adddean.setFont(new Font("宋体",Font.PLAIN,20));
			adddean.setBounds(350,100,120,40);
			adddean.addActionListener(this);
			deldean=new JButton("删除科长");
			deldean.setFont(new Font("宋体",Font.PLAIN,20));
			deldean.setBounds(350,200,120,40);
			deldean.addActionListener(this);
			revisedean=new JButton("修改科长");
			revisedean.setFont(new Font("宋体",Font.PLAIN,20));
			revisedean.setBounds(350,300,120,40);
			revisedean.addActionListener(this);
			
			//科室
			adddept=new JButton("增加科室");
			adddept.setFont(new Font("宋体",Font.PLAIN,20));
			adddept.setBounds(500,100,120,40);
			adddept.addActionListener(this);
			deldept=new JButton("删除科室");
			deldept.setFont(new Font("宋体",Font.PLAIN,20));
			deldept.setBounds(500,200,120,40);
			deldept.addActionListener(this);
			revisedept=new JButton("修改科室");
			revisedept.setFont(new Font("宋体",Font.PLAIN,20));
			revisedept.setBounds(500,300,120,40);
			revisedept.addActionListener(this);
			
			bg=new JLabel(new ImageIcon("image/bg2.jpg"));
			bg.setBounds(0,0,700,500);
			
			JPanel jp=(JPanel)this.getContentPane();
			jp.setLayout(null);
			
			jp.add(bg);
			jp.add(welcome);
			jp.add(addp);
			jp.add(lbaddp);
			jp.add(delp);
			jp.add(adddoc);
			jp.add(deldoc);
			jp.add(revisedoc);
			jp.add(adddean);
			jp.add(deldean);
			jp.add(revisedean);
			jp.add(adddean);
			jp.add(deldean);
			jp.add(revisedean);
			jp.add(revisedept);
			jp.add(adddept);
			jp.add(deldept);
			jp.add(revisedept);
			jp.add(lbid);
			jp.add(jtf);
			jp.add(jbquit);
			
			jp.setOpaque(false);
			
			ImageIcon imageIcon = new ImageIcon("image/icon.png");
			Image _image = imageIcon.getImage();
			this.setIconImage(_image);
			
			//设置标题
			this.setTitle("个人页面———administrator");
			//设置大小
			this.setSize(700, 500);
			//设置可见
			this.setVisible(true);
			this.setLocationRelativeTo(null);// 居中
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jbquit){
			//显示医生表
			dispose();
		}
		/*if(e.getSource()==jb1){
			//显示医生表
			new Doctor();
		}else if(e.getSource()==jb2){
			//显示护士表
			new Nurse();
		}else if(e.getSource()==jb3){
			//显示病人表
			new Patient();
		}else if(e.getSource()==jb4){
			//显示药品表
			new Medicine();
		}else if(e.getSource()==jb5){
			//显示病房表
			new Room();
		}else if(e.getSource()==jb6){
			//显示科室表
			new Office();
		}else if(e.getSource()==jb7){
			//显示手术表
			new Operate();
		}*/
	}
}

