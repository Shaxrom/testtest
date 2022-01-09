package uz.episodeone.merchants.configuration;

import feign.Logger;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {
    @Bean
    public OkHttpClient client()
    {
        return new OkHttpClient();
    }

    @Bean
    public Logger.Level feignLoggerLevel()
    {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder()
    {
        return new ErrorDecoder.Default();
    }
}