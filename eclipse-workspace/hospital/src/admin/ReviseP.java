package admin;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class ReviseP extends JFrame implements ActionListener{
		//����һЩ���
		JLabel bg;
		JButton jb;
		JTable tb;
		JPanel jp;
		String username,userID;
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			new ReviseP("ylj","1800013005");
		}

		//���캯��
		public ReviseP(String username_,String userID_){
			username=username_;
			userID=userID_;
			
			
			String[] columnNames = { "A", "B" }; // ��������������
			// ��������������
			String[][] tableValues = { { "A1", "B1" }, { "A2", "B2" },
					{ "A3", "B3" }, { "A4", "B4" }, { "A5", "B5" } };
			// ����ָ�����������ݵı��
			JTable table = new JTable(tableValues, columnNames);
			// ������ʾ���Ĺ������
			JScrollPane scrollPane = new JScrollPane(table);
			// �����������ӵ��߽粼�ֵ��м�
			getContentPane().add(scrollPane);
			
			JPanel jp=(JPanel)this.getContentPane();
			jp.setLayout(null);
			
			bg=new JLabel(new ImageIcon("image/bg2.jpg"));
			bg.setBounds(0,0,700,500);
			
			
			
			jp.add(bg);
			jp.add(scrollPane);
			
			jp.setOpaque(false);
			
			ImageIcon imageIcon = new ImageIcon("image/icon.png");
			Image _image = imageIcon.getImage();
			this.setIconImage(_image);
			
			//���ñ���
			this.setTitle("�ҵĹ�������Ա��");
			//���ô�С
			this.setSize(700, 500);
			//���ÿɼ�
			this.setVisible(true);
			this.setLocationRelativeTo(null);// ����
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
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

