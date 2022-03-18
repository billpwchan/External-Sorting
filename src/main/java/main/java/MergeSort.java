package main.java;

public class MergeSort {
    static void mergeSort(int[] inputArray) {
        int arrayLength = inputArray.length;

        if (arrayLength < 2) {
            return;
        }

        int midPoint = arrayLength / 2;
        int[] leftArray = new int[midPoint];
        int[] rightArray = new int[arrayLength - midPoint];

        System.arraycopy(inputArray, 0, leftArray, 0, midPoint);
        if (arrayLength - midPoint >= 0)
            System.arraycopy(inputArray, midPoint, rightArray, 0, arrayLength - midPoint);

        mergeSort(leftArray);
        mergeSort(rightArray);

        merge(inputArray, leftArray, rightArray);
    }

    static void merge(int[] inputArray, int[] leftArray, int[] rightArray) {
        int leftArrayLength = leftArray.length;
        int rightArrayLength = rightArray.length;

        int x = 0;
        int y = 0;
        int z = 0;

        while (x < leftArrayLength && y < rightArrayLength) {
            if (leftArray[x] <= rightArray[y]) {
                inputArray[z] = leftArray[x];
                x++;
            } else {
                inputArray[z] = rightArray[y];
                y++;
            }
            z++;
        }

        while (x < leftArrayLength) {
            inputArray[z] = leftArray[x];
            x++;
            z++;
        }

        while (y < rightArrayLength) {
            inputArray[z] = rightArray[y];
            y++;
            z++;
        }

    }
}