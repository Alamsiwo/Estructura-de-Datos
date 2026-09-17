public class Bubble {

    public static void bubbleSort(int[] array) {
        int s = array.length;
        boolean swapped;
        for(int i = 0; i < s - 1; i++){
            swapped = false;
            for(int j = 0; j < s - i - 1; j++){
                if(array[j] > array[j + 1]){
                    int temp = array[j];
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

    public static void main(String[] args) {
        int[] array = {5, 3, 8, 4, 2};
        System.out.println("Antes de ordenar los elementos del array: ");
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }

        bubbleSort(array);
        
        System.out.println("\nDespues de ordenar los elementos del array: ");
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
    }

}