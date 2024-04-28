import java.util.Random;

public class Matriz{

    private int[][] mat;
    private int tamLinha;
    private int tamColuna;

    Matriz(){
        mat = new int[6][6];
        this.setTamanhoLinha(6); 
        this.setTamanhoColuna(6);
    }

    Matriz(int numLinhas, int numColunas){
        mat = new int[numLinhas][numColunas];
        this.setTamanhoLinha(numLinhas); 
        this.setTamanhoColuna(numColunas);
    }


    public int getValor(int indiceI,int indiceJ){
        return mat[indiceI][indiceJ];
    } 
    
    public void setValor(int indiceI,int indiceJ, int novoValor){
        mat[indiceI][indiceJ] = novoValor;
    }

    public int getTamanhoLinha(){
        return tamLinha;
    } 
    
    public int getTamanhoColuna(){
        return tamColuna;
    } 

    private void setTamanhoLinha(int novoValor){
        tamLinha = novoValor;
    }

    private void setTamanhoColuna(int novoValor){
        tamColuna = novoValor;
    }
 


    public void imprime(){
        int conti,contj; 
        for(conti = 0; conti < this.getTamanhoLinha(); conti++){
            System.out.println();

            for(contj = 0; contj < this.getTamanhoColuna(); contj++){
                System.out.print(this.getValor(conti,contj) + " ");
            }
        }
        System.out.println(); 
    }

    public void inicializaRandomico(){
        int conti,contj, novoValor;
        int ordem = this.getTamanhoLinha();
        Random gerador = new Random();

        for(conti = 0; conti < this.getTamanhoLinha(); conti++){
            for(contj = 0; contj < this.getTamanhoColuna(); contj++){
                novoValor = gerador.nextInt(ordem);
                this.setValor(conti,contj,novoValor);
            }
        }
    }

    // caso matriz nao quadrada, retorna -1
    public int retorneOrdem(){
        int numL, numC, ordem;

        numL = this.getTamanhoLinha();
        numC = this.getTamanhoColuna();
        ordem = -1;
        if(numL == numC){
            ordem = numL;
        }

        return ordem;
    } 



    private int detOrdem1(Matriz mat){
        return mat.getValor(0,0);
    }
    
    private int detOrdem2(Matriz mat){
        int diagonalP, diagonalI;

        diagonalP = mat.getValor(0,0) * mat.getValor(1,1);  
        diagonalI = mat.getValor(1,0) * mat.getValor(0,1);  

        return (diagonalP - diagonalI);
    }


    private int calculaSinal(int indiceL, int indiceC){
        int sinal;

        sinal = -1;

        if( ((indiceL + indiceC)% 2) == 0 ){
            sinal = 1;
        }

        return sinal;  
    }

    public void copiaMatrizMaiorParaMenor(Matriz maior,Matriz menor,int isqn,int jsqn){

        int contAi,contAj,contBi,contBj,temp,numL,numC;
        numL = menor.getTamanhoLinha();
        numC = menor.getTamanhoColuna();

        contAi = 0;
        for(contBi = 0; contBi < numL; contBi++){
            if(contAi == isqn){
                contAi++;
            }

            contAj = 0;
            for(contBj = 0; contBj < numC; contBj++){
                if(contAj == jsqn){
                contAj++;
                }
                temp = maior.getValor(contAi,contAj);
                menor.setValor(contBi,contBj,temp);
                contAj++;
            }
            contAi++;
        }
    }

    private int detOrdemN(Matriz mat){
        int sinal,cofator,detTemp,resposta,contL,contC,numL,numC;
        Matriz matmenor;
        numL = this.getTamanhoLinha();
        numC = this.getTamanhoColuna();
        resposta = 0;
        contL = 0;
        
        for(contC = 0; contC < mat.getTamanhoColuna(); contC++){
            cofator = mat.getValor(contL,contC);
            sinal = this.calculaSinal(contL,contC);
            matmenor = new Matriz(numL-1,numC-1);
            this.copiaMatrizMaiorParaMenor(mat,matmenor,contL,contC);
            detTemp = matmenor.determinante();
            resposta = resposta + (cofator * sinal * detTemp);
        }
        return (resposta);
    }


     // achar a linha com mais zeros

