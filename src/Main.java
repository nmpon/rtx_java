import javax.imageio.ImageIO;


void main() throws IOException {
    var sceneObjects = new ArrayList<Sphere>();
    sceneObjects.add(new Sphere(2.0, new Vector3(0.0, 0.0, 10), new Color(.7, .8, .1)));
    sceneObjects.add(new Sphere(1.5, new Vector3(2.0, -5.0, 12), new Color(.8, .6, .8)));
    sceneObjects.add(new Sphere(2.5, new Vector3(-5.0, 2.0, 8), new Color(.2, .3, .4)));
    sceneObjects.add(new Sphere(2.0, new Vector3(4.0, -2.0, 8), new Color(2, 2, 2)));
    var camera = new Camera(
            new Vector3(0, 0, 0),
            new Vector3(0, 0, 0)
    );
    var scene = new Scene(sceneObjects, camera);
    var outputImage = RenderEngine.renderSimple(scene, 400, 400, 40, 12);

    File outputfile = new File("output.png");
    ImageIO.write(outputImage, "png", outputfile);
}


