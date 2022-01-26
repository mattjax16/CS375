import numpy as np


def find_best_sum_dynamic(arr):
    opt_sum = np.zeros(len(arr))

    return rec_helper(arr,opt_sum)





def rec_helper(arr,opt_sum):
    arr_size = len(arr)

    # if opt_sum[arr_size-1] != 0:
        # return(opt_sum[arr_size-1])

    if (arr_size == 0):
        return 0
    elif (arr_size == 1):
        return arr[0]
    elif (arr_size == 2):
        return max(arr[0],arr[1])

    else:

        exculude = rec_helper(arr[1:],opt_sum)
        include = arr[0] + rec_helper(arr[2:],opt_sum)

        opt_sum[arr_size-1] =  max(include,exculude)

        return opt_sum[arr_size-1]

if __name__ == '__main__':
    arr = [1, 29, 30, 28, 19, 2, 40, 20]

    print(find_best_sum_dynamic(arr))
