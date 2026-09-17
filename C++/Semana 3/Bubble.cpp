#include <iostream>
#include <vector>
using namespace std;

void bubbleSort(std::vector<int>& arr){
    int s = arr.size();
        for(int i = 0; i < s-1; i++){
            bool swapped = false;
            for(int j = 0; j < s-i-1; j++){
                if(arr[j] > arr[j+1]){
                    swap(arr[j], arr[j+1]);
                    swapped = true;
                }
            }
            if(!swapped){
                break;
            }
        }
        bool swapped = false;
}




int main() {
    std::vector<int> arr = {15,16,11,13,14};
    cout << "Antes de ordenar los elementos del array: " << endl;
    for(int i = 0; i < arr.size(); i++){
        cout << arr[i] << " ";
    }
    cout << endl;

    bubbleSort(arr);
    cout << "Despues de ordenar los elementos del array: " << endl;
    for(int i = 0; i < arr.size(); i++){
        cout << arr[i] << " ";
    }
    return 0;
}