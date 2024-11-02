package geometry2d;

import Exceptions.NegativeDimensionException;
import javafx.scene.paint.Color;

public class RectangleShape extends Figure {
    private double width;
    private double height;

    public RectangleShape(double width, double height, Color color, double x, double y) throws NegativeDimensionException {
        super(x, y, color);
        if (width < 0 || height < 0) {
            throw new NegativeDimensionException("Width and height cannot be negative");
        }
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    // Проверяем, находится ли точка внутри прямоугольника
    @Override
    public boolean contains(double x, double y) {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }
}
