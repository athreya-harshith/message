import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.GenericDeclaration;
import java.net.*;
import java.io.*;
import javax.swing.*;
public class test 
{
    public test()
    {
        JFrame jfr = new JFrame("Swing Messenger");
        jfr.setLayout(new FlowLayout());
        jfr.setSize(400,400);
        jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton ct = new JButton("Client");
        ct.addActionListener(
                new ActionListener()
                {

                    public void actionPerformed(ActionEvent ae)
                    {
                        client();
                        jfr.dispose();
                        jfr.setVisible(false);
                    }
                }
        ); 
        JButton sr = new JButton("Server");
        sr.addActionListener(
                new ActionListener()
                {

                    public void actionPerformed(ActionEvent ae)
                    {
                        JLabel serv = new JLabel();
                        try
                        {
                            server();
                        }
                        catch(Exception e)
                        {
                            jfr.add(serv);
                            serv.setText("Server Err");
                        }
                        jfr.dispose();
                        jfr.setVisible(false);
                    }
                }
        ); 
        jfr.add(ct);
        jfr.add(sr);
        jfr.setVisible(true);
    }
    public void client()
    {
        JFrame jfr = new JFrame("Swing Messenger Client Side");
        jfr.setLayout(new FlowLayout());
        jfr.setSize(400,400);
        jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfr.setVisible(true);

    
        JLabel jl = new JLabel("Enter the IP Address");
       
        // jfr.add(jtf);
        JButton jbt = new JButton("Ok");
        jfr.add(jbt);
        String ip;
        JLabel jip = new JLabel();
        JLabel jP = new JLabel();
        JPanel jp = new JPanel();
        JTextField jtfip = new JTextField(20);
        jtfip.setText("Enter Ip");
        jp.add(jtfip);
        JTextField jtfp = new JTextField(20);
        jtfp.setText("Enter Port");
        jp.add(jtfp);
        jfr.add(jp);
        jbt.addActionListener(
                new ActionListener()
                {

                    public void actionPerformed(ActionEvent ae)
                    {
                       jip.setText(jtfip.getText());
                       jtfip.setText(null);
                       jP.setText(jtfp.getText());
                       try
                       {
                        clientMsg(jip.getText(),Integer.parseInt(jP.getText()));
                       }
                       catch(Exception e)
                       {
                        JLabel err = new JLabel();
                        err.setText("Error In Connecting");
                       }
                       jfr.dispose();
                       jfr.setVisible(false);
                    }
                }
        ); 
    }

    public void clientMsg(String ip,int port) throws Exception
    {
        JFrame jfr = new JFrame("Messaging to "+ip);
        jfr.setLayout(new FlowLayout());
        jfr.setSize(400,400);
        jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfr.setVisible(true);


        JLabel mslb = new JLabel("Enter The Message");
        JTextField msg = new JTextField(20);
        jfr.add(mslb);
        jfr.add(msg);
        JButton sendbt = new JButton("Send");
        jfr.add(sendbt);


        /* The Client Server Program  */
        Socket sock=new Socket(ip,port); 
        /*
        BufferedReader keyRead=new BufferedReader(new InputStreamReader(System.in)); 
         String fname=keyRead.readLine(); 
        OutputStream ostream=sock.getOutputStream(); 
        PrintWriter pwrite=new PrintWriter(ostream,true); 
         */
        // while(true)
        // {
            OutputStream ostream=sock.getOutputStream(); 
        PrintWriter pwrite=new PrintWriter(ostream,true); 
        sendbt.addActionListener(
                new ActionListener()
                {

                    public void actionPerformed(ActionEvent ae)
                    {
                       String send = msg.getText();
                       pwrite.println(send);
                       msg.setText(null);
                    }
                }
        ); 
        InputStream istream=sock.getInputStream(); 
        BufferedReader socketRead=new BufferedReader(new InputStreamReader(istream)); 
        String rep = socketRead.readLine();
        JLabel recv = new JLabel("rec :");
        jfr.add(recv);
        JTextField rept = new JTextField(20);
        rept.setText("HEllooooo");
        // }
        
    }
    public void server() throws Exception
    {
        JFrame jfr = new JFrame("Swing Messenger server Side");
        jfr.setLayout(new FlowLayout());
        jfr.setSize(400,400);
        jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfr.setVisible(true);


        JTextField jtf = new JTextField(20);
        jfr.add(jtf);
        JLabel jl = new JLabel();
        jfr.add(jl);
        JButton jbt = new JButton("Send");


        jfr.add(jbt);
        // JPanel jp = new JPanel();
        // jp.d(new GridLayout(20,20));
        // JScrollPane jsp = new JScrollPane(jp);
        // jfr.add(jsp);
        ServerSocket sersock=new ServerSocket(4000); 
        Socket sock=sersock.accept(); 
        //while(true)
        //{
            InputStream istream=sock.getInputStream(); 
            BufferedReader fileRead=new BufferedReader(new InputStreamReader(istream)); 
            String rep=fileRead.readLine(); 
            JLabel recv = new JLabel();
            recv.setText(rep);
            jfr.add(recv);
            OutputStream ostream=sock.getOutputStream(); 
            PrintWriter pwrite=new PrintWriter(ostream,true); 
            String str; 
            jbt.addActionListener(
                    new ActionListener()
                    {
    
                        public void actionPerformed(ActionEvent ae)
                        {
                           //jl.setText("ME :"+jtf.getText());
                           pwrite.println(jtf.getText());
                           // scrPane(jfr,jp,jsp,new JLabel("Me : "+jtf.getText()));
                           jtf.setText(null);
                        }
                    }
            ); 
        //}
        
        
    }

    // public void scrPane(JFrame jfr,JPanel jp,JScrollPane jsp,JLabel jl)
    // {
    //     jp.add(jl);
    //     jsp.add(jp);
    //     jfr.add(jsp);
    // }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                new test();
            }
        }
        );
    }
}

/*
 

public void client() throws Exception
    {
        Socket sock=new Socket("127.0.01",4000); 

        System.out.println("Enter the filename"); 

    BufferedReader keyRead=new BufferedReader(new InputStreamReader(System.in)); 

    String fname=keyRead.readLine(); 

    OutputStream ostream=sock.getOutputStream(); 

    PrintWriter pwrite=new PrintWriter(ostream,true); 

    pwrite.println(fname); 

    InputStream istream=sock.getInputStream(); 

    BufferedReader socketRead=new BufferedReader(new InputStreamReader(istream)); 

    String str; 
    while((str=socketRead.readLine())!=null) 
    { 
        System.out.println(str); 
    } 

    pwrite.close(); 
    socketRead.close(); 
    keyRead.close(); 
    }
 */

 /*
  ServerSocket sersock=new ServerSocket(4000); 
System.out.println("Server ready for connection"); 

Socket sock=sersock.accept(); 

System.out.println("Connection Is successful and waiting for chatting"); 

InputStream istream=sock.getInputStream(); 

BufferedReader fileRead=new BufferedReader(new InputStreamReader(istream)); 

String fname=fileRead.readLine(); 

BufferedReader ContentRead=new BufferedReader(new FileReader(fname)); 

OutputStream ostream=sock.getOutputStream(); 

PrintWriter pwrite=new PrintWriter(ostream,true); 

String str; 

while((str=ContentRead.readLine())!=null){ 

pwrite.println(str); 

} 
sock.close(); 
sersock.close(); 
pwrite.close(); 
fileRead.close(); 
ContentRead.close();
    }

    public static void main(String[] args) {
        tcp a = new tcp();
        String s;
  */