package fr.univrouen.cv24.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(PATH)
    public String handleError(Model model) {
        // Ajoutez ici votre logique de gestion d'erreur
        // Par exemple, vous pouvez ajouter des informations sur l'erreur à l'objet Model
        model.addAttribute("errorMessage", "Une erreur s'est produite. Veuillez contacter l'administrateur.");

        // Renvoie le nom de la vue d'erreur à afficher
        return "errorCustom";
    }

    public String getErrorPath() {
        return PATH;
    }
}
