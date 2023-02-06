package ru.bvkuchin.market.Market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.bvkuchin.market.Market.services.CartService;

@SpringBootApplication
//@EnableAspectJAutoProxy
public class MarketApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MarketApplication.class, args);
	}

}
