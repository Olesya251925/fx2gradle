package geometry2d;

import Exceptions.NegativeDimensionException;
import javafx.scene.paint.Color;

public class CircleShape extends Figure {
    private double radius;

    public CircleShape(double radius, Color color, double x, double y) throws NegativeDimensionException {
        super(x, y, color);
        if (radius < 0) {
            throw new NegativeDimensionException("Radius cannot be negative");
        }
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    // Проверяем, находится ли точка внутри окружности
    @Override
    public boolean contains(double x, double y) {
        double dx = this.x - x;
        double dy = this.y - y;
        return dx * dx + dy * dy <= radius * radius;
    }
}
