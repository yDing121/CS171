import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons extends JFrame{
    private JPanel jPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public Buttons(){
        this.setSize(300,300);
        jPanel = new JPanel();
        this.add(jPanel);

        button1 = new JButton("Button 1");
        Button1ActionListener button1Listener = new Button1ActionListener();
        button1.addActionListener(button1Listener);
        jPanel.add(button1);

        // Inner class
        button2 = new JButton("Button 2");
        Button2ActionListener button2Listener = new Button2ActionListener();
        button2.addActionListener(button2Listener);
        jPanel.add(button2);

        // Hidden class
        button3 = new JButton("Button 3");
        button3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Button3 got clicked");
                // jPanel.setBackground(Color.GREEN);
            }
        });
        jPanel.add(button3);
    }

    public class Button2ActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            System.out.println("Button 2 clicked");
        }
    }
    

    public static void main(String[] args) {
        Buttons buttons = new Buttons();
        buttons.setVisible(true);
    }
}
