package com.dgyj.yjpicture.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EntityManager;
import java.util.Optional;

@Configuration
@EnableJpaAuditing // JPA Auditing 활성화
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware(){
        return () -> {

            try{
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                UserDetails userDetails = (UserDetails)principal;
                String currentUser = userDetails.getUsername();

                if(currentUser != null){
                    return Optional.of(currentUser);
                }else{
                    return Optional.of("Anonymous");
                }
            }catch(Exception e){
                return Optional.of("Anonymous");
            }

        };
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }
}

