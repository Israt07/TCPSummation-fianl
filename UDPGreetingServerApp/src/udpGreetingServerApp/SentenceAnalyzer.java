package udpGreetingServerApp;

/**
 * 
 * @Israt Jahan Bhuiyan
 *
 */

public class SentenceAnalyzer {

    private String sentence;

    public SentenceAnalyzer(String sentence) {
        this.sentence = sentence;
    }

    /**
     * Count the number of vowels in the sentence.
     *
     * @return The number of vowels.
     */
    public int countVowels() {
        int count = 0;
        String vowels = "AEIOUaeiou";

        for (char c : sentence.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }

        return count;
    }

    /**
     * Count the number of consonants in the sentence.
     *
     * @return The number of consonants.
     */
    public int countConsonants() {
        int count = 0;
        String consonants = "BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz";

        for (char c : sentence.toCharArray()) {
            if (consonants.indexOf(c) != -1) {
                count++;
            }
        }

        return count;
    }

    /**
     * Count the number of punctuations in the sentence.
     *
     * @return The number of punctuations.
     */
    public int countPunctuations() {
        int count = 0;
        String punctuations = ".?!,;:-(){}[]";

        for (char c : sentence.toCharArray()) {
            if (punctuations.indexOf(c) != -1) {
                count++;
            }
        }

        return count;
    }
}
