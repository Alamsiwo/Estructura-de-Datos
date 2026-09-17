using System;

class Program
{
    static void selection(int[] a)
    {
        for (int i = 0; i < a.Length; i++)
        {
            int small = i;

            for (int j = i + 1; j < a.Length; j++)
            {
                if (a[small] > a[j])
                {
                    small = j;
                }
            }

            int temp = a[i];
            a[i] = a[small];
            a[small] = temp;
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

        selection(a);

        Console.WriteLine("\nArreglo despues de ser ordenado:");
        printArr(a);
    }
}