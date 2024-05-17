import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("masukkan path gambar : ");
        String imagePath = scanner.nextLine();

        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            if (image == null) {
                System.out.println("Gambar tidak ditemukan");
                return;
            }

            Map<Integer, Integer> colorFrequencyMap = new HashMap<>();

            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    int pixelColor = image.getRGB(x, y);
                    colorFrequencyMap.put(pixelColor, colorFrequencyMap.getOrDefault(pixelColor, 0) + 1);

                }
            }

            int dominantColor = 0;
            int maxFrequency = 0;

            for (Map.Entry<Integer, Integer> entry : colorFrequencyMap.entrySet()) {
                if (entry.getValue() > maxFrequency) {
                    dominantColor = entry.getKey();
                    maxFrequency = entry.getValue();
                }
            }

            System.out.println("Warna yang muncul pada gambar:");
            for (Map.Entry<Integer, Integer> entry : colorFrequencyMap.entrySet()) {

                System.out.println("Warna: #" + String.format("%06X", entry.getKey() & 0xFFFFFF) +
                        ", Frekuensi: " + entry.getValue());
            }

            System.out.println("Warna yang paling dominan: #" + String.format("%06X", dominantColor & 0xFFFFFF) +
                    ", Frekuensi: " + maxFrequency);

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat memuat gambar: " + e.getMessage());
        }
    }
}
