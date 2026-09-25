import javax.imageio.ImageIO;

/*
TODO:
add shaders:
a. Blinn-Phong
b. emission

AA pixel mixing
*/



void main() throws IOException {
    var sceneObjects = new ArrayList<Sphere>();

    sceneObjects.add(new Sphere(2.0, new Vector3(0.0, 0.0, 10), Shader.shaderDiffuse(new Color(.7, .7, .1))));
    sceneObjects.add(new Sphere(1.5, new Vector3(2.0, -5.0, 12), Shader.shaderDiffuse(new Color(.8, .6, .8))));
    sceneObjects.add(new Sphere(2.5, new Vector3(-5.0, 2.0, 8), Shader.shaderDiffuse(new Color(.2, .3, .4))));
    sceneObjects.add(new Sphere(2.0, new Vector3(4.0, -2.0, 8), Shader.shaderEmission(new Color(9, 9, 9))));
    var camera = new Camera(
            new Vector3(0, 0, 0),
            new Vector3(0, 0, 0),
            Math.toRadians(50)
    );
    var scene = new Scene(sceneObjects, camera, new Color(0.2, 0.2, 0.2));
    var outputImage = RenderEngine.renderSimple(scene, 1280, 720, 50, 12);

    File outputfile = new File("output.png");
    ImageIO.write(outputImage, "png", outputfile);
}