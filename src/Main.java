import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = 4;

        System.out.print("Введіть розмір матриці (додатнє число): ");
        if (scanner.hasNextInt()) {
            size = scanner.nextInt();
            if (size <= 0) {
                System.out.println("Розмір матриці повинен бути додатнім числом. " +
                        "Використовується стандартне значення 4.");
            }
        } else {
            System.out.println("Неправильний формат введення. " +
                    "Використовується стандартне значення 4.");
            scanner.next();
        }

        int[][] matrix = initializeMatrix(size);
        System.out.println("Матриця:");
        printMatrix(matrix);

        int[] rowSums = calculateRowSums(matrix);
        int[] colProducts = calculateColProducts(matrix);

        System.out.println("Сума елементів в парних рядках (0, 2): " + rowSums[0]);
        System.out.println("Сума елементів в непарних рядках (1, 3): " + rowSums[1]);
        System.out.println("Добуток елементів в парних стовпцях (0, 2): " + colProducts[0]);
        System.out.println("Добуток елементів в непарних стовпцях (1, 3): " + colProducts[1]);

        System.out.println(isMagicSquare(matrix) ? "Матриця є магічним квадратом." :
                "Матриця не є магічним квадратом.");

        scanner.close();
    }

    public static int[][] initializeMatrix(int size) {
        Random rand = new Random();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rand.nextInt(50) + 1;
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }

    public static int[] calculateRowSums(int[][] matrix) {
        int evenRowSum = 0;
        int oddRowSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i % 2 == 0) {
                    evenRowSum += matrix[i][j];
                } else {
                    oddRowSum += matrix[i][j];
                }
            }
        }
        return new int[]{evenRowSum, oddRowSum};
    }

    public static int[] calculateColProducts(int[][] matrix) {
        int evenColProduct = 1;
        int oddColProduct = 1;
        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                if (j % 2 == 0) {
                    evenColProduct *= matrix[i][j];
                } else {
                    oddColProduct *= matrix[i][j];
                }
            }
        }
        return new int[]{evenColProduct, oddColProduct};
    }

    public static boolean isMagicSquare(int[][] matrix) {
        int size = matrix.length;
        int magicSum = 0;

        for (int i = 0; i < size; i++) {
            magicSum += matrix[0][i];
        }

        for (int[] row : matrix) {
            int rowSum = 0;
            for (int value : row) {
                rowSum += value;
            }
            if (rowSum != magicSum) return false;
        }

        for (int j = 0; j < size; j++) {
            int colSum = 0;
            for (int i = 0; i < size; i++) {
                colSum += matrix[i][j];
            }
            if (colSum != magicSum) return false;
        }

        int diagSum1 = 0, diagSum2 = 0;
        for (int i = 0; i < size; i++) {
            diagSum1 += matrix[i][i];
            diagSum2 += matrix[i][size - 1 - i];
        }

        return diagSum1 == magicSum && diagSum2 == magicSum;
    }
}
