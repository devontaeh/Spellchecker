

public static void main(String[]args)throws FileNotFoundException{
        Scanner scanDictionary=new Scanner(new File("C:\\Users\\devon\\Documents\\CS2210\\A2_test\\toyDictionary.txt"));
        Scanner scanWordsToCheck=new Scanner(new File("C:\\Users\\devon\\Documents\\CS2210\\A2_test\\fileToCheck.txt"));
        Spell spell=new Spell(scanDictionary,scanWordsToCheck);


        Hashtable<String, Boolean> dictionary=getDictionary();
        ArrayList<String> wordsToCheck=getWordsToCheck();


        for(int i=0;i<wordsToCheck.size();i++){
        if(!checkSpelling(wordsToCheck.get(i))){
        suggestCorrections(wordsToCheck.get(i));
        }else{
        System.out.printf("%s: Correct Spelling\n",wordsToCheck.get(i));
        }
        }

        }