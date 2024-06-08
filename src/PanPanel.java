import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PanPanel extends JPanel implements MouseListener {
    private Color backgroundColor = new Color(222, 198, 178);
    private Color lineColor = Color.black;
    private boolean flag = false; // false: black
    private int[][] board = new int[14][14]; // 14x14 board
    public int count = 0;
    static private Image blackStoneImage = Toolkit.getDefaultToolkit().getImage("Img/blackS.png");
    static private Image whiteStoneImage = Toolkit.getDefaultToolkit().getImage("Img/whiteS.png");
    private ArrayList<Stone> stones = new ArrayList<>();

    public PanPanel() {
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
        int x = e.getX() / 50;
        int y = e.getY() / 50;

        if (board[y][x] == 0) {
            board[y][x] = flag ? 1 : -1;
            flag = !flag;
            count++;
            stones.add(new Stone(x, y, flag ? Color.black : Color.white));
            repaint();
            if (checkWin(y, x)) {
                String winner = flag ? "Black" : "White";
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
            }
        }

        for (Stone stone : stones) {
            stone.draw(g);
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
        stones.clear();
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                board[i][j] = 0;
            }
        }
        flag = false;
        count = 0;
        repaint();
    }

    private static class Stone {
        private int x;
        private int y;
        private Color color;

        public Stone(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public void draw(Graphics g) {
            if (color == Color.black) {
                g.drawImage(blackStoneImage, x * 50 , y * 50 , 50, 50, null);
            } else if (color == Color.white) {
                g.drawImage(whiteStoneImage, x * 50 , y * 50 , 50, 50, null);
            }
        }
    }
}
