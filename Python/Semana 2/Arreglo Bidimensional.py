TwoDimensionalArray = [
  [  [1,2,3],
[4,5,6],
[7,8,9]
],
[
[10,11,12],
[13,14,15],
[16,17,18]
]
]
print("Los elemoentos del array son: ")

for row in TwoDimensionalArray:
    for element in row:
        print(element,end=" ")
    print()