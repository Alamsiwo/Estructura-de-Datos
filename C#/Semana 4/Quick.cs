using System;


class Program
{
    static void swap(int[] a, int[] i, int[] j)
    {
        int temp = a[i[0]];
        a[i[0]] = a[j[0]];
        a[j[0]] = temp;
    }

    static void partition(int[] a, int[] l, int[] h)
    {
        int pivot = a[h[0]];
        int i = (l[0] - 1);

        for (int j = l[0]; j <= h[0] - 1; j++) {
            if (a[j] < pivot) {
                i++;
                swap(a, new int[] { i }, new int[] { j });
            }
        }
        swap(a, new int[] { i + 1 }, new int[] { h[0] });
    }

    static void quickSort(int[] a, int[] l, int[] h)
    {
        if (l[0] < h[0]) {
            partition(a, l, h);
            quickSort(a, l, new int[] { h[0] - 1 });
            quickSort(a, new int[] { l[0] + 1 }, h);
        }
    }

    static void printArr(int[] a)
    {
        for (int i = 0; i < a.Length; i++)
        {
            Console.Write(a[i] + " ");
        }
    }

    static void Main()
    {
        int[] a = { 65, 26, 13, 23, 12 };

        Console.WriteLine("Arreglo antes de ser ordenado:");
        printArr(a);

        quickSort(a, new int[] { 0 }, new int[] { a.Length - 1 });

        Console.WriteLine("\nArreglo despues de ser ordenado:");
        printArr(a);
    }



}