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
        if (intersectedSphere == null) {
            return scene.environmentColor;
        }
        if (depth == 0) {
            return new Color(0, 0, 0);
        }
        var intersectionPoint = ray.positionAt(currentLowestT);
        var intersectionNormal = intersectedSphere.center.subtractVector(intersectionPoint).normalized();

        var outgoingRayDirection = Vector3.randomUnitVector();
        var outgoingRayOrigin = intersectionPoint;
        var outgoingRay = new Ray(outgoingRayOrigin, outgoingRayDirection);

        var futureColor = traceRay(outgoingRay, scene, depth - 1);

        return intersectedSphere.shader.calculateColor(ray.direction, outgoingRayDirection, intersectionNormal, futureColor);
    }


    static BufferedImage renderSimple(Scene scene, int outputWidth, int outputHeight, int raysPerPixel, int maxDepth) {
        var outputImage = new Image(outputWidth, outputHeight);

        var aspectRatio = (float) outputWidth / (float) outputHeight;

        // x and y ranges in one direction (0, ...)
        var halfHeight = Math.tan(scene.camera.verticalFieldOfView / 2);
        var halfWidth = halfHeight * aspectRatio;

        long totalRays = (long) outputWidth * outputHeight * raysPerPixel;
        for (long raysCast = 0; raysCast < totalRays; raysCast++) {
            double x = (Math.random() * 2 - 1) * halfWidth;
            double y = (Math.random() * 2 - 1) * halfHeight;

            var ray = new Ray(new Vector3(0.0, 0.0, 0.0), new Vector3(x, y, 1));
            var pixelColor = traceRay(ray, scene, maxDepth);

            int imageX = Math.toIntExact((int) ((x / halfWidth + 1) * 0.5 * outputWidth));
            int imageY = Math.toIntExact((int) ((y / halfHeight + 1) * 0.5 * outputHeight));
            outputImage.writePixel(imageX, imageY, pixelColor);

            if (raysCast % 1_000_000 == 0) {
                IO.print("\r%e/%e (%.2f)".formatted((double) raysCast, (double) totalRays, ((double) raysCast / totalRays * 100d)));
            }
        }

        return outputImage.getImage();
    }
}
 