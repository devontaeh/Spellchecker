import java.io.File;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Spell {

    private static final ArrayList<String> wordsToCheck = new ArrayList<>();
    private static final Hashtable<String, Boolean> dictionary = new Hashtable<>();


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


        Hashtable<String, Boolean> dictionary = getDictionary();
        ArrayList<String> wordsToCheck = getWordsToCheck();


        for (int i = 0; i < wordsToCheck.size(); i++) {
            if (!checkSpelling(wordsToCheck.get(i))) {
                suggestCorrections(wordsToCheck.get(i));
            } else {
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


    public static boolean checkSpelling(String word) {

        return dictionary.containsKey(word);
    }

    public static boolean suggestCorrections(String word) {

        System.out.printf("%s: Incorrect Spelling%n", word);
        ArrayList<String> insertion = correctSpellingWithInsertion(word);
        Set<String> suggestedWords = new HashSet<>(insertion);

        System.out.println(correctSpellingWithInsertion(word.toLowerCase()));

//        if (correctSpellingSubstitution(word) != null) {
//            System.out.printf("%s => %s\n", word.toLowerCase(), correctSpellingSubstitution(word));
//        } else if (correctSpellingWithOmission(word) != null) {
//            System.out.printf("%s => %s\n", word.toLowerCase(), correctSpellingWithOmission(word));
//        } else if (!insertion.isEmpty()) {
//            System.out.printf("%s => ", word.toLowerCase());
//            for (String wd : insertion) {
//                System.out.printf("%s ", wd);
//            }
//            System.out.println();
//        } else if (correctSpellingWithReversal(word) != null) {
//            System.out.println();
//            System.out.printf("%s => %s\n", word.toLowerCase(), correctSpellingWithReversal(word));
//        }

        return true;
    }

    // This function takes in a string word and tries to correct the spelling by substituting letters and
    // check if the resulting new word is in the dictionary.
    static String correctSpellingSubstitution(String word) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();


        for (int i = 0; i < word.length(); i++) {

            char[] newWord = word.toLowerCase().toCharArray();

            for (char letter : alphabet) {
                newWord[i] = letter;
                String newWordString = new String(newWord);
                //check if the value is in the dictionary
                if (checkSpelling(newWordString)) {

                    return newWordString;
                }

            }

        }

        return null;
    }

    // This function tries to omit (in turn, one by one) a single character in the misspelled word
    // and check if the resulting new word is in the dictionary.
    static ArrayList<String> correctSpellingWithOmission(String word) {
        ArrayList<String> wordList = new ArrayList<>();

        for (int i = 0; i < word.length() - 1; i++) {
            String omissionWord = word.toLowerCase();
            omissionWord = omissionWord.substring(0, i) + omissionWord.substring(i + 1);
            if (checkSpelling(omissionWord)) {
                wordList.add(omissionWord);
            }
        }


        return wordList;
    }

    //
    // This function tries to insert a letter in the misspelled word
    // and check if the resulting new word is in the dictionary.
    static ArrayList<String> correctSpellingWithInsertion(String word) {
        ArrayList<String> insertion = new ArrayList<>();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();


//        List<Character> wordList = new ArrayList<>()


        //iterate through word to place new char
        for (int i = 0; i < word.length() + 1; i++) {
            //create a word array of length +!
            char[] wordArr = new char[word.length() + 1];

            //place values up to insert point => i
            for (int j = 0; j < i; j++) {

                wordArr[j] = word.toLowerCase().charAt(j);


            }
            for (int k = i; k < wordArr.length - 1; k++) {
                wordArr[k + 1] = word.toLowerCase().charAt(k);

            }
            for (char letter : alphabet) {
                wordArr[i] = letter;
                String subWord = new String(wordArr);
                if (checkSpelling(subWord)) {
                    insertion.add(subWord);
                }
            }


        }


//

        return insertion;

    }


    // This function tries swapping every pair of adjacent characters
    // and check if the resulting new word is in the dictionary.Re
    static ArrayList<String> correctSpellingWithReversal(String word) {
        ArrayList<String> wordList = new ArrayList<>();


        for (int i = 0; i < word.length() - 1; i++) {
            char[] wordArr = word.toCharArray();
            char temp = wordArr[i];
            wordArr[i] = wordArr[i + 1];
            wordArr[i + 1] = temp;
            String reversal = new String(wordArr);
            if (checkSpelling(reversal)) {
                wordList.add(reversal);
            }
        }


        return wordList;
    }

}

