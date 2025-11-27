// src/main/java/cl/matriculas2026/util/RutUtils.java
package cl.matriculas2026.util;

public final class RutUtils {
    private RutUtils() {}

    /** Normaliza: deja solo dígitos y K en mayúscula, sin puntos ni guion. */
    public static String normalize(String rut) {
        if (rut == null) return null;
        String cleaned = rut.replaceAll("[^0-9kK]", "");
        return cleaned.toUpperCase(); // 12345678K
    }
}
