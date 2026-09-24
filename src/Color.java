public class Color {
    double red, green, blue;

    public Color(double red, double green, double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Color Multiplied(Color other){
        return new Color(this.red * other.red, this.green * other.green, this.blue * other.blue);
    }

    public Color Add(Color other){
        return new Color(this.red + other.red, this.green + other.green, this.blue + other.blue);
    }

    public int AsIntegerColor(){
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
