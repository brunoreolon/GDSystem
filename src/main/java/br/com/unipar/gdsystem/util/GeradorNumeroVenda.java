package br.com.unipar.gdsystem.util;

public class GeradorNumeroVenda {
	private static Integer numeroVenda = 1;
	
	public static int getProximaNumeroVenda() {
        return ++numeroVenda;
    }
}
