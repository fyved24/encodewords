import java.util.*;

public class Encoder {

    public Map<String, String> binToStrMap = new HashMap<>();
    public Map<String, String> strToBinMap = new HashMap<>();
    public int deg = 0;
    // 一个key的长度
    public int len = 0;
    // 用于分割加密后字符串的长度
    public int splitLen = 0;
    // key
    public String keys;

    public void setKeys(String keys) {
        this.keys = keys;
        String[] strs = keys.split(",");
        // 获取 01 串长度
        deg = deg(strs.length);
        len = strs[0].length();
        List<String> binStrs = gainBinaryKeys(deg);
        gainMaps(strs, binStrs);
    }

    // 加密
    public String encodeCharacter(char c) {
        StringBuilder encodedWords = new StringBuilder();
        String unicode = toUnicode(c);
        // 在01串有剩余时，在前面补0，使得可以被16整除
        if (unicode.length() % deg != 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < deg - (16 % deg); ++i) {
                builder.append('0');
            }
            unicode = builder.toString() +  unicode;
        }
        List<String> separatedBins = stringSpilt(unicode, deg);
        for (String binKey : separatedBins) {
            encodedWords.append(binToStrMap.get(binKey));
        }
        splitLen = encodedWords.toString().length();
        return encodedWords.toString();
    }

    // 解密
    public char decodeCharacter(String encodedBin) {
        // 富强自由法治平等自由富强
        StringBuilder bins = new StringBuilder();
        List<String> separatedWords = splitEncodedBin(encodedBin);
        for (String word : separatedWords) {
            bins.append(strToBinMap.get(word));
        }
        String t = bins.toString().substring(deg - (16 % deg));
        int parseInt = Integer.parseInt(t, 2);
        return (char) parseInt;
    }

    // 加密段落
    public String encodeParagraph(String paragraph) {
        StringBuilder encode = new StringBuilder();
        for (int i = 0; i < paragraph.length(); ++i) {
            encode.append(encodeCharacter(paragraph.charAt(i)));
        }
        return encode.toString();
    }

    // 解密段落
    public String decodeParagraph(String paragraph) {
        List<String> words = stringSpilt(paragraph, splitLen);
        StringBuilder decode = new StringBuilder();
        for (String word : words) {
            decode.append(decodeCharacter(word));
        }
        return decode.toString();
    }

    /**
     * 将所给的解密后的串分割
     * @param encodedBin 如 富强民主文明和谐自由平等公正法治
     * @return  [富强,民主,文明,和谐,自由,平等,公正,法治]
     */
    public List<String> splitEncodedBin(String encodedBin) {
        return stringSpilt(encodedBin, len);
    }

    // 生成 bin to str 和 to bin 的map
    public void gainMaps(String[] strs, List<String> binStrs) {
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
