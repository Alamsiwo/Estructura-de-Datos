#include <iostream>
using namespace std;

void selection(int a[], int n)
{
    for (int i = 0; i < n; i++)
    {
        int small = i;

        for (int j = i + 1; j < n; j++)
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

void printArr(int a[], int n)
{
    for (int i = 0; i < n; i++)
    {
        cout << a[i] << " ";
    }
}

int main()
{
    int a[] = {65, 26, 13, 23, 12};
    int n = 5;

    cout << "Arreglo antes de ser ordenado:" << endl;
    printArr(a, n);

    selection(a, n);

    cout << "\nArreglo despues de ser ordenado:" << endl;
    printArr(a, n);

    return 0;
}