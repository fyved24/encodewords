public class App {
    public static void main(String[] args) {
        Encoder encoder = new Encoder();
        encoder.setKeys("嗷,呜,啊,~");
        String s2 = encoder.encodeParagraph("你好中国，你好世界！");
        System.out.println(s2);
        String s3 = encoder.decodeParagraph(s2);
        System.out.println(s3);
    }
}
