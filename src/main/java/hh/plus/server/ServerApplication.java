package hh.plus.server;

import lombok.extern.slf4j.Slf4j;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
		api.call(new SlackMessage("Application started to run"));
	}

}
