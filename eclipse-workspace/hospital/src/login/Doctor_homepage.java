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

public class Doctor_homepage extends JFrame implements ActionListener{
		JLabel lbinfo,lbhist,lbtoday,bg,lbquit,welcome;
		JButton info,hist,today,jb4,jbquit;
		JPanel jp;
		String username,userID;

		public Doctor_homepage(String username_,String userID_){
			username=username_;
			userID=userID_;
			
			welcome=new JLabel("���ã�"+username+" ҽ��");
			welcome.setFont(new Font("����",Font.BOLD ,15));
			welcome.setBounds(10,10,200,20);
			
			jbquit=new JButton("�˳���¼");
			jbquit.setBounds(540,340,120,20);
			jbquit.addActionListener(this);
			
			lbinfo=new JLabel(new ImageIcon("images/info.png"));
			lbinfo.setBounds(150,100,80,80);
			info=new JButton("������Ϣ");
			info.setBounds(140, 200, 100, 30);
			info.addActionListener(this);
			
			lbhist=new JLabel(new ImageIcon("images/record.png"));
			lbhist.setBounds(330,100,80,80);
			hist=new JButton("��ʷ����");
			hist.setBounds(320, 200, 100, 30);
			hist.addActionListener(this);
			
			lbtoday=new JLabel(new ImageIcon("images/record.png"));
			lbtoday.setBounds(510,100,80,80);
			today=new JButton("���մ�������");
			today.setBounds(470, 200, 150, 30);
			today.addActionListener(this);
			
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
			jp.add(bg);
			
			jp.setOpaque(false);
			
			ImageIcon imageIcon = new ImageIcon("images/hospital_icon.png");
			Image _image = imageIcon.getImage();
			this.setIconImage(_image);
			
			//���ñ���
			this.setTitle("������ҳ����Doctor");
			//���ô�С
			this.setSize(700, 400);
			//���ÿɼ�
			this.setVisible(true);
			this.setLocationRelativeTo(null);// ����
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Doctor_homepage("ylj","1800013005");
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
