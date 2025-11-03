package cl.matriculas2026.service.impl;

import cl.matriculas2026.entity.Matricula;
import cl.matriculas2026.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${app.mail.from:Matriculas 2026 <no-reply@matriculas.local>}")
    private String from;

    @Value("${app.mail.fallback-to:}") // opcional: correo “respaldo” si falta el del apoderado
    private String fallbackTo;

    public EmailServiceImpl(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void enviarComprobanteMatricula(Matricula m) {
        try {
            if (m == null) {
                log.warn("Matricula nula: no se puede enviar comprobante");
                return;
            }

            // ========== DESTINATARIO ==========
            String to = firstNonBlank(
                // Modelo anidado (relaciones)
                getSafe(() -> m.getApoderadoTitular() != null ? m.getApoderadoTitular().getCorreo() : null),
                getSafe(() -> m.getApoderadoSuplente() != null ? m.getApoderadoSuplente().getCorreoApoderadoSuplente() : null),
                // Fallback opcional (admin/coordinador)
                blankToNull(fallbackTo)
            );

            if (isBlank(to)) {
                log.warn("No hay correo del apoderado ni fallback configurado. Comprobante NO enviado. nroMatricula={}", m.getNumeroMatricula());
                return;
            }

            // ========== DATOS ==========
            String numero = m.getNumeroMatricula() != null ? String.valueOf(m.getNumeroMatricula()) : "(sin número)";
            String alumnoNombre = firstNonBlank(
                    // anidado
                    m.getAlumno() != null ? joinNames(
                            m.getAlumno().getNombres(),
                            m.getAlumno().getApellidoPaterno(),
                            m.getAlumno().getApellidoMaterno()
                    ) : null
            );

            String apoderadoNombre = firstNonBlank(
                    // anidado (titular)
                    m.getApoderadoTitular() != null ? joinNames(
                            m.getApoderadoTitular().getNombres(),
                            m.getApoderadoTitular().getApellidoPaterno(),
                            m.getApoderadoTitular().getApellidoMaterno()
                    ) : null
            );

            if (isBlank(alumnoNombre)) alumnoNombre = "(alumno sin nombre)";
            if (isBlank(apoderadoNombre)) apoderadoNombre = "(apoderado sin nombre)";

            // ========== TEMPLATE ==========
            Context ctx = new Context();
            ctx.setVariable("numeroMatricula", numero);
            ctx.setVariable("alumnoNombre", alumnoNombre);
            ctx.setVariable("apoderadoNombre", apoderadoNombre);
            ctx.setVariable("fecha", LocalDate.now().toString());

            String html = templateEngine.process("matricula-ok", ctx);

            // ========== ENVÍO ==========
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name()
            );
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("Comprobante de Matrícula #" + numero);
            helper.setText(html, true);

            log.info("Enviando comprobante a '{}' (nroMatricula={}, alumno='{}', apoderado='{}')",
                    to, numero, alumnoNombre, apoderadoNombre);

            mailSender.send(message);
            log.info("Comprobante enviado OK a {}", to);

        } catch (Exception ex) {
            log.error("Error enviando comprobante de matrícula", ex);
        }
    }

    // ===== Utils =====

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
    private static String blankToNull(String s) {
        return isBlank(s) ? null : s.trim();
    }
    private static String firstNonBlank(String... vals) {
        if (vals == null) return null;
        for (String v : vals) {
            if (!isBlank(v)) return v.trim();
        }
        return null;
    }
    private static String joinNames(String... parts) {
        StringBuilder sb = new StringBuilder();
        if (parts != null) {
            for (String p : parts) {
                if (!isBlank(p)) {
                    if (!sb.isEmpty()) sb.append(' ');
                    sb.append(p.trim());
                }
            }
        }
        return sb.toString();
    }

    private static <T> T getSafe(SupplierEx<T> supplier) {
        try { return supplier.get(); } catch (Exception e) { return null; }
    }
    @FunctionalInterface
    private interface SupplierEx<T> { T get() throws Exception; }
}
