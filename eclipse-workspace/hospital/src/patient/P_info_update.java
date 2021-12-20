/*
Patient information update;
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

import javax.swing.*;

import login.Login_patient;

public class P_info_update extends JDialog implements ActionListener{
    //定义需要的控件
    JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10,jl11;
    JButton jb1,jb2;
    JButton jb3; // 修改密码按钮
    JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8;
    JPasswordField jtf9, jtf10, jtf11;
    JPanel jp1,jp2,jp3,jp4,jp5,jp6;
    boolean password_modify = false;
    //连接数据库要用的东西
    Connection ct=null;
    PreparedStatement ps= null;
    ResultSet rs=null;
    String driver="com.mysql.cj.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/hospital";
    String user="root";
    String password="A20001112a";
    int pid;

    //owner是它的父窗口，title窗口名，model指定是模式窗口还是非模式窗口
    //模式非模式：窗口点开后能不能再点别的窗口
    public P_info_update(P_info P_info_,String title,boolean model,String pid_){
        super(P_info_,title,model);//调用父类构造方法，达到模式对话框效果
        pid = Integer.parseInt(pid_);
        jl1=new JLabel("患者ID");
        jl2=new JLabel("姓名");
        jl3=new JLabel("性别");
        jl4=new JLabel("出生日期");
        jl5=new JLabel("身份证号");
        jl6=new JLabel("电话");
        jl7=new JLabel("电子邮件");
        jl8=new JLabel("登录名");
        jl9=new JLabel("密码");
        jl10 = new JLabel("新密码");
        jl11 = new JLabel("确认新密码");

        jtf1=new JTextField(10);
        jtf2=new JTextField(10);
        jtf3=new JTextField(10);
        jtf4=new JTextField(10);
        jtf5=new JTextField(16);
        jtf6=new JTextField(10);
        jtf7=new JTextField(16);
        jtf8=new JTextField(10);
        jtf9=new JPasswordField(10);
        jtf10=new JPasswordField(10);
        jtf11=new JPasswordField(10);



        try{
            //1、加载驱动(把下需要的驱动程序加入内存中)
            Class.forName(driver);
            //2、得到连接(指定连接到哪个数据源)
            ct = DriverManager.getConnection(url,user,password);
            //3、创建ps
            ps=ct.prepareStatement("select * from patient /*where patient_id=pid*/");
            //预编译语句对象
            rs=ps.executeQuery();//返回查询结果
            //如果有查询结果
            while(rs.next()){
                //初始数据
                jtf1.setText(rs.getString(1));
                //让jtf1不能修改
                jtf1.setEditable(false);
                jtf2.setText(rs.getString(2));
                jtf2.setEditable(false);
                jtf3.setText(rs.getString(5));
                jtf3.setEditable(false);
                jtf4.setText(rs.getString(3));
                jtf4.setEditable(false);
                jtf5.setText(rs.getString(4));
                jtf5.setEditable(false);
                jtf6.setText(rs.getString(6));
                //jtf6.setEditable(false);
                jtf7.setText(rs.getString(7));
                //jtf7.setEditable(false);
                jtf8.setText(rs.getString(8));
                //jtf8.setEditable(false);
                jtf9.setText(rs.getString(9));
                jtf9.setEditable(false);
            }
        }catch(Exception e2){

        }finally{
            //关闭资源（关闭顺序：谁后创建则先关闭)
            try{
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
                if(ct!=null) ct.close();
            }catch(Exception e1){
            }
        }

        jb1=new JButton("确认修改");
        jb1.addActionListener(this);
        jb2=new JButton("取消");
        jb2.addActionListener(this);
        jb3=new JButton("修改密码");
        jb3.addActionListener(this);

        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();
        jp4=new JPanel();
        jp5=new JPanel();
        jp6=new JPanel();

        jp1.add(jl1);
        jp1.add(jtf1);
        jp1.add(jl2);
        jp1.add(jtf2);
        jp1.add(jl3);
        jp1.add(jtf3);

        jp2.add(jl4);
        jp2.add(jtf4);
        jp2.add(jl5);
        jp2.add(jtf5);

        jp3.add(jl6);
        jp3.add(jtf6);
        jp3.add(jl7);
        jp3.add(jtf7);

        jp4.add(jl8);
        jp4.add(jtf8);
        jp4.add(jl9);
        jp4.add(jtf9);
        jp4.add(jb3);

        jp5.add(jb1);
        jp5.add(jb2);


        this.setLayout(new GridLayout(5,1));
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);

        //展现
        this.setSize(600,400);
        this.setLocationRelativeTo(null);// 居中
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==jb3) //可添加条件，当pid是病人id而不是管理员id时，才可修改密码，或直接在113行判断，隐藏修改密码按钮
        {
            password_modify = true;
            jp6.add(jl10);
            jp6.add(jtf10);
            jp6.add(jl11);
            jp6.add(jtf11);
            this.setLayout(new GridLayout(6,1));
            this.add(jp1);
            this.add(jp2);
            this.add(jp3);
            this.add(jp4);
            this.add(jp6);
            this.add(jp5);
            this.setSize(550,300);
            this.setLocationRelativeTo(null);// 居中
            this.setVisible(true);
        }
        if(e.getSource()==jb1)//确认对信息进行修改之后进行的操作
        {
            if(jtf6.getText().length()==0){
                JOptionPane.showMessageDialog((Component)null,"电话不能为空","提示信息",JOptionPane.ERROR_MESSAGE);
                jl2.requestFocus();
            }
            else if(jtf8.getText().length()==0){
                JOptionPane.showMessageDialog((Component)null,"登录名不能为空","提示信息",JOptionPane.ERROR_MESSAGE);
                jl3.requestFocus();
            }

            if(password_modify){
                String s1=jtf10.getText();
                String s2=jtf11.getText();
                if(s1.length()==0||s2.length()==0){
                    JOptionPane.showMessageDialog((Component)null,"请输入您的密码","提示信息",JOptionPane.ERROR_MESSAGE);
                    jl10.requestFocus();
                    jl11.requestFocus();}
                else if(s1 != s2)
                    JOptionPane.showMessageDialog((Component)null,"请重新确认密码","提示信息",JOptionPane.ERROR_MESSAGE);
                    jl10.requestFocus();
                    jl11.requestFocus();
                    return;
            }

            //做一个SQL
            String sql="update patient set patient_tel=?,patient_mail=?,patient_user_name=?,patient_password=? /*where patient_id=pid*/";
            try{
                //1、加载驱动(把下需要的驱动程序加入内存中)
                Class.forName(driver);
                //2、得到连接(指定连接到哪个数据源)
                ct = DriverManager.getConnection(url,user,password);
                //3、创建ps
                ps=ct.prepareStatement(sql);
                //预编译语句对象


                ps.setString(1, jtf6.getText());
                ps.setString(2, jtf7.getText());
                ps.setString(3, jtf8.getText());
                if(!password_modify){
                    ps.setString(4, jtf9.getText());}
                else if(password_modify){
                    ps.setString(4, jtf11.getText());
                }



                //4、执行(如果是增加，删除，修改使用executeUpdate();查询executeQuery)
                ps.executeUpdate();
            }catch(Exception e2){

            }finally{
                //关闭资源（关闭顺序：谁后创建则先关闭)
                try{
                    if(rs!=null) rs.close();
                    if(ps!=null) ps.close();
                    if(ct!=null) ct.close();
                }catch(Exception e1){
                }
            }
            new P_info(Integer.toString(pid));
            this.dispose();
        }else if(e.getSource()==jb2){
            this.dispose();
        }
    }
}
