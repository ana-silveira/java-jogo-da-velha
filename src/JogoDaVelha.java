import java.util.Scanner;

public class JogoDaVelha {
// Criação da matriz de campos:

    public static void main(String[] args) {

        Campo[][] velha = new Campo[3][3];
        char simboloAtual = 'X';
        boolean game = true;
        boolean vitoria = false;
        Scanner scan = new Scanner(System.in);


        iniciarJogo(velha);

        while (game) {
            desenhaJogo(velha);

            try {
                if (verificarJogada(velha, jogar(scan, simboloAtual), simboloAtual)) {
                    vitoria = verificarVitoria(velha);
                    if (vitoria) {
                        System.out.println();
                        desenhaJogo(velha);
                        System.out.printf("Jogador %s venceu!%n", simboloAtual);
                        break;
                    }
                    if(!verificarEmpate(velha)) {
                        System.out.println("----- Velha -----");
                        break;
                    }
                    if (simboloAtual == 'X') {
                        simboloAtual = 'O'; //troca de jogador
                    } else {
                        simboloAtual = 'X';
                    }
                }
            } catch (Exception e) {
                System.out.print("Erro");
            }
            limparTela();
        }
        System.out.print("Fim do jogo");
    }

    //redesenhar o jogo da velha com cada nova jogada:
    public static void desenhaJogo(Campo[][] velha) {

        System.out.println();
        System.out.println("    0     1     2");
        System.out.printf("0   %c  |  %c  |  %c %n", velha[0][0].getSimbolo(), velha[0][1].getSimbolo(), velha[0][2].getSimbolo());
        System.out.println("   ---------------");
        System.out.printf("1   %c  |  %c  |  %c %n", velha[1][0].getSimbolo(), velha[1][1].getSimbolo(), velha[1][2].getSimbolo());
        System.out.println("   ---------------");
        System.out.printf("2   %c  |  %c  |  %c %n", velha[2][0].getSimbolo(), velha[2][1].getSimbolo(), velha[2][2].getSimbolo());
    }

    public static void limparTela() {
        for (int cont = 0; cont < 10; cont++) {
            System.out.println();
        }
    }

    public static int[] jogar(Scanner scan, char sAtual) {
        int[] p = new int[2];
        System.out.println();
        System.out.printf("%s %c%n", "Quem joga: ", sAtual);
        System.out.print("Informa a linha: ");
        p[0] = scan.nextInt();
        System.out.print("Informa a coluna: ");
        p[1] = scan.nextInt();
        return p; // esse p vai retornar valores para a função verificaJogada
    }

    public static void iniciarJogo(Campo[][] velha) {
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                velha[l][c] = new Campo();
            }
        }
    }

    public static boolean verificarJogada(Campo[][] velha, int[] p, char simboloAtual) {
        if (velha[p[0]][p[1]].getSimbolo() == ' ') {
            velha[p[0]][p[1]].setSimbolo(simboloAtual);
            return true;
        } else {
            return false;
        }
    }

    public static boolean verificarVitoria(Campo[][] velha) {
        if(velha[0][0].getSimbolo() == velha[1][1].getSimbolo() && velha[1][1].getSimbolo() == velha[2][2].getSimbolo() && velha[1][1].getSimbolo() != ' ' ){return true;}
        if(velha[0][2].getSimbolo() == velha[1][1].getSimbolo() && velha[1][1].getSimbolo() == velha[2][0].getSimbolo() && velha[1][1].getSimbolo() != ' '){return true;}
        for(int i = 0; i < 3; i++) {
            if (velha[i][0].getSimbolo() == velha[i][1].getSimbolo() && velha[i][1].getSimbolo() == velha[i][2].getSimbolo() && velha[i][1].getSimbolo() != ' ') {
                return true;
            }
            if (velha[0][i].getSimbolo() == velha[1][i].getSimbolo() && velha[1][i].getSimbolo() == velha[2][i].getSimbolo() && velha[1][i].getSimbolo() != ' ') {
                return true;
            }
        }
        return false;
    }

    public static boolean verificarEmpate(Campo[][] velha) {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (velha[i][j].getSimbolo() == ' ') {return true;}
            }
        }
        return false;
    }
}