/*
Choose payment method;
Parameter:pid, pay(number);
Written by zjy;
 */
package patient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class payment_choice extends JFrame implements ActionListener{
    JPanel jp1, jp2, jp3, jp4;
    JLabel jl1, jl2, jl3;
    JButton jb1, jb2, jb3;
    JLabel jl;
    JTextField jtf;
    String pid;
    public payment_choice(String pid_, String pay)
    {
        pid=pid_;

        jl=new JLabel("您需要支付");
        jl.setFont(new Font("宋体",Font.BOLD ,20));
        jtf=new JTextField(10);
        jtf.setText(pay);
        jtf.setFont(new Font("宋体",Font.BOLD ,20));
        jtf.setEditable(false);

        jl1=new JLabel("微信支付");
        jl2=new JLabel("支付宝支付");
        jl3=new JLabel("银联卡支付");

        jb1=new JButton("选择");
        jb1.addActionListener(this);
        jb2=new JButton("选择");
        jb2.addActionListener(this);
        jb3=new JButton("选择");
        jb3.addActionListener(this);

        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();
        jp4=new JPanel();

        jp1.add(jl1);
        jp1.add(jb1);
        jp2.add(jl2);
        jp2.add(jb2);
        jp3.add(jl3);
        jp3.add(jb3);
        jp4.add(jl);
        jp4.add(jtf);

        this.setLayout(new GridLayout(4,1));
        this.add(jp4);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setSize(500,300);
        this.setTitle("缴费");
        this.setLocationRelativeTo(null);// 居中
        this.setVisible(true);
    }

    public static void main(String[] args) {new payment_choice("1", "55");}

    @Override

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jb1){
            //跳转到微信支付
            this.dispose();
            return;
        }
        else if(e.getSource()==jb2){
            //跳转到支付宝支付
            this.dispose();
        }
        else if(e.getSource()==jb3){
            //跳转到银联卡支付
            this.dispose();
        }

    }
}