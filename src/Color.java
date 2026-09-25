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

    public int asIntegerColor() {
        var red = Math.clamp(this.red, 0, 1);
        var green = Math.clamp(this.green, 0, 1);
        var blue = Math.clamp(this.blue, 0, 1);
        return
              (255 << 24)
            | ((int) Math.ceil(red * 255) << 16)
            | ((int) Math.ceil(green * 255) << 8)
            | ((int) Math.ceil(blue * 255));

    }
}
