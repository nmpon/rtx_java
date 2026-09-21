import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

Color TraceRay(Ray ray, ArrayList<Sphere> scene){
    double current_lowest_t = 1e99;
    Sphere intersected_sphere = null;

    for (Sphere sphere : scene){
        var check_t = ray.Intersect(sphere);
        if (check_t >= 0.0001 && check_t <= current_lowest_t) {
            current_lowest_t = check_t;
            intersected_sphere = sphere;
        }
    }

    return new Color(0.8, 0.6, 0.8);
}

void main() throws IOException {
    var outputImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);

    var scene = new ArrayList<Sphere>();
    scene.add(new Sphere(2.0, new Vector3(0.0, 0.0, 10)));

    for (double x = -1.0;  x <= 1.0; x += 0.001) {
        for (double y = -1.0; y <= 1.0; y += 0.001) {
            var ray = new Ray(new Vector3(0.0, 0.0, 0.0), new Vector3(x, y, 1));

            var pixel_color = TraceRay(ray, scene);


            int image_x = Math.toIntExact((int) ((x + 1) * 0.5 * outputImage.getWidth()));
            int image_y = Math.toIntExact((int) ((y + 1) * 0.5 * outputImage.getHeight()));
//            IO.println(Integer.toBinaryString(pixel_color.AsIntegerColor()));
            outputImage.setRGB(image_x, image_y, pixel_color.AsIntegerColor());

            /*
            var t_intersection = ray.Intersect(sphere);
            if (t_intersection < 0.0001) {
                continue;
            }

            var hit_point = ray.origin.AddVector(ray.direction.Multiplied(t_intersection));
            var sphere_hit_normal = hit_point.SubtractVector(sphere.center).Normalized();

            // rotate incoming ray around normal
            var m = ray.direction.Multiplied(-1);
            var n = sphere_hit_normal;
            var u = n.Multiplied(2).Multiplied(n.DotProduct(m)).SubtractVector(m);
            */
        }
    }

    File outputfile = new File("output.png");
    ImageIO.write(outputImage, "png", outputfile);
}


