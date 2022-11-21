package SoftwareEngineering.server.Config;


import SoftwareEngineering.server.Config.Jwt.JwtAccessDeniedHandler;
import SoftwareEngineering.server.Config.Jwt.JwtAuthenticationEntryPoint;
import SoftwareEngineering.server.Config.Jwt.JwtAuthenticationFilter;
import SoftwareEngineering.server.Config.Jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// 기존 WebSecurityConfigurerAdapter는 삭제되되어 이전에 오버라이딩 하여 사용하던 방식을 빈을 등록하여 사용한다.
// WebMvcConfigurer가 Java8이 기본이 되면서 default method를 포함하게되 Adapter가 필요하지 않기때문
// EnableWebSecurity는 Security를 활성시키는 역 (WebSecurity, MvcSecurity, OAuthSecurity, HttpSecurity 등의 설정파일을 임포트
// EnableWebMvc를 통해 mvc 설정 추가로 생성가능
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration  {


    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));

    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.httpBasic().disable()
                        .cors().and()
                        .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and().formLogin().disable();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.httpBasic()
                .disable().formLogin().disable();
        http.authorizeRequests()
                        .antMatchers("/user/login").permitAll()
                        .antMatchers("/user/signUp").permitAll();

        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
