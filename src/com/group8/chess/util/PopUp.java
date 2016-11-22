import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PopUp
{
    //variables for buttons
    private JFrame frame;
    private JTextField txtButton;
    private JTextField txtWhite;
    private JTextField txtBlack;
    private JTextField txtCheck;
    private JTextField txtCMate;
    private JTextField txtSMate;
    private JTextField txtConnect;
    
    //constructer to test button
    public PopUp()
    {
        basicButton();
        //whiteTurn();
    }
    
    //main method builds button
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PopUp window = new PopUp();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //button template
    private void basicButton() {
       frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        txtButton = new JTextField();
        txtButton.setText("Button");
        txtButton.setBounds(180, 20, 85, 20);
        frame.getContentPane().add(txtButton);
        txtButton.setColumns(10);

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
            }
        });
        btnOk.setBounds(166, 159, 89, 23);
        frame.getContentPane().add(btnOk);      
    }
    private void whiteTurn() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        txtWhite = new JTextField();
        txtWhite.setText("Player White Turn");
        txtWhite.setBounds(180, 20, 85, 20);
        frame.getContentPane().add(txtWhite);
        txtWhite.setColumns(10);

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
            }
        });
        btnOk.setBounds(166, 159, 89, 23);
        frame.getContentPane().add(btnOk);     
    }
    private void blackTurn() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        txtWhite = new JTextField();
        txtWhite.setText("Player black Turn");
        txtWhite.setBounds(169, 21, 86, 20);
        frame.getContentPane().add(txtWhite);
        txtWhite.setColumns(10);
        
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
            }
        });
        btnOk.setBounds(166, 159, 89, 23);
        frame.getContentPane().add(btnOk);
    }
    private void checkPop() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        txtWhite = new JTextField();
        txtWhite.setText("Check");
        txtWhite.setBounds(169, 21, 86, 20);
        frame.getContentPane().add(txtWhite);
        txtWhite.setColumns(10);
        
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
            }
        });
        btnOk.setBounds(166, 159, 89, 23);
        frame.getContentPane().add(btnOk);
    }
    private void cMatePop() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        txtWhite = new JTextField();
        txtWhite.setText("Check Mate");
        txtWhite.setBounds(169, 21, 86, 20);
        frame.getContentPane().add(txtWhite);
        txtWhite.setColumns(10);
        
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
            }
        });
        btnOk.setBounds(166, 159, 89, 23);
        frame.getContentPane().add(btnOk);
    }
    private void sMatePop() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        txtWhite = new JTextField();
        txtWhite.setText("Stale Mate");
        txtWhite.setBounds(169, 21, 86, 20);
        frame.getContentPane().add(txtWhite);
        txtWhite.setColumns(10);
        
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
            }
        });
        btnOk.setBounds(166, 159, 89, 23);
        frame.getContentPane().add(btnOk);
    }
    private void connection() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        txtWhite = new JTextField();
        txtWhite.setText("Connecting to Server.\n Waiting for other player");
        txtWhite.setBounds(169, 21, 86, 20);
        frame.getContentPane().add(txtWhite);
        txtWhite.setColumns(10);
        
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
            }
        });
        btnOk.setBounds(166, 159, 89, 23);
        frame.getContentPane().add(btnOk);
    }
}
