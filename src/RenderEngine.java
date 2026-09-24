import java.awt.image.BufferedImage;

public class RenderEngine {

    static Color traceRay(Ray ray, Scene scene, int depth) {
        double currentLowestT = 1e99;
        Sphere intersectedSphere = null;

        for (Sphere sphere : scene.sceneObjects){
            var checkT = ray.intersectRay(sphere);
            if (checkT >= 0.0001 && checkT <= currentLowestT) {
                currentLowestT = checkT;
                intersectedSphere = sphere;
            }
        }
        if (intersectedSphere == null){
            return new Color(0.5, 0.5, 0.5);
        }
        if (depth == 0){
            return intersectedSphere.color;
        }
        var intersectionPoint = ray.positionAt(currentLowestT);
        var intersectionNormal = intersectedSphere.center.subtractVector(intersectionPoint).normalized();

        var incomingRayReversed = ray.direction.multiply(-1);
        var outgoingRayDirection = intersectionNormal
                .multiply(2)
                .multiply(intersectionNormal
                        .dotProduct(incomingRayReversed))
                .subtractVector(incomingRayReversed);
        var outgoingRayOrigin = intersectionPoint;
        var outgoingRay = new Ray(outgoingRayOrigin, outgoingRayDirection);


        var futureColor = traceRay(outgoingRay, scene,depth - 1);
        var thisColor = intersectedSphere.color;

        return thisColor.multiply(futureColor);
    }


    static BufferedImage renderSimple(Scene scene, int outputWidth, int outputHeight, int raysPerPixel, int maxDepth) {
        var outputImage = new BufferedImage(outputWidth, outputHeight, BufferedImage.TYPE_INT_RGB);

        var pixelsInImage = outputWidth * outputHeight;
        var totalRays = raysPerPixel * pixelsInImage;
        for (int raysCast = 0; raysCast < totalRays; raysCast++){
            double x = Math.random() * 2d - 1d;
            double y = Math.random() * 2d - 1d;

            var ray = new Ray(new Vector3(0.0, 0.0, 0.0), new Vector3(x, y, 1));
            var pixelColor = traceRay(ray, scene, maxDepth);

            int imageX = Math.toIntExact((int) ((x + 1) * 0.5 * outputImage.getWidth()));
            int imageY = Math.toIntExact((int) ((y + 1) * 0.5 * outputImage.getHeight()));
            outputImage.setRGB(imageX, imageY, pixelColor.asIntegerColor());
        }

        return outputImage;
    }
}
