public class Main
{
    public static void main( String[] args)
    {
        System.out.println();

        Encrypter enc = new Encrypter("ATTACKATONCE", 4);
        enc.encrypt();
        String encrypted = enc.getEncrypted();
        System.out.println(encrypted);

        System.out.println();

        Decrypter dec = new Decrypter(encrypted, 4);
        dec.decrypt();
        String decrypted = dec.getDecrypted();
        System.out.println(decrypted);

    }
}