    public int linhasComZero(){
        int conti, linhaComMaisZeros, maxZeros, numZeros; 
        maxZeros = 0;
        linhaComMaisZeros = 0;

        for(conti = 0; conti < this.getTamanhoLinha(); conti++){
            numZeros = this.contaLinha(conti);

            if(numZeros > maxZeros){
                maxZeros = numZeros;
                linhaComMaisZeros = conti;
            }

        }
        return(linhaComMaisZeros);
    }

    public int contaLinha(int i){
        int contj;
        int zerosNaLinha = 0;
        for(contj = 0; contj < this.getTamanhoColuna(); contj++){
            if(this.getValor(i, contj) == 0){
                zerosNaLinha++;
            } 
        }

        return(zerosNaLinha);
    }


	 // achar a coluna com mais zeros

    public int colunasComZero(){
        int contj, colunaComMaisZeros, maxZeros; 
        maxZeros = 0;
        colunaComMaisZeros = 0;

        for(contj = 0; contj < this.getTamanhoLinha(); contj++){
            int numZeros = this.contaColuna(contj);
                
            if(numZeros > maxZeros){
                maxZeros = numZeros;
                colunaComMaisZeros = contj;
            }

        }
        return(colunaComMaisZeros);
    }
    
    public int contaColuna(int j){
        int conti, zerosNaColuna;
        zerosNaColuna = 0;
        for(conti = 0; conti < this.getTamanhoColuna(); conti++){
                if(this.getValor(conti, j) == 0){
                    zerosNaColuna++;
                }
            }
        
        return(zerosNaColuna);
    }


	// otimizaçao 01 
    

    public int detOtimiza01(Matriz mat){
       int sinal,cofator,detTemp,resposta,contL,contC,numL,numC;
        Matriz matmenor;
        numL = this.getTamanhoLinha();
        numC = this.getTamanhoColuna();
        resposta = 0;
            
            if(this.contaColuna(this.colunasComZero()) > this.contaLinha(this.linhasComZero())){
                contC = this.colunasComZero();
                for(contL = 0; contL < mat.getTamanhoLinha(); contL++){
                    cofator = mat.getValor(contL,contC);

                    if(cofator != 0){
                        sinal = this.calculaSinal(contL,contC);
                        matmenor = new Matriz(numL-1,numC-1);
                        this.copiaMatrizMaiorParaMenor(mat,matmenor,contL,contC);
                        detTemp = matmenor.determinante();
                        resposta = resposta + (cofator * sinal * detTemp);
                    }
                }
            }
            else{
                contL = this.linhasComZero();
                for(contC = 0; contC < mat.getTamanhoColuna(); contC++){
                    cofator = mat.getValor(contL,contC);

                    if(cofator != 0){
                        sinal = this.calculaSinal(contL,contC);
                        matmenor = new Matriz(numL-1,numC-1);
                        this.copiaMatrizMaiorParaMenor(mat,matmenor,contL,contC);
                        detTemp = matmenor.determinante();
                        resposta = resposta + (cofator * sinal * detTemp);
                    }
                }
            }
            

        
        return (resposta);

    }


    // igualdade ou proporcionalidade
    
   private boolean comparaLinhas(int linha1, int linha2) {
		boolean linhasIguais = true;
		int coluna = 0;
		while (coluna < this.getTamanhoColuna() && linhasIguais) {
			if (this.getValor(linha1, coluna) != this.getValor(linha2, coluna)) {
				linhasIguais = false;
			}
        	coluna++;
  		}
		return(linhasIguais);
	}

	private boolean comparaColunas(int coluna1, int coluna2){
		boolean colunasIguais = true;
		int linha = 0;
		while (linha < this.getTamanhoLinha() && colunasIguais) {
			if (this.getValor(linha, coluna1) != this.getValor(linha, coluna2)) {
				colunasIguais = false;
			}
        	linha++;
  		}
		return(colunasIguais);
	}

	private boolean verificaLinhasOuColunasIguais() {
		boolean encontrouIguais = false;
		int linha1 = 0;
		
		while (encontrouIguais == false && linha1 < this.getTamanhoLinha()) {
			int linha2 = linha1 + 1;
			while (encontrouIguais == false && linha2 < this.getTamanhoLinha()) {
				if (this.comparaLinhas(linha1, linha2)) {
					encontrouIguais = true;
				}
				linha2++;
			}
			linha1++;
		}

		if (encontrouIguais == false) {
			int coluna1 = 0;
			while (encontrouIguais == false && coluna1 < this.getTamanhoColuna()) {
				int coluna2 = coluna1 + 1;
				while (encontrouIguais == false && coluna2 < this.getTamanhoColuna()) {
					if (this.comparaColunas(coluna1, coluna2)) {
						encontrouIguais = true;
					}
					coluna2++;
				}
				coluna1++;
			}
		}

		return(encontrouIguais);
	}

