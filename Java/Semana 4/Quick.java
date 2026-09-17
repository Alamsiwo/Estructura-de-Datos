public class Quick {

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static int partition(int[] a, int l, int h) {
        int pivot = a[h];
        int i = (l - 1);

        for (int j = l; j <= h - 1; j++) {
            if (a[j] < pivot) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, h);
        return (i + 1);
    }

    public static void quickSort(int[] a, int l, int h) {
        if (l < h) {
            int pi = partition(a, l, h);
            quickSort(a, l, pi - 1);
            quickSort(a, pi + 1, h);
        }
    }

    public static void printArr(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        
        }
    }

    public static void main(String[] args) {
        int[] a = {65, 26, 13, 23, 12};

        System.out.println("Arreglo antes de ser ordenado:");
        printArr(a);

        quickSort(a, 0, a.length - 1);

        System.out.println("\nArreglo despues de ser ordenado:");
        printArr(a);
    }
}