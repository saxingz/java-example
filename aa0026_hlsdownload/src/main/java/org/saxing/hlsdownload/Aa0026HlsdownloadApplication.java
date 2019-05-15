package org.saxing.hlsdownload;


import java.util.List;
import java.util.Random;

public class Aa0026HlsdownloadApplication {

    public static void main(String[] args) throws InterruptedException {

        List<String> res = Down026.getList();

        for (int i = 0; i < res.size() / 2; i++) {
            String path = res.get(2 * i);
            String fileName = res.get(2 * i + 1) + ".mp4";


            System.out.println("\n\n ------------------------------------------------------------ \n ");
            System.out.println("path: " + path);
            System.out.println("fileName: " + fileName);

            download(path, fileName);

            Thread.sleep(new Random().nextInt(1000) + 1000);
        }

    }

    private static void download(String originUrlpath, String fileName){
        String preUrlPath = originUrlpath.substring(0, originUrlpath.lastIndexOf("/")+1);
        String rootPath = "E:\\videodir";
        HlsDownloader downLoader = new HlsDownloader(originUrlpath, preUrlPath, rootPath, fileName);
        downLoader.setThreadQuantity(4);
        try{
            fileName = downLoader.download(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if(fileName.isEmpty()){
            System.err.println(" --------------------------------- ");
            System.err.println("下载失败");
            System.err.println(" --------------------------------- ");
        }else{
            System.out.println("下载成功: " + rootPath + fileName);
        }
    }

}
