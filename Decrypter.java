public class Decrypter
{
    private String decrypted;
    private String encrypted;
    private int shift;

    private String[][] charNums;

    public Decrypter(String encryptedText, int move)
    {
        encrypted = encryptedText;
        shift = move;
    }

    public void decrypt()
    {
        fillArray();

        StringBuilder decryptedBuilder = new StringBuilder();

        for (int i = 0; i < encrypted.length(); i++)
        {
            char currentChar = encrypted.charAt(i);
            boolean isUpperCase = Character.isUpperCase(currentChar);

            if (Character.isLetter(currentChar))
            {
                currentChar = Character.toLowerCase(currentChar);
                int rowIndex = 0; // Index of the row in the charNums array
                int colIndex = 0; // Index of the column in the charNums array

                // Find the indices of the current character in the charNums array
                outerloop:
                for (int row = 0; row < 2; row++)
                {
                    for (int col = 0; col < 26; col++)
                    {
                        if (charNums[row][col].charAt(0) == currentChar)
                        {
                            rowIndex = row;
                            colIndex = col;
                            break outerloop;
                        }
                    }
                }

                // Apply the inverse shift to the indices
                colIndex = (colIndex - shift + 26) % 26;

                // Get the decrypted character from the charNums array
                char decryptedChar = charNums[rowIndex][colIndex].charAt(0);

                if (isUpperCase)
                {
                    decryptedChar = Character.toUpperCase(decryptedChar);
                }

                decryptedBuilder.append(decryptedChar);
            }

            else
            {
                decryptedBuilder.append(currentChar); // Append non-letter characters as is
            }
        }

        decrypted = decryptedBuilder.toString();
    }

    public void fillArray()
    {
        charNums = new String[2][26];

        for (int i = 0; i < 26; i++)
        {
            char letter = (char) ('a' + i);
            charNums[0][i] = Character.toString(letter);
        }

        for (int i = 0; i < 26; i++)
        {
            int number = i + 1;
            charNums[1][i] = Integer.toString(number);
        }
    }

    public String getDecrypted()
    {
        return decrypted;
    }
}





