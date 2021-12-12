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
		//定义一些组件
		JLabel bg;
		JButton jb;
		JTable tb;
		JPanel jp;
		String username,userID;
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			new ReviseP("ylj","1800013005");
		}

		//构造函数
		public ReviseP(String username_,String userID_){
			username=username_;
			userID=userID_;
			
			
			String[] columnNames = { "A", "B" }; // 定义表格列名数组
			// 定义表格数据数组
			String[][] tableValues = { { "A1", "B1" }, { "A2", "B2" },
					{ "A3", "B3" }, { "A4", "B4" }, { "A5", "B5" } };
			// 创建指定列名和数据的表格
			JTable table = new JTable(tableValues, columnNames);
			// 创建显示表格的滚动面板
			JScrollPane scrollPane = new JScrollPane(table);
			// 将滚动面板添加到边界布局的中间
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
			
			//设置标题
			this.setTitle("我的管理（管理员）");
			//设置大小
			this.setSize(700, 500);
			//设置可见
			this.setVisible(true);
			this.setLocationRelativeTo(null);// 居中
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
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

