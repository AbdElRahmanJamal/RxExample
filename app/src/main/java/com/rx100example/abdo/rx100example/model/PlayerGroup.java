package com.rx100example.abdo.rx100example.model;

import java.util.List;

public class PlayerGroup {
    private String position;
    private List<Player> players;

    public PlayerGroup(String position, List<Player> players) {
        this.position = position;
        this.players = players;
    }
    public String getPosition() {
        return position;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
