package managers;

import scenes.SceneListener;
import scenes.ActionType;

import java.util.ArrayList;

/**
 * Created by Nhan on 5/1/2017.
 */
public class SceneManager {
    private ArrayList<SceneListener> sceneListeners;
    private static SceneManager instance;
    private SceneManager(){
        sceneListeners = new ArrayList<>();
    }

    public static SceneManager getInstance() {
        if(instance == null)
            instance = new SceneManager();
        return instance;
    }

    public void register(SceneListener sceneListener){
        sceneListeners.add(sceneListener);
    }

    public void unregister(SceneListener sceneListener){
        sceneListeners.remove(sceneListener);
    }

    public void sceneAction(ActionType actionType){
        for (int i = 0; i < sceneListeners.size(); i++){
            sceneListeners.get(i).sceneAction(actionType);
        }
    }
}
