import java.util.Random;
import java.util.Scanner;

public class main{

    static int MAXIMO_TEMPO_EXECUCAO = 65535;

    static int numProcessos = 3;  

    public static void main(String[] args) {

    int[] tempo_execucao = new int[numProcessos];
    int[] tempo_chegada = new int[numProcessos];
    int[] prioridade = new int[numProcessos];
    int[] tempo_espera = new int[numProcessos];
    int[] tempo_restante = new int[numProcessos];
    
    
    Scanner teclado = new Scanner (System.in);
    
    
    popular_processos(tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);
    
    imprime_processos(tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);
    
    int escolha = 1;

        while (escolha!=9) { //MENU
            System.out.println("Escolha o Algoritmo: "+"\n"+
                                "1) FCFS"+"\n"+
                                "2) SJF Preemtivo"+"\n"+
                                "3) SJF Não-Preemtivo"+"\n"+
                                "4) Prioridade Preemtivo"+"\n"+
                                "5) Prioridade Não-Preemtivo"+"\n"+
                                "6) Round-Robin "+"\n"+
                                "7) Imprimir Lista de processos"+"\n"+
                                "8) Popular processos novamente"+"\n"+
                                "9) Sair do Programa"+"\n");
                                System.out.print("Digite: ");
                escolha = teclado.nextInt();
        
                if(escolha==1){
                    FCFS(tempo_execucao, tempo_espera, tempo_restante, tempo_chegada);
                }
                if(escolha==2){
                    SJF(true, tempo_execucao, tempo_espera, tempo_restante, tempo_chegada);
                }
                if(escolha==3){
                    SJF(false,tempo_execucao, tempo_espera, tempo_restante, tempo_chegada);
                }
                if(escolha==4){
                    PRIORIDADE(true, tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);
                }
                if(escolha==5){
                    PRIORIDADE(false, tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);
                }
                if(escolha==6){
                    //Round_Robin(tempo_execucao, tempo_espera, tempo_restante);
                }
                if(escolha==7){
                    imprime_processos(tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);
                }
                if(escolha==8){
                    popular_processos(tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);
                    imprime_processos(tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);
                }
                if(escolha==9){
                    System.out.println("\n"+"Você está saindo do programa...."+"\n"+"Volte Sempre :)"+"\n");
                    break;
                }
                if(escolha>9 || escolha<1){
                    System.out.println("\n"+"Escolha inválida. Tente novamente.");
                }
            }
        
    }

    public static void popular_processos(int[] tempo_execucao, int[] tempo_espera, int[] tempo_restante, int[] tempo_chegada,  int [] prioridade ){
        Random random = new Random();
        Scanner teclado = new Scanner (System.in);
        int aleatorio;
        System.out.println("--------------------------------");
        System.out.println("Será aleatório?"+"\n"+"1 - Sim "+"\n"+"2 - Não"+"\n");
        System.out.print("Digite: ");
        aleatorio =  teclado.nextInt();

        for (int i = 0; i < numProcessos; i++) {
            //Popular Processos Aleatorio
            if (aleatorio == 1){
                tempo_execucao[i] = random.nextInt(10)+1;
                tempo_chegada[i] = random.nextInt(10)+1;
                prioridade[i] = random.nextInt(15)+1;
            }
            //Popular Processos Manual
            else {
                System.out.print("Digite o tempo de execução do processo["+i+"]:  ");
                tempo_execucao[i] = teclado.nextInt();
                System.out.print("Digite o tempo de chegada do processo["+i+"]:  ");
                tempo_chegada[i] = teclado.nextInt();
                System.out.print("Digite a prioridade do processo["+i+"]:  ");
                prioridade[i] = teclado.nextInt();
            }
            tempo_restante[i] = tempo_execucao[i];
    
        }
    }

    public static void imprime_processos(int[] tempo_execucao, int[] tempo_espera, int[] tempo_restante, int[] tempo_chegada,  int []prioridade){
        System.out.println("--------------------------------");
        //Imprime lista de processos
    for (int i = 0; i < numProcessos; i++) {
        System.out.println("Processo["+i+"]: tempo_execucao = "+ tempo_execucao[i] + " tempo_restante = "+tempo_restante[i] + " tempo_chegada = " + tempo_chegada[i] + " prioridade = " +prioridade[i]);
    }
    System.out.println("--------------------------------");
    }

