/*
Appointment for fever patient;
Parameter:pid;
Written by zjy;
 */
package patient;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.sql.Date;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.JComboBox;


import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Date;
import java.util.GregorianCalendar;
import patient.P_appoint;

//是否去过高风险地区，体温，选择发热医生，发热病人不选择日期，日期就是当日日期
public class P_simple extends JFrame implements ActionListener,ItemListener
{
    String pid;
    JPanel jp1, jp2, jp3,jp4,jp5;
    JLabel jl1, jl2, jl3,jl4;
    JButton jb1;
    JComboBox box1,box2;
    JTextField jtf;
    JComboBox box3,box4; // 选择发热科医生

    //连接数据库要用的东西
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


    public P_simple(String pid_) {
        pid=pid_;
        jl1=new JLabel("14天内是否去过高风险地区：");
        box1=new JComboBox();
        box1.addItem("是");
        box1.addItem("否");
        box1.setSelectedIndex(-1);

        jp1=new JPanel();
        jp1.add(jl1);
        jp1.add(box1);

        jl2=new JLabel("体温：");
        jtf=new JTextField(4);
        jp2=new JPanel();
        jp2.add(jl2);
        jp2.add(jtf);

        jl3=new JLabel("选择科室：");
        box2=new JComboBox();
        box2.addItem("发热科");
        box2.setSelectedIndex(0);
        jp3=new JPanel();
        jp3.add(jl3);
        jp3.add(box2);


        //box3，box4分别是发热科医生的编号和姓名
        jl4=new JLabel("选择医生：");
        box3=new JComboBox();
        box4=new JComboBox();
        try {
            //1、加载驱动(把下需要的驱动程序加入内存中)
            Class.forName(driver);
            //2、得到连接(指定连接到哪个数据源)
            ct = DriverManager.getConnection(url, user, password);
            //3、创建ps
            ps1 = ct.prepareStatement("select * from doctor where department_id=1");
            //预编译语句对象
            rs1 = ps1.executeQuery();//返回查询结果
            //如果有查询结果
            while (rs1.next()) {
                box3.addItem(rs1.getString(1));
                box4.addItem(rs1.getString(4));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //关闭资源（关闭顺序：谁后创建则先关闭)
            try{
                if(rs1!=null) rs.close();
                if(ps1!=null) ps.close();
                if(ct!=null) ct.close();
            }catch(Exception e){}
        }
        box3.setSelectedIndex(-1);
        box4.setSelectedIndex(-1);
        box3.addItemListener(this);
        box4.addItemListener(this);

        jp4=new JPanel();
        jp4.add(jl4);
        jp4.add(box3);
        jp4.add(box4);

        jp5=new JPanel();
        jb1=new JButton("确认");
        jb1.addActionListener(this);
        jp5.add(jb1);

        this.setLayout(new GridLayout(6,1));
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        this.setSize(400,600);
        this.setLocationRelativeTo(null);// 居中
        this.setTitle("发热预约");
        this.setVisible(true);

    }

    public static void main(String[] args) {new P_simple("1");}

    @Override

    public void actionPerformed(ActionEvent e) {
        //确认预约，插入预约表
        if(e.getSource()==jb1) {
            if (box1.getSelectedIndex() == -1 || box2.getSelectedIndex() == -1 || box3.getSelectedIndex() == -1 || box4.getSelectedIndex() == -1||jtf.getText().equals("")) {
                JOptionPane.showMessageDialog((Component) null, "预约信息不完整", "提示信息", JOptionPane.ERROR_MESSAGE);
                return;
            } else {  //插入发热病人表，不插入预约表

                int patientID=Integer.parseInt(pid);
                String tem=jtf.getText();
                int ever=0;
                if(box1.getSelectedIndex()==0)//去过
                {
                    ever=1;
                }
                String dx=box3.getSelectedItem().toString();
                int doctorID=Integer.parseInt(dx);


                try {
                    //1、加载驱动(把下需要的驱动程序加入内存中)
                    Class.forName(driver);
                    //2、得到连接(指定连接到哪个数据源)
                    ct = DriverManager.getConnection(url, user, password);
                    //3、创建ps
                    ps = ct.prepareStatement("insert into fever_patient(patient_id, fever_temperature,ever_went_to_high_risk_area,is_diagnosed,doctor_id,department_id,referral_doctor_id) values (?,?,?,?,?,?,?)");
                    //预编译语句对象
                    ps.setInt(1,patientID);
                    ps.setString(2,tem);
                    ps.setInt(3,ever);
                    ps.setInt(4,0);
                    ps.setInt(5,doctorID);
                    ps.setInt(6,1);  //需要医生填写
                    ps.setInt(7,6);  //需要医生填写
                    ps.executeUpdate();

                } catch (Exception x) {
                    x.printStackTrace();
                } finally {
                    //关闭资源（关闭顺序：谁后创建则先关闭)
                    try {
                        if (ps != null) ps.close();
                        if (ct != null) ct.close();
                    } catch (Exception d) {
                    }
                }
                JOptionPane.showMessageDialog((Component) null, "预约成功", "提示信息", JOptionPane.ERROR_MESSAGE);
                this.dispose();
            }

        }
    }





    public void itemStateChanged(ItemEvent ie)
    {
        if(ie.getSource()==box3){
            box4.setSelectedIndex(box3.getSelectedIndex());
        }
        else if(ie.getSource()==box4) {
            box3.setSelectedIndex(box4.getSelectedIndex());
        }
    }


}