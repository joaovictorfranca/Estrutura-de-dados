package multiplicacao_lista;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		scan.useLocale(Locale.US);
		
		System.out.println("Insira o primeiro numero: ");
		String primeiraEntrada = scan.next();
		System.out.println("Insira o segundo numero: ");
		String segundaEntrada = scan.next();
		System.out.println("Insira o tamanho de partições: ");
		int qtdParticoes = scan.nextInt();
		System.out.println("\nNumero particionado: ");
		List<String> numeroA = Split.manipulaStringRetornandoUmaListaDoNumero(primeiraEntrada, qtdParticoes);
		List<String> numeroB = Split.manipulaStringRetornandoUmaListaDoNumero(segundaEntrada, qtdParticoes);
		
		System.out.println(numeroA.toString());
		System.out.println(numeroB.toString());
		
		System.out.println(Split.multiplicar(numeroA, numeroB, qtdParticoes));
		
		scan.close();
		
	}
}