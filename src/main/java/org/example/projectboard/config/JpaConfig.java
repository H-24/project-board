package org.example.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing  // JPA 엔티티의 감사(Auditing) 기능을 활성화, 엔티티에 대해 생성, 수정 시간 등을 자동으로 기록할 수 있도록 함
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of("win");     // TODO: 스프링 시큐리티로 인증 기능을 부여할 때 수정하자
    }
}
