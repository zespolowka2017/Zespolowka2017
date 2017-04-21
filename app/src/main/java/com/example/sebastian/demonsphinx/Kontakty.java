package com.example.sebastian.demonsphinx;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;

public class Kontakty extends ActionBarActivity {
	public String nazwaWyswietlana;
	public String telefonKomorkowy;
	public String telefonDomowy;
	public String telefonWPracy;
	public Kontakty(String nazwaWyswietlana, String telefonKomorkowy, String telefonDomowy, String telefonWPracy){
		this.nazwaWyswietlana=nazwaWyswietlana;
		this.telefonKomorkowy=telefonKomorkowy;
		this.telefonDomowy=telefonDomowy;
		this.telefonWPracy=telefonWPracy;
	}
	private Kontakty(ContentResolver contentResolver, Cursor cursor){
		this.nazwaWyswietlana="";
		this.telefonKomorkowy="";
		this.telefonDomowy="";
		this.telefonWPracy="";
		String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
		this.nazwaWyswietlana = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
		if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))>0){
			Cursor numeryTelefonow = contentResolver.query(Phone.CONTENT_URI, null, Phone.CONTACT_ID+" = "+id,null,null);
			while(numeryTelefonow.moveToNext())
			{
				String numer = numeryTelefonow.getString(numeryTelefonow.getColumnIndex(Phone.NUMBER));
				int typNumeru = numeryTelefonow.getInt(numeryTelefonow.getColumnIndex(Phone.TYPE));
				switch(typNumeru)
				{
					case Phone.TYPE_MOBILE:
						this.telefonKomorkowy = numer;
						break;
					case Phone.TYPE_HOME:
						this.telefonDomowy = numer;
						break;
					case Phone.TYPE_WORK:
						this.telefonWPracy = numer;
						break;
				}
			}
			numeryTelefonow.close();
		}
	}
	public static ArrayList<Kontakty> listaWszystkichKontaktow(ContentResolver contentResolver){
		ArrayList<Kontakty> lista = new ArrayList<Kontakty>();
		Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				lista.add(new Kontakty(contentResolver,cursor));
			}
		} else {
			lista=null;
		}
		return lista;
	}
	public static ArrayList<String> listaOpisowWszystkichKontaktow(ContentResolver contentResolver)
	{
		ArrayList<Kontakty> listaKontaktow = Kontakty.listaWszystkichKontaktow(contentResolver);
		ArrayList<String> lista = new ArrayList<String>();
		for(Kontakty kontakt : listaKontaktow)
		{
			String opis =
					kontakt.nazwaWyswietlana+
					"\ntel. kom.:"+kontakt.telefonKomorkowy;
			lista.add(opis.trim());
		}		
		return lista;
	}
}
