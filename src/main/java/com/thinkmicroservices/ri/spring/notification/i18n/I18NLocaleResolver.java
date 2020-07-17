package com.thinkmicroservices.ri.spring.notification.i18n;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**
 *
 * @author cwoodward
 */
@Configuration
public class I18NLocaleResolver
        extends AcceptHeaderLocaleResolver
        implements WebMvcConfigurer {

    private static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";
    private static final String BASE_I18N_RESOURCE_PATH = "i18n/messages";
    private static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";

    // supported languages
    private static final String ENGLISH = "en";
    private static final String SPANISH = "es";
    private static final String ITALIAN = "it";
    private static final String FRENCH = "fr";
    private static final String GERMAN = "de";

    private List<Locale> LOCALES = Arrays.asList(new Locale(ENGLISH),
            new Locale(GERMAN),
            new Locale(FRENCH),
            new Locale(ITALIAN),
            new Locale(SPANISH));

    /**
     *
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String headerLang = request.getHeader(HEADER_ACCEPT_LANGUAGE);
        return headerLang == null || headerLang.isEmpty()
                ? Locale.getDefault()
                : Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
    }

    /**
     *
     * @return
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.setBasename(BASE_I18N_RESOURCE_PATH);
        rs.setDefaultEncoding(DEFAULT_CHARACTER_ENCODING);
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }

}
