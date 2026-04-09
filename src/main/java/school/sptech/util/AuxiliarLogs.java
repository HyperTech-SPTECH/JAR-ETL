package school.sptech.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuxiliarLogs {

    // Formatter estático para não recriar toda vez (ganho de performance)
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static String getHeader(String level) {
        String dataHora = LocalDateTime.now().format(formatter);
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();

        // Índice [3] se você usar este método auxiliar getHeader
        // Índice [2] se o código estiver direto dentro do info/error
        String classeCompleta = stack[3].getClassName();
        String classeSimples = classeCompleta.substring(classeCompleta.lastIndexOf('.') + 1);
        String metodo = stack[3].getMethodName();

        return String.format("[%s] [%-5s] [%s.%s] ", dataHora, level, classeSimples, metodo);
    }

    public static void error(String mensagem) {
        System.err.println(getHeader("ERROR") + mensagem);
    }

    public static void warn(String mensagem) {
        System.out.println(getHeader("WARN") + mensagem);
    }

    public static void info(String mensagem) {
        System.out.println(getHeader("INFO") + mensagem);
    }

    public static void debug(String mensagem) {
        System.out.println(getHeader("DEBUG") + mensagem);
    }
}