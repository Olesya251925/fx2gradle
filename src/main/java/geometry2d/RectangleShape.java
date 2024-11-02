package geometry2d;

import Exceptions.NegativeDimensionException;
import javafx.scene.paint.Color;

public class RectangleShape extends Figure {
    private final double width;
    private final double height;

    public RectangleShape(double width, double height, Color color, double x, double y) throws NegativeDimensionException {
        super(x, y, color);
        if (width < 0 || height < 0) {
            throw new NegativeDimensionException("Ширина и высота не могут быть отрицательными");
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

    @Override
    public boolean contains(double x, double y) {
        // Проверяем, находится ли точка (x, y) внутри прямоугольника
        return x >= this.x && x <= this.x + width &&
                y >= this.y && y <= this.y + height;
    }
}