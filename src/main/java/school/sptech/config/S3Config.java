package school.sptech.config;

import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;

public class S3Config {
    public static S3Client getS3Client() {
        String accessKey = "ASIA5PZP7QFYFV4JQKNF";
        String secretKey = "j6w4t+jlTt8DQMnj7tw8nw1FVuzPpF6a49JV2uKh";
        String sessionToken = "IQoJb3JpZ2luX2VjEFUaCXVzLXdlc3QtMiJGMEQCIF/WnHGZY8BdVvOz2/amN/HqJwdRMxyYATTX64jh4qVoAiAbbENv090FamN412eys6fQNekHmPKAKaLtwHb7DsdqByqzAggeEAEaDDkyNzI3NjY5NTkyMCIMGJXHpRcXzQz8Ow5kKpACkeIOj/rya+yN8a5+gJDELYLuR7xpIVK7uL47h5mcC9OaIDCHe8OFB4rEKjdTbjNbFw4v7jTTfUx9f/t2LOTn0p+W2Uchm6iQX+zhKxTj7Prlvrzzved1U3d285/FzuIQs6n/JteBvUOQU0DrtRXJLERLT7+plxDfX9lXRGgEv/K68o23XMSNKP0qsBv74cLf64Ex5OJG/s2aPtqLl+Ad5Srp2Dc5cfPmt1dIVNZx3sDn/ZxQlGb8Nhdt+aHvF4IHuWs2UbhDajk+HYdqqP46a6mF5rWlcnhc48uTO7U+09J8ch3FILlyXkbiMlUgJMkjGc0q+Hpxx2neGH5NwXp9Ae1FW78WG/dIVJ5jMkI5GiIw9pjgzgY6ngHJua46scYXkAK/1XwOToBb28tHCreELqhF3X3LJX7ut/MgD5NSX+3QEFIfkGlYfvNHbDIC3gBbzM1zp7raLExJYkJnBwKdykZIrWitYnP6mtgUz7i6d02lL8QDi1OS6tR8MAflssxw5ywsdUMciV+lDI+rGReEOp7xUZrPYfo2q30GlHtHjkzrN2SQoCp/rhlg7pTKajFKqCex6yDsIw==";
        return S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsSessionCredentials.create(accessKey, secretKey, sessionToken.trim())
                ))
                .build();
    }
}