#include <iostream>

void swap(int a[], int i, int j) {
    int temp = a[j];
    a[j] = a[i];
    a[i] = temp;
}

int partition(int a[], int l, int h) {
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

void quickSort(int a[], int l, int h) {
    if (l < h) {
        int pi = partition(a, l, h);
        quickSort(a, l, pi - 1);
        quickSort(a, pi + 1, h);
    }
}


void printArr(int a[], int size) {
    for (int i = 0; i < size; i++)
        std::cout << a[i] << " ";
    std::cout << std::endl;
}


int main() {
    int a[] = { 10, 7, 8, 9, 1, 5 };
    std::cout << "arreglo antes de ordenar: ";
    printArr(a, 6);

    quickSort(a, 0, 5);
    std::cout << "arreglo despues de ordenar: ";
    printArr(a, 6);

    return 0;
}