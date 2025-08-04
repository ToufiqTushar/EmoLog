package emolog;

public class EmotionAnalyzer {

    public static String analyzeEmotion(String text) {
        if (text == null || text.isBlank()) return "😐 Neutral - Emoni: MindCloud";

        String lower = text.toLowerCase();

        if (containsAny(lower, "happy", "joy", "great", "awesome", "excited", "delighted", "fantastic", "smile", "wonderful",
                "pleased", "fun", "cheerful", "amazing", "blessed", "thrilled", "content", "elated", "glad", "joyful", "laugh", "love"))
            return "😊 Happy - Emoni: JoySpark";

        if (containsAny(lower, "sad", "depressed", "cry", "down", "unhappy", "heartbroken", "tear", "lonely", "gloomy",
                "blue", "miserable", "disappointed", "hurt", "grief", "sorrow", "melancholy", "tragic", "bad", "upset"))
            return "😢 Sad - Emoni: TearDrop";

        if (containsAny(lower, "angry", "mad", "furious", "annoyed", "irritated", "rage", "fuming", "resent", "hate",
                "frustrated", "offended", "cross", "wrath", "enraged", "boiling", "upset", "outraged"))
            return "😠 Angry - Emoni: FireStorm";

        if (containsAny(lower, "fear", "scared", "afraid", "worried", "nervous", "anxious", "panic", "terrified",
                "frightened", "uneasy", "alarm", "dread", "apprehensive", "tense", "concerned"))
            return "😨 Fearful - Emoni: ShadowVeil";

        if (containsAny(lower, "surprise", "shocked", "amazed", "astonished", "startled", "stunned", "wow", "unexpected",
                "astounded", "speechless"))
            return "😲 Surprised - Emoni: FlashBang";

        return "😐 Neutral - Emoni: MindCloud";
    }

    private static boolean containsAny(String text, String... keywords) {
        for (String word : keywords) {
            if (text.contains(word)) return true;
        }
        return false;
    }
}
