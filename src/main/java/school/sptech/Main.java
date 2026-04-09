package school.sptech;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import school.sptech.service.OcorrenciaService;
import school.sptech.service.S3Service;


import java.io.InputStream;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        OcorrenciaService service = new OcorrenciaService();
        /*
        String caminhoLocal = "C:/Downloads/base-de-dados.xlsx";
        try (InputStream is = new FileInputStream(new File(caminhoLocal))) {
            service.executar(is, "base-de-dados.xlsx");
        } catch (Exception e) { log.error(e); }
        */
        log.info("Iniciando ETL via S3...");
        S3Service s3Service = new S3Service();
        String nomeNoS3 = "base-de-dados.xlsx";

        try (InputStream is = s3Service.obterArquivo(nomeNoS3)) {
            service.executar(is, nomeNoS3);
            log.info("ETL do S3 finalizado!");
        } catch (Exception e) {
            log.fatal("Erro ao buscar o arquivo especificado no S3!", e);
        }
    }
}