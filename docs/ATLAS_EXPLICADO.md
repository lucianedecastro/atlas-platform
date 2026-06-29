# O QUE É O ATLAS

## Em uma frase

O Atlas é um mapa — não uma metáfora de mapa, um mapa de verdade — que mostra onde projetos culturais e esportivos se cruzam com as organizações que podem apoiá-los, e onde existem oportunidades de captação ainda não exploradas.

---

## O problema que ele resolve

Quem trabalha com captação de recursos para cultura e esporte no Brasil enfrenta sempre o mesmo obstáculo: o conhecimento sobre relacionamentos institucionais — quem já patrocinou o quê, qual edital está aberto, qual organização tem afinidade com qual causa — vive disperso em planilhas, e-mails, memória de quem participou da negociação. Quando uma pessoa sai do projeto, parte desse conhecimento desaparece com ela. Oportunidades deixam de ser vistas porque ninguém consegue olhar o ecossistema inteiro de uma vez.

O Atlas nasceu para resolver exatamente isso — não inventando mais um lugar para cadastrar contatos, mas construindo um sistema que revela conexões que já existem, mas que ninguém consegue ver.

---

## A metáfora que guia todas as decisões

Um atlas geográfico não é uma lista de lugares. É uma forma de organizar camadas de um território — relevo, fronteiras, rotas, recursos — para que quem olha entenda o espaço como um todo, não ponto por ponto.

O Atlas faz isso com território institucional, não geográfico. Organizações, projetos, documentos, oportunidades — são camadas diferentes do mesmo ecossistema. Relacionamentos são as rotas entre elas. E o usuário não navega por uma lista de registros: ele explora um território.

Essa decisão apareceu de forma literal na primeira tela construída: a página inicial do Atlas não é um painel com números e tabelas. É um mapa. Projetos e organizações aparecem como pontos de território; relacionamentos aparecem como rotas tracejadas entre eles, com espessura proporcional à prioridade da negociação. Cadastrar uma organização nova é, visualmente, adicionar um território ao mapa — não preencher um formulário escondido em um menu.

---

## Como o Atlas é estruturado hoje

Por trás do mapa, existe um modelo de domínio que reflete decisões deliberadas sobre o que importa:

**Organização e Projeto** são os dois territórios principais — quem pode apoiar, e o que busca apoio.

**Relacionamento** é o elemento central de todo o sistema, não uma tabela auxiliar. Ele representa o vínculo entre um projeto e uma organização específicos, com seu próprio histórico, prioridade e estágio — o "pipeline" de uma negociação real (prospecção, contato inicial, proposta, negociação, aprovação).

**Documento** carrega uma regra sutil e importante: ele pode nascer dentro de uma negociação já aberta, ou pode existir antes disso — um material institucional do projeto, ou uma proposta personalizada para um patrocinador específico, mesmo sem nenhuma negociação formal ainda em andamento. Essas duas origens nunca se confundem: o sistema sabe sempre de onde cada documento veio.

**Oportunidade** é o pedido concreto dentro de uma negociação — quanto está sendo solicitado, qual a probabilidade, qual o prazo. Uma negociação pode gerar mais de uma oportunidade ao longo do tempo.

**Interesse** é o que conecta projetos e organizações por afinidade, não só por contato direto — temas como "educação esportiva" ou "literatura" que ambos compartilham, com peso e confiança próprios. Esse é o dado que alimenta o cálculo automático de afinidade entre quem busca e quem pode apoiar.

---

## A peça que torna o Atlas diferente de um CRM

A maioria das ferramentas de captação espera que alguém digite manualmente cada oportunidade que existe no mundo. O Atlas foi desenhado com uma porta de entrada diferente: a **Oportunidade Candidata**.

Uma candidata é uma sugestão de oportunidade — um edital encontrado, um possível patrocinador identificado — que entra no sistema com sua origem registrada (de onde veio a informação) e um grau de confiança explícito (quão certo é esse dado). Ela nunca se mistura automaticamente com dados confirmados: precisa ser revisada e aprovada por uma pessoa antes de se tornar uma oportunidade real, vinculada a um projeto e a uma organização que já existem no Atlas.

Hoje, essa porta de entrada já está construída e testada — só falta o motor que a alimenta sozinho, buscando editais e patrocinadores em fontes públicas (como os sistemas oficiais de incentivo cultural e esportivo do governo). Quando isso existir, o Atlas para de ser um lugar onde você registra o que já sabe, e passa a ser um lugar que te avisa do que você ainda não tinha visto.

---

## O que já funciona, de ponta a ponta

Todo o backend do Atlas está construído, testado e rodando em produção — não como protótipo, como sistema real, hospedado na nuvem, acessível por uma URL própria, sem depender de nenhum computador específico ligado.

O frontend tem hoje cinco telas vivas: o mapa inicial do ecossistema, o cadastro de projeto, o cadastro de organização, o registro de oportunidade candidata, e o perfil completo de uma organização — mostrando seu pipeline de relacionamento, seus documentos e suas oportunidades, tudo puxado diretamente do banco de dados real.

A inteligência do sistema já calcula, sem intervenção manual, duas coisas: quais regiões do país têm projetos buscando patrocínio mas poucos patrocinadores cadastrados (uma lacuna geográfica real), e — assim que houver dado suficiente de interesses cadastrados — quais combinações específicas de projeto e organização têm a maior afinidade entre si.

---

## O que ainda falta

Contato e Interação — quem fala com quem, e quando — ainda existem só como estrutura no banco, sem tela nem lógica de aplicação. O motor de busca automática de oportunidades, que transformaria a fila de candidatas de manual para autônoma, também ainda não foi construído. E a navegação entre projeto e seu próprio perfil — hoje só organizações têm página própria — é o próximo buraco visível no mapa.

Nenhuma dessas ausências é acidental. São decisões de sequência, não esquecimentos: construir a fundação certa primeiro, e expandir sobre ela, em vez de adicionar tudo de uma vez sem testar se cada peça realmente sustenta a próxima.

---

## Por que isso importa

O documento fundacional do Atlas afirma que a empresa por trás dele atua na intersecção entre esporte, cultura e tecnologia — três territórios que raramente dialogam de verdade entre si. O Atlas é a prova concreta dessa intersecção: tecnologia não como vitrine, mas como instrumento que revela conexões que esporte e cultura já têm, mas que ninguém, sem ele, conseguiria ver de uma vez.

O conhecimento sobre relacionamentos institucionais não deveria desaparecer quando uma pessoa sai de uma organização. O Atlas existe para que ele não desapareça.
