import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;


public class FlappyBird extends JPanel implements ActionListener, KeyListener{

    //Images
    private Image birdImg;
    private Image backgroundImg;
    private Image topPipeImg;
    private Image bottomPipeImg;

    //Panel Size
    private int width = 360;
    private int height = 640;

    //Bird Position
    private int birdX = width / 8;
    private int birdY = height / 2;

    Bird bird;
    Timer gameLoop;
    int velocityY = -10;
    int gravity = 1;

    public FlappyBird() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        addKeyListener(this);
        loadImages();

        bird = new Bird(birdX, birdY, birdImg);

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    //load images
    private void loadImages() {
        birdImg = new ImageIcon(getClass().getResource("/Resources/flappybird.png")).getImage();
        backgroundImg = new ImageIcon(getClass().getResource("/Resources/flappybirdbg.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("/Resources/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("/Resources/bottompipe.png")).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        //background
        g.drawImage(backgroundImg, 0, 0, width, height, null);
        //bird
        g.drawImage(birdImg, bird.birdX, bird.birdY, bird.getBirdWidth(), bird.getBirdHeight(), null);
    }

    public void move() {
        bird.birdY += velocityY;
        velocityY += gravity;
        bird.birdY = Math.max(bird.birdY, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    class Bird extends JPanel {
        private int birdX;
        private int birdY;

        public int getBirdHeight() {
            return birdHeight;
        }

        public int getBirdWidth() {
            return birdWidth;
        }

        private int birdHeight;
        private int birdWidth;
        private Image image;

        public Bird(int birdX, int birdY, Image image) {
            this.image = image;
            this.birdX = birdX;
            this.birdY = birdY;
            birdHeight = 24;
            birdWidth = 34;
        }
    }
}
