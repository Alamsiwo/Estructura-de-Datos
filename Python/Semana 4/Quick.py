def swap(a, i, j):
    a[i], a[j] = a[j], a[i]


def partition(a, l, h):
    pvt = a[h]
    j = l - 1
    for k in range(l, h):
        if a[k] < pvt:
            j += 1
            swap(a, j, k)
    swap(a, j + 1, h)
    return j + 1


def qckSort(a, l, h):
    if l < h:
        pi = partition(a, l, h)
        qckSort(a, l, pi - 1)
        qckSort(a, pi + 1, h)



def printArr(a):
    for i in range(len(a)):
        print(a[i], end = " ")

a = [65, 26, 13, 23, 12]
print("Arreglo antes de ser ordenado: ")
printArr(a)

qckSort(a, 0, len(a) - 1)
print("\nArreglo despues de ser ordenado: ")
printArr(a)