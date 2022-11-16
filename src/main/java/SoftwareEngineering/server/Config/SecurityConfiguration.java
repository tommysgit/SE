package SoftwareEngineering.server.Config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 기존 WebSecurityConfigurerAdapter는 삭제되되어 이전에 오버라이딩 하여 사용하던 방식을 빈을 등록하여 사용한다.
// WebMvcConfigurer가 Java8이 기본이 되면서 default method를 포함하게되 Adapter가 필요하지 않기때문
// EnableWebSecurity는 Security를 활성시키는 역 (WebSecurity, MvcSecurity, OAuthSecurity, HttpSecurity 등의 설정파일을 임포트
// EnableWebMvc를 통해 mvc 설정 추가로 생성가능
@EnableWebSecurity
@Configuration
public class SecurityConfiguration  {


    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));

    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .httpBasic().disable()
                        .cors().and()
                        .csrf().disable()
                        .formLogin().disable();

        httpSecurity
                .authorizeRequests()
                .antMatchers("/reservation").hasAnyRole("User", "Admin")
                .anyRequest().authenticated();

//        httpSecurity.sessionManagement()
//                .maximumSessions(10)
//                .maxSessionsPreventsLogin(true); // 세션수를 넘어갔을 때 추가적인 인증은 실패처리한다.


        return httpSecurity.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authz) -> authz
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(withDefaults());
//        return http.build();
//    }
}
