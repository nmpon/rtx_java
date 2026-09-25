public class AveragePixel {
    float red;
    float green;
    float blue;
    int count;

    AveragePixel(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.count = 0;
    }

    void addPixel(float red, float green, float blue) {
        this.red += red;
        this.green += green;
        this.blue += blue;
        this.count++;
    }

    void addPixel(Color color) {
        this.red = (float) (this.red + color.red);
        this.green = (float) (this.green + color.green);
        this.blue = (float) (this.blue + color.blue);
        this.count++;
    }

    public int asIntegerColor() {
        var red = Math.clamp(this.red / this.count, 0, 1);
        var green = Math.clamp(this.green / this.count, 0, 1);
        var blue = Math.clamp(this.blue / this.count, 0, 1);
        return
                (255 << 24)
                        | ((int) Math.ceil(red * 255) << 16)
                        | ((int) Math.ceil(green * 255) << 8)
                        | ((int) Math.ceil(blue * 255));

    }
}
