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


    Gui() {
        oProgramieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Serwer GUI by Szczupakowski.", "O programie", JOptionPane.INFORMATION_MESSAGE);
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
                if(isDataValid()){
                    switch(comboBox1.getSelectedIndex()){
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
                    JOptionPane.showMessageDialog(null, "Dodano pomyślnie,  zmiany zadziałają po zresetowaniu serwera.", "Sukces!", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null, "Musisz podać wszystkie dane!", "Błąd!", JOptionPane.ERROR_MESSAGE);
            }

        });
        pomocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, " W oknie konfiguracji można dodać nowe: komendę, program lub ścieżkę. \n Wybieramy z listy to, co chcemy dodać, po czym podajemy nazwę oraz wartość danego ustawienia. \n Zatwierdzamy przyciskiem \"Zapisz\".", "Pomoc ustawień", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private boolean isDataValid(){
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

    void clearVarsView(){
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
        frame.setVisible(true);
    }

}
