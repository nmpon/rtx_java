public class Camera {
    Vector3 position;
    Vector3 rotation;
    double verticalFieldOfView;

    Camera(Vector3 position, Vector3 rotation, double verticalFieldOfView) {
        this.position = position;
        this.rotation = rotation;
        this.verticalFieldOfView = verticalFieldOfView;
    }
}
