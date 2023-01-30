public class CsvReadException extends Exception {
    private String data;

    public CsvReadException(String input) {
        data = input;
    }

    @Override
    public String toString() {
        return "CsvReadException{" +
                "data='" + data + '\'' +
                '}';
    }
}