    public static void imprime_stats (int[] espera) {
        int[] tempo_espera = espera.clone();
        //Implementar o calculo e impressão de estatisticas
        
        double tempo_espera_total = 0;
        
        for(int i=0; i<numProcessos; i++){ 
            System.out.println("Processo["+i+"]: tempo_espera = "+tempo_espera[i]);
            tempo_espera_total = tempo_espera_total + tempo_espera[i];
        }

        System.out.println("\n"+"Tempo médio de espera: "+(tempo_espera_total/numProcessos));
        System.out.println("--------------------------------");
        
    }

    //------------------FCFS--------------------
    
    public static void FCFS(int[] execucao, int[] espera, int[] restante, int[] chegada){
        // Clonando arrays para evitar alterações indesejadas nos argumentos originais
        int[] tempo_execucao = execucao.clone();
        int[] tempo_espera = espera.clone();
        int[] tempo_restante = restante.clone();
        //int[] tempo_chegada = chegada.clone();

        int processo_em_execucao = 0; //processo inicial no FIFO é o zero

        System.out.println("--------------------------------");

        //implementar código do FCFS
        for (int i=1; i<MAXIMO_TEMPO_EXECUCAO; i++) {
            System.out.println("tempo["+i+"]: processo["+processo_em_execucao+"] restante = "+tempo_restante[processo_em_execucao]);
            
            if (tempo_execucao[processo_em_execucao] == tempo_restante[processo_em_execucao])
                tempo_espera[processo_em_execucao] = i-1;

            if (tempo_restante[processo_em_execucao] == 1) {
                if (processo_em_execucao == (numProcessos-1))
                    break;
                else
                    processo_em_execucao++;
            }
            else
                tempo_restante[processo_em_execucao]--;
            
        }
        System.out.println("--------------------------------");

        imprime_stats(tempo_espera);
    }

    //------------------SFJ--------------------

    public static void SJF(boolean preemptivo, int[] execucao, int[] espera, int[] restante, int[] chegada) {
        int[] tempo_execucao = execucao.clone();
        int[] tempo_espera = espera.clone();
        int[] tempo_restante = restante.clone();
        int[] tempo_chegada = chegada.clone();
    
        int tempoAtual = 1;  // Tempo atual de execução
        int processosCompletos = 0; // Contador de processos concluídos

        System.out.println("--------------------------------");
    
        while (processosCompletos < numProcessos) {
            int menor_tempo_execucao = Integer.MAX_VALUE;
            int processo_menor_tempo = -1;
    
            for (int i = 0; i < numProcessos; i++) {
                if (tempo_chegada[i] <= tempoAtual && tempo_restante[i] < menor_tempo_execucao && tempo_restante[i] > 0) {
                    menor_tempo_execucao = tempo_restante[i];
                    processo_menor_tempo = i;
                }
            }
    
            if (processo_menor_tempo == -1) {
                System.out.println("\n"+"tempo[" + tempoAtual + "]: nenhum processo está pronto");
                tempoAtual++;
                continue;
            }
    
            if (preemptivo) {
                // Verifica se há um processo com tempo restante menor que o processo atual em execução
                for (int i = 0; i < numProcessos; i++) {
                    if (i != processo_menor_tempo && tempo_chegada[i] <= tempoAtual && tempo_restante[i] < tempo_restante[processo_menor_tempo] && tempo_restante[i] > 0) {
                        System.out.println("\n"+"tempo[" + tempoAtual + "]: processo[" + processo_menor_tempo + "] interrompido");
                        processo_menor_tempo = i;
                    }
                }
            }
    
            // Execução do processo
            tempo_restante[processo_menor_tempo]--;
            System.out.println("\n"+"tempo[" + tempoAtual + "]: processo[" + processo_menor_tempo + "] restante = " + tempo_restante[processo_menor_tempo]);
    
            if (tempo_restante[processo_menor_tempo] == 0) {
                processosCompletos++;
    
                // Calcula o tempo de espera para o processo concluído
                int tempo_espera_processo = tempoAtual - tempo_execucao[processo_menor_tempo] + 1 - tempo_chegada[processo_menor_tempo];
                tempo_espera[processo_menor_tempo] = tempo_espera_processo >= 0 ? tempo_espera_processo : 0;
            }
    
            tempoAtual++;
        }
        System.out.println("--------------------------------");
        imprime_stats(tempo_espera);
    }

