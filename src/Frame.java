import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Frame extends JFrame implements ActionListener, MouseListener {
    JPanel bgd = new JPanel();
    static JPanel menu = new JPanel();
    JButton[] buttons = new JButton[1];
    JLabel label = new JLabel();
    static JLabel scoreBLabel = new JLabel();
    static JLabel scoreWLabel = new JLabel();
    static JLabel colon = new JLabel();
    static Font font = new Font("맑은 고딕", Font.BOLD, 22);

    JLabel score = new JLabel();
    PanPanel panPanel = new PanPanel();
    JLabel turn = new JLabel();

    Frame() {
        bgd.setLayout(null);
        bgd.setBounds(280,0, 900, 900);
        bgd.setBackground(Color.gray);

        menu.setBounds(0,0,280,900);
        menu.setBackground(Color.lightGray);
        menu.setLayout(null);

        label.setText("Gomoku");
        label.setFont(new Font("맑은고딕", Font.BOLD,35));
        label.setBounds(80, 20, 250,70);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.white);
            buttons[i].setForeground(Color.black);
        }
        buttons[0].setText("ReStart");
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setFont(font);
            menu.add(buttons[i]);
            buttons[i].setBounds(70, 100 + i*80, 150, 50);
            buttons[i].addActionListener(this);
        }

        setPreferredSize(new Dimension(1200, 950));//프레임 크기
        scoreWLabel.setFont(new Font("맑은고딕", Font.BOLD,35));
        scoreBLabel.setFont(new Font("맑은고딕", Font.BOLD,35));
        colon.setFont(font);
        colon.setForeground(Color.gray);
        scoreWLabel.setForeground(Color.white);
        scoreBLabel.setBounds(90, 600, 130, 50);
        colon.setBounds(133, 600, 20,50);
        scoreWLabel.setBounds(160, 600, 50, 50);

        add(scoreBLabel);
        add(scoreWLabel);
        add(colon);

        // scoreBLabel.setText(String.valueOf(panPanel.Bscore));
        // colon.setText(":");
        // scoreWLabel.setText(String.valueOf(panPanel.Wscore));

        turn.setBounds(105, 738, 130, 50);
        turn.setFont(font);
        turn.setText("Turn");

        add(turn);
        add(label);
        add(menu);
        bgd.add(panPanel);
        add(bgd);
        panPanel.addMouseListener(this);

        setLayout(null);
        setLocation(400, 20);//frame 창 위치 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//끄기

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttons[0]) {
            int response = JOptionPane.showConfirmDialog(this, "정말 게임을 다시 시작하시겠습니까?", "게임 재시작 확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (response == JOptionPane.YES_OPTION) {
                panPanel.resetGame();

                scoreBLabel.setText(String.valueOf(panPanel.Bscore));
                scoreWLabel.setText(String.valueOf(panPanel.Wscore));

                repaint();
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (panPanel.count % 2 == 0) {
            g.setColor(Color.black);
            g.fillOval(90, 680, 90, 90);
        } else {
            g.setColor(Color.white);
            g.fillOval(90, 680, 90, 90);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        scoreBLabel.setText(String.valueOf(panPanel.Bscore));
        colon.setText(":");
        scoreWLabel.setText(String.valueOf(panPanel.Wscore));
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
