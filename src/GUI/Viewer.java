package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Viewer extends JFrame {

    private Button save = new Button("Save");
    private Button reset = new Button("Reset");
    private JLabel textToEnter = new JLabel("one ring to rule them all one ring to find them one ring to bring them all and in the darkness bind them");
    private JLabel nameLabel = new JLabel("Your name: ");
    private JLabel textHint = new JLabel("Enter following text: ");
    private JTextField name = new JTextField();
    private JTextField text = new JTextField();
    List<Long> list = new ArrayList<>();

    public Viewer() {
        this.setLayout(null);
        this.setTitle("Keystroke Dynamics");
        this.setSize(new Dimension(1000, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        add(nameLabel);
        nameLabel.setBounds(20, 20, 100, 20);

        add(name);
        name.setBounds(20, 50, 200, 20);

        add(textHint);
        textHint.setBounds(20, 100, 600, 20);

        add(textToEnter);
        textToEnter.setBounds(20, 130, 600, 20);

        add(text);
        text.setBounds(20, 160, 600, 20);

        add(save);
        save.setBounds(600, 450, 130, 50);

        add(reset);
        reset.setBounds(750, 450, 130, 50);

        text.addKeyListener(new KeyListener() {
            int lastCode = -1;
            long timeLast = -1;

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                lastCode = e.getKeyCode();
                timeLast = System.currentTimeMillis();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (lastCode != -1) {
                    long measuredTime =
                            System.currentTimeMillis() - timeLast;
                    System.out.println(String.format("Code: %d, time %d", lastCode, measuredTime));
                    list.add(measuredTime);
                }
            }
        });

        save.addActionListener(e -> {
            String enteredText = text.getText();
            if (enteredText.equals(textToEnter.getText())) {
                if (list.size() == 104) {
                    String yourName = name.getText();
                    System.out.println("Dobrze " + yourName);
                    File file = new File(yourName + "1.txt");
                    try {
                        if (file.createNewFile()) {
                            System.out.println("File " + yourName + "1.txt" + " created");
                            FileWriter writer = new FileWriter(file);
                            list.forEach(i -> {
                                System.out.println(i);
                                try {
                                    writer.write(i.toString() + " ");
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            });
                            writer.close();
                        } else {
                            File file2 = new File(yourName + "2.txt");
                            if (file2.createNewFile()) {
                                FileWriter writer2 = new FileWriter(file2);
                                list.forEach(i -> {
                                    System.out.println(i);
                                    try {
                                        writer2.write(i.toString() + " ");
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                });
                                writer2.close();
                                System.out.println("File " + yourName + "2.txt created");
                            } else {
                                File file3 = new File(yourName + "3.txt");
                                if (file3.createNewFile()) {
                                    FileWriter writer3 = new FileWriter(file3);
                                    list.forEach(i -> {
                                        System.out.println(i);
                                        try {
                                            writer3.write(i.toString() + " ");
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                    });
                                    writer3.close();
                                    System.out.println("File " + yourName + "3.txt created");
                                } else {
                                    System.out.println("All files created");
                                }
                            }
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Something went wrong, Press reset nad try again!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Texts do not match. Press reset and try again!");
            }
            list.clear();
        });

        reset.addActionListener(e -> {
            list.clear();
            text.setText("");
        });

    }

}
