public class Color {
    double red;
    double green;
    double blue;

    public Color(double red, double green, double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Color multiply(Color other) {
        return new Color(this.red * other.red, this.green * other.green, this.blue * other.blue);
    }

    public Color mix(Color other, double weight) {
        // mixed using x = (1-w) * this + (w-1) * other
        // 0 <= x <= 1
        // higher x -> more other color
        return new Color(
                this.red * (1 - weight) + other.red * (weight - 1),
                this.green * (1 - weight) + other.green * (weight - 1),
                this.blue * (1 - weight) + other.blue * (weight - 1)
        );
    }

    public Color add(Color other) {
        return new Color(this.red + other.red, this.green + other.green, this.blue + other.blue);
    }
}
