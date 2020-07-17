 
package com.thinkmicroservices.ri.spring.notification.i18n;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * 
 * @author cwoodward
 */
@Component
public class I18NResourceBundle {

   private static ResourceBundleMessageSource messageSource;

   /**
    * 
    * @param messageSource 
    */
   @Autowired
   I18NResourceBundle(ResourceBundleMessageSource messageSource) {
      I18NResourceBundle.messageSource = messageSource;
   }

   /**
    * 
    * @param msgKey
    * @return 
    */
   public static String translateForLocale(String msgKey) {
      Locale locale = LocaleContextHolder.getLocale();
      return messageSource.getMessage(msgKey, null, locale);
   }
}
