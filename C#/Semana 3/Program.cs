namespace Bubble{
    class Program{
        public static void BubbleSort(int[] arr){
            int s = arr.Length;
            for(int i = 0; i < s - 1; i++){
                for(int j = 0; j < s - i - 1; j++){
                    if(arr[j] > arr[j + 1]){
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }



        static void Main(string[] args){
            int[] a = { 64, 34, 25, 12, 22, 11, 90 };
            Console.WriteLine("Antes de ordenar: ");
            foreach(int x in a){
                Console.Write(x + " ");
            }

            BubbleSort(a);
            
            Console.WriteLine("\nDespués de ordenar: ");
            foreach(int x in a){
                Console.Write(x + " ");
            }

        }
    }
}