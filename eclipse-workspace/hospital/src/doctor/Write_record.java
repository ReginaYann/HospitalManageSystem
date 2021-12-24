package doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//医生可选择一些为某个患者处理预约的时候可选择填写病历，同时提交病历时必须填写处方！！！填写病历处方之后才算完成该次诊断，预约status变为1
public class Write_record extends JFrame implements ActionListener {
    //JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9, jl10, jl11;
    //预约编号，患者姓名，医生姓名，就诊科室，就诊日期，主诉，现病史，现病治疗情况，既往病史，过敏史，评估诊断
    JLabel jl1, jl6, jl7, jl8, jl9, jl10, jl11;

    JButton jb1, jb2;    //确定提交或者取消

    //JTextField jt1, jt2, jt3, jt4, jt5, jt6, jt7, jt8, jt9, jt10, jt11;
    JTextField jt1, jt6, jt7, jt8, jt9, jt10, jt11;

    //JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9, jp10;
    JPanel jp1, jp4, jp5, jp6, jp7, jp8, jp9, jp10;

    //连接数据库要用的东西
    PreparedStatement ps= null;
    ResultSet rs=null;
    String URL = "jdbc:mysql://localhost/hospital?";
    String user="root";
    String password="123";

    int a_id,p_id, d_id, de_id, r_id;   //分别对应预约/病人/医生/科室/病历编号
    public Write_record(int appointment_id) {
        a_id = appointment_id;
        jl1 = new JLabel("预约编号");
        jl6 = new JLabel("主诉");
        jl7 = new JLabel("现病史");
        jl8 = new JLabel("现病治疗情况");
        jl9 = new JLabel("既往病史");
        jl10 = new JLabel("过敏史");
        jl11 = new JLabel("评估诊断");

        jb1 = new JButton("提交");
        jb1.addActionListener(this);
        jb2 = new JButton("取消");
        jb2.addActionListener(this);

        jt1 = new JTextField("",10);
        jt6 = new JTextField("",25);
        jt7 = new JTextField("",25);
        jt8 = new JTextField("",25);
        jt9 = new JTextField("",25);
        jt10 = new JTextField("",25);
        jt11 = new JTextField("",25);

        jt1.setText(String.valueOf(appointment_id));
        jt1.setEditable(false);

        jp1 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
        jp7 = new JPanel();
        jp8 = new JPanel();
        jp9 = new JPanel();
        jp10 = new JPanel();

        jp1.add(jl1);
        jp1.add(jt1);

        jp4.add(jl6);
        jp4.add(jt6);

        jp5.add(jl7);
        jp5.add(jt7);

        jp6.add(jl8);
        jp6.add(jt8);

        jp7.add(jl9);
        jp7.add(jt9);

        jp8.add(jl10);
        jp8.add(jt10);

        jp9.add(jl11);
        jp9.add(jt11);

        jp10.add(jb1);
        jp10.add(jb2);

        this.setLayout(new GridLayout(8, 1));
        this.add(jp1);
        this.add(jp4);
        this.add(jp5);
        this.add(jp6);
        this.add(jp7);
        this.add(jp8);
        this.add(jp9);
        this.add(jp10);

        //展现
        this.setSize(700, 400);
        this.setTitle("病历填写");
        this.setLocationRelativeTo(null);// 居中
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Write_record(3);
    }

    @Override
    public void actionPerformed (ActionEvent e){
            // TODO Auto-generated method stub
            if (e.getSource() == jb1) {       //提交病历
                try (Connection ct=DriverManager.getConnection(URL,user,password)) {
                    try {
                        ps = ct.prepareStatement("select patient_id,doctor_id,department_id from appointment where appointment_id=?");
                        ps.setInt(1, a_id);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            p_id = rs.getInt(1);
                            d_id = rs.getInt(2);
                            de_id = rs.getInt(3);
                        }
                        Date dnow = new Date(); //获取当前时间
                        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //格式化
                        ps = ct.prepareStatement("insert into medical_record (appointment_id,patient_id,doctor_id,department_id,date) values (?,?,?,?,?)");
                        ps.setInt(1, a_id);
                        ps.setInt(2, p_id);
                        ps.setInt(3, d_id);
                        ps.setInt(4, de_id);
                        ps.setString(5, ft.format(dnow));
                        ps.executeUpdate();

                        ps = ct.prepareStatement("select record_id from medical_record where appointment_id=?");
                        ps.setInt(1, a_id);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            r_id = rs.getInt(1);    //插入成功后查询出新建的病历编号，放入处方表中形成一一对应
                        }

                        ps = ct.prepareStatement("update medical_record set complaint=?,current_medical_history=?,treatment=?,past_medical_history=?,allergy=?,diagnosis=? where record_id =?");
                        ps.setString(1, jt6.getText());
                        ps.setString(2, jt7.getText());
                        ps.setString(3, jt8.getText());
                        ps.setString(4, jt9.getText());
                        ps.setString(5, jt10.getText());
                        ps.setString(6, jt11.getText());
                        ps.setInt(7, r_id);
                        ps.executeUpdate(); //更新病历表

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        //关闭资源
                        try {
                            if (rs != null) rs.close();
                            if (ps != null) ps.close();
                            if (ct != null) ct.close();
                        } catch (Exception e1) {
                        }
                    }
                }catch (SQLException es) {
                    es.printStackTrace();
                }
                new Wirte_prescription(p_id, d_id, r_id);    //立马跳到编辑处方界面
                this.dispose();
            } else if (e.getSource() == jb2) {
                this.dispose();
            }
        }
}