package com.panjohnny.why;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class CockRidingCrow extends Canvas {
    private BufferedImage cock, crow;
    public int x = 500, y = 500, haha = 0;
    public Color color = Color.WHITE;

    public CockRidingCrow() throws IOException, InterruptedException {
        cock = ImageIO.read(Objects.requireNonNull(CockRidingCrow.class.getResourceAsStream("/cock.png")));
        crow = ImageIO.read(Objects.requireNonNull(CockRidingCrow.class.getResourceAsStream("/c.png")));
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-cock.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        cock = op.filter(cock, null);

        JFrame frame = new JFrame("Why?");
        frame.setResizable(false);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.add(this);
        this.addKeyListener(new KeyboardStuff(this));

        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
        Random random = new Random();

        // The wrapper thread is unnecessary, unless it blocks on the
// Clip finishing; see comments.
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(
                        Objects.requireNonNull(CockRidingCrow.class.getResourceAsStream("/music.wav"))));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        while (frame.isVisible()) {
//            int dirX = random.nextInt(-10, 10);
//            int dirY = random.nextInt(-10, 10);
//            x+=dirX;
//            y+=dirY;
//
//            if(x<0)
//                x = frame.getWidth();
//            else if(x>frame.getWidth())
//                x = 0;
//            if(y<0)
//                y = frame.getHeight();
//            else if(y>frame.getHeight())
//                y = 0;
            setBackground(color);
            render();
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        if (haha == 3) {
            g.clearRect(0, 0, getWidth(), getHeight());
            haha = 0;
        }

        g.drawImage(crow, x, y, 200, 100, null);
        g.drawImage(cock, x, y - 100, 300, 200, null);

        g.dispose();
        bs.show();
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        new CockRidingCrow();
    }

    public void party() {
        Random random = new Random();
        int r = random.nextInt(255);
        int b = random.nextInt(255);
        int g = random.nextInt(255);

        color = new Color(r, g, b);
    }
}
