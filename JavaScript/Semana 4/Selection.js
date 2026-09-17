function selection(a) {
    for (let i = 0; i < a.length; i++) {
        let small = i;

        for (let j = i + 1; j < a.length; j++) {
            if (a[small] > a[j]) {
                small = j;
            }
        }

        let temp = a[i];
        a[i] = a[small];
        a[small] = temp;
    }
}

function printArr(a) {
    for (let i = 0; i < a.length; i++) {
        process.stdout.write(a[i] + " ");
    }
}

let a = [65, 26, 13, 23, 12];

console.log("Arreglo antes de ser ordenado:");
printArr(a);

selection(a);

console.log("\nArreglo despues de ser ordenado:");
printArr(a);