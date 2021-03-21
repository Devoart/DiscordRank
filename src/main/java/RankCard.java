import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class RankCard {
    public static ByteArrayOutputStream generateCard(String avatarUrl, String name, String serverPostion, String serverRankNumber, String level, String XP, int rankP,String status){
        BufferedImage originalImg = null;
        try {
            originalImg  = ImageIO.read(new URL("https://media.discordapp.net/attachments/817335682283339781/822890104182931486/DiscordRank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage avatarImg = null;
        try {
            avatarImg = roundedImage(ImageIO.read(new URL(avatarUrl)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = originalImg.getWidth();
        int height = originalImg.getHeight();

        BufferedImage bim = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bim.createGraphics();
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(qualityHints);
        g2.drawImage(originalImg, 0, 0, null);
        g2.drawImage(avatarImg, 40, 59, 164, 164, null, null);
        g2.setColor(new Color(26, 26, 26));
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(40,59,164,164);
//        g2.drawImage(onlineImg, 190, 190, 40, 40, null, null);
        g2.setStroke(new BasicStroke(3));
        System.out.println("status : "+status);

        System.out.println(status.toLowerCase());
        if(status.toLowerCase().equals("online")){
            g2.setColor(new Color(68,179,128));
        }else if(status.toLowerCase().equals("invisible") || status.toLowerCase().equals("offline") ){
            g2.setColor(new Color(115, 127, 141));
        }else if(status.equals("DO_NOT_DISTURB")){
            g2.setColor(new Color(240, 72, 72));
        }else if(status.toLowerCase().equals("idle")){
            g2.setColor(new Color(251, 165, 28));
        }
        g2.fillOval(163,172,42,42);
        g2.setColor(new Color(26, 26, 26));
        g2.drawOval(163,172,42,42);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        g2.drawString(name, 220, 85);
        //rank
        g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
        g2.drawString("#"+serverRankNumber, 425, 200);
        //server member
        g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD,25));
        g2.drawString(serverPostion, 425, 228);

        //level
        Font font = new Font(Font.SANS_SERIF, Font.BOLD,15);
//        g2.rotate(-90);

        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(-90), 0, 0);
        Font rotatedFont = font.deriveFont(affineTransform);

        g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD,40));
        g2.setColor(new Color(255, 255, 255));
        g2.drawString("Level "+level, 600, 55);
        //level

//
//        //rank
//        g2.setFont(rotatedFont);
//        g2.drawString("Rank 01", 775, 175);
//        //rank

        //xp
        g2.setFont(rotatedFont);
        g2.setColor(new Color(255,255,255));
        g2.drawString("XP "+XP+"/"+rankP, 775, 250);
        //xp


        ////progrss bar
        g2.setColor(new Color(98, 98, 98));
        g2.fillRoundRect(782,24,35,230,20,20);
        g2.setColor(new Color(255, 255, 255));

        int progressBarY = Integer.parseInt(String.valueOf(rankP));
        int progressBar = 0;
        if(progressBarY > 0){
            progressBar = Integer.parseInt(String.valueOf(rankP));
        }else if(progressBarY < 0){
            progressBar = 0;
        }

//        float sizeT = (float) (2.3*progressBar/5);
        System.out.println("XP "+XP);
        float sizeY = Float.valueOf(XP)/rankP;
        float sizeM =  sizeY * 100;
        System.out.println("progress value  of M : "+sizeM);
        System.out.println("Rank "+rankP);
        System.out.println("progress value  of Y : "+sizeY);
        float sizeT = (float) (2.3*sizeM);
        System.out.println("progress value "+sizeT);
        int size =  (int)sizeT;
        int progressY = 254 - (size);
        g2.fillRoundRect(782,progressY,35,size,20,20);
        ////progrss bar
        g2.dispose();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bim, "png", stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream;
    }


    private static BufferedImage roundedImage(BufferedImage img) {
        int width = img.getWidth();
        int w = img.getWidth();
        int height = img.getHeight();
        int h = img.getHeight();




        BufferedImage bim = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bim.createGraphics();
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(qualityHints);
        g2.setClip(new RoundRectangle2D.Double(0.0, 0.0, width, height, width, height));
        g2.drawImage(img, 0, 0, null);
        g2.dispose();
        return bim;
    }
}
