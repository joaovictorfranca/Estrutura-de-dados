package multiplicacao_lista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Split {
	
	public static List<String> manipulaStringRetornandoUmaListaDoNumero(String numeroEntrada, Integer qtdCaracteresBase){
		List<String> listaRetorno = new ArrayList<String>();
		
		int ultimaPosicaoLeitura = numeroEntrada.length() - 1;
		if(qtdCaracteresBase == null) {
			qtdCaracteresBase = 3;
		}
		
		int posicaoDestino = ultimaPosicaoLeitura - qtdCaracteresBase + 1;
		
		if(numeroEntrada.length() <= qtdCaracteresBase) {
			listaRetorno.add(numeroEntrada);
			return listaRetorno;
		}
		
		int loop = (numeroEntrada.length() / qtdCaracteresBase) + 1;
		int statusEstourouCota = 0;
		while(loop > 0) {
			statusEstourouCota += qtdCaracteresBase;
			if(statusEstourouCota >= numeroEntrada.length()) {
				posicaoDestino = 0;
			} else {
				posicaoDestino = ultimaPosicaoLeitura - qtdCaracteresBase + 1;
			}
			
			listaRetorno.add(numeroEntrada.substring(posicaoDestino, ultimaPosicaoLeitura) + numeroEntrada.charAt(ultimaPosicaoLeitura));
			ultimaPosicaoLeitura -= qtdCaracteresBase;
			
			if(ultimaPosicaoLeitura <= 0) {
				if(ultimaPosicaoLeitura == 0) {
					listaRetorno.add(String.valueOf(numeroEntrada.charAt(0)));
				}
				break;
			}
			
			loop--;
		}
		return inverterLista(listaRetorno);
	}
	
	private static List<String> inverterLista(List<String> lista){
		List<String> listaInvertida = new ArrayList<>();
		
		for(int i = lista.size()-1; i >= 0; i--) {
			listaInvertida.add(lista.get(i));
		}
		return listaInvertida;
	}
	
	private static List<BigDecimal> converterListaStringToBigDecimal(List<String> lista){
		List<BigDecimal> retorno = new ArrayList<>();
		for(String item : lista) {
			retorno.add(BigDecimal.valueOf(Long.parseLong(item)));
		}
		return retorno;
	}
	
	
	public static List<String> multiplicar(List<String> a, List<String> b, Integer qtdCaracteresBase) {
		List<String> resultadoFinal = new ArrayList<String>();
		List<BigDecimal> listaA;
		List<BigDecimal> listaB;
		if(conteudoMaiorIgual(a,b)) {
			listaA = converterListaStringToBigDecimal(a);
			listaB = converterListaStringToBigDecimal(b);
		} else {
			listaA = converterListaStringToBigDecimal(b);
			listaB = converterListaStringToBigDecimal(a);
		}
		List<String> buffer = new ArrayList<String>();
		
		//----------------------- MULTIPLICA��O -----------------------//
		// CASO BASICO
		if(listaA.size() == 1 && listaB.size() == 1) {
			return manipulaStringRetornandoUmaListaDoNumero(multiplicacao(listaA.get(0), listaB.get(0)).toString(), qtdCaracteresBase);
		}
		
		// CASO LISTA A > 1 E B == 1
		if(listaA.size() > 1 && listaB.size() == 1) {
			return multiplicaListaTamMaiorPorListaTamIgualUm(qtdCaracteresBase, listaA, listaB, buffer);
		}
		
		// CASO LISTA B > 1 E A == 1
		if(listaA.size() == 1 && listaB.size() > 1) {
			return multiplicaListaTamMaiorPorListaTamIgualUm(qtdCaracteresBase, listaB, listaA, buffer);
		}
		
		// CASO EM QUE LISTA B < LISTA A
		List<ListaDeListas> retornos = new ArrayList<>();
		int incrementoDefault = 1;
		if(listaB.size() <= listaA.size()) {
			for(int i = listaB.size() - 1; i >= 0; i --) {
				List<BigDecimal> lista = new ArrayList<BigDecimal>();
				lista.add(listaB.get(i));
				if(i == listaB.size() - 1) {
					retornos.add(new ListaDeListas(multiplicaListaTamMaiorPorListaTamIgualUm(qtdCaracteresBase, listaA, lista, buffer)));
				} else {
					List<String> retorno = multiplicaListaTamMaiorPorListaTamIgualUm(qtdCaracteresBase, listaA, lista, buffer);
					for(int j = 0; j < incrementoDefault; j++) {
						incrementoDefaultNoFinalDaLista(retorno, qtdCaracteresBase);
					}
					retornos.add(new ListaDeListas(retorno));
					incrementoDefault++;
				}
			}
		}
		resultadoFinal = somaRetornos(retornos, qtdCaracteresBase);
		
		return resultadoFinal;
	}
	
	private static boolean conteudoMaiorIgual(List<String> a, List<String> b) {
		String valorA = "";
		String valorB = "";
		for(String valor : a) {
			valorA += valor;
		}
		for(String valor : b) {
			valorB += valor;
		}
		if(Long.compare(Long.valueOf(valorA), Long.valueOf(valorB)) >= 0) {
			return true;
		}
		return false;
	}

	private static List<String> somaRetornos(List<ListaDeListas> retornos, Integer qtdCaracteresBase) {
		List<String> resultado;
		
		if(retornos.size() == 1 || retornos.size() == 0) {
			if(retornos.size() == 1) {
				return retornos.get(0).getLista();
			} else {
				return Collections.emptyList();
			}
		} else {
			while(retornos.size() > 1) {
				resultado = new ArrayList<String>();
				List<String> listaA = removeInicio1OuFim2RetornandoValorRemovidoListaListas(retornos, 1);
				List<String> listaB = removeInicio1OuFim2RetornandoValorRemovidoListaListas(retornos, 1);
				resultado.addAll(somaAritmeticaDeListas(listaA, listaB, qtdCaracteresBase));
				retornos.add(0, new ListaDeListas(resultado));
			}
		}
		
		return retornos.get(0).getLista();
	}

	public static void incrementoDefaultNoFinalDaLista(List<String> x, Integer qtdCaracteresBase){
		String valor = "";
		for(int i = 1; i <= qtdCaracteresBase; i++) {
			valor += "0";
		}
		x.add(valor);
	}

	/**
	 * @param qtdCaracteresBase
	 * @param resultadoFinal
	 * @param listaMaiorQueUm
	 * @param listaTamUm
	 * @param buffer
	 * @return 
	 */
	private static List<String> multiplicaListaTamMaiorPorListaTamIgualUm(Integer qtdCaracteresBase,
			List<BigDecimal> listaMaiorQueUm, List<BigDecimal> listaTamUm,
			List<String> buffer) {
		List<String> resultadoFinal = new ArrayList<String>();
		List<String> resultBporA = null;
		String valorEmBuffer = null;
		for(int i = listaMaiorQueUm.size() - 1; i >= 0; i--) {
			resultBporA = new ArrayList<String>();
			if(!buffer.isEmpty()) {
				valorEmBuffer = removeInicio1OuFim2RetornandoValorRemovido(buffer, 1);
			} else {
				valorEmBuffer = null;
			}
			resultBporA.addAll(manipulaStringRetornandoUmaListaDoNumero(
					multiplicacao(listaTamUm.get(0), listaMaiorQueUm.get(i)).toString(), qtdCaracteresBase));
			somaParaAlgoritmoMultiplicacao(resultBporA, valorEmBuffer, qtdCaracteresBase);
			if(i != 0) {
				if(resultBporA.size() > 1) {
					buffer.add(resultBporA.get(0));
					resultadoFinal.add(0, resultBporA.get(1));
				} else {
					resultadoFinal.add(0, resultBporA.get(0));
				}
			} else {
				resultadoFinal.addAll(0, resultBporA);
			}
		}
		return resultadoFinal;
	}
	
	public static void somaParaAlgoritmoMultiplicacao(List<String> resultBporA, String valorEmBuffer, Integer qtdCaracteresBase) {
		if(valorEmBuffer != null) {
			if(!valorEmBuffer.isEmpty() && !valorEmBuffer.isBlank()) {
				BigDecimal valorB = BigDecimal.valueOf(Long.parseLong(valorEmBuffer));
				if(resultBporA.size() > 1) {
					BigDecimal valorA = BigDecimal.valueOf(Long.parseLong(resultBporA.get(1)));
					BigDecimal soma = valorA.add(valorB);
					List<String> resultadoSoma = manipulaStringRetornandoUmaListaDoNumero(soma.toString(), qtdCaracteresBase);
					if(resultadoSoma.size() > 1) {
						resultBporA.remove(1);
						resultBporA.add(resultadoSoma.get(1));
						resultBporA.add(0, somaString(resultadoSoma.get(0), resultBporA.get(0)));
						resultBporA.remove(1);
					} else {
						resultBporA.remove(1);
						resultBporA.addAll(resultadoSoma);
					}
				} else {
					BigDecimal valorA = BigDecimal.valueOf(Long.parseLong(resultBporA.get(0)));
					BigDecimal soma = valorA.add(valorB);
					List<String> resultadoSoma = manipulaStringRetornandoUmaListaDoNumero(soma.toString(), qtdCaracteresBase);
					resultBporA.remove(0);
					resultBporA.addAll(resultadoSoma);
				}
			}
		}
	}
	
	public static List<String> somaAritmeticaDeListas(List<String> a, List<String> b, Integer qtdCaracteresBase){
		String numeroA = transformaListaStringEmUmaString(a);
		String numeroB = transformaListaStringEmUmaString(b);
		Long soma = Long.sum(Long.valueOf(numeroA), Long.valueOf(numeroB));
		
		return manipulaStringRetornandoUmaListaDoNumero(soma.toString(), qtdCaracteresBase);
	}
	
	
	private static String transformaListaStringEmUmaString(List<String> a) {
		String numero = "";
		for(String x : a) {
			numero += x;
		}
		return numero;
	}

	public static void igualarTamanho(List<String> a, List<String> b, Integer qtdCaracteresBase) {
		if(a.size() > b.size()) {
			while(b.size() < a.size()) {
				String valorDefault = "";
				for(int i = 1; i <= qtdCaracteresBase; i++) {
					valorDefault += "0";
				}
				b.add(0, valorDefault);
			}
		} else if(b.size() > a.size()) {
			while(a.size() < b.size()) {
				String valorDefault = "";
				for(int i = 1; i <= qtdCaracteresBase; i++) {
					valorDefault += "0";
				}
				a.add(0, valorDefault);
			}
		}
		
	}

	public static String somaString(String a, String b) {
		BigDecimal valorA = BigDecimal.valueOf(Long.parseLong(a));
		BigDecimal valorB = BigDecimal.valueOf(Long.parseLong(b));
		return valorA.add(valorB).toString();
	}

	public static BigDecimal multiplicacao(BigDecimal a, BigDecimal b) {
		return BigDecimal.valueOf(Math.multiplyExact(a.longValue(), b.longValue()));
	}
	
	private static String removeInicio1OuFim2RetornandoValorRemovido(List<String> lista, int inicioOuFim){
		String valor = "";
		if(inicioOuFim != 1 || inicioOuFim != 2) {
			inicioOuFim = 2;
		}
		
		if(inicioOuFim == 1) {
			valor += lista.get(0);
			lista.remove(0);
		} else if(inicioOuFim == 2) {
			valor += lista.get(lista.size() - 1);
			lista.remove(lista.size() - 1);
		}
		
		return valor;
	}
	
	private static List<String> removeInicio1OuFim2RetornandoValorRemovidoListaListas(List<ListaDeListas> lista, int inicioOuFim){
		List<String> valor = new ArrayList<String>();
		if(inicioOuFim != 1 || inicioOuFim != 2) {
			inicioOuFim = 2;
		}
		
		if(inicioOuFim == 1) {
			valor.addAll(lista.get(0).getLista());
			lista.remove(0);
		} else if(inicioOuFim == 2) {
			valor.addAll(lista.get(lista.size() - 1).getLista());
			lista.remove(lista.size() - 1);
		}
		
		return valor;
	}
}
