import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Music {
    public Music() throws FileNotFoundException, JavaLayerException {
        Player player;
        //拼接音乐路径
        String str = System.getProperty("user.dir")+ "/src/Music/music.wav";

        BufferedInputStream name = new BufferedInputStream(new FileInputStream(str));
        player = new Player(name);
        player.play();

    }
}
