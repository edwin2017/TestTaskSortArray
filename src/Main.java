import java.util.Random;

public class Main {
    private static int maxLength = 100; // Максимальная длина массива
    private static int maxRandomValue = 1000; // Максимальное значение случайного числа

    public static void main(String[] args) {
        int n = 5; // Задайте желаемое количество массивов n (должно быть меньше длины массивов)

        int[][] arrays = generateAndSort(n);
        if (arrays == null) {
            System.out.println("Количество массивов не должно превышать максимальной длины массивов.");
            return;
        }

        for (int i = 0; i < arrays.length; i++) {
            System.out.print("Массив " + i + ": ");
            for (int j = 0; j < arrays[i].length; j++) {
                System.out.print(arrays[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Вот требуемая функция по генерированию и сортировке
    // массивов. Максимальное значение элемента и длина
    // массивов - глобальные переменные. Передаём в функцию только n - количество массивов
    // Считаем номера массивов с 0, четный порядковый номер 0,2,4..
    // Нечетный 1,3,5...
    public static int[][] generateAndSort(int n) {
        if (n > maxLength) {
            return null;
        }

        int[][] arrays = new int[n][];
        int[] usedSizes = new int[n]; // Временный массив для отслеживания уже сгенерированных размеров

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            int size;
            do {
                size = random.nextInt(maxLength) + 1; // Генерируем случайный размер массива от 1 до maxLength
            } while (isSizeUsed(usedSizes, size));

            int[] array = new int[size];
            usedSizes[i] = size;

            for (int j = 0; j < size; j++) {
                array[j] = random.nextInt(maxRandomValue); // Генерируем случайные числа от 0 до maxRandomValue - 1
            }

            arrays[i] = array;
        }

        for (int i = 0; i < arrays.length; i++) {
            if (i % 2 == 0) {
                sortAscending(arrays[i]); // Сортировка по возрастанию
            } else {
                sortDescending(arrays[i]); // Сортировка по убыванию
            }
        }

        return arrays;
    }

    public static void sortAscending(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void sortDescending(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static boolean isSizeUsed(int[] sizes, int size) {
        for (int s : sizes) {
            if (s == size) {
                return true;
            }
        }
        return false;
    }
}
