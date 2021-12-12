package login;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class add_patient extends JFrame implements ActionListener{
		public static String userId;
	    //�������
		JLabel lbname,lbid,lbbirthday,lbex,lbgender,lbphone,lbemail,lbpsw1,lbpsw2;
		JLabel msg,lblogname;
		JButton jbconfirm,jbquit;
		JTextField name,id,birthday,gender,phone,email,logname;
		JPasswordField psw1,psw2;
		
		//�������ݿ�Ҫ�õĶ���
		Connection ct=null;
		PreparedStatement ps= null;	
		ResultSet rs=null;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url="jdbc:sqlserver://localhost:1433;databaseName=hospital";
		String user="aaa";
		String password="111";
				
		//���캯��
		public add_patient()
		{

			lbname=new JLabel("����");
			name=new JTextField(200);
			lbname.setBounds(20,20,80,30);
			name.setBounds(110,20,200,30);
			
			lbid=new JLabel("���֤��");
			id=new JTextField(200);
			lbid.setBounds(20,50,80,30);
			id.setBounds(110,50,200,30);
			
			lbbirthday=new JLabel("��������");
			lbex=new JLabel("(ʾ����20000804)");
			birthday=new JTextField(200);
			lbbirthday.setBounds(20,80,80,30);
			lbex.setBounds(0,100,120,30);
			birthday.setBounds(110,80,200,30);
			
			lbgender=new JLabel("�Ա�");
			gender=new JTextField(200);
			lbgender.setBounds(20,120,80,30);
			gender.setBounds(110,120,200,30);
			
			lbphone=new JLabel("�绰");
			phone=new JTextField(200);
			lbphone.setBounds(20,150,80,30);
			phone.setBounds(110,150,200,30);

			lbemail=new JLabel("���䣨ѡ�");
			email=new JTextField(200);
			lbemail.setBounds(20,180,80,30);
			email.setBounds(110,180,200,30);
			
			lblogname=new JLabel("��¼�û���");
			logname=new JTextField(200);
			lblogname.setBounds(20,210,80,30);
			logname.setBounds(110,210,200,30);

			lbpsw1=new JLabel("����");
			psw1=new JPasswordField(200);
			lbpsw1.setBounds(20,240,80,30);
			psw1.setBounds(110,240,200,30);
			
			lbpsw2=new JLabel("ȷ������");
			psw2=new JPasswordField(200);
			lbpsw2.setBounds(20,270,80,30);
			psw2.setBounds(110,270,200,30);
			
			msg=new JLabel("�������Ա����֤�ţ���¼��һ���ύ���޷��޸ģ�");
			msg.setFont(new Font("����",Font.BOLD ,15));
			msg.setBounds(0,300,380,30);
			
			jbconfirm=new JButton("�ύ");
			jbconfirm.setBounds(240,330,60,20);
			jbconfirm.addActionListener(this);
			
			jbquit=new JButton("ȡ��");
			jbquit.setBounds(300,330,60,20);
			jbquit.addActionListener(this);
			
			JPanel jp=(JPanel)this.getContentPane();
			jp.setLayout(null);

			
			jp.add(lbname);
			jp.add(name);
			jp.add(lbid);
			jp.add(id);
			jp.add(lbbirthday);
			jp.add(lbex);
			jp.add(birthday);
			jp.add(lbgender);
			jp.add(gender);
			jp.add(lbphone);
			jp.add(phone);
			jp.add(lbemail);
			jp.add(email);
			jp.add(lblogname);
			jp.add(logname);
			jp.add(lbpsw1);
			jp.add(psw1);
			jp.add(lbpsw2);
			jp.add(psw2);
			jp.add(msg);
			jp.add(jbconfirm);
			jp.add(jbquit);
			
			JLabel bg=new JLabel(new ImageIcon("image/login1.jpg"));
			bg.setBounds(0,0,355,220);
			jp.add(bg);
			jp.setOpaque(false);
			
			ImageIcon imageIcon = new ImageIcon("images/hospital_icon.png");
			Image _image = imageIcon.getImage();
			this.setIconImage(_image);
			
			//���ñ���
			this.setTitle("����ע��");
			//���ô�С
			this.setSize(400, 400);
			this.setLocationRelativeTo(null);// ����
			this.setResizable(false);// �̶����ڴ�С
			//���ÿɼ�
			this.setVisible(true);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==jbquit){
				this.dispose();
			}
			else if(e.getSource()==jbconfirm) {
				// �ж���������Ϣȫ��ȫ��ȫ�Ļ�������Ϣ����ȫ�Ļ�����ʾ��Щ������ȫ
				// ���ע��ɹ���������һ������˵ע��ɹ������ɹ��Ļ���˵���ɹ�
			}
			/*else if(e.getSource()==jb1){
				String users=jtf.getText();  
				String passwords=jpf.getText();
				//�������ݿ�
				Connection ct=null;
				PreparedStatement ps= null;	
				ResultSet rs=null;
				if(users.length()==0){
					JOptionPane.showMessageDialog((Component)null,"�����������û���","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
					jlb1.requestFocus();
				}
				else if(passwords.length()==0){
					JOptionPane.showMessageDialog((Component)null,"��������������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
					jlb2.requestFocus();
				}
				else{
					try{
						//1����������(������Ҫ��������������ڴ���)
						Class.forName(driver);
						//2���õ�����(ָ�����ӵ��ĸ�����Դ)
						ct = DriverManager.getConnection(url,user,password); 
						//3������ps
						ps=ct.prepareStatement("select * from Nurse where NurseID="+users+"");
						//Ԥ����������
						rs=ps.executeQuery();//���ز�ѯ���
						//����в�ѯ���
						rs.next();
						String sql_pass=rs.getString(7).trim();
						userId=rs.getString(1).trim();
						if(!rs.next()){
							if(passwords.equals(sql_pass)){
								System.out.println("����"+passwords);
								new N_homepage();
								this.dispose();
							}
							else{
								JOptionPane.showMessageDialog((Component)null,"�������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
								jpf.requestFocus();
							}
						}
					}
					catch(Exception e2){
						JOptionPane.showMessageDialog((Component)null,"�û�������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
						jlb1.requestFocus();
					}
					finally{
						//�ر���Դ���ر�˳��˭�󴴽����ȹر�)
						try{
							if(rs!=null) rs.close();
							if(ps!=null) ps.close();
							if(ct!=null) ct.close();
						}
						catch(Exception e1){
							
						}
					}
				}
			}*/
		}
}
