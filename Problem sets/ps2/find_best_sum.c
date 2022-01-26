#include<stdio.h>

/*Function to return max sum such that no two elements
are adjacent */
int FindMaxSum_nonDynamic(int arr[], int n){

    int include = arr[0];
    int exclude = 0;
    int opt;
    int i;

    for (i = 1; i < n; i++)
    {

        if(include > exclude){
            opt = include;
        }
        else{
            opt = exclude;
        }

        include = exclude + arr[i];
        exclude = opt;
    }

    if(include > exclude){
        return include;
    }
    else{
        return exclude;
    }
}


int FindMaxSum(int arr[], int n){

    int include = arr[0];
    int exclude = 0;
    int new_exclude;
    int i;

    for (i = 1; i < n; i++)
    {

        if(include > exclude){
            new_exclude = include;
        }
        else{
            new_exclude = exclude;
        }

        include = exclude + arr[i];
        exclude = new_exclude;
    }

    if(include > exclude){
        return include;
    }
    else{
        return exclude;
    }
}

int main(){
    int arr[] = {1, 29, 30, 28, 19, 2, 40, 20};
    int n = sizeof(arr) / sizeof(arr[0]);
    printf("%d n", FindMaxSum(arr, n));
    return 0;
}
