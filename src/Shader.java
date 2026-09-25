public class Shader {
    Color color;

    Shader(Color color) {
        this.color = color;
    }

    Color calculateColor(Vector3 incoming, Vector3 outgoing, Vector3 normal, Color incomingColor) {
        // use simple diffuse + color.mix for now
        var weight = simpleDiffuse(outgoing, normal);
        return this.color.mix(incomingColor, weight);
    }

    double simpleDiffuse(Vector3 outgoing, Vector3 normal) {
        return Math.max(0, outgoing.normalized().dotProduct(normal.normalized()));
    }
}
