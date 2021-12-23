/*
Department list generation;
Parameter: sql for selection(can be empty);
Written by zjy;
*/
package patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

//一个表的模型
public class DeptTable extends AbstractTableModel{
    //rowData用来存放行数据，columnNames存放列名
    Vector rowData,columnNames;
    //连接数据库要用的东西
    Connection ct=null;
    PreparedStatement ps_dept= null;
    ResultSet rs_dept=null;
    PreparedStatement ps_doctor= null;
    ResultSet rs_doctor=null;
    String driver="com.mysql.cj.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/hospital";
    String user="root";
    String password="A20001112a";


    public void init(String deptname){
        columnNames = new Vector();
        //设置列名
        columnNames.add("科室ID");
        columnNames.add("科室名称");
        columnNames.add("科室主任");
        columnNames.add("医生数量");

        rowData = new Vector();//可存放多行

        try{
            //1、加载驱动(把下需要的驱动程序加入内存中)
            Class.forName(driver);
            //2、得到连接(指定连接到哪个数据源)
            ct = DriverManager.getConnection(url,user,password);
            //3、创建ps
            if(deptname.equals("")){
                ps_dept=ct.prepareStatement("select * from department");
            }
            else{
                ps_dept=ct.prepareStatement("select * from department where department_name=?");
                ps_dept.setString(1,deptname);
            }

            //4、执行(如果是增加，删除，修改使用executeUpdate();查询executeQuery)
            rs_dept = ps_dept.executeQuery();

            while(rs_dept.next()){
                Vector col = new Vector();
                col.add(rs_dept.getString(1));
                col.add(rs_dept.getString(2));

                //找到科长
                ps_doctor=ct.prepareStatement("select * from doctor  where doctor_id=?");
                int dd=rs_dept.getInt(3);
                ps_doctor.setInt(1,dd);
                //预编译语句对象
                rs_doctor=ps_doctor.executeQuery();//返回查询结果
                //如果有查询结果
                while(rs_doctor.next()){
                    col.add(rs_doctor.getString(4));
                }

                //对医生数量计数
                ps_doctor=ct.prepareStatement("select * from doctor  where department_id=?");
                //预编译语句对象
                int dede=rs_dept.getInt(1);
                ps_doctor.setInt(1,dede);
                rs_doctor=ps_doctor.executeQuery();//返回查询结果
                //如果有查询结果
                int cnt = 0;
                while(rs_doctor.next()){
                    cnt=cnt+1;
                }
                col.add(Integer.toString(cnt));
                //加入到rowData
                rowData.add(col);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //关闭资源（关闭顺序：谁后创建则先关闭)
            try{
                if(rs_dept!=null) rs_dept.close();
                if(ps_dept!=null) ps_dept.close();
                if(rs_doctor!=null) rs_doctor.close();
                if(ps_doctor!=null) ps_doctor.close();
                if(ct!=null) ct.close();
            }catch(Exception e){

            }
        }
    }

    //通过传递的sql语句来获得数据模型
    public DeptTable(String deptname_){
        this.init(deptname_);
    }
    //做一个构造函数，用于初始化数据模型
    public DeptTable(){
        this.init("");
    }
    @Override
    //得到共有多少行
    public int getRowCount() {
        // TODO Auto-generated method stub
        return this.rowData.size();
    }

    @Override
    //得到共有多少列
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return this.columnNames.size();
    }

    @Override
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return (String) this.columnNames.get(column);
    }
    @Override
    //得到某行某列的数据
    public Object getValueAt(int row, int column) {
        // TODO Auto-generated method stub
        return ((Vector)this.rowData.get(row)).get(column);
    }

}
