package array;

public class SecondLargestElementInArray {

    public static void getSecondLargestElement(int[] arr){

        int largest=arr[0];
        int secondLargest=arr[1];
        if(arr[1]>arr[0]){
            largest=arr[1];
            secondLargest=arr[0];
        }
        for(int i=2; i<arr.length; i++){

            if(arr[i]>largest ){
                secondLargest=largest;
                largest=arr[i];

            }else if(arr[i]>secondLargest){
                secondLargest=arr[i];

            }

        }
        System.out.println(secondLargest);
    }
    public static void main(String[] args) {
        int arr[] = {-1,19,10, 12, 20, 4, 15,14,17};
        SecondLargestElementInArray.getSecondLargestElement(arr);

    }
}
