CREATE VIEW vw_score_afinidade AS

SELECT

    p.id AS projeto_id,

    p.nome AS projeto,

    o.id AS organizacao_id,

    o.nome AS organizacao,

    COUNT(pi.interesse_id) AS interesses_em_comum,

    SUM(
        LEAST(pi.peso, oi.peso) * pi.criticidade
    ) AS score_bruto

FROM projeto p

INNER JOIN projeto_interesse pi
    ON pi.projeto_id = p.id

INNER JOIN organizacao_interesse oi
    ON oi.interesse_id = pi.interesse_id

INNER JOIN organizacao o
    ON o.id = oi.organizacao_id

GROUP BY
    p.id,
    p.nome,
    o.id,
    o.nome;