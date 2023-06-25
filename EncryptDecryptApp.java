import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncryptDecryptApp
{
    private JFrame frame;
    private JPanel mainPanel;
    private JTextField inputField;
    private JTextField shiftField;
    private JTextArea resultArea;
    private JButton encryptButton;
    private JButton decryptButton;

    public EncryptDecryptApp()
    {
        createAndShowGUI();
    }

    private void createAndShowGUI()
    {
        // Create the main frame
        frame = new JFrame("Encrypt Decrypt App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the options panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout());

        // Create the encrypt button
        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                handleEncrypt();
            }
        });
        optionsPanel.add(encryptButton);

        // Create the decrypt button
        decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                handleDecrypt();
            }
        });
        optionsPanel.add(decryptButton);

        // Create the input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        // Create the input field for plaintext or encrypted text
        JLabel inputLabel = new JLabel("Input:");
        inputField = new JTextField(20);
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        // Create the shift field for encryption/decryption key
        JLabel shiftLabel = new JLabel("Shift:");
        shiftField = new JTextField(5);
        inputPanel.add(shiftLabel);
        inputPanel.add(shiftField);

        // Create the result area
        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Add components to the main panel
        mainPanel.add(optionsPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        // Add the main panel to the frame and display the window
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
    }

    private void handleEncrypt()
    {
        String plainText = inputField.getText();
        int shift = Integer.parseInt(shiftField.getText());

        Encrypter encrypter = new Encrypter(plainText, shift);
        encrypter.encrypt();

        String encryptedText = encrypter.getEncrypted();
        resultArea.setText("Encrypted Text: " + encryptedText);
    }

    private void handleDecrypt()
    {
        String encryptedText = inputField.getText();
        int shift = Integer.parseInt(shiftField.getText());

        Decrypter decrypter = new Decrypter(encryptedText, shift);
        decrypter.decrypt();

        String decryptedText = decrypter.getDecrypted();
        resultArea.setText("Decrypted Text: " + decryptedText);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new EncryptDecryptApp();
            }
        });
    }
}