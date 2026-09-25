import java.util.ArrayList;

public class Scene {
    ArrayList<Sphere> sceneObjects;
    Camera camera;
    Color environmentColor;
    //TODO: lights

    Scene(ArrayList<Sphere> sceneObjects,  Camera camera) {
        this.sceneObjects = sceneObjects;
        this.camera = camera;
        environmentColor = new Color(0.5d, 0.5d, 0.5d);
    }

    Scene(ArrayList<Sphere> sceneObjects, Camera camera,  Color environmentColor) {
        this.sceneObjects = sceneObjects;
        this.camera = camera;
        this.environmentColor = environmentColor;
    }
}
