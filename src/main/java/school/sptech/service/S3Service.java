package school.sptech.service;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import school.sptech.config.S3Config;
import java.io.InputStream;

public class S3Service {
    private final S3Client s3 = S3Config.getS3Client();
    private final String bucketName = "projeto-etl-sptech";

    public InputStream obterArquivo(String chaveS3) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(chaveS3)
                .build();
        return s3.getObject(request);
    }
}