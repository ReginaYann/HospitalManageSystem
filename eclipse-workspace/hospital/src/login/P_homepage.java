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

/*import com.administrator.Patient;
import com.administrator.Room;
import com.nurse.Nurse_inf;
import com.nurse.Operate;*/

public class P_homepage extends JFrame implements ActionListener{
		//����һЩ���
		JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb,lbid,bg;
		JButton jb1,jb2,jb3,jb4,jb5,jb6,jbquit,fever,appoint;
		JPanel jp;
		String username,pid;
		JLabel lbpay;
		
		//���캯��
		public P_homepage(String username_,String pid_){
			username=username_;
			pid=pid_;
			lbid=new JLabel("���ã� "+username);
			lbid.setFont(new Font("����",Font.BOLD ,15));
			lbid.setBounds(10,10,200,20);
				
			lb1=new JLabel(new ImageIcon("images/info.png"));
			lb1.setBounds(70,50,80,80);
			jb1=new JButton("�鿴������Ϣ");
			jb1.setBounds(50, 140, 120, 30);
			jb1.addActionListener(this);
			
			lb2=new JLabel(new ImageIcon("images/search.png"));
			lb2.setBounds(220,50,80,80);
			jb2=new JButton("���Ҳ�ѯ");
			jb2.setBounds(210, 140, 100, 30);
			jb2.addActionListener(this);
			
			lb3=new JLabel(new ImageIcon("images/search.png"));
			lb3.setBounds(370,50,80,80);
			jb3=new JButton("ҽ����ѯ");
			jb3.setBounds(360, 140, 100, 30);
			jb3.addActionListener(this);
			
			lb4=new JLabel(new ImageIcon("images/record.png"));
			lb4.setBounds(520,50,80,80);
			jb4=new JButton("�鿴�����¼");
			jb4.setBounds(500, 140, 120, 30);
			jb4.addActionListener(this);
			
			appoint=new JButton("�鿴�Һż�¼");
			appoint.setBounds(500, 170, 120, 30);
			appoint.addActionListener(this);
			
			lb5=new JLabel(new ImageIcon("images/payment.png"));
			lb5.setBounds(170,190,80,80);
			jb5=new JButton("�ɷѽ���ͨ��");
			jb5.setBounds(150, 280, 120, 30);
			jb5.addActionListener(this);
			
			lb6=new JLabel(new ImageIcon("images/reservation.png"));
			lb6.setBounds(420,190,80,80);
			jb6=new JButton("�Ƿ��Ȳ���ԤԼ�Һ�");
			jb6.setBounds(370, 280, 180, 30);
			jb6.addActionListener(this);
			
			fever=new JButton("���Ȳ���ר��ԤԼ�Һ�");
			fever.setBounds(370, 310, 180, 30);
			fever.addActionListener(this);
			
			jbquit=new JButton("�˳���¼");
			jbquit.setBounds(540,340,120,20);
			jbquit.addActionListener(this);
			
			
			lb=new JLabel(new ImageIcon("image/bg2.jpg"));
			lb.setBounds(0,0,700,400);
			
			// ��ѯ�Ƿ���δ��ɵĽɷѣ�����о���ʾ��һ��
			int ispayed=1;
			lbpay=new JLabel("����δ�ɷѼ�¼���뾡��ɷ�");
			lbpay.setFont(new Font("����",Font.BOLD ,20));
			lbpay.setBounds(10,340,500,20);
			
			JPanel jp=(JPanel)this.getContentPane();
			jp.setLayout(null);
			
			if (ispayed==1) {
				jp.add(lbpay);
			} 
			jp.add(lbid);
			jp.add(lb1);
			jp.add(lb2);
			jp.add(lb3);
			jp.add(lb4);
			jp.add(lb5);
			jp.add(lb6);
			jp.add(jb1);
			jp.add(jb2);
			jp.add(jb3);
			jp.add(jb4);
			jp.add(jb5);
			jp.add(jb6);
			jp.add(fever);
			jp.add(appoint);
			jp.add(lb);
			jp.add(jbquit);
			
			jp.setOpaque(false);
			
			ImageIcon imageIcon = new ImageIcon("images/hospital_icon.png");
			Image _image = imageIcon.getImage();
			this.setIconImage(_image);
			
			//���ñ���
			this.setTitle("������ҳ����Patient");
			//���ô�С
			this.setSize(700, 400);
			//���ÿɼ�
			this.setVisible(true);
			this.setLocationRelativeTo(null);// ����
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new P_homepage("ylj","1800013005");	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jbquit){
			dispose();
		}
		/*if(e.getSource()==jb1){
			new P_info(pid);
		}
		else if(e.getSource()==jb2){
			new P_search_dept();
		}
		else if(e.getSource()==jb3){
			new P_search_doctor();
		}
		else if(e.getSource()==jb4){
			new P_history();
		}
		else if(e.getSource()==jb5) {
			new P_pay();
		}
		else if(e.getSource()==jb6) {
			new P_simple();
		}
		else if(e.getSource()==fever) {
			new P_fever();
		}
		else if(e.getSource()==appoint) {
			new P_checka();
		}
		*/
	}
}
