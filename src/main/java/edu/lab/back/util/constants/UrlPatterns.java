package edu.lab.back.util.constants;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UrlPatterns {

    /**
     * Базовый урл круда туда.
     */
    public static final String CRUD_BASE_URL = "/crud";

    @Value( "${server.servlet.context-path}" )
    private String baseUrl;

}
