public class App {
    public static void main(String[] args) {
        Encoder encoder = new Encoder();
        // keys 只能是2的次方
        encoder.setKeys("富强,民主,文明,和谐,自由,平等,公正,法治");
        String words = encoder.encodeParagraph("你好中国，你好世界！");
        System.out.println(words);
        String s3 = encoder.decodeParagraph(words);
        System.out.println(s3);
    }
}
