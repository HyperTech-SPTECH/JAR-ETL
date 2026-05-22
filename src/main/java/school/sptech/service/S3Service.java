package school.sptech.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import school.sptech.config.S3Config;
import school.sptech.exception.ArquivoS3Exception;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import java.io.InputStream;

public class S3Service {

    private static final Logger log = LogManager.getLogger(S3Service.class);
    private final S3Client s3 = S3Config.getS3Client();
    private final String bucketName = "hyperbucket2026";

    public InputStream obterArquivo(String chaveS3) {

        try {
            GetObjectRequest request = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(chaveS3)
                    .build();

            log.info("Buscando arquivo {} no bucket {}", chaveS3, bucketName);

            return s3.getObject(request);

        } catch (Exception e) {

            log.error("Erro ao obter arquivo do S3", e);
            throw new ArquivoS3Exception(
                    "Erro ao obter arquivo do bucket S3",
                    e
            );
        }
    }
}