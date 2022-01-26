def count_mega_inverssions(arr, n):
 
    mega_inv_count = 0
    for i in range(n):
        for j in range(i + 1, n):
            if (arr[i] > arr[j]):
                mega_inv_count += 1
 
    return mega_inv_count