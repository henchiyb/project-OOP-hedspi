package scenes;

/**
 * Created by Nhan on 3/7/2017.
 */
public interface SceneListener {
    void replaceScene(GameScene newScene, boolean addToBackStack);
    void back();
}
