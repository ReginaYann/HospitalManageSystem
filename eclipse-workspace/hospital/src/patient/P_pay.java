/*
Unpaid record list generation;
Parameter:pid(String);
Written by zjy;
 */
package patient;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import login.choose_login;

//显示未缴费记录，跳转到缴费
public class P_pay extends JFrame implements ActionListener{
    JPanel jp1;  //组件1，提供缴费按钮
    JButton jb1;
    JTable jt;
    JScrollPane jsp;
    int pid; // 病人编号
    String sql = null;
    RecordTable table_init;
    public static void main(String[] args) {new P_pay("1");};
    public static int row_number;

    //连接数据库用到的东西
    Connection ct=null;
    PreparedStatement ps= null;
    ResultSet rs=null;
    PreparedStatement ps1= null;
    ResultSet rs1=null;
    PreparedStatement ps2= null;
    ResultSet rs2=null;
    String driver="com.mysql.cj.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/hospital";
    String user="root";
    String password="A20001112a";

    public P_pay(String pid_) {
        jp1=new JPanel();
        jb1=new JButton("缴费");
        jb1.addActionListener(this);

        //显示未缴费病历窗口
        jp1.add(jb1);
        pid=Integer.parseInt(pid_);
        if(pid_.equals("")){
            //如果是病人，展示提示信息
            JOptionPane.showMessageDialog(null,"查询不到您的编号，请重新登录！","提示信息",JOptionPane.ERROR_MESSAGE);
            new choose_login();
            //如果是管理员在查看，则直接进行下一步
        }
        else{
            sql="select * from medical_record where patient_id=pid and is_payed=0";

        }
        table_init=new RecordTable(sql);
        jt=new JTable(table_init);
        jsp=new JScrollPane(jt);

        this.add(jsp);
        this.add(jp1,"South");

        this.setSize(600,400);
        this.setLocationRelativeTo(null);// 居中
        this.setTitle("未缴费记录");
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jb1) {
            int row_num=this.jt.getSelectedRow(); //选中病历
            if(row_num==-1){
                JOptionPane.showMessageDialog(this,"请选择您要缴费的记录！","提示信息",JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
                String payment_=(String) table_init.getValueAt(row_num, 4);
               new payment_choice(Integer.toString(pid), payment_);
            }
        }

        //管理员可据此添加其他操作，如删除等；——>ylj

    }


}