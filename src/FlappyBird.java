import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;



public class FlappyBird extends JPanel {
    private Image birdImg;
    private Image backgroundImg;
    private Image topPipeImg;
    private Image bottomPipeImg;
    private int width = 360;
    private int height = 640;

    public FlappyBird() {
        setPreferredSize(new Dimension(width, height));

        loadImages();

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
    }

}
