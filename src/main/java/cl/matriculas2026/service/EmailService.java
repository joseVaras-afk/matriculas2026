package cl.matriculas2026.service;

import cl.matriculas2026.entity.Matricula;

public interface EmailService {
    void enviarComprobanteMatricula(Matricula matricula);
}
