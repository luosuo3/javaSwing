package Student2;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

class Xiugai extends JDialog implements ActionListener
{
    JLabel bq1,bq2,bq3,bq4,bq5,bq6;
    JTextField wbk1,wbk2,wbk3,wbk4,wbk5,wbk6;
    JButton an1,an2;
    JPanel mb1,mb2,mb3,mb4;

    public Xiugai(Frame fck,String ckm,Boolean msck,Xsxx xsxx2,int hang)
    {
        super(fck,ckm,msck);
        bq1=new JLabel("               学号      ");
        bq2=new JLabel("               姓名      ");
        bq3=new JLabel("               性别     ");
        bq4=new JLabel("               年龄     ");
        bq5=new JLabel("               籍贯     ");
        bq6=new JLabel("               院系     ");

        wbk1=new JTextField(5);
        wbk1.setText((String)xsxx2.getValueAt(hang,0));
        wbk1.setEditable(false);
        wbk2=new JTextField(5);
        wbk2.setText((String)xsxx2.getValueAt(hang,1));
        wbk3=new JTextField(5);
        wbk3.setText((String)xsxx2.getValueAt(hang,2));
        wbk4=new JTextField(5);
        wbk4.setText((String)xsxx2.getValueAt(hang,3).toString());
        wbk5=new JTextField(5);
        wbk5.setText((String)xsxx2.getValueAt(hang,4));
        wbk6=new JTextField(5);
        wbk6.setText((String)xsxx2.getValueAt(hang,5));


        an1=new JButton("修改");
        an1.addActionListener(this);
        an1.setActionCommand("xiugai");
        an2=new JButton("取消");
        an2.addActionListener(this);
        an2.setActionCommand("quxiao");

        mb1=new JPanel();
        mb2=new JPanel();
        mb3=new JPanel();
        mb4=new JPanel();

        mb1.setLayout(new GridLayout(6,1));
        mb2.setLayout(new GridLayout(6,1));

        mb1.add(bq1);  mb1.add(bq2);  mb1.add(bq3);
        mb1.add(bq4);  mb1.add(bq5);  mb1.add(bq6);

        mb2.add(wbk1);  mb2.add(wbk2);  mb2.add(wbk3);
        mb2.add(wbk4);  mb2.add(wbk5);  mb2.add(wbk6);

        mb3.add(an1);  mb3.add(an2);

        this.add(mb1,BorderLayout.WEST);
        this.add(mb2);
        this.add(mb3,BorderLayout.SOUTH);
        this.add(mb4,BorderLayout.EAST);

        this.setSize(370,270);
        this.setLocation(401,281);
        this.setResizable(false);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("xiugai"))
        {
            PreparedStatement ps=null;
            Connection ct=null;
            ResultSet rs=null;
            Statement sm=null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/student1?serverTimezone=UTC&useSSL=false","root","521986");
                String ss=("update xuesheng set  xingming=?,xingbie=?,nianling=?,jiguan=?,yxmc=? where xuehao=?");
                ps=ct.prepareStatement(ss);
                ps.setString(1,wbk2.getText());
                ps.setString(2,wbk3.getText());
                ps.setString(3,wbk4.getText());
                ps.setString(4,wbk5.getText());
                ps.setString(5,wbk6.getText());
                ps.setString(6,wbk1.getText());
                ps.executeUpdate();

                this.dispose();
            } catch (Exception e2){}
            finally
            {
                try {
                    if(rs!=null)
                    {
                        rs.close();
                    }
                    if(ps!=null)
                    {
                        ps.close();
                    }
                    if(ct!=null)
                    {
                        ct.close();
                    }

                } catch (Exception e3){}
            }
        }
        else if(e.getActionCommand().equals("quxiao"))
        {
            this.dispose();
        }
    }
}


