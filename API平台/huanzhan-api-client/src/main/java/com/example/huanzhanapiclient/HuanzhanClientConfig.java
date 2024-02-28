package com.example.huanzhanapiclient;


import com.example.huanzhanapiclient.client.HuanzhanClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
@ComponentScan
@Configuration
@ConfigurationProperties("huanzhan.client")
public class HuanzhanClientConfig {

    private String accessKey ;
    private String secretKey ;
    @Bean
    public HuanzhanClient client(){
        return new HuanzhanClient(accessKey,secretKey);
    }

}
