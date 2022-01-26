import numpy as np


def opt_slack_1(X,W):

    n = len(X)

    # A = np.ones((W,N)) * np.inf
    A = np.ones((n, W)).astype('int') * np.inf

    n = n-1

    for j in range(0,n+1):
        for k in range(0,W):

            w_j =  X[(n-j)]

            if j == 0:
                if w_j > k:
                    A[(n-j),k] = (k**2) + ((W - w_j)**2)
                else:
                    A[(n-j),k] = (k -w_j)**2

            else:
                if w_j > k:
                    A[(n-j),k] = (k**2) + A[(n-j)+1,W - w_j]
                else:
                    A[(n-j),k] = min( ((k**2) + A[(n-j)+1,W - w_j]), A[(n-j)+1,k - w_j])



    return min(A[0,:])


def opt_slack_2(X,W):

    n = len(X)

    A = np.zeros((2,n))

    # the first best possible slack always is
    # just the slack of that item in the box
    A[1,0] = (W - X[0])**2


    for j in range(1,n):

        k = j
        while sum(X[k:j+1]) <= W and k >= 0:

            if k == j:
                # set the best possible slack to the
                # item being in its own box
                A[1,j] = (W - X[j])**2 + A[1,j-1]
            else:
                t = sum(X[k:j+1])
                ns_1 = (W - sum(X[k:j+1]))**2
                if k == 0:

                    new_slack = ns_1
                else:
                    ns_2 = A[1,k-1]
                    new_slack = ns_1 + ns_2

                if A[1,j] > new_slack:
                    A[0,j] = k
                    A[1,j] = new_slack


            k = k-1

    return A[1,n-1]




def main():

    X_1 = np.array([1,4,2,3])
    W_1 = 5
    X_2 = np.array([2, 6, 4, 3])
    W_2 =13
    print(opt_slack_2(X_1,W_1))
    return


if __name__ == "__main__":
    main()