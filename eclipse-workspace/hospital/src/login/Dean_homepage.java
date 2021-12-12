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

/*import com.administrator.Nurse;
import com.administrator.Operate;
import com.administrator.Patient;
import com.doctor.Doctor_inf;*/

public class Dean_homepage extends JFrame implements ActionListener{
		JLabel lbinfo,lbhist,lbtoday,bg,lbquit,welcome;
		JButton info,hist,today,jb4,jbquit;
		JLabel lbdscp,lbdinfo,lbcheckr;
		JButton dscp,dinfo,checkr;
		JPanel jp;
		String username,userID;

		public Dean_homepage(String username_,String userID_){
			username=username_;
			userID=userID_;
			
			welcome=new JLabel("您好！"+username+" 科长");
			welcome.setFont(new Font("宋体",Font.BOLD ,15));
			welcome.setBounds(10,10,200,20);
			
			jbquit=new JButton("退出登录");
			jbquit.setBounds(540,340,120,20);
			jbquit.addActionListener(this);
			
			lbinfo=new JLabel(new ImageIcon("images/info.png"));
			lbinfo.setBounds(150,50,80,80);
			info=new JButton("个人信息");
			info.setBounds(140, 135, 100, 30);
			info.addActionListener(this);
			
			lbhist=new JLabel(new ImageIcon("images/record.png"));
			lbhist.setBounds(330,50,80,80);
			hist=new JButton("历史病人");
			hist.setBounds(320, 135, 100, 30);
			hist.addActionListener(this);
			
			lbtoday=new JLabel(new ImageIcon("images/record.png"));
			lbtoday.setBounds(510,50,80,80);
			today=new JButton("今日待处理病人");
			today.setBounds(470, 135, 150, 30);
			today.addActionListener(this);
			
			lbdscp=new JLabel(new ImageIcon("images/revise.png"));
			lbdscp.setBounds(150,200,80,80);
			dscp=new JButton("修改科室描述");
			dscp.setBounds(130, 285, 120, 30);
			dscp.addActionListener(this);
			
			lbdinfo=new JLabel(new ImageIcon("images/revise.png"));
			lbdinfo.setBounds(330,200,80,80);
			dinfo=new JButton("修改医生信息");
			dinfo.setBounds(310, 285, 120, 30);
			dinfo.addActionListener(this);
			
			lbcheckr=new JLabel(new ImageIcon("images/search.png"));
			lbcheckr.setBounds(510,200,80,80);
			checkr=new JButton("查看医生处方");
			checkr.setBounds(470, 285, 150, 30);
			checkr.addActionListener(this);
			
			bg=new JLabel(new ImageIcon("image/bg2.jpg"));
			bg.setBounds(0,0,700,400);
			
			JPanel jp=(JPanel)this.getContentPane();
			jp.setLayout(null);
			
			jp.add(welcome);
			jp.add(lbinfo);
			jp.add(info);
			jp.add(lbhist);
			jp.add(hist);
			jp.add(lbtoday);
			jp.add(today);
			jp.add(jbquit);
			jp.add(lbdscp);
			jp.add(dscp);
			jp.add(lbdinfo);
			jp.add(dinfo);
			jp.add(lbcheckr);
			jp.add(checkr);
			jp.add(bg);
			
			jp.setOpaque(false);
			
			ImageIcon imageIcon = new ImageIcon("images/hospital_icon.png");
			Image _image = imageIcon.getImage();
			this.setIconImage(_image);
			
			//设置标题
			this.setTitle("个人页面——Dean");
			//设置大小
			this.setSize(700, 400);
			//设置可见
			this.setVisible(true);
			this.setLocationRelativeTo(null);// 居中
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Dean_homepage("ylj","1800013005");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jbquit){
			dispose();
		}
		/*if(e.getSource()==jb1){
			new Doctor_inf();
		}else if(e.getSource()==jb2){
			new Patient();
		}else if(e.getSource()==jb3){
			new Nurse();
		}else if(e.getSource()==jb4){
			new Operate();
		}*/
	}
}
