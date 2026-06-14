    package school.sptech.exception;

    public class ArquivoS3Exception extends RuntimeException {

        public ArquivoS3Exception(String message) {
            super(message);
        }

        public ArquivoS3Exception(String message, Throwable cause) {
            super(message, cause);
        }
    }