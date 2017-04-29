package game;

import scenes.PlayScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Nhan on 2/28/2017.
 */
public class GameWindow extends Frame implements Runnable{
    private static final int SCREEN_WIDTH = 768;
    private static final int SCREEN_HEIGHT = 600;
    private BufferedImage backBuffer;
    private Graphics backGraphic;
    private PlayScene playScene;

    public GameWindow(){
        setVisible(true);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        playScene = new PlayScene();
        this.addKeyListener(playScene);
        backBuffer = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_BGR);
        backGraphic = backBuffer.getGraphics();
    }

    @Override
    public void run() {
        while (true){
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            playScene.run();
        }

    }

    @Override
    public void update(Graphics g) {
       playScene.update(backGraphic);
       g.drawImage(backBuffer, 0, 0, null);
    }
}
