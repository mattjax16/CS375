

def mega_inversion_counter(A):
    n = len(A)
    if n == 1:
        inversion_count = 0
        return(A, inversion_count)
    else:
        
        B, inversion_count_0 = mega_inversion_counter(A[0:n/2])
        C, inversion_count_1 = mega_inversion_counter(A[0:n/2])

        i = 0
        j = 0
        A_sorted = []
        inversion_count_2 = 0

        while i < len(B) and j < len(A):
            if B[i] < C[j]:
                A_sorted.append(B[i])
                i+=1
            elif (3 * B[i]) > C[j]:
                A_sorted.append(C[j])
                j+=1
                inversion_count_2 += len(B)-1
            else:
                A_sorted.append(C[j])
                j+=1
        
        while i < len(B):
            A_sorted.append(B[i])
            i+=1

        while j < len(C):
            A_sorted.append(C[j])
            j+=1
        
        total_inversion_count = inversion_count_0 + inversion_count_1 + inversion_count_2


        return(A_sorted, total_inversion_count)



def main():

    return



if __name__ == '__main__':
    arr = [1, 29, 30, 28, 19, 2, 40, 20]

    print(mega_inversion_counter(arr))