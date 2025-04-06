import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class FlappyBird extends JPanel {

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


    public FlappyBird() {
        setPreferredSize(new Dimension(width, height));

        loadImages();

        bird = new Bird(birdX, birdY, birdImg);
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
        g.drawImage(backgroundImg, 0, 0, width, height, null);
        g.drawImage(birdImg, birdX, birdY, bird.getBirdWidth(), bird.getBirdHeight(), null);
    }

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
