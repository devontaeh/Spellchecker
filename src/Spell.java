import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Spell {

    private static ArrayList<String> wordsToCheck = new ArrayList<>();
    private static Hashtable<String, Boolean> dictionary = new Hashtable<>();


    Spell(Scanner scanDictionary, Scanner scanWordsToCheck) throws FileNotFoundException {

        //creating dictionary hashtable
        while (scanDictionary.hasNextLine()) {
            dictionary.put(scanDictionary.nextLine(), true);
        }

        //creating arrayList of fileToCheck
        while (scanWordsToCheck.hasNextLine()) {
            wordsToCheck.add(scanWordsToCheck.nextLine());
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanDictionary = new Scanner(new File("C:\\Users\\devon\\Documents\\CS2210\\A2_test\\toyDictionary.txt"));
        Scanner scanWordsToCheck = new Scanner(new File("C:\\Users\\devon\\Documents\\CS2210\\A2_test\\fileToCheck.txt"));
        Spell spell = new Spell(scanDictionary, scanWordsToCheck);


        Hashtable<String, Boolean> dictionary = spell.getDictionary();
        ArrayList<String> wordsToCheck = spell.getWordsToCheck();


        for(int i = 0; i<wordsToCheck.size(); i++){
            if(!checkSpelling(wordsToCheck.get(i))){
                suggestCorrections(wordsToCheck.get(i));
            }else{
                System.out.printf("%s: Correct Spelling\n", wordsToCheck.get(i));
            }
        }

    }



    //check if the dictionary loaded
    public static Hashtable<String, Boolean> getDictionary() {
        return dictionary;
    }

    //check if the words to check list is loaded
    public static ArrayList<String> getWordsToCheck() {
        return wordsToCheck;
    }


    public static boolean checkSpelling(String word){

        return dictionary.containsKey(word);
    }

    public static boolean suggestCorrections(String word) {




        System.out.printf("%s: Incorrect Spelling%n", word);

        if(correctSpellingSubstitution(word) != null){
            System.out.printf("%s => %s\n",word.toLowerCase(), correctSpellingSubstitution(word));
        } else if (correctSpellingWithOmission(word) != null) {
            System.out.printf("%s => %s\n",word.toLowerCase(), correctSpellingWithOmission(word));
        }



//        correctSpellingWithReversal(word);
//        correctSpellingWithInsertion(word);



        return true;
    }

    // This function takes in a string word and tries to correct the spelling by substituting letters and
    // check if the resulting new word is in the dictionary.
    static String correctSpellingSubstitution(String word) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();


        for (int i = 0; i < word.length(); i++){

            char[] newWord = word.toLowerCase().toCharArray();

            for(char letter : alphabet ){
                newWord[i] = letter;
                String newWordString = new String(newWord);
                //check if the value is in the dictionary
                if(checkSpelling(newWordString)){

                    return newWordString;
                }

            }

        }

    return null;
    }

    // This function tries to omit (in turn, one by one) a single character in the misspelled word
    // and check if the resulting new word is in the dictionary.
    static String correctSpellingWithOmission(String word) {


        for(int i=0; i<word.length();i++){
            ArrayList<String> list = new ArrayList<>(Arrays.asList(word.toLowerCase().split("")));

            list.remove(i);
            String omission = String.join("",list);

            if(checkSpelling(omission)){

                return omission;

            }

        }

        return null;
    }
//
//    // This function tries to insert a letter in the misspelled word
//    // and check if the resulting new word is in the dictionary.
//    static ArrayList<String> correctSpellingWithInsertion(String word) {
//        // add your code here
//    }
//
//    // This function tries swapping every pair of adjacent characters
//    // and check if the resulting new word is in the dictionary.
//    static String correctSpellingWithReversal(String word) {
//        // add your code here
//    }

}

