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
		//����һЩ���
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

		//���캯��
		public A_homepage(String username_,String userID_){
			username=username_;
			userID=userID_;
			
			welcome=new JLabel("���ã�����Ա");
			welcome.setFont(new Font("����",Font.BOLD ,15));
			welcome.setBounds(10,10,200,20);
			
			jbquit=new JButton("�˳���¼");
			jbquit.setBounds(560,440,120,20);
			jbquit.addActionListener(this);
			//���ӡ�ɾ�� ���ߡ�ҽ�����Ƴ�������
			//�޸� ҽ�����Ƴ���������Ϣ
			
			lbid=new JLabel("      ��Ҫ����ɾ�����޸Ĳ���ʱ���뽫��Ӧ��Ա/����id�������Ϸ�����");
			lbid.setFont(new Font("����",Font.BOLD,15));
			lbid.setBounds(30,430,600,40);
			jtf=new JTextField(10);
			jtf.setBounds(275,60,120,30);
			
			//����
			addp=new JButton("���ӻ���");
			addp.setFont(new Font("����",Font.PLAIN,20));
			addp.setBounds(50,100,120,40);
			addp.addActionListener(this);
			lbaddp=new JLabel("��ע���ɹ���Ա������Ա����ʱ��������Ĭ����Ϊ123456");
			lbaddp.setFont(new Font("����",Font.BOLD,15));
			lbaddp.setBounds(30,400,600,40);
			delp=new JButton("ɾ������");
			delp.setFont(new Font("����",Font.PLAIN,20));
			delp.setBounds(50,200,120,40);
			delp.addActionListener(this);
			
			//ҽ��
			adddoc=new JButton("����ҽ��");
			adddoc.setFont(new Font("����",Font.PLAIN,20));
			adddoc.setBounds(200,100,120,40);
			adddoc.addActionListener(this);
			deldoc=new JButton("ɾ��ҽ��");
			deldoc.setFont(new Font("����",Font.PLAIN,20));
			deldoc.setBounds(200,200,120,40);
			deldoc.addActionListener(this);
			revisedoc=new JButton("�޸�ҽ��");
			revisedoc.setFont(new Font("����",Font.PLAIN,20));
			revisedoc.setBounds(200,300,120,40);
			revisedoc.addActionListener(this);
			
			//�Ƴ�
			adddean=new JButton("���ӿƳ�");
			adddean.setFont(new Font("����",Font.PLAIN,20));
			adddean.setBounds(350,100,120,40);
			adddean.addActionListener(this);
			deldean=new JButton("ɾ���Ƴ�");
			deldean.setFont(new Font("����",Font.PLAIN,20));
			deldean.setBounds(350,200,120,40);
			deldean.addActionListener(this);
			revisedean=new JButton("�޸ĿƳ�");
			revisedean.setFont(new Font("����",Font.PLAIN,20));
			revisedean.setBounds(350,300,120,40);
			revisedean.addActionListener(this);
			
			//����
			adddept=new JButton("���ӿ���");
			adddept.setFont(new Font("����",Font.PLAIN,20));
			adddept.setBounds(500,100,120,40);
			adddept.addActionListener(this);
			deldept=new JButton("ɾ������");
			deldept.setFont(new Font("����",Font.PLAIN,20));
			deldept.setBounds(500,200,120,40);
			deldept.addActionListener(this);
			revisedept=new JButton("�޸Ŀ���");
			revisedept.setFont(new Font("����",Font.PLAIN,20));
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
			
			//���ñ���
			this.setTitle("����ҳ�桪����administrator");
			//���ô�С
			this.setSize(700, 500);
			//���ÿɼ�
			this.setVisible(true);
			this.setLocationRelativeTo(null);// ����
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jbquit){
			//��ʾҽ����
			dispose();
		}
		/*if(e.getSource()==jb1){
			//��ʾҽ����
			new Doctor();
		}else if(e.getSource()==jb2){
			//��ʾ��ʿ��
			new Nurse();
		}else if(e.getSource()==jb3){
			//��ʾ���˱�
			new Patient();
		}else if(e.getSource()==jb4){
			//��ʾҩƷ��
			new Medicine();
		}else if(e.getSource()==jb5){
			//��ʾ������
			new Room();
		}else if(e.getSource()==jb6){
			//��ʾ���ұ�
			new Office();
		}else if(e.getSource()==jb7){
			//��ʾ������
			new Operate();
		}*/
	}
}

