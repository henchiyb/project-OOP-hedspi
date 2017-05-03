package game;

import managers.SceneManager;
import scenes.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Nhan on 2/28/2017.
 */
public class GameWindow extends Frame implements Runnable, SceneListener{
    private BufferedImage backBuffer;
    private Graphics backGraphic;
    private GameScene gameScene;

    public GameWindow(){
        setVisible(true);
        setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        SceneManager.getInstance().register(this);
        try {
            gameScene = new MenuScene(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addKeyListener(gameScene);
        this.addMouseListener((MenuScene) gameScene);
        backBuffer = new BufferedImage(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, BufferedImage.TYPE_INT_BGR);
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
            gameScene.run();
        }

    }

    @Override
    public void update(Graphics g) {
       gameScene.update(backGraphic);
       g.drawImage(backBuffer, 0, 0, null);
    }

    @Override
    public void sceneAction(ActionType actionType) {
        switch (actionType){
            case MENU_SCENE:
                try {
                    attach(new MenuScene(this));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case PLAY_STAGE_1:
                attach(new PlayScene());
                break;
            case PLAY_STAGE_2:
                attach(new PlayScene2());
                break;
            case ATTACH:
                attach(gameScene);
                break;
            case DETACH:
                detach();
                break;
        }
    }

    private void detach(){
        if(gameScene != null){
            if(gameScene.getActionType() == ActionType.MENU_SCENE) {
                removeMouseListener((MenuScene) gameScene);
            }
            removeKeyListener(gameScene);
        }
    }

    private void attach(GameScene newScene){
        detach();
        gameScene = newScene;
        if(gameScene.getActionType() == ActionType.MENU_SCENE) {
            addMouseListener((MenuScene) gameScene);
        }
        addKeyListener(gameScene);
    }
}
