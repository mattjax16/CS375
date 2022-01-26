import numpy as np


def calc_taget(x,X,T):

    n = len(x)


    max_pos_sum = n*X

    A = np.zeros((max_pos_sum*2,n)).astype('int')

    A[max_pos_sum-x[0],0] = 1
    A[max_pos_sum+x[0], 0] = 1

    for j in range(1,n):
        for k in range(0,(max_pos_sum*2)):


            if A[k,j-1] == 1:

                x_j = x[j]

                # adding
                adding = k + x_j
                A[adding,j] = 1
                #subtracting
                subtracting = k - x_j
                A[subtracting, j] = 1

    if A[max_pos_sum+T,n-1] == 1:
        return True, A
    else:
        return False, A


def backtrack_calc(A,x,X,T):
    n = len(x)



    max_pos_sum = n * X
    moves = []
    start_pos = max_pos_sum+T
    for j in range(0,n):

        num = x[n-j-1]

        added = A[start_pos-x[n-j-1],n-j-2]

        subtrated = A[start_pos+x[n-j-1],n-j-2]

        if added == 1:
            moves.append(num)
            start_pos = start_pos-num

        elif subtrated == 1:
            moves.append(-num)
            start_pos = start_pos + num
        else:
            break

    if start_pos > max_pos_sum:
        moves.append(x[0])
    else:
        moves.append(-x[0])
    return moves


def main():

    x_1 = np.array([3,5,1])
    X_1 = 6
    T_1 = 7

    res_1, A_1 = calc_taget(x_1,X_1,T_1)
    print(res_1)
    print(f"moves: {backtrack_calc(A_1,x_1,X_1,T_1)}")
    return


if __name__ == "__main__":
    main()