package TimeWaste;

abstract class SocialMedia {
    private String name;
    private int yearCreated;

    public SocialMedia(String name, int yearCreated) {
        name = Character.toUpperCase(name.charAt(0)) + name.substring(1);

        this.name = name;
        this.yearCreated = yearCreated;
    }

    public abstract Item[] getFeed(int itemsCount);
    public abstract Item[] getFeed();

    @Override
    public String toString() {
        return this.name + " created last " + yearCreated;
    }
}

class Reddit extends SocialMedia {
    private static final int YEAR_CREATED = 2005;
    private static final String NAME = "Reddit";

    public Reddit() {
        super(NAME, YEAR_CREATED);
    }

    @Override
    public Item[] getFeed(int itemsCount) {
        Item[] feed = new Item[itemsCount];
        for(int i = 0; i < itemsCount; i++) {
            feed[i] = new Item("Reddit Item Title " + i, "Reddit Item Description " + i);
        }

        return feed;
    }

    @Override
    public Item[] getFeed() {
        Item[] feed = new Item[10];
        for(int i = 0; i < feed.length; i++) {
            feed[i] = new Item("Reddit Item Title " + i, "Reddit Item Description " + i);
        }

        return feed;
    }
}

class Facebook extends SocialMedia {
    private static final String NAME = "FaCeBoOk";
    private static final int YEAR_CREATED = 2004;

    public Facebook() {
        super(NAME, YEAR_CREATED);
    }

    @Override
    public Item[] getFeed(int itemsCount) {
        Item[] feed = new Item[itemsCount];
        for(int i = 0; i < itemsCount; i++) {
            feed[i] = new Item("Facebook Item Title " + i, "Facebook Item Description " + i);
        }

        return feed;
    }

    @Override
    public Item[] getFeed() {
        Item[] feed = new Item[10];
        for(int i = 0; i < feed.length; i++) {
            feed[i] = new Item("Facebook Item Title " + i, "Facebook Item Description " + i);
        }

        return feed;
    }
}