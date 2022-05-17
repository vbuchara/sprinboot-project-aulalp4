package com.ifms.edu.siaula3.util;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public final class Toasts {
    
    public static void showSuccessToast(Model view, String message){
        view.addAttribute("toastMsg", message);
        view.addAttribute("toastClass", "success-msg");
        view.addAttribute("toastIcon", "check_circle");
    }

    public static void showErrorToast(Model view, String message){
        view.addAttribute("toastMsg", message);
        view.addAttribute("toastClass", "error-msg");
        view.addAttribute("toastIcon", "error");
    }

    public static void sendToastFlashAttr(
        RedirectAttributes rdAttributes, 
        ToastTypes type,
        String message
    ){
        rdAttributes.addFlashAttribute(type.toString(), message);
    }
    
    public enum ToastTypes {
        SUCCESS("successMsg"), 
        ERROR("errorMsg");

        private final String text;

        ToastTypes(final String text){
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
