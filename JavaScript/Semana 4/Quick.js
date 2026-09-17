function swap(a, i, j) {
    let temp = a[i];
    a[i] = a[j];
    a[j] = temp;
}

function partition(a, l, h){
    let pivot = a[h];
    let i = l - 1;

    for (let j = l; j <= h - 1; j++) {
        if (a[j] < pivot) {
            i++;
            swap(a, i, j);
        }
    }
    swap(a, i + 1, h);
    return (i + 1);
}

function quickSort(a, l, h) {
    if (l < h) {
        let p = partition(a, l, h);
        quickSort(a, l, p - 1);
        quickSort(a, p + 1, h);
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

quickSort(a, 0, a.length - 1);

console.log("\nArreglo despues de ser ordenado:");
printArr(a);