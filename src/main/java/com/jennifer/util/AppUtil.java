package com.jennifer.util;

import com.jennifer.dto.CustomerDetails;
import com.jennifer.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Helps facilitating some parts of the application
 */

@Component
public class AppUtil {

    //private static final Logger logger = LoggerFactory.getLogger(AppUtil.class);

    private static MessageSource messageSource;

    @Autowired
    public AppUtil(MessageSource messageSource) {
        AppUtil.messageSource = messageSource;
    }

    public static void flashWithName(RedirectAttributes redirectAttributes, String varName, String kind, String messageKey) {
        redirectAttributes.addFlashAttribute("flash" + varName, kind);
        redirectAttributes.addFlashAttribute("flashMessage", getMessage(messageKey));
    }

    public static void flashModelAttribute(Model model, String varName, String kind, String messageKey) {
        model.addAttribute("flash" + varName, kind);
        model.addAttribute("flashMessage", getMessage(messageKey));
    }

    public static String getMessage(String messageKey, Object... args) {
        return messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
    }

    public static UserInfo getCustomerFromSession() {
        CustomerDetails customerDetails = getAuth();
        return customerDetails == null ? null : customerDetails.getUser();
    }

    private static CustomerDetails getAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof CustomerDetails) {
                return (CustomerDetails) principal;
            }
        }

        return null;
    }

    public static void validate(boolean valid, String falseKeyCode, Object... args) {
        if (!valid) {
            throw new RuntimeException(getMessage(falseKeyCode, args));
        }
    }
}