	private boolean linhasProp(int linha1, int linha2){
		int valor1 = this.getValor(linha1, 0);
		int valor2 = this.getValor(linha2, 0);
		int escalar = 0;
		boolean ehProp = true;
		// Verifique se o primeiro elemento da segunda linha não é zero
		if (valor2 == 0) {
			escalar = 0; // Evita divisão por zero
		}else{
			escalar = valor1 / valor2;
		}
	
		int coluna = 0;
		while(coluna < this.getTamanhoColuna() && ehProp){
			int elemento1 = this.getValor(linha1, coluna);
			int elemento2 = this.getValor(linha2, coluna);
			if(elemento2 * escalar != elemento1){
				ehProp = false;
			}
			coluna++;
		}

		return(ehProp);
	}
	
	private boolean colunasProp(int coluna1, int coluna2){
		int valor1 = this.getValor(0, coluna1);
		int valor2 = this.getValor(0, coluna2);
		int escalar = 0;
		boolean ehProp = true;
		// Verifique se o primeiro elemento da segunda linha não é zero
		if (valor2 == 0) {
			escalar = 0; // Evita divisão por zero
		}else{
			escalar = valor1 / valor2;
		}
	
		int linha = 0;
		while(linha < this.getTamanhoLinha() && ehProp){
			int elemento1 = this.getValor(linha, coluna1);
			int elemento2 = this.getValor(linha, coluna2);
			if(elemento2 * escalar != elemento1){
				ehProp = false;
			}
			linha++;
		}

		return(ehProp);
	}
	
	private boolean verificaLinhasOuColunasProp() {
		boolean encontrouProp = false;
		int linha1 = 0;
		
		while (encontrouProp == false && linha1 < this.getTamanhoLinha()) {
			int linha2 = linha1 + 1;
			while (encontrouProp == false && linha2 < this.getTamanhoLinha()) {
				if (this.linhasProp(linha1, linha2)) {
					encontrouProp = true;
				}
				linha2++;
			}
			linha1++;
		}

		if (encontrouProp == false) {
			int coluna1 = 0;
			while (encontrouProp == false && coluna1 < this.getTamanhoColuna()) {
				int coluna2 = coluna1 + 1;
				while (encontrouProp == false && coluna2 < this.getTamanhoColuna()) {
					if (this.colunasProp(coluna1, coluna2)) {
						encontrouProp = true;
					}
					coluna2++;
				}
				coluna1++;
			}
		}

		return(encontrouProp);
	}

	// otimizaçao 02

	public int detOtimiza02(){
		int det;
		if(this.verificaLinhasOuColunasProp() || this.verificaLinhasOuColunasIguais()){
			det = 0;
		}
		else{
			det = this.determinante();
		}
		return(det);
	}



	// calculo do determinante 


    public int determinante(){
        int ordem,det;

        ordem = this.retorneOrdem();
        det = 0;

        if(ordem > 0){
            switch (ordem) {
                case 1:  det = this.detOrdem1(this);
                    break;
                case 2:  det = this.detOrdem2(this);;
                    break;
                default: det = this.detOrdemN(this);;
                    break;
            }
    
        }
        else{
        System.out.println("Matriz nao eh quadrada!! retornando 0");
        }

        return det;
    }

    public int determinante02(){
        int ordem,det;

        ordem = this.retorneOrdem();
        det = 0;

        if(ordem > 0){
            switch (ordem) {
                case 1:  det = this.detOrdem1(this);
                    break;
                case 2:  det = this.detOrdem2(this);;
                    break;
                default: det = this.detOtimiza01(this);;
                    break;
            }
    
        }
        else{
        System.out.println("Matriz nao eh quadrada!! retornando 0");
        }

        return det;
    }

    public int determinante03(){
        int ordem,det;

        ordem = this.retorneOrdem();
        det = 0;

        if(ordem > 0){
            switch (ordem) {
                case 1:  det = this.detOrdem1(this);
                    break;
                case 2:  det = this.detOrdem2(this);;
                    break;
                default: det = this.detOtimiza02();;
                    break;
            }
    
        }
        else{
        System.out.println("Matriz nao eh quadrada!! retornando 0");
        }

        return det;
    }
}



