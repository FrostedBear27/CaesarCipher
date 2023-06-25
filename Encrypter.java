public class Encrypter
{
    private String plainText;

    private String encrypted;
    private int shift;
    private String[][] charNums;

    public Encrypter( String text, int move)
    {
        plainText = text;
        shift = move;
    }

    public Encrypter(){}

    public void encrypt()
    {
        fillArray();

        StringBuilder encryptedBuilder = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++)
        {
            char currentChar = plainText.charAt(i);
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

                // Apply the shift to the indices
                colIndex = (colIndex + shift) % 26;

                // Get the encrypted character from the charNums array
                char encryptedChar = charNums[rowIndex][colIndex].charAt(0);

                if (isUpperCase)
                {
                    encryptedChar = Character.toUpperCase(encryptedChar);
                }

                encryptedBuilder.append(encryptedChar);
            }

            else
            {
                encryptedBuilder.append(currentChar); // Append non-letter characters as is
            }
        }

        encrypted = encryptedBuilder.toString();
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

    public String getEncrypted()
    {
        return encrypted;
    }

    public String[][] getCharNums()
    {
        return charNums;
    }

    public int getShift()
    {
        return shift;
    }
}
