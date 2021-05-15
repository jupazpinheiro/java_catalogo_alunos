package aluno;

import java.util.Arrays;
import java.util.Scanner;


public class cadastro {

	private static String[] nomes;
	private static float[] av1;
	private static float[] av2;
	
	private static int index;	
	
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_RESET = "\u001B[0m";

	private static final int QTDE = 100;
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		nomes = new String[QTDE];
		av1 = new float[QTDE];
		av2 = new float[QTDE];
		
		String opcao = null;
		
		do {
			System.out.println("----------------Sistema de cadastro de notas----------------");
			System.out.println("[1] Novo aluno: ");
			System.out.println("[2] Consulta: ");
			System.out.println("[3] Exibir todos: ");
			System.out.println("[4] Deletar: ");
			System.out.println("[5] Sair");
			
			System.out.print("Informe a opcao desejada: ");
			opcao = in.next();
			
			switch (opcao) {
			case "1": 
				if(index < QTDE) {
					//revisa e atualiza a lista de acordo com o tamanho
					index = Arrays.stream(nomes).filter(e -> e!=null).toArray(String[]::new).length;
					System.out.println("Nome do aluno: ");
					nomes[index] = in.next();
					
					System.out.println("Nota da AV1: ");
					av1[index] = in.nextFloat();
	
					System.out.println("Nota da AV2:: ");
					av2[index] = in.nextFloat();

					System.out.println("Aluno cadastrado no sistema! ");
					imprimir(index);
					
				} else {
					//limita a lista em 100 elementos
					System.out.println("Não existe mais vaga para o cadastramento!!!");
				}
				
				break;

			case "2":
				System.out.print("Informe a posicao: ");
				int pos = in.nextInt();
				
				if(pos >= 0 && pos < index) {
					imprimir(pos);
				} else {
					System.out.println("Aluno inexistente!!!");
				}
				
				break;

			case "3":
				imprimir();
				
				break;
				
			case "4":
				remover();
				
				break;
							
				
			case "5":
				System.out.println("Finalizou!!!");
				break;
				
			default:
				System.out.println("Opcao invalida!!!");
				break;
			}

		} while (!opcao.equals("5"));
		
		in.close();		
	}	
	
	private static void imprimir() {
		System.out.println("Listagem de alunos:");
		for(int i = 0; i < nomes.length; i++) {
			if (nomes[i]!=null) { 
				imprimir(i);
		
			}
		}
	}
	
	private static void remover() {
		imprimir();
		System.out.println("Remover a posição do aluno: ");
		
		//recebe a input do usuario
		Scanner in = new Scanner(System.in);
		int elem = in.nextInt();
		
		//pesquisa e deleta os elementos numéricos
		for(int i = elem; i < av1.length -1; i++){
			nomes[i] = nomes[i + 1];
		    av1[i] = av1[i + 1];
		    av2[i] = av2[i + 1];
		  }

	}
	
	
	private static void imprimir(int posicao){
		
		float media = calcularMedia(posicao);
		
		System.out.printf("*%d* %s - AV1 %.2f - AV2 %.2f - Média %.2f - (%s)\n", 
				posicao, 
				nomes[posicao],
				av1[posicao],
				av2[posicao],
				media,
				getSituacao(media)
			);
	}
	
	private static float calcularMedia(int idx) {
		return (av1[idx] + av2[idx]) / 2;
	}
	
	private static String getSituacao(float sl) {
		if (sl < 4){
			return (ANSI_RED_BACKGROUND + "Reprovado" + ANSI_RESET);
		}
		if (sl >= 4 && sl < 7) {
			return (ANSI_YELLOW_BACKGROUND + "Prova Final" + ANSI_RESET);
		}
		if (sl >=7 && sl <= 10) {
			return (ANSI_BLUE_BACKGROUND + "Aprovado, parabéns!" + ANSI_RESET);
		}
		else {
			return "ERRO";
		}
	}
}