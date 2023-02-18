import java.io.File;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
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


        Hashtable<String, Boolean> dictionary = spell.dictionary;
        ArrayList<String> wordsToCheck = spell.wordsToCheck;


        for (int i = 0; i < wordsToCheck.size(); i++) {
            if (!checkSpelling(wordsToCheck.get(i))) {
                suggestCorrections(wordsToCheck.get(i));
            } else {
                System.out.printf("%s: Correct Spelling\n", wordsToCheck.get(i));
            }
        }

    }


    //check if the dictionary loaded
//    public static Hashtable<String, Boolean> getDictionary()  {
//
//        return dictionary;
//    }
//
//    //check if the words to check list is loaded
//    public static ArrayList<String> getWordsToCheck() {
//        return wordsToCheck;
//    }


    public static boolean checkSpelling(String word) {

        return dictionary.containsKey(word);
    }

    public static Set<String> suggestCorrections(String word) {

        System.out.println(word.toLowerCase() + ": Incorrect Spelling");


        Set<String> suggestedWords = new HashSet<>();

        suggestedWords.addAll(correctSpellingSubstitution(word));
        suggestedWords.addAll(correctSpellingWithInsertion(word));
        suggestedWords.addAll(correctSpellingWithOmission(word));
        suggestedWords.addAll(correctSpellingWithReversal(word));

        System.out.print(word.toLowerCase() + " => ");
        int i = 0;
        for (String correctWord : suggestedWords) {
            System.out.print(correctWord);
            if (i != suggestedWords.size() - 1) {
                System.out.print(", ");
            }
            i++;
        }
        System.out.println();

        return suggestedWords;
    }

    // This function takes in a string word and tries to correct the spelling by substituting letters and
    // check if the resulting new word is in the dictionary.
    static ArrayList<String> correctSpellingSubstitution(String word) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        ArrayList<String> substitution = new ArrayList<>();


        for (int i = 0; i < word.length(); i++) {

            char[] newWord = word.toLowerCase().toCharArray();
            char charInWord = newWord[i]; //gives us the letter at i so i can be skipped

            for (char letter : alphabet) {
                if (letter == charInWord) {
                    continue;
                }

                newWord[i] = letter;
                String newWordString = new String(newWord);

                //check if the value is in the dictionary
                if (checkSpelling(newWordString)) {

                    substitution.add(newWordString);
                }

            }

        }

        return substitution;
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

