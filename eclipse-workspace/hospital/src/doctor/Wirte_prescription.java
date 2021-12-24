package doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//开处方之后将预约表中的状态改为1
public class Wirte_prescription extends JFrame implements ActionListener {
    //定义需要的控件
    //假设最多开四种品类的药
    JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8,jl9,jl10; //药品1编号，药品1数量，药品2编号，药品2数量，药品3编号，药品3数量，药品4编号，药品4数量。
    JButton jb1,jb2;
    JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8,jtf9,jtf10;
    JPanel jp1, jp2, jp3, jp4, jp5,jp6;

    //连接数据库要用的东西
    PreparedStatement ps= null;
    ResultSet rs=null;
    String URL = "jdbc:mysql://localhost/hospital?";
    String user="root";
    String password="123";

    int pres_id;    //处方编号

    public Wirte_prescription(int p_id, int d_id, int r_id) {
        try (Connection ct=DriverManager.getConnection(URL,user,password)) {
            try {
                //3、创建ps
                ps = ct.prepareStatement("insert into prescription (patient_id,doctor_id,record_id) values (?,?,?)");
                ps.setInt(1,p_id);
                ps.setInt(2,d_id);
                ps.setInt(3,r_id);
                ps.executeUpdate(); //更新处方表 获得处方id

                ps = ct.prepareStatement("select prescription_id from prescription where record_id = ?");
                ps.setInt(1,r_id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    pres_id = rs.getInt(1);     //插入成功后查询出新建的处方编号
                }
            }
            catch (Exception e2) {
            }
            finally {
                //关闭资源
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (ct != null) ct.close();
                } catch (Exception e1) {
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jl1 = new JLabel("药品1名称");
        jl2 = new JLabel("药品1数量");
        jl3 = new JLabel("药品2名称");
        jl4 = new JLabel("药品2数量");
        jl5 = new JLabel("药品3名称");
        jl6 = new JLabel("药品3数量");
        jl7 = new JLabel("药品4名称");
        jl8 = new JLabel("药品4数量");
        jl9 = new JLabel("药品5名称");
        jl10 = new JLabel("药品5数量");

        jtf1 = new JTextField("",6);
        jtf2 = new JTextField("",6);
        jtf3 = new JTextField("",6);
        jtf4 = new JTextField("",6);
        jtf5 = new JTextField("",6);
        jtf6 = new JTextField("",6);
        jtf7 = new JTextField("",6);
        jtf8 = new JTextField("",6);
        jtf9 = new JTextField("",6);
        jtf10 = new JTextField("",6);


        jb1 = new JButton("确认");
        jb1.addActionListener(this);
        jb2 = new JButton("取消");
        jb2.addActionListener(this);


        try(Connection ct=DriverManager.getConnection(URL,user,password)){
            //首先根据病历编号查出处方编号
            ps = ct.prepareStatement("select appointment_id from medical_record where record_id=?");
            ps.setInt(1,r_id);
            rs = ps.executeQuery();
            rs.next();
            int a_id = rs.getInt(1);

            //更新预约表
            ps = ct.prepareStatement("update appointment set status = ? where appointment_id = ?");
            ps.setInt(1,1);
            ps.setInt(2,a_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();

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
        jp3.add(jl6);
        jp3.add(jtf6);

        jp4.add(jl7);
        jp4.add(jtf7);
        jp4.add(jl8);
        jp4.add(jtf8);

        jp5.add(jl9);
        jp5.add(jtf9);
        jp5.add(jl10);
        jp5.add(jtf10);

        jp6.add(jb1);
        jp6.add(jb2);

        this.setLayout(new GridLayout(6, 1));
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        this.add(jp6);

        //展现
        this.setSize(500, 300);
        this.setTitle("处方表");
        this.setLocationRelativeTo(null);// 居中
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==jb1) {
            try(Connection ct=DriverManager.getConnection(URL,user,password)) {
                try {
                    add_drug(ct, jtf1,jtf2);
                    add_drug(ct, jtf3, jtf4);
                    add_drug(ct, jtf5, jtf6);
                    add_drug(ct, jtf7, jtf8);
                    add_drug(ct, jtf9, jtf10);
                } catch (Exception e2) {
                } finally {
                    //关闭资源（关闭顺序：谁后创建则先关闭)
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
        }
    }

    private void add_drug(Connection ct, JTextField jtfName, JTextField jtfNum) throws SQLException {
        if (jtfName.getText().trim() != "" && jtfNum.getText() != "" && jtfNum.getText() != "0") {
            String d1 = jtfName.getText();
            String d2 = jtfNum.getText();
            int d_num = Integer.parseInt(d2);

            ps = ct.prepareStatement("select drug_id from drug where drug_name=?");
            ps.setString(1,d1);
            rs = ps.executeQuery();
            int d;
            if(!rs.next()){      //未查询到结果，说明填入的药品名称错误
                JOptionPane.showMessageDialog(this, "存在不正确的药品名称，请检查是否输入有误");
                jtfName.requestFocus();
            }else{
                d = rs.getInt(1);

                //3、创建ps
                ps = ct.prepareStatement("insert into pre_drug_ref (prescription_id,drug_id,drug_num) values (?,?,?)");
                ps.setInt(1,pres_id);
                ps.setInt(2,d);
                ps.setInt(3,d_num);
                ps.executeUpdate();
            }

        }
    }
}
