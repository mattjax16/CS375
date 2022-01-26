import numpy as np


def opt(x ,start_in ,end_ind ,n ):



    j = int(np.floor(start_in+(end_ind-start_in)/2))

    if ((j == 0 or x[j - 1] <= x[j]) and
            (j == n - 1 or x[j + 1] <= x[j])):

        return j

    elif (j > 0 and x[j - 1] > x[j]):
        return opt(x, start_in, (j - 1), n)

    else:
        return opt(x, (j + 1), end_ind, n)

def main():

    x_1 = [1,2,3,4,5,6,7,8,9,7,6,5]

    print(opt(x_1,0,len(x_1)-1,len(x_1)))
    return


if __name__ == "__main__":
    main()