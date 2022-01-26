import numpy as np


def lis_2(X):

    n = len(X)

    A = np.zeros((n,n))

    for j in range(0,n-1):
        for k in range(1,j):

            A[j+1, k] = max(A[j+1, k],A[j, k])

            if X[j+1] > X[k]:
                A[j+1,j+1] = max(j+1)

    return



def main():

    X = np.array([10, 22, 9, 33, 21, 50, 41, 60, 80])

    lis_2(X)
    return


if __name__ == "__main__"():
    main()