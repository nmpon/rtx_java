public class Vector3 {
    double x, y, z;

    public Vector3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 AddVector(Vector3 other){
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3 SubtractVector(Vector3 other){
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector3 Multiplied(double factor){
        return new Vector3(this.x * factor, this.y * factor, this.z * factor);
    }

    public double DotProduct(Vector3 other){
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3 CrossProduct(Vector3 other){
        return new Vector3(
                this.y * other.z - this.z * other.y,
                this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x
        );
    }

    public double length(){
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    public Vector3 Normalized(){
        double length = this.length();

        return new Vector3(this.x / length, this.y / length, this.z / length);
    }

    public String toString(){
        return "(%.2f, %.2f, %.2f)".formatted(this.x, this.y, this.z);
    }
}
