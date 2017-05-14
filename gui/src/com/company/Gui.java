package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Created by john on 06.03.17.
 */
public class Gui {
    private JPanel MainPanel;
    private JButton komendyButton;
    private JButton glownyWidokButton;
    private JButton oProgramieButton;
    private JPanel CardView;
    private JPanel MainView;
    private JPanel ComandView;
    private JTextArea TextAreaNetLog;
    private JTextArea textArea1;
    private JButton Konfiguracja;
    private JPanel ConfigView;
    private JTextField KluczTextField;
    private JTextField WartoscTextField;
    private JComboBox comboBox1;
    private JButton zapiszButton;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTabbedPane tabbedPane1;
    private JButton pomocButton;
    private JButton browseButton;
    private JLabel nazwaLabel;
    private JLabel wartoscLabel;
    private JComboBox<String> comboBox2;
    private JButton usunButton;
    private JLabel delAppLabel;


    Gui() {
        oProgramieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Link do strony: https://aleks-2.mat.umk.pl/pz2016/zesp09/. \n Autorzy aplikacji: \n WITTKOWSKA KATARZYNA \n STRASZEWSKI SEBASTIAN \n STĘPIŃSKI HUBERT \n MARKUSZEWSKI MATEUSZ \n RUDZIŃSKI KRYSTIAN \n SZCZUPAKOWSKI DAWID.", "O programie", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        glownyWidokButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout cd = (CardLayout) CardView.getLayout();
                cd.show(CardView, "Card1");

            }
        });
        komendyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout cd = (CardLayout) CardView.getLayout();
                cd.show(CardView, "Card2");
            }
        });
        Konfiguracja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout cd = (CardLayout) CardView.getLayout();
                cd.show(CardView, "Card3");
            }
        });
        zapiszButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (isDataValid()) {
                    switch (comboBox1.getSelectedIndex()) {
                        case 0:
                            Serwer.addCommand(KluczTextField.getText(), WartoscTextField.getText());
                            break;
                        case 1:
                            Serwer.addApp(KluczTextField.getText(), WartoscTextField.getText());
                            break;
                        case 2:
                            Serwer.addPath(KluczTextField.getText(), WartoscTextField.getText());
                            break;
                    }
                    JOptionPane.showMessageDialog(null, "Dodano pomyślnie,  zmiany zadziałają po zresetowaniu serwera.", "Sukces!", JOptionPane.INFORMATION_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(null, "Musisz podać wszystkie dane!", "Błąd!", JOptionPane.ERROR_MESSAGE);
            }

        });
        pomocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, " W oknie konfiguracji można dodać nowe: komendę, program lub ścieżkę. \n Wybieramy z listy to, co chcemy dodać, po czym podajemy nazwę oraz wartość danego ustawienia. \n Zatwierdzamy przyciskiem \"Zapisz\".", "Pomoc ustawień", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (comboBox1.getSelectedIndex() > 0)
                    browseButton.setEnabled(true);
                else
                    browseButton.setEnabled(false);

                switch (comboBox1.getSelectedIndex()) {
                    case 0:
                        delAppLabel.setVisible(false);
                        comboBox2.setVisible(false);
                        usunButton.setVisible(false);
                        KluczTextField.setText("");
                        WartoscTextField.setText("");
                        nazwaLabel.setVisible(true);
                        KluczTextField.setVisible(true);
                        nazwaLabel.setText("Komenda?");
                        wartoscLabel.setText("Słowo wywołujące");
                        break;
                    case 1:
                        WartoscTextField.setText("");
                        KluczTextField.setText("");
                        nazwaLabel.setVisible(true);
                        KluczTextField.setVisible(true);
                        nazwaLabel.setText("Alias aplikacji?");
                        wartoscLabel.setText("Ścieżka");
                        fillAppListToDel();
                        delAppLabel.setVisible(true);
                        comboBox2.setVisible(true);
                        usunButton.setVisible(true);
                        break;
                    case 2:
                        delAppLabel.setVisible(false);
                        comboBox2.setVisible(false);
                        usunButton.setVisible(false);
                        WartoscTextField.setText("");
                        nazwaLabel.setVisible(false);
                        KluczTextField.setVisible(false);
                        KluczTextField.setText("p_ScreenShot");
                        wartoscLabel.setText("Ścieżka do zapisu zrzutu");
                        break;
                }
            }
        });
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                if (comboBox1.getSelectedIndex() == 2) {
                    fc.setAcceptAllFileFilterUsed(false);
                    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                }
                if (fc.showOpenDialog(ConfigView) == JFileChooser.APPROVE_OPTION)
                    WartoscTextField.setText(String.valueOf(fc.getSelectedFile()));
            }
        });
        usunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = 0;
                String deleteByKey = "";
                for (String key : Serwer.appList.keySet()) {
                    if (index == comboBox2.getSelectedIndex())
                        deleteByKey = key;
                    index++;
                }
                Serwer.removeApp(deleteByKey);
                fillAppListToDel();
                textArea2.setText("");
                for (String key : Serwer.appList.keySet()) textArea2.append(key + " -> " + Serwer.appList.get(key));
            }
        });
    }

    void fillAppListToDel() {
        comboBox2.removeAllItems();
        for (String key : Serwer.appList.keySet())
            comboBox2.addItem(key + " -> " + Serwer.appList.get(key));
    }

    private boolean isDataValid() {
        return !(Objects.equals(KluczTextField.getText(), "") || Objects.equals(WartoscTextField.getText(), ""));
    }

    void logNetInfo(String data) {
        TextAreaNetLog.append(data);
    }

    void listCommands(String data) {
        textArea1.append(data + "\n");
    }

    void listApps(String data) {
        textArea2.append(data + "\n");
    }

    void listPaths(String data) {
        textArea3.append(data + "\n");
    }

    void clearVarsView() {
        textArea1.setText("");
        textArea2.setText("");
        textArea3.setText("");
    }

    void show() {
        JFrame frame = new JFrame("Projekt");
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.pack();
        delAppLabel.setVisible(false);
        comboBox2.setVisible(false);
        usunButton.setVisible(false);
        frame.setVisible(true);
    }

}
