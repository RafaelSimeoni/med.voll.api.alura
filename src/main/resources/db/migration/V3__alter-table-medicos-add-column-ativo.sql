ALTER TABLE medicos ADD ativo TINYINT(20);
UPDATE medicos SET ativo = 1;