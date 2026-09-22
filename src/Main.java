import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

Color TraceRay(Ray ray, ArrayList<Sphere> scene, int depth){
    double current_lowest_t = 1e99;
    Sphere intersected_sphere = null;

    for (Sphere sphere : scene){
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
    var outgoing_ray_origin = intersection_point;
    var outgoing_ray = new Ray(outgoing_ray_origin, outgoing_ray_direction);


    var future_color = TraceRay(outgoing_ray, scene,depth - 1);
    var this_color = intersected_sphere.color;

    return this_color.Multiplied(future_color);
}

void main() throws IOException {
    var outputImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
    var raysPerPixel = 40;

    var scene = new ArrayList<Sphere>();
    scene.add(new Sphere(2.0, new Vector3(0.0, 0.0, 10), new Color(.7, .8, .1)));
    scene.add(new Sphere(1.5, new Vector3(2.0, -5.0, 12), new Color(.8, .6, .8)));
    scene.add(new Sphere(2.5, new Vector3(-5.0, 2.0, 8), new Color(.2, .3, .4)));
    scene.add(new Sphere(2.0, new Vector3(4.0, -2.0, 8), new Color(2, 2, 2)));



    for (int raysCast = 0; raysCast < raysPerPixel * outputImage.getWidth() * outputImage.getHeight(); raysCast++){
        double x = Math.random() * 2d - 1d;
        double y = Math.random() * 2d - 1d;

        var ray = new Ray(new Vector3(0.0, 0.0, 0.0), new Vector3(x, y, 1));

        var pixel_color = TraceRay(ray, scene, 3);


        int image_x = Math.toIntExact((int) ((x + 1) * 0.5 * outputImage.getWidth()));
        int image_y = Math.toIntExact((int) ((y + 1) * 0.5 * outputImage.getHeight()));
        outputImage.setRGB(image_x, image_y, pixel_color.AsIntegerColor());
    }

    File outputfile = new File("output.png");
    ImageIO.write(outputImage, "png", outputfile);
}


