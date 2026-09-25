public class Shader {

    enum ShaderType {
        DIFFUSE,
        EMISSION
    }

    Color color;
    ShaderType shaderType;

    private Shader(ShaderType shaderType, Color color) {
        this.shaderType = shaderType;
        this.color = color;
    }

    public static Shader shaderDiffuse(Color color) {
        return new Shader(ShaderType.DIFFUSE, color);
    }

    public static Shader shaderEmission(Color color) {
        return new Shader(ShaderType.EMISSION, color);
    }

    Color calculateColor(Vector3 incoming, Vector3 outgoing, Vector3 normal, Color incomingColor) {
        // use simple diffuse + color.mix for now
        double weight;
        switch (this.shaderType) {
            case DIFFUSE -> {
                weight = simpleDiffuse(incoming, normal);
            }
            case EMISSION -> {
                weight = simpleEmission();
            }
            default -> {
                throw new IllegalArgumentException("Invalid shader type");
            }
        }
        return incomingColor.mix(this.color, weight);

    }

    double simpleDiffuse(Vector3 outgoing, Vector3 normal) {
        return Math.max(0, outgoing.normalized().dotProduct(normal.normalized()));
    }

    double simpleEmission() {
        return 1;
    }
}
