package polytech.qgl;


import flag.game.*;
import polytech.qgl.players.SimpleIntermediatePlayer;
import polytech.qgl.players.SimplePhasePlayer;
import polytech.qgl.players.SimplePlayer;

public class Launcher {

    public static void main(String[] args) {

        System.out.println("####\n## Flag Capture\n####\n");

        GameMap map = GameMap.build(6);

        Player player;
        double score;

        System.out.println("\nPlaying with SimplePlayer");
        player = new SimplePlayer();
        score = player.run(new Game(map));
        System.out.println(String.format("Score: %.2f", score));

        System.out.println("\nPlaying with SimpleIntermediatePlayer");
        player = new SimpleIntermediatePlayer();
        score = player.run(new Game(map));
        System.out.println(String.format("Score: %.2f", score));

        System.out.println("\nPlaying with SimplePlayer");
        player = new SimplePhasePlayer();
        score = player.run(new Game(map));
        System.out.println(String.format("Score: %.2f", score));

    }

}