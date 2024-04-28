import java.util.Scanner;

public class Main{

    public static void main(String [] agrs){
     Scanner ler = new Scanner(System.in);


       Matriz mat1;
       int det, vezes, ordem, det02, det03;
       long inicio, fim, resultado, resultado02, inicio02, fim02, inicioOtm01,fimOtm01, resultadoOtm01, inicioOtm02, fimOtm02, resultadoOtm02,inicioOtm03, fimOtm03, resultadoOtm03,inicioOtm04, fimOtm04, resultadoOtm04,temp01, temp02, media01, media02, media03,media04, tempOtm01, mediaOtm01, tempOtm02, mediaOtm02,tempOtm03, mediaOtm03,tempOtm04, mediaOtm04;
 	tempOtm02 = 0;
 	tempOtm01 = 0;
	tempOtm03 = 0;
 	tempOtm04 = 0;
 	temp01 = 0;
	temp02 = 0;
     

        System.out.println("insira a ordem da matriz quadrada: ");
        ordem = ler.nextInt();
       
        mat1 = new Matriz(ordem, ordem);

        System.out.println("insira o numero de vezes que deseja repetir a operacao: ");
        vezes = ler.nextInt();
            
        for(int cont = 0; cont < vezes; cont++){
                        
            mat1.inicializaRandomico();
                                
            mat1.imprime();

            inicio = System.currentTimeMillis();
            det = mat1.determinante();
            fim = System.currentTimeMillis();
            resultado = fim - inicio;

            inicio02 = System.nanoTime();
            det = mat1.determinante();
            fim02 = System.nanoTime();
            resultado02 = fim02 - inicio02;

            temp01 += resultado;
            temp02 += resultado02;

            System.out.println("determinante: " + det);
            System.out.println("Tempo em mili: " + resultado);
            System.out.println("Tempo em nano: " + resultado02);

            System.out.println();

            // otimizaçao 01

            inicioOtm01 = System.currentTimeMillis();
            det02 = mat1.determinante02();
            fimOtm01 = System.currentTimeMillis();
            resultadoOtm01 = fimOtm01 - inicioOtm01;

            inicioOtm02 = System.nanoTime();
            det02 = mat1.determinante02();
            fimOtm02 = System.nanoTime();
            resultadoOtm02 = fimOtm02 - inicioOtm02;

            tempOtm01 += resultadoOtm01;
            tempOtm02 += resultadoOtm02;

            System.out.println("determinante Otimizacao01: " + det02);
            System.out.println("Tempo em mili: "  + resultadoOtm01);
            System.out.println("Tempo em nano: " + resultadoOtm02);          
        
             
           System.out.println();

	    // otimizaçao 02

            inicioOtm03 = System.currentTimeMillis();
            det03 = mat1.determinante03();
            fimOtm03 = System.currentTimeMillis();
            resultadoOtm03 = fimOtm03 - inicioOtm03;

            inicioOtm04 = System.nanoTime();
            det03 = mat1.determinante03();
            fimOtm04 = System.nanoTime();
            resultadoOtm04 = fimOtm04 - inicioOtm04;

            tempOtm03 += resultadoOtm03;
            tempOtm04 += resultadoOtm04;

            System.out.println("determinante Otimizacao02: " + det03);
            System.out.println("Tempo em mili: "  + resultadoOtm03);
            System.out.println("Tempo em nano: " + resultadoOtm04);          
        }
             
           System.out.println();

       media01 = (temp01/vezes);
       media02 = (temp02/vezes);

       mediaOtm01 = (tempOtm01/ vezes);
       mediaOtm02 = (tempOtm02/ vezes);

       mediaOtm03 = (tempOtm03/ vezes);
       mediaOtm04 = (tempOtm04/ vezes);

       System.out.println("Media do tempo em mili: " + media01);
       System.out.println("Media do tempo em nano: " + media02);

       System.out.println("Media do tempo em mili determinante Otimizacao01: "+ mediaOtm01);
       System.out.println("Media do tempo em nano determinante Otimizacao01: "+ mediaOtm02);
           
       System.out.println("Media do tempo em mili determinante Otimizacao02: "+ mediaOtm03);
       System.out.println("Media do tempo em nano determinante Otimizacao02: "+ mediaOtm04);
       
        ler.close();
    }
}