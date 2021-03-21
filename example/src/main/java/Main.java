import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.examples.command.PingCommand;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {
        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setPrefix("!");
        builder.addCommand(new PingCommand());
        builder.addCommand(new RankCommand());

        CommandClient client = builder.setOwnerId("407195947093983232").build();

        JDABuilder.createDefault("token", GatewayIntent.GUILD_MESSAGES,GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                // ...
                .addEventListeners(client) // Add the new CommandClient as a listener
                // ...
                .build();

    }
}
