package Project._Percent_Project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EtcController {
    @GetMapping("/")
    public String LoginView(){
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
        model.addAttribute("name",name);
        model.addAttribute("id",id);
        return "MainView";
    }

    @PostMapping("/transfer")
    public String TransferView(@RequestParam("action") String action, @RequestParam("id") String id, Model model){
        model.addAttribute("id",id);

        if ("transactionsView".equals(action)) {
            return "TransactionsHistoryInquiryView"; // 거래내역조회 페이지로 이동
        } else if ("transferView".equals(action)) {
            return "TransferView"; // 이체 페이지로 이동
        }

        return "ErrorPage"; // 잘못된 요청 처리
    }
}
