package calculadora.exp.posfixa;

import java.util.Scanner;

public class Aplication {
    public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String entrada;
		Pilha pilha = new Pilha();
		
		System.out.println("Entre com a Expressao [POS FIXA]: \n Ex.: 3 42 + 2 * 180 / 2 ^");
		System.out.println();
		System.out.println(">> ");
		entrada = scan.nextLine();
        if(Calculadora.isPosFixa(entrada)){
            Calculadora.lerEntrada(entrada, pilha);
		    pilha.mostrar();            
        }else{System.out.println("\n SUA EXPRESSÃO NÃO É PÓS-FIXA!");}

		scan.close();
		System.out.println("\n\n\t FIM !");
	}
}
