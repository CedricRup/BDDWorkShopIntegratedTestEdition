package com.santa;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SantaApplication {

    public static void main(String[] args) throws Exception {
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(SantaApplication.class);
        if (args.length > 0) {
            springApplicationBuilder = springApplicationBuilder.web(false);
            if (args[0].equals("--migrate")) {
                springApplicationBuilder.run(args);
            }
        } else {
            springApplicationBuilder.run(args);
        }

    }
}
