public class CRC {
    public static void main(String[] args) {
        String data = "1101100110";
        String polynomial = "100110";

        // Adiciona zeros ao final dos dados
        String dataPadded = data + "0".repeat(polynomial.length() - 1);

        // Calcula o CRC
        String crc = calculateCRC(dataPadded, polynomial);

        System.out.println("CRC: " + crc);
    }

    public static String calculateCRC(String data, String polynomial) {
        int[] dividend = new int[data.length()];
        int[] divisor = new int[polynomial.length()];

        // Converte strings binárias para arrays de inteiros
        for (int i = 0; i < data.length(); i++) {
            dividend[i] = Character.getNumericValue(data.charAt(i));
        }

        for (int i = 0; i < polynomial.length(); i++) {
            divisor[i] = Character.getNumericValue(polynomial.charAt(i));
        }

        // Realiza a divisão binária
        for (int i = 0; i <= dividend.length - divisor.length; i++) {
            if (dividend[i] == 1) {  // Realiza XOR somente se o bit mais à esquerda for 1
                for (int j = 0; j < divisor.length; j++) {
                    dividend[i + j] ^= divisor[j];
                }
            }
        }

        // O resto da divisão é a parte não dividida
        StringBuilder remainder = new StringBuilder();
        for (int i = dividend.length - (divisor.length - 1); i < dividend.length; i++) {
            remainder.append(dividend[i]);
        }

        return remainder.toString();
    }
}
