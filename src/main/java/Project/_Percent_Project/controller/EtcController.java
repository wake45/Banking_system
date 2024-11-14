package Project._Percent_Project.controller;

import Project._Percent_Project.service.DataInsertService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EtcController {
    private final DataInsertService dataInsertService;
    private boolean onLoad = false;

    public EtcController(DataInsertService dataInsertService) {
        this.dataInsertService = dataInsertService;
    }

    @GetMapping("/")
    public String LoginView(){

//        if(!onLoad){
//            dataInsertService.DataInsert();
//            onLoad = true;
//        }

        return "LoginView";
    }

    @PostMapping("/main")
    public String MainView(@RequestParam("id") String id, Model model){
        String name = switch (id) {
            case "001" -> "에잇퍼센트";
            case "002" -> "카카오뱅크";
            case "003" -> "토스뱅크";
            default -> "";
        };
        System.out.println("로그인 ID : " + id);

        model.addAttribute("name",name);
        model.addAttribute("id",id);
        return "MainView";
    }

}
