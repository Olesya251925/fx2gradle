package geometry2d;

import Exceptions.NegativeDimensionException;
import javafx.scene.paint.Color;

public class CircleShape extends Figure {
    private final double radius;

    public CircleShape(double radius, Color color, double x, double y) throws NegativeDimensionException {
        super(x, y, color);
        if (radius < 0) {
            throw new NegativeDimensionException("Радиус не может быть отрицательным");
        }
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean contains(double x, double y) {
        // Проверяем, находится ли точка (x, y) внутри круга
        double dx = x - this.x - radius; // Смещаем центр круга
        double dy = y - this.y - radius;
        return dx * dx + dy * dy <= radius * radius;
    }
}