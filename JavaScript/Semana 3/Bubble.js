function bubbleSort(array) {
    let s = array.length;
    let swapped;
    for(let i = 0; i < s - 1; i++){
        swapped = false;
        for(let j = 0; j < s - i - 1; j++){
            if(array[j] > array[j + 1]){
                let temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
                swapped = true;
            }
        }
        if(!swapped){
            break;
        }
    }
}

function main(){
    let array = [7,23,5,235,4,7];
    console.log("Antes de ordenar los elementos del array: ");
    console.log(array);

    bubbleSort(array);

    console.log("Después de ordenar los elementos del array: ");
    console.log(array);

}

main();