public class Camera {
    Vector3 position;
    Vector3 rotation;
    double fieldOfView;

    Camera(Vector3 position, Vector3 rotation, double fieldOfView) {
        this.position = position;
        this.rotation = rotation;
        this.fieldOfView = fieldOfView;
    }
}
