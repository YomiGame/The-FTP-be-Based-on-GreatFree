package trycloud.message;

public class ByteToAllByte {
    private byte[] allByte;
    public ByteToAllByte(byte[]... values)
    {
        int length_byte = 0;
        for (int i = 0; i < values.length; i++) {
            length_byte += values[i].length;
        }
        byte[] all_byte = new byte[length_byte];
        int countLength = 0;
        for (int i = 0; i < values.length; i++) {
            byte[] b = values[i];
            System.arraycopy(b, 0, all_byte, countLength, b.length);
            countLength += b.length;
        }
        this.allByte = all_byte;

    }
    public byte[] getAllByte()
    {
        return this.allByte;
    }

}
