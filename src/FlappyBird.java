import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;


public class FlappyBird extends JPanel implements ActionListener, KeyListener {

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

    //pipe
    private int pipeX = width;
    private int pipeY = 0;
    private int pipeWith = 64;
    private int pipeHeight = 512;

    //game logic
    Bird bird;
    Timer gameLoop;
    Timer pipesTimer;
    int velocityX = -4; //for pipes
    int velocityY = -10; //for fly
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random random = new Random();

    double score = 0;
    boolean gameOver = false;

    public FlappyBird() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        addKeyListener(this);
        loadImages();

        bird = new Bird(birdX, birdY, birdImg);
        pipes = new ArrayList<>();

        pipesTimer = new Timer(1500, e -> placePipes());
        pipesTimer.start();
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    //load images
    private void loadImages() {
        birdImg = new ImageIcon(getClass().getResource("/Resources/flappybird.png")).getImage();
        backgroundImg = new ImageIcon(getClass().getResource("/Resources/flappybirdbg.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("/Resources/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("/Resources/bottompipe.png")).getImage();
    }

    public void placePipes() {
        int randomPipeY = (int) (pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = height / 4;

        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe botoomPipe = new Pipe(bottomPipeImg);
        botoomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(botoomPipe);
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
        //pipe
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.image, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if(gameOver)
            g.drawString("Game Over: " + String.valueOf(score), 10, 35);
        else g.drawString(String.valueOf((int)score), 10, 35);
    }

    public void move() {
        bird.birdY += velocityY;
        velocityY += gravity;
        bird.birdY = Math.max(bird.birdY, 0);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;

            if(!pipe.passed && bird.birdX > pipe.x + pipe.width) {
                pipe.passed = true;
                score += 0.5;
            }

            if (collisioin(bird, pipe))
                gameOver = true;
        }
        if (bird.birdY > height)
            gameOver = true;
    }

    public boolean collisioin(Bird a, Pipe b) {
        return  a.birdX < b.x + b.width &&
                a.birdX + a.birdWidth > b.x &&
                a.birdY < b.y + b.height &&
                a.birdY + a.birdHeight > b.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if (gameOver) {
            pipesTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
            if(gameOver) {
                bird.birdY = birdY;
                velocityY = 10;
                pipes.clear();
                score = 0;
                gameOver = false;
                gameLoop.start();
                pipesTimer.start();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


    class Pipe {
        int x = pipeX;
        int y = pipeY;
        int height = pipeHeight;
        int width = pipeWith;
        Image image;
        boolean passed = false;

        public Pipe(Image image) {
            this.image = image;
        }

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
