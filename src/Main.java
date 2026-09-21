import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

Color traceRay(Ray ray, ArrayList<Sphere> scene, int depth) {
    double currentLowestT = 1e99;
    Sphere intersectedSphere = null;

    for (Sphere sphere : scene) {
        var tToCheck = ray.intersectRay(sphere);
        if (tToCheck >= 0.0001 && tToCheck <= currentLowestT) {
            currentLowestT = tToCheck;
            intersectedSphere = sphere;
        }
    }
    if (intersectedSphere == null) {
        return new Color(0.5, 0.5, 0.5);
    }
    if (depth == 0) {
        return intersectedSphere.color;
    }
    var intersectionPoint = ray.positionAt(currentLowestT);
    var intersectionNormal = intersectedSphere.center
        .subtractVector(intersectionPoint)
        .normalized();

    var incomingRayReversed = ray.direction.multiply(-1);
    var outgoingRayDirection = intersectionNormal
            .multiply(2)
            .multiply(intersectionNormal
            .dotProduct(incomingRayReversed))
            .subtractVector(incomingRayReversed);
    var outgoingRayOrigin = intersectionPoint;
    var outgoingRay = new Ray(outgoingRayOrigin, outgoingRayDirection);


    var futureColor = traceRay(outgoingRay, scene, depth - 1);
    var thisColor = intersectedSphere.color;

    return thisColor.multiply(futureColor);
}

void main() throws IOException {
    var outputImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);

    var scene = new ArrayList<Sphere>();
    scene.add(new Sphere(2.0, new Vector3(0.0, 0.0, 10), new Color(.7, .8, .1)));
    scene.add(new Sphere(1.5, new Vector3(2.0, -5.0, 12), new Color(.8, .6, .8)));
    scene.add(new Sphere(2.5, new Vector3(-5.0, 2.0, 8), new Color(.2, .3, .8)));


    for (double x = -1.0;  x <= 1.0; x += 0.001) {
        for (double y = -1.0; y <= 1.0; y += 0.001) {
            var ray = new Ray(new Vector3(0.0, 0.0, 0.0), new Vector3(x, y, 1));

            var pixelColor = traceRay(ray, scene, 3);

            int imageX = Math.toIntExact((int) ((x + 1) * 0.5 * outputImage.getWidth()));
            int imageY = Math.toIntExact((int) ((y + 1) * 0.5 * outputImage.getHeight()));
            outputImage.setRGB(imageX, imageY, pixelColor.asIntegerColor());
        }
    }

    File outputfile = new File("output.png");
    ImageIO.write(outputImage, "png", outputfile);
}


