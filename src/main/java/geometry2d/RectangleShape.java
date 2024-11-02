package geometry2d;

import Exceptions.NegativeDimensionException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle; // Импортируем класс Rectangle из javafx.scene.shape

public class RectangleShape implements Figure { // Переименовываем класс в RectangleShape
    private double width; // Ширина прямоугольника
    private double height; // Высота прямоугольника
    private Rectangle javafxRectangle; // JavaFX объект для отображения

    public RectangleShape(double width, double height) throws NegativeDimensionException {
        if (width < 0 || height < 0) {
            throw new NegativeDimensionException("Ширина и высота не могут быть отрицательными."); // Проверка на отрицательные размеры
        }
        this.width = width; // Присваиваем ширину
        this.height = height; // Присваиваем высоту
        this.javafxRectangle = new Rectangle(width, height); // Инициализация JavaFX объекта
        this.javafxRectangle.setFill(Color.color(Math.random(), Math.random(), Math.random())); // Установка случайного цвета
    }

    public Rectangle getShape() {
        return javafxRectangle; // Метод для получения JavaFX объекта
    }

    @Override
    public double area() {
        return width * height; // Вычисление площади прямоугольника
    }

    @Override
    public String toString() {
        return "Прямоугольник с шириной: " + width + " и высотой: " + height; // Строковое представление прямоугольника
    }
}
