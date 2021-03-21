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

        JDABuilder.createDefault("ODAxMzQwNzExOTg3NDQ1ODAw.YAfQsQ.XJLTXb1K5o7lBcS1M9UzjTT076o", GatewayIntent.GUILD_MESSAGES,GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
//        JDABuilder.createDefault("ODIyMDUzMzkzNTkzMDA4MTY4.YFMq4g.M1-AvuXeGY3v4y1gEFeAra44Df0")
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                // ...
                .addEventListeners(client) // Add the new CommandClient as a listener
                // ...
                .build();

    }
}
