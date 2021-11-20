ALTER TABLE transacoes
        ADD imposto decimal(18,2);

UPDATE transacoes 
   SET imposto = ((preco * quantidade) * 15 / 100)
 WHERE tipo = 'VENDA'
   AND ((preco * quantidade) > 20000);
   
UPDATE transacoes 
   SET imposto = 0
 WHERE imposto IS NULL;
 
INSERT INTO usuarios (nome, login, senha) VALUES ('Administrador do Sistema', 'adm', '$2a$10$uzvz5rEIkOqufbnNe7Ga9.yK0NZPQj3grSFbDgzzz21Kz9.n1SOlq');
        
INSERT INTO perfis_usuarios (usuario_id, perfil_id) VALUES (1, 1);
