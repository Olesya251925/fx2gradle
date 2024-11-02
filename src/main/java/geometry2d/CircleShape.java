package geometry2d;

import Exceptions.NegativeDimensionException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle; // Импортируем класс Circle из javafx.scene.shape

public class CircleShape implements Figure { // Переименовываем класс в CircleShape
    private double radius; // Радиус окружности
    private Circle javafxCircle; // JavaFX объект для отображения

    public CircleShape(double radius) throws NegativeDimensionException {
        if (radius < 0) {
            throw new NegativeDimensionException("Радиус не может быть отрицательным."); // Проверка на отрицательный радиус
        }
        this.radius = radius; // Присваиваем радиус
        this.javafxCircle = new Circle(radius); // Инициализация JavaFX объекта
        this.javafxCircle.setFill(Color.color(Math.random(), Math.random(), Math.random())); // Установка случайного цвета
    }

    public Circle getShape() {
        return javafxCircle; // Метод для получения JavaFX объекта
    }

    @Override
    public double area() {
        return Math.PI * radius * radius; // Вычисление площади окружности
    }

    @Override
    public String toString() {
        return "Окружность с радиусом: " + radius; // Строковое представление окружности
    }
}
