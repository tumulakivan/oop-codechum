package Influencer;

abstract class Influencer {
    private String name;
    private String platform;

    public Influencer(String name, String platform) {
        this.name = name;
        if(platform != "Facebook" || platform != "Tiktok") this.platform = "Facebook";
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "I'm " + this.name + " an influencer at " + this.platform;
    }

    abstract void doLiveStream();
}

class FacebookInfluencer extends Influencer {
    public FacebookInfluencer(String name) {
        super(name, "Facebook");
    }

    @Override
    public void doLiveStream() {
        System.out.println("Doing livestream on Facebook, please send stars");
    }
}

class TiktokInfluencer extends Influencer {
    public TiktokInfluencer(String name) {
        super(name, "Tiktok");
    }

    @Override
    public void doLiveStream() {
        System.out.println("Doing livestream on Tiktok, please send love");
    }
}