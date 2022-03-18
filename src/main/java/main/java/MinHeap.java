package main.java;

class MinHeapNode {
    // The value of the node
    int element;
    // Array Index
    int i;

    // Index of the Next Element
    int j;

    public MinHeapNode(int element, int i, int j) {
        this.element = element;
        this.i = i;
        this.j = j;
    }
};

class MinHeap {
    MinHeapNode[] heap_arr;
    int heap_size;


    public MinHeap(MinHeapNode[] heap_arr, int size) {
        this.heap_size = size;
        this.heap_arr = heap_arr;
        int i = (heap_size - 1) / 2;
        while (i >= 0) {
            MinHeapify(i);
            i--;
        }
    }

    void MinHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l < heap_size && heap_arr[l].element < heap_arr[i].element)
            smallest = l;
        if (r < heap_size && heap_arr[r].element < heap_arr[smallest].element)
            smallest = r;
        if (smallest != i) {
            swap(heap_arr, i, smallest);
            MinHeapify(smallest);
        }
    }

    int left(int i) {
        return (2 * i + 1);
    }

    int right(int i) {
        return (2 * i + 2);
    }

    // Function to return the minimum element from heap
    MinHeapNode getMin() {
        if (heap_size <= 0) {
            System.out.println("Heap underflow");
            return null;
        }
        return heap_arr[0];
    }

    // Replace root with new node
    void replaceMin(MinHeapNode root) {
        heap_arr[0] = root;
        MinHeapify(0);
    }

    // A utility function to swap two min heap nodes
    void swap(MinHeapNode[] arr, int i, int j) {
        MinHeapNode temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}