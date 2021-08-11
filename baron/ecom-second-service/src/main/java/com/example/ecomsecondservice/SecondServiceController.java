package com.example.ecomsecondservice;

import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController // 화면을 매핑하지 않는 컨트롤러
@RequestMapping("/ecom-second-service") // 사용자로부터 요청되는 URI 값
@Slf4j
public class SecondServiceController {

    // yml파일 환경설정 정보 가져오기
    Environment env;

    @Autowired
    public SecondServiceController(Environment env) {
        this.env = env;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the Second Service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header){
        // 롬복의 기능 : @Slf4j로 log변수를 사용할 수 있다.
        log.info(header);
        return "Hello world in Second Service : " + header;
    }

    /**
     * 어느 서비스가 불렸는지 확인하는 메소드
     *
     * Spring MVC 기준, HttpServletRequest의 인스턴스는 매개변수로 지정만 해줘도
     * 따로 받아오지 않더라도 객체를 주입받아 바로 사용할 수 있다.
     *
     * @param request
     * @return
     */
    @GetMapping("/check")
    public String check(HttpServletRequest request){
        log.info("Server port = {}", request.getServerPort());

        return String.format("Second Service Check and Port : %s",
                env.getProperty("local.server.port"));
    }
}
