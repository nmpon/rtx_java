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
        if (this.red > 1 ||  this.green > 1 || this.blue > 1){
            throw new RuntimeException("color value too high (>1)");
        }
        if (this.red < 0 ||  this.green < 0 || this.blue < 0){
            throw new RuntimeException("color value too low (<0)");
        }
        return
              (255 << 24)
            | ((int) Math.ceil(this.red * 255) << 16)
            | ((int) Math.ceil(this.green * 255) << 8)
            | ((int) Math.ceil(this.blue * 255));

    }
}