    //------------------PRIORIDADE--------------------

    public static void PRIORIDADE(boolean preemptivo, int[] execucao, int[] espera, int[] restante, int[] chegada, int[] prioridade){
    	int[] tempo_execucao = execucao.clone();
        int[] tempo_espera = espera.clone();
        int[] tempo_restante = restante.clone();
        int[] tempo_chegada = chegada.clone();
        int[] prioridade_temp = prioridade.clone();

        int processosCompletos=0;
        int tempoAtual=1;

        System.out.println("--------------------------------");

        while (processosCompletos < numProcessos) { // Loop principal que executa até que todos os processos sejam concluídos
            int maior_prioridade = Integer.MAX_VALUE; // Inicialização com um valor grande para encontrar a menor prioridade
            int processo_maior_prioridade = -1; // Índice do processo com a maior prioridade

            for (int i = 0; i < numProcessos; i++) { // Encontra o processo com a maior prioridade pronto para execução no momento

                if (tempo_chegada[i] <= tempoAtual && prioridade_temp[i] < maior_prioridade && tempo_restante[i] > 0) {
                maior_prioridade = prioridade_temp[i];
                processo_maior_prioridade = i;
                }
            }
            // Se nenhum processo está pronto para execução, avança o tempo e continua o loop
            if (processo_maior_prioridade == -1) {
                System.out.println("\n"+"tempo[" + tempoAtual + "]: nenhum processo está pronto");
                tempoAtual++;
                continue;
            }
            // Se o escalonamento é preemptivo, verifica se há um processo com prioridade mais alta
            if (preemptivo) {
                // Verifica se há um processo com prioridade mais alta do que o processo atual em execução
                for (int i = 0; i < numProcessos; i++) {
                    if (i != processo_maior_prioridade && tempo_chegada[i] <= tempoAtual && prioridade_temp[i] < prioridade_temp[processo_maior_prioridade] && tempo_restante[i] > 0) {
                        System.out.println("\n"+"tempo[" + tempoAtual + "]: processo[" + processo_maior_prioridade + "] interrompido");
                        processo_maior_prioridade = i;
                    }
                }
            }
            // Executa o processo com a maior prioridade encontrado
            tempo_restante[processo_maior_prioridade]--;
            System.out.println("\n"+"tempo[" + tempoAtual + "]: processo[" + processo_maior_prioridade + "] restante = " + tempo_restante[processo_maior_prioridade]);

            // Verifica se o processo foi concluído
            if (tempo_restante[processo_maior_prioridade] == 0) {
                processosCompletos++;

                // Calcula o tempo de espera para o processo concluído
                int tempo_espera_processo = tempoAtual - tempo_execucao[processo_maior_prioridade] + 1 - tempo_chegada[processo_maior_prioridade];
                tempo_espera[processo_maior_prioridade] = tempo_espera_processo >= 0 ? tempo_espera_processo : 0;
            }

            // Incrementa o tempo atual para a próxima iteração do loop
            tempoAtual++;
        }

        System.out.println("--------------------------------");

        // Imprime os tempos de espera calculados para cada processo
        imprime_stats(tempo_espera);
    }

    
    public static void Round_Robin(int[] execucao, int[] espera, int[] restante){
        int[] tempo_execucao = execucao.clone();
        int[] tempo_espera = espera.clone();
        int[] tempo_restante = restante.clone();

        // Definindo o quantum para o algoritmo Round-Robin
        int quantum = 2; // Pode ser ajustado conforme necessário

        // Inicialização de variáveis de controle
        int tempo_atual = 0; // Tempo atual de execução
        int processosCompletos = 0; // Contador de processos concluídos
        
        
        imprime_stats(tempo_espera);
    }
}