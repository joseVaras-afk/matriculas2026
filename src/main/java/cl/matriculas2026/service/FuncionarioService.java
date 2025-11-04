package cl.matriculas2026.service;

import cl.matriculas2026.entity.Funcionario;

import java.util.Optional;

public interface FuncionarioService {
    Optional<Funcionario> findByEmail(String email);
    Funcionario save(Funcionario f);
}
