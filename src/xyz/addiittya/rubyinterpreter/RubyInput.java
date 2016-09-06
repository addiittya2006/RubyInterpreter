package xyz.addiittya.rubyinterpreter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import xyz.addiittya.lexer.Lexer;

/**
 *
 * @author addiittya
 */

class RubyInput extends JFrame {
    public JTextPane rubyText;
    JButton next; 
    FileWriter fWriter = null;
    BufferedWriter writer = null;
    
    public RubyInput()
    {
        rubyText = new JTextPane();
        next = new JButton("Run");
        setSize(900,525);
        setTitle("RUBY INTERPRETER: ENTER RUBY CODE");
        setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        JPanel mainPanel1 = new JPanel();
        mainPanel1.setLayout(new BorderLayout());
        mainPanel1.add(rubyText);
        add("Center",mainPanel1);
        
        mainPanel1 = new JPanel();
        mainPanel1.add(next);
        add("South",mainPanel1);
        
        next.addActionListener(new btn());
    }
    
     class btn implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try {
                String text = rubyText.getText();
                fWriter = new FileWriter("ruby.txt");
                writer = new BufferedWriter(fWriter);
                writer.write(text);
                writer.newLine();
                writer.close();
                
            } catch (IOException ex) {
                Logger.getLogger(RubyInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            Lexer l = new Lexer();
            
            dispose();
        }
    }
}
