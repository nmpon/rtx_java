import java.util.ArrayList;

public class Scene {
    ArrayList<Sphere> sceneObjects;
    Camera camera;
    //TODO: lights

    Scene(ArrayList<Sphere> sceneObjects,  Camera camera) {
        this.sceneObjects = sceneObjects;
        this.camera = camera;
    }
}
