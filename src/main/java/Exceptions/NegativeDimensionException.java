package Exceptions;

//размеры фигуры заданы неверно (отрицательные значения).
public class NegativeDimensionException extends Exception {
    public NegativeDimensionException(String message) {
        super(message);
    }
}
