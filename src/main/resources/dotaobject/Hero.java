package main.resources.dotaobject;

import java.util.HashMap;
import java.util.Map;

public class Hero {
    // https://liquipedia.net/dota2/MediaWiki:Dota2webapi-heroes.json


    public Hero(int id, String name, String localizedName, AttributeType primaryAttribute, String attackType,
                String[] roles, boolean captainsMode, int[] picks, int[] wins, int proBans, int[][] rolesPicks, int[][] rolesWins) {
        this.id = id;
        this.name = name;
        this.localizedName = localizedName;
        this.primaryAttribute = primaryAttribute;
        this.attackType = attackType;
        this.roles = roles;
        this.captainsMode = captainsMode;
        this.picks = picks;
        this.wins = wins;
        this.proBans = proBans;
        this.rolesPicks = rolesPicks;
        this.rolesWins = rolesWins;
    }

    private final int id;
    private final String name;
    private final String localizedName;
    private final AttributeType primaryAttribute;
    public enum AttributeType {
        STRENGTH("str"),
        AGILITY("agi"),
        INTELLIGENCE("int");
        private final String value;
        AttributeType(String value) {
            this.value = value;
        }
        private static final Map<String, Hero.AttributeType> map = new HashMap<>();
        static {
            for (Hero.AttributeType attributeType : Hero.AttributeType.values()) {
                map.put(attributeType.value, attributeType);
            }
        }
        public static Hero.AttributeType convert(String value) {
            return (Hero.AttributeType) map.get(value);
        }
    }
    private final String attackType;
    private final String[] roles;
    /*private final String img;
    private final String icon; TODO need to fix this, these should be images */
    private boolean captainsMode;

    private int[] picks;
    private int[] wins;
    private int proBans;
    // herald, guardian, crusader, archon, legend, ancient, divine, immortal, pro

    enum LaneRoles {
        SAFELANE,
        MIDLANE,
        OFFLANE,
        JUNGLE
    }

    private int[][] rolesPicks;
    // [role][time]
    private int[][] rolesWins;


    @Override
    public String toString() {
        return this.localizedName;
    }

    public float winPercentage(Player.PlayerRank rank) {
        // Hero win percentage in a certain skill bracket
        int picks_ = picks[rank.ordinal()];
        int wins_ = wins[rank.ordinal()];
        return ((float) wins_ / (float) picks_);
    }
    public float winPercentage() {
        // General hero win percentage, excludes pro data because of small sample size and skewed data
        int picks_ = 0;
        int wins_ = 0;
        for (int i = 0; i < Player.PlayerRank.values().length - 1; i++) {
            picks_ += picks[i];
            wins_ += wins[i];
        }
        return ((float) wins_ / (float) picks_);
    }

}
