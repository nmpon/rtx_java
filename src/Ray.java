public class Ray {
    Vector3 origin;
    Vector3 direction;

    public Ray(Vector3 _origin, Vector3 _direction){
        this.origin = _origin;
        this.direction = _direction.Normalized();
    }

    public double Intersect(Sphere sphere){
        // first determine the discriminant
        // if D > 0 determine the solutions of
        // the equation for t using abc formula
        // then intersection = min(t0, t1) where t is a rational number and t > 0
        var center = sphere.center;
        var radius = sphere.radius;


        double a = direction.DotProduct(direction);
        double b = 2 * direction.DotProduct(origin) - 2 * center.DotProduct(direction);
        double c = origin.DotProduct(origin) - 2 * center.DotProduct(origin) + center.DotProduct(center) - Math.pow(radius, 2);
        double D = Math.pow(b, 2) - 4 * a * c;

        if (D < 0){
            return 0;
        }

        double t0 = (-b + Math.sqrt(D)) / (2 * a);
        double t1 = (-b - Math.sqrt(D)) / (2 * a);

        return Math.min(Math.max(0, t0), Math.max(0, t1));
    }
}
