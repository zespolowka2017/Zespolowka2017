package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
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
    private JComboBox<String> comboBox3;


    Gui() {
        TextAreaNetLog.setAutoscrolls(true);
        glownyWidokButton.setEnabled(false);
        oProgramieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                oProgramieButton.setEnabled(false);
                komendyButton.setEnabled(true);
                glownyWidokButton.setEnabled(true);
                Konfiguracja.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Link do strony: https://aleks-2.mat.umk.pl/pz2016/zesp09/. \n Autorzy aplikacji: \n WITTKOWSKA KATARZYNA \n STRASZEWSKI SEBASTIAN \n STĘPIŃSKI HUBERT \n MARKUSZEWSKI MATEUSZ \n RUDZIŃSKI KRYSTIAN \n SZCZUPAKOWSKI DAWID.", "O programie", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        glownyWidokButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                oProgramieButton.setEnabled(true);
                komendyButton.setEnabled(true);
                glownyWidokButton.setEnabled(false);
                Konfiguracja.setEnabled(true);
                CardLayout cd = (CardLayout) CardView.getLayout();
                cd.show(CardView, "Card1");

            }
        });
        komendyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                oProgramieButton.setEnabled(true);
                komendyButton.setEnabled(false);
                glownyWidokButton.setEnabled(true);
                Konfiguracja.setEnabled(true);
                CardLayout cd = (CardLayout) CardView.getLayout();
                cd.show(CardView, "Card2");
            }
        });
        Konfiguracja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                oProgramieButton.setEnabled(true);
                komendyButton.setEnabled(true);
                glownyWidokButton.setEnabled(true);
                Konfiguracja.setEnabled(false);
                CardLayout cd = (CardLayout) CardView.getLayout();
                cd.show(CardView, "Card3");
                fillCombo(Services.commandList, comboBox3);
            }
        });
        zapiszButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (isDataValid()) {
                    switch (comboBox1.getSelectedIndex()) {
                        case 0:
                            Services.addCommand(KluczTextField.getText(), WartoscTextField.getText());
                            break;
                        case 1:
                            Services.addApp(KluczTextField.getText(), WartoscTextField.getText());
                            break;
                        case 2:
                            Services.addPath(KluczTextField.getText(), WartoscTextField.getText());
                            break;
                    }
                    fillSettingsView();
                    JOptionPane.showMessageDialog(null, "Dodano pomyślnie!", "Sukces!", JOptionPane.INFORMATION_MESSAGE);
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
                        settings(false, false, false,
                                true, "",
                                true, "Koemnda?", "Słowo wywołujące",
                                true);
                        break;
                    case 1:
                        settings(true, true, true,
                                true, "",
                                true, "Nazwa Aplikacji?", "Scieżka",
                                false);
                        fillCombo(Services.appList, comboBox2);
                        break;
                    case 2:
                        settings(false, false, false,
                                false, "p_ScreenShot",
                                false, "", "Ścieżka do zapisu zrzutu",
                                false);
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
                Services.removeApp(getListValueByIndex(Services.appList, comboBox2.getSelectedIndex()));
                fillCombo(Services.appList, comboBox2);
                textArea2.setText("");
                for (String key : Services.appList.keySet()) textArea2.append(key + " -> " + Services.appList.get(key));
            }
        });
        comboBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                KluczTextField.setText(getListValueByIndex(Services.commandList, comboBox3.getSelectedIndex()));
            }
        });
    }

    private void settings(boolean delAppLabelVisibility, boolean comboBox2Visibility, boolean usunButtonVisibility,
                          Boolean KluczTextFieldVisibility, String KluczTextFieldText,
                          boolean nazwaLabelVisibility, String nazwaLabelText, String wartoscLabelText, boolean comboBox3Visibility) {
        delAppLabel.setVisible(delAppLabelVisibility);
        comboBox2.setVisible(comboBox2Visibility);
        usunButton.setVisible(usunButtonVisibility);
        WartoscTextField.setText("");
        KluczTextField.setVisible(KluczTextFieldVisibility);
        KluczTextField.setText(KluczTextFieldText);
        nazwaLabel.setVisible(nazwaLabelVisibility);
        nazwaLabel.setText(nazwaLabelText);
        wartoscLabel.setText(wartoscLabelText);
        comboBox3.setVisible(comboBox3Visibility);
    }

    private String getListValueByIndex(Map<String, String> list, int index){
        int i = 0;
        String retValue = "";
        for (String key : list.keySet()) {
            if (i == index)
                retValue = key;
            i++;
        }
        return retValue;
    }

    public void fillSettingsView(){
        clearVarsView();
        for (String key : Services.commandList.keySet()) listCommands(key + " -> " + Services.commandList.get(key));
        for (String key : Services.appList.keySet()) listApps(key + " -> " + Services.appList.get(key));
        for (String key : Services.pathList.keySet()) listPaths(key + " -> " + Services.pathList.get(key));
    }

    private void fillCombo(Map<String, String> list, JComboBox<String> combo) {
        combo.removeAllItems();
        for (String key : list.keySet())
            combo.addItem(key + " -> " + list.get(key));
    }

    private boolean isDataValid() {
        return !(Objects.equals(KluczTextField.getText(), "") || Objects.equals(WartoscTextField.getText(), ""));
    }

    void logNetInfo(String data) {
        TextAreaNetLog.append(data);

    }

    public void listCommands(String data) {
        textArea1.append(data + "\n");
    }

    public void listApps(String data) {
        textArea2.append(data + "\n");
    }

    public void listPaths(String data) {
        textArea3.append(data + "\n");
    }

    public void clearVarsView() {
        textArea1.setText("");
        textArea2.setText("");
        textArea3.setText("");
    }

    void show() throws IOException {
        JFrame frame = new JFrame("Projekt");
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(ImageIO.read(new File("favicon.png")));
        frame.pack();
        delAppLabel.setVisible(false);
        comboBox2.setVisible(false);
        usunButton.setVisible(false);
        fillSettingsView();
        frame.setVisible(true);
    }

}
