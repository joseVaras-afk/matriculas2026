package cl.matriculas2026.service.impl;

import cl.matriculas2026.entity.Funcionario;
import cl.matriculas2026.repository.FuncionarioRepository;
import cl.matriculas2026.service.FuncionarioService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository repo;

    public FuncionarioServiceImpl(FuncionarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Funcionario> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public Funcionario save(Funcionario f) {
        return repo.save(f);
    }
}
