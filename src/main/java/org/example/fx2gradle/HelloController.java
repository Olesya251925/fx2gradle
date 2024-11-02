package org.example.fx2gradle;

import Exceptions.NegativeDimensionException;
import geometry2d.CircleShape; // Импортируйте CircleShape вместо Circle
import geometry2d.Figure;
import geometry2d.RectangleShape;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox; // Импортируем HBox
import javafx.scene.paint.Color; // Импортируем класс Color

import java.util.Random;

public class HelloController {

    @FXML
    private Canvas canvas; // Объект для рисования на канвасе

    @FXML
    private HBox buttonContainer; // Изменено на HBox

    private final Random random = new Random(); // Генератор случайных чисел

    @FXML
    public void initialize() {
        // Инициализация кнопок: установка обработчиков событий
        for (Node node : buttonContainer.getChildren()) {
            if (node instanceof Button button) {
                button.setOnAction(event -> drawShape(button.getText())); // Рисуем фигуру в зависимости от текста кнопки
            }
        }
    }

    private void drawShape(String shapeType) {
        GraphicsContext gc = canvas.getGraphicsContext2D(); // Получаем графический контекст для рисования
        double x = random.nextDouble() * canvas.getWidth(); // Случайные координаты
        double y = random.nextDouble() * canvas.getHeight();
        Figure figure; // Объект фигуры

        // Генерация случайного цвета
        Color randomColor = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1.0);
        gc.setFill(randomColor); // Установка цвета заливки

        try {
            switch (shapeType) {
                case "Circle": // Если выбрана окружность
                    double radius = 20 + random.nextDouble() * 80; // Увеличенный радиус от 20 до 100
                    figure = new CircleShape(radius); // Создаем окружность
                    gc.fillOval(x, y, radius, radius); // Рисуем окружность
                    break;
                case "Rectangle": // Если выбрана прямоугольник
                    double width = 50 + random.nextDouble() * 150; // Увеличенная ширина от 50 до 200
                    double height = 30 + random.nextDouble() * 100; // Увеличенная высота от 30 до 130
                    figure = new RectangleShape(width, height); // Создаем прямоугольник
                    gc.fillRect(x, y, width, height); // Рисуем прямоугольник
                    break;
                default:
                    return; // Если фигура неизвестна, выходим из метода
            }
        } catch (NegativeDimensionException e) {
            e.printStackTrace(); // Обработка ошибок
        }
    }
}
