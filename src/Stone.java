import java.awt.*;

public class Stone {
    private final int x;
    private final int y;
    private final Color color;

    public Stone(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void draw(Graphics g, int size) {
        g.setColor(color);
        g.fillOval(x - size / 2, y - size / 2, size, size);
    }
}
