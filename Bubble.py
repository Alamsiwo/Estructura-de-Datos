def bubblesort(a):
    s = len(a)
    for j in range(s):
        isSwapped = False
        for j in range(0, s - j - 1):
            if a[j] > a[j + 1]:
                a[j], a[j + 1] = a[j + 1], a[j]
                isSwapped = True
        if (isSwapped == False):
            break
if __name__ == "__main__":
    a = [15,16,11,13,14]
    print("Antes de ordenar los elementos del array: ")
    for j in a:
        print(j, end=' ')
    
    bubblesort(a)
    print("\nDespues de ordenar los elementos del array: " )
    for j in range(len(a)):
        print("%d" % a[j], end=" ")