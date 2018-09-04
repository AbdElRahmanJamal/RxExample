package com.rx100example.abdo.rx100example.model;

public class Player {

    private String name;
    private String team;
    private String position;

    public Player() {
    }

    public Player(String team, String name, String position) {
        this.name = name;
        this.team = team;
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return this.getTeam() + " " + this.getName() + " " + this.getPosition();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Player)) {
            return false;
        }
        Player player1 = (Player) obj;
        return player1.getName().equals(this.getName()) &&
                player1.getPosition().equals(this.getPosition()) &&
                player1.getTeam().equals(this.getTeam());
    }
}
