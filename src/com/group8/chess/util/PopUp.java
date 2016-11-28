package com.group8.chess.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class PopUp {
    //variables for buttons
    private JFrame frame;
    private JTextField txtButton;
    private JTextField txt;
    
    //constructer to test button
    public PopUp(PopupType type)
    {
    	switch (type) {
		case CONNECTING:
			connectServer();
			break;
		case WHITE_TURN:
			whiteTurn();
			break;
		case BLACK_TURN:
			blackTurn();
			break;
		case CHECK:
			check();
			break;
		case CHECKMATE:
			checkMate();
			break;
		default:
			break;
		}
    	frame.setVisible(true);
        //whiteTurn();
    }
    
    //main method builds button
    public static void main(String[] args) {
        
    }
    //button templates
    private void noButton() {  //popup no button
        frame = new JFrame();
         frame.setBounds(100, 100, 450, 300);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.getContentPane().setLayout(null);
         
         txt = new JTextField();
         txt.setText("");
         txt.setBounds(180, 20, 85, 20);
         txt.setBounds(frame.getWidth()/2-txt.getWidth()/2, 20, txt.getWidth(), txt.getHeight());
         frame.getContentPane().add(txt);
         txt.setColumns(10);
      
     }

    private void oneButton() {  //popup with one button / OK button
       frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        txt = new JTextField();
        txt.setText("");
        txt.setBounds(180, 20, 85, 20);
        txt.setBounds(frame.getWidth()/2-txt.getWidth()/2, 20, txt.getWidth(), txt.getHeight());
        frame.getContentPane().add(txt);
        txt.setColumns(10);

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
            }
        });
        btnOk.setBounds(166, 159, 89, 23);
        frame.getContentPane().add(btnOk);      
    }
    private void twoButton() {  //popup with two buttons / OK + Cancel buttons
        frame = new JFrame();
         frame.setBounds(100, 100, 450, 300);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.getContentPane().setLayout(null);
         
         txt = new JTextField();
         txt.setText("");
         txt.setBounds(180, 20, 85, 20);
         txt.setBounds(frame.getWidth()/2-txt.getWidth()/2, 20, txt.getWidth(), txt.getHeight());
         frame.getContentPane().add(txt);
         txt.setColumns(10);

         JButton btnOk = new JButton("OK");
         JButton btnCan = new JButton("Cancel");
         
         btnOk.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent arg0) {
                 frame.dispose();  //set action for OK button
             }
         });
         btnOk.setBounds(110, 160, 90, 20);  //play with the bounds if needed
         frame.getContentPane().add(btnOk);   


         btnCan.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent arg0) {
                 frame.dispose();  //set action for Cancel button
             }
         });
         btnCan.setBounds(230, 160, 90, 20);  //play with the bounds if needed
         frame.getContentPane().add(btnCan);  
     }
    
    private void whiteTurn() {
    	oneButton();
    	txt.setText("White's Turn");
    }
    private void blackTurn() {
    	oneButton();
    	txt.setText("Black's Turn");
    }
    private void check() {
    	oneButton();
    	txt.setText("Check");
    }
    private void checkMate() {
    	oneButton();
    	txt.setText("Check Mate");
    }
    private void staleMate() {
    	oneButton();
    	txt.setText("Stale Mate");
    }
    private void connectServer() {
    	noButton();
    	txt.setText("Connecting to server:\nWaiting for other player.");
    }
}