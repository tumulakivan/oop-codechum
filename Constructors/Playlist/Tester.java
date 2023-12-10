public class Tester {
    public Tester() {
    }

    public static void test(Music music, int duration, char durationType, int constructorType) {
        if (constructorType == 1 || durationType == 'm') {
            Music newMusic = new Music(music.duration, music.genre);

            if (newMusic.duration == music.duration && newMusic.genre == music.genre) {
                System.out.println("PASSED 1");
                int testDuration = 250;
                String testGenre = "TestGenre";

                Music testMusic = new Music(testDuration, testGenre);

                if (testMusic.duration == testDuration && testMusic.genre == testGenre) {
                    System.out.println("PASSED 2");
                    System.out.println("SUCCESS");
                    return;
                }
            }
        } else {
            int testDuration0 = 10;
            char testDurationType0 = 'h';
            String genre0 = "TestGenre";
            int expectedDuration0 = 10 * 60;

            Music testMusic0 = new Music(testDuration0, genre0, testDurationType0);

            if (testMusic0.duration == expectedDuration0 && testMusic0.genre == genre0) {
                System.out.println("PASSED 1");
                int testDuration1 = 2;
                char testDurationType1 = 'd';
                String genre1 = "TestGenre";
                int expectedDuration1 = 2 * 1440;

                Music testMusic1 = new Music(testDuration1, genre1, testDurationType1);

                if (testMusic1.duration == expectedDuration1 && testMusic1.genre == genre1) {
                    System.out.println("PASSED 2");
                    System.out.println("SUCCESS");
                    return;
                }
            }
        }

        System.out.println("FAILED");
    }
}