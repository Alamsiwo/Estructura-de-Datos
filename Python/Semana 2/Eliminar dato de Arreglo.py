inputArr = [11,21,32,41,51,61]
print("El array es: ")
for i in range(len(inputArr)):
    print(inputArr[i], end= " ")

inputArr.pop(0)



print("\nEl array tras eliminar es:")
for i in range(len(inputArr)):
    print(inputArr[i], end=" ")
