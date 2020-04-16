import java.util.*;

public class Main {
    public static Map<String, String> binToStrMap = new HashMap<>();
    public static Map<String, String> strToBinMap = new HashMap<>();
    public static int deg = 0;
    public static int len = 0;

    public static void main(String[] args) {
        String[] strs = args[0].split(",");
        // 获取 01 串长度
        deg = deg(strs.length);
        len = strs[0].length();
        List<String> binStrs = gainBinaryKeys(deg);
        gainMaps(strs, binStrs);
        System.out.println(binToStrMap);
        System.out.println(strToBinMap);
        String s = "你好中国";
        List<String> encodedWords = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            encodedWords.add(encodeCharacter(s.charAt(i)));
        }
        for (String word : encodedWords) {
            System.out.println(decodeCharacter(word));

        }
    }

    // 加密
    public static String encodeCharacter(char c) {
        StringBuilder encodedWords = new StringBuilder();
        System.out.println(c);
        List<String> separatedBins = stringSpilt(toUnicode(c), deg);
        for (String binKey : separatedBins) {
            encodedWords.append(binToStrMap.get(binKey));
        }
        System.out.println(encodedWords);
        return encodedWords.toString();
    }

    // 解密
    public static char decodeCharacter(String encodedBin) {
        StringBuilder bins = new StringBuilder();
        List<String> separatedWords = splitEncodedBin(encodedBin);
        for (String word : separatedWords) {
            bins.append(strToBinMap.get(word));
        }
        int parseInt = Integer.parseInt(bins.toString(), 2);
        return (char) parseInt;
    }

    // 分割加密后的串
    public static List<String> splitEncodedBin(String encodedBin) {
        return stringSpilt(encodedBin, len);
    }

    // 生成 bin to str 和 to bin 的map
    public static void gainMaps(String[] strs, List<String> binStrs) {
        for (int i = 0; i < strs.length; ++i) {
            binToStrMap.put(binStrs.get(i), strs[i]);
            strToBinMap.put(strs[i], binStrs.get(i));
        }
    }

    // 获取能够作为 key 的二进制串list
    public static List<String> gainBinaryKeys(int deg) {

        // 能表示的01串最大数量
        int max = (int) (Math.pow(2, deg));

        List<String> keys = new ArrayList<>();
        for (int i = 0; i < max; ++i) {
            String s = toUnicode(i);
            keys.add(s.substring(s.length() - deg));
        }
        return keys;
    }

    // 获取可以完全表示的01串的长度
    public static int deg(int length) {
        return (int) (Math.floor(Math.log(length) / Math.log(2)));
    }

    //转为16位的二进制unicode码
    public static String toUnicode(int s) {
        String binary = Integer.toBinaryString(s);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 16 - binary.length(); ++i) {
            builder.append('0');
        }
        builder.append(binary);
        return builder.toString();
    }

    public static List<String> stringSpilt(String s, int spiltNum) {
        int startIndex = 0;
        int endIndex = spiltNum;
        int lenght = s.length();
        List<String> subs = new ArrayList<>();//创建可分割数量的数组
        boolean isEnd = true;

        while (isEnd) {

            if (endIndex >= lenght) {
                endIndex = lenght;
                isEnd = false;
            }
            subs.add(s.substring(startIndex, endIndex));
            startIndex = endIndex;
            endIndex = endIndex + spiltNum;
        }
        return subs;
    }
}
