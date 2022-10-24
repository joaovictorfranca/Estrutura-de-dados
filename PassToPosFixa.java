package calculadoraexposfixa;

public class PassToPosFixa {

    public static String LerExp (String expr){ 
    String psfxa = "";
    String numeros = "";
    String operadores = "";
    String semPreferencia = "";
    
    for (int i = 0; i < expr.length(); i++) {
        // ANALISA SE É DIGITO
        char itemString = expr.charAt(i);

        if (Character.isDigit(itemString)) {
            numeros = numeros + itemString;
            if (i == expr.length() - 1){
                psfxa = psfxa + numeros + " " + operadores + semPreferencia;
            }
            continue;
        }
        if (itemString == ' ') {
            if (numeros.length() > 0) {
                psfxa = psfxa + numeros + " ";
            }
            numeros = "";
            continue;
        }

        if(itemString == '('){
            continue;
        }
        
        if(itemString == ')'){
            psfxa = psfxa + numeros + " " + operadores + " " + semPreferencia + " ";
            numeros = "";
            operadores = "";
            semPreferencia = "";
            continue;
        }

        if (itemString == '.') {
            numeros = numeros + ".";
        }

        if (itemString == '*' || itemString == '/' || itemString == '^') {
            operadores = operadores + itemString;
            if (i == expr.length() - 1){
                psfxa = psfxa + numeros + " " + operadores + semPreferencia;
            }
            continue;
        }

        if (itemString == '+' || itemString == '-'){
            semPreferencia = semPreferencia + itemString;
            if (i == expr.length() - 1){
                psfxa = psfxa + numeros + " " + operadores + semPreferencia;
            }
            continue;
        }
    }
    return psfxa;
    }
    
}