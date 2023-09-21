package com.adroyoyo.ServidorApi;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.adroyoyo.ServidorApi.Entity.ERole;
import com.adroyoyo.ServidorApi.Entity.RoleEntity;
import com.adroyoyo.ServidorApi.Entity.UserEntity;
import com.adroyoyo.ServidorApi.Repositorio.UserRepository;

@SpringBootApplication
@EnableScheduling
public class ServidorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServidorApiApplication.class, args);		
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;
 
	/* @Bean
	CommandLineRunner init(){
		return args -> {

			UserEntity userEntity = UserEntity.builder()							// Creamos usuarios
										.email("adroyoyo@gmail.com")
										.username("adroyoyo")
										.password(passwordEncoder.encode("1234"))
										.roles(Set.of(RoleEntity.builder().name(ERole.valueOf(ERole.ADMIN.name())).build())) 
										.build();

			UserEntity userEntity3 = UserEntity.builder()							// Creamos usuarios
										.email("adela@gmail.com")
										.username("adela")
										.password(passwordEncoder.encode("1234"))
										.roles(Set.of(RoleEntity.builder().name(ERole.valueOf(ERole.USER.name())).build())) 
										.build();

			UserEntity userEntity2 = UserEntity.builder()							// Creamos usuarios
										.email("maria@gmail.com")
										.username("maria")
										.password(passwordEncoder.encode("1234"))
										.roles(Set.of(RoleEntity.builder().name(ERole.valueOf(ERole.INVITED.name())).build())) 
										.build();

			userRepository.save(userEntity);
			userRepository.save(userEntity2);
			userRepository.save(userEntity3);

										
		};
	}  */

}
