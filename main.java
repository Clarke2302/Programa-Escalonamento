import java.util.Random;
import java.util.Scanner;

public class main {

    static int MAXIMO_TEMPO_EXECUCAO = 65535;

    static int numProcessos = 3;

    public static void main(String[] args) {

        int[] tempo_execucao = new int[numProcessos];
        int[] tempo_chegada = new int[numProcessos];
        int[] prioridade = new int[numProcessos];
        int[] tempo_espera = new int[numProcessos];
        int[] tempo_restante = new int[numProcessos];


        Scanner teclado = new Scanner(System.in);


        popular_processos(tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);

        imprime_processos(tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);

        int escolha = 1;

        while (escolha != 9) { //MENU
            System.out.println("Escolha o Algoritmo: " + "\n" +
                    "1) FCFS" + "\n" +
                    "2) SJF Preemtivo" + "\n" +
                    "3) SJF Não-Preemtivo" + "\n" +
                    "4) Prioridade Preemtivo" + "\n" +
                    "5) Prioridade Não-Preemtivo" + "\n" +
                    "6) Round-Robin " + "\n" +
                    "7) Imprimir Lista de processos" + "\n" +
                    "8) Popular processos novamente" + "\n" +
                    "9) Sair do Programa" + "\n");
            System.out.print("Digite: ");
            escolha = teclado.nextInt();

            if (escolha == 1) {
                FCFS(tempo_execucao, tempo_espera, tempo_restante, tempo_chegada);
            }
            if (escolha == 2) {
                SJF(true, tempo_execucao, tempo_espera, tempo_restante, tempo_chegada);
            }
            if (escolha == 3) {
                SJF(false, tempo_execucao, tempo_espera, tempo_restante, tempo_chegada);
            }
            if (escolha == 4) {
                PRIORIDADE(true, tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);
            }
            if (escolha == 5) {
                PRIORIDADE(false, tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);
            }
            if (escolha == 6) {
                //Round_Robin(tempo_execucao, tempo_espera, tempo_restante);
            }
            if (escolha == 7) {
                imprime_processos(tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);
            }
            if (escolha == 8) {
                popular_processos(tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);
                imprime_processos(tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);
            }
            if (escolha == 9) {
                System.out.println("\n" + "Você está saindo do programa...." + "\n" + "Volte Sempre :)" + "\n");
                break;
            }
            if (escolha > 9 || escolha < 1) {
                System.out.println("\n" + "Escolha inválida. Tente novamente.");
            }
        }

    }

    public static void popular_processos(int[] tempo_execucao, int[] tempo_espera, int[] tempo_restante, int[] tempo_chegada, int[] prioridade) {
        Random random = new Random();
        Scanner teclado = new Scanner(System.in);
        int aleatorio;
        System.out.println("--------------------------------");
        System.out.println("Será aleatório?" + "\n" + "1 - Sim " + "\n" + "2 - Não" + "\n");
        System.out.print("Digite: ");
        aleatorio = teclado.nextInt();

        for (int i = 0; i < numProcessos; i++) {
            //Popular Processos Aleatorio
            if (aleatorio == 1) {
                tempo_execucao[i] = random.nextInt(10) + 1;
                tempo_chegada[i] = random.nextInt(10) + 1;
                prioridade[i] = random.nextInt(15) + 1;
            }
            //Popular Processos Manual
            else {
                System.out.print("Digite o tempo de execução do processo[" + i + "]:  ");
                tempo_execucao[i] = teclado.nextInt();
                System.out.print("Digite o tempo de chegada do processo[" + i + "]:  ");
                tempo_chegada[i] = teclado.nextInt();
                System.out.print("Digite a prioridade do processo[" + i + "]:  ");
                prioridade[i] = teclado.nextInt();
            }
            tempo_restante[i] = tempo_execucao[i];

        }
    }

    public static void imprime_processos(int[] tempo_execucao, int[] tempo_espera, int[] tempo_restante, int[] tempo_chegada, int[] prioridade) {
        System.out.println("--------------------------------");
        //Imprime lista de processos
        for (int i = 0; i < numProcessos; i++) {
            System.out.println("Processo[" + i + "]: tempo_execucao = " + tempo_execucao[i] + " tempo_restante = " + tempo_restante[i] + " tempo_chegada = " + tempo_chegada[i] + " prioridade = " + prioridade[i]);
        }
        System.out.println("--------------------------------");
    }

