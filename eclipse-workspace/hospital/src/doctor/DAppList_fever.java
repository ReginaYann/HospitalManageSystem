package doctor;

import login.Login_doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

//只看还未处理的（未完成诊断，即诊病，写病历、开处方）预约
public class DAppList_fever extends JFrame implements ActionListener {
    JTable jt;
    JScrollPane jsp;
    JPanel jp1,jp2;
    JLabel jl1;
    JTextField jt1;
    JButton jb1;


    //连接数据库要用的东西
    PreparedStatement ps= null;
    ResultSet rs=null;
    String URL = "jdbc:mysql://localhost/hospital?";
    String user="root";
    String password="123";

    String[] columnNames = new String[]{"预约编号", "患者编号", "患者姓名", "预约日期"};
    Object[][] rowData;

    public DAppList_fever(int Doctor_id){
        try(Connection ct=DriverManager.getConnection(URL,user,password)) {
            try {
                ps = ct.prepareStatement("select count(1) from appointment where doctor_id =? and status = ?");
                ps.setInt(1, Doctor_id);
                ps.setInt(2, 0);
                rs = ps.executeQuery();
                rs.next();
                int cnt = rs.getInt(1);

                rowData = new Object[cnt][4];

                DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
                Calendar cal = Calendar.getInstance();
                //System.out.println(df.format(cal.getTime()));
                //3、创建ps
                ps = ct.prepareStatement("select t1.appointment_id,t1.patient_id,t2.patient_name,t1.date " +
                        "from appointment as t1 " +
                        "left join patient as t2 on t1.patient_id = t2.patient_id " +
                        "where t1.doctor_id = ? and t1.status = ? and t1.date=?");
                ps.setInt(1, Doctor_id);
                ps.setInt(2, 0);
                ps.setString(3, df.format(cal.getTime()));
                rs = ps.executeQuery();
                int i = 0;
                while (rs.next()) {
                    rowData[i][0] = rs.getInt(1);
                    rowData[i][1] = rs.getInt(2);
                    rowData[i][2] = rs.getString(3);
                    rowData[i][3] = rs.getString(4);
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //关闭资源（关闭顺序：谁后创建则先关闭)
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

        DefaultTableModel defaultModel = new DefaultTableModel(rowData, columnNames);
        jt = new JTable(defaultModel);
        jt.setPreferredScrollableViewportSize(new Dimension(150,80));  //JTable的高度和宽度设定
        jt.setFillsViewportHeight(true);

        jsp = new JScrollPane();
        jsp.setViewportView(jt);

        jl1 = new JLabel("请输入要处理的预约编号:");
        jt1 = new JTextField("",10);
        jb1 = new JButton("开始诊断");  //判断是否确诊新冠
        jb1.addActionListener(this);
        jp1 = new JPanel();
        jp2 = new JPanel();

        jp1.add(jl1);
        jp1.add(jt1);
        jp2.add(jb1);


        this.setLayout(new BorderLayout());
        this.add(jsp,BorderLayout.CENTER);
        this.add(jp1,BorderLayout.NORTH);
        this.add(jp2,BorderLayout.SOUTH);
        this.setTitle("发热预约列表");
        this.setSize(500,300);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //默认关闭
        //设置可见
        this.setVisible(true);
        this.setLocationRelativeTo(null);// 居中
    }

    public static void main(String[] args) {
        new DAppointmentList(1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==jb1){
            String d = jt1.getText();
            int a_id = Integer.parseInt(d);              //将医生输入的预约编号转为int类型

            try(Connection ct=DriverManager.getConnection(URL,user,password)) {
                try {
                    //3、创建ps
                    ps = ct.prepareStatement("select count(1) from appointment where appointment_id=?");
                    ps.setInt(1,a_id);
                    rs = ps.executeQuery();
                    rs.next();
                    if (rs.getInt(1) != 1) {
                        JOptionPane.showMessageDialog(this, "该预约编号不存在!请输入正确的预约编号!");
                        jt1.requestFocus();
                    } else {
                        new fever_diagnosis(a_id);    //跳转至写病历页面
                        this.dispose();
                    }
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
            }
            catch (SQLException et) {
                et.printStackTrace();
            }
        }
    }
}