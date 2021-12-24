package doctor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import login.Login_doctor;


public class Doctor_inf extends JFrame implements ActionListener {

    //定义需要的控件
    JLabel jlD_id,jlDe_id,jlD_name,jlD_birth,jlD_identity,jlD_gender,jlD_tel,jlD_mail,jlD_uName,jlD_pwd,jlD_school,jlD_degree,jlD_title,jlD_domain;
    JButton jb1,jb2;
    JTextField jtD_id,jtDe_id,jtD_name,jtD_birth,jtD_identity,jtD_gender,jtD_tel,jtD_mail,jtD_uName,jtD_pwd,jtD_school,jtD_degree,jtD_title,jtD_domain;
    JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8;

    //连接数据库要用的东西
    PreparedStatement ps= null;
    ResultSet rs=null;
    String URL = "jdbc:mysql://localhost/hospital?";
    String user="root";
    String password="123";

    int d_id;
    public Doctor_inf(int Doctor_id ) throws SQLException {
        d_id = Doctor_id;
        jlD_id = new JLabel("医生id");
        jlDe_id = new JLabel("科室id");
        jlD_name = new JLabel("医生姓名");
        jlD_birth = new JLabel("出生日期");
        jlD_identity = new JLabel("身份证号");
        jlD_gender = new JLabel("性别");
        jlD_tel = new JLabel("电话号码");
        jlD_mail = new JLabel("邮箱");
        jlD_uName= new JLabel("用户昵称");
        jlD_pwd= new JLabel("用户密码");
        jlD_school = new JLabel("毕业院校");
        jlD_degree= new JLabel("学位");
        jlD_title= new JLabel("技术职称");
        jlD_domain = new JLabel("专业特长");

        jb1=new JButton("修改");
        jb1.addActionListener(this);
        jb2=new JButton("取消");
        jb2.addActionListener(this);

        jtD_id = new JTextField(10);
        jtDe_id = new JTextField(10);
        jtD_name = new JTextField(10);
        jtD_birth = new JTextField(10);
        jtD_identity = new JTextField(10);
        jtD_gender = new JTextField(10);
        jtD_tel = new JTextField(10);
        jtD_mail = new JTextField(10);
        jtD_uName = new JTextField(10);
        jtD_pwd = new JTextField(10);
        jtD_school = new JTextField(10);
        jtD_degree = new JTextField(10);
        jtD_title = new JTextField(10);
        jtD_domain = new JTextField(10);

        try (Connection ct=DriverManager.getConnection(URL,user,password)){
            //Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                String sql = "select * from doctor where doctor_id= ? ";
                ps = ct.prepareStatement(sql);
                ps.setInt(1, d_id);
                rs = ps.executeQuery();
                //System.out.println(rs.getInt(1));
                rs.next();
                // while(rs.next()) {
                //展示页面不允许更改任何信息
                jtD_id.setText(String.valueOf(rs.getInt(1)));
                jtD_id.setEditable(false);
                jtDe_id.setText(String.valueOf(rs.getInt(2)));
                jtDe_id.setEditable(false);
                jtD_name.setText(rs.getString(4));
                jtD_name.setEditable(false);
                jtD_birth.setText(rs.getString(5));
                jtD_birth.setEditable(false);
                jtD_identity.setText(rs.getString(6));
                jtD_identity.setEditable(false);
                jtD_gender.setText(rs.getString(7));
                jtD_gender.setEditable(false);
                jtD_tel.setText(rs.getString(8));
                jtD_tel.setEditable(false);
                jtD_mail.setText(rs.getString(9));
                jtD_mail.setEditable(false);
                jtD_uName.setText(rs.getString(10));
                jtD_uName.setEditable(false);
                jtD_pwd.setText(rs.getString(11));
                jtD_pwd.setEditable(false);
                jtD_school.setText(rs.getString(12));
                jtD_school.setEditable(false);
                jtD_degree.setText(rs.getString(13));
                jtD_degree.setEditable(false);
                jtD_title.setText(rs.getString(14));
                jtD_title.setEditable(false);
                jtD_domain.setText(rs.getString(15));
                jtD_domain.setEditable(false);
                //   }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            finally{
                //关闭资源
                try{
                    if(rs!=null) rs.close();
                    if(ps!=null) ps.close();
                    if(ct!=null) ct.close();
                }catch(Exception e1){
                }
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
        jp7 = new JPanel();
        jp8 = new JPanel();


        jp1.add(jlD_id);
        jp1.add(jtD_id);
        jp1.add(jlDe_id);
        jp1.add(jtDe_id);

        jp2.add(jlD_name);
        jp2.add(jtD_name);
        jp2.add(jlD_birth);
        jp2.add(jtD_birth);

        jp3.add(jlD_identity);
        jp3.add(jtD_identity);
        jp3.add(jlD_gender);
        jp3.add(jtD_gender);

        jp4.add(jlD_tel);
        jp4.add(jtD_tel);
        jp4.add(jlD_mail);
        jp4.add(jtD_mail);

        jp5.add(jlD_uName);
        jp5.add(jtD_uName);
        jp5.add(jlD_pwd);
        jp5.add(jtD_pwd);

        jp6.add(jlD_school);
        jp6.add(jtD_school);
        jp6.add(jlD_degree);
        jp6.add(jtD_degree);

        jp7.add(jlD_title);
        jp7.add(jtD_title);
        jp7.add(jlD_domain);
        jp7.add(jtD_domain);

        jp8.add(jb1);
        jp8.add(jb2);

        this.setLayout(new GridLayout(8,1));
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        this.add(jp6);
        this.add(jp7);
        this.add(jp8);


        //展现
        this.setSize(500,300);
        this.setTitle("我的信息");
        this.setLocationRelativeTo(null);// 居中
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
   public static void main(String[] args) throws SQLException {
       new Doctor_inf(1);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==jb1){
            new Doctor_update(d_id);
            this.dispose();
        }else if(e.getSource()==jb2){
            this.dispose();
        }

    }


}
