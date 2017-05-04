package scenes;

import game.GameWindow;
import managers.SceneManager;
import utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Nhan on 5/4/2017.
 */
public class ChooseMainScene extends GameScene implements MouseListener{
    private BufferedImage backgroundImageType;

    private BufferedImage mainCharacter1;
    private BufferedImage mainCharacter2;
    private BufferedImage chooseText;

    private Rectangle mainRect1;
    private Rectangle mainRect2;
    private Rectangle chooseRect;

    private final Point firstButtonLocation;
    private final Dimension buttonSize;


    private GameWindow gameWindow;

    public ChooseMainScene(GameWindow gameWindow) throws IOException {
        this.actionType = ActionType.CHOOSE_MAIN;
        this.gameWindow = gameWindow;
        buttonSize = new Dimension(57, 92);
        loadImage();
        firstButtonLocation = new Point(this.gameWindow.getWidth() / 2  - this.buttonSize.width - 20 ,
                this.gameWindow.getHeight() - 2 * this.buttonSize.height );
        makeRect();
    }



    private void loadImage()  {

        try {
            backgroundImageType = ImageIO.read(new FileInputStream("res/choicetype.gif"));
            mainCharacter1 = ImageIO.read(new FileInputStream("res/davis_shooting_0.png"));
            mainCharacter2 = ImageIO.read(new FileInputStream("res/10.png"));
            chooseText = ImageIO.read(new FileInputStream("res/choose_your_character.png"));
            backgroundImageType = Utils.setSize(backgroundImageType, this.gameWindow.getSize());
            mainCharacter1 = Utils.setSize(mainCharacter1,buttonSize.width, buttonSize.height);
            mainCharacter2 = Utils.setSize(mainCharacter2,buttonSize.width, buttonSize.height);
            chooseText = Utils.setSize(chooseText,500,200);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeRect() {
        chooseRect = new Rectangle(firstButtonLocation.x - 2 * buttonSize.width,
                firstButtonLocation.y - 3 * buttonSize.height,
                chooseText.getWidth(),
                chooseText.getHeight());
        mainRect1 = new Rectangle(firstButtonLocation.x,
                firstButtonLocation.y,
                mainCharacter1.getWidth(),
                mainCharacter1.getHeight());
        mainRect2 = new Rectangle(firstButtonLocation.x + 2 * buttonSize.width + 20,
                firstButtonLocation.y,
                mainCharacter2.getWidth(),
                mainCharacter2.getHeight());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (mainRect1.contains(e.getX(),e.getY())) {
            PlayScene.mainType = 1 ;
            SceneManager.getInstance().sceneAction(ActionType.PLAY_STAGE_1);
        }

        if (mainRect2.contains(e.getX(),e.getY())) {
            PlayScene.mainType = 2;
            SceneManager.getInstance().sceneAction(ActionType.PLAY_STAGE_1);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void update(Graphics g) {
        g.drawImage(backgroundImageType, 0, 0, null);
        g.drawImage(chooseText,chooseRect.x,chooseRect.y,null);
        g.drawImage(mainCharacter1,mainRect1.x,mainRect1.y,null);
        g.drawImage(mainCharacter2,mainRect2.x,mainRect2.y,null);
    }

    @Override
    public void run() {

    }
}
