/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

/**
 * @author Laptokodonozozaur
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import java.io.ObjectInputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class Hello {

    //Ścieżka do nircmd.exe (KONIECZNIE!)
    static String path = "E:\\Users\\Laptokodonozozaur\\Desktop\\nircmd.exe";

//	Map<String, String> appList = new HashMap<String, String>();

//	static String configurableName_sound = "dzwięk";
//	static String configurableName_brightness = "jasność";
//	static String configurableName_system = "system";
//	static String configurableName_monitor = "monitor";
//	static String configurableName_screen = "ekran";
//	static String configurableName_open = "uruchom";
//	static String configurableName_close = "zatrzymaj";

//	public static void addNewApp(Map<String, String> list)
//	{
//		
//	}

    public static void muteSystemVolume(int value) {
        Runtime rt = Runtime.getRuntime();
        Process pr;

        try {
            pr = rt.exec(path + " mutesysvolume " + value);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void setSystemVolume(int volume) {
        Runtime rt = Runtime.getRuntime();
        Process pr;

        if (volume < 0 || volume > 100) {
            try {
                pr = rt.exec(path + " infobox \"Wybierz liczbę pomiedzy 0 a 100 (wybrana: " + volume + ")\" \"Błąd\"");
            } catch (IOException e) {
                e.printStackTrace();
            }

            throw new RuntimeException("Błąd: " + volume + " wybierz liczbę między 0 a 100");

        } else {
            double endVolume = 655.35 * volume;

            try {
                pr = rt.exec(path + " setsysvolume " + endVolume);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setBrightness(int value) {
        Runtime rt = Runtime.getRuntime();
        Process pr;

        if (value < 0 || value > 100) {
            try {
                pr = rt.exec(path + " infobox \"Wybierz liczbę pomiedzy 0 a 100 (wybrana: " + value + ")\" \"Błąd\"");
            } catch (IOException e) {
                e.printStackTrace();
            }

            throw new RuntimeException("Błąd: " + value + " wybierz liczbę między 0 a 100");

        } else {
            try {
                pr = rt.exec(path + " setbrightness " + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void logOffCurrentUser() {
        Runtime rt = Runtime.getRuntime();
        Process pr;

        try {
            pr = rt.exec(path + " exitwin logoff");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void computerStandby() {
        Runtime rt = Runtime.getRuntime();
        Process pr;

        try {
            pr = rt.exec(path + " standby");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void computerRestart() {
        Runtime rt = Runtime.getRuntime();
        Process pr;

        try {
            pr = rt.exec(path + " exitwin reboot");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void computerTurnOff() {
        Runtime rt = Runtime.getRuntime();
        Process pr;

        try {
            pr = rt.exec(path + " exitwin poweroff");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //	ex. C:\Users\KR\Desktop\pz2017-zrzuty
//	Statyczna ścieżka do folderu ze zrzutami ekranu (potencjalnie konfigurowalna z GUI)
    static String pathScreenshot = "C:\\Users\\KR\\Desktop\\pz2017-zrzuty\\";

    public static void saveScreenshot() {
        Runtime rt = Runtime.getRuntime();
        Process pr;

        try {
            pr = rt.exec(path + " savescreenshot " + pathScreenshot + "zrzut_~$currdate.MM_dd_yyyy$-~$currtime.HH_mm_ss$.png");
            System.out.println("Zrzut zrobiony!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void monitorStandby() {
        Runtime rt = Runtime.getRuntime();
        Process pr;

        try {
            pr = rt.exec(path + " monitor async_off");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //	Statyczna ścieżka do folderu z plikiem .exe konkretnej apki (potencjalnie konfigurowalna z GUI)
//	Wystarczy podać samą nazwę pliku w stringu, bez .exe
    static String pathProgram = "F:\\Steam\\";

    public static void runProgram(String programName) {
        Runtime rt = Runtime.getRuntime();
        Process pr;

        try {
            pr = rt.exec(path + " exec show " + pathProgram + programName + ".exe");
            System.out.println("Uruchomiono program " + programName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopProgram(String programName) {
        Runtime rt = Runtime.getRuntime();
        Process pr;

        try {
            pr = rt.exec(path + " killprocess " + programName + ".exe"); //closeprocess?
            System.out.println("Zatrzymano proces " + programName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/*
	 * Funkcje obecne (nazwy potencjalnie konfigurowalne z GUI):
	 * 
	 * "dzwięk włącz"		- "włącza" głośność, jeżeli dzwięki w systemie były wyciszone
	 * "dzwięk wyłącz"		- wycisza kompletnie dzwięki systemowe
	 * "dzwięk przełącz"	- przełącza na głośność jeżeli było wyciszenie i odwrotnie
	 * "dzwięk <int>		- ustawia głośność w % w systemie, np: "dzwięk 75" ustawi głośność na 75
	 * 
	 * "jasność <int>		- analogicznie jak z wartością głośności (działa tylko na laptopach)
	 * 
	 * "system wyloguj"		- wylogowanie obecnego użytkownika
	 * "system wyłącz"		- wyłączenie systemu
	 * "system restart"		- restart systemu
	 * "system czuwaj"		- przejście w stan czuwania
	 * 
	 * "monitor czuwaj"		- przejście ekranu w stan czuwania
	 * 
	 * "ekran zrzut"		- zrobienie i zapis zrzutu ekranu; pliki zapisane w ścieżce pathScreenshot
	 * 
	 * "uruchom <nazwa>"	- uruchomienie konkretnej aplikacji <nazwa>.exe; plik wyszukiwany w folderze ze ścieżki pathProgram
	 * "zatrzymaj <nazwa>"	- zabicie procesu <nazwa>.exe
	 */

    public static void chooseAction(String command) {
        //Rozbija wejściowego stringa na poszczególne komenty i w if'ach szuka, której metody użyć
        String[] str = command.split(" ");

        if (str[0].equals("dzwięk")) {
            if (str[1].equals("włącz")) {
                muteSystemVolume(0);
            } else if (str[1].equals("wyłącz")) {
                muteSystemVolume(1);
            } else if (str[1].equals("przełącz")) {
                muteSystemVolume(2);
            } else {
                int volume = Integer.parseInt(str[1]);
                setSystemVolume(volume);
            }
        } else if (str[0].equals("jasność")) {
            int value = Integer.parseInt(str[1]);
            setBrightness(value);
        } else if (str[0].equals("system")) {
            if (str[1].equals("wyloguj")) {
                logOffCurrentUser();
            } else if (str[1].equals("wyłącz")) {
                computerTurnOff();
            } else if (str[1].equals("restart")) {
                computerRestart();
            } else if (str[1].equals("czuwaj")) {
                computerStandby();
            }
        } else if (str[0].equals("monitor")) {
            if (str[1].equals("czuwaj")) {
                monitorStandby();
            }
        } else if (str[0].equals("ekran")) {
            if (str[1].equals("zrzut")) {
                saveScreenshot();
            }
        } else if (str[0].equals("uruchom")) {
            runProgram(str[1]);
        } else if (str[0].equals("zatrzymaj")) {
            stopProgram(str[1]);
        } else {
            System.out.println("Nie rozpoznano komendy!");
        }
    }


}

