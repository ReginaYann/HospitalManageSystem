package doctor;

import login.Login_doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


//D_PatientList 展示医生历史病人
public class D_PatientList extends JFrame implements ActionListener{

    //连接数据库要用的东西
    PreparedStatement ps= null;
    ResultSet rs=null;
    String URL = "jdbc:mysql://localhost/hospital?";
    String user="root";
    String password="123";

    //查看历史病人，即已经处理了的预约信息，展示预约列表，包括信息：预约编号，患者姓名，科室名称，预约日期，病历编号
    //可通过输入具体的病历编号查看病历详情 病历详情界面又可以去查看处方详情
    JTable jt ;
    JScrollPane jsp ;
    JPanel jp1,jp2;
    JLabel jl1;
    JTextField jt1;
    JButton jb1;

    //存放 数据 以及 列名
    String[] columnNames = new String[]{"预约编号", "患者姓名", "科室名称", "预约日期", "病历编号"};
    Object[][] rowData;
    public D_PatientList(int Doctor_id) {
        try(Connection ct=DriverManager.getConnection(URL,user,password)) {
            try {
                //3、创建ps
                ps = ct.prepareStatement("select count(1) from appointment where doctor_id =? and status = ?");
                ps.setInt(1, Doctor_id);
                ps.setInt(2, 1);
                rs = ps.executeQuery();
                rs.next();
                int cnt = rs.getInt(1);

                rowData = new Object[cnt][5];

                ps = ct.prepareStatement("select t1.appointment_id,t2.patient_name,t3.department_name,t1.date,t4.record_id " +
                        "from appointment as t1 " +
                        "left join patient as t2 on t1.patient_id = t2.patient_id " +
                        "left join department as t3 on t1.department_id = t3.department_id " +
                        "left join medical_record as t4 on t1.appointment_id = t4.appointment_id " +
                        "where t1.doctor_id = ? and t1.status = ?");
                ps.setInt(1, Doctor_id);
                ps.setInt(2, 1);
                rs = ps.executeQuery();
                int i = 0;
                while (rs.next()) {
                    rowData[i][0] = rs.getInt(1);
                    rowData[i][1] = rs.getString(2);
                    rowData[i][2] = rs.getString(3);
                    rowData[i][3] = rs.getString(4);
                    rowData[i][4] = rs.getInt(5);
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

        jl1 = new JLabel("请输入要查看的病历编号:");
        jt1 = new JTextField("",10);
        jb1 = new JButton("查看病历详情");
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
        this.setTitle("历史病人");
        this.setSize(500,300);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //默认关闭
        //设置可见
        this.setVisible(true);
        this.setLocationRelativeTo(null);// 居中
    }

    public static void main(String[] args) {
        new D_PatientList(1);
    }

        @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource()==jb1){
                String d =jt1.getText();
                int re_id = Integer.parseInt(d);              //将医生输入的病历编号转为int类型

                try(Connection ct=DriverManager.getConnection(URL,user,password)) {
                    try {
                        //3、创建ps
                        ps = ct.prepareStatement("select count(1) from medical_record where record_id=?");
                        ps.setInt(1,re_id);
                        rs = ps.executeQuery();
                        rs.next();
                        if (rs.getInt(1) != 1) {
                            JOptionPane.showMessageDialog(this, "该病历编号不存在!请输入正确的病历编号!");
                            jt1.requestFocus();
                        } else {
                            //new record_info(re_id);    //跳转至病历详情
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
                }catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
    }
}
