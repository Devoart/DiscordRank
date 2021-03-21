import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.ByteArrayOutputStream;

public class RankCommand extends Command {

    public RankCommand()
    {
        this.name = "rank";
        this.help = "generate rank";
        this.guildOnly = false;
        this.aliases = new String[]{"rnk"};
    }



    @Override
    protected void execute(CommandEvent event) {

        ByteArrayOutputStream byteArrayOutputStream = RankCard.generateCard(event.getAuthor().getAvatarUrl(),event.getAuthor().getAsTag(),"Admin","01","20","200",300,event.getMember().getOnlineStatus().toString());

        event.getChannel().sendFile(byteArrayOutputStream.toByteArray(), "image.png").queue();


    }
}
