import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Correlation {

    public static byte[] rotateRight(byte[] src) {
        byte[] result = new byte[src.length];

        byte lastBit;
        for (int i = 0; i < src.length; i++) {
            if (i == 0) {
                lastBit = (byte) ((int) src[src.length - 1] & 1);
            } else {
                lastBit = (byte) ((int) src[i - 1] & 1);
            }

            result[i] = (byte) ((((int) src[i] >> 1) & ~(byte) 128) | ((int) lastBit << 7));
        }

        return result;
    }

    public static double countCorrelation(byte[] first, byte[] second) {
        int count = 0;
        int size = first.length;
        for (int i = 0; i < size; i++) {
            int inputByte = first[i] & 0xFF;
            int outputByte = second[i] & 0xFF;
            for (int j = 0; j < 8; j++) {
                int tmpIn = (inputByte >>> j) & 1;
                int tmpOut = (outputByte >>> j) & 1;
                count += (2 * tmpIn - 1) * (2 * tmpOut - 1);
//                if (tmpIn == tmpOut) count++;
            }
        }
        double N = size * 8;

//        return (count - (N - count)) / (N);
        return count / N;
    }

    public static double[] autoCorrelation(byte[] data) {
        int size = data.length;
        double[] corelTest = new double[size * 8 + 1];
        byte[] shifted = Arrays.copyOf(data, data.length);

        for (int k = 0; k <= size * 8; k++) {
            System.out.println(toBitString(shifted));
            corelTest[k] = countCorrelation(shifted, data);
            shifted = rotateRight(shifted);
        }

        return corelTest;
    }

    public static String toBitString(final byte[] b) {
        final char[] bits = new char[8 * b.length];
        for (int i = 0; i < b.length; i++) {
            final byte byteval = b[i];
            int bytei = i << 3;
            int mask = 0x1;
            for (int j = 7; j >= 0; j--) {
                final int bitval = byteval & mask;
                if (bitval == 0) {
                    bits[bytei + j] = '0';
                } else {
                    bits[bytei + j] = '1';
                }
                mask <<= 1;
            }
        }
        return String.valueOf(bits);
    }

    static void writeTxtBytes(byte[] bytes, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
