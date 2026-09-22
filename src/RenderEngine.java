import java.awt.image.BufferedImage;

public class RenderEngine {

    static Color traceRay(Ray ray, Scene scene, int depth) {
        double current_lowest_t = 1e99;
        Sphere intersected_sphere = null;

        for (Sphere sphere : scene.sceneObjects){
            var check_t = ray.Intersect(sphere);
            if (check_t >= 0.0001 && check_t <= current_lowest_t) {
                current_lowest_t = check_t;
                intersected_sphere = sphere;
            }
        }
        if (intersected_sphere == null){
            return new Color(0.5, 0.5, 0.5);
        }
        if (depth == 0){
            return intersected_sphere.color;
        }
        var intersection_point = ray.positionAt(current_lowest_t);
        var intersection_normal = intersected_sphere.center.SubtractVector(intersection_point).Normalized();

        var incoming_ray_reversed = ray.direction.Multiplied(-1);
        var outgoing_ray_direction = intersection_normal
                .Multiplied(2)
                .Multiplied(intersection_normal
                        .DotProduct(incoming_ray_reversed))
                .SubtractVector(incoming_ray_reversed);
        var outgoing_ray = new Ray(intersection_point, outgoing_ray_direction);


        var future_color = traceRay(outgoing_ray, scene,depth - 1);
        var this_color = intersected_sphere.color;

        return this_color.Multiplied(future_color);
    }


    static BufferedImage renderSimple(Scene scene, int outputWidth, int outputHeight, int raysPerPixel, int maxDepth) {
        var outputImage = new BufferedImage(outputWidth, outputHeight, BufferedImage.TYPE_INT_RGB);

        var pixelsInImage = outputWidth * outputHeight;
        var totalRays = raysPerPixel * pixelsInImage;
        for (int raysCast = 0; raysCast < totalRays; raysCast++){
            double x = Math.random() * 2d - 1d;
            double y = Math.random() * 2d - 1d;

            var ray = new Ray(new Vector3(0.0, 0.0, 0.0), new Vector3(x, y, 1));
            var pixel_color = traceRay(ray, scene, maxDepth);

            int image_x = Math.toIntExact((int) ((x + 1) * 0.5 * outputImage.getWidth()));
            int image_y = Math.toIntExact((int) ((y + 1) * 0.5 * outputImage.getHeight()));
            outputImage.setRGB(image_x, image_y, pixel_color.AsIntegerColor());
        }

        return outputImage;
    }
}
