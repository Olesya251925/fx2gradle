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
    private Canvas canvas;

    @FXML
    private HBox buttonContainer;

    private final Random random = new Random();
    private final List<Figure> figures = new ArrayList<>();
    private Figure selectedFigure;
    private double offsetX, offsetY;

    @FXML
    public void initialize() {
        for (Node node : buttonContainer.getChildren()) {
            if (node instanceof Button button) {
                button.setOnAction(event -> drawShape(button.getText()));
            }
        }

        canvas.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                selectedFigure = getFigureAt(event.getX(), event.getY());
                if (selectedFigure != null) {
                    bringToFront(selectedFigure);
                    offsetX = event.getX() - selectedFigure.getX();
                    offsetY = event.getY() - selectedFigure.getY();
                }
            } else if (event.isSecondaryButtonDown()) {
                Figure figure = getFigureAt(event.getX(), event.getY());
                if (figure != null) {
                    Color randomColor = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1.0);
                    figure.setColor(randomColor);
                    redrawCanvas();
                }
            }
        });

        canvas.setOnMouseDragged(event -> {
            if (selectedFigure != null) {
                selectedFigure.setX(event.getX() - offsetX);
                selectedFigure.setY(event.getY() - offsetY);
                redrawCanvas();
            }
        });

        canvas.setOnMouseReleased(event -> {
            selectedFigure = null;
        });
    }

    private void drawShape(String shapeType) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double x = random.nextDouble() * (canvas.getWidth() - 100); // Уменьшаем область для рисования
        double y = random.nextDouble() * (canvas.getHeight() - 100);
        Figure figure;
        Color randomColor = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1.0);

        try {
            switch (shapeType) {
                case "Circle":
                    double radius = 20 + random.nextDouble() * 40;
                    figure = new CircleShape(radius, randomColor, x, y);
                    gc.setFill(randomColor);
                    gc.fillOval(x, y, radius * 2, radius * 2);
                    break;
                case "Rectangle":
                    double width = 40 + random.nextDouble() * 60;
                    double height = 30 + random.nextDouble() * 50;
                    figure = new RectangleShape(width, height, randomColor, x, y);
                    gc.setFill(randomColor);
                    gc.fillRect(x, y, width, height);
                    break;
                default:
                    return;
            }
            figures.add(figure);
        } catch (NegativeDimensionException e) {
            e.printStackTrace();
        }
    }

    private Figure getFigureAt(double x, double y) {
        for (int i = figures.size() - 1; i >= 0; i--) {
            Figure figure = figures.get(i);
            if (figure.contains(x, y)) {
                return figure;
            }
        }
        return null;
    }

    private void bringToFront(Figure figure) {
        figures.remove(figure);
        figures.add(figure);
        redrawCanvas();
    }

    private void redrawCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Figure figure : figures) {
            gc.setFill(figure.getColor());
            if (figure instanceof CircleShape circle) {
                gc.fillOval(circle.getX(), circle.getY(), circle.getRadius() * 2, circle.getRadius() * 2);
            } else if (figure instanceof RectangleShape rectangle) {
                gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
            }
        }
    }
}