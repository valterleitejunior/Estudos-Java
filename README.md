Gerenciamento de Disciplinas com Arquivo
Este projeto é um sistema simples de gerenciamento de disciplinas desenvolvido em Java. Ele permite ao usuário adicionar, consultar e exibir disciplinas, além de salvar e carregar o histórico de disciplinas em um arquivo de texto. O sistema é projetado para ser utilizado via linha de comando, oferecendo um menu interativo para facilitar a navegação e operação.

Funcionalidades
Adicionar Disciplina: Permite ao usuário adicionar uma nova disciplina, informando o nome, notas da unidade 1 e 2, e o percentual de frequência. O sistema calcula automaticamente o status da disciplina (aprovado, reprovado, ou necessidade de prova final) com base nas notas e frequência.

Consultar Disciplina: Oferece a possibilidade de consultar uma disciplina específica pelo nome. A busca é realizada de forma recursiva.

Exibir Todas as Disciplinas: Lista todas as disciplinas cadastradas, exibindo seus respectivos nomes, notas, frequência e status.

Salvar e Carregar Histórico: O sistema salva automaticamente o histórico de disciplinas em um arquivo de texto (historico_notas.txt) e carrega esses dados ao ser iniciado, garantindo a persistência dos dados entre execuções.

Estrutura do Código
GerenciamentoDisciplinaComArquivo: Classe principal que contém o método main e gerencia o fluxo do programa.

Métodos Principais:

adicionarDisciplina: Adiciona uma nova disciplina ao array de disciplinas.

consultarDisciplinaRecursivo: Realiza uma busca recursiva por uma disciplina específica.

exibirDisciplinas: Exibe todas as disciplinas cadastradas.

determinarStatus: Determina o status da disciplina com base nas notas e frequência.

carregarHistorico: Carrega o histórico de disciplinas de um arquivo de texto.

salvarHistorico: Salva o histórico de disciplinas em um arquivo de texto.

Como Usar
Compilação: Compile o código Java utilizando o comando javac GerenciamentoDisciplinaComArquivo.java.

Execução: Execute o programa com o comando java GerenciamentoDisciplinaComArquivo.

Menu: Siga as instruções no menu para adicionar, consultar ou exibir disciplinas. Para sair, selecione a opção "4".

Requisitos
Java Development Kit (JDK) instalado.

Ambiente de linha de comando para execução.Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests para melhorias, correções de bugs ou novas funcionalidades.

Licença
Este projeto está licenciado sob a MIT License.

# Estudos-Java