    public static void imprime_stats(int[] espera) {
        int[] tempo_espera = espera.clone();
        //Implementar o calculo e impressão de estatisticas

        double tempo_espera_total = 0;

        for (int i = 0; i < numProcessos; i++) {
            System.out.println("Processo[" + i + "]: tempo_espera = " + tempo_espera[i]);
            tempo_espera_total = tempo_espera_total + tempo_espera[i];
        }

        System.out.println("\n" + "Tempo médio de espera: " + (tempo_espera_total / numProcessos));
        System.out.println("--------------------------------");

    }

    //------------------FCFS--------------------

    public static void FCFS(int[] execucao, int[] espera, int[] restante, int[] chegada) {
        // Clonando arrays para evitar alterações indesejadas nos argumentos originais
        int[] tempo_execucao = execucao.clone();
        int[] tempo_espera = espera.clone();
        int[] tempo_restante = restante.clone();
        //int[] tempo_chegada = chegada.clone();

        int processoExecucao = 0; //processo inicial no FIFO é o zero

        System.out.println("--------------------------------");

        //implementar código do FCFS
        for (int i = 1; i < MAXIMO_TEMPO_EXECUCAO; i++) {
            System.out.println("tempo[" + i + "]: processo[" + processoExecucao + "] restante = " + tempo_restante[processoExecucao]);

            if (tempo_execucao[processoExecucao] == tempo_restante[processoExecucao])
                tempo_espera[processoExecucao] = i - 1;

            if (tempo_restante[processoExecucao] == 1) {
                if (processoExecucao == (numProcessos - 1))
                    break;
                else
                    processoExecucao++;
            } else
                tempo_restante[processoExecucao]--;

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

        int menorTempo = MAXIMO_TEMPO_EXECUCAO;
        int processoExecucao = -1; // Processos em execução
        int processosConcluidos = 0;
        int tempo = 0;

        System.out.println("--------------------------------");

        while(true){
            tempo++;
            //PREEMPTIVO
            if(preemptivo || processoExecucao == -1){ //se for preemptivo ou os processos estiverem iguais ao início
                for(int i=0; i <numProcessos; i++){ // loop até acabar o número de processos

                    if((tempo_restante[i] != 0) && (tempo_chegada[i] <= tempo)){ //Se o tempo restante atual for diferente de 0 e o tempo de chegada for menor ou igual ao tempo
                        if(tempo_restante[i] < menorTempo){ // se o tempo restante atual for menor que o menor tempo
                            menorTempo = tempo_restante[i]; //então o menor tempo é o tempo restante atual
                            processoExecucao = i; // e o processo que está em execução é o atual
                        }
                    } 
                }
            }
            if(processoExecucao == -1){ //se processos em execução estiver igual, nenhum processo ficou pronto
                System.out.println("tempo["+tempo+"]: nenhum processo está pronto");
            
            }else{ // NÃO PREEMPTIVO
                
                if(tempo_execucao[processoExecucao] == tempo_restante[processoExecucao])
                tempo_espera[processoExecucao] = tempo - tempo_chegada[processoExecucao];

                tempo_restante[processoExecucao]--;
                System.out.println("tempo[" + tempo + "]: processo[" + processoExecucao + "] restante = " + tempo_restante[processoExecucao]);

                if(tempo_restante[processoExecucao] == 0){
                    processoExecucao = -1;
                    menorTempo = MAXIMO_TEMPO_EXECUCAO;
                    processosConcluidos++;

                    if(processosConcluidos == numProcessos)
                    break;
                }
            }
        }
        System.out.println("--------------------------------");
        imprime_stats(tempo_espera);
    }

    //------------------PRIORIDADE--------------------

    public static void PRIORIDADE(boolean preemptivo, int[] execucao, int[] espera, int[] restante, int[] chegada, int[] prioridade) {
        int[] tempo_execucao = execucao.clone();
        int[] tempo_espera = espera.clone();
        int[] tempo_restante = restante.clone();
        int[] tempo_chegada = chegada.clone();
        int[] prioridade_ = prioridade.clone();

        int maiorPrioridade = 0; //esse programa utiliza da maior prioridade para ser o primeiro processo a ser feito
        int processoExecucao = -1;
        int processosConcluidos = 0;
        int tempo = 0;

        while(true) { 
            tempo++;

            //PREEMPTIVO
            // Escalonamento preemptivo: seleciona o processo com maior prioridade que chegou até o momento
            if (preemptivo || processoExecucao == -1) {
                for (int i=0; i<numProcessos; i++) {
                    if ((tempo_restante[i] != 0) && (tempo_chegada[i] <= tempo)) {
                        if (prioridade_[i] > maiorPrioridade) {
                            maiorPrioridade = prioridade_[i];
                            processoExecucao = i;
                        }
                    }
                }
            }
            // Verifica se nenhum processo está pronto para execução
            if (processoExecucao == -1)
            System.out.println("tempo["+tempo+"]: nenhum processo está pronto");

            else {
                // Atualiza o tempo de espera do processo que acabou de chegar à CPU
                if (tempo_restante[processoExecucao] == tempo_execucao[processoExecucao])
                    tempo_espera[processoExecucao] = tempo - tempo_chegada[processoExecucao];

                // Executa o processo por 1 unidade de tempo
                tempo_restante[processoExecucao]--;
                System.out.println("tempo[" + tempo + "]: processo[" + processoExecucao + "] restante = " + tempo_restante[processoExecucao]);

                // Verifica se o processo foi concluído
                if (tempo_restante[processoExecucao] == 0) {
                    processoExecucao = -1;
                    maiorPrioridade = 0;
                    processosConcluidos++;

                    if (processosConcluidos == numProcessos)
                        break;
                }
            }
        }
        imprime_stats(tempo_espera);
    }


        public static void Round_Robin ( int[] execucao, int[] espera, int[] restante){
            int[] tempo_execucao = execucao.clone();
            int[] tempo_espera = espera.clone();
            int[] tempo_restante = restante.clone();

            // Definindo o quantum para o algoritmo Round-Robin
            int quantum = 2; // Pode ser ajustado conforme necessário

            // Inicialização de variáveis de controle

            int tempo_atual = 0; // Tempo atual de execução
            int processosCompletos = 0; // Contador de processos concluídos

            System.out.println("--------------------------------");

            while (processosCompletos < numProcessos) {
                // Itera sobre todos os processos
                for (int i = 0; i < numProcessos; i++) {
                    // Verifica se o processo está pronto para execução e possui tempo restante
                    if (tempo_restante[i] > 0) {
                        // Executa o processo pelo quantum ou pelo tempo restante, o que for menor
                        int tempo_executado = Math.min(quantum, tempo_restante[i]);
                        tempo_restante[i] -= tempo_executado; // Atualiza o tempo restante do processo

                        // Atualiza o tempo atual de execução
                        tempo_atual += tempo_executado;
                        // Atualiza o tempo de espera dos processos que não estão em execução
                        for (int j = 0; j < numProcessos; j++) {

                            if (j != i && tempo_restante[j] > 0) {
                                tempo_espera[j] += tempo_executado;
                            }
                        }

                        // Verifica se o processo foi concluído
                        if (tempo_restante[i] == 0) {
                            processosCompletos++;
                            // Calcula o tempo de espera para o processo concluído
                            tempo_espera[i] = tempo_atual - tempo_execucao[i];
                        }

                        // Imprime o status do processo
                        System.out.println("\n" + "tempo[" + tempo_atual + "]: processo[" + i + "] restante = " + tempo_restante[i]);

                        // Verifica se todos os processos foram concluídos
                        if (processosCompletos == numProcessos) {
                            break; // Encerra o loop se todos os processos foram concluídos
                        }

                    }
                }
            }
            System.out.println("--------------------------------");
            // Imprime os tempos de espera calculados para cada processo
            imprime_stats(tempo_espera);
        }
    }