package Exceptions;

// Исключение для некорректной фигуры
public class InvalidFigureException extends Exception {
    public InvalidFigureException(String message) {
        super(message); // Вызываем конструктор родительского класса с сообщением
    }
}
