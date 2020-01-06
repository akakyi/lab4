package edu.lab.back.config;

import edu.lab.back.util.constants.UrlPatterns;
import edu.lab.back.util.converters.CustomJaxb2RootElementHttpMessageConverter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;

@Configuration
public class MessageConverterConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Autowired
    public Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter(
            @NonNull final UrlPatterns urlPatterns
    )
    {
        final Jaxb2RootElementHttpMessageConverter res = new CustomJaxb2RootElementHttpMessageConverter(urlPatterns);
        return res;
    }

}
