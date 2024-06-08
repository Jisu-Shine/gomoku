import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanPanel extends JPanel implements MouseListener {
    Color backgroundColor = new Color(222, 198, 178);
    Color lineColor = Color.black;

    boolean click = false;
    boolean flag = true; // true: black
    int[][] board = new int[14][14]; // 14x14 board
    int count = 0;
    int Bscore = 0;
    int Wscore = 0;

    PanPanel() {
        addMouseListener(this);
        setBorder(new LineBorder(Color.black, 5));
        setLayout(null);
        setBounds(50, 50, 700, 700);
        setBackground(backgroundColor);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        click = true;
        int x = e.getX() / 50;
        int y = e.getY() / 50;

        if (board[y][x] == 0) {
            board[y][x] = flag ? 1 : -1;
            flag = !flag;
            count++;
            repaint();
            if (checkWin(y, x)) {
                String winner = board[y][x] == 1 ? "Black" : "White";
                JOptionPane.showMessageDialog(null, "GAME OVER\n Winner : " + winner);
                resetGame();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(lineColor);
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                g.drawLine(i * 50 + 25, 25, i * 50 + 25, 675);
                g.drawLine(25, j * 50 + 25, 675, j * 50 + 25);
                if (board[j][i] == 1) {
                    g.setColor(Color.black);
                    g.fillOval(i * 50 + 15, j * 50 + 15, 20, 20);
                } else if (board[j][i] == -1) {
                    g.setColor(Color.white);
                    g.fillOval(i * 50 + 15, j * 50 + 15, 20, 20);
                }
            }
        }
    }

    private boolean checkWin(int y, int x) {
        int player = board[y][x];
        return (checkDirection(y, x, player, 1, 0) || // Horizontal
                checkDirection(y, x, player, 0, 1) || // Vertical
                checkDirection(y, x, player, 1, 1) || // Diagonal 
                checkDirection(y, x, player, 1, -1)); // Diagonal 
    }

    private boolean checkDirection(int y, int x, int player, int dy, int dx) {
        int count = 1;
        for (int i = 1; i < 5; i++) {
            int ny = y + dy * i;
            int nx = x + dx * i;
            if (ny < 0 || ny >= 14 || nx < 0 || nx >= 14 || board[ny][nx] != player) break;
            count++;
        }
        for (int i = 1; i < 5; i++) {
            int ny = y - dy * i;
            int nx = x - dx * i;
            if (ny < 0 || ny >= 14 || nx < 0 || nx >= 14 || board[ny][nx] != player) break;
            count++;
        }
        return count >= 5;
    }

    public void resetGame() {
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                board[i][j] = 0;
            }
        }
        flag = true;
        count = 0;
        repaint();
    }
}
