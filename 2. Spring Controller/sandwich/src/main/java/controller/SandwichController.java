package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SandwichController {
    @RequestMapping("/")
    public String loadIndex() {
        return "index";
    }

    @GetMapping(path = "/save")
    public String save(
            @RequestParam(required = false, name = "condiment") String[] condiment,
            ModelMap modelMap
    ) {
        if (condiment == null) {
            modelMap.addAttribute("message", "Bạn chưa chọn gia vị");
            return "index";
        }else{
            modelMap.addAttribute("condiment", condiment);
            return "save";
        }

    }
}
