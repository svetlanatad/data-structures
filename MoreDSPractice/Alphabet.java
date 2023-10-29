public class Alphabet {

    public static ArrayList<Character> completeAlphabetArrayList(ArrayList<Character> arr) {
        ArrayList<Character> alphabet = new ArrayList<>();
        ArrayList<Character> missingLetters = new ArrayList<>();

        char currentLetter = 'z';
        int index = 0;

//The outer while loop iterates from 'z' to 'a', which means it iterates 26 times, making it O(26) ~ O(1).
        while (currentLetter >= 'a' && index < arr.size()) {
            if (arr.get(index) == currentLetter) {
                currentLetter--;
                index++;
            } else {
                missingLetters.add(currentLetter);
                currentLetter--;
            }
        }

        while (currentLetter >= 'a') {
            missingLetters.add(currentLetter);
            currentLetter--;
        }



        return alphabet;
    }
    public static LinkedPositionalList<Character> completeAlphabetPositionalList(LinkedPositionalList<Character> list) {
        LinkedPositionalList<Character> alphabet = new LinkedPositionalList<>();
        LinkedPositionalList<Character> missingLetters = new LinkedPositionalList<>();

        char currentLetter = 'z';

        for (Position<Character> position : list.positions()) {
            char element = position.getElement();
            while (element > currentLetter) {
                missingLetters.addLast(currentLetter);
                currentLetter--;
            }
            if (element == currentLetter) {
                alphabet.addLast(currentLetter);
                currentLetter--;
            }
        }

        while (currentLetter >= 'a') {
            missingLetters.addLast(currentLetter);
            currentLetter--;
        }
        //The outer loop iterates through each position in the list. In the worst case, it iterates
        // through all positions in the list, which is O(n),
        // where n is the number of positions in the list.

        //I know it's wrong, i don't know how to merge missing letters with alphabet, but I tried ;-;

        return alphabet;


    }






}
/**
 * Here is the inefficient way I implemented it which worked
 * public class Alphabet {
 *
 *     public static ArrayList<Character> completeAlphabetArrayList(ArrayList<Character> arr) {
 *         ArrayList<Character> alphabet = new ArrayList<>();
 *         char currentLetter = 'z';
 *
 *         while(currentLetter >= 'a'){
 *
 *             for(char letter : arr){
 *                 if(currentLetter == '`'){
 *                     break;
 *                 }
 *                 if(currentLetter == letter){
 *                 }else{
 *                     alphabet.add(currentLetter);
 *                     currentLetter--;
 *                 }
 *             }
 *         }
 *
 *
 *
 *         return alphabet;
 *     }
 *     public static LinkedPositionalList<Character> completeAlphabetPositionalList(LinkedPositionalList<Character> list){
 *
 *        LinkedPositionalList<Character> alphabet = new LinkedPositionalList<>();
 *         char currentLetter = 'z';
 *
 *         while(currentLetter >= 'a'){
 *
 *             for(char letter : list){
 *                 if(currentLetter == '`'){
 *                     break;
 *                 }
 *                 if(currentLetter == letter){
 *                 }else{
 *                     alphabet.addLast(currentLetter);
 *                     currentLetter--;
 *                 }
 *             }
 *         }
 *
 *
 *
 *         return alphabet;
 *
 *
 *
 *     }
 *
 *
 *     public static void main(String[] args) {
 *
 *         ArrayList<Character> arr = new ArrayList<>();
 *         arr.add('z');
 *         arr.add('d');
 *         arr.add('c');
 *         arr.add('a');
 *         LinkedPositionalList<Character> list = new LinkedPositionalList<>();
 *         list.addFirst('a');
 *         list.addFirst('b');
 *         list.addFirst('z');
 *         LinkedPositionalList<Character> newList = completeAlphabetPositionalList(list);
 *
 *
 *
 *         ArrayList<Character> b = completeAlphabetArrayList(arr);
 *         for (int i = 0; i < b.size(); i++) {
 *             System.out.println(b.get(i));
 *         }
 *         System.out.println("Linked Alphabet: " + newList);
 *
 *     }
 *
 * }
 */



