/**
**Disclaimer
 * I forgot to ask if I needed to explain my codes, so I decided to explain them all anyway. (´∩｡• ᵕ •｡∩`)
 */
public class SuffixProducts {

    public static boolean example(int[] first, int[] second) {

        int n = first.length, total = 1;

        for (int j = n - 1; j >= 0; j--) {

            for (int k = n - 1; k >= j; k--) {
     //1           //here, it does a complete loop from n-1 to 0, so it runs n times. In the second loop, it runs 1+2+3...+n times, which is equal to n*(n+1)/2,which is quadratic since it contains n*n in there. Therefore, the complexity is ~O(n^2)
                total *= first[k];

            }
        }

        for (int i = 0; i < n; i++) {

            if (second[i] == total) {

                return true;
      //2          //worst case scenario, here, it should check until i = n-1, so the complexity is n(linear), ~O(n)
            }
        }
        return false;
    }
// a) from 1 and 2 we can know, that the  complexity would be n^2 + n therefore would be ~ O(n^2), so the complexity would be quadratic
//b
    public static boolean fastVersion(int[] first, int[] second) {

        int[] a = new int[first.length]; //in 'a', we store suffix products as an array

        //This method is better since it runs in linear time, O(n)
        int product = 1;

        for (int i = first.length - 1; i >= 0; i--) {

            product *= first[i];

            a[i] = product;
        } //runs O(n1), where n1 is the length of first array

        int arrayProducts = 1;

        for(int i = 0; i < a.length; i++){

            arrayProducts*=a[i];
        }

        //runs O(n1)
        for(int i = 0; i<second.length; i++){

            if(second[i] == arrayProducts){

                return true;
            }
        }
        //runs O(n2), where n2 is the length of second array
        //In total, runs in n1+n2+n3 amount of time (time complexity)
        //Since n1=n2=n3 then it runs in 3n amount of time
        //therefore yielding the big-oh complexity of ~ O(n), so the complexity is linear, which I agree is not the best, but it's
        //better than quadratic
       return false;
    }

}


