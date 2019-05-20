package com.sort;

import java.util.LinkedList;

/**
 * @Description:
 * @Author CLD
 * @Date 2019/5/15 20:27
 */
public class sort_ten {

    //冒泡
    public static void Bubble_Sort(int[] arr){
        int temp;
        boolean flag;
        for(int i=0;i<arr.length-1;i++){
            flag = false;
            for(int j=arr.length-1;j>i;j++){
                if(arr[j]<arr[j-1]){
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            if(!flag) break;
        }
    }

    //选择
    public static void Selection_Sort(int[] arr){
        for (int i=0;i<arr.length-1;i++){
            int min_index = i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[min_index])
                    min_index = j;
            }
            if(min_index!=i){
                int temp = arr[i];
                arr[i] = arr[min_index];
                arr[min_index] = temp;
            }
        }
    }

    //插入
    public static void Insert_Sort(int[] arr){
        int temp;
        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j>0;j--){
                if(arr[j]<arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }else {
                    break;
                }
            }
        }
    }

    //归并
    public static void merge_sort(int[]arr,int left,int right){
        if(left<right){
            int middle = (left+right)/2;
            merge_sort(arr,left,middle);
            merge_sort(arr,middle+1,right);
            mergeArray(arr,left,middle,right);
        }
    }
    public static void mergeArray(int[] arr,int left,int middle,int right){
        int i = left;
        int m = middle;
        int j = middle+1;
        int n = right;
        int k = 0;
        int[] temp = new int[right-left+1];
        while(i<=m && j<=n){
            if(arr[i] <= arr[j]){
                temp[k] = arr[i];
                k++;
                i++;
            }else{
                temp[k] = arr[j];
                k++;
                j++;
            }
        }
        while(i<=m){
            temp[k] = arr[i];
            k++;
            i++;
        }
        while(j<=n){
            temp[k] = arr[j];
            k++;
            j++;
        }
        for(int ii=0;ii<k;ii++){
            arr[left + ii] = temp[ii];
        }

    }

    //快排 递归
    public static void quick_sort(int[] arr,int left,int right){
        if(left>=right) return;
        int i = left;
        int j = right;
        int key = arr[i];
        while(i<j){
            while(i<j && arr[j]>=key)
                j--;
            arr[i] = arr[j];
            while (i<j && arr[i]<=key)
                i++;
            arr[j] = arr[i];
        }
        arr[i] = key;
        quick_sort(arr,left,i-1);
        quick_sort(arr,i+1,right);
    }

    //快排 非递归
    public static void quick_sort_2(int[]arr){
        LinkedList<Integer> left = new LinkedList<Integer>();
        LinkedList<Integer> right = new LinkedList<Integer>();
        left.push(0);
        right.push(arr.length-1);
        while(!left.isEmpty()){
            int l = left.pop();
            int r = right.pop();
            int L = l;
            int R = r;
            int key = arr[l];
            while(l<r){
                while(l<r && arr[r]>=key)
                    r--;
                arr[l]=arr[r];
                while(l<r && arr[l]<=key)
                    l++;
                arr[r]=arr[l];
            }
            arr[l] = key;
            if(L<l-1){
                left.push(L);
                right.push(l-1);
            }
            if(R>r+1){
                left.push(r+1);
                right.push(R);
            }
        }
    }

    // 堆排序
    // 最小堆为例
    public static void min_heap_sort(int [] arr){
        MakeMinHeap(arr); //生成最小堆
        for(int i=arr.length-1;i>0;i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            MinHeapFixdown(arr,0,i-1);
        }
    }
    // 从下往上生成小顶堆
    public static void MakeMinHeap(int[] arr){
        for(int i=arr.length/2;i>=0;i--)
            MinHeapFixdown(arr,i,arr.length-1);
    }
    // 将大值沉底
    public static void MinHeapFixdown(int[]arr,int i,int n){
        int j = i*2+1;
        while(j<=n){
            if (j+1<=n && arr[j]>arr[j+1])
                j++;
            if(arr[i]<=arr[j])
                break;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i = j;
            j = i*2+1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,5,3,10,1,8,3,5,4};
        min_heap_sort(arr);
        for(int i : arr){
            System.out.println(i);
        }
    }


}
