package SoftwareEngineering.server.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// 기존 WebSecurityConfigurerAdapter는 삭제되되어 이전에 오버라이딩 하여 사용하던 방식을 빈을 등록하여 사용한다.
// WebMvcConfigurer가 Java8이 기본이 되면서 default method를 포함하게되 Adapter가 필요하지 않기때문
// EnableWebSecurity는 Security를 활성시키는 역 (WebSecurity, MvcSecurity, OAuthSecurity, HttpSecurity 등의 설정파일을 임포트
// EnableWebMvc를 통해 mvc 설정 추가로 생성가능
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();
    }

}
