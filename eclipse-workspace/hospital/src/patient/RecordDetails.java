/*
Record details generation;
Parameter:recordID, root("PATIENT" or other)
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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class RecordDetails extends JFrame implements ActionListener {
    JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8,jp9,jp10,jp11,jp12;
    JLabel jl1_0,jl1_1,jl2_0,jl2_1,jl2_2,jl3_0,jl3_1,jl3_2,jl4_0,jl11_1,jl11_0;
    JLabel jl5,jl6,jl7,jl8,jl9,jl10;
    JTextField jtf1_0,jtf1_1,jtf2_0,jtf2_1,jtf2_2,jtf3_0,jtf3_1,jtf3_2,jtf11_0,jtf11_1;
    JTextField jtf4_0,jtf5,jtf6,jtf7,jtf8,jtf9,jtf10;
    JButton jb1;  //只有医生和管理员能看见的修改按钮
    String STATUS;

    //连接数据库要用的东西
    Connection ct=null;
    PreparedStatement ps= null;
    ResultSet rs=null;
    PreparedStatement ps1= null;
    ResultSet rs1=null;
    PreparedStatement ps2= null;
    ResultSet rs2=null;
    PreparedStatement ps3= null;
    ResultSet rs3=null;
    String driver="com.mysql.cj.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/hospital";
    String user="root";
    String password="A20001112a";
    String pid;

    //字符串p表明身份，patient是医生
    public RecordDetails(int recordID, String status) {
        STATUS=status;
        jl1_0=new JLabel("病历编号"); jtf1_0=new JTextField(4);
        jl1_1=new JLabel("挂号记录"); jtf1_1=new JTextField(4);
        jl2_0=new JLabel("科室编号"); jtf2_0=new JTextField(4);
        jl2_1=new JLabel("科室名称"); jtf2_1=new JTextField(4);
        jl2_2=new JLabel("日期"); jtf2_2=new JTextField(12);
        jl3_0=new JLabel("患者编号"); jtf3_0=new JTextField(5);
        jl3_1=new JLabel("患者姓名"); jtf3_1=new JTextField(8);
        jl3_2=new JLabel("患者性别"); jtf3_2=new JTextField(3);
        jl4_0=new JLabel("患者身份证号"); jtf4_0=new JTextField(20);
        jl5=new JLabel("主诉"); jtf5=new JTextField(32);
        jl6=new JLabel("过敏史"); jtf6=new JTextField(32);
        jl7=new JLabel("既往史"); jtf7=new JTextField(32);
        jl8=new JLabel("现病史"); jtf8=new JTextField(32);
        jl9=new JLabel("治疗情况"); jtf9=new JTextField(30);
        jl10=new JLabel("评估诊断"); jtf10=new JTextField(30);

        jl11_0=new JLabel("医生编号"); jtf11_0=new JTextField(5);
        jl11_1=new JLabel("医生姓名"); jtf11_1=new JTextField(8);

        try{
            //1、加载驱动(把下需要的驱动程序加入内存中)
            Class.forName(driver);
            //2、得到连接(指定连接到哪个数据源)
            ct = DriverManager.getConnection(url,user,password);
            //3、创建ps
            ps=ct.prepareStatement("select * from medical_record  where record_id=recordID");
            //预编译语句对象
            rs=ps.executeQuery();//返回查询结果
            //如果有查询结果
            while(rs.next()){
                //初始数据
                jtf1_0.setText(rs.getString(1));
                //让jtf1不能修改
                jtf1_0.setEditable(false);
                jtf1_1.setText(rs.getString(2));
                jtf1_1.setEditable(false);
                jtf2_0.setText(rs.getString(5));
                jtf2_0.setEditable(false);
                jtf2_2.setText(rs.getString(8));
                jtf2_2.setEditable(false);
                jtf3_0.setText(rs.getString(3));
                jtf3_0.setEditable(false);
                jtf5.setText(rs.getString(9));
                jtf5.setEditable(false);
                jtf6.setText(rs.getString(13));
                jtf6.setEditable(false);
                jtf7.setText(rs.getString(12));
                jtf7.setEditable(false);
                jtf8.setText(rs.getString(10));
                jtf8.setEditable(false);
                jtf9.setText(rs.getString(11));
                jtf9.setEditable(false);
                jtf10.setText(rs.getString(12));
                jtf10.setEditable(false);

                //科室名称
                ps1=ct.prepareStatement("select * from department  where department_id=(int)rs.getString(5)");
                //预编译语句对象
                rs1=ps1.executeQuery();//返回查询结果
                while(rs1.next()){
                    jtf2_1.setText(rs1.getString(2));
                    jtf2_1.setEditable(false);
                }

                //医生姓名
                ps2=ct.prepareStatement("select * from department  where department_id=rs.getInt(4)");
                //预编译语句对象
                rs2=ps2.executeQuery();//返回查询结果
                while(rs2.next()){
                    jtf11_1.setText(rs2.getString(4));
                }

                //患者信息
                ps3=ct.prepareStatement("select * from patient  where patient_id=rs.getInt(3)");
                //预编译语句对象
                rs3=ps3.executeQuery();//返回查询结果
                while(rs3.next()){
                    jtf3_1.setText(rs3.getString(2));
                    jtf3_2.setText(rs3.getString(5));
                    jtf4_0.setText(rs3.getString(4));
                    jtf3_1.setEditable(false);
                    jtf3_2.setEditable(false);
                    jtf4_0.setEditable(false);
                }

            }
        }catch(Exception e2){

        }finally{
            //关闭资源（关闭顺序：谁后创建则先关闭)
            try{
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
                if(rs1!=null) rs1.close();
                if(ps1!=null) ps1.close();
                if(rs2!=null) rs2.close();
                if(ps2!=null) ps2.close();
                if(rs3!=null) rs3.close();
                if(ps3!=null) ps3.close();
                if(ct!=null) ct.close();
            }catch(Exception e1){
            }
        }

        if(status.equals("PATIENT")){
            jb1=new JButton("确认");
            jb1.addActionListener(this);
        }
        else{
            jb1=new JButton("修改病历信息");
            jb1.addActionListener(this);
        }

        jp1=new JPanel();
        jp1.add(jl1_0); jp1.add(jtf1_0);
        jp1.add(jl1_1); jp1.add(jtf1_1);

        jp2=new JPanel();
        jp2.add(jl2_0); jp2.add(jtf2_0);
        jp2.add(jl2_1); jp2.add(jtf2_1);
        jp2.add(jl2_2); jp2.add(jtf2_2);

        jp3=new JPanel();
        jp3.add(jl3_0); jp3.add(jtf3_0);
        jp3.add(jl3_1); jp3.add(jtf3_1);
        jp3.add(jl3_2); jp3.add(jtf3_2);

        jp4=new JPanel();
        jp4.add(jl4_0); jp4.add(jtf4_0);

        jp5=new JPanel();
        jp5.add(jl5); jp5.add(jtf5);

        jp6=new JPanel();
        jp6.add(jl6); jp6.add(jtf6);

        jp7=new JPanel();
        jp7.add(jl7); jp7.add(jtf7);

        jp8=new JPanel();
        jp8.add(jl8); jp8.add(jtf8);

        jp9=new JPanel();
        jp9.add(jl9); jp9.add(jtf9);

        jp10=new JPanel();
        jp10.add(jl10); jp10.add(jtf10);

        jp11=new JPanel();
        jp11.add(jl11_0); jp11.add(jtf11_0);
        jp11.add(jl11_1); jp11.add(jtf11_1);

        jp12=new JPanel();
        jp12.add(jb1);

        this.setLayout(new GridLayout(12,1));
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp11);
        this.add(jp5);
        this.add(jp6);
        this.add(jp7);
        this.add(jp8);
        this.add(jp9);
        this.add(jp10);
        this.add(jp12);


        this.setSize(400,600);
        this.setTitle("病历详情");
        this.setLocationRelativeTo(null);// 居中
        this.setVisible(true);

    }

    public static void main(String[] args) {new RecordDetails(1,"PATIENT");}



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==jb1){
            //病人点击确认
            if(STATUS.equals("PATIENT")){
                return ;
            }
            else{
                //医生更新病历
            }
        }
    }

}