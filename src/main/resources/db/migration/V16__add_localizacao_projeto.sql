ALTER TABLE projeto
    ADD COLUMN cidade VARCHAR(100),
    ADD COLUMN estado VARCHAR(2),
    ADD COLUMN latitude NUMERIC(9,6),
    ADD COLUMN longitude NUMERIC(9,6);