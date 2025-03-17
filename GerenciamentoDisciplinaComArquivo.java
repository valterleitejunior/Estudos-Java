package aplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GerenciamentoDisciplinaComArquivo {

    private static final int MAX_DISCIPLINAS = 100;
    private static final int COLUNAS = 5; // Nome, Nota1, Nota2, Frequência, Status
    private static final String ARQUIVO_HISTORICO = "historico_notas.txt";
    private static final String DELIMITADOR = " |";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] disciplinas = new String[MAX_DISCIPLINAS][COLUNAS];
        int totalDisciplinas = carregarHistorico(disciplinas); // Carrega dados do arquivo
        String opcao;

        do {
            exibirMenu();
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    if (totalDisciplinas < MAX_DISCIPLINAS) {
                        adicionarDisciplina(disciplinas, totalDisciplinas, scanner);
                        totalDisciplinas++;
                    } else {
                        System.out.println("Limite de disciplinas atingido!");
                    }
                    break;
                case "2":
                    System.out.print("Digite o nome da disciplina: ");
                    String nome = formatarNome(scanner.nextLine());
                    consultarDisciplinaRecursivo(disciplinas, nome, 0, totalDisciplinas);
                    break;
                case "3":
                    exibirDisciplinas(disciplinas, totalDisciplinas);
                    break;
                case "4":
                    salvarHistorico(disciplinas, totalDisciplinas);
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!opcao.equals("4"));

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Adicionar disciplina");
        System.out.println("2. Consultar disciplina");
        System.out.println("3. Exibir todas as disciplinas");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarDisciplina(String[][] disciplinas, int indice, Scanner scanner) {
        System.out.print("Digite o nome da disciplina: ");
        String nome = formatarNome(scanner.nextLine());

        System.out.print("Digite a nota da Unidade 1: ");
        double nota1 = scanner.nextDouble();

        System.out.print("Digite a nota da Unidade 2: ");
        double nota2 = scanner.nextDouble();

        System.out.print("Digite o percentual de frequência: ");
        double frequencia = scanner.nextDouble();

        scanner.nextLine(); // Limpar o buffer

        String status = determinarStatus(nota1, nota2, frequencia);

        disciplinas[indice][0] = nome;
        disciplinas[indice][1] = String.valueOf(nota1);
        disciplinas[indice][2] = String.valueOf(nota2);
        disciplinas[indice][3] = String.valueOf(frequencia);
        disciplinas[indice][4] = status;

        System.out.println("Disciplina adicionada com sucesso!");
    }

    private static void consultarDisciplinaRecursivo(String[][] disciplinas, String nome, int indice, int totalDisciplinas) {
        if (indice >= totalDisciplinas) {
            System.out.println("Disciplina não encontrada.");
            return;
        }

        if (disciplinas[indice][0].equalsIgnoreCase(nome)) {
            System.out.println("\n--- Dados da Disciplina ---");
            System.out.println("Nome: " + disciplinas[indice][0]);
            System.out.println("Nota 1: " + disciplinas[indice][1]);
            System.out.println("Nota 2: " + disciplinas[indice][2]);
            System.out.println("Frequência: " + disciplinas[indice][3] + "%");
            System.out.println("Status: " + disciplinas[indice][4]);
            return;
        }

        consultarDisciplinaRecursivo(disciplinas, nome, indice + 1, totalDisciplinas);
    }

    private static void exibirDisciplinas(String[][] disciplinas, int totalDisciplinas) {
        if (totalDisciplinas == 0) {
            System.out.println("Nenhuma disciplina cadastrada.");
        } else {
            System.out.println("\n--- Lista de Disciplinas ---");
            for (int i = 0; i < totalDisciplinas; i++) {
                System.out.println("Disciplina: " + disciplinas[i][0]);
                System.out.println("Nota 1: " + disciplinas[i][1]);
                System.out.println("Nota 2: " + disciplinas[i][2]);
                System.out.println("Frequência: " + disciplinas[i][3] + "%");
                System.out.println("Status: " + disciplinas[i][4]);
                System.out.println("-----------------------------");
            }
        }
    }

    private static String formatarNome(String nome) {
        String[] palavras = nome.split(" ");
        StringBuilder nomeFormatado = new StringBuilder();

        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                nomeFormatado.append(palavra.substring(0, 1).toUpperCase())
                        .append(palavra.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        return nomeFormatado.toString().trim();
    }

    private static String determinarStatus(double nota1, double nota2, double frequencia) {
        double media = (nota1 + nota2) / 2;

        if (frequencia < 75) {
            return "Reprovado por faltas";
        } else if (media >= 7) {
            return "Aprovado por média";
        } else if (media < 4) {
            return "Reprovado por média";
        } else {
            double notaNecessaria = 10 - media;
            return String.format("Necessita de nota %.1f na prova final", notaNecessaria);
        }
    }

    private static int carregarHistorico(String[][] disciplinas) {
        int totalDisciplinas = 0;

        try (Scanner fileScanner = new Scanner(new File(ARQUIVO_HISTORICO))) {
            while (fileScanner.hasNextLine() && totalDisciplinas < MAX_DISCIPLINAS) {
                String[] dados = fileScanner.nextLine().split("//" + DELIMITADOR);
                if (dados.length == COLUNAS) {
                    disciplinas[totalDisciplinas] = dados;
                    totalDisciplinas++;
                }
            }
            System.out.println("Histórico carregado com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de histórico não encontrado. Iniciando novo histórico.");
        }

        return totalDisciplinas;
    }

    private static void salvarHistorico(String[][] disciplinas, int totalDisciplinas) {
        try (PrintWriter writer = new PrintWriter(ARQUIVO_HISTORICO)) {
            for (int i = 0; i < totalDisciplinas; i++) {
                writer.println(String.join(DELIMITADOR, disciplinas[i]));
            }
            System.out.println("Histórico salvo com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao salvar o histórico.");
        }
    }
}