public class Ray {
    Vector3 origin;
    Vector3 direction;

    public Ray(Vector3 origin, Vector3 direction) {
        this.origin = origin;
        this.direction = direction.Normalized();
    }

    public double intersectRay(Sphere sphere) {
        // first determine the discriminant
        // if D > 0 determine the solutions of
        // the equation for t using abc formula
        // then intersection = min(t0, t1) where t is a rational number and t > 0
        var center = sphere.center;
        var radius = sphere.radius;


        double a = direction.DotProduct(direction);
        double b = 2 * direction.DotProduct(origin) - 2 * center.DotProduct(direction);
        double c = origin.DotProduct(origin)
            - 2 * center.DotProduct(origin)
            + center.DotProduct(center)
            - Math.pow(radius, 2);
        double determinant = Math.pow(b, 2) - 4 * a * c;

        if (determinant < 0) {
            return 0;
        }

        double t0 = (-b + Math.sqrt(determinant)) / (2 * a);
        double t1 = (-b - Math.sqrt(determinant)) / (2 * a);

        return Math.min(Math.max(0, t0), Math.max(0, t1));
    }

    public Vector3 positionAt(double t) {
        return this.origin.AddVector(this.direction.Multiplied(t));
    }
}
