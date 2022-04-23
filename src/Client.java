import lenz.htw.gaap.net.NetworkClient;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException {

        NetworkClient client = new NetworkClient("ipadr", "Name", ImageIO.read(new File("C:\\Users\\wilde\\Pictures\\Wallpaper\\pb")));



    }


}
