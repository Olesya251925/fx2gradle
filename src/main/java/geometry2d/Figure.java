package geometry2d;

import javafx.scene.paint.Color;

public abstract class Figure {
    protected double x;  // Координата x
    protected double y;  // Координата y
    protected Color color;  // Цвет фигуры

    public Figure(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // Абстрактный метод для проверки, находится ли точка внутри фигуры
    public abstract boolean contains(double x, double y);

    // Метод для перемещения фигуры на передний план
    public void toFront() {
        // Логика перемещения фигуры на передний план (если необходимо)
    }
}
