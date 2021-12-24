package doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class fever_diagnosis extends JFrame implements ActionListener {

    JLabel jl1,jl2,jl3,jl4,jl5;
    JTextField jtf1,jtf2,jtf3,jtf4,jtf5;
    JButton jb1;
    JPanel jp1,jp2,jp3,jp4;

    //连接数据库要用的东西
    PreparedStatement ps= null;
    ResultSet rs=null;
    String URL = "jdbc:mysql://localhost/hospital?";
    String user="root";
    String password="123";

    int p_id,a_id;   //患者编号
    public fever_diagnosis( int app_id){
        a_id = app_id;
        jl1 = new JLabel("患者编号");
        jl2 = new JLabel("患者姓名");
        jl3 = new JLabel("患者体温");
        jl4 = new JLabel("是否去过中高风险地区");
        jl5 = new JLabel("是否确诊新冠");

        jtf1 = new JTextField("", 6);
        jtf2 = new JTextField("", 6);
        jtf3 = new JTextField("", 6);
        jtf4 = new JTextField("", 6);
        jtf5 = new JTextField("", 6);

        jb1 = new JButton("确认");
        jb1.addActionListener(this);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();

        try(Connection ct= DriverManager.getConnection(URL,user,password)){
            try{
                ps = ct.prepareStatement("select t1.patient_id,t3.patient_name,t2.fever_temperature,t2.ever_went_to_high_risk_area " +
                        "from appointment as t1 " +
                        "left join fever_patient as t2 on t1.patient_id = t2.patient_id " +
                        "left join patient as t3 on t1.patient_id = t3.patient_id " +
                        "where t1.appointment_id =?");
                ps.setInt(1,a_id);
                rs = ps.executeQuery();
                rs.next();
                jtf1.setText(String.valueOf(rs.getInt(1)));
                jtf1.setEditable(false);
                p_id = rs.getInt(1);
                jtf2.setText(rs.getString(2));
                jtf2.setEditable(false);
                jtf3.setText(String.valueOf(rs.getDouble(3)));
                jtf3.setEditable(false);
                if(rs.getInt(4) == 1){
                    jtf4.setText("是");
                }else{
                    jtf4.setText("否");
                }
                jtf4.setEditable(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                //关闭资源（关闭顺序：谁后创建则先关闭)
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (ct != null) ct.close();
                } catch (Exception e1) {
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        jp1.add(jl1);
        jp1.add(jtf1);
        jp1.add(jl2);
        jp1.add(jtf2);

        jp2.add(jl3);
        jp2.add(jtf3);
        jp2.add(jl4);
        jp2.add(jtf4);

        jp3.add(jl5);
        jp3.add(jtf5);

        jp4.add(jb1);

        this.setLayout(new GridLayout(4, 1));
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);

        //展现
        this.setSize(500, 300);
        this.setTitle("发热患者诊断");
        this.setLocationRelativeTo(null);// 居中
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb1){
            String s = jtf5.getText();
            try(Connection ct= DriverManager.getConnection(URL,user,password)){
                if(Objects.equals(s, "否")) {
                    try {       //更新是否确诊新冠字段
                        ps = ct.prepareStatement("update fever_patient set is_diagnosed = ? where patient_id = ?");
                        ps.setInt(1, 0);
                        ps.setInt(2, p_id);
                        ps.executeUpdate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                try{    //更新预约表，处理状态变为已处理
                    ps = ct.prepareStatement("update appointment set status = ? where appointment_id = ?");
                    ps.setInt(1,1);
                    ps.setInt(2,a_id);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }finally{
                    //关闭资源
                    try{
                        if(rs!=null) rs.close();
                        if(ps!=null) ps.close();
                        if(ct!=null) ct.close();
                    }catch(Exception e1){
                    }
                }
            }catch (SQLException es) {
                es.printStackTrace();
            }

        }
        this.dispose();
    }
}
