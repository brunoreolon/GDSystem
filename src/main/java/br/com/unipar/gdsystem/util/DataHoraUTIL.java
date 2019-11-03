package br.com.unipar.gdsystem.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DataHoraUTIL {

	private static Locale locale = new Locale("pt", "BR");
	private static GregorianCalendar calendar = new GregorianCalendar();
	private static SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy - HH:mm", DataHoraUTIL.locale);
	
	public static String getDataHora() {
		return formatador.format(calendar.getTime());
	}
	
	public static String converterDataHoraString(Calendar calendar) {
		return formatador.format(calendar.getTime());
	}
}
