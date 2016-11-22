package com.group8.chess.util;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.omg.PortableServer.ServantRetentionPolicyValue;

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
			
			break;
		case WHITE_TURN:
			whiteTurn();
			break;
		case BLACK_TURN:
			
			break;
		case CHECK:
			
			break;
		case CHECKMATE:
			
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
    //button template
    private void basicButton() {
       frame = new JFrame();
       frame.setVisible(true);
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        txt = new JTextField("");
        txt.setEditable(false);
        txt.setBounds(frame.getWidth()/2, 21, 200, 20);
        txt.setBounds(frame.getWidth()/2-txt.getWidth()/2, 21, txt.getWidth(), txt.getHeight());
        frame.getContentPane().add(txt);
        txt.setColumns(10);
        
        txtButton = new JTextField();
        txtButton.setText("Button");
        txtButton.setBounds(frame.getWidth()/2-85/2, 20, 85, 20);
        frame.getContentPane().add(txtButton);
        txtButton.setColumns(10);
        txt.setText("Player White Turn");
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
        basicButton();
        txt.setText("White turn");
    	
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
