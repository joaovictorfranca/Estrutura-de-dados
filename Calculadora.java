package calculadoraexposfixa;

public class Calculadora {
	double sobrando[];

	public static void lerEntrada(String expPosFixa, Pilha pilha) {
		String numero = "";
		
		for (int i = 0; i < expPosFixa.length(); i++) {
			// ANALISA SE É DIGITO
			char itemString = expPosFixa.charAt(i);
			if (Character.isDigit(itemString)) {
				numero = numero + itemString;
				continue;
			}
			if (itemString == ' ') {
				if (numero.length() > 0) {
					pilha.push(Double.valueOf(numero));
				}
				numero = "";
				continue;
			}

			if (expPosFixa.charAt(i) == '.') {
				numero = numero + ".";
				continue;
			}

			if (itemString == '+' || itemString == '-' || itemString == '*' || itemString == '/' || itemString == '^') {
				pilha.push(calcularRetornando(pilha, itemString));
			}
		}
	}

	private static double calcularRetornando(Pilha pilha, char operador) {

		if(pilha.qtdeElementos == 1){
			System.out.println("\nSua pilha está faltando numero para realizar a operação: " + operador + "\n\nA ultima operação realizada retorna:");
		}else{
			double a = pilha.pop();
			double b = pilha.pop();

			switch (operador) {
				case '+':
					return b + a;
				case '-':
					return b - a;
				case '*':
					return b * a;
				case '/':
					return b / a;
				case '^':
					return Math.pow(b, a);
				default:
					return 0;
			}
		}
		return 0;
	}

    
    public static boolean isPosFixa(String dadosEntrada) {
        
        for(int i = 0; i < dadosEntrada.length(); i++){
           char a = dadosEntrada.charAt(i);
            
            if(a == '('){
                return false;
            }else if(a == ')'){
                return false;
            }else if(a == '='){
                return false;
            }else{
                continue;
            }
        }
        return true;
        
    }
}