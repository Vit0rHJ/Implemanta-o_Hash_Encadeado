
// implementação com a inserção de 1000 elementos em uma matriz de 100 posições, em caso de colisão realizar a implementação da lista encadeada na posição.
 //Utilize random para gerar números aleatórios para completar a matriz.
//Use seed = 1090. 
//E intervalo de 0 a 100 000 para os números aleatórios. 
//Ao fim, imprima a maior lista encadeada, conte quantas listas encadeadas foram geradas, quantas posições ficaram vazias e guarde os três últimos valores que foram inseridos em listas encadeadas. 
//Sem a utilização de comandos prontos da api do java, apenas, if, else, for, while e random. 



import java.util.Random;

// Criação da estrutura do No
class No {
    int chave;
    No prox;
    
    No(int chave) {
        this.chave = chave;
        this.prox = null;
    }
}

public class TabelaHashEncadeamento {
    public static void main(String[] args) {
        int tamanho = 100;
        // Tabela é um vetor de referências para o primeiro No de cada lista
        No[] tabela = new No[tamanho]; 

        Random random = new Random(1090);
        int[] ultimosValores = new int[3];
        int quantidadeValoresGuardados = 0;

        // Inserção com Encadeamento Ordenado
        for (int k = 0; k < 1000; k++) {
            int chave = random.nextInt(100001);
            
            int i = chave % tamanho; // Função hash

            if (tabela[i] == null) {
                // Posição vazia: cria o primeiro nó da lista
                tabela[i] = new No(chave);
            } else {
                // Posição ocupada: colisão detetada. Inserir na lista de forma ordenada
                if (quantidadeValoresGuardados < 3) {
                    ultimosValores[quantidadeValoresGuardados] = chave;
                    quantidadeValoresGuardados++;
                } else {
                    ultimosValores[0] = ultimosValores[1];
                    ultimosValores[1] = ultimosValores[2];
                    ultimosValores[2] = chave;
                }
                
                No atual = tabela[i];
                No anterior = null;
                
                // Percorre a lista para encontrar a posição correta em crescente
                while (atual != null && atual.chave < chave) {
                    anterior = atual;
                    atual = atual.prox;
                }
                
                No novoNo = new No(chave);
                if (anterior == null) {
                    // Insere no início da lista ligada
                    novoNo.prox = tabela[i];
                    tabela[i] = novoNo;
                } else {
                    // Insere no meio ou no fim da lista ligada
                    novoNo.prox = atual;
                    anterior.prox = novoNo;
                }
            }
        }

        int maiorLista = 0;
        int indiceMaiorLista = -1;
        int quantidadeListasEncadeadas = 0;
        int posicoesVazias = 0;

        for (int j = 0; j < tamanho; j++) {
            if (tabela[j] == null) {
                posicoesVazias++;
            } else {
                int quantidadeNos = 0;
                No atual = tabela[j];

                while (atual != null) {
                    quantidadeNos++;
                    atual = atual.prox;
                }

                if (quantidadeNos > 1) {
                    quantidadeListasEncadeadas++;
                }

                if (quantidadeNos > maiorLista) {
                    maiorLista = quantidadeNos;
                    indiceMaiorLista = j;
                }
            }
        }

        System.out.println("\nMaior lista encadeada (posição " + indiceMaiorLista + "):");
        No atual = tabela[indiceMaiorLista];
        while (atual != null) {
            System.out.print(atual.chave + " ");
            atual = atual.prox;
        }

        System.out.println("\n\nQuantidade de listas encadeadas geradas: " + quantidadeListasEncadeadas);
        System.out.println("Quantidade de posições vazias: " + posicoesVazias);
        System.out.println("\nTrês últimos valores inseridos em listas encadeadas:");
        if (quantidadeValoresGuardados == 0) {
            System.out.println("Nenhum valor foi inserido em uma lista encadeada.");
        } else {
            for (int j = 0; j < quantidadeValoresGuardados; j++) {
                System.out.println(ultimosValores[j]);
            }
        }
    }
}