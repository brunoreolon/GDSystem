package br.com.unipar.gdsystem.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DataHoraUTIL {

	private static Locale locale = new Locale("pt", "BR");
	private static GregorianCalendar calendar = new GregorianCalendar();
	private static SimpleDateFormat formatador = new SimpleDateFormat("HH:mm - dd/MM/yyyy", DataHoraUTIL.locale);
	
	public static Calendar getDataHora() {
		return Calendar.getInstance();
	}

	public static String getDataHoraString() {
		return formatador.format(calendar.getTime());
	}
	
	public static String converterDataHoraString(Calendar calendar) {
		return formatador.format(calendar.getTime());
	}

	public static String getData() {
		formatador = new SimpleDateFormat("dd/MM/yyyy", DataHoraUTIL.locale);
		return formatador.format(calendar.getTime());
	}
	
	public static String getHora() {
		formatador = new SimpleDateFormat("HH:mm", DataHoraUTIL.locale);
		return formatador.format(calendar.getTime());
	}
}
