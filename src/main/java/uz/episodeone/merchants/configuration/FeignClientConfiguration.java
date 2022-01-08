package uz.episodeone.merchants.configuration;

import feign.Feign;
import feign.Logger;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FeignClientConfiguration {
    @Bean
    public OkHttpClient client()
    {
        return new OkHttpClient();
    }

//    @Bean
//    public RequestInterceptor requestInterceptor(AppProperties appProperties)
//    {
//        return requestTemplate -> {
//            requestTemplate.header( "Accept", ContentType.APPLICATION_JSON.getMimeType() );
//            requestTemplate.header( "Content-Type", ContentType.APPLICATION_JSON.getMimeType() );
//        };
//    }

    @Bean
    public Logger.Level feignLoggerLevel()
    {
        return Logger.Level.FULL;
    }

    @Bean
    @Scope( "prototype" )
    public Feign.Builder feignBuilder()
    {
        return Feign.builder();
    }

    @Bean
    public ErrorDecoder errorDecoder()
    {
        return new ErrorDecoder.Default();
    }
}