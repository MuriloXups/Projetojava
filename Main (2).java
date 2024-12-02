import java.util.Arrays;
import java.util.Random;

public class Main {
    static int[] lista = {5, 4, 3, 2, 1};

    public static void main(String[] args) {
        testarAlgoritmos();
    }

  
    public static void bubbleSort(int[] lista) {
        int aux;
        for (int j = 0; j < lista.length - 1; j++) {
            for (int i = 0; i < lista.length - 1; i++) {
                if (lista[i] > lista[i + 1]) {
                    aux = lista[i];
                    lista[i] = lista[i + 1];
                    lista[i + 1] = aux;
                }
            }
        }
    }

    public static void selectionSort(int[] lista) {
        for (int i = 0; i < lista.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < lista.length; j++) {
                if (lista[min] > lista[j]) {
                    min = j;
                }
            }
            if (i != min) {
                int aux = lista[i];
                lista[i] = lista[min];
                lista[min] = aux;
            }
        }
    }

    public static void insertionSort(int[] lista) {
        for (int i = 1; i < lista.length; i++) {
            int key = lista[i];
            int j = i - 1;

            while (j >= 0 && lista[j] > key) {
                lista[j + 1] = lista[j];
                j--;
            }
            lista[j + 1] = key;
        }
    }

    public static void mergeSort(int[] list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);

            merge(list, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) leftArray[i] = arr[left + i];
        for (int j = 0; j < n2; j++) rightArray[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) arr[k++] = leftArray[i++];
            else arr[k++] = rightArray[j++];
        }
        while (i < n1) arr[k++] = leftArray[i++];
        while (j < n2) arr[k++] = rightArray[j++];
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }


    public static void testarAlgoritmos() {
        int[] tamanhos = {100, 1000, 10000, 50000, 100000};
        for (int tamanho : tamanhos) {
            int[] array = gerarArrayAleatorio(tamanho);

            System.out.println("Tamanho: " + tamanho);

            medirTempo("Bubble Sort", () -> bubbleSort(clonarArray(array)));
            medirTempo("Selection Sort", () -> selectionSort(clonarArray(array)));
            medirTempo("Insertion Sort", () -> insertionSort(clonarArray(array)));
            medirTempo("Merge Sort", () -> mergeSort(clonarArray(array), 0, array.length - 1));
            medirTempo("Quick Sort", () -> quickSort(clonarArray(array), 0, array.length - 1));
            medirTempo("Heap Sort", () -> heapSort(clonarArray(array)));
        }
    }

    private static int[] gerarArrayAleatorio(int tamanho) {
        Random random = new Random();
        return random.ints(tamanho, 0, 100000).toArray();
    }

    private static int[] clonarArray(int[] original) {
        return Arrays.copyOf(original, original.length);
    }

    private static void medirTempo(String nome, Runnable algoritmo) {
        long inicio = System.nanoTime();
        algoritmo.run();
        long fim = System.nanoTime();
        System.out.printf("%s levou %.2f ms%n", nome, (fim - inicio) / 1_000_000.0);
    }
}