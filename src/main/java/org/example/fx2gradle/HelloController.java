package org.example.fx2gradle;

import Exceptions.NegativeDimensionException;
import geometry2d.CircleShape;
import geometry2d.Figure;
import geometry2d.RectangleShape;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloController {

    @FXML
    private Canvas canvas; // Поле для рисования

    @FXML
    private HBox buttonContainer; // Контейнер для кнопок

    private final Random random = new Random(); // Генератор случайных чисел
    private final List<Figure> figures = new ArrayList<>(); // Список фигур для рисования
    private Figure selectedFigure; // Выбранная фигура
    private double offsetX, offsetY; // Смещение для перемещения фигуры

    @FXML
    public void initialize() {
        // Инициализация кнопок в контейнере
        for (Node node : buttonContainer.getChildren()) {
            if (node instanceof Button button) {
                button.setOnAction(event -> drawShape(button.getText())); // Установка обработчика события для рисования фигуры
            }
        }

        // Обработчик нажатия мыши на холсте
        canvas.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) { // Левое нажатие
                selectedFigure = getFigureAt(event.getX(), event.getY()); // Получаем фигуру по координатам
                if (selectedFigure != null) {
                    bringToFront(selectedFigure); // Перемещаем фигуру на передний план
                    offsetX = event.getX() - selectedFigure.getX(); // Рассчитываем смещение по X
                    offsetY = event.getY() - selectedFigure.getY(); // Рассчитываем смещение по Y
                }
            } else if (event.isSecondaryButtonDown()) { // Правое нажатие
                Figure figure = getFigureAt(event.getX(), event.getY()); // Получаем фигуру по координатам
                if (figure != null) {
                    Color randomColor = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1.0); // Генерируем случайный цвет
                    figure.setColor(randomColor); // Устанавливаем новый цвет фигуры
                    redrawCanvas(); // Перерисовываем холст
                }
            }
        });

        // Обработчик перетаскивания мыши на холсте
        canvas.setOnMouseDragged(event -> {
            if (selectedFigure != null) {
                selectedFigure.setX(event.getX() - offsetX); // Обновляем координату X фигуры
                selectedFigure.setY(event.getY() - offsetY); // Обновляем координату Y фигуры
                redrawCanvas(); // Перерисовываем холст
            }
        });

        // Обработчик отпускания мыши
        canvas.setOnMouseReleased(event -> selectedFigure = null); // Сбрасываем выбранную фигуру
    }

    //отвечает за создание и рисование фигуры (круга или прямоугольника) на холсте с случайными параметрами.
    private void drawShape(String shapeType) {
        GraphicsContext gc = canvas.getGraphicsContext2D(); // Получаем контекст рисования
        double x = random.nextDouble() * (canvas.getWidth() - 100); // Генерируем случайную координату X
        double y = random.nextDouble() * (canvas.getHeight() - 100); // Генерируем случайную координату Y
        Figure figure; // Фигура для рисования
        Color randomColor = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1.0); // Генерируем случайный цвет

        try {
            switch (shapeType) {
                case "Circle": // Если тип фигуры "Круг"
                    double radius = 20 + random.nextDouble() * 40; // Генерируем случайный радиус
                    figure = new CircleShape(radius, randomColor, x, y); // Создаем объект круга
                    gc.setFill(randomColor); // Устанавливаем цвет заливки
                    gc.fillOval(x, y, radius * 2, radius * 2); // Рисуем круг
                    break;
                case "Rectangle": // Если тип фигуры "Прямоугольник"
                    double width = 40 + random.nextDouble() * 60; // Генерируем случайную ширину
                    double height = 30 + random.nextDouble() * 50; // Генерируем случайную высоту
                    figure = new RectangleShape(width, height, randomColor, x, y); // Создаем объект прямоугольника
                    gc.setFill(randomColor); // Устанавливаем цвет заливки
                    gc.fillRect(x, y, width, height); // Рисуем прямоугольник
                    break;
                default:
                    return; // Если тип фигуры не поддерживается, выходим из метода
            }
            figures.add(figure); // Добавляем фигуру в список
        } catch (NegativeDimensionException e) { // Обработка исключений для негативных размеров
            e.printStackTrace();
        }
    }

    //ищет фигуру, которая содержит заданные координаты, и возвращает её
    private Figure getFigureAt(double x, double y) {
        // Получаем фигуру по координатам
        for (int i = figures.size() - 1; i >= 0; i--) { // Проходим в обратном порядке для правильного выбора
            Figure figure = figures.get(i);
            if (figure.contains(x, y)) { // Проверяем, содержит ли фигура точку (x, y)
                return figure; // Возвращаем найденную фигуру
            }
        }
        return null; // Если фигура не найдена, возвращаем null
    }

    //метод удаляет заданную фигуру из списка фигур и добавляет её снова в конец списка, тем самым перемещая её на передний план.
    private void bringToFront(Figure figure) {
        figures.remove(figure); // Удаляем фигуру из списка
        figures.add(figure); // Добавляем фигуру в конец списка (на передний план)
        redrawCanvas(); // Перерисовываем холст
    }

    //очищает холст и заново рисует все фигуры
    private void redrawCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D(); // Получаем контекст рисования
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Очищаем холст
        for (Figure figure : figures) { // Перебираем все фигуры
            gc.setFill(figure.getColor()); // Устанавливаем цвет заливки фигуры
            if (figure instanceof CircleShape circle) { // Если фигура круг
                gc.fillOval(circle.getX(), circle.getY(), circle.getRadius() * 2, circle.getRadius() * 2); // Рисуем круг
            } else if (figure instanceof RectangleShape rectangle) { // Если фигура прямоугольник
                gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight()); // Рисуем прямоугольник
            }
        }
    }
}
