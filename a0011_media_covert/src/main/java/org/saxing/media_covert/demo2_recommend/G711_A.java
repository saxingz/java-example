package org.saxing.media_covert.demo2_recommend;

import java.io.*;

public class G711_A {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\saxing\\Desktop\\media_server\\test_audio\\123-abc-123-out.PCMA");
        byte[] bytes = toByteArray(file);
        int length = bytes.length;

        byte[] newFile = new byte[length*2];
        for (int i = 0; i < length; i++) {
//            newFile[i] = shortToByte();
            g711_alaw2linear((char) bytes[i]);
            newFile[2*i] = shortToByte(g711_alaw2linear((char) bytes[i]))[0];
            newFile[2*i+1] = shortToByte(g711_alaw2linear((char) bytes[i]))[1];

        }

        exportFile(newFile, "d:\\", "myout22.pcm");
    }

    public static byte[] shortToByte(short number) {
        int temp = number;
        byte[] b = new byte[2]; // 将最低位保存在最低位
        b[0] = (byte)(temp & 0xff);
        temp = temp >> 8; // 向右移8位
        b[1] = (byte)(temp & 0xff);
        return b;
    }

    static short g711_alaw2linear(  char alaw )
    {
        // PCM A-Law 解码
        int sign;
        int data;
        int exponent;

        //Invert every other bit,
        //and the sign bit (0xD5 = 1101 0101)
        alaw ^= 0xD5;

        //Pull out the value of the sign bit
        sign = alaw & 0x80;
        //Pull out and shift over the value of the exponent
        exponent = (alaw & 0x70) >> 4;
        //Pull out the four bits of data
        data = alaw & 0x0f;

        //Shift the data four bits to the left
        data <<= 4;
        //Add 8 to put the result in the middle
        //of the range (like adding a half)
        data += 8;

        //If the exponent is not 0, then we know the four bits followed a 1,
        //and can thus add this implicit 1 with 0x100.
        if (exponent != 0) {
            data += 0x100;
        }
        /* Shift the bits to where they need to be: left (exponent - 1) places
         * Why (exponent - 1) ?
         * 1 2 3 4 5 6 7 8 9 A B C D E F G
         * . 7 6 5 4 3 2 1 . . . . . . . . <-- starting bit (based on exponent)
         * . . . . . . . Z x x x x 1 0 0 0 <-- our data (Z is 0 only when      * exponent is 0)
         * We need to move the one under the value of the exponent,
         * which means it must move (exponent - 1) times
         * It also means shifting is unnecessary if exponent is 0 or 1.
         */
        if (exponent > 1) {
            data <<= (exponent - 1);
        }

        return (short) (sign == 0 ? data : -data);
    }




    /*读取文件的字节数组*/
    public static byte[] toByteArray(File file) throws IOException {
        File f = file;
        if (!f.exists()) {
            throw new FileNotFoundException("file not exists");
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }


    /**
     * 根据byte数组，生成文件
     */
    public static void exportFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath+"\\"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


}
