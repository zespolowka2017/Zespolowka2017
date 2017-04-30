package serwer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class Services {
	
	//Sciezka do nircmd.exe (KONIECZNIE!)
	static String path = "nircmd.exe";
	
	/* Slownik dla otwieranych aplikacji (klucz - nazwa; value - absolutna sciezka do pliku)
	 * Analogicznie dla komend i sciezek;
	 *
	 *	Przechodzenie po kluczach:
	 *		for(String key : map.keySet())
	 *		{	
	 *			System.out.print(key + " ");
	 *			System.out.println(map.get(key));
	 *		}
	 *
	 *	Przechodzenie po wartosciac:
	 *		for(String value : map.values())
	 *		{
	 *			System.out.println(value);
	 *		}
	 *
	 *	Przechodzenie po calych setach:
	 *		for(Map.Entry<String, String> entry : map.entrySet())
	 *		{
	 *			String key = entry.getKey();
	 *			String value = entry.getValue();
	 *
	 *			...
	 *		}
	 */
	static Map<String, String> appList = new HashMap<String, String>();
	static Map<String, String> commandList = new HashMap<String, String>();
	static Map<String, String> pathList = new HashMap<String, String>();
	
	//Ustawienia	
	static String appsProperties = "apps.properties";
	static String commandsProperties = "commands.properties";
	static String pathsProperties = "paths.properties";
		
	static Runtime rt = Runtime.getRuntime();
    static Process pr;
	
    //Dodawanie pozycji do slownika aplikacji
	public static void addApp(String key, String value)
	{
		appList.put(key, value);
		appsSaveProperties();
	}
	
	//Usuwanie aplikacji z listy aplikacji
	public static void removeApp(String key)
	{
		appList.remove(key);
		appsSaveProperties();
	}
	
	/*
	 * Dodawanie pozycji do slownika komend (rownoznaczne z edycja)
	 * 
	 * Prefiksy dla kluczy:
	 * 		c_ - komenda glowna (pierwsze slowo),
	 * 		a_ - parametr (drugie i kolejne slowa klucze),
	 */
	public static void addCommand(String key, String value)
	{
		commandList.put(key, value);
		commandsSaveProperties();
	}
	
	/*
	 * Dodawanie roznych sciezek na potrzeby aplikacji (prafiks p_)
	 * 
	 * Klucze:
	 * p_ScreenShot - folder, gdzie maja sie zapisywac zrzuty ekranu (MUSI istniec)
	*/
	public static void addPath(String key, String value)
	{
		pathList.put(key, value);
		pathsSaveProperties();
		
	}

	//Zapisanie ustawien
	public static void appsSaveProperties()
	{
		try
		{
			Properties properties = new Properties();
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(appsProperties, false), "UTF-8") ;

			properties.putAll(appList);
			properties.store(out, null);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void commandsSaveProperties()
	{
		try
		{
			Properties properties = new Properties();
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(commandsProperties, false), "UTF-8");

			properties.putAll(commandList);
			properties.store(out, null);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void pathsSaveProperties()
	{
		try
		{
			Properties properties = new Properties();
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(pathsProperties, false), "UTF-8");

			properties.putAll(pathList);
			properties.store(out, null);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//Wczytanie ustawien
	public static void loadProperties()
	{
		InputStreamReader in;
		Properties properties;
		
		try
		{
			//Wczytywanie apps.properties
			properties = new Properties();
			in = new InputStreamReader(new FileInputStream(appsProperties), "UTF-8");
			properties.load(in);
			
			for (String key : properties.stringPropertyNames())
			{
				appList.put(key, properties.get(key).toString());
			}
			
			in.close();
			
			//Wczytywanie commands.properties
			properties = new Properties();
			in = new InputStreamReader(new FileInputStream(commandsProperties), "UTF-8");
			properties.load(in);
			
			for (String key : properties.stringPropertyNames())
			{
				commandList.put(key, properties.get(key).toString());
			}
			
			in.close();
			
			//Wczytywanie paths.properties
			properties = new Properties();
			in = new InputStreamReader(new FileInputStream(pathsProperties), "UTF-8");
			properties.load(in);
			
			for (String key : properties.stringPropertyNames())
			{
				pathList.put(key, properties.get(key).toString());
			}
			
			in.close();
			
			pathScreenshot = "C:\\";
			System.out.println(pathScreenshot);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void muteSystemVolume(int value)
	{
        try 
        {
            pr = rt.exec(path + " mutesysvolume " + value);

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

	}
	
	public static void setSystemVolume(int volume)
	{
	    if(volume < 0 || volume > 100)
	    {
	    	try
	    	{
	    		pr = rt.exec(path + " infobox \"Wybierz liczbę pomiędzy 0 a 100 (wybrana: " + volume + ")\" \"Błąd\"");
	    	}
	    	catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	    	
	        throw new RuntimeException("Błąd: " + volume + " wybierz liczbę między 0 a 100");

	    }
	    else
	    {
	        double endVolume = 655.35 * volume;

	        try 
	        {
	            pr = rt.exec(path + " setsysvolume " + endVolume);

	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	    }
	}
	
	public static void setBrightness(int value)
	{       
        if(value < 0 || value > 100)
	    {
	    	try
	    	{
	    		pr = rt.exec(path + " infobox \"Wybierz liczbę pomiędzy 0 a 100 (wybrana: " + value + ")\" \"Błąd\"");
	    	}
	    	catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	    	
	        throw new RuntimeException("Bład: " + value + " wybierz liczbę między 0 a 100");

	    }
        else
        {
        	try 
            {
                pr = rt.exec(path + " setbrightness " + value);
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
	}
	
	public static void logOffCurrentUser()
	{
        try 
        {
            pr = rt.exec(path + " exitwin logoff");

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	public static void computerStandby()
	{   
        try 
        {
            pr = rt.exec(path + " standby");

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	public static void computerRestart()
	{   
        try 
        {
            pr = rt.exec(path + " exitwin reboot");

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	public static void computerTurnOff()
	{   
        try 
        {
            pr = rt.exec(path + " exitwin poweroff");

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
//	ex. C:\Users\KR\Desktop\pz2017-zrzuty
//	Statyczna sciezka do folderu ze zrzutami ekranu (potencjalnie konfigurowalna z GUI)
	static String pathScreenshot;
	
	public static void saveScreenshot()
	{   
        try 
        {
            pr = rt.exec(path + " savescreenshot " + pathScreenshot + "zrzut_~$currdate.MM_dd_yyyy$-~$currtime.HH_mm_ss$.png");
            System.out.println("Zrzut zrobiony!");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	public static void monitorStandby()
	{   
        try 
        {
            pr = rt.exec(path + " monitor async_off");

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
//	Statyczna sciezka do folderu z plikiem .exe konkretnej apki (potencjalnie konfigurowalna z GUI)
//	Wystarczy podac sama nazwe pliku w stringu, bez .exe
	static String pathProgram = "F:/Steam/";
	
	public static void runProgram(String appName)
	{   
        try 
        {
        	pr = rt.exec(path + " exec show " + (String)appList.get(appName));
            System.out.println("Uruchomiono program " + appName);

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	public static void stopProgram(String appName)
	{
        try 
        {
            pr = rt.exec(path + " killprocess " + appName + ".exe"); //closeprocess?
            System.out.println("Zatrzymano proces " + appName);

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	public static void commands()
	{
		try
    	{
    		pr = rt.exec(path + " infobox \""
    				+ "Regulacja głośności w systemie:~n"
    				+ commandList.get("c_Volume") + "~t" + commandList.get("a_TurnOn") + "~t(włączenie dźwięku)" + "~n"
    				+ commandList.get("c_Volume") + "~t" + commandList.get("a_TurnOff") + "~t(wyłączenie dźwięku)" + "~n"
    				+ commandList.get("c_Volume") + "~t" + commandList.get("a_Toggle") + "~t(przełączenie między dźwiękiem, a wyciszeniem)" + "~n"
    				+ commandList.get("c_Volume") + "~t<0-100>" + "~t(regulacja o konkretną wartość)" + "~n"
    				+ "~n"
    				+ "Regulacja jasności ekranu (działa tylko w laptopach):~n"
    				+ commandList.get("c_Brightness") + " <0-100>" + "~t(regulacja o konkretną wartość)" + "~n"
    				+ "~n"
    				+ "System:~n"
    				+ commandList.get("c_System") + "~t" + commandList.get("a_LogOut") + "~t(wylogowanie użytkownika)" + "~n"
    				+ commandList.get("c_System") + "~t" + commandList.get("a_TurnOff") + "~t(wyłączenie systemu)" + "~n"
    				+ commandList.get("c_System") + "~t" + commandList.get("a_Restart") + "~t(restart systemu)" + "~n"
    				+ commandList.get("c_System") + "~t" + commandList.get("a_Standby") + "~t(tryb czuwania)" + "~n"
    				+ "~n"
    				+ "Opcje dostępne dla monitora:~n"
    				+ commandList.get("c_Monitor") + "~t" + commandList.get("a_Standby") + "~n"
    				+ "~n"
    				+ "Zrzut ekranu i zapis w folderze:~n"
    				+ commandList.get("c_ScreenShot") + "~t(folder: " + pathList.get("p_ScreensShot") + ")" + "~n"
    				+ "~n"
    				+ "Uruchamianie/kończenie pracy aplikacji:~n"
    				+ commandList.get("c_Run") + "~t" + " <nazwa aplikacji>" + "~t(uruchom)" + "~n"
    				+ commandList.get("c_Stop") + "~t" + " <nazwa procesu>" + "~t(zatrzymaj)" + "~n"
    				+ "\" \"Komendy\"");
    	}
    	catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	public static void chooseAction(String command)
	{
		//Rozbija wejsciowego stringa na poszczegolne komendy i w if'ach szuka, ktorej metody uzyc
		//Komendy/slowa kluczowe znajduja sie w pliku commands.properties
        String[] str = command.split(" ");
        
        if(str[0].equals(commandList.get("c_Volume")))
        {
        	if(str[1].equals(commandList.get("a_TurnOn")))
            {
        		muteSystemVolume(0);
            }
            else if(str[1].equals(commandList.get("a_TurnOff")))
            {
            	muteSystemVolume(1);
            }
            else if(str[1].equals(commandList.get("a_Toggle")))
            {
            	muteSystemVolume(2);
            }
            else
            {
            	int volume = Integer.parseInt(str[1]);
            	setSystemVolume(volume);
            }
        }
        else if(str[0].equals(commandList.get("c_Brightness")))
        {
        	int value = Integer.parseInt(str[1]);
        	setBrightness(value);
        }
        else if(str[0].equals(commandList.get("c_System")))
        {
        	if(str[1].equals(commandList.get("a_LogOut")))
            {
        		logOffCurrentUser();
            }
            else if(str[1].equals(commandList.get("a_TurnOff")))
            {
            	computerTurnOff();
            }
            else if(str[1].equals(commandList.get("a_Restart")))
            {
            	computerRestart();
            }
            else if(str[1].equals(commandList.get("a_Standby")))
            {
            	computerStandby();
            }
        }
        else if(str[0].equals(commandList.get("c_Monitor")))
        {
        	if(str[1].equals(commandList.get("a_Standby")))
            {
        		monitorStandby();
            }
        }
        else if(str[0].equals(commandList.get("c_ScreenShot")))
        {
        	saveScreenshot();
        }
        else if(str[0].equals(commandList.get("c_Run")))
        {
        	runProgram(str[1]);
        }
        else if(str[0].equals(commandList.get("c_Stop")))
        {
        	stopProgram(str[1]);
        }
        else if(str[0].equals(commandList.get("c_Commands")))
        {
        	commands();
        }
        else
        {
        	try
	    	{
	    		pr = rt.exec(path + " infobox \"Nie rozpoznano komendy: " + command + "\" \"Błąd komendy\" ");
	    	}
	    	catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	    	
        	System.out.println("Nie rozpoznano komendy!");
        }
	}
	

    
}
