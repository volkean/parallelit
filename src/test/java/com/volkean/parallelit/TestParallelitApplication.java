package com.volkean.parallelit;

import org.springframework.boot.SpringApplication;

public class TestParallelitApplication {

	public static void main(String[] args) {
		SpringApplication.from(ParallelitApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